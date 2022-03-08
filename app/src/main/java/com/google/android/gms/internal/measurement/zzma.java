package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzma implements zzmb {
    private static final zzcv<Long> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Boolean> zzc;
    private static final zzcv<Boolean> zzd;
    private static final zzcv<Long> zze;

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final boolean zza() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final boolean zzb() {
        return zzc.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmb
    public final boolean zzc() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.id.lifecycle.app_in_background_parameter", 0L);
        zzb = zzdbVar.zza("measurement.lifecycle.app_backgrounded_engagement", false);
        zzc = zzdbVar.zza("measurement.lifecycle.app_backgrounded_tracking", true);
        zzd = zzdbVar.zza("measurement.lifecycle.app_in_background_parameter", false);
        zze = zzdbVar.zza("measurement.id.lifecycle.app_backgrounded_tracking", 0L);
    }
}
