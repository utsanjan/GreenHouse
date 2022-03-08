package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzio implements Runnable {
    private final /* synthetic */ zzii zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzio(zzii zziiVar) {
        this.zza = zziiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzh = null;
    }
}
