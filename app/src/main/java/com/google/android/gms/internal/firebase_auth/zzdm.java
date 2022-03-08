package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzdm implements Parcelable.Creator<zzdj> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdj[] newArray(int i) {
        return new zzdj[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdj createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zzfy zzfyVar = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                zzfyVar = (zzfy) SafeParcelReader.createParcelable(parcel, readHeader, zzfy.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzdj(zzfyVar);
    }
}
