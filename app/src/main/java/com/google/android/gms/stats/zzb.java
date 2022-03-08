package com.google.android.gms.stats;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzb implements Runnable {
    private final /* synthetic */ WakeLock zzp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzb(WakeLock wakeLock) {
        this.zzp = wakeLock;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzp.zza(0);
    }
}
