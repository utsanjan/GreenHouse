package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzw;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjj implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzn zzd;
    private final /* synthetic */ zzw zze;
    private final /* synthetic */ zzir zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjj(zzir zzirVar, String str, String str2, boolean z, zzn zznVar, zzw zzwVar) {
        this.zzf = zzirVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = zznVar;
        this.zze = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        Bundle bundle = new Bundle();
        try {
            zzemVar = this.zzf.zzb;
            if (zzemVar == null) {
                this.zzf.zzr().zzf().zza("Failed to get user properties; not connected to service", this.zza, this.zzb);
                return;
            }
            bundle = zzkr.zza(zzemVar.zza(this.zza, this.zzb, this.zzc, this.zzd));
            this.zzf.zzak();
        } catch (RemoteException e) {
            this.zzf.zzr().zzf().zza("Failed to get user properties; remote exception", this.zza, e);
        } finally {
            this.zzf.zzp().zza(this.zze, bundle);
        }
    }
}
