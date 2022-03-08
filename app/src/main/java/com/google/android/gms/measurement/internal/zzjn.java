package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzjn implements Runnable {
    private final /* synthetic */ zzem zza;
    private final /* synthetic */ zzji zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjn(zzji zzjiVar, zzem zzemVar) {
        this.zzb = zzjiVar;
        this.zza = zzemVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zzb) {
            this.zzb.zzb = false;
            if (!this.zzb.zza.zzab()) {
                this.zzb.zza.zzr().zzw().zza("Connected to remote service");
                this.zzb.zza.zza(this.zza);
            }
        }
    }
}
