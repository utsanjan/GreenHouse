package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzat implements Continuation<ResultT, Task<ResultT>> {
    private final /* synthetic */ zzap zza;
    private final /* synthetic */ zzau zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzat(zzau zzauVar, zzap zzapVar) {
        this.zzb = zzauVar;
        this.zza = zzapVar;
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final /* synthetic */ Object then(Task task) throws Exception {
        if (task.getException() instanceof UnsupportedApiCallException) {
            return this.zzb.zza(this.zza.zzc());
        }
        return task;
    }
}
