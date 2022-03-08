package com.google.android.gms.measurement.internal;

import android.app.ActivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzkh;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkd {
    final /* synthetic */ zzjv zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkd(zzjv zzjvVar) {
        this.zza = zzjvVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        this.zza.zzd();
        if (this.zza.zzs().zza(this.zza.zzm().currentTimeMillis())) {
            this.zza.zzs().zzm.zza(true);
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (runningAppProcessInfo.importance == 100) {
                this.zza.zzr().zzx().zza("Detected application was in foreground");
                zzb(this.zza.zzm().currentTimeMillis(), false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(long j, boolean z) {
        this.zza.zzd();
        this.zza.zzab();
        if (this.zza.zzs().zza(j)) {
            this.zza.zzs().zzm.zza(true);
        }
        this.zza.zzs().zzp.zza(j);
        if (this.zza.zzs().zzm.zza()) {
            zzb(j, z);
        }
    }

    private final void zzb(long j, boolean z) {
        this.zza.zzd();
        if (this.zza.zzy.zzab()) {
            this.zza.zzs().zzp.zza(j);
            this.zza.zzr().zzx().zza("Session started, time", Long.valueOf(this.zza.zzm().elapsedRealtime()));
            Long valueOf = Long.valueOf(j / 1000);
            this.zza.zzf().zza("auto", "_sid", valueOf, j);
            this.zza.zzs().zzm.zza(false);
            Bundle bundle = new Bundle();
            bundle.putLong("_sid", valueOf.longValue());
            if (this.zza.zzt().zza(zzaq.zzbp) && z) {
                bundle.putLong("_aib", 1L);
            }
            this.zza.zzf().zza("auto", "_s", j, bundle);
            if (zzkh.zzb() && this.zza.zzt().zza(zzaq.zzbu)) {
                String zza = this.zza.zzs().zzu.zza();
                if (!TextUtils.isEmpty(zza)) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString("_ffr", zza);
                    this.zza.zzf().zza("auto", "_ssr", j, bundle2);
                }
            }
        }
    }
}
