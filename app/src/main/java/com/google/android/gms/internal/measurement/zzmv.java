package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmv implements zzdj<zzmu> {
    private static zzmv zza = new zzmv();
    private final zzdj<zzmu> zzb;

    public static boolean zzb() {
        return ((zzmu) zza.zza()).zza();
    }

    private zzmv(zzdj<zzmu> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzmv() {
        this(zzdm.zza(new zzmx()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmu zza() {
        return this.zzb.zza();
    }
}
