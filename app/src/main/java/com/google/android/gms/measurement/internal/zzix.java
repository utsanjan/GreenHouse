package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzw;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzix implements Runnable {
    private final /* synthetic */ zzn zza;
    private final /* synthetic */ zzw zzb;
    private final /* synthetic */ zzir zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzix(zzir zzirVar, zzn zznVar, zzw zzwVar) {
        this.zzc = zzirVar;
        this.zza = zznVar;
        this.zzb = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        String str = null;
        try {
            zzemVar = this.zzc.zzb;
            if (zzemVar == null) {
                this.zzc.zzr().zzf().zza("Failed to get app instance id");
                return;
            }
            str = zzemVar.zzc(this.zza);
            if (str != null) {
                this.zzc.zzf().zza(str);
                this.zzc.zzs().zzj.zza(str);
            }
            this.zzc.zzak();
        } catch (RemoteException e) {
            this.zzc.zzr().zzf().zza("Failed to get app instance id", e);
        } finally {
            this.zzc.zzp().zza(this.zzb, str);
        }
    }
}
