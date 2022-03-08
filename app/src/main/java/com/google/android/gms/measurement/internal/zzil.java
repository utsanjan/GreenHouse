package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzil implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzij zzb;
    private final /* synthetic */ zzij zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzii zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzil(zzii zziiVar, Bundle bundle, zzij zzijVar, zzij zzijVar2, long j) {
        this.zze = zziiVar;
        this.zza = bundle;
        this.zzb = zzijVar;
        this.zzc = zzijVar2;
        this.zzd = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd);
    }
}
