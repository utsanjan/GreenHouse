package com.google.firebase.installations;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
final /* synthetic */ class FirebaseInstallations$$Lambda$1 implements Runnable {
    private final FirebaseInstallations arg$1;

    private FirebaseInstallations$$Lambda$1(FirebaseInstallations firebaseInstallations) {
        this.arg$1 = firebaseInstallations;
    }

    public static Runnable lambdaFactory$(FirebaseInstallations firebaseInstallations) {
        return new FirebaseInstallations$$Lambda$1(firebaseInstallations);
    }

    @Override // java.lang.Runnable
    public void run() {
        FirebaseInstallations.access$lambda$0(this.arg$1);
    }
}
