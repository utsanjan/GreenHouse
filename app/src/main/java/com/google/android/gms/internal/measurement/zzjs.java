package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjs implements zzjt {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Long> zzc;

    @Override // com.google.android.gms.internal.measurement.zzjt
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzjt
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjt
    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.service.configurable_service_limits", false);
        zzb = zzdbVar.zza("measurement.client.configurable_service_limits", false);
        zzc = zzdbVar.zza("measurement.id.service.configurable_service_limits", 0L);
    }
}
