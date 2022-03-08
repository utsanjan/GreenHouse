package com.google.android.material.floatingactionbutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.content.Context;
import android.view.View;
import androidx.core.util.Preconditions;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.MotionSpec;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
abstract class BaseMotionStrategy implements MotionStrategy {
    private final Context context;
    private MotionSpec defaultMotionSpec;
    private final ExtendedFloatingActionButton fab;
    private final ArrayList<Animator.AnimatorListener> listeners = new ArrayList<>();
    private MotionSpec motionSpec;
    private final AnimatorTracker tracker;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BaseMotionStrategy(ExtendedFloatingActionButton fab, AnimatorTracker tracker) {
        this.fab = fab;
        this.context = fab.getContext();
        this.tracker = tracker;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void setMotionSpec(MotionSpec motionSpec) {
        this.motionSpec = motionSpec;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final MotionSpec getCurrentMotionSpec() {
        MotionSpec motionSpec = this.motionSpec;
        if (motionSpec != null) {
            return motionSpec;
        }
        if (this.defaultMotionSpec == null) {
            this.defaultMotionSpec = MotionSpec.createFromResource(this.context, getDefaultMotionSpecResource());
        }
        return (MotionSpec) Preconditions.checkNotNull(this.defaultMotionSpec);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void addAnimationListener(Animator.AnimatorListener listener) {
        this.listeners.add(listener);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final void removeAnimationListener(Animator.AnimatorListener listener) {
        this.listeners.remove(listener);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public final List<Animator.AnimatorListener> getListeners() {
        return this.listeners;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public MotionSpec getMotionSpec() {
        return this.motionSpec;
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationStart(Animator animator) {
        this.tracker.onNextAnimationStart(animator);
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationEnd() {
        this.tracker.clear();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public void onAnimationCancel() {
        this.tracker.clear();
    }

    @Override // com.google.android.material.floatingactionbutton.MotionStrategy
    public AnimatorSet createAnimator() {
        return createAnimator(getCurrentMotionSpec());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimatorSet createAnimator(MotionSpec spec) {
        List<Animator> animators = new ArrayList<>();
        if (spec.hasPropertyValues("opacity")) {
            animators.add(spec.getAnimator("opacity", this.fab, View.ALPHA));
        }
        if (spec.hasPropertyValues("scale")) {
            animators.add(spec.getAnimator("scale", this.fab, View.SCALE_Y));
            animators.add(spec.getAnimator("scale", this.fab, View.SCALE_X));
        }
        if (spec.hasPropertyValues("width")) {
            animators.add(spec.getAnimator("width", this.fab, ExtendedFloatingActionButton.WIDTH));
        }
        if (spec.hasPropertyValues("height")) {
            animators.add(spec.getAnimator("height", this.fab, ExtendedFloatingActionButton.HEIGHT));
        }
        AnimatorSet set = new AnimatorSet();
        AnimatorSetCompat.playTogether(set, animators);
        return set;
    }
}
