package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmd implements zzdj<zzmc> {
    private static zzmd zza = new zzmd();
    private final zzdj<zzmc> zzb;

    public static boolean zzb() {
        return ((zzmc) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzmc) zza.zza()).zzb();
    }

    public static boolean zzd() {
        return ((zzmc) zza.zza()).zzc();
    }

    private zzmd(zzdj<zzmc> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzmd() {
        this(zzdm.zza(new zzmf()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmc zza() {
        return this.zzb.zza();
    }
}
