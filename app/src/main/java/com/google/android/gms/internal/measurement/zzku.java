package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzku implements zzdj<zzkx> {
    private static zzku zza = new zzku();
    private final zzdj<zzkx> zzb;

    public static boolean zzb() {
        return ((zzkx) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkx) zza.zza()).zzb();
    }

    private zzku(zzdj<zzkx> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzku() {
        this(zzdm.zza(new zzkw()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzkx zza() {
        return this.zzb.zza();
    }
}
