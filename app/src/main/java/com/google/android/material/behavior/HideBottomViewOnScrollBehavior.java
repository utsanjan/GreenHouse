package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.animation.AnimationUtils;

/* loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    protected static final int ENTER_ANIMATION_DURATION = 225;
    protected static final int EXIT_ANIMATION_DURATION = 175;
    private static final int STATE_SCROLLED_DOWN = 1;
    private static final int STATE_SCROLLED_UP = 2;
    private ViewPropertyAnimator currentAnimator;
    private int height = 0;
    private int currentState = 2;
    private int additionalHiddenOffsetY = 0;

    public HideBottomViewOnScrollBehavior() {
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        ViewGroup.MarginLayoutParams paramsCompat = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
        this.height = child.getMeasuredHeight() + paramsCompat.bottomMargin;
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    public void setAdditionalHiddenOffsetY(V child, int offset) {
        this.additionalHiddenOffsetY = offset;
        if (this.currentState == 1) {
            child.setTranslationY(this.height + offset);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == 2;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyConsumed > 0) {
            slideDown(child);
        } else if (dyConsumed < 0) {
            slideUp(child);
        }
    }

    public void slideUp(V child) {
        if (this.currentState != 2) {
            ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                child.clearAnimation();
            }
            this.currentState = 2;
            animateChildTo(child, 0, 225L, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        }
    }

    public void slideDown(V child) {
        if (this.currentState != 1) {
            ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
            if (viewPropertyAnimator != null) {
                viewPropertyAnimator.cancel();
                child.clearAnimation();
            }
            this.currentState = 1;
            animateChildTo(child, this.height + this.additionalHiddenOffsetY, 175L, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
    }

    private void animateChildTo(V child, int targetY, long duration, TimeInterpolator interpolator) {
        this.currentAnimator = child.animate().translationY(targetY).setInterpolator(interpolator).setDuration(duration).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                HideBottomViewOnScrollBehavior.this.currentAnimator = null;
            }
        });
    }
}
