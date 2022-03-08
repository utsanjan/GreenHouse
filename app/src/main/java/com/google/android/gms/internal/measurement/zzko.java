package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzko implements zzdj<zzkr> {
    private static zzko zza = new zzko();
    private final zzdj<zzkr> zzb;

    public static boolean zzb() {
        return ((zzkr) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkr) zza.zza()).zzb();
    }

    private zzko(zzdj<zzkr> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzko() {
        this(zzdm.zza(new zzkq()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzkr zza() {
        return this.zzb.zza();
    }
}
