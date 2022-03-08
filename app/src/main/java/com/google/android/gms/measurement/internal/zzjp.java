package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzjp implements Runnable {
    private final /* synthetic */ zzji zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjp(zzji zzjiVar) {
        this.zza = zzjiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza.zzb = null;
        this.zza.zza.zzan();
    }
}
