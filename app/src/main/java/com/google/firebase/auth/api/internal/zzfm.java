package com.google.firebase.auth.api.internal;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzfm implements Runnable {
    private final /* synthetic */ zzfl zza;
    private final /* synthetic */ zzfg zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfm(zzfg zzfgVar, zzfl zzflVar) {
        this.zzb = zzfgVar;
        this.zza = zzflVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb.zza.zzi) {
            if (!this.zzb.zza.zzi.isEmpty()) {
                this.zza.zza(this.zzb.zza.zzi.get(0), new Object[0]);
            }
        }
    }
}
