package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmk implements zzdj<zzmn> {
    private static zzmk zza = new zzmk();
    private final zzdj<zzmn> zzb;

    public static boolean zzb() {
        return ((zzmn) zza.zza()).zza();
    }

    private zzmk(zzdj<zzmn> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzmk() {
        this(zzdm.zza(new zzmm()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmn zza() {
        return this.zzb.zza();
    }
}
