package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.MultiFactorSession;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzu implements Continuation<GetTokenResult, Task<MultiFactorSession>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzu(zzr zzrVar) {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<MultiFactorSession> then(Task<GetTokenResult> task) throws Exception {
        if (!task.isSuccessful()) {
            return Tasks.forException(task.getException());
        }
        return Tasks.forResult(zzy.zza(task.getResult().getToken()));
    }
}
