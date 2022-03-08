package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzq;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public abstract class zzag {
    private static volatile Handler zzb;
    private final zzgu zza;
    private final Runnable zzc;
    private volatile long zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzag(zzgu zzguVar) {
        Preconditions.checkNotNull(zzguVar);
        this.zza = zzguVar;
        this.zzc = new zzaj(this, zzguVar);
    }

    public abstract void zza();

    public final void zza(long j) {
        zzc();
        if (j >= 0) {
            this.zzd = this.zza.zzm().currentTimeMillis();
            if (!zzd().postDelayed(this.zzc, j)) {
                this.zza.zzr().zzf().zza("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean zzb() {
        return this.zzd != 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzc() {
        this.zzd = 0L;
        zzd().removeCallbacks(this.zzc);
    }

    private final Handler zzd() {
        Handler handler;
        if (zzb != null) {
            return zzb;
        }
        synchronized (zzag.class) {
            if (zzb == null) {
                zzb = new zzq(this.zza.zzn().getMainLooper());
            }
            handler = zzb;
        }
        return handler;
    }
}
