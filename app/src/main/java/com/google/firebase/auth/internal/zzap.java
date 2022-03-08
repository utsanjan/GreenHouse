package com.google.firebase.auth.internal;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzap implements Runnable {
    private final /* synthetic */ FederatedSignInActivity zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzap(FederatedSignInActivity federatedSignInActivity) {
        this.zza = federatedSignInActivity;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza();
        Runnable unused = FederatedSignInActivity.zze = null;
    }
}
