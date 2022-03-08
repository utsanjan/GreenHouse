package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzje implements zzdj<zzjh> {
    private static zzje zza = new zzje();
    private final zzdj<zzjh> zzb;

    public static boolean zzb() {
        return ((zzjh) zza.zza()).zza();
    }

    private zzje(zzdj<zzjh> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzje() {
        this(zzdm.zza(new zzjg()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzjh zza() {
        return this.zzb.zza();
    }
}
