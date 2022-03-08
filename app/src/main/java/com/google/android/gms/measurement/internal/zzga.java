package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzga implements Runnable {
    private final /* synthetic */ zzhd zza;
    private final /* synthetic */ zzfy zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzga(zzfy zzfyVar, zzhd zzhdVar) {
        this.zzb = zzfyVar;
        this.zza = zzhdVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}
