package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgn implements Runnable {
    private final /* synthetic */ zzao zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzgd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgn(zzgd zzgdVar, zzao zzaoVar, String str) {
        this.zzc = zzgdVar;
        this.zza = zzaoVar;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkjVar = this.zzc.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzc.zza;
        zzkjVar2.zza(this.zza, this.zzb);
    }
}
