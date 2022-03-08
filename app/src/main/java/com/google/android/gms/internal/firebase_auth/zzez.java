package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.firebase.auth.zzc;
import java.util.ArrayList;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzez implements Parcelable.Creator<zzew> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzew[] newArray(int i) {
        return new zzew[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzew createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String str = null;
        long j = 0;
        long j2 = 0;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        zzfl zzflVar = null;
        String str5 = null;
        String str6 = null;
        zzc zzcVar = null;
        ArrayList arrayList = null;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 2:
                    str = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 3:
                    str2 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 4:
                    z = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 5:
                    str3 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 6:
                    str4 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 7:
                    zzflVar = (zzfl) SafeParcelReader.createParcelable(parcel, readHeader, zzfl.CREATOR);
                    break;
                case 8:
                    str5 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 9:
                    str6 = SafeParcelReader.createString(parcel, readHeader);
                    break;
                case 10:
                    j = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 11:
                    j2 = SafeParcelReader.readLong(parcel, readHeader);
                    break;
                case 12:
                    z2 = SafeParcelReader.readBoolean(parcel, readHeader);
                    break;
                case 13:
                    zzcVar = (zzc) SafeParcelReader.createParcelable(parcel, readHeader, zzc.CREATOR);
                    break;
                case 14:
                    arrayList = SafeParcelReader.createTypedList(parcel, readHeader, zzfh.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new zzew(str, str2, z, str3, str4, zzflVar, str5, str6, j, j2, z2, zzcVar, arrayList);
    }
}
