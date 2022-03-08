package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.firebase_auth.zzej;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.MultiFactorAssertion;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.MultiFactorResolver;
import com.google.firebase.auth.MultiFactorSession;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import com.google.firebase.auth.zzc;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzt extends MultiFactorResolver {
    public static final Parcelable.Creator<zzt> CREATOR = new zzv();
    private final List<PhoneMultiFactorInfo> zza = new ArrayList();
    private final zzy zzb;
    private final String zzc;
    private final zzc zzd;
    private final zzn zze;

    public zzt(List<PhoneMultiFactorInfo> list, zzy zzyVar, String str, zzc zzcVar, zzn zznVar) {
        for (PhoneMultiFactorInfo phoneMultiFactorInfo : list) {
            if (phoneMultiFactorInfo instanceof PhoneMultiFactorInfo) {
                this.zza.add(phoneMultiFactorInfo);
            }
        }
        this.zzb = (zzy) Preconditions.checkNotNull(zzyVar);
        this.zzc = Preconditions.checkNotEmpty(str);
        this.zzd = zzcVar;
        this.zze = zznVar;
    }

    public static zzt zza(zzej zzejVar, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        List<MultiFactorInfo> zzc = zzejVar.zzc();
        ArrayList arrayList = new ArrayList();
        for (MultiFactorInfo multiFactorInfo : zzc) {
            if (multiFactorInfo instanceof PhoneMultiFactorInfo) {
                arrayList.add((PhoneMultiFactorInfo) multiFactorInfo);
            }
        }
        return new zzt(arrayList, zzy.zza(zzejVar.zzc(), zzejVar.zza()), firebaseAuth.zzb().getName(), zzejVar.zzb(), (zzn) firebaseUser);
    }

    @Override // com.google.firebase.auth.MultiFactorResolver
    public final MultiFactorSession getSession() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.MultiFactorResolver
    public final List<MultiFactorInfo> getHints() {
        ArrayList arrayList = new ArrayList();
        for (PhoneMultiFactorInfo phoneMultiFactorInfo : this.zza) {
            arrayList.add(phoneMultiFactorInfo);
        }
        return arrayList;
    }

    @Override // com.google.firebase.auth.MultiFactorResolver
    public final Task<AuthResult> resolveSignIn(MultiFactorAssertion multiFactorAssertion) {
        return getFirebaseAuth().zza(multiFactorAssertion, this.zzb, this.zze).continueWithTask(new zzw(this));
    }

    @Override // com.google.firebase.auth.MultiFactorResolver
    public final FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance(FirebaseApp.getInstance(this.zzc));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getSession(), i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
