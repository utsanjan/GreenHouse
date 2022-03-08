package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzaj implements Runnable {
    private final /* synthetic */ zzgu zza;
    private final /* synthetic */ zzag zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(zzag zzagVar, zzgu zzguVar) {
        this.zzb = zzagVar;
        this.zza = zzguVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzu();
        if (zzx.zza()) {
            this.zza.zzq().zza(this);
            return;
        }
        boolean zzb = this.zzb.zzb();
        this.zzb.zzd = 0L;
        if (zzb) {
            this.zzb.zza();
        }
    }
}
