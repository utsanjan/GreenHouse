package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjx implements zzju {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Boolean> zzc;
    private static final zzcv<Long> zzd;

    @Override // com.google.android.gms.internal.measurement.zzju
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.sdk.dynamite.allow_remote_dynamite2", false);
        zzb = zzdbVar.zza("measurement.collection.init_params_control_enabled", true);
        zzc = zzdbVar.zza("measurement.sdk.dynamite.use_dynamite3", true);
        zzd = zzdbVar.zza("measurement.id.sdk.dynamite.use_dynamite", 0L);
    }
}
