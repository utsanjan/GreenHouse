package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.firebase.auth.AdditionalUserInfo;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.zzc;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzh implements AuthResult {
    public static final Parcelable.Creator<zzh> CREATOR = new zzk();
    private zzn zza;
    private zzf zzb;
    private zzc zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzh(zzn zznVar, zzf zzfVar, zzc zzcVar) {
        this.zza = zznVar;
        this.zzb = zzfVar;
        this.zzc = zzcVar;
    }

    public zzh(zzn zznVar) {
        zzn zznVar2 = (zzn) Preconditions.checkNotNull(zznVar);
        this.zza = zznVar2;
        List<zzj> zzh = zznVar2.zzh();
        this.zzb = null;
        for (int i = 0; i < zzh.size(); i++) {
            if (!TextUtils.isEmpty(zzh.get(i).zza())) {
                this.zzb = new zzf(zzh.get(i).getProviderId(), zzh.get(i).zza(), zznVar.zzi());
            }
        }
        if (this.zzb == null) {
            this.zzb = new zzf(zznVar.zzi());
        }
        this.zzc = zznVar.zzj();
    }

    @Override // com.google.firebase.auth.AuthResult
    public final FirebaseUser getUser() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final AdditionalUserInfo getAdditionalUserInfo() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.AuthResult
    public final AuthCredential getCredential() {
        return this.zzc;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getUser(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getAdditionalUserInfo(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzc, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }
}
