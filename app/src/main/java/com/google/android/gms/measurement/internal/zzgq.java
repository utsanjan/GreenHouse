package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgq implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzgd zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgq(zzgd zzgdVar, String str, String str2, String str3, long j) {
        this.zze = zzgdVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        if (this.zza == null) {
            zzkjVar2 = this.zze.zza;
            zzkjVar2.zzs().zzv().zza(this.zzb, (zzij) null);
            return;
        }
        zzij zzijVar = new zzij(this.zzc, this.zza, this.zzd);
        zzkjVar = this.zze.zza;
        zzkjVar.zzs().zzv().zza(this.zzb, zzijVar);
    }
}
