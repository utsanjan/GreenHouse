package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zznh implements zzdj<zzng> {
    private static zznh zza = new zznh();
    private final zzdj<zzng> zzb;

    public static boolean zzb() {
        return ((zzng) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzng) zza.zza()).zzb();
    }

    private zznh(zzdj<zzng> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zznh() {
        this(zzdm.zza(new zznj()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzng zza() {
        return this.zzb.zza();
    }
}
