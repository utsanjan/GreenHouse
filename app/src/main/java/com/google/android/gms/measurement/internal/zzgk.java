package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgk implements Runnable {
    private final /* synthetic */ zzao zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzgd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgk(zzgd zzgdVar, zzao zzaoVar, zzn zznVar) {
        this.zzc = zzgdVar;
        this.zza = zzaoVar;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzao zzb = this.zzc.zzb(this.zza, this.zzb);
        zzkjVar = this.zzc.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzc.zza;
        zzkjVar2.zza(zzb, this.zzb);
    }
}
