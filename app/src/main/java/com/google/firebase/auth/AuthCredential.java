package com.google.firebase.auth;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class AuthCredential extends AbstractSafeParcelable {
    public abstract String getProvider();

    public abstract String getSignInMethod();

    public abstract AuthCredential zza();
}
