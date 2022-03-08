package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkc implements zzdj<zzkf> {
    private static zzkc zza = new zzkc();
    private final zzdj<zzkf> zzb;

    public static boolean zzb() {
        return ((zzkf) zza.zza()).zza();
    }

    private zzkc(zzdj<zzkf> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzkc() {
        this(zzdm.zza(new zzke()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzkf zza() {
        return this.zzb.zza();
    }
}
