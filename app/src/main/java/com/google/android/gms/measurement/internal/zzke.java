package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzke extends zzag {
    private final /* synthetic */ zzkj zza;
    private final /* synthetic */ zzkf zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzke(zzkf zzkfVar, zzgu zzguVar, zzkj zzkjVar) {
        super(zzguVar);
        this.zzb = zzkfVar;
        this.zza = zzkjVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzag
    public final void zza() {
        this.zzb.zzf();
        this.zzb.zzr().zzx().zza("Starting upload from DelayedRunnable");
        this.zza.zzl();
    }
}
