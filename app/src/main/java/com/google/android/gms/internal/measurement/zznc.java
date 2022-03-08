package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zznc implements zzdj<zznf> {
    private static zznc zza = new zznc();
    private final zzdj<zznf> zzb;

    public static boolean zzb() {
        return ((zznf) zza.zza()).zza();
    }

    private zznc(zzdj<zznf> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zznc() {
        this(zzdm.zza(new zzne()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zznf zza() {
        return this.zzb.zza();
    }
}
