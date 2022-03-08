package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzey extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzey> CREATOR = new zzfb();
    private List<zzew> zza;

    public zzey() {
        this.zza = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzey(List<zzew> list) {
        List<zzew> list2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.zza = list2;
    }

    public final List<zzew> zza() {
        return this.zza;
    }

    public static zzey zza(zzey zzeyVar) {
        Preconditions.checkNotNull(zzeyVar);
        List<zzew> list = zzeyVar.zza;
        zzey zzeyVar2 = new zzey();
        if (list != null && !list.isEmpty()) {
            zzeyVar2.zza.addAll(list);
        }
        return zzeyVar2;
    }

    public static zzey zza(zzp.zzg zzgVar) {
        ArrayList arrayList = new ArrayList(zzgVar.zza());
        for (int i = 0; i < zzgVar.zza(); i++) {
            zzz zza = zzgVar.zza(i);
            arrayList.add(new zzew(Strings.emptyToNull(zza.zza()), Strings.emptyToNull(zza.zzb()), zza.zze(), Strings.emptyToNull(zza.zzc()), Strings.emptyToNull(zza.zzd()), zzfl.zza(zza.zzf()), Strings.emptyToNull(zza.zzi()), Strings.emptyToNull(zza.zzj()), zza.zzh(), zza.zzg(), false, null, zzfh.zza(zza.zzk())));
        }
        return new zzey(arrayList);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
