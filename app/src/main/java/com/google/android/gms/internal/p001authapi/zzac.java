package com.google.android.gms.internal.p001authapi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.common.api.internal.IStatusCallback;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzac  reason: invalid package */
/* loaded from: classes.dex */
public final class zzac extends zzd implements zzad {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzac(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.identity.internal.ISignInService");
    }

    @Override // com.google.android.gms.internal.p001authapi.zzad
    public final void zzc(zzab zzabVar, BeginSignInRequest beginSignInRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzc(obtainAndWriteInterfaceToken, zzabVar);
        zzf.zzc(obtainAndWriteInterfaceToken, beginSignInRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.p001authapi.zzad
    public final void zzc(IStatusCallback iStatusCallback, String str) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzc(obtainAndWriteInterfaceToken, iStatusCallback);
        obtainAndWriteInterfaceToken.writeString(str);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }
}
