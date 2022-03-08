package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zziz implements Runnable {
    private final /* synthetic */ zzij zza;
    private final /* synthetic */ zzir zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziz(zzir zzirVar, zzij zzijVar) {
        this.zzb = zzirVar;
        this.zza = zzijVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        zzemVar = this.zzb.zzb;
        if (zzemVar == null) {
            this.zzb.zzr().zzf().zza("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zza == null) {
                zzemVar.zza(0L, (String) null, (String) null, this.zzb.zzn().getPackageName());
            } else {
                zzemVar.zza(this.zza.zzc, this.zza.zza, this.zza.zzb, this.zzb.zzn().getPackageName());
            }
            this.zzb.zzak();
        } catch (RemoteException e) {
            this.zzb.zzr().zzf().zza("Failed to send current screen to the service", e);
        }
    }
}
