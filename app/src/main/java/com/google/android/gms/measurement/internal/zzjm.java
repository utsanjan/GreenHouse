package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzjm implements Runnable {
    private final /* synthetic */ zzji zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjm(zzji zzjiVar) {
        this.zza = zzjiVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzir zzirVar = this.zza.zza;
        Context zzn = this.zza.zza.zzn();
        this.zza.zza.zzu();
        zzirVar.zza(new ComponentName(zzn, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
