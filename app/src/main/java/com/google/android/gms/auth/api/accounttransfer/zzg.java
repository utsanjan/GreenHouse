package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;
import com.google.android.gms.internal.auth.zzv;
import com.google.android.gms.internal.auth.zzz;

/* loaded from: classes.dex */
final class zzg extends AccountTransferClient.zzb<DeviceMetaData> {
    private final /* synthetic */ zzv zzar;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzg(AccountTransferClient accountTransferClient, zzv zzvVar) {
        super(null);
        this.zzar = zzvVar;
    }

    @Override // com.google.android.gms.auth.api.accounttransfer.AccountTransferClient.zzb
    protected final void zza(zzz zzzVar) throws RemoteException {
        zzzVar.zza(new zzh(this, this), this.zzar);
    }
}
