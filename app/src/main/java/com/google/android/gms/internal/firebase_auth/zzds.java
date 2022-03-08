package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.auth.EmailAuthCredential;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzds implements Parcelable.Creator<zzdp> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdp[] newArray(int i) {
        return new zzdp[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdp createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        EmailAuthCredential emailAuthCredential = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(readHeader) != 1) {
                SafeParcelReader.skipUnknownField(parcel, readHeader);
            } else {
                emailAuthCredential = (EmailAuthCredential) SafeParcelReader.createParcelable(parcel, readHeader, EmailAuthCredential.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzdp(emailAuthCredential);
    }
}
