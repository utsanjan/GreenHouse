package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zzw;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjb implements Runnable {
    private final /* synthetic */ zzao zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzw zzc;
    private final /* synthetic */ zzir zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjb(zzir zzirVar, zzao zzaoVar, String str, zzw zzwVar) {
        this.zzd = zzirVar;
        this.zza = zzaoVar;
        this.zzb = str;
        this.zzc = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        byte[] bArr = null;
        try {
            zzemVar = this.zzd.zzb;
            if (zzemVar == null) {
                this.zzd.zzr().zzf().zza("Discarding data. Failed to send event to service to bundle");
                return;
            }
            bArr = zzemVar.zza(this.zza, this.zzb);
            this.zzd.zzak();
        } catch (RemoteException e) {
            this.zzd.zzr().zzf().zza("Failed to send event to the service to bundle", e);
        } finally {
            this.zzd.zzp().zza(this.zzc, bArr);
        }
    }
}
