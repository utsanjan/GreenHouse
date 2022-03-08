package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzis implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ boolean zzc;
    private final /* synthetic */ zzir zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzis(zzir zzirVar, AtomicReference atomicReference, zzn zznVar, boolean z) {
        this.zzd = zzirVar;
        this.zza = atomicReference;
        this.zzb = zznVar;
        this.zzc = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        synchronized (this.zza) {
            try {
                zzemVar = this.zzd.zzb;
            } catch (RemoteException e) {
                this.zzd.zzr().zzf().zza("Failed to get all user properties; remote exception", e);
                this.zza.notify();
            }
            if (zzemVar == null) {
                this.zzd.zzr().zzf().zza("Failed to get all user properties; not connected to service");
                this.zza.notify();
                return;
            }
            this.zza.set(zzemVar.zza(this.zzb, this.zzc));
            this.zzd.zzak();
            this.zza.notify();
        }
    }
}
