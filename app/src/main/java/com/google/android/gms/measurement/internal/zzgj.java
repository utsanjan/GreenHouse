package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgj implements Callable<List<zzw>> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzgd zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgj(zzgd zzgdVar, zzn zznVar, String str, String str2) {
        this.zzd = zzgdVar;
        this.zza = zznVar;
        this.zzb = str;
        this.zzc = str2;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzw> call() throws Exception {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkjVar = this.zzd.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzd.zza;
        return zzkjVar2.zze().zzb(this.zza.zza, this.zzb, this.zzc);
    }
}
