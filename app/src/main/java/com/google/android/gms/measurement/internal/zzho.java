package com.google.android.gms.measurement.internal;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzho implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ zzhc zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzho(zzhc zzhcVar, AtomicReference atomicReference) {
        this.zzb = zzhcVar;
        this.zza = atomicReference;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.zza) {
            this.zza.set(this.zzb.zzt().zzj(this.zzb.zzg().zzab()));
            this.zza.notify();
        }
    }
}
