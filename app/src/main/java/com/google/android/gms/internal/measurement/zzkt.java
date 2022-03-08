package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkt implements zzdj<zzks> {
    private static zzkt zza = new zzkt();
    private final zzdj<zzks> zzb;

    public static boolean zzb() {
        return ((zzks) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzks) zza.zza()).zzb();
    }

    private zzkt(zzdj<zzks> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzkt() {
        this(zzdm.zza(new zzkv()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzks zza() {
        return this.zzb.zza();
    }
}
