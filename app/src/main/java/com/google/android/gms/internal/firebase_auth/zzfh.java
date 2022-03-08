package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfh extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfh> CREATOR = new zzfg();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final long zzd;
    private String zze;

    public zzfh(String str, String str2, String str3, long j) {
        this.zza = str;
        this.zzb = Preconditions.checkNotEmpty(str2);
        this.zzc = str3;
        this.zzd = j;
    }

    public final String zza() {
        return this.zza;
    }

    public final String zzb() {
        return this.zzb;
    }

    public final String zzc() {
        return this.zzc;
    }

    public final long zzd() {
        return this.zzd;
    }

    public static zzfh zza(zzr zzrVar) {
        zzfh zzfhVar = new zzfh(zzrVar.zza(), zzrVar.zzb(), zzrVar.zzc(), zzrVar.zzd().zza());
        zzfhVar.zze = zzrVar.zze();
        return zzfhVar;
    }

    public static List<zzfh> zza(List<zzr> list) {
        if (list == null) {
            return zzbg.zza();
        }
        ArrayList arrayList = new ArrayList();
        for (zzr zzrVar : list) {
            arrayList.add(zza(zzrVar));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
