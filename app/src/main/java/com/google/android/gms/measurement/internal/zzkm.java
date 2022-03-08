package com.google.android.gms.measurement.internal;

import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkm implements Callable<String> {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzkj zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkm(zzkj zzkjVar, zzn zznVar) {
        this.zzb = zzkjVar;
        this.zza = zznVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        zzf zzc = this.zzb.zzc(this.zza);
        if (zzc != null) {
            return zzc.zzd();
        }
        this.zzb.zzr().zzi().zza("App info was null when attempting to get app instance id");
        return null;
    }
}
