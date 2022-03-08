package com.google.android.gms.auth.api.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public final class SignInConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<SignInConfiguration> CREATOR = new zzx();
    private final String zzcm;
    private GoogleSignInOptions zzcn;

    public SignInConfiguration(String str, GoogleSignInOptions googleSignInOptions) {
        this.zzcm = Preconditions.checkNotEmpty(str);
        this.zzcn = googleSignInOptions;
    }

    public final GoogleSignInOptions zzo() {
        return this.zzcn;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzcm, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzcn, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SignInConfiguration)) {
            return false;
        }
        SignInConfiguration signInConfiguration = (SignInConfiguration) obj;
        if (this.zzcm.equals(signInConfiguration.zzcm)) {
            GoogleSignInOptions googleSignInOptions = this.zzcn;
            if (googleSignInOptions == null) {
                if (signInConfiguration.zzcn == null) {
                    return true;
                }
            } else if (googleSignInOptions.equals(signInConfiguration.zzcn)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return new HashAccumulator().addObject(this.zzcm).addObject(this.zzcn).hash();
    }
}
