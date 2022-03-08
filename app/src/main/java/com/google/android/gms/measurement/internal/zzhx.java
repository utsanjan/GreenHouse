package com.google.android.gms.measurement.internal;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzhx implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzhc zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzhx(zzhc zzhcVar, boolean z) {
        this.zzb = zzhcVar;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zzab = this.zzb.zzy.zzab();
        boolean zzaa = this.zzb.zzy.zzaa();
        this.zzb.zzy.zza(this.zza);
        if (zzaa == this.zza) {
            this.zzb.zzy.zzr().zzx().zza("Default data collection state already set to", Boolean.valueOf(this.zza));
        }
        if (this.zzb.zzy.zzab() == zzab || this.zzb.zzy.zzab() != this.zzb.zzy.zzaa()) {
            this.zzb.zzy.zzr().zzk().zza("Default data collection is different than actual status", Boolean.valueOf(this.zza), Boolean.valueOf(zzab));
        }
        this.zzb.zzam();
    }
}
