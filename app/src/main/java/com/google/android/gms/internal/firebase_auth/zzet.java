package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public enum zzet {
    REFRESH_TOKEN("refresh_token"),
    AUTHORIZATION_CODE("authorization_code");
    
    private final String zzc;

    zzet(String str) {
        this.zzc = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.zzc;
    }
}
