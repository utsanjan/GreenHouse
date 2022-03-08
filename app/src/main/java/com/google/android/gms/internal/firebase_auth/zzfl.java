package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfl> CREATOR = new zzfk();
    private List<zzfj> zza;

    public zzfl() {
        this.zza = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfl(List<zzfj> list) {
        if (list == null || list.isEmpty()) {
            this.zza = Collections.emptyList();
        } else {
            this.zza = Collections.unmodifiableList(list);
        }
    }

    public final List<zzfj> zza() {
        return this.zza;
    }

    public static zzfl zza(zzfl zzflVar) {
        List<zzfj> list = zzflVar.zza;
        zzfl zzflVar2 = new zzfl();
        if (list != null) {
            zzflVar2.zza.addAll(list);
        }
        return zzflVar2;
    }

    public static zzfl zza(List<zzu> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzu zzuVar = list.get(i);
            arrayList.add(new zzfj(Strings.emptyToNull(zzuVar.zzd()), Strings.emptyToNull(zzuVar.zzb()), Strings.emptyToNull(zzuVar.zzc()), Strings.emptyToNull(zzuVar.zza()), null, Strings.emptyToNull(zzuVar.zzf()), Strings.emptyToNull(zzuVar.zze())));
        }
        return new zzfl(arrayList);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
