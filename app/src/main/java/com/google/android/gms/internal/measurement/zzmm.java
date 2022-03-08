package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmm implements zzmn {
    private static final zzcv<Boolean> zza = new zzdb(zzcw.zza("com.google.android.gms.measurement")).zza("measurement.config.string.always_update_disk_on_set", true);

    @Override // com.google.android.gms.internal.measurement.zzmn
    public final boolean zza() {
        return zza.zzc().booleanValue();
    }
}
