package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkh implements zzdj<zzkg> {
    private static zzkh zza = new zzkh();
    private final zzdj<zzkg> zzb;

    public static boolean zzb() {
        return ((zzkg) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzkg) zza.zza()).zzb();
    }

    private zzkh(zzdj<zzkg> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzkh() {
        this(zzdm.zza(new zzkj()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzkg zza() {
        return this.zzb.zza();
    }
}
