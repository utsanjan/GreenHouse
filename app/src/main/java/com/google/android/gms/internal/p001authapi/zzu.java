package com.google.android.gms.internal.p001authapi;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzu  reason: invalid package */
/* loaded from: classes.dex */
public abstract class zzu extends zzc implements zzv {
    public zzu() {
        super("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
    }

    @Override // com.google.android.gms.internal.p001authapi.zzc
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzc((Status) zzf.zzc(parcel, Status.CREATOR), (Credential) zzf.zzc(parcel, Credential.CREATOR));
        } else if (i == 2) {
            zzd((Status) zzf.zzc(parcel, Status.CREATOR));
        } else if (i != 3) {
            return false;
        } else {
            zzc((Status) zzf.zzc(parcel, Status.CREATOR), parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}
