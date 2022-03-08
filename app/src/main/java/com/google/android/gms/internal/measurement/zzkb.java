package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkb implements zzdj<zzka> {
    private static zzkb zza = new zzkb();
    private final zzdj<zzka> zzb;

    public static boolean zzb() {
        return ((zzka) zza.zza()).zza();
    }

    private zzkb(zzdj<zzka> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzkb() {
        this(zzdm.zza(new zzkd()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzka zza() {
        return this.zzb.zza();
    }
}
