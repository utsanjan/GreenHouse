package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.firebase_auth.zzb;
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
public final class zzeq extends zzb implements zzeo {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeq(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(zzff zzffVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzffVar);
        zzb(1, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(zzff zzffVar, zzew zzewVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzffVar);
        zzd.zza(zza, zzewVar);
        zzb(2, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(zzem zzemVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzemVar);
        zzb(3, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(zzfm zzfmVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzfmVar);
        zzb(4, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(Status status) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, status);
        zzb(5, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void i_() throws RemoteException {
        zzb(6, zza());
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zzb() throws RemoteException {
        zzb(7, zza());
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(8, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zzb(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(9, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, phoneAuthCredential);
        zzb(10, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zzc(String str) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzb(11, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, status);
        zzd.zza(zza, phoneAuthCredential);
        zzb(12, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zzc() throws RemoteException {
        zzb(13, zza());
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(zzeh zzehVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzehVar);
        zzb(14, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzeo
    public final void zza(zzej zzejVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzejVar);
        zzb(15, zza);
    }
}
