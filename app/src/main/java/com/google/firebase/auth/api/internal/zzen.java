package com.google.firebase.auth.api.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zza;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzeh;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfm;
import com.google.firebase.auth.PhoneAuthCredential;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzen extends zza implements zzeo {
    public zzen() {
        super("com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    @Override // com.google.android.gms.internal.firebase_auth.zza
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zza((zzff) zzd.zza(parcel, zzff.CREATOR));
                return true;
            case 2:
                zza((zzff) zzd.zza(parcel, zzff.CREATOR), (zzew) zzd.zza(parcel, zzew.CREATOR));
                return true;
            case 3:
                zza((zzem) zzd.zza(parcel, zzem.CREATOR));
                return true;
            case 4:
                zza((zzfm) zzd.zza(parcel, zzfm.CREATOR));
                return true;
            case 5:
                zza((Status) zzd.zza(parcel, Status.CREATOR));
                return true;
            case 6:
                i_();
                return true;
            case 7:
                zzb();
                return true;
            case 8:
                zza(parcel.readString());
                return true;
            case 9:
                zzb(parcel.readString());
                return true;
            case 10:
                zza((PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 11:
                zzc(parcel.readString());
                return true;
            case 12:
                zza((Status) zzd.zza(parcel, Status.CREATOR), (PhoneAuthCredential) zzd.zza(parcel, PhoneAuthCredential.CREATOR));
                return true;
            case 13:
                zzc();
                return true;
            case 14:
                zza((zzeh) zzd.zza(parcel, zzeh.CREATOR));
                return true;
            case 15:
                zza((zzej) zzd.zza(parcel, zzej.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
