package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzno implements zzdj<zznr> {
    private static zzno zza = new zzno();
    private final zzdj<zznr> zzb;

    public static boolean zzb() {
        return ((zznr) zza.zza()).zza();
    }

    private zzno(zzdj<zznr> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzno() {
        this(zzdm.zza(new zznq()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zznr zza() {
        return this.zzb.zza();
    }
}
