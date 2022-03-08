package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzjz implements Runnable {
    long zza;
    long zzb;
    final /* synthetic */ zzjw zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjz(zzjw zzjwVar, long j, long j2) {
        this.zzc = zzjwVar;
        this.zza = j;
        this.zzb = j2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzq().zza(new Runnable(this) { // from class: com.google.android.gms.measurement.internal.zzjy
            private final zzjz zza;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zza = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzjz zzjzVar = this.zza;
                zzjw zzjwVar = zzjzVar.zzc;
                long j = zzjzVar.zza;
                long j2 = zzjzVar.zzb;
                zzjwVar.zza.zzd();
                zzjwVar.zza.zzr().zzw().zza("Application going to the background");
                boolean z = true;
                if (zzjwVar.zza.zzt().zza(zzaq.zzcc)) {
                    zzjwVar.zza.zzs().zzr.zza(true);
                }
                Bundle bundle = new Bundle();
                if (!zzjwVar.zza.zzt().zzj().booleanValue()) {
                    zzjwVar.zza.zzb.zzb(j2);
                    if (zzjwVar.zza.zzt().zza(zzaq.zzbr)) {
                        bundle.putLong("_et", zzjwVar.zza.zza(j2));
                        zzii.zza(zzjwVar.zza.zzi().zza(true), bundle, true);
                    } else {
                        z = false;
                    }
                    zzjwVar.zza.zza(false, z, j2);
                }
                zzjwVar.zza.zzf().zza("auto", "_ab", j, bundle);
            }
        });
    }
}
