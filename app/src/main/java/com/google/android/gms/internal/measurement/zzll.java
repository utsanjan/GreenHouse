package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzll implements zzdj<zzlk> {
    private static zzll zza = new zzll();
    private final zzdj<zzlk> zzb;

    public static boolean zzb() {
        return ((zzlk) zza.zza()).zza();
    }

    private zzll(zzdj<zzlk> zzdjVar) {
        this.zzb = zzdm.zza((zzdj) zzdjVar);
    }

    public zzll() {
        this(zzdm.zza(new zzln()));
    }

    @Override // com.google.android.gms.internal.measurement.zzdj
    public final /* synthetic */ zzlk zza() {
        return this.zzb.zza();
    }
}
