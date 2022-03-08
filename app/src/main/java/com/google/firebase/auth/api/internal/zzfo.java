package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfo<ResultT, CallbackT> implements zzff<ResultT> {
    private final zzfe<ResultT, CallbackT> zza;
    private final TaskCompletionSource<ResultT> zzb;

    public zzfo(zzfe<ResultT, CallbackT> zzfeVar, TaskCompletionSource<ResultT> taskCompletionSource) {
        this.zza = zzfeVar;
        this.zzb = taskCompletionSource;
    }

    @Override // com.google.firebase.auth.api.internal.zzff
    public final void zza(ResultT resultt, Status status) {
        FirebaseUser firebaseUser;
        Preconditions.checkNotNull(this.zzb, "completion source cannot be null");
        if (status == null) {
            this.zzb.setResult(resultt);
        } else if (this.zza.zzt != null) {
            TaskCompletionSource<ResultT> taskCompletionSource = this.zzb;
            FirebaseAuth instance = FirebaseAuth.getInstance(this.zza.zzd);
            zzej zzejVar = this.zza.zzt;
            if ("reauthenticateWithCredential".equals(this.zza.zza()) || "reauthenticateWithCredentialWithData".equals(this.zza.zza())) {
                firebaseUser = this.zza.zze;
            } else {
                firebaseUser = null;
            }
            taskCompletionSource.setException(zzeh.zza(instance, zzejVar, firebaseUser));
        } else if (this.zza.zzq != null) {
            this.zzb.setException(zzeh.zza(status, this.zza.zzq, this.zza.zzr, this.zza.zzs));
        } else {
            this.zzb.setException(zzeh.zza(status));
        }
    }
}
