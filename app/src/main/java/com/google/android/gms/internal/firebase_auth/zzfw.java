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
public final class zzfw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfw> CREATOR = new zzfz();
    private final int zza;
    private List<String> zzb;

    public final List<String> zza() {
        return this.zzb;
    }

    public zzfw() {
        this(null);
    }

    private zzfw(List<String> list) {
        this.zza = 1;
        this.zzb = new ArrayList();
        if (list != null && !list.isEmpty()) {
            this.zzb.addAll(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfw(int i, List<String> list) {
        this.zza = i;
        if (list == null || list.isEmpty()) {
            this.zzb = Collections.emptyList();
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            list.set(i2, Strings.emptyToNull(list.get(i2)));
        }
        this.zzb = Collections.unmodifiableList(list);
    }

    public static zzfw zzb() {
        return new zzfw(null);
    }

    public static zzfw zza(zzfw zzfwVar) {
        return new zzfw(zzfwVar != null ? zzfwVar.zzb : null);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zza);
        SafeParcelWriter.writeStringList(parcel, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
