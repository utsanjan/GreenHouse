package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzla implements zzdj<zzld> {
    private static zzla zza = new zzla();
    private final zzdj<zzld> zzb;

    public static boolean zzb() {
        return ((zzld) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzld) zza.zza()).zzb();
    }

    private zzla(zzdj<zzld> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzla() {
        this(zzdm.zza(new zzlc()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzld zza() {
        return this.zzb.zza();
    }
}
