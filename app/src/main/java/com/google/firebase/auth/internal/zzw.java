package com.google.firebase.auth.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.zzc;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzw implements Continuation<AuthResult, Task<AuthResult>> {
    private final /* synthetic */ zzt zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(zzt zztVar) {
        this.zza = zztVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Task<AuthResult> then(Task<AuthResult> task) throws Exception {
        zzc zzcVar;
        zzc zzcVar2;
        zzc zzcVar3;
        zzcVar = this.zza.zzd;
        if (zzcVar == null) {
            return task;
        }
        if (task.isSuccessful()) {
            AuthResult result = task.getResult();
            zzcVar3 = this.zza.zzd;
            return Tasks.forResult(new zzh((zzn) result.getUser(), (zzf) result.getAdditionalUserInfo(), zzcVar3));
        }
        Exception exception = task.getException();
        if (exception instanceof FirebaseAuthUserCollisionException) {
            zzcVar2 = this.zza.zzd;
            ((FirebaseAuthUserCollisionException) exception).zza(zzcVar2);
        }
        return Tasks.forException(exception);
    }
}
