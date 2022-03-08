package com.google.firebase.installations.remote;

import com.google.firebase.installations.remote.InstallationResponse;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
final class AutoValue_InstallationResponse extends InstallationResponse {
    private final TokenResult authToken;
    private final String fid;
    private final String refreshToken;
    private final InstallationResponse.ResponseCode responseCode;
    private final String uri;

    private AutoValue_InstallationResponse(String uri, String fid, String refreshToken, TokenResult authToken, InstallationResponse.ResponseCode responseCode) {
        this.uri = uri;
        this.fid = fid;
        this.refreshToken = refreshToken;
        this.authToken = authToken;
        this.responseCode = responseCode;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public String getUri() {
        return this.uri;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public String getFid() {
        return this.fid;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public TokenResult getAuthToken() {
        return this.authToken;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public InstallationResponse.ResponseCode getResponseCode() {
        return this.responseCode;
    }

    public String toString() {
        return "InstallationResponse{uri=" + this.uri + ", fid=" + this.fid + ", refreshToken=" + this.refreshToken + ", authToken=" + this.authToken + ", responseCode=" + this.responseCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InstallationResponse)) {
            return false;
        }
        InstallationResponse that = (InstallationResponse) o;
        String str = this.uri;
        if (str != null ? str.equals(that.getUri()) : that.getUri() == null) {
            String str2 = this.fid;
            if (str2 != null ? str2.equals(that.getFid()) : that.getFid() == null) {
                String str3 = this.refreshToken;
                if (str3 != null ? str3.equals(that.getRefreshToken()) : that.getRefreshToken() == null) {
                    TokenResult tokenResult = this.authToken;
                    if (tokenResult != null ? tokenResult.equals(that.getAuthToken()) : that.getAuthToken() == null) {
                        InstallationResponse.ResponseCode responseCode = this.responseCode;
                        if (responseCode == null) {
                            if (that.getResponseCode() == null) {
                                return true;
                            }
                        } else if (responseCode.equals(that.getResponseCode())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        String str = this.uri;
        int i = 0;
        int h$2 = (h$ ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.fid;
        int h$3 = (h$2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.refreshToken;
        int h$4 = (h$3 ^ (str3 == null ? 0 : str3.hashCode())) * 1000003;
        TokenResult tokenResult = this.authToken;
        int h$5 = (h$4 ^ (tokenResult == null ? 0 : tokenResult.hashCode())) * 1000003;
        InstallationResponse.ResponseCode responseCode = this.responseCode;
        if (responseCode != null) {
            i = responseCode.hashCode();
        }
        return h$5 ^ i;
    }

    @Override // com.google.firebase.installations.remote.InstallationResponse
    public InstallationResponse.Builder toBuilder() {
        return new Builder(this);
    }

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    /* loaded from: classes.dex */
    static final class Builder extends InstallationResponse.Builder {
        private TokenResult authToken;
        private String fid;
        private String refreshToken;
        private InstallationResponse.ResponseCode responseCode;
        private String uri;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(InstallationResponse source) {
            this.uri = source.getUri();
            this.fid = source.getFid();
            this.refreshToken = source.getRefreshToken();
            this.authToken = source.getAuthToken();
            this.responseCode = source.getResponseCode();
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setUri(String uri) {
            this.uri = uri;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setFid(String fid) {
            this.fid = fid;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setAuthToken(TokenResult authToken) {
            this.authToken = authToken;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse.Builder setResponseCode(InstallationResponse.ResponseCode responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        @Override // com.google.firebase.installations.remote.InstallationResponse.Builder
        public InstallationResponse build() {
            return new AutoValue_InstallationResponse(this.uri, this.fid, this.refreshToken, this.authToken, this.responseCode);
        }
    }
}
