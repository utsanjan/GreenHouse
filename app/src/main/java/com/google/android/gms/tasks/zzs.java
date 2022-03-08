package com.google.android.gms.tasks;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzs implements OnTokenCanceledListener {
    private final /* synthetic */ TaskCompletionSource zzv;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzs(TaskCompletionSource taskCompletionSource) {
        this.zzv = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnTokenCanceledListener
    public final void onCanceled() {
        zzu zzuVar;
        zzuVar = this.zzv.zza;
        zzuVar.zza();
    }
}
