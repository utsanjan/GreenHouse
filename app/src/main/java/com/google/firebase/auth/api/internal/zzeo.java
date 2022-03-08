package com.google.firebase.auth.api.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzeh;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.internal.firebase_auth.zzem;
import com.google.android.gms.internal.firebase_auth.zzew;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.gms.internal.firebase_auth.zzfm;
import com.google.firebase.auth.PhoneAuthCredential;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public interface zzeo extends IInterface {
    void i_() throws RemoteException;

    void zza(Status status) throws RemoteException;

    void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zza(zzeh zzehVar) throws RemoteException;

    void zza(zzej zzejVar) throws RemoteException;

    void zza(zzem zzemVar) throws RemoteException;

    void zza(zzff zzffVar) throws RemoteException;

    void zza(zzff zzffVar, zzew zzewVar) throws RemoteException;

    void zza(zzfm zzfmVar) throws RemoteException;

    void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zza(String str) throws RemoteException;

    void zzb() throws RemoteException;

    void zzb(String str) throws RemoteException;

    void zzc() throws RemoteException;

    void zzc(String str) throws RemoteException;
}
