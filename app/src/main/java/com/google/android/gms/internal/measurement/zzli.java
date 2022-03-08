package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzli implements zzlj {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Long> zzc;

    @Override // com.google.android.gms.internal.measurement.zzlj
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlj
    public final boolean zzb() {
        return zzb.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.collection.efficient_engagement_reporting_enabled_2", true);
        zzb = zzdbVar.zza("measurement.collection.redundant_engagement_removal_enabled", false);
        zzc = zzdbVar.zza("measurement.id.collection.redundant_engagement_removal_enabled", 0L);
    }
}
