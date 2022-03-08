package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmq implements zzdj<zzmt> {
    private static zzmq zza = new zzmq();
    private final zzdj<zzmt> zzb;

    public static boolean zzb() {
        return ((zzmt) zza.zza()).zza();
    }

    private zzmq(zzdj<zzmt> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzmq() {
        this(zzdm.zza(new zzms()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmt zza() {
        return this.zzb.zza();
    }
}
