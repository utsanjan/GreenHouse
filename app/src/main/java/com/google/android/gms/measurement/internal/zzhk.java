package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzla;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzhk implements Runnable {
    private final /* synthetic */ long zza;
    private final /* synthetic */ zzhc zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhk(zzhc zzhcVar, long j) {
        this.zzb = zzhcVar;
        this.zza = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzhc zzhcVar = this.zzb;
        long j = this.zza;
        zzhcVar.zzd();
        zzhcVar.zzb();
        zzhcVar.zzw();
        zzhcVar.zzr().zzw().zza("Resetting analytics data (FE)");
        zzjv zzk = zzhcVar.zzk();
        zzk.zzd();
        zzk.zzb.zza();
        boolean zzab = zzhcVar.zzy.zzab();
        zzfg zzs = zzhcVar.zzs();
        zzs.zzh.zza(j);
        if (!TextUtils.isEmpty(zzs.zzs().zzu.zza())) {
            zzs.zzu.zza(null);
        }
        if (zzla.zzb() && zzs.zzt().zza(zzaq.zzbv)) {
            zzs.zzp.zza(0L);
        }
        if (!zzs.zzt().zzh()) {
            zzs.zzc(!zzab);
        }
        zzs.zzv.zza(null);
        zzs.zzw.zza(0L);
        zzs.zzx.zza(null);
        zzhcVar.zzh().zzad();
        if (zzla.zzb() && zzhcVar.zzt().zza(zzaq.zzbv)) {
            zzhcVar.zzk().zza.zza();
        }
        zzhcVar.zzc = !zzab;
        this.zzb.zzh().zza(new AtomicReference<>());
    }
}
