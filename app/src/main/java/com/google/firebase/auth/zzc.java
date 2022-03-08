package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.firebase_auth.zzfy;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class zzc extends OAuthCredential {
    public static final Parcelable.Creator<zzc> CREATOR = new zze();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final zzfy zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(String str, String str2, String str3, zzfy zzfyVar, String str4, String str5, String str6) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzfyVar;
        this.zze = str4;
        this.zzf = str5;
        this.zzg = str6;
    }

    public static zzc zza(String str, String str2, String str3) {
        return zza(str, str2, str3, null, null);
    }

    public static zzc zza(String str, String str2, String str3, String str4, String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zzc(str, str2, str3, null, str4, str5, null);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    public static zzc zza(zzfy zzfyVar) {
        Preconditions.checkNotNull(zzfyVar, "Must specify a non-null webSignInCredential");
        return new zzc(null, null, null, zzfyVar, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzc zza(String str, String str2, String str3, String str4) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            return new zzc(str, str2, str3, null, null, null, str4);
        }
        throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
    }

    @Override // com.google.firebase.auth.AuthCredential
    public String getProvider() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.AuthCredential
    public String getSignInMethod() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public String getIdToken() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public String getAccessToken() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    public String getSecret() {
        return this.zzf;
    }

    public static zzfy zza(zzc zzcVar, String str) {
        Preconditions.checkNotNull(zzcVar);
        zzfy zzfyVar = zzcVar.zzd;
        if (zzfyVar != null) {
            return zzfyVar;
        }
        return new zzfy(zzcVar.getIdToken(), zzcVar.getAccessToken(), zzcVar.getProvider(), null, zzcVar.getSecret(), null, str, zzcVar.zze, zzcVar.zzg);
    }

    @Override // com.google.firebase.auth.AuthCredential
    public final AuthCredential zza() {
        return new zzc(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, getSecret(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
