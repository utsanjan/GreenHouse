package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmw implements zzdj<zzmz> {
    private static zzmw zza = new zzmw();
    private final zzdj<zzmz> zzb;

    public static boolean zzb() {
        return ((zzmz) zza.zza()).zza();
    }

    private zzmw(zzdj<zzmz> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzmw() {
        this(zzdm.zza(new zzmy()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmz zza() {
        return this.zzb.zza();
    }
}
