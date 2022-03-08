package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    OverScroller scroller;
    private VelocityTracker velocityTracker;
    private int activePointerId = -1;
    private int touchSlop = -1;

    public HeaderBehavior() {
    }

    public HeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
        if (r3 != 3) goto L_0x0083;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r8, V r9, android.view.MotionEvent r10) {
        /*
            r7 = this;
            int r0 = r7.touchSlop
            if (r0 >= 0) goto L_0x0012
            android.content.Context r0 = r8.getContext()
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
            int r0 = r0.getScaledTouchSlop()
            r7.touchSlop = r0
        L_0x0012:
            int r0 = r10.getAction()
            r1 = 2
            r2 = 1
            if (r0 != r1) goto L_0x001f
            boolean r3 = r7.isBeingDragged
            if (r3 == 0) goto L_0x001f
            return r2
        L_0x001f:
            int r3 = r10.getActionMasked()
            r4 = 0
            if (r3 == 0) goto L_0x0060
            r5 = -1
            if (r3 == r2) goto L_0x0051
            if (r3 == r1) goto L_0x002f
            r1 = 3
            if (r3 == r1) goto L_0x0051
            goto L_0x0083
        L_0x002f:
            int r1 = r7.activePointerId
            if (r1 != r5) goto L_0x0034
            goto L_0x0083
        L_0x0034:
            int r3 = r10.findPointerIndex(r1)
            if (r3 != r5) goto L_0x003b
            goto L_0x0083
        L_0x003b:
            float r4 = r10.getY(r3)
            int r4 = (int) r4
            int r5 = r7.lastMotionY
            int r5 = r4 - r5
            int r5 = java.lang.Math.abs(r5)
            int r6 = r7.touchSlop
            if (r5 <= r6) goto L_0x0083
            r7.isBeingDragged = r2
            r7.lastMotionY = r4
            goto L_0x0083
        L_0x0051:
            r7.isBeingDragged = r4
            r7.activePointerId = r5
            android.view.VelocityTracker r1 = r7.velocityTracker
            if (r1 == 0) goto L_0x0083
            r1.recycle()
            r1 = 0
            r7.velocityTracker = r1
            goto L_0x0083
        L_0x0060:
            r7.isBeingDragged = r4
            float r1 = r10.getX()
            int r1 = (int) r1
            float r2 = r10.getY()
            int r2 = (int) r2
            boolean r3 = r7.canDragView(r9)
            if (r3 == 0) goto L_0x0083
            boolean r3 = r8.isPointInChildBounds(r9, r1, r2)
            if (r3 == 0) goto L_0x0083
            r7.lastMotionY = r2
            int r3 = r10.getPointerId(r4)
            r7.activePointerId = r3
            r7.ensureVelocityTracker()
        L_0x0083:
            android.view.VelocityTracker r1 = r7.velocityTracker
            if (r1 == 0) goto L_0x008a
            r1.addMovement(r10)
        L_0x008a:
            boolean r1 = r7.isBeingDragged
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0021, code lost:
        if (r0 != 3) goto L_0x00af;
     */
    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r12, V r13, android.view.MotionEvent r14) {
        /*
            r11 = this;
            int r0 = r11.touchSlop
            if (r0 >= 0) goto L_0x0012
            android.content.Context r0 = r12.getContext()
            android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
            int r0 = r0.getScaledTouchSlop()
            r11.touchSlop = r0
        L_0x0012:
            int r0 = r14.getActionMasked()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x008e
            r3 = -1
            if (r0 == r1) goto L_0x005c
            r4 = 2
            if (r0 == r4) goto L_0x0025
            r4 = 3
            if (r0 == r4) goto L_0x007f
            goto L_0x00af
        L_0x0025:
            int r0 = r11.activePointerId
            int r0 = r14.findPointerIndex(r0)
            if (r0 != r3) goto L_0x002e
            return r2
        L_0x002e:
            float r2 = r14.getY(r0)
            int r2 = (int) r2
            int r3 = r11.lastMotionY
            int r3 = r3 - r2
            boolean r4 = r11.isBeingDragged
            if (r4 != 0) goto L_0x0049
            int r4 = java.lang.Math.abs(r3)
            int r5 = r11.touchSlop
            if (r4 <= r5) goto L_0x0049
            r11.isBeingDragged = r1
            if (r3 <= 0) goto L_0x0048
            int r3 = r3 - r5
            goto L_0x0049
        L_0x0048:
            int r3 = r3 + r5
        L_0x0049:
            boolean r4 = r11.isBeingDragged
            if (r4 == 0) goto L_0x00af
            r11.lastMotionY = r2
            int r8 = r11.getMaxDragOffset(r13)
            r9 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r7 = r3
            r4.scroll(r5, r6, r7, r8, r9)
            goto L_0x00af
        L_0x005c:
            android.view.VelocityTracker r0 = r11.velocityTracker
            if (r0 == 0) goto L_0x007f
            r0.addMovement(r14)
            android.view.VelocityTracker r0 = r11.velocityTracker
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r11.velocityTracker
            int r4 = r11.activePointerId
            float r0 = r0.getYVelocity(r4)
            int r4 = r11.getScrollRangeForDragFling(r13)
            int r8 = -r4
            r9 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r10 = r0
            r5.fling(r6, r7, r8, r9, r10)
        L_0x007f:
            r11.isBeingDragged = r2
            r11.activePointerId = r3
            android.view.VelocityTracker r0 = r11.velocityTracker
            if (r0 == 0) goto L_0x00af
            r0.recycle()
            r0 = 0
            r11.velocityTracker = r0
            goto L_0x00af
        L_0x008e:
            float r0 = r14.getX()
            int r0 = (int) r0
            float r3 = r14.getY()
            int r3 = (int) r3
            boolean r4 = r12.isPointInChildBounds(r13, r0, r3)
            if (r4 == 0) goto L_0x00b7
            boolean r4 = r11.canDragView(r13)
            if (r4 == 0) goto L_0x00b7
            r11.lastMotionY = r3
            int r2 = r14.getPointerId(r2)
            r11.activePointerId = r2
            r11.ensureVelocityTracker()
        L_0x00af:
            android.view.VelocityTracker r0 = r11.velocityTracker
            if (r0 == 0) goto L_0x00b6
            r0.addMovement(r14)
        L_0x00b6:
            return r1
        L_0x00b7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int setHeaderTopBottomOffset(CoordinatorLayout parent, V header, int newOffset) {
        return setHeaderTopBottomOffset(parent, header, newOffset, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    int setHeaderTopBottomOffset(CoordinatorLayout parent, V header, int newOffset, int minOffset, int maxOffset) {
        int newOffset2;
        int curOffset = getTopAndBottomOffset();
        if (minOffset == 0 || curOffset < minOffset || curOffset > maxOffset || curOffset == (newOffset2 = MathUtils.clamp(newOffset, minOffset, maxOffset))) {
            return 0;
        }
        setTopAndBottomOffset(newOffset2);
        int consumed = curOffset - newOffset2;
        return consumed;
    }

    int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int scroll(CoordinatorLayout coordinatorLayout, V header, int dy, int minOffset, int maxOffset) {
        return setHeaderTopBottomOffset(coordinatorLayout, header, getTopBottomOffsetForScrollingSibling() - dy, minOffset, maxOffset);
    }

    final boolean fling(CoordinatorLayout coordinatorLayout, V layout, int minOffset, int maxOffset, float velocityY) {
        Runnable runnable = this.flingRunnable;
        if (runnable != null) {
            layout.removeCallbacks(runnable);
            this.flingRunnable = null;
        }
        if (this.scroller == null) {
            this.scroller = new OverScroller(layout.getContext());
        }
        this.scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(velocityY), 0, 0, minOffset, maxOffset);
        if (this.scroller.computeScrollOffset()) {
            FlingRunnable flingRunnable = new FlingRunnable(coordinatorLayout, layout);
            this.flingRunnable = flingRunnable;
            ViewCompat.postOnAnimation(layout, flingRunnable);
            return true;
        }
        onFlingFinished(coordinatorLayout, layout);
        return false;
    }

    void onFlingFinished(CoordinatorLayout parent, V layout) {
    }

    boolean canDragView(V view) {
        return false;
    }

    int getMaxDragOffset(V view) {
        return -view.getHeight();
    }

    int getScrollRangeForDragFling(V view) {
        return view.getHeight();
    }

    private void ensureVelocityTracker() {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class FlingRunnable implements Runnable {
        private final V layout;
        private final CoordinatorLayout parent;

        FlingRunnable(CoordinatorLayout parent, V layout) {
            this.parent = parent;
            this.layout = layout;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.layout != null && HeaderBehavior.this.scroller != null) {
                if (HeaderBehavior.this.scroller.computeScrollOffset()) {
                    HeaderBehavior headerBehavior = HeaderBehavior.this;
                    headerBehavior.setHeaderTopBottomOffset(this.parent, this.layout, headerBehavior.scroller.getCurrY());
                    ViewCompat.postOnAnimation(this.layout, this);
                    return;
                }
                HeaderBehavior.this.onFlingFinished(this.parent, this.layout);
            }
        }
    }
}
