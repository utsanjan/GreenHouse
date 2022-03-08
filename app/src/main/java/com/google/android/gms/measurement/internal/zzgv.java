package com.google.android.gms.measurement.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public abstract class zzgv extends zzgs {
    private boolean zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgv(zzfy zzfyVar) {
        super(zzfyVar);
        this.zzy.zza(this);
    }

    protected abstract boolean zze();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzz() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzaa() {
        if (!zzz()) {
            throw new IllegalStateException("Not initialized");
        }
    }

    public final void zzab() {
        if (this.zza) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zze()) {
            this.zzy.zzaf();
            this.zza = true;
        }
    }

    public final void zzac() {
        if (!this.zza) {
            f_();
            this.zzy.zzaf();
            this.zza = true;
            return;
        }
        throw new IllegalStateException("Can't initialize twice");
    }

    protected void f_() {
    }
}
