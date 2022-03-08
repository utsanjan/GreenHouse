package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgo implements Callable<List<zzks>> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgo(zzgd zzgdVar, zzn zznVar) {
        this.zzb = zzgdVar;
        this.zza = zznVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ List<zzks> call() throws Exception {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkjVar = this.zzb.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzb.zza;
        return zzkjVar2.zze().zza(this.zza.zza);
    }
}
