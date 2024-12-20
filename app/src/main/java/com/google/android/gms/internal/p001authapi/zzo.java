package com.google.android.gms.internal.p001authapi;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzo  reason: invalid package */
/* loaded from: classes.dex */
abstract class zzo<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzq> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzo(GoogleApiClient googleApiClient) {
        super(Auth.CREDENTIALS_API, googleApiClient);
    }

    protected abstract void zzc(Context context, zzx zzxVar) throws DeadObjectException, RemoteException;

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected /* synthetic */ void doExecute(zzq zzqVar) throws RemoteException {
        zzq zzqVar2 = zzqVar;
        zzc(zzqVar2.getContext(), (zzx) zzqVar2.getService());
    }
}
