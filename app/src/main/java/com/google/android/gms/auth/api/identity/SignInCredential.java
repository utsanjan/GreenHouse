package com.google.android.gms.auth.api.identity;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public final class SignInCredential extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SignInCredential> CREATOR = new zzg();
    private final String zzba;
    private final String zzbb;
    private final String zzbc;
    private final String zzbd;
    private final Uri zzbe;
    private final String zzbf;
    private final String zzbg;

    public SignInCredential(String str, String str2, String str3, String str4, Uri uri, String str5, String str6) {
        this.zzba = Preconditions.checkNotEmpty(str);
        this.zzbb = str2;
        this.zzbc = str3;
        this.zzbd = str4;
        this.zzbe = uri;
        this.zzbf = str5;
        this.zzbg = str6;
    }

    public final String getId() {
        return this.zzba;
    }

    public final String getDisplayName() {
        return this.zzbb;
    }

    public final String getGivenName() {
        return this.zzbc;
    }

    public final String getFamilyName() {
        return this.zzbd;
    }

    public final Uri getProfilePictureUri() {
        return this.zzbe;
    }

    public final String getPassword() {
        return this.zzbf;
    }

    public final String getGoogleIdToken() {
        return this.zzbg;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzba, this.zzbb, this.zzbc, this.zzbd, this.zzbe, this.zzbf, this.zzbg);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SignInCredential)) {
            return false;
        }
        SignInCredential signInCredential = (SignInCredential) obj;
        return Objects.equal(this.zzba, signInCredential.zzba) && Objects.equal(this.zzbb, signInCredential.zzbb) && Objects.equal(this.zzbc, signInCredential.zzbc) && Objects.equal(this.zzbd, signInCredential.zzbd) && Objects.equal(this.zzbe, signInCredential.zzbe) && Objects.equal(this.zzbf, signInCredential.zzbf) && Objects.equal(this.zzbg, signInCredential.zzbg);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, getGivenName(), false);
        SafeParcelWriter.writeString(parcel, 4, getFamilyName(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getProfilePictureUri(), i, false);
        SafeParcelWriter.writeString(parcel, 6, getPassword(), false);
        SafeParcelWriter.writeString(parcel, 7, getGoogleIdToken(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
