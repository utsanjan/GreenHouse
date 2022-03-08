package com.google.firebase.auth.api.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.firebase_auth.zzb;
import com.google.android.gms.internal.firebase_auth.zzbw;
import com.google.android.gms.internal.firebase_auth.zzbx;
import com.google.android.gms.internal.firebase_auth.zzbz;
import com.google.android.gms.internal.firebase_auth.zzcb;
import com.google.android.gms.internal.firebase_auth.zzcd;
import com.google.android.gms.internal.firebase_auth.zzcf;
import com.google.android.gms.internal.firebase_auth.zzch;
import com.google.android.gms.internal.firebase_auth.zzcj;
import com.google.android.gms.internal.firebase_auth.zzcl;
import com.google.android.gms.internal.firebase_auth.zzcn;
import com.google.android.gms.internal.firebase_auth.zzcp;
import com.google.android.gms.internal.firebase_auth.zzcr;
import com.google.android.gms.internal.firebase_auth.zzct;
import com.google.android.gms.internal.firebase_auth.zzcv;
import com.google.android.gms.internal.firebase_auth.zzcx;
import com.google.android.gms.internal.firebase_auth.zzcz;
import com.google.android.gms.internal.firebase_auth.zzd;
import com.google.android.gms.internal.firebase_auth.zzdb;
import com.google.android.gms.internal.firebase_auth.zzdd;
import com.google.android.gms.internal.firebase_auth.zzdf;
import com.google.android.gms.internal.firebase_auth.zzdh;
import com.google.android.gms.internal.firebase_auth.zzdj;
import com.google.android.gms.internal.firebase_auth.zzdl;
import com.google.android.gms.internal.firebase_auth.zzdn;
import com.google.android.gms.internal.firebase_auth.zzdp;
import com.google.android.gms.internal.firebase_auth.zzdr;
import com.google.android.gms.internal.firebase_auth.zzdt;
import com.google.android.gms.internal.firebase_auth.zzdv;
import com.google.android.gms.internal.firebase_auth.zzdx;
import com.google.android.gms.internal.firebase_auth.zzdz;
import com.google.android.gms.internal.firebase_auth.zzeb;
import com.google.android.gms.internal.firebase_auth.zzed;
import com.google.android.gms.internal.firebase_auth.zzef;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.internal.firebase_auth.zzfy;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzer extends zzb implements zzep {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzer(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(1, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzb(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(2, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzfy zzfyVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzfyVar);
        zzd.zza(zza, zzeoVar);
        zza(3, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, userProfileChangeRequest);
        zzd.zza(zza, zzeoVar);
        zza(4, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(String str, String str2, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, zzeoVar);
        zza(5, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzb(String str, String str2, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, zzeoVar);
        zza(6, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzc(String str, String str2, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, zzeoVar);
        zza(7, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzd(String str, String str2, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, zzeoVar);
        zza(8, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzc(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(9, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzd(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(10, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(String str, String str2, String str3, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zza.writeString(str3);
        zzd.zza(zza, zzeoVar);
        zza(11, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(String str, zzfy zzfyVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzfyVar);
        zzd.zza(zza, zzeoVar);
        zza(12, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zze(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(13, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zze(String str, String str2, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, zzeoVar);
        zza(14, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzf(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(15, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzeoVar);
        zza(16, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzg(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(17, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzh(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(18, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzi(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(19, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzj(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(20, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzf(String str, String str2, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zza.writeString(str2);
        zzd.zza(zza, zzeoVar);
        zza(21, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzfr zzfrVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzfrVar);
        zzd.zza(zza, zzeoVar);
        zza(22, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(PhoneAuthCredential phoneAuthCredential, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, phoneAuthCredential);
        zzd.zza(zza, zzeoVar);
        zza(23, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(String str, PhoneAuthCredential phoneAuthCredential, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, phoneAuthCredential);
        zzd.zza(zza, zzeoVar);
        zza(24, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(String str, ActionCodeSettings actionCodeSettings, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, actionCodeSettings);
        zzd.zza(zza, zzeoVar);
        zza(25, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzb(String str, ActionCodeSettings actionCodeSettings, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, actionCodeSettings);
        zzd.zza(zza, zzeoVar);
        zza(26, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzk(String str, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, zzeoVar);
        zza(27, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zzc(String str, ActionCodeSettings actionCodeSettings, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zza.writeString(str);
        zzd.zza(zza, actionCodeSettings);
        zzd.zza(zza, zzeoVar);
        zza(28, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(EmailAuthCredential emailAuthCredential, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, emailAuthCredential);
        zzd.zza(zza, zzeoVar);
        zza(29, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcn zzcnVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcnVar);
        zzd.zza(zza, zzeoVar);
        zza(101, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdl zzdlVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdlVar);
        zzd.zza(zza, zzeoVar);
        zza(102, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdj zzdjVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdjVar);
        zzd.zza(zza, zzeoVar);
        zza(103, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzed zzedVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzedVar);
        zzd.zza(zza, zzeoVar);
        zza(104, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzbx zzbxVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbxVar);
        zzd.zza(zza, zzeoVar);
        zza(105, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzbz zzbzVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbzVar);
        zzd.zza(zza, zzeoVar);
        zza(106, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcf zzcfVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcfVar);
        zzd.zza(zza, zzeoVar);
        zza(107, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdn zzdnVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdnVar);
        zzd.zza(zza, zzeoVar);
        zza(108, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcp zzcpVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcpVar);
        zzd.zza(zza, zzeoVar);
        zza(109, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcr zzcrVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcrVar);
        zzd.zza(zza, zzeoVar);
        zza(111, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzct zzctVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzctVar);
        zzd.zza(zza, zzeoVar);
        zza(112, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdz zzdzVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdzVar);
        zzd.zza(zza, zzeoVar);
        zza(113, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzeb zzebVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzebVar);
        zzd.zza(zza, zzeoVar);
        zza(114, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcx zzcxVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcxVar);
        zzd.zza(zza, zzeoVar);
        zza(115, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdh zzdhVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdhVar);
        zzd.zza(zza, zzeoVar);
        zza(116, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzch zzchVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzchVar);
        zzd.zza(zza, zzeoVar);
        zza(117, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcb zzcbVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcbVar);
        zzd.zza(zza, zzeoVar);
        zza(119, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzbw zzbwVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzbwVar);
        zzd.zza(zza, zzeoVar);
        zza(120, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcd zzcdVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcdVar);
        zzd.zza(zza, zzeoVar);
        zza(121, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdd zzddVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzddVar);
        zzd.zza(zza, zzeoVar);
        zza(122, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdr zzdrVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdrVar);
        zzd.zza(zza, zzeoVar);
        zza(123, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcv zzcvVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcvVar);
        zzd.zza(zza, zzeoVar);
        zza(124, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcz zzczVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzczVar);
        zzd.zza(zza, zzeoVar);
        zza(126, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdf zzdfVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdfVar);
        zzd.zza(zza, zzeoVar);
        zza(127, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdb zzdbVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdbVar);
        zzd.zza(zza, zzeoVar);
        zza(128, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdp zzdpVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdpVar);
        zzd.zza(zza, zzeoVar);
        zza(129, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdt zzdtVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdtVar);
        zzd.zza(zza, zzeoVar);
        zza(130, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdx zzdxVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdxVar);
        zzd.zza(zza, zzeoVar);
        zza(131, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcj zzcjVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzcjVar);
        zzd.zza(zza, zzeoVar);
        zza(132, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzdv zzdvVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzdvVar);
        zzd.zza(zza, zzeoVar);
        zza(133, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzcl zzclVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzclVar);
        zzd.zza(zza, zzeoVar);
        zza(134, zza);
    }

    @Override // com.google.firebase.auth.api.internal.zzep
    public final void zza(zzef zzefVar, zzeo zzeoVar) throws RemoteException {
        Parcel zza = zza();
        zzd.zza(zza, zzefVar);
        zzd.zza(zza, zzeoVar);
        zza(135, zza);
    }
}
