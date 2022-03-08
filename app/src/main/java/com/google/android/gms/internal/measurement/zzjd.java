package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjd implements zzdj<zzjc> {
    private static zzjd zza = new zzjd();
    private final zzdj<zzjc> zzb;

    public static boolean zzb() {
        return ((zzjc) zza.zza()).zza();
    }

    private zzjd(zzdj<zzjc> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzjd() {
        this(zzdm.zza(new zzjf()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzjc zza() {
        return this.zzb.zza();
    }
}
