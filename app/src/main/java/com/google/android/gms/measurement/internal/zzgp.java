package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgp implements Runnable {
    private final /* synthetic */ zzkq zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgp(zzgd zzgdVar, zzkq zzkqVar, zzn zznVar) {
        this.zzc = zzgdVar;
        this.zza = zzkqVar;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkj zzkjVar3;
        zzkjVar = this.zzc.zza;
        zzkjVar.zzo();
        if (this.zza.zza() == null) {
            zzkjVar3 = this.zzc.zza;
            zzkjVar3.zzb(this.zza, this.zzb);
            return;
        }
        zzkjVar2 = this.zzc.zza;
        zzkjVar2.zza(this.zza, this.zzb);
    }
}
