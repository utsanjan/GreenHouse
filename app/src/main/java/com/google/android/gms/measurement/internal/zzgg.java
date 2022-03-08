package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgg implements Callable<List<zzks>> {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ zzgd zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgg(zzgd zzgdVar, String str, String str2, String str3) {
        this.zzd = zzgdVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzks> call() throws Exception {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkjVar = this.zzd.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzd.zza;
        return zzkjVar2.zze().zza(this.zza, this.zzb, this.zzc);
    }
}
