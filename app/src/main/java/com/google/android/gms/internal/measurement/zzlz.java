package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzlz implements zzlw {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Boolean> zzc;
    private static final zzcv<Boolean> zzd;
    private static final zzcv<Long> zze;

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final boolean zzb() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final boolean zzc() {
        return zzc.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlw
    public final boolean zzd() {
        return zzd.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.sdk.collection.enable_extend_user_property_size", true);
        zzb = zzdbVar.zza("measurement.sdk.collection.last_deep_link_referrer2", true);
        zzc = zzdbVar.zza("measurement.sdk.collection.last_deep_link_referrer_campaign2", false);
        zzd = zzdbVar.zza("measurement.sdk.collection.last_gclid_from_referrer2", false);
        zze = zzdbVar.zza("measurement.id.sdk.collection.last_deep_link_referrer2", 0L);
    }
}
