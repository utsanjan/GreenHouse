package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzls implements zzdj<zzlv> {
    private static zzls zza = new zzls();
    private final zzdj<zzlv> zzb;

    public static boolean zzb() {
        return ((zzlv) zza.zza()).zza();
    }

    private zzls(zzdj<zzlv> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzls() {
        this(zzdm.zza(new zzlu()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzlv zza() {
        return this.zzb.zza();
    }
}
