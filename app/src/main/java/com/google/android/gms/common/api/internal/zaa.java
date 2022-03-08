package com.google.android.gms.common.api.internal;

import android.app.Activity;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public final class zaa extends ActivityLifecycleObserver {
    private final WeakReference<C0031zaa> zaco;

    public zaa(Activity activity) {
        this(C0031zaa.zaa(activity));
    }

    private zaa(C0031zaa zaaVar) {
        this.zaco = new WeakReference<>(zaaVar);
    }

    @Override // com.google.android.gms.common.api.internal.ActivityLifecycleObserver
    public final ActivityLifecycleObserver onStopCallOnce(Runnable runnable) {
        C0031zaa zaaVar = this.zaco.get();
        if (zaaVar != null) {
            zaaVar.zaa(runnable);
            return this;
        }
        throw new IllegalStateException("The target activity has already been GC'd");
    }

    /* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
    /* renamed from: com.google.android.gms.common.api.internal.zaa$zaa  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    static class C0031zaa extends LifecycleCallback {
        private List<Runnable> zacn = new ArrayList();

        /* JADX INFO: Access modifiers changed from: private */
        public static C0031zaa zaa(Activity activity) {
            C0031zaa zaaVar;
            synchronized (activity) {
                LifecycleFragment fragment = getFragment(activity);
                zaaVar = (C0031zaa) fragment.getCallbackOrNull("LifecycleObserverOnStop", C0031zaa.class);
                if (zaaVar == null) {
                    zaaVar = new C0031zaa(fragment);
                }
            }
            return zaaVar;
        }

        private C0031zaa(LifecycleFragment lifecycleFragment) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("LifecycleObserverOnStop", this);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final synchronized void zaa(Runnable runnable) {
            this.zacn.add(runnable);
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        public void onStop() {
            List<Runnable> list;
            synchronized (this) {
                list = this.zacn;
                this.zacn = new ArrayList();
            }
            for (Runnable runnable : list) {
                runnable.run();
            }
        }
    }
}
