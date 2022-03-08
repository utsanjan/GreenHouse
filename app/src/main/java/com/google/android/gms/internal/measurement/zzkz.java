package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkz implements zzdj<zzky> {
    private static zzkz zza = new zzkz();
    private final zzdj<zzky> zzb;

    public static boolean zzb() {
        return ((zzky) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzky) zza.zza()).zzb();
    }

    private zzkz(zzdj<zzky> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzkz() {
        this(zzdm.zza(new zzlb()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzky zza() {
        return this.zzb.zza();
    }
}
