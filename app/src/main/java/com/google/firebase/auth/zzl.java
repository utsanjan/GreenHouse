package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzl implements Runnable {
    private final /* synthetic */ FirebaseAuth zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<FirebaseAuth.AuthStateListener> list;
        list = this.zza.zzd;
        for (FirebaseAuth.AuthStateListener authStateListener : list) {
            authStateListener.onAuthStateChanged(this.zza);
        }
    }
}
