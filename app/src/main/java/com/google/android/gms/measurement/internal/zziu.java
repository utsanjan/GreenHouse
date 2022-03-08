package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zziu implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzn zzb;
    private final /* synthetic */ zzir zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zziu(zzir zzirVar, AtomicReference atomicReference, zzn zznVar) {
        this.zzc = zzirVar;
        this.zza = atomicReference;
        this.zzb = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        synchronized (this.zza) {
            try {
                zzemVar = this.zzc.zzb;
            } catch (RemoteException e) {
                this.zzc.zzr().zzf().zza("Failed to get app instance id", e);
                this.zza.notify();
            }
            if (zzemVar == null) {
                this.zzc.zzr().zzf().zza("Failed to get app instance id");
                this.zza.notify();
                return;
            }
            this.zza.set(zzemVar.zzc(this.zzb));
            String str = (String) this.zza.get();
            if (str != null) {
                this.zzc.zzf().zza(str);
                this.zzc.zzs().zzj.zza(str);
            }
            this.zzc.zzak();
            this.zza.notify();
        }
    }
}
