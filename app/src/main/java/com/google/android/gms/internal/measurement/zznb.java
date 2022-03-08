package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zznb implements zzdj<zzna> {
    private static zznb zza = new zznb();
    private final zzdj<zzna> zzb;

    public static boolean zzb() {
        return ((zzna) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzna) zza.zza()).zzb();
    }

    private zznb(zzdj<zzna> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zznb() {
        this(zzdm.zza(new zznd()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzna zza() {
        return this.zzb.zza();
    }
}
