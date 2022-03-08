package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.internal.IdTokenListener;
import com.google.firebase.internal.InternalTokenResult;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzi implements Runnable {
    private final /* synthetic */ InternalTokenResult zza;
    private final /* synthetic */ FirebaseAuth zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzi(FirebaseAuth firebaseAuth, InternalTokenResult internalTokenResult) {
        this.zzb = firebaseAuth;
        this.zza = internalTokenResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List<IdTokenListener> list;
        List<FirebaseAuth.IdTokenListener> list2;
        list = this.zzb.zzc;
        for (IdTokenListener idTokenListener : list) {
            idTokenListener.onIdTokenChanged(this.zza);
        }
        list2 = this.zzb.zzb;
        for (FirebaseAuth.IdTokenListener idTokenListener2 : list2) {
            idTokenListener2.onIdTokenChanged(this.zzb);
        }
    }
}
