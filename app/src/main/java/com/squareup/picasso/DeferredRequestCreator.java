package com.squareup.picasso;

import android.view.ViewTreeObserver;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DeferredRequestCreator implements ViewTreeObserver.OnPreDrawListener {
    Callback callback;
    final RequestCreator creator;
    final WeakReference<ImageView> target;

    DeferredRequestCreator(RequestCreator creator, ImageView target) {
        this(creator, target, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeferredRequestCreator(RequestCreator creator, ImageView target, Callback callback) {
        this.creator = creator;
        this.target = new WeakReference<>(target);
        this.callback = callback;
        target.getViewTreeObserver().addOnPreDrawListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        ImageView target = this.target.get();
        if (target == null) {
            return true;
        }
        ViewTreeObserver vto = target.getViewTreeObserver();
        if (!vto.isAlive()) {
            return true;
        }
        int width = target.getWidth();
        int height = target.getHeight();
        if (width <= 0 || height <= 0) {
            return true;
        }
        vto.removeOnPreDrawListener(this);
        this.creator.unfit().resize(width, height).into(target, this.callback);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.callback = null;
        ImageView target = this.target.get();
        if (target != null) {
            ViewTreeObserver vto = target.getViewTreeObserver();
            if (vto.isAlive()) {
                vto.removeOnPreDrawListener(this);
            }
        }
    }
}
