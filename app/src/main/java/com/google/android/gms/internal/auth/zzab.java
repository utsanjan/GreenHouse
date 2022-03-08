package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes.dex */
public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzab> CREATOR = new zzac();
    private final String accountType;
    private final int zzbw;
    private final int zzv;

    public zzab(String str, int i) {
        this(1, str, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzab(int i, String str, int i2) {
        this.zzv = 1;
        this.accountType = (String) Preconditions.checkNotNull(str);
        this.zzbw = i2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeString(parcel, 2, this.accountType, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbw);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
