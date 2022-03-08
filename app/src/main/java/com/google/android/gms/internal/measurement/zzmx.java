package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmx implements zzmu {
    private static final zzcv<Boolean> zza = new zzdb(zzcw.zza("com.google.android.gms.measurement")).zza("measurement.experiment.enable_experiment_reporting", true);

    @Override // com.google.android.gms.internal.measurement.zzmu
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}
