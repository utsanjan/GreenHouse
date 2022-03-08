package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzel;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzeu extends AbstractSafeParcelable implements zzel<zzeu, zzp.zzg> {
    public static final Parcelable.Creator<zzeu> CREATOR = new zzex();
    private zzey zza;

    public zzeu() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzeu(zzey zzeyVar) {
        this.zza = zzeyVar == null ? new zzey() : zzey.zza(zzeyVar);
    }

    public final List<zzew> zzb() {
        return this.zza.zza();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzp.zzg> zza() {
        return zzp.zzg.zzb();
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzeu zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzp.zzg) {
            zzp.zzg zzgVar = (zzp.zzg) zzjnVar;
            if (zzgVar.zza() == 0) {
                this.zza = new zzey();
            } else {
                this.zza = zzey.zza(zzgVar);
            }
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of GetAccountInfoResponse.");
    }
}
