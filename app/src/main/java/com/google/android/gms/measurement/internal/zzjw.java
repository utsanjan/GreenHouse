package com.google.android.gms.measurement.internal;

import android.os.Handler;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjw {
    final /* synthetic */ zzjv zza;
    private zzjz zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjw(zzjv zzjvVar) {
        this.zza = zzjvVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        Handler handler;
        this.zza.zzd();
        if (this.zza.zzt().zza(zzaq.zzbo) && this.zzb != null) {
            handler = this.zza.zzc;
            handler.removeCallbacks(this.zzb);
        }
        if (this.zza.zzt().zza(zzaq.zzcc)) {
            this.zza.zzs().zzr.zza(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(long j) {
        Handler handler;
        if (this.zza.zzt().zza(zzaq.zzbo)) {
            this.zzb = new zzjz(this, this.zza.zzm().currentTimeMillis(), j);
            handler = this.zza.zzc;
            handler.postDelayed(this.zzb, 2000L);
        }
    }
}
