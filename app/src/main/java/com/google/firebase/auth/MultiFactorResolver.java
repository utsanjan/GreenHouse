package com.google.firebase.auth;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class MultiFactorResolver extends AbstractSafeParcelable {
    public abstract FirebaseAuth getFirebaseAuth();

    public abstract List<MultiFactorInfo> getHints();

    public abstract MultiFactorSession getSession();

    public abstract Task<AuthResult> resolveSignIn(MultiFactorAssertion multiFactorAssertion);
}
