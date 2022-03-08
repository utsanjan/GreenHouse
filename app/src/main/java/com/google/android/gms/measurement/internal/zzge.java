package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzge implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzgd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzge(zzgd zzgdVar, zzw zzwVar) {
        this.zzb = zzgdVar;
        this.zza = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkj zzkjVar3;
        zzkjVar = this.zzb.zza;
        zzkjVar.zzo();
        if (this.zza.zzc.zza() == null) {
            zzkjVar3 = this.zzb.zza;
            zzkjVar3.zzb(this.zza);
            return;
        }
        zzkjVar2 = this.zzb.zza;
        zzkjVar2.zza(this.zza);
    }
}
