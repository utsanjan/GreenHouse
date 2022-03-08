package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.TokenResult;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
final class AutoValue_TokenResult extends TokenResult {
    private final TokenResult.ResponseCode responseCode;
    private final String token;
    private final long tokenExpirationTimestamp;

    private AutoValue_TokenResult(String token, long tokenExpirationTimestamp, TokenResult.ResponseCode responseCode) {
        this.token = token;
        this.tokenExpirationTimestamp = tokenExpirationTimestamp;
        this.responseCode = responseCode;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public String getToken() {
        return this.token;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public long getTokenExpirationTimestamp() {
        return this.tokenExpirationTimestamp;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public TokenResult.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String toString() {
        return "TokenResult{token=" + this.token + ", tokenExpirationTimestamp=" + this.tokenExpirationTimestamp + ", responseCode=" + this.responseCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof TokenResult)) {
            return false;
        }
        TokenResult that = (TokenResult) o;
        String str = this.token;
        if (str != null ? str.equals(that.getToken()) : that.getToken() == null) {
            if (this.tokenExpirationTimestamp == that.getTokenExpirationTimestamp()) {
                TokenResult.ResponseCode responseCode = this.responseCode;
                if (responseCode == null) {
                    if (that.getResponseCode() == null) {
                        return true;
                    }
                } else if (responseCode.equals(that.getResponseCode())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        String str = this.token;
        int i = 0;
        int hashCode = str == null ? 0 : str.hashCode();
        long j = this.tokenExpirationTimestamp;
        int h$2 = (((h$ ^ hashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        TokenResult.ResponseCode responseCode = this.responseCode;
        if (responseCode != null) {
            i = responseCode.hashCode();
        }
        return h$2 ^ i;
    }

    @Override // com.google.firebase.installations.remote.TokenResult
    public TokenResult.Builder toBuilder() {
        return new Builder(this);
    }

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    /* loaded from: classes.dex */
    static final class Builder extends TokenResult.Builder {
        private TokenResult.ResponseCode responseCode;
        private String token;
        private Long tokenExpirationTimestamp;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(TokenResult source) {
            this.token = source.getToken();
            this.tokenExpirationTimestamp = Long.valueOf(source.getTokenExpirationTimestamp());
            this.responseCode = source.getResponseCode();
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setToken(String token) {
            this.token = token;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setTokenExpirationTimestamp(long tokenExpirationTimestamp) {
            this.tokenExpirationTimestamp = Long.valueOf(tokenExpirationTimestamp);
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult.Builder setResponseCode(TokenResult.ResponseCode responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        @Override // com.google.firebase.installations.remote.TokenResult.Builder
        public TokenResult build() {
            String missing = "";
            if (this.tokenExpirationTimestamp == null) {
                missing = missing + " tokenExpirationTimestamp";
            }
            if (missing.isEmpty()) {
                return new AutoValue_TokenResult(this.token, this.tokenExpirationTimestamp.longValue(), this.responseCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
