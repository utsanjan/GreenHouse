package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzju implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzjv zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzju(zzjv zzjvVar, long j) {
        this.zzb = zzjvVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzjv.zza(this.zzb, this.zza);
    }
}
