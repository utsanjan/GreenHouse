package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmr implements zzmo {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Double> zzb;
    private static final zzcv<Long> zzc;
    private static final zzcv<Long> zzd;
    private static final zzcv<String> zze;

    @Override // com.google.android.gms.internal.measurement.zzmo
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    public final double zzb() {
        return zzb.zzc().doubleValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    public final long zzc() {
        return zzc.zzc().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    public final long zzd() {
        return zzd.zzc().longValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmo
    public final String zze() {
        return zze.zzc();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.test.boolean_flag", false);
        zzb = zzdbVar.zza("measurement.test.double_flag", -3.0d);
        zzc = zzdbVar.zza("measurement.test.int_flag", -2L);
        zzd = zzdbVar.zza("measurement.test.long_flag", -1L);
        zze = zzdbVar.zza("measurement.test.string_flag", "---");
    }
}
