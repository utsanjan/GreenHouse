package com.google.android.material.snackbar;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    static final int ANIMATION_DURATION = 250;
    static final int ANIMATION_FADE_DURATION = 180;
    private static final int ANIMATION_FADE_IN_DURATION = 150;
    private static final int ANIMATION_FADE_OUT_DURATION = 75;
    public static final int ANIMATION_MODE_FADE = 1;
    public static final int ANIMATION_MODE_SLIDE = 0;
    private static final float ANIMATION_SCALE_FROM_VALUE = 0.8f;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    private static final int[] SNACKBAR_STYLE_ATTR;
    private static final String TAG;
    private static final boolean USE_OFFSET_API;
    static final Handler handler;
    private final AccessibilityManager accessibilityManager;
    private View anchorView;
    private Behavior behavior;
    private List<BaseCallback<B>> callbacks;
    private final ContentViewCallback contentViewCallback;
    private final Context context;
    private int duration;
    private int extraBottomMarginAnchorView;
    private int extraBottomMarginGestureInset;
    private int extraBottomMarginWindowInset;
    private int extraLeftMarginWindowInset;
    private int extraRightMarginWindowInset;
    private boolean gestureInsetBottomIgnored;
    private Rect originalMargins;
    private final ViewGroup targetParent;
    protected final SnackbarBaseLayout view;
    private final Runnable bottomMarginGestureInsetRunnable = new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.2
        @Override // java.lang.Runnable
        public void run() {
            int currentInsetBottom;
            if (BaseTransientBottomBar.this.view != null && BaseTransientBottomBar.this.context != null && (currentInsetBottom = (BaseTransientBottomBar.this.getScreenHeight() - BaseTransientBottomBar.this.getViewAbsoluteBottom()) + ((int) BaseTransientBottomBar.this.view.getTranslationY())) < BaseTransientBottomBar.this.extraBottomMarginGestureInset) {
                ViewGroup.LayoutParams layoutParams = BaseTransientBottomBar.this.view.getLayoutParams();
                if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                    Log.w(BaseTransientBottomBar.TAG, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                    return;
                }
                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginParams.bottomMargin += BaseTransientBottomBar.this.extraBottomMarginGestureInset - currentInsetBottom;
                BaseTransientBottomBar.this.view.requestLayout();
            }
        }
    };
    SnackbarManager.Callback managerCallback = new SnackbarManager.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.5
        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void show() {
            BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(0, BaseTransientBottomBar.this));
        }

        @Override // com.google.android.material.snackbar.SnackbarManager.Callback
        public void dismiss(int event) {
            BaseTransientBottomBar.handler.sendMessage(BaseTransientBottomBar.handler.obtainMessage(1, event, 0, BaseTransientBottomBar.this));
        }
    };

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface AnimationMode {
    }

    @Deprecated
    /* loaded from: classes.dex */
    public interface ContentViewCallback extends ContentViewCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Duration {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface OnAttachStateChangeListener {
        void onViewAttachedToWindow(View view);

        void onViewDetachedFromWindow(View view);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public interface OnLayoutChangeListener {
        void onLayoutChange(View view, int i, int i2, int i3, int i4);
    }

    /* loaded from: classes.dex */
    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes.dex */
        public @interface DismissEvent {
        }

        public void onDismissed(B transientBottomBar, int event) {
        }

        public void onShown(B transientBottomBar) {
        }
    }

    static {
        USE_OFFSET_API = Build.VERSION.SDK_INT >= 16 && Build.VERSION.SDK_INT <= 19;
        SNACKBAR_STYLE_ATTR = new int[]{R.attr.snackbarStyle};
        TAG = BaseTransientBottomBar.class.getSimpleName();
        handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                int i = message.what;
                if (i == 0) {
                    ((BaseTransientBottomBar) message.obj).showView();
                    return true;
                } else if (i != 1) {
                    return false;
                } else {
                    ((BaseTransientBottomBar) message.obj).hideView(message.arg1);
                    return true;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseTransientBottomBar(ViewGroup parent, View content, ContentViewCallback contentViewCallback) {
        if (parent == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        } else if (content == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        } else if (contentViewCallback != null) {
            this.targetParent = parent;
            this.contentViewCallback = contentViewCallback;
            Context context = parent.getContext();
            this.context = context;
            ThemeEnforcement.checkAppCompatTheme(context);
            LayoutInflater inflater = LayoutInflater.from(this.context);
            SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) inflater.inflate(getSnackbarBaseLayoutResId(), this.targetParent, false);
            this.view = snackbarBaseLayout;
            if (snackbarBaseLayout.getBackground() == null) {
                ViewCompat.setBackground(this.view, createThemedBackground());
            }
            if (content instanceof SnackbarContentLayout) {
                ((SnackbarContentLayout) content).updateActionTextColorAlphaIfNeeded(this.view.getActionTextColorAlpha());
            }
            this.view.addView(content);
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
                this.originalMargins = new Rect(marginParams.leftMargin, marginParams.topMargin, marginParams.rightMargin, marginParams.bottomMargin);
            }
            ViewCompat.setAccessibilityLiveRegion(this.view, 1);
            ViewCompat.setImportantForAccessibility(this.view, 1);
            ViewCompat.setFitsSystemWindows(this.view, true);
            ViewCompat.setOnApplyWindowInsetsListener(this.view, new OnApplyWindowInsetsListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.3
                @Override // androidx.core.view.OnApplyWindowInsetsListener
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    BaseTransientBottomBar.this.extraBottomMarginWindowInset = insets.getSystemWindowInsetBottom();
                    BaseTransientBottomBar.this.extraLeftMarginWindowInset = insets.getSystemWindowInsetLeft();
                    BaseTransientBottomBar.this.extraRightMarginWindowInset = insets.getSystemWindowInsetRight();
                    BaseTransientBottomBar.this.updateMargins();
                    return insets;
                }
            });
            ViewCompat.setAccessibilityDelegate(this.view, new AccessibilityDelegateCompat() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.4
                @Override // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                    super.onInitializeAccessibilityNodeInfo(host, info);
                    info.addAction(1048576);
                    info.setDismissable(true);
                }

                @Override // androidx.core.view.AccessibilityDelegateCompat
                public boolean performAccessibilityAction(View host, int action, Bundle args) {
                    if (action != 1048576) {
                        return super.performAccessibilityAction(host, action, args);
                    }
                    BaseTransientBottomBar.this.dismiss();
                    return true;
                }
            });
            this.accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
        } else {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
    }

    private Drawable createThemedBackground() {
        int backgroundColor = MaterialColors.layer(this.view, R.attr.colorSurface, R.attr.colorOnSurface, this.view.getBackgroundOverlayColorAlpha());
        float cornerRadius = this.view.getResources().getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
        GradientDrawable background = new GradientDrawable();
        background.setShape(0);
        background.setColor(backgroundColor);
        background.setCornerRadius(cornerRadius);
        return background;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMargins() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams) || this.originalMargins == null) {
            Log.w(TAG, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        int extraBottomMargin = this.anchorView != null ? this.extraBottomMarginAnchorView : this.extraBottomMarginWindowInset;
        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) layoutParams;
        marginParams.bottomMargin = this.originalMargins.bottom + extraBottomMargin;
        marginParams.leftMargin = this.originalMargins.left + this.extraLeftMarginWindowInset;
        marginParams.rightMargin = this.originalMargins.right + this.extraRightMarginWindowInset;
        this.view.requestLayout();
        if (Build.VERSION.SDK_INT >= 29 && shouldUpdateGestureInset()) {
            this.view.removeCallbacks(this.bottomMarginGestureInsetRunnable);
            this.view.post(this.bottomMarginGestureInsetRunnable);
        }
    }

    private boolean shouldUpdateGestureInset() {
        return this.extraBottomMarginGestureInset > 0 && !this.gestureInsetBottomIgnored && isSwipeDismissable();
    }

    private boolean isSwipeDismissable() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return (layoutParams instanceof CoordinatorLayout.LayoutParams) && (((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof SwipeDismissBehavior);
    }

    protected int getSnackbarBaseLayoutResId() {
        return hasSnackbarStyleAttr() ? R.layout.mtrl_layout_snackbar : R.layout.design_layout_snackbar;
    }

    protected boolean hasSnackbarStyleAttr() {
        TypedArray a = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
        int snackbarStyleResId = a.getResourceId(0, -1);
        a.recycle();
        return snackbarStyleResId != -1;
    }

    public B setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public int getDuration() {
        return this.duration;
    }

    public B setGestureInsetBottomIgnored(boolean gestureInsetBottomIgnored) {
        this.gestureInsetBottomIgnored = gestureInsetBottomIgnored;
        return this;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public int getAnimationMode() {
        return this.view.getAnimationMode();
    }

    public B setAnimationMode(int animationMode) {
        this.view.setAnimationMode(animationMode);
        return this;
    }

    public View getAnchorView() {
        return this.anchorView;
    }

    public B setAnchorView(View anchorView) {
        this.anchorView = anchorView;
        return this;
    }

    public B setAnchorView(int anchorViewId) {
        View findViewById = this.targetParent.findViewById(anchorViewId);
        this.anchorView = findViewById;
        if (findViewById != null) {
            return this;
        }
        throw new IllegalArgumentException("Unable to find anchor view with id: " + anchorViewId);
    }

    public B setBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public Context getContext() {
        return this.context;
    }

    public View getView() {
        return this.view;
    }

    public void show() {
        SnackbarManager.getInstance().show(getDuration(), this.managerCallback);
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void dispatchDismiss(int event) {
        SnackbarManager.getInstance().dismiss(this.managerCallback, event);
    }

    public B addCallback(BaseCallback<B> callback) {
        if (callback == null) {
            return this;
        }
        if (this.callbacks == null) {
            this.callbacks = new ArrayList();
        }
        this.callbacks.add(callback);
        return this;
    }

    public B removeCallback(BaseCallback<B> callback) {
        if (callback == null) {
            return this;
        }
        List<BaseCallback<B>> list = this.callbacks;
        if (list == null) {
            return this;
        }
        list.remove(callback);
        return this;
    }

    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.managerCallback);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
    }

    protected SwipeDismissBehavior<? extends View> getNewBehavior() {
        return new Behavior();
    }

    final void showView() {
        this.view.setOnAttachStateChangeListener(new OnAttachStateChangeListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6
            @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
                WindowInsets insets;
                if (Build.VERSION.SDK_INT >= 29 && (insets = BaseTransientBottomBar.this.view.getRootWindowInsets()) != null) {
                    BaseTransientBottomBar.this.extraBottomMarginGestureInset = insets.getMandatorySystemGestureInsets().bottom;
                    BaseTransientBottomBar.this.updateMargins();
                }
            }

            @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                if (BaseTransientBottomBar.this.isShownOrQueued()) {
                    BaseTransientBottomBar.handler.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            BaseTransientBottomBar.this.onViewHidden(3);
                        }
                    });
                }
            }
        });
        if (this.view.getParent() == null) {
            ViewGroup.LayoutParams lp = this.view.getLayoutParams();
            if (lp instanceof CoordinatorLayout.LayoutParams) {
                setUpBehavior((CoordinatorLayout.LayoutParams) lp);
            }
            this.extraBottomMarginAnchorView = calculateBottomMarginForAnchorView();
            updateMargins();
            this.view.setVisibility(4);
            this.targetParent.addView(this.view);
        }
        if (ViewCompat.isLaidOut(this.view)) {
            showViewImpl();
        } else {
            this.view.setOnLayoutChangeListener(new OnLayoutChangeListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.7
                @Override // com.google.android.material.snackbar.BaseTransientBottomBar.OnLayoutChangeListener
                public void onLayoutChange(View view, int left, int top, int right, int bottom) {
                    BaseTransientBottomBar.this.view.setOnLayoutChangeListener(null);
                    BaseTransientBottomBar.this.showViewImpl();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showViewImpl() {
        if (shouldAnimate()) {
            animateViewIn();
            return;
        }
        this.view.setVisibility(0);
        onViewShown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getViewAbsoluteBottom() {
        int[] absoluteLocation = new int[2];
        this.view.getLocationOnScreen(absoluteLocation);
        return absoluteLocation[1] + this.view.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getScreenHeight() {
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    private void setUpBehavior(CoordinatorLayout.LayoutParams lp) {
        SwipeDismissBehavior<? extends View> behavior = this.behavior;
        if (behavior == null) {
            behavior = getNewBehavior();
        }
        if (behavior instanceof Behavior) {
            ((Behavior) behavior).setBaseTransientBottomBar(this);
        }
        behavior.setListener(new SwipeDismissBehavior.OnDismissListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.8
            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void onDismiss(View view) {
                view.setVisibility(8);
                BaseTransientBottomBar.this.dispatchDismiss(0);
            }

            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void onDragStateChanged(int state) {
                if (state == 0) {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.managerCallback);
                } else if (state == 1 || state == 2) {
                    SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.managerCallback);
                }
            }
        });
        lp.setBehavior(behavior);
        if (this.anchorView == null) {
            lp.insetEdge = 80;
        }
    }

    private int calculateBottomMarginForAnchorView() {
        View view = this.anchorView;
        if (view == null) {
            return 0;
        }
        int[] anchorViewLocation = new int[2];
        view.getLocationOnScreen(anchorViewLocation);
        int anchorViewAbsoluteYTop = anchorViewLocation[1];
        int[] targetParentLocation = new int[2];
        this.targetParent.getLocationOnScreen(targetParentLocation);
        int targetParentAbsoluteYBottom = targetParentLocation[1] + this.targetParent.getHeight();
        return targetParentAbsoluteYBottom - anchorViewAbsoluteYTop;
    }

    void animateViewIn() {
        this.view.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.9
            @Override // java.lang.Runnable
            public void run() {
                if (BaseTransientBottomBar.this.view != null) {
                    BaseTransientBottomBar.this.view.setVisibility(0);
                    if (BaseTransientBottomBar.this.view.getAnimationMode() == 1) {
                        BaseTransientBottomBar.this.startFadeInAnimation();
                    } else {
                        BaseTransientBottomBar.this.startSlideInAnimation();
                    }
                }
            }
        });
    }

    private void animateViewOut(int event) {
        if (this.view.getAnimationMode() == 1) {
            startFadeOutAnimation(event);
        } else {
            startSlideOutAnimation(event);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFadeInAnimation() {
        ValueAnimator alphaAnimator = getAlphaAnimator(0.0f, 1.0f);
        ValueAnimator scaleAnimator = getScaleAnimator(ANIMATION_SCALE_FROM_VALUE, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator, scaleAnimator);
        animatorSet.setDuration(150L);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewShown();
            }
        });
        animatorSet.start();
    }

    private void startFadeOutAnimation(final int event) {
        ValueAnimator animator = getAlphaAnimator(1.0f, 0.0f);
        animator.setDuration(75L);
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.11
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                BaseTransientBottomBar.this.onViewHidden(event);
            }
        });
        animator.start();
    }

    private ValueAnimator getAlphaAnimator(float... alphaValues) {
        ValueAnimator animator = ValueAnimator.ofFloat(alphaValues);
        animator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.12
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseTransientBottomBar.this.view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        return animator;
    }

    private ValueAnimator getScaleAnimator(float... scaleValues) {
        ValueAnimator animator = ValueAnimator.ofFloat(scaleValues);
        animator.setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.13
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float scale = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BaseTransientBottomBar.this.view.setScaleX(scale);
                BaseTransientBottomBar.this.view.setScaleY(scale);
            }
        });
        return animator;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSlideInAnimation() {
        final int translationYBottom = getTranslationYBottom();
        if (USE_OFFSET_API) {
            ViewCompat.offsetTopAndBottom(this.view, translationYBottom);
        } else {
            this.view.setTranslationY(translationYBottom);
        }
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(translationYBottom, 0);
        animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        animator.setDuration(250L);
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.14
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentIn(70, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                BaseTransientBottomBar.this.onViewShown();
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.15
            private int previousAnimatedIntValue;

            {
                this.previousAnimatedIntValue = translationYBottom;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animator2) {
                int currentAnimatedIntValue = ((Integer) animator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, currentAnimatedIntValue - this.previousAnimatedIntValue);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(currentAnimatedIntValue);
                }
                this.previousAnimatedIntValue = currentAnimatedIntValue;
            }
        });
        animator.start();
    }

    private void startSlideOutAnimation(final int event) {
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(0, getTranslationYBottom());
        animator.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        animator.setDuration(250L);
        animator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.16
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator2) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentOut(0, BaseTransientBottomBar.ANIMATION_FADE_DURATION);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator2) {
                BaseTransientBottomBar.this.onViewHidden(event);
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.17
            private int previousAnimatedIntValue = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator animator2) {
                int currentAnimatedIntValue = ((Integer) animator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    ViewCompat.offsetTopAndBottom(BaseTransientBottomBar.this.view, currentAnimatedIntValue - this.previousAnimatedIntValue);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(currentAnimatedIntValue);
                }
                this.previousAnimatedIntValue = currentAnimatedIntValue;
            }
        });
        animator.start();
    }

    private int getTranslationYBottom() {
        int translationY = this.view.getHeight();
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return translationY + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin;
        }
        return translationY;
    }

    final void hideView(int event) {
        if (!shouldAnimate() || this.view.getVisibility() != 0) {
            onViewHidden(event);
        } else {
            animateViewOut(event);
        }
    }

    void onViewShown() {
        SnackbarManager.getInstance().onShown(this.managerCallback);
        List<BaseCallback<B>> list = this.callbacks;
        if (list != null) {
            int callbackCount = list.size();
            for (int i = callbackCount - 1; i >= 0; i--) {
                this.callbacks.get(i).onShown(this);
            }
        }
    }

    void onViewHidden(int event) {
        SnackbarManager.getInstance().onDismissed(this.managerCallback);
        List<BaseCallback<B>> list = this.callbacks;
        if (list != null) {
            int callbackCount = list.size();
            for (int i = callbackCount - 1; i >= 0; i--) {
                this.callbacks.get(i).onDismissed(this, event);
            }
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    boolean shouldAnimate() {
        List<AccessibilityServiceInfo> serviceList = this.accessibilityManager.getEnabledAccessibilityServiceList(1);
        return serviceList != null && serviceList.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public static class SnackbarBaseLayout extends FrameLayout {
        private static final View.OnTouchListener consumeAllTouchListener = new View.OnTouchListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        };
        private final float actionTextColorAlpha;
        private int animationMode;
        private final float backgroundOverlayColorAlpha;
        private OnAttachStateChangeListener onAttachStateChangeListener;
        private OnLayoutChangeListener onLayoutChangeListener;

        /* JADX INFO: Access modifiers changed from: protected */
        public SnackbarBaseLayout(Context context) {
            this(context, null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public SnackbarBaseLayout(Context context, AttributeSet attrs) {
            super(ThemeEnforcement.createThemedContext(context, attrs, 0, 0), attrs);
            Context context2 = getContext();
            TypedArray a = context2.obtainStyledAttributes(attrs, R.styleable.SnackbarLayout);
            if (a.hasValue(R.styleable.SnackbarLayout_elevation)) {
                ViewCompat.setElevation(this, a.getDimensionPixelSize(R.styleable.SnackbarLayout_elevation, 0));
            }
            this.animationMode = a.getInt(R.styleable.SnackbarLayout_animationMode, 0);
            this.backgroundOverlayColorAlpha = a.getFloat(R.styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            this.actionTextColorAlpha = a.getFloat(R.styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            a.recycle();
            setOnTouchListener(consumeAllTouchListener);
            setFocusable(true);
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : consumeAllTouchListener);
            super.setOnClickListener(onClickListener);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            OnLayoutChangeListener onLayoutChangeListener = this.onLayoutChangeListener;
            if (onLayoutChangeListener != null) {
                onLayoutChangeListener.onLayoutChange(this, l, t, r, b);
            }
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.onAttachStateChangeListener;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewAttachedToWindow(this);
            }
            ViewCompat.requestApplyInsets(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            OnAttachStateChangeListener onAttachStateChangeListener = this.onAttachStateChangeListener;
            if (onAttachStateChangeListener != null) {
                onAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
        }

        void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            this.onLayoutChangeListener = onLayoutChangeListener;
        }

        void setOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
            this.onAttachStateChangeListener = listener;
        }

        int getAnimationMode() {
            return this.animationMode;
        }

        void setAnimationMode(int animationMode) {
            this.animationMode = animationMode;
        }

        float getBackgroundOverlayColorAlpha() {
            return this.backgroundOverlayColorAlpha;
        }

        float getActionTextColorAlpha() {
            return this.actionTextColorAlpha;
        }
    }

    /* loaded from: classes.dex */
    public static class Behavior extends SwipeDismissBehavior<View> {
        private final BehaviorDelegate delegate = new BehaviorDelegate(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.delegate.setBaseTransientBottomBar(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View child) {
            return this.delegate.canSwipeDismissView(child);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
            this.delegate.onInterceptTouchEvent(parent, child, event);
            return super.onInterceptTouchEvent(parent, child, event);
        }
    }

    /* loaded from: classes.dex */
    public static class BehaviorDelegate {
        private SnackbarManager.Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior<?> behavior) {
            behavior.setStartAlphaSwipeDistance(0.1f);
            behavior.setEndAlphaSwipeDistance(0.6f);
            behavior.setSwipeDirection(0);
        }

        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.managerCallback = baseTransientBottomBar.managerCallback;
        }

        public boolean canSwipeDismissView(View child) {
            return child instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent event) {
            int actionMasked = event.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked == 1 || actionMasked == 3) {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
                }
            } else if (parent.isPointInChildBounds(child, (int) event.getX(), (int) event.getY())) {
                SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
            }
        }
    }
}
