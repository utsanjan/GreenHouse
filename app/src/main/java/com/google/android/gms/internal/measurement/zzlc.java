package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzlc implements zzld {
    private static final zzcv<Boolean> zza;
    private static final zzcv<Boolean> zzb;
    private static final zzcv<Boolean> zzc;

    @Override // com.google.android.gms.internal.measurement.zzld
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzld
    public final boolean zzb() {
        return zza.zzc().booleanValue();
    }

    static {
        zzdb zzdbVar = new zzdb(zzcw.zza("com.google.android.gms.measurement"));
        zza = zzdbVar.zza("measurement.client.sessions.check_on_reset_and_enable2", true);
        zzb = zzdbVar.zza("measurement.client.sessions.check_on_startup", true);
        zzc = zzdbVar.zza("measurement.client.sessions.start_session_before_view_screen", true);
    }
}
