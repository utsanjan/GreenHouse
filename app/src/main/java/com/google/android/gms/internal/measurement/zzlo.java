package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzlo implements zzlp {
    private static final zzcv<Boolean> zza = new zzdb(zzcw.zza("com.google.android.gms.measurement")).zza("measurement.ga.ga_app_id", false);

    @Override // com.google.android.gms.internal.measurement.zzlp
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzlp
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }
}
