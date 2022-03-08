package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public class zzgs implements zzgu {
    protected final zzfy zzy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgs(zzfy zzfyVar) {
        Preconditions.checkNotNull(zzfyVar);
        this.zzy = zzfyVar;
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public zzx zzu() {
        return this.zzy.zzu();
    }

    public zzy zzt() {
        return this.zzy.zzb();
    }

    public zzfg zzs() {
        return this.zzy.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public zzeu zzr() {
        return this.zzy.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public zzfv zzq() {
        return this.zzy.zzq();
    }

    public zzkr zzp() {
        return this.zzy.zzi();
    }

    public zzes zzo() {
        return this.zzy.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public Context zzn() {
        return this.zzy.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzgu
    public Clock zzm() {
        return this.zzy.zzm();
    }

    public zzai zzl() {
        return this.zzy.zzx();
    }

    public void zzd() {
        this.zzy.zzq().zzd();
    }

    public void zzc() {
        this.zzy.zzq().zzc();
    }

    public void zzb() {
        this.zzy.zzad();
    }

    public void zza() {
        this.zzy.zzae();
    }
}
