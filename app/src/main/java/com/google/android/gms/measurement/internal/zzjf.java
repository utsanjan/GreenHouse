package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjf implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ boolean zzb;
    private final /* synthetic */ zzw zzc;
    private final /* synthetic */ zzn zzd;
    private final /* synthetic */ zzw zze;
    private final /* synthetic */ zzir zzf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjf(zzir zzirVar, boolean z, boolean z2, zzw zzwVar, zzn zznVar, zzw zzwVar2) {
        this.zzf = zzirVar;
        this.zza = z;
        this.zzb = z2;
        this.zzc = zzwVar;
        this.zzd = zznVar;
        this.zze = zzwVar2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        zzemVar = this.zzf.zzb;
        if (zzemVar == null) {
            this.zzf.zzr().zzf().zza("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zza) {
            this.zzf.zza(zzemVar, this.zzb ? null : this.zzc, this.zzd);
        } else {
            try {
                if (TextUtils.isEmpty(this.zze.zza)) {
                    zzemVar.zza(this.zzc, this.zzd);
                } else {
                    zzemVar.zza(this.zzc);
                }
            } catch (RemoteException e) {
                this.zzf.zzr().zzf().zza("Failed to send conditional user property to the service", e);
            }
        }
        this.zzf.zzak();
    }
}
