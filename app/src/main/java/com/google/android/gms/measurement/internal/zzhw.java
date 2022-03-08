package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzhw implements Runnable {
    private final /* synthetic */ zzhc zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhw(zzhc zzhcVar) {
        this.zza = zzhcVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzb.zza();
    }
}
