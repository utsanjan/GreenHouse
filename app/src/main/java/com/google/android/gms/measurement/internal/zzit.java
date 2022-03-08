package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzit implements Runnable {
    private final /* synthetic */ boolean zza;
    private final /* synthetic */ zzkq zzb;
    private final /* synthetic */ zzn zzc;
    private final /* synthetic */ zzir zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzit(zzir zzirVar, boolean z, zzkq zzkqVar, zzn zznVar) {
        this.zzd = zzirVar;
        this.zza = z;
        this.zzb = zzkqVar;
        this.zzc = zznVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzem zzemVar;
        zzemVar = this.zzd.zzb;
        if (zzemVar == null) {
            this.zzd.zzr().zzf().zza("Discarding data. Failed to set user property");
            return;
        }
        this.zzd.zza(zzemVar, this.zza ? null : this.zzb, this.zzc);
        this.zzd.zzak();
    }
}
