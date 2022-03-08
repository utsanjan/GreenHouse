package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzme implements zzdj<zzmh> {
    private static zzme zza = new zzme();
    private final zzdj<zzmh> zzb;

    public static long zzb() {
        return ((zzmh) zza.zza()).zza();
    }

    private zzme(zzdj<zzmh> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzme() {
        this(zzdm.zza(new zzmg()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmh zza() {
        return this.zzb.zza();
    }
}
