package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.measurement.zza;
import com.google.android.gms.internal.measurement.zzb;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzeo extends zza implements zzem {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(zzao zzaoVar, zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zzaoVar);
        zzb.zza(a_, zznVar);
        zzb(1, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(zzkq zzkqVar, zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zzkqVar);
        zzb.zza(a_, zznVar);
        zzb(2, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zznVar);
        zzb(4, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(zzao zzaoVar, String str, String str2) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zzaoVar);
        a_.writeString(str);
        a_.writeString(str2);
        zzb(5, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zzb(zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zznVar);
        zzb(6, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final List<zzkq> zza(zzn zznVar, boolean z) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zznVar);
        zzb.zza(a_, z);
        Parcel zza = zza(7, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzkq.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final byte[] zza(zzao zzaoVar, String str) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zzaoVar);
        a_.writeString(str);
        Parcel zza = zza(9, a_);
        byte[] createByteArray = zza.createByteArray();
        zza.recycle();
        return createByteArray;
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeLong(j);
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        zzb(10, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final String zzc(zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zznVar);
        Parcel zza = zza(11, a_);
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(zzw zzwVar, zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zzwVar);
        zzb.zza(a_, zznVar);
        zzb(12, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(zzw zzwVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zzwVar);
        zzb(13, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final List<zzkq> zza(String str, String str2, boolean z, zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzb.zza(a_, z);
        zzb.zza(a_, zznVar);
        Parcel zza = zza(14, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzkq.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final List<zzkq> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        zzb.zza(a_, z);
        Parcel zza = zza(15, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzkq.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final List<zzw> zza(String str, String str2, zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        zzb.zza(a_, zznVar);
        Parcel zza = zza(16, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzw.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final List<zzw> zza(String str, String str2, String str3) throws RemoteException {
        Parcel a_ = a_();
        a_.writeString(str);
        a_.writeString(str2);
        a_.writeString(str3);
        Parcel zza = zza(17, a_);
        ArrayList createTypedArrayList = zza.createTypedArrayList(zzw.CREATOR);
        zza.recycle();
        return createTypedArrayList;
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zzd(zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, zznVar);
        zzb(18, a_);
    }

    @Override // com.google.android.gms.measurement.internal.zzem
    public final void zza(Bundle bundle, zzn zznVar) throws RemoteException {
        Parcel a_ = a_();
        zzb.zza(a_, bundle);
        zzb.zza(a_, zznVar);
        zzb(19, a_);
    }
}
