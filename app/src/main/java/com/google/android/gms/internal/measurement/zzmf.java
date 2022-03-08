package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmf implements zzmc {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;

    @Override // com.google.android.gms.internal.measurement.zzmc
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzmc
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    @Override // com.google.android.gms.internal.measurement.zzmc
    public final boolean zzc() {
        return zzb.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.sdk.screen.manual_screen_view_logging", true);
        zzb = zzdbVar.zza("measurement.sdk.screen.disabling_automatic_reporting", true);
    }
}
