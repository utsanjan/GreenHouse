package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzel;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzgc extends AbstractSafeParcelable implements zzel<zzgc, zzp.zzu> {
    public static final Parcelable.Creator<zzgc> CREATOR = new zzgf();
    private String zza;
    private String zzb;
    private long zzc;
    private boolean zzd;

    public zzgc() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzgc(String str, String str2, long j, boolean z) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = j;
        this.zzd = z;
    }

    public final String zzb() {
        return this.zza;
    }

    public final String zzc() {
        return this.zzb;
    }

    public final long zzd() {
        return this.zzc;
    }

    public final boolean zze() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeLong(parcel, 4, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final zzjx<zzp.zzu> zza() {
        return zzp.zzu.zze();
    }

    @Override // com.google.firebase.auth.api.internal.zzel
    public final /* synthetic */ zzgc zza(zzjn zzjnVar) {
        if (zzjnVar instanceof zzp.zzu) {
            zzp.zzu zzuVar = (zzp.zzu) zzjnVar;
            this.zza = Strings.emptyToNull(zzuVar.zza());
            this.zzb = Strings.emptyToNull(zzuVar.zzb());
            this.zzc = zzuVar.zzc();
            this.zzd = zzuVar.zzd();
            return this;
        }
        throw new IllegalArgumentException("The passed proto must be an instance of VerifyCustomTokenResponse.");
    }
}
