package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzik implements Runnable {
    private final /* synthetic */ zzij zza;
    private final /* synthetic */ zzij zzb;
    private final /* synthetic */ long zzc;
    private final /* synthetic */ boolean zzd;
    private final /* synthetic */ zzii zze;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzik(zzii zziiVar, zzij zzijVar, zzij zzijVar2, long j, boolean z) {
        this.zze = zziiVar;
        this.zza = zzijVar;
        this.zzb = zzijVar2;
        this.zzc = j;
        this.zzd = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zze.zza(this.zza, this.zzb, this.zzc, this.zzd, (Bundle) null);
    }
}
