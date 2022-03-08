package com.google.android.gms.internal.p001authapi;

import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzaj  reason: invalid package */
/* loaded from: classes.dex */
final class zzaj extends zzaa {
    private final /* synthetic */ TaskCompletionSource zzbk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzaj(zzaf zzafVar, TaskCompletionSource taskCompletionSource) {
        this.zzbk = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.p001authapi.zzab
    public final void zzc(Status status, BeginSignInResult beginSignInResult) throws RemoteException {
        TaskUtil.setResultOrApiException(status, beginSignInResult, this.zzbk);
    }
}
