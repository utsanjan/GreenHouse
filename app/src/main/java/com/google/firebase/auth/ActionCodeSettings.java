package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.firebase_auth.zzgj;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class ActionCodeSettings extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ActionCodeSettings> CREATOR = new zzb();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final boolean zze;
    private final String zzf;
    private final boolean zzg;
    private String zzh;
    private int zzi;
    private String zzj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActionCodeSettings(String str, String str2, String str3, String str4, boolean z, String str5, boolean z2, String str6, int i, String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = str4;
        this.zze = z;
        this.zzf = str5;
        this.zzg = z2;
        this.zzh = str6;
        this.zzi = i;
        this.zzj = str7;
    }

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    public static class Builder {
        private String zza;
        private String zzb;
        private String zzc;
        private boolean zzd;
        private String zze;
        private boolean zzf;
        private String zzg;

        private Builder() {
            this.zzf = false;
        }

        public String getUrl() {
            return this.zza;
        }

        public Builder setUrl(String str) {
            this.zza = str;
            return this;
        }

        public String getIOSBundleId() {
            return this.zzb;
        }

        public Builder setIOSBundleId(String str) {
            this.zzb = str;
            return this;
        }

        public Builder setAndroidPackageName(String str, boolean z, String str2) {
            this.zzc = str;
            this.zzd = z;
            this.zze = str2;
            return this;
        }

        public boolean getHandleCodeInApp() {
            return this.zzf;
        }

        public Builder setHandleCodeInApp(boolean z) {
            this.zzf = z;
            return this;
        }

        public String getDynamicLinkDomain() {
            return this.zzg;
        }

        public Builder setDynamicLinkDomain(String str) {
            this.zzg = str;
            return this;
        }

        public ActionCodeSettings build() {
            if (this.zza != null) {
                return new ActionCodeSettings(this);
            }
            throw new IllegalArgumentException("Cannot build ActionCodeSettings with null URL. Call #setUrl(String) before calling build()");
        }
    }

    private ActionCodeSettings(Builder builder) {
        this.zza = builder.zza;
        this.zzb = builder.zzb;
        this.zzc = null;
        this.zzd = builder.zzc;
        this.zze = builder.zzd;
        this.zzf = builder.zze;
        this.zzg = builder.zzf;
        this.zzj = builder.zzg;
    }

    public static ActionCodeSettings zza() {
        return new ActionCodeSettings(new Builder());
    }

    public String getUrl() {
        return this.zza;
    }

    public String getIOSBundle() {
        return this.zzb;
    }

    public final String zzb() {
        return this.zzc;
    }

    public String getAndroidPackageName() {
        return this.zzd;
    }

    public boolean getAndroidInstallApp() {
        return this.zze;
    }

    public String getAndroidMinimumVersion() {
        return this.zzf;
    }

    public boolean canHandleCodeInApp() {
        return this.zzg;
    }

    public final void zza(String str) {
        this.zzh = str;
    }

    public final String zzc() {
        return this.zzh;
    }

    public final void zza(zzgj zzgjVar) {
        this.zzi = zzgjVar.zza();
    }

    public final int zzd() {
        return this.zzi;
    }

    public final String zze() {
        return this.zzj;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUrl(), false);
        SafeParcelWriter.writeString(parcel, 2, getIOSBundle(), false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, getAndroidPackageName(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, getAndroidInstallApp());
        SafeParcelWriter.writeString(parcel, 6, getAndroidMinimumVersion(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, canHandleCodeInApp());
        SafeParcelWriter.writeString(parcel, 8, this.zzh, false);
        SafeParcelWriter.writeInt(parcel, 9, this.zzi);
        SafeParcelWriter.writeString(parcel, 10, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
