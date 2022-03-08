package com.google.android.gms.common.api.internal;

import android.os.Bundle;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
final class zzb implements Runnable {
    private final /* synthetic */ LifecycleCallback zzbu;
    private final /* synthetic */ String zzbv;
    private final /* synthetic */ zza zzbw;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(zza zzaVar, LifecycleCallback lifecycleCallback, String str) {
        this.zzbw = zzaVar;
        this.zzbu = lifecycleCallback;
        this.zzbv = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Bundle bundle;
        Bundle bundle2;
        Bundle bundle3;
        i = this.zzbw.zzbs;
        if (i > 0) {
            LifecycleCallback lifecycleCallback = this.zzbu;
            bundle = this.zzbw.zzbt;
            if (bundle != null) {
                bundle3 = this.zzbw.zzbt;
                bundle2 = bundle3.getBundle(this.zzbv);
            } else {
                bundle2 = null;
            }
            lifecycleCallback.onCreate(bundle2);
        }
        i2 = this.zzbw.zzbs;
        if (i2 >= 2) {
            this.zzbu.onStart();
        }
        i3 = this.zzbw.zzbs;
        if (i3 >= 3) {
            this.zzbu.onResume();
        }
        i4 = this.zzbw.zzbs;
        if (i4 >= 4) {
            this.zzbu.onStop();
        }
        i5 = this.zzbw.zzbs;
        if (i5 >= 5) {
            this.zzbu.onDestroy();
        }
    }
}
