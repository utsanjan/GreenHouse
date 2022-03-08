package com.google.firebase.auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class FirebaseAuthUserCollisionException extends FirebaseAuthException {
    private AuthCredential zza;
    private String zzb;
    private String zzc;

    public FirebaseAuthUserCollisionException(String str, String str2) {
        super(str, str2);
    }

    public final FirebaseAuthUserCollisionException zza(String str) {
        this.zzb = str;
        return this;
    }

    public final FirebaseAuthUserCollisionException zza(AuthCredential authCredential) {
        this.zza = authCredential;
        return this;
    }

    public final FirebaseAuthUserCollisionException zzb(String str) {
        this.zzc = str;
        return this;
    }

    public final AuthCredential getUpdatedCredential() {
        return this.zza;
    }

    public final String getEmail() {
        return this.zzb;
    }
}
