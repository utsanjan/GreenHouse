package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgt implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgt(zzgd zzgdVar, zzw zzwVar, zzn zznVar) {
        this.zzc = zzgdVar;
        this.zza = zzwVar;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkj zzkjVar3;
        zzkjVar = this.zzc.zza;
        zzkjVar.zzo();
        if (this.zza.zzc.zza() == null) {
            zzkjVar3 = this.zzc.zza;
            zzkjVar3.zzb(this.zza, this.zzb);
            return;
        }
        zzkjVar2 = this.zzc.zza;
        zzkjVar2.zza(this.zza, this.zzb);
    }
}
