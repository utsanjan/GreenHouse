package com.google.firebase.auth;

import com.google.android.gms.tasks.Task;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class MultiFactor {
    public abstract Task<Void> enroll(MultiFactorAssertion multiFactorAssertion, String str);

    public abstract List<MultiFactorInfo> getEnrolledFactors();

    public abstract Task<MultiFactorSession> getSession();

    public abstract Task<Void> unenroll(MultiFactorInfo multiFactorInfo);

    public abstract Task<Void> unenroll(String str);
}
