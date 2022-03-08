package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgr implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgr(zzgd zzgdVar, zzn zznVar) {
        this.zzb = zzgdVar;
        this.zza = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkjVar = this.zzb.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzb.zza;
        zzkjVar2.zzb(this.zza);
    }
}
