package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzip implements Runnable {
    private final /* synthetic */ zzij zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzii zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzip(zzii zziiVar, zzij zzijVar, long j) {
        this.zzc = zziiVar;
        this.zza = zzijVar;
        this.zzb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza(this.zza, false, this.zzb);
        this.zzc.zza = null;
        this.zzc.zzh().zza((zzij) null);
    }
}
