package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.firebase_auth.zzp;
import com.google.firebase.auth.api.internal.zzfw;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfy extends AbstractSafeParcelable implements zzfw<zzp.zzr> {
    public static final Parcelable.Creator<zzfy> CREATOR = new zzgb();
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzf;
    private String zzg;
    private String zzh;
    private boolean zzi;
    private boolean zzj;
    private String zzk;
    private String zzl;
    private String zzm;
    private String zzn;
    private boolean zzo;
    private String zzp;

    public zzfy() {
        this.zzi = true;
        this.zzj = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzfy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, boolean z2, String str9, String str10, String str11, String str12, boolean z3, String str13) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = str5;
        this.zzf = str6;
        this.zzg = str7;
        this.zzh = str8;
        this.zzi = z;
        this.zzj = z2;
        this.zzk = str9;
        this.zzl = str10;
        this.zzm = str11;
        this.zzn = str12;
        this.zzo = z3;
        this.zzp = str13;
    }

    public zzfy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.zza = "http://localhost";
        this.zzc = str;
        this.zzd = str2;
        this.zzh = str5;
        this.zzk = str6;
        this.zzn = str7;
        this.zzp = str8;
        this.zzi = true;
        if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(this.zzd) || !TextUtils.isEmpty(this.zzk)) {
            this.zze = Preconditions.checkNotEmpty(str3);
            this.zzf = null;
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(this.zzc)) {
                sb.append("id_token=");
                sb.append(this.zzc);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzd)) {
                sb.append("access_token=");
                sb.append(this.zzd);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzf)) {
                sb.append("identifier=");
                sb.append(this.zzf);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzh)) {
                sb.append("oauth_token_secret=");
                sb.append(this.zzh);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(this.zzk)) {
                sb.append("code=");
                sb.append(this.zzk);
                sb.append("&");
            }
            if (!TextUtils.isEmpty(str9)) {
                sb.append("nonce=");
                sb.append(str9);
                sb.append("&");
            }
            sb.append("providerId=");
            sb.append(this.zze);
            this.zzg = sb.toString();
            this.zzj = true;
            return;
        }
        throw new IllegalArgumentException("idToken, accessToken and authCode cannot all be null");
    }

    public final zzfy zza(String str) {
        this.zzb = Preconditions.checkNotEmpty(str);
        return this;
    }

    public final zzfy zza(boolean z) {
        this.zzj = false;
        return this;
    }

    public final zzfy zzb(String str) {
        this.zzn = str;
        return this;
    }

    public final zzfy zzb(boolean z) {
        this.zzi = true;
        return this;
    }

    public final zzfy zzc(boolean z) {
        this.zzo = true;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 6, this.zze, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzf, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzg, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzh, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzi);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzj);
        SafeParcelWriter.writeString(parcel, 12, this.zzk, false);
        SafeParcelWriter.writeString(parcel, 13, this.zzl, false);
        SafeParcelWriter.writeString(parcel, 14, this.zzm, false);
        SafeParcelWriter.writeString(parcel, 15, this.zzn, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzo);
        SafeParcelWriter.writeString(parcel, 17, this.zzp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzfw
    public final /* synthetic */ zzp.zzr zza() {
        zzp.zzr.zza zzc = zzp.zzr.zza().zza(this.zzi).zzc(this.zzj);
        String str = this.zzb;
        if (str != null) {
            zzc.zzd(str);
        }
        String str2 = this.zza;
        if (str2 != null) {
            zzc.zza(str2);
        }
        String str3 = this.zzg;
        if (str3 != null) {
            zzc.zzb(str3);
        }
        String str4 = this.zzn;
        if (str4 != null) {
            zzc.zze(str4);
        }
        String str5 = this.zzp;
        if (str5 != null) {
            zzc.zzf(str5);
        }
        if (!TextUtils.isEmpty(this.zzl)) {
            zzc.zzc(this.zzl);
        }
        if (!TextUtils.isEmpty(this.zzm)) {
            zzc.zza(this.zzm);
        }
        return (zzp.zzr) ((zzif) zzc.zzb(this.zzo).zzg());
    }
}
