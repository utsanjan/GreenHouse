package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
abstract class Action<T> {
    boolean cancelled;
    final Drawable errorDrawable;
    final int errorResId;
    final String key;
    final int memoryPolicy;
    final int networkPolicy;
    final boolean noFade;
    final Picasso picasso;
    final Request request;
    final Object tag;
    final WeakReference<T> target;
    boolean willReplay;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void complete(Bitmap bitmap, Picasso.LoadedFrom loadedFrom);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void error();

    /* loaded from: classes.dex */
    static class RequestWeakReference<M> extends WeakReference<M> {
        final Action action;

        public RequestWeakReference(Action action, M referent, ReferenceQueue<? super M> q) {
            super(referent, q);
            this.action = action;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Action(Picasso picasso, T target, Request request, int memoryPolicy, int networkPolicy, int errorResId, Drawable errorDrawable, String key, Object tag, boolean noFade) {
        this.picasso = picasso;
        this.request = request;
        this.target = target == null ? null : new RequestWeakReference(this, target, picasso.referenceQueue);
        this.memoryPolicy = memoryPolicy;
        this.networkPolicy = networkPolicy;
        this.noFade = noFade;
        this.errorResId = errorResId;
        this.errorDrawable = errorDrawable;
        this.key = key;
        this.tag = tag != null ? tag : this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.cancelled = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request getRequest() {
        return this.request;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public T getTarget() {
        WeakReference<T> weakReference = this.target;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getKey() {
        return this.key;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isCancelled() {
        return this.cancelled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean willReplay() {
        return this.willReplay;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMemoryPolicy() {
        return this.memoryPolicy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getNetworkPolicy() {
        return this.networkPolicy;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Picasso getPicasso() {
        return this.picasso;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Picasso.Priority getPriority() {
        return this.request.priority;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object getTag() {
        return this.tag;
    }
}
