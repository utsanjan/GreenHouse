package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjg implements zzjh {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;

    @Override // com.google.android.gms.internal.measurement.zzjh
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.androidId.delete_feature", true);
        zzb = zzdbVar.zza("measurement.log_androidId_enabled", false);
    }
}
