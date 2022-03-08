package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjk implements zzdj<zzjn> {
    private static zzjk zza = new zzjk();
    private final zzdj<zzjn> zzb;

    public static boolean zzb() {
        return ((zzjn) zza.zza()).zza();
    }

    public static long zzc() {
        return ((zzjn) zza.zza()).zzb();
    }

    private zzjk(zzdj<zzjn> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzjk() {
        this(zzdm.zza(new zzjm()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzjn zza() {
        return this.zzb.zza();
    }
}
