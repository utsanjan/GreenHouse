package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzy extends MultiFactorSession {
    public static final Parcelable.Creator<zzy> CREATOR = new zzx();
    private String zza;
    private String zzb;
    private List<PhoneMultiFactorInfo> zzc;

    private zzy() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzy(String str, String str2, List<PhoneMultiFactorInfo> list) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = list;
    }

    public static zzy zza(String str) {
        Preconditions.checkNotEmpty(str);
        zzy zzyVar = new zzy();
        zzyVar.zza = str;
        return zzyVar;
    }

    public static zzy zza(List<MultiFactorInfo> list, String str) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotEmpty(str);
        zzy zzyVar = new zzy();
        zzyVar.zzc = new ArrayList();
        for (MultiFactorInfo multiFactorInfo : list) {
            if (multiFactorInfo instanceof PhoneMultiFactorInfo) {
                zzyVar.zzc.add((PhoneMultiFactorInfo) multiFactorInfo);
            }
        }
        zzyVar.zzb = str;
        return zzyVar;
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean zzc() {
        return this.zza != null;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
