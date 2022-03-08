package com.google.android.gms.internal.p001authapi;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzaa  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzaa extends zzc implements zzab {
    public zzaa() {
        super("com.google.android.gms.auth.api.identity.internal.IBeginSignInCallback");
    }

    @Override // com.google.android.gms.internal.p001authapi.zzc
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zzc((Status) zzf.zzc(parcel, Status.CREATOR), (BeginSignInResult) zzf.zzc(parcel, BeginSignInResult.CREATOR));
        return true;
    }
}
