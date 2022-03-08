package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class UserProfileChangeRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<UserProfileChangeRequest> CREATOR = new zzab();
    private String zza;
    private String zzb;
    private boolean zzc;
    private boolean zzd;
    private Uri zze;

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static class Builder {
        private String zza;
        private Uri zzb;
        private boolean zzc;
        private boolean zzd;

        public String getDisplayName() {
            return this.zza;
        }

        public Builder setDisplayName(String str) {
            if (str == null) {
                this.zzc = true;
            } else {
                this.zza = str;
            }
            return this;
        }

        public Uri getPhotoUri() {
            return this.zzb;
        }

        public Builder setPhotoUri(Uri uri) {
            if (uri == null) {
                this.zzd = true;
            } else {
                this.zzb = uri;
            }
            return this;
        }

        public UserProfileChangeRequest build() {
            String str = this.zza;
            Uri uri = this.zzb;
            return new UserProfileChangeRequest(str, uri == null ? null : uri.toString(), this.zzc, this.zzd);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UserProfileChangeRequest(String str, String str2, boolean z, boolean z2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = z;
        this.zzd = z2;
        this.zze = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    public String getDisplayName() {
        return this.zza;
    }

    public final String zza() {
        return this.zzb;
    }

    public Uri getPhotoUri() {
        return this.zze;
    }

    public final boolean zzb() {
        return this.zzc;
    }

    public final boolean zzc() {
        return this.zzd;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzc);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
