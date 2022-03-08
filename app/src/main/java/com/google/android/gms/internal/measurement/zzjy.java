package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjy implements zzjz {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Boolean> zzc;
    private static final zzcv<Boolean> zzd;
    private static final zzcv<Boolean> zze;
    private static final zzcv<Boolean> zzf;

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final boolean zzd() {
        return zzc.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final boolean zze() {
        return zzd.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final boolean zzf() {
        return zze.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final boolean zzg() {
        return zzf.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.gold.enhanced_ecommerce.format_logs", true);
        zzb = zzdbVar.zza("measurement.gold.enhanced_ecommerce.log_nested_complex_events", true);
        zzc = zzdbVar.zza("measurement.gold.enhanced_ecommerce.nested_param_daily_event_count", true);
        zzd = zzdbVar.zza("measurement.gold.enhanced_ecommerce.updated_schema.client", true);
        zze = zzdbVar.zza("measurement.gold.enhanced_ecommerce.updated_schema.service", true);
        zzf = zzdbVar.zza("measurement.gold.enhanced_ecommerce.upload_nested_complex_events", true);
    }
}
