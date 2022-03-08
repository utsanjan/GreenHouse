package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzkt;
import com.google.android.gms.internal.measurement.zzla;
import com.google.android.gms.internal.measurement.zzlf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkb {
    private long zza;
    private long zzb;
    private final zzag zzc;
    private final /* synthetic */ zzjv zzd;

    public zzkb(zzjv zzjvVar) {
        this.zzd = zzjvVar;
        this.zzc = new zzka(this, this.zzd.zzy);
        long elapsedRealtime = zzjvVar.zzm().elapsedRealtime();
        this.zza = elapsedRealtime;
        this.zzb = elapsedRealtime;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza(long j) {
        this.zzd.zzd();
        this.zzc.zzc();
        this.zza = j;
        this.zzb = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzb(long j) {
        this.zzc.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zza() {
        this.zzc.zzc();
        this.zza = 0L;
        this.zzb = 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc() {
        this.zzd.zzd();
        zza(false, false, this.zzd.zzm().elapsedRealtime());
        this.zzd.zze().zza(this.zzd.zzm().elapsedRealtime());
    }

    public final boolean zza(boolean z, boolean z2, long j) {
        this.zzd.zzd();
        this.zzd.zzw();
        if (!zzkt.zzb() || !this.zzd.zzt().zza(zzaq.zzbz)) {
            j = this.zzd.zzm().elapsedRealtime();
        }
        if (!zzla.zzb() || !this.zzd.zzt().zza(zzaq.zzbv) || this.zzd.zzy.zzab()) {
            this.zzd.zzs().zzp.zza(this.zzd.zzm().currentTimeMillis());
        }
        long j2 = j - this.zza;
        if (z || j2 >= 1000) {
            if (this.zzd.zzt().zza(zzaq.zzat) && !z2) {
                j2 = (!zzlf.zzb() || !this.zzd.zzt().zza(zzaq.zzav) || !zzkt.zzb() || !this.zzd.zzt().zza(zzaq.zzbz)) ? zzb() : zzc(j);
            }
            this.zzd.zzr().zzx().zza("Recording user engagement, ms", Long.valueOf(j2));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j2);
            zzii.zza(this.zzd.zzi().zza(!this.zzd.zzt().zzj().booleanValue()), bundle, true);
            if (this.zzd.zzt().zza(zzaq.zzat) && !this.zzd.zzt().zza(zzaq.zzau) && z2) {
                bundle.putLong("_fr", 1L);
            }
            if (!this.zzd.zzt().zza(zzaq.zzau) || !z2) {
                this.zzd.zzf().zza("auto", "_e", bundle);
            }
            this.zza = j;
            this.zzc.zzc();
            this.zzc.zza(3600000L);
            return true;
        }
        this.zzd.zzr().zzx().zza("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j2));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zzb() {
        long elapsedRealtime = this.zzd.zzm().elapsedRealtime();
        long j = elapsedRealtime - this.zzb;
        this.zzb = elapsedRealtime;
        return j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long zzc(long j) {
        long j2 = j - this.zzb;
        this.zzb = j;
        return j2;
    }
}
