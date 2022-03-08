package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmg implements zzmh {
    private static final zzcv<Long> zza;
    private static final zzcv<Long> zzb;

    @Override // com.google.android.gms.internal.measurement.zzmh
    public final long zza() {
        return zzb.zzc().longValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.id.max_bundles_per_iteration", 0L);
        zzb = zzdbVar.zza("measurement.max_bundles_per_iteration", 2L);
    }
}
