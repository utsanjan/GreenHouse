package com.google.firebase.installations;

import com.google.firebase.installations.InstallationTokenResult;

/* compiled from: com.google.firebase:firebase-installations-interop@@16.0.0 */
/* loaded from: classes.dex */
final class AutoValue_InstallationTokenResult extends InstallationTokenResult {
    private final String token;
    private final long tokenCreationTimestamp;
    private final long tokenExpirationTimestamp;

    private AutoValue_InstallationTokenResult(String token, long tokenExpirationTimestamp, long tokenCreationTimestamp) {
        this.token = token;
        this.tokenExpirationTimestamp = tokenExpirationTimestamp;
        this.tokenCreationTimestamp = tokenCreationTimestamp;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public long getTokenCreationTimestamp() {
        return this.tokenCreationTimestamp;
    }

    public String toString() {
        return "InstallationTokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", tokenCreationTimestamp=" + this.tokenCreationTimestamp + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InstallationTokenResult)) {
            return false;
        }
        InstallationTokenResult that = (InstallationTokenResult) o;
        return this.token.equals(that.getToken()) && this.tokenExpirationTimestamp == that.getTokenExpirationTimestamp() && this.tokenCreationTimestamp == that.getTokenCreationTimestamp();
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.tokenExpirationTimestamp;
        long j2 = this.tokenCreationTimestamp;
        return ((((h$ ^ this.token.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    @Override // com.google.firebase.installations.InstallationTokenResult
    public InstallationTokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    /* compiled from: com.google.firebase:firebase-installations-interop@@16.0.0 */
    /* loaded from: classes.dex */
    static final class Builder extends InstallationTokenResult.Builder {
        private String token;
        private Long tokenCreationTimestamp;
        private Long tokenExpirationTimestamp;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(InstallationTokenResult source) {
            this.token = source.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(source.getTokenExpirationTimestamp());
            this.tokenCreationTimestamp = Long.valueOf(source.getTokenCreationTimestamp());
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setToken(String token) {
            if (token != null) {
                this.token = token;
                return this;
            }
            throw new NullPointerException("Null token");
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenExpirationTimestamp(long tokenExpirationTimestamp) {
            this.tokenExpirationTimestamp = Long.valueOf(tokenExpirationTimestamp);
            return this;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult.Builder setTokenCreationTimestamp(long tokenCreationTimestamp) {
            this.tokenCreationTimestamp = Long.valueOf(tokenCreationTimestamp);
            return this;
        }

        @Override // com.google.firebase.installations.InstallationTokenResult.Builder
        public InstallationTokenResult build() {
            String missing = "";
            if (this.token == null) {
                missing = missing + " token";
            }
            if (this.tokenExpirationTimestamp == null) {
                missing = missing + " tokenExpirationTimestamp";
            }
            if (this.tokenCreationTimestamp == null) {
                missing = missing + " tokenCreationTimestamp";
            }
            if (missing.isEmpty()) {
                return new AutoValue_InstallationTokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.tokenCreationTimestamp.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
