package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgm implements Callable<byte[]> {
    private final /* synthetic */ zzao zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzgd zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgm(zzgd zzgdVar, zzao zzaoVar, String str) {
        this.zzc = zzgdVar;
        this.zza = zzaoVar;
        this.zzb = str;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ byte[] call() throws Exception {
        zzkj zzkjVar;
        zzkj zzkjVar2;
        zzkjVar = this.zzc.zza;
        zzkjVar.zzo();
        zzkjVar2 = this.zzc.zza;
        return zzkjVar2.zzg().zza(this.zza, this.zzb);
    }
}
