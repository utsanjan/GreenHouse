package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgh implements Callable<List<zzks>> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzgd zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgh(zzgd zzgdVar, zzn zznVar, String str, String str2) {
        this.zzd = zzgdVar;
        this.zza = zznVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzks> call() throws Exception {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkjVar = this.zzd.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzd.zza;
        return zzkjVar2.zze().zza(this.zza.zza, this.zzb, this.zzc);
    }
}
