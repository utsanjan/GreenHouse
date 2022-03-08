package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzki implements Runnable {
    private final /* synthetic */ zzko zza;
    private final /* synthetic */ zzkj zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzki(zzkj zzkjVar, zzko zzkoVar) {
        this.zzb = zzkjVar;
        this.zza = zzkoVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza(this.zza);
        this.zzb.zza();
    }
}
