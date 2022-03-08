package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zziy implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzir zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziy(zzir zzirVar, Bundle bundle, zzn zznVar) {
        this.zzc = zzirVar;
        this.zza = bundle;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        zzemVar = this.zzc.zzb;
        if (zzemVar == null) {
            this.zzc.zzr().zzf().zza("Failed to send default event parameters to service");
            return;
        }
        try {
            zzemVar.zza(this.zza, this.zzb);
        } catch (RemoteException e) {
            this.zzc.zzr().zzf().zza("Failed to send default event parameters to service", e);
        }
    }
}
