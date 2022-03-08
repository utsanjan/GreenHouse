package com.google.android.gms.internal.p001authapi;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzw  reason: invalid package */
/* loaded from: classes.dex */
public final class zzw extends zzd implements zzx {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    @Override // com.google.android.gms.internal.p001authapi.zzx
    public final void zzc(zzv zzvVar, CredentialRequest credentialRequest) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzc(obtainAndWriteInterfaceToken, zzvVar);
        zzf.zzc(obtainAndWriteInterfaceToken, credentialRequest);
        transactAndReadExceptionReturnVoid(1, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.p001authapi.zzx
    public final void zzc(zzv zzvVar, zzz zzzVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzc(obtainAndWriteInterfaceToken, zzvVar);
        zzf.zzc(obtainAndWriteInterfaceToken, zzzVar);
        transactAndReadExceptionReturnVoid(2, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.p001authapi.zzx
    public final void zzc(zzv zzvVar, zzt zztVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzc(obtainAndWriteInterfaceToken, zzvVar);
        zzf.zzc(obtainAndWriteInterfaceToken, zztVar);
        transactAndReadExceptionReturnVoid(3, obtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.p001authapi.zzx
    public final void zzc(zzv zzvVar) throws RemoteException {
        Parcel obtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzc(obtainAndWriteInterfaceToken, zzvVar);
        transactAndReadExceptionReturnVoid(4, obtainAndWriteInterfaceToken);
    }
}
