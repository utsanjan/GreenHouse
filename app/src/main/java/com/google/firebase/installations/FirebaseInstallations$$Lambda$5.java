package com.google.firebase.installations;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
final /* synthetic */ class FirebaseInstallations$$Lambda$5 implements Runnable {
    private final FirebaseInstallations arg$1;
    private final boolean arg$2;

    private FirebaseInstallations$$Lambda$5(FirebaseInstallations firebaseInstallations, boolean z) {
        this.arg$1 = firebaseInstallations;
        this.arg$2 = z;
    }

    public static Runnable lambdaFactory$(FirebaseInstallations firebaseInstallations, boolean z) {
        return new FirebaseInstallations$$Lambda$5(firebaseInstallations, z);
    }

    @Override // java.lang.Runnable
    public void run() {
        FirebaseInstallations.lambda$doRegistrationInternal$0(this.arg$1, this.arg$2);
    }
}
