package com.google.firebase.installations;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
final /* synthetic */ class FirebaseInstallations$$Lambda$2 implements Runnable {
    private final FirebaseInstallations arg$1;

    private FirebaseInstallations$$Lambda$2(FirebaseInstallations firebaseInstallations) {
        this.arg$1 = firebaseInstallations;
    }

    public static Runnable lambdaFactory$(FirebaseInstallations firebaseInstallations) {
        return new FirebaseInstallations$$Lambda$2(firebaseInstallations);
    }

    @Override // java.lang.Runnable
    public void run() {
        FirebaseInstallations.access$lambda$1(this.arg$1);
    }
}
