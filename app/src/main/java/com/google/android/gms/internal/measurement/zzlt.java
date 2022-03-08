package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzlt implements zzlq {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Boolean> zzc;

    @Override // com.google.android.gms.internal.measurement.zzlq
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzlq
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlq
    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzlq
    public final boolean zzd() {
        return zzc.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.client.global_params.dev", false);
        zzb = zzdbVar.zza("measurement.service.global_params_in_payload", true);
        zzc = zzdbVar.zza("measurement.service.global_params", false);
    }
}
