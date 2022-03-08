package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzgf implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzgd zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgf(zzgd zzgdVar, zzn zznVar) {
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
        zzn zznVar = this.zza;
        zzkjVar2.zzq().zzd();
        zzkjVar2.zzk();
        Preconditions.checkNotEmpty(zznVar.zza);
        zzkjVar2.zzc(zznVar);
    }
}
