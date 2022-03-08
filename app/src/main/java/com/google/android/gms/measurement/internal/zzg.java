package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public abstract class zzg extends zzd {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzg(zzfy zzfyVar) {
        super(zzfyVar);
        this.zzy.zza(this);
    }

    protected abstract boolean zzz();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzv() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzw() {
        if (!zzv()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzx() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzz()) {
            this.zzy.zzaf();
            this.zza = true;
        }
    }

    public final void zzy() {
        if (!this.zza) {
            zzaa();
            this.zzy.zzaf();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    protected void zzaa() {
    }
}
