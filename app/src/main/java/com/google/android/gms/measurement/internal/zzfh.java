package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
final class zzfh implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzfe zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfh(zzfe zzfeVar, boolean z) {
        this.zzb = zzfeVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzkj zzkjVar;
        zzkjVar = this.zzb.zzb;
        zzkjVar.zza(this.zza);
    }
}
