package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkj implements zzkg {
    private static final zzcv<Boolean> zza = new zzdb(zzcw.zza("com.google.android.gms.measurement")).zza("measurement.client.firebase_feature_rollout.v1.enable", true);

    @Override // com.google.android.gms.internal.measurement.zzkg
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzkg
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }
}
