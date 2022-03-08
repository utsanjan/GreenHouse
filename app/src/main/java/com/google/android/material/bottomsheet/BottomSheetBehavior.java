package com.google.android.material.bottomsheet;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private static final int CORNER_ANIMATION_DURATION = 500;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_BottomSheet_Modal;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "BottomSheetBehavior";
    int activePointerId;
    private final ArrayList<BottomSheetCallback> callbacks;
    int collapsedOffset;
    private final ViewDragHelper.Callback dragCallback;
    float elevation;
    int expandedOffset;
    private boolean fitToContents;
    int fitToContentsOffset;
    int halfExpandedOffset;
    float halfExpandedRatio;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private ValueAnimator interpolatorAnimator;
    private boolean isShapeExpanded;
    private int lastNestedScrollDy;
    private MaterialShapeDrawable materialShapeDrawable;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    int parentHeight;
    int parentWidth;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightMin;
    private int saveFlags;
    private BottomSheetBehavior<V>.SettleRunnable settleRunnable;
    private ShapeAppearanceModel shapeAppearanceModelDefault;
    private boolean shapeThemingEnabled;
    private boolean skipCollapsed;
    int state;
    boolean touchingScrollingChild;
    private VelocityTracker velocityTracker;
    ViewDragHelper viewDragHelper;
    WeakReference<V> viewRef;

    /* loaded from: classes.dex */
    public static abstract class BottomSheetCallback {
        public abstract void onSlide(View view, float f);

        public abstract void onStateChanged(View view, int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface SaveFlags {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface State {
    }

    public BottomSheetBehavior() {
        this.saveFlags = 0;
        this.fitToContents = true;
        this.settleRunnable = null;
        this.halfExpandedRatio = HIDE_THRESHOLD;
        this.elevation = -1.0f;
        this.state = 4;
        this.callbacks = new ArrayList<>();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.3
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View child, int pointerId) {
                if (BottomSheetBehavior.this.state == 1 || BottomSheetBehavior.this.touchingScrollingChild) {
                    return false;
                }
                if (BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == pointerId) {
                    View scroll = BottomSheetBehavior.this.nestedScrollingChildRef != null ? BottomSheetBehavior.this.nestedScrollingChildRef.get() : null;
                    if (scroll != null && scroll.canScrollVertically(-1)) {
                        return false;
                    }
                }
                return BottomSheetBehavior.this.viewRef != null && BottomSheetBehavior.this.viewRef.get() == child;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                BottomSheetBehavior.this.dispatchOnSlide(top);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int state) {
                if (state == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                int targetState;
                int top;
                if (yvel < 0.0f) {
                    if (BottomSheetBehavior.this.fitToContents) {
                        top = BottomSheetBehavior.this.fitToContentsOffset;
                        targetState = 3;
                    } else if (releasedChild.getTop() > BottomSheetBehavior.this.halfExpandedOffset) {
                        int top2 = BottomSheetBehavior.this.halfExpandedOffset;
                        top = top2;
                        targetState = 6;
                    } else {
                        int top3 = BottomSheetBehavior.this.expandedOffset;
                        top = top3;
                        targetState = 3;
                    }
                } else if (!BottomSheetBehavior.this.hideable || !BottomSheetBehavior.this.shouldHide(releasedChild, yvel) || (releasedChild.getTop() <= BottomSheetBehavior.this.collapsedOffset && Math.abs(xvel) >= Math.abs(yvel))) {
                    int top4 = (yvel > 0.0f ? 1 : (yvel == 0.0f ? 0 : -1));
                    if (top4 == 0 || Math.abs(xvel) > Math.abs(yvel)) {
                        int currentTop = releasedChild.getTop();
                        if (BottomSheetBehavior.this.fitToContents) {
                            if (Math.abs(currentTop - BottomSheetBehavior.this.fitToContentsOffset) < Math.abs(currentTop - BottomSheetBehavior.this.collapsedOffset)) {
                                int top5 = BottomSheetBehavior.this.fitToContentsOffset;
                                top = top5;
                                targetState = 3;
                            } else {
                                int top6 = BottomSheetBehavior.this.collapsedOffset;
                                top = top6;
                                targetState = 4;
                            }
                        } else if (currentTop < BottomSheetBehavior.this.halfExpandedOffset) {
                            if (currentTop < Math.abs(currentTop - BottomSheetBehavior.this.collapsedOffset)) {
                                int top7 = BottomSheetBehavior.this.expandedOffset;
                                top = top7;
                                targetState = 3;
                            } else {
                                int top8 = BottomSheetBehavior.this.halfExpandedOffset;
                                top = top8;
                                targetState = 6;
                            }
                        } else if (Math.abs(currentTop - BottomSheetBehavior.this.halfExpandedOffset) < Math.abs(currentTop - BottomSheetBehavior.this.collapsedOffset)) {
                            int top9 = BottomSheetBehavior.this.halfExpandedOffset;
                            top = top9;
                            targetState = 6;
                        } else {
                            int top10 = BottomSheetBehavior.this.collapsedOffset;
                            top = top10;
                            targetState = 4;
                        }
                    } else if (BottomSheetBehavior.this.fitToContents) {
                        top = BottomSheetBehavior.this.collapsedOffset;
                        targetState = 4;
                    } else {
                        int currentTop2 = releasedChild.getTop();
                        if (Math.abs(currentTop2 - BottomSheetBehavior.this.halfExpandedOffset) < Math.abs(currentTop2 - BottomSheetBehavior.this.collapsedOffset)) {
                            int top11 = BottomSheetBehavior.this.halfExpandedOffset;
                            top = top11;
                            targetState = 6;
                        } else {
                            int top12 = BottomSheetBehavior.this.collapsedOffset;
                            top = top12;
                            targetState = 4;
                        }
                    }
                } else {
                    top = BottomSheetBehavior.this.parentHeight;
                    targetState = 5;
                }
                BottomSheetBehavior.this.startSettlingAnimation(releasedChild, targetState, top, true);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View child, int top, int dy) {
                return MathUtils.clamp(top, BottomSheetBehavior.this.getExpandedOffset(), BottomSheetBehavior.this.hideable ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return child.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View child) {
                if (BottomSheetBehavior.this.hideable) {
                    return BottomSheetBehavior.this.parentHeight;
                }
                return BottomSheetBehavior.this.collapsedOffset;
            }
        };
    }

    public BottomSheetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.saveFlags = 0;
        this.fitToContents = true;
        this.settleRunnable = null;
        this.halfExpandedRatio = HIDE_THRESHOLD;
        this.elevation = -1.0f;
        this.state = 4;
        this.callbacks = new ArrayList<>();
        this.dragCallback = new ViewDragHelper.Callback() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.3
            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public boolean tryCaptureView(View child, int pointerId) {
                if (BottomSheetBehavior.this.state == 1 || BottomSheetBehavior.this.touchingScrollingChild) {
                    return false;
                }
                if (BottomSheetBehavior.this.state == 3 && BottomSheetBehavior.this.activePointerId == pointerId) {
                    View scroll = BottomSheetBehavior.this.nestedScrollingChildRef != null ? BottomSheetBehavior.this.nestedScrollingChildRef.get() : null;
                    if (scroll != null && scroll.canScrollVertically(-1)) {
                        return false;
                    }
                }
                return BottomSheetBehavior.this.viewRef != null && BottomSheetBehavior.this.viewRef.get() == child;
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                BottomSheetBehavior.this.dispatchOnSlide(top);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewDragStateChanged(int state) {
                if (state == 1) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                int targetState;
                int top;
                if (yvel < 0.0f) {
                    if (BottomSheetBehavior.this.fitToContents) {
                        top = BottomSheetBehavior.this.fitToContentsOffset;
                        targetState = 3;
                    } else if (releasedChild.getTop() > BottomSheetBehavior.this.halfExpandedOffset) {
                        int top2 = BottomSheetBehavior.this.halfExpandedOffset;
                        top = top2;
                        targetState = 6;
                    } else {
                        int top3 = BottomSheetBehavior.this.expandedOffset;
                        top = top3;
                        targetState = 3;
                    }
                } else if (!BottomSheetBehavior.this.hideable || !BottomSheetBehavior.this.shouldHide(releasedChild, yvel) || (releasedChild.getTop() <= BottomSheetBehavior.this.collapsedOffset && Math.abs(xvel) >= Math.abs(yvel))) {
                    int top4 = (yvel > 0.0f ? 1 : (yvel == 0.0f ? 0 : -1));
                    if (top4 == 0 || Math.abs(xvel) > Math.abs(yvel)) {
                        int currentTop = releasedChild.getTop();
                        if (BottomSheetBehavior.this.fitToContents) {
                            if (Math.abs(currentTop - BottomSheetBehavior.this.fitToContentsOffset) < Math.abs(currentTop - BottomSheetBehavior.this.collapsedOffset)) {
                                int top5 = BottomSheetBehavior.this.fitToContentsOffset;
                                top = top5;
                                targetState = 3;
                            } else {
                                int top6 = BottomSheetBehavior.this.collapsedOffset;
                                top = top6;
                                targetState = 4;
                            }
                        } else if (currentTop < BottomSheetBehavior.this.halfExpandedOffset) {
                            if (currentTop < Math.abs(currentTop - BottomSheetBehavior.this.collapsedOffset)) {
                                int top7 = BottomSheetBehavior.this.expandedOffset;
                                top = top7;
                                targetState = 3;
                            } else {
                                int top8 = BottomSheetBehavior.this.halfExpandedOffset;
                                top = top8;
                                targetState = 6;
                            }
                        } else if (Math.abs(currentTop - BottomSheetBehavior.this.halfExpandedOffset) < Math.abs(currentTop - BottomSheetBehavior.this.collapsedOffset)) {
                            int top9 = BottomSheetBehavior.this.halfExpandedOffset;
                            top = top9;
                            targetState = 6;
                        } else {
                            int top10 = BottomSheetBehavior.this.collapsedOffset;
                            top = top10;
                            targetState = 4;
                        }
                    } else if (BottomSheetBehavior.this.fitToContents) {
                        top = BottomSheetBehavior.this.collapsedOffset;
                        targetState = 4;
                    } else {
                        int currentTop2 = releasedChild.getTop();
                        if (Math.abs(currentTop2 - BottomSheetBehavior.this.halfExpandedOffset) < Math.abs(currentTop2 - BottomSheetBehavior.this.collapsedOffset)) {
                            int top11 = BottomSheetBehavior.this.halfExpandedOffset;
                            top = top11;
                            targetState = 6;
                        } else {
                            int top12 = BottomSheetBehavior.this.collapsedOffset;
                            top = top12;
                            targetState = 4;
                        }
                    }
                } else {
                    top = BottomSheetBehavior.this.parentHeight;
                    targetState = 5;
                }
                BottomSheetBehavior.this.startSettlingAnimation(releasedChild, targetState, top, true);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionVertical(View child, int top, int dy) {
                return MathUtils.clamp(top, BottomSheetBehavior.this.getExpandedOffset(), BottomSheetBehavior.this.hideable ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset);
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return child.getLeft();
            }

            @Override // androidx.customview.widget.ViewDragHelper.Callback
            public int getViewVerticalDragRange(View child) {
                if (BottomSheetBehavior.this.hideable) {
                    return BottomSheetBehavior.this.parentHeight;
                }
                return BottomSheetBehavior.this.collapsedOffset;
            }
        };
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomSheetBehavior_Layout);
        this.shapeThemingEnabled = a.hasValue(R.styleable.BottomSheetBehavior_Layout_shapeAppearance);
        boolean hasBackgroundTint = a.hasValue(R.styleable.BottomSheetBehavior_Layout_backgroundTint);
        if (hasBackgroundTint) {
            ColorStateList bottomSheetColor = MaterialResources.getColorStateList(context, a, R.styleable.BottomSheetBehavior_Layout_backgroundTint);
            createMaterialShapeDrawable(context, attrs, hasBackgroundTint, bottomSheetColor);
        } else {
            createMaterialShapeDrawable(context, attrs, hasBackgroundTint);
        }
        createShapeValueAnimator();
        if (Build.VERSION.SDK_INT >= 21) {
            this.elevation = a.getDimension(R.styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        }
        TypedValue value = a.peekValue(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight);
        if (value == null || value.data != -1) {
            setPeekHeight(a.getDimensionPixelSize(R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight, -1));
        } else {
            setPeekHeight(value.data);
        }
        setHideable(a.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setFitToContents(a.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(a.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        setSaveFlags(a.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        setHalfExpandedRatio(a.getFloat(R.styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, HIDE_THRESHOLD));
        setExpandedOffset(a.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset, 0));
        a.recycle();
        ViewConfiguration configuration = ViewConfiguration.get(context);
        this.maximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public Parcelable onSaveInstanceState(CoordinatorLayout parent, V child) {
        return new SavedState(super.onSaveInstanceState(parent, child), (BottomSheetBehavior<?>) this);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onRestoreInstanceState(CoordinatorLayout parent, V child, Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(parent, child, ss.getSuperState());
        restoreOptionalState(ss);
        if (ss.state == 1 || ss.state == 2) {
            this.state = 4;
        } else {
            this.state = ss.state;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onAttachedToLayoutParams(CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        MaterialShapeDrawable materialShapeDrawable;
        if (ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
            child.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            this.peekHeightMin = parent.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            this.viewRef = new WeakReference<>(child);
            if (this.shapeThemingEnabled && (materialShapeDrawable = this.materialShapeDrawable) != null) {
                ViewCompat.setBackground(child, materialShapeDrawable);
            }
            MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
            if (materialShapeDrawable2 != null) {
                float f = this.elevation;
                if (f == -1.0f) {
                    f = ViewCompat.getElevation(child);
                }
                materialShapeDrawable2.setElevation(f);
                boolean z = this.state == 3;
                this.isShapeExpanded = z;
                this.materialShapeDrawable.setInterpolation(z ? 0.0f : 1.0f);
            }
            updateAccessibilityActions();
            if (ViewCompat.getImportantForAccessibility(child) == 0) {
                ViewCompat.setImportantForAccessibility(child, 1);
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(parent, this.dragCallback);
        }
        int savedTop = child.getTop();
        parent.onLayoutChild(child, layoutDirection);
        this.parentWidth = parent.getWidth();
        int height = parent.getHeight();
        this.parentHeight = height;
        this.fitToContentsOffset = Math.max(0, height - child.getHeight());
        calculateHalfExpandedOffset();
        calculateCollapsedOffset();
        int i = this.state;
        if (i == 3) {
            ViewCompat.offsetTopAndBottom(child, getExpandedOffset());
        } else if (i == 6) {
            ViewCompat.offsetTopAndBottom(child, this.halfExpandedOffset);
        } else if (!this.hideable || i != 5) {
            int i2 = this.state;
            if (i2 == 4) {
                ViewCompat.offsetTopAndBottom(child, this.collapsedOffset);
            } else if (i2 == 1 || i2 == 2) {
                ViewCompat.offsetTopAndBottom(child, savedTop - child.getTop());
            }
        } else {
            ViewCompat.offsetTopAndBottom(child, this.parentHeight);
        }
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(child));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, V child, MotionEvent event) {
        ViewDragHelper viewDragHelper;
        if (!child.isShown()) {
            this.ignoreEvents = true;
            return false;
        }
        int action = event.getActionMasked();
        if (action == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(event);
        View scroll = null;
        if (action == 0) {
            int initialX = (int) event.getX();
            this.initialY = (int) event.getY();
            if (this.state != 2) {
                WeakReference<View> weakReference = this.nestedScrollingChildRef;
                View scroll2 = weakReference != null ? weakReference.get() : null;
                if (scroll2 != null && parent.isPointInChildBounds(scroll2, initialX, this.initialY)) {
                    this.activePointerId = event.getPointerId(event.getActionIndex());
                    this.touchingScrollingChild = true;
                }
            }
            this.ignoreEvents = this.activePointerId == -1 && !parent.isPointInChildBounds(child, initialX, this.initialY);
        } else if (action == 1 || action == 3) {
            this.touchingScrollingChild = false;
            this.activePointerId = -1;
            if (this.ignoreEvents) {
                this.ignoreEvents = false;
                return false;
            }
        }
        if (!this.ignoreEvents && (viewDragHelper = this.viewDragHelper) != null && viewDragHelper.shouldInterceptTouchEvent(event)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.nestedScrollingChildRef;
        if (weakReference2 != null) {
            scroll = weakReference2.get();
        }
        return action == 2 && scroll != null && !this.ignoreEvents && this.state != 1 && !parent.isPointInChildBounds(scroll, (int) event.getX(), (int) event.getY()) && this.viewDragHelper != null && Math.abs(((float) this.initialY) - event.getY()) > ((float) this.viewDragHelper.getTouchSlop());
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onTouchEvent(CoordinatorLayout parent, V child, MotionEvent event) {
        if (!child.isShown()) {
            return false;
        }
        int action = event.getActionMasked();
        if (this.state == 1 && action == 0) {
            return true;
        }
        ViewDragHelper viewDragHelper = this.viewDragHelper;
        if (viewDragHelper != null) {
            viewDragHelper.processTouchEvent(event);
        }
        if (action == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(event);
        if (action == 2 && !this.ignoreEvents && Math.abs(this.initialY - event.getY()) > this.viewDragHelper.getTouchSlop()) {
            this.viewDragHelper.captureChildView(child, event.getPointerId(event.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int axes, int type) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (axes & 2) != 0;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dx, int dy, int[] consumed, int type) {
        if (type != 1) {
            WeakReference<View> weakReference = this.nestedScrollingChildRef;
            View scrollingChild = weakReference != null ? weakReference.get() : null;
            if (target == scrollingChild) {
                int currentTop = child.getTop();
                int newTop = currentTop - dy;
                if (dy > 0) {
                    if (newTop < getExpandedOffset()) {
                        consumed[1] = currentTop - getExpandedOffset();
                        ViewCompat.offsetTopAndBottom(child, -consumed[1]);
                        setStateInternal(3);
                    } else {
                        consumed[1] = dy;
                        ViewCompat.offsetTopAndBottom(child, -dy);
                        setStateInternal(1);
                    }
                } else if (dy < 0 && !target.canScrollVertically(-1)) {
                    int i = this.collapsedOffset;
                    if (newTop <= i || this.hideable) {
                        consumed[1] = dy;
                        ViewCompat.offsetTopAndBottom(child, -dy);
                        setStateInternal(1);
                    } else {
                        consumed[1] = currentTop - i;
                        ViewCompat.offsetTopAndBottom(child, -consumed[1]);
                        setStateInternal(4);
                    }
                }
                dispatchOnSlide(child.getTop());
                this.lastNestedScrollDy = dy;
                this.nestedScrolled = true;
            }
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target, int type) {
        int top;
        int currentTop;
        if (child.getTop() == getExpandedOffset()) {
            setStateInternal(3);
            return;
        }
        WeakReference<View> weakReference = this.nestedScrollingChildRef;
        if (weakReference != null && target == weakReference.get() && this.nestedScrolled) {
            if (this.lastNestedScrollDy > 0) {
                currentTop = getExpandedOffset();
                top = 3;
            } else if (!this.hideable || !shouldHide(child, getYVelocity())) {
                int top2 = this.lastNestedScrollDy;
                if (top2 == 0) {
                    int currentTop2 = child.getTop();
                    if (!this.fitToContents) {
                        int top3 = this.halfExpandedOffset;
                        if (currentTop2 < top3) {
                            if (currentTop2 < Math.abs(currentTop2 - this.collapsedOffset)) {
                                int top4 = this.expandedOffset;
                                currentTop = top4;
                                top = 3;
                            } else {
                                int top5 = this.halfExpandedOffset;
                                currentTop = top5;
                                top = 6;
                            }
                        } else if (Math.abs(currentTop2 - top3) < Math.abs(currentTop2 - this.collapsedOffset)) {
                            int top6 = this.halfExpandedOffset;
                            currentTop = top6;
                            top = 6;
                        } else {
                            int top7 = this.collapsedOffset;
                            currentTop = top7;
                            top = 4;
                        }
                    } else if (Math.abs(currentTop2 - this.fitToContentsOffset) < Math.abs(currentTop2 - this.collapsedOffset)) {
                        int top8 = this.fitToContentsOffset;
                        currentTop = top8;
                        top = 3;
                    } else {
                        int top9 = this.collapsedOffset;
                        currentTop = top9;
                        top = 4;
                    }
                } else if (this.fitToContents) {
                    currentTop = this.collapsedOffset;
                    top = 4;
                } else {
                    int currentTop3 = child.getTop();
                    if (Math.abs(currentTop3 - this.halfExpandedOffset) < Math.abs(currentTop3 - this.collapsedOffset)) {
                        int top10 = this.halfExpandedOffset;
                        currentTop = top10;
                        top = 6;
                    } else {
                        int top11 = this.collapsedOffset;
                        currentTop = top11;
                        top = 4;
                    }
                }
            } else {
                currentTop = this.parentHeight;
                top = 5;
            }
            startSettlingAnimation(child, top, currentTop, false);
            this.nestedScrolled = false;
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, int[] consumed) {
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V child, View target, float velocityX, float velocityY) {
        WeakReference<View> weakReference = this.nestedScrollingChildRef;
        if (weakReference == null || target != weakReference.get()) {
            return false;
        }
        return this.state != 3 || super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public void setFitToContents(boolean fitToContents) {
        if (this.fitToContents != fitToContents) {
            this.fitToContents = fitToContents;
            if (this.viewRef != null) {
                calculateCollapsedOffset();
            }
            setStateInternal((!this.fitToContents || this.state != 6) ? this.state : 3);
            updateAccessibilityActions();
        }
    }

    public void setPeekHeight(int peekHeight) {
        setPeekHeight(peekHeight, false);
    }

    public final void setPeekHeight(int peekHeight, boolean animate) {
        V view;
        boolean layout = false;
        if (peekHeight == -1) {
            if (!this.peekHeightAuto) {
                this.peekHeightAuto = true;
                layout = true;
            }
        } else if (this.peekHeightAuto || this.peekHeight != peekHeight) {
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, peekHeight);
            layout = true;
        }
        if (layout && this.viewRef != null) {
            calculateCollapsedOffset();
            if (this.state == 4 && (view = this.viewRef.get()) != null) {
                if (animate) {
                    settleToStatePendingLayout(this.state);
                } else {
                    view.requestLayout();
                }
            }
        }
    }

    public int getPeekHeight() {
        if (this.peekHeightAuto) {
            return -1;
        }
        return this.peekHeight;
    }

    public void setHalfExpandedRatio(float ratio) {
        if (ratio <= 0.0f || ratio >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.halfExpandedRatio = ratio;
    }

    public void setExpandedOffset(int offset) {
        if (offset >= 0) {
            this.expandedOffset = offset;
            return;
        }
        throw new IllegalArgumentException("offset must be greater than or equal to 0");
    }

    public float getHalfExpandedRatio() {
        return this.halfExpandedRatio;
    }

    public void setHideable(boolean hideable) {
        if (this.hideable != hideable) {
            this.hideable = hideable;
            if (!hideable && this.state == 5) {
                setState(4);
            }
            updateAccessibilityActions();
        }
    }

    public boolean isHideable() {
        return this.hideable;
    }

    public void setSkipCollapsed(boolean skipCollapsed) {
        this.skipCollapsed = skipCollapsed;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public void setSaveFlags(int flags) {
        this.saveFlags = flags;
    }

    public int getSaveFlags() {
        return this.saveFlags;
    }

    @Deprecated
    public void setBottomSheetCallback(BottomSheetCallback callback) {
        Log.w(TAG, "BottomSheetBehavior now supports multiple callbacks. `setBottomSheetCallback()` removes all existing callbacks, including ones set internally by library authors, which may result in unintended behavior. This may change in the future. Please use `addBottomSheetCallback()` and `removeBottomSheetCallback()` instead to set your own callbacks.");
        this.callbacks.clear();
        if (callback != null) {
            this.callbacks.add(callback);
        }
    }

    public void addBottomSheetCallback(BottomSheetCallback callback) {
        if (!this.callbacks.contains(callback)) {
            this.callbacks.add(callback);
        }
    }

    public void removeBottomSheetCallback(BottomSheetCallback callback) {
        this.callbacks.remove(callback);
    }

    public void setState(int state) {
        if (state != this.state) {
            if (this.viewRef != null) {
                settleToStatePendingLayout(state);
            } else if (state == 4 || state == 3 || state == 6 || (this.hideable && state == 5)) {
                this.state = state;
            }
        }
    }

    private void settleToStatePendingLayout(final int state) {
        final V child = this.viewRef.get();
        if (child != null) {
            ViewParent parent = child.getParent();
            if (parent == null || !parent.isLayoutRequested() || !ViewCompat.isAttachedToWindow(child)) {
                settleToState(child, state);
            } else {
                child.post(new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.1
                    @Override // java.lang.Runnable
                    public void run() {
                        BottomSheetBehavior.this.settleToState(child, state);
                    }
                });
            }
        }
    }

    public int getState() {
        return this.state;
    }

    void setStateInternal(int state) {
        View bottomSheet;
        if (this.state != state) {
            this.state = state;
            WeakReference<V> weakReference = this.viewRef;
            if (!(weakReference == null || (bottomSheet = weakReference.get()) == null)) {
                if (state == 6 || state == 3) {
                    updateImportantForAccessibility(true);
                } else if (state == 5 || state == 4) {
                    updateImportantForAccessibility(false);
                }
                updateDrawableForTargetState(state);
                for (int i = 0; i < this.callbacks.size(); i++) {
                    this.callbacks.get(i).onStateChanged(bottomSheet, state);
                }
                updateAccessibilityActions();
            }
        }
    }

    private void updateDrawableForTargetState(int state) {
        ValueAnimator valueAnimator;
        if (state != 2) {
            boolean expand = state == 3;
            if (this.isShapeExpanded != expand) {
                this.isShapeExpanded = expand;
                if (this.materialShapeDrawable != null && (valueAnimator = this.interpolatorAnimator) != null) {
                    if (valueAnimator.isRunning()) {
                        this.interpolatorAnimator.reverse();
                        return;
                    }
                    float to = expand ? 0.0f : 1.0f;
                    float from = 1.0f - to;
                    this.interpolatorAnimator.setFloatValues(from, to);
                    this.interpolatorAnimator.start();
                }
            }
        }
    }

    private void calculateCollapsedOffset() {
        int peek;
        if (this.peekHeightAuto) {
            peek = Math.max(this.peekHeightMin, this.parentHeight - ((this.parentWidth * 9) / 16));
        } else {
            peek = this.peekHeight;
        }
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - peek, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - peek;
        }
    }

    private void calculateHalfExpandedOffset() {
        this.halfExpandedOffset = (int) (this.parentHeight * (1.0f - this.halfExpandedRatio));
    }

    private void reset() {
        this.activePointerId = -1;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    private void restoreOptionalState(SavedState ss) {
        int i = this.saveFlags;
        if (i != 0) {
            if (i == -1 || (i & 1) == 1) {
                this.peekHeight = ss.peekHeight;
            }
            int i2 = this.saveFlags;
            if (i2 == -1 || (i2 & 2) == 2) {
                this.fitToContents = ss.fitToContents;
            }
            int i3 = this.saveFlags;
            if (i3 == -1 || (i3 & 4) == 4) {
                this.hideable = ss.hideable;
            }
            int i4 = this.saveFlags;
            if (i4 == -1 || (i4 & 8) == 8) {
                this.skipCollapsed = ss.skipCollapsed;
            }
        }
    }

    boolean shouldHide(View child, float yvel) {
        if (this.skipCollapsed) {
            return true;
        }
        if (child.getTop() < this.collapsedOffset) {
            return false;
        }
        float newTop = child.getTop() + (HIDE_FRICTION * yvel);
        return Math.abs(newTop - ((float) this.collapsedOffset)) / ((float) this.peekHeight) > HIDE_THRESHOLD;
    }

    View findScrollingChild(View view) {
        if (ViewCompat.isNestedScrollingEnabled(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup group = (ViewGroup) view;
        int count = group.getChildCount();
        for (int i = 0; i < count; i++) {
            View scrollingChild = findScrollingChild(group.getChildAt(i));
            if (scrollingChild != null) {
                return scrollingChild;
            }
        }
        return null;
    }

    private void createMaterialShapeDrawable(Context context, AttributeSet attrs, boolean hasBackgroundTint) {
        createMaterialShapeDrawable(context, attrs, hasBackgroundTint, null);
    }

    private void createMaterialShapeDrawable(Context context, AttributeSet attrs, boolean hasBackgroundTint, ColorStateList bottomSheetColor) {
        if (this.shapeThemingEnabled) {
            this.shapeAppearanceModelDefault = ShapeAppearanceModel.builder(context, attrs, R.attr.bottomSheetStyle, DEF_STYLE_RES).build();
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModelDefault);
            this.materialShapeDrawable = materialShapeDrawable;
            materialShapeDrawable.initializeElevationOverlay(context);
            if (!hasBackgroundTint || bottomSheetColor == null) {
                TypedValue defaultColor = new TypedValue();
                context.getTheme().resolveAttribute(16842801, defaultColor, true);
                this.materialShapeDrawable.setTint(defaultColor.data);
                return;
            }
            this.materialShapeDrawable.setFillColor(bottomSheetColor);
        }
    }

    private void createShapeValueAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.interpolatorAnimator = ofFloat;
        ofFloat.setDuration(500L);
        this.interpolatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = ((Float) animation.getAnimatedValue()).floatValue();
                if (BottomSheetBehavior.this.materialShapeDrawable != null) {
                    BottomSheetBehavior.this.materialShapeDrawable.setInterpolation(value);
                }
            }
        });
    }

    private float getYVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getExpandedOffset() {
        return this.fitToContents ? this.fitToContentsOffset : this.expandedOffset;
    }

    void settleToState(View child, int state) {
        int top;
        if (state == 4) {
            top = this.collapsedOffset;
        } else if (state == 6) {
            top = this.halfExpandedOffset;
            if (this.fitToContents && top <= this.fitToContentsOffset) {
                state = 3;
                top = this.fitToContentsOffset;
            }
        } else if (state == 3) {
            top = getExpandedOffset();
        } else if (!this.hideable || state != 5) {
            throw new IllegalArgumentException("Illegal state argument: " + state);
        } else {
            top = this.parentHeight;
        }
        startSettlingAnimation(child, state, top, false);
    }

    void startSettlingAnimation(View child, int state, int top, boolean settleFromViewDragHelper) {
        boolean startedSettling;
        if (settleFromViewDragHelper) {
            startedSettling = this.viewDragHelper.settleCapturedViewAt(child.getLeft(), top);
        } else {
            startedSettling = this.viewDragHelper.smoothSlideViewTo(child, child.getLeft(), top);
        }
        if (startedSettling) {
            setStateInternal(2);
            updateDrawableForTargetState(state);
            if (this.settleRunnable == null) {
                this.settleRunnable = new SettleRunnable(child, state);
            }
            if (!((SettleRunnable) this.settleRunnable).isPosted) {
                this.settleRunnable.targetState = state;
                ViewCompat.postOnAnimation(child, this.settleRunnable);
                ((SettleRunnable) this.settleRunnable).isPosted = true;
                return;
            }
            this.settleRunnable.targetState = state;
            return;
        }
        setStateInternal(state);
    }

    void dispatchOnSlide(int top) {
        View bottomSheet = this.viewRef.get();
        if (!(bottomSheet == null || this.callbacks.isEmpty())) {
            int i = this.collapsedOffset;
            float f = i - top;
            float slideOffset = top > i ? f / (this.parentHeight - i) : f / (i - getExpandedOffset());
            for (int i2 = 0; i2 < this.callbacks.size(); i2++) {
                this.callbacks.get(i2).onSlide(bottomSheet, slideOffset);
            }
        }
    }

    int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public void disableShapeAnimations() {
        this.interpolatorAnimator = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class SettleRunnable implements Runnable {
        private boolean isPosted;
        int targetState;
        private final View view;

        SettleRunnable(View view, int targetState) {
            this.view = view;
            this.targetState = targetState;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BottomSheetBehavior.this.viewDragHelper == null || !BottomSheetBehavior.this.viewDragHelper.continueSettling(true)) {
                BottomSheetBehavior.this.setStateInternal(this.targetState);
            } else {
                ViewCompat.postOnAnimation(this.view, this);
            }
            this.isPosted = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel in, ClassLoader loader) {
                return new SavedState(in, loader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in, (ClassLoader) null);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
        boolean fitToContents;
        boolean hideable;
        int peekHeight;
        boolean skipCollapsed;
        final int state;

        public SavedState(Parcel source) {
            this(source, (ClassLoader) null);
        }

        public SavedState(Parcel source, ClassLoader loader) {
            super(source, loader);
            this.state = source.readInt();
            this.peekHeight = source.readInt();
            boolean z = false;
            this.fitToContents = source.readInt() == 1;
            this.hideable = source.readInt() == 1;
            this.skipCollapsed = source.readInt() == 1 ? true : z;
        }

        public SavedState(Parcelable superState, BottomSheetBehavior<?> behavior) {
            super(superState);
            this.state = behavior.state;
            this.peekHeight = ((BottomSheetBehavior) behavior).peekHeight;
            this.fitToContents = ((BottomSheetBehavior) behavior).fitToContents;
            this.hideable = behavior.hideable;
            this.skipCollapsed = ((BottomSheetBehavior) behavior).skipCollapsed;
        }

        @Deprecated
        public SavedState(Parcelable superstate, int state) {
            super(superstate);
            this.state = state;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(this.state);
            out.writeInt(this.peekHeight);
            out.writeInt(this.fitToContents ? 1 : 0);
            out.writeInt(this.hideable ? 1 : 0);
            out.writeInt(this.skipCollapsed ? 1 : 0);
        }
    }

    public static <V extends View> BottomSheetBehavior<V> from(V view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior<?> behavior = ((CoordinatorLayout.LayoutParams) params).getBehavior();
            if (behavior instanceof BottomSheetBehavior) {
                return (BottomSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    private void updateImportantForAccessibility(boolean expanded) {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            ViewParent viewParent = weakReference.get().getParent();
            if (viewParent instanceof CoordinatorLayout) {
                CoordinatorLayout parent = (CoordinatorLayout) viewParent;
                int childCount = parent.getChildCount();
                if (Build.VERSION.SDK_INT >= 16 && expanded) {
                    if (this.importantForAccessibilityMap == null) {
                        this.importantForAccessibilityMap = new HashMap(childCount);
                    } else {
                        return;
                    }
                }
                for (int i = 0; i < childCount; i++) {
                    View child = parent.getChildAt(i);
                    if (child != this.viewRef.get()) {
                        if (!expanded) {
                            Map<View, Integer> map = this.importantForAccessibilityMap;
                            if (map != null && map.containsKey(child)) {
                                ViewCompat.setImportantForAccessibility(child, this.importantForAccessibilityMap.get(child).intValue());
                            }
                        } else {
                            if (Build.VERSION.SDK_INT >= 16) {
                                this.importantForAccessibilityMap.put(child, Integer.valueOf(child.getImportantForAccessibility()));
                            }
                            ViewCompat.setImportantForAccessibility(child, 4);
                        }
                    }
                }
                if (!expanded) {
                    this.importantForAccessibilityMap = null;
                }
            }
        }
    }

    private void updateAccessibilityActions() {
        V child;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null && (child = weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(child, 524288);
            ViewCompat.removeAccessibilityAction(child, 262144);
            ViewCompat.removeAccessibilityAction(child, 1048576);
            if (this.hideable && this.state != 5) {
                addAccessibilityActionForState(child, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            int i = this.state;
            int nextState = 6;
            if (i == 3) {
                if (this.fitToContents) {
                    nextState = 4;
                }
                addAccessibilityActionForState(child, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, nextState);
            } else if (i == 4) {
                if (this.fitToContents) {
                    nextState = 3;
                }
                addAccessibilityActionForState(child, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, nextState);
            } else if (i == 6) {
                addAccessibilityActionForState(child, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE, 4);
                addAccessibilityActionForState(child, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            }
        }
    }

    private void addAccessibilityActionForState(V child, AccessibilityNodeInfoCompat.AccessibilityActionCompat action, final int state) {
        ViewCompat.replaceAccessibilityAction(child, action, null, new AccessibilityViewCommand() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.4
            @Override // androidx.core.view.accessibility.AccessibilityViewCommand
            public boolean perform(View view, AccessibilityViewCommand.CommandArguments arguments) {
                BottomSheetBehavior.this.setState(state);
                return true;
            }
        });
    }
}
