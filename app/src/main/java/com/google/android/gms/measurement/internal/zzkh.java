package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public class zzkh extends zzgs implements zzgu {
    protected final zzkj zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkh(zzkj zzkjVar) {
        super(zzkjVar.zzs());
        Preconditions.checkNotNull(zzkjVar);
        this.zza = zzkjVar;
    }

    public zzfs zzj() {
        return this.zza.zzc();
    }

    public zzad zzi() {
        return this.zza.zze();
    }

    public zzo e_() {
        return this.zza.zzf();
    }

    public zzkn zzg() {
        return this.zza.zzh();
    }
}
