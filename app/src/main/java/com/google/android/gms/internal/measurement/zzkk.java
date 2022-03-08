package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkk implements zzkl {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Boolean> zzc;
    private static final zzcv<Boolean> zzd;

    @Override // com.google.android.gms.internal.measurement.zzkl
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzkl
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzkl
    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzkl
    public final boolean zzd() {
        return zzc.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzkl
    public final boolean zze() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.service.audience.fix_skip_audience_with_failed_filters", true);
        zzb = zzdbVar.zza("measurement.audience.refresh_event_count_filters_timestamp", false);
        zzc = zzdbVar.zza("measurement.audience.use_bundle_end_timestamp_for_non_sequence_property_filters", false);
        zzd = zzdbVar.zza("measurement.audience.use_bundle_timestamp_for_event_count_filters", false);
    }
}
