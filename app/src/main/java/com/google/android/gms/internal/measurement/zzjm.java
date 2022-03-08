package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjm implements zzjn {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Long> zzb;

    @Override // com.google.android.gms.internal.measurement.zzjn
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjn
    public final long zzb() {
        return zzb.zzc().longValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.sdk.attribution.cache", true);
        zzb = zzdbVar.zza("measurement.sdk.attribution.cache.ttl", 604800000L);
    }
}
