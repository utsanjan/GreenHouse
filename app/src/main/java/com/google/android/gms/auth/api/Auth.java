package com.google.android.gms.auth.api;

import android.os.Bundle;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.auth.api.signin.internal.zzh;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.p001authapi.zzj;
import com.google.android.gms.internal.p001authapi.zzq;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public final class Auth {
    public static final Api.ClientKey<zzq> zzg = new Api.ClientKey<>();
    public static final Api.ClientKey<zzh> zzh = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzq, AuthCredentialsOptions> zzi = new zzc();
    private static final Api.AbstractClientBuilder<zzh, GoogleSignInOptions> zzj = new zzd();
    @Deprecated
    public static final Api<AuthProxyOptions> PROXY_API = AuthProxy.API;
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API = new Api<>("Auth.CREDENTIALS_API", zzi, zzg);
    public static final Api<GoogleSignInOptions> GOOGLE_SIGN_IN_API = new Api<>("Auth.GOOGLE_SIGN_IN_API", zzj, zzh);
    @Deprecated
    public static final ProxyApi ProxyApi = AuthProxy.ProxyApi;
    public static final CredentialsApi CredentialsApi = new zzj();
    public static final GoogleSignInApi GoogleSignInApi = new zze();

    private Auth() {
    }

    /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
    @Deprecated
    /* loaded from: classes.dex */
    public static class AuthCredentialsOptions implements Api.ApiOptions.Optional {
        public static final AuthCredentialsOptions zzk = new Builder().zzd();
        private final String zzl;
        private final boolean zzm;
        private final String zzn;

        public AuthCredentialsOptions(Builder builder) {
            this.zzl = builder.zzl;
            this.zzm = builder.zzu.booleanValue();
            this.zzn = builder.zzn;
        }

        /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
        @Deprecated
        /* loaded from: classes.dex */
        public static class Builder {
            protected String zzl;
            protected String zzn;
            protected Boolean zzu;

            public Builder() {
                this.zzu = false;
            }

            public Builder(AuthCredentialsOptions authCredentialsOptions) {
                this.zzu = false;
                this.zzl = authCredentialsOptions.zzl;
                this.zzu = Boolean.valueOf(authCredentialsOptions.zzm);
                this.zzn = authCredentialsOptions.zzn;
            }

            public Builder forceEnableSaveDialog() {
                this.zzu = true;
                return this;
            }

            public Builder zzc(String str) {
                this.zzn = str;
                return this;
            }

            public AuthCredentialsOptions zzd() {
                return new AuthCredentialsOptions(this);
            }
        }

        public final String zzc() {
            return this.zzl;
        }

        public final String getLogSessionId() {
            return this.zzn;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof AuthCredentialsOptions)) {
                return false;
            }
            AuthCredentialsOptions authCredentialsOptions = (AuthCredentialsOptions) obj;
            return Objects.equal(this.zzl, authCredentialsOptions.zzl) && this.zzm == authCredentialsOptions.zzm && Objects.equal(this.zzn, authCredentialsOptions.zzn);
        }

        public int hashCode() {
            return Objects.hashCode(this.zzl, Boolean.valueOf(this.zzm), this.zzn);
        }

        public final Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", this.zzl);
            bundle.putBoolean("force_save_dialog", this.zzm);
            bundle.putString("log_session_id", this.zzn);
            return bundle;
        }
    }
}
