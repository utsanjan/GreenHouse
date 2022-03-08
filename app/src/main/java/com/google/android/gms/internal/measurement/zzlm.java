package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzlm implements zzdj<zzlp> {
    private static zzlm zza = new zzlm();
    private final zzdj<zzlp> zzb;

    public static boolean zzb() {
        return ((zzlp) zza.zza()).zza();
    }

    public static boolean zzc() {
        return ((zzlp) zza.zza()).zzb();
    }

    private zzlm(zzdj<zzlp> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzlm() {
        this(zzdm.zza(new zzlo()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzlp zza() {
        return this.zzb.zza();
    }
}
