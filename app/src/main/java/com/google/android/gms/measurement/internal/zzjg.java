package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjg implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzn zzf;
    private final /* synthetic */ zzir zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjg(zzir zzirVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z, zzn zznVar) {
        this.zzg = zzirVar;
        this.zza = atomicReference;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = z;
        this.zzf = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        synchronized (this.zza) {
            try {
                zzemVar = this.zzg.zzb;
            } catch (RemoteException e) {
                this.zzg.zzr().zzf().zza("(legacy) Failed to get user properties; remote exception", zzeu.zza(this.zzb), this.zzc, e);
                this.zza.set(Collections.emptyList());
                this.zza.notify();
            }
            if (zzemVar == null) {
                this.zzg.zzr().zzf().zza("(legacy) Failed to get user properties; not connected to service", zzeu.zza(this.zzb), this.zzc, this.zzd);
                this.zza.set(Collections.emptyList());
                this.zza.notify();
                return;
            }
            if (TextUtils.isEmpty(this.zzb)) {
                this.zza.set(zzemVar.zza(this.zzc, this.zzd, this.zze, this.zzf));
            } else {
                this.zza.set(zzemVar.zza(this.zzb, this.zzc, this.zzd, this.zze));
            }
            this.zzg.zzak();
            this.zza.notify();
        }
    }
}
