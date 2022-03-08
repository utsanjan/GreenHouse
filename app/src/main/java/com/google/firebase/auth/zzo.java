package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.internal.zzad;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzo implements zzad {
    private final /* synthetic */ FirebaseUser zza;
    private final /* synthetic */ FirebaseAuth zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zzb = firebaseAuth;
        this.zza = firebaseUser;
    }

    @Override // com.google.firebase.auth.internal.zzad
    public final void zza() {
        FirebaseUser firebaseUser;
        firebaseUser = this.zzb.zzf;
        if (firebaseUser.getUid().equalsIgnoreCase(this.zza.getUid())) {
            this.zzb.zza();
        }
    }

    @Override // com.google.firebase.auth.internal.zzag
    public final void zza(Status status) {
        if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
            this.zzb.signOut();
        }
    }
}
