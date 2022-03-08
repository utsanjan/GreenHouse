package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzmj implements zzdj<zzmi> {
    private static zzmj zza = new zzmj();
    private final zzdj<zzmi> zzb;

    public static boolean zzb() {
        return ((zzmi) zza.zza()).zza();
    }

    private zzmj(zzdj<zzmi> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzmj() {
        this(zzdm.zza(new zzml()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzmi zza() {
        return this.zzb.zza();
    }
}
