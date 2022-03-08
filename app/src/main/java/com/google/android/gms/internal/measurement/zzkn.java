package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkn implements zzdj<zzkm> {
    private static zzkn zza = new zzkn();
    private final zzdj<zzkm> zzb;

    public static boolean zzb() {
        return ((zzkm) zza.zza()).zza();
    }

    private zzkn(zzdj<zzkm> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzkn() {
        this(zzdm.zza(new zzkp()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzkm zza() {
        return this.zzb.zza();
    }
}
