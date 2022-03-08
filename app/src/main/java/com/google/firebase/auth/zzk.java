package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.internal.zza;
import com.google.firebase.auth.internal.zzag;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzk implements zza, zzag {
    private final /* synthetic */ FirebaseAuth zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(FirebaseAuth firebaseAuth) {
        this.zza = firebaseAuth;
    }

    @Override // com.google.firebase.auth.internal.zza
    public final void zza(zzff zzffVar, FirebaseUser firebaseUser) {
        this.zza.zza(firebaseUser, zzffVar, true, true);
    }

    @Override // com.google.firebase.auth.internal.zzag
    public final void zza(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zza.signOut();
        }
    }
}
