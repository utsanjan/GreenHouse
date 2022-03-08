package com.google.android.gms.measurement.internal;

import com.google.android.gms.internal.measurement.zzw;

/* compiled from: com.google.android.gms:play-services-measurement-sdk@@17.4.3 */
/* loaded from: classes.dex */
final class zzj implements Runnable {
    private final /* synthetic */ zzw zza;
    private final /* synthetic */ zzao zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ AppMeasurementDynamiteService zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzj(AppMeasurementDynamiteService appMeasurementDynamiteService, zzw zzwVar, zzao zzaoVar, String str) {
        this.zzd = appMeasurementDynamiteService;
        this.zza = zzwVar;
        this.zzb = zzaoVar;
        this.zzc = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzd.zza.zzw().zza(this.zza, this.zzb, this.zzc);
    }
}
