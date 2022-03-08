package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmp implements zzdj<zzmo> {
    private static zzmp zza = new zzmp();
    private final zzdj<zzmo> zzb;

    public static boolean zzb() {
        return ((zzmo) zza.zza()).zza();
    }

    public static double zzc() {
        return ((zzmo) zza.zza()).zzb();
    }

    public static long zzd() {
        return ((zzmo) zza.zza()).zzc();
    }

    public static long zze() {
        return ((zzmo) zza.zza()).zzd();
    }

    public static String zzf() {
        return ((zzmo) zza.zza()).zze();
    }

    private zzmp(zzdj<zzmo> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzmp() {
        this(zzdm.zza(new zzmr()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmo zza() {
        return this.zzb.zza();
    }
}
