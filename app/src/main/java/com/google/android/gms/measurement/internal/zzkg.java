package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public abstract class zzkg extends zzkh {
    private boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkg(zzkj zzkjVar) {
        super(zzkjVar);
        this.zza.zza(this);
    }

    protected abstract boolean zze();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzaj() {
        return this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzak() {
        if (!zzaj()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzal() {
        if (!this.zzb) {
            zze();
            this.zza.zzp();
            this.zzb = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }
}
