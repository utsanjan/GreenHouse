package com.google.android.gms.auth.api.identity;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public final class BeginSignInRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<BeginSignInRequest> CREATOR = new zzc();
    private final PasswordRequestOptions zzas;
    private final GoogleIdTokenRequestOptions zzat;
    private final String zzau;

    /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
    /* loaded from: classes.dex */
    public static final class GoogleIdTokenRequestOptions extends AbstractSafeParcelable {
        public static final Parcelable.Creator<GoogleIdTokenRequestOptions> CREATOR = new zzd();
        private final boolean zzav;
        private final String zzaw;
        private final String zzax;
        private final boolean zzay;

        public static Builder builder() {
            return new Builder();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public GoogleIdTokenRequestOptions(boolean z, String str, String str2, boolean z2) {
            this.zzav = z;
            if (z) {
                Preconditions.checkNotNull(str, "serverClientId must be provided if Google ID tokens are requested");
            }
            this.zzaw = str;
            this.zzax = str2;
            this.zzay = z2;
        }

        /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
        /* loaded from: classes.dex */
        public static final class Builder {
            private boolean zzav = false;
            private String zzaw = null;
            private String zzax = null;
            private boolean zzay = true;

            public final Builder setSupported(boolean z) {
                this.zzav = z;
                return this;
            }

            public final Builder setServerClientId(String str) {
                this.zzaw = Preconditions.checkNotEmpty(str);
                return this;
            }

            public final Builder setNonce(String str) {
                this.zzax = str;
                return this;
            }

            public final Builder setFilterByAuthorizedAccounts(boolean z) {
                this.zzay = z;
                return this;
            }

            public final GoogleIdTokenRequestOptions build() {
                return new GoogleIdTokenRequestOptions(this.zzav, this.zzaw, this.zzax, this.zzay);
            }
        }

        public final boolean isSupported() {
            return this.zzav;
        }

        public final String getServerClientId() {
            return this.zzaw;
        }

        public final String getNonce() {
            return this.zzax;
        }

        public final boolean filterByAuthorizedAccounts() {
            return this.zzay;
        }

        public final int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.zzav), this.zzaw, this.zzax, Boolean.valueOf(this.zzay));
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof GoogleIdTokenRequestOptions)) {
                return false;
            }
            GoogleIdTokenRequestOptions googleIdTokenRequestOptions = (GoogleIdTokenRequestOptions) obj;
            return this.zzav == googleIdTokenRequestOptions.zzav && Objects.equal(this.zzaw, googleIdTokenRequestOptions.zzaw) && Objects.equal(this.zzax, googleIdTokenRequestOptions.zzax) && this.zzay == googleIdTokenRequestOptions.zzay;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, isSupported());
            SafeParcelWriter.writeString(parcel, 2, getServerClientId(), false);
            SafeParcelWriter.writeString(parcel, 3, getNonce(), false);
            SafeParcelWriter.writeBoolean(parcel, 4, filterByAuthorizedAccounts());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
    /* loaded from: classes.dex */
    public static final class PasswordRequestOptions extends AbstractSafeParcelable {
        public static final Parcelable.Creator<PasswordRequestOptions> CREATOR = new zzf();
        private final boolean zzav;

        public static Builder builder() {
            return new Builder();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public PasswordRequestOptions(boolean z) {
            this.zzav = z;
        }

        /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
        /* loaded from: classes.dex */
        public static final class Builder {
            private boolean zzav = false;

            public final Builder setSupported(boolean z) {
                this.zzav = z;
                return this;
            }

            public final PasswordRequestOptions build() {
                return new PasswordRequestOptions(this.zzav);
            }
        }

        public final boolean isSupported() {
            return this.zzav;
        }

        public final int hashCode() {
            return Objects.hashCode(Boolean.valueOf(this.zzav));
        }

        public final boolean equals(Object obj) {
            return (obj instanceof PasswordRequestOptions) && this.zzav == ((PasswordRequestOptions) obj).zzav;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeBoolean(parcel, 1, isSupported());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public BeginSignInRequest(PasswordRequestOptions passwordRequestOptions, GoogleIdTokenRequestOptions googleIdTokenRequestOptions, String str) {
        this.zzas = (PasswordRequestOptions) Preconditions.checkNotNull(passwordRequestOptions);
        this.zzat = (GoogleIdTokenRequestOptions) Preconditions.checkNotNull(googleIdTokenRequestOptions);
        this.zzau = str;
    }

    /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
    /* loaded from: classes.dex */
    public static final class Builder {
        private PasswordRequestOptions zzas = PasswordRequestOptions.builder().setSupported(false).build();
        private GoogleIdTokenRequestOptions zzat = GoogleIdTokenRequestOptions.builder().setSupported(false).build();
        private String zzau;

        public final Builder setPasswordRequestOptions(PasswordRequestOptions passwordRequestOptions) {
            this.zzas = (PasswordRequestOptions) Preconditions.checkNotNull(passwordRequestOptions);
            return this;
        }

        public final Builder setGoogleIdTokenRequestOptions(GoogleIdTokenRequestOptions googleIdTokenRequestOptions) {
            this.zzat = (GoogleIdTokenRequestOptions) Preconditions.checkNotNull(googleIdTokenRequestOptions);
            return this;
        }

        public final Builder zzd(String str) {
            this.zzau = str;
            return this;
        }

        public final BeginSignInRequest build() {
            return new BeginSignInRequest(this.zzas, this.zzat, this.zzau);
        }
    }

    public final PasswordRequestOptions getPasswordRequestOptions() {
        return this.zzas;
    }

    public final GoogleIdTokenRequestOptions getGoogleIdTokenRequestOptions() {
        return this.zzat;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzas, this.zzat, this.zzau);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof BeginSignInRequest)) {
            return false;
        }
        BeginSignInRequest beginSignInRequest = (BeginSignInRequest) obj;
        return Objects.equal(this.zzas, beginSignInRequest.zzas) && Objects.equal(this.zzat, beginSignInRequest.zzat) && Objects.equal(this.zzau, beginSignInRequest.zzau);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getPasswordRequestOptions(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getGoogleIdTokenRequestOptions(), i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzau, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static Builder zzc(BeginSignInRequest beginSignInRequest) {
        Preconditions.checkNotNull(beginSignInRequest);
        Builder passwordRequestOptions = builder().setGoogleIdTokenRequestOptions(beginSignInRequest.getGoogleIdTokenRequestOptions()).setPasswordRequestOptions(beginSignInRequest.getPasswordRequestOptions());
        String str = beginSignInRequest.zzau;
        if (str != null) {
            passwordRequestOptions.zzd(str);
        }
        return passwordRequestOptions;
    }
}
