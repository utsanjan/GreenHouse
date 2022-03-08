package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkq implements zzkr {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Long> zzb;

    @Override // com.google.android.gms.internal.measurement.zzkr
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzkr
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.sdk.referrer.delayed_install_referrer_api", false);
        zzb = zzdbVar.zza("measurement.id.sdk.referrer.delayed_install_referrer_api", 0L);
    }
}
