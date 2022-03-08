package com.google.firebase.installations.local;

import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
final class AutoValue_PersistedInstallationEntry extends PersistedInstallationEntry {
    private final String authToken;
    private final long expiresInSecs;
    private final String firebaseInstallationId;
    private final String fisError;
    private final String refreshToken;
    private final PersistedInstallation.RegistrationStatus registrationStatus;
    private final long tokenCreationEpochInSecs;

    private AutoValue_PersistedInstallationEntry(String firebaseInstallationId, PersistedInstallation.RegistrationStatus registrationStatus, String authToken, String refreshToken, long expiresInSecs, long tokenCreationEpochInSecs, String fisError) {
        this.firebaseInstallationId = firebaseInstallationId;
        this.registrationStatus = registrationStatus;
        this.authToken = authToken;
        this.refreshToken = refreshToken;
        this.expiresInSecs = expiresInSecs;
        this.tokenCreationEpochInSecs = tokenCreationEpochInSecs;
        this.fisError = fisError;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFirebaseInstallationId() {
        return this.firebaseInstallationId;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public PersistedInstallation.RegistrationStatus getRegistrationStatus() {
        return this.registrationStatus;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getAuthToken() {
        return this.authToken;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getExpiresInSecs() {
        return this.expiresInSecs;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public long getTokenCreationEpochInSecs() {
        return this.tokenCreationEpochInSecs;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public String getFisError() {
        return this.fisError;
    }

    public String toString() {
        return "PersistedInstallationEntry{firebaseInstallationId=" + this.firebaseInstallationId + ", registrationStatus=" + this.registrationStatus + ", authToken=" + this.authToken + ", refreshToken=" + this.refreshToken + ", expiresInSecs=" + this.expiresInSecs + ", tokenCreationEpochInSecs=" + this.tokenCreationEpochInSecs + ", fisError=" + this.fisError + "}";
    }

    public boolean equals(Object o) {
        String str;
        String str2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof PersistedInstallationEntry)) {
            return false;
        }
        PersistedInstallationEntry that = (PersistedInstallationEntry) o;
        String str3 = this.firebaseInstallationId;
        if (str3 != null ? str3.equals(that.getFirebaseInstallationId()) : that.getFirebaseInstallationId() == null) {
            if (this.registrationStatus.equals(that.getRegistrationStatus()) && ((str = this.authToken) != null ? str.equals(that.getAuthToken()) : that.getAuthToken() == null) && ((str2 = this.refreshToken) != null ? str2.equals(that.getRefreshToken()) : that.getRefreshToken() == null) && this.expiresInSecs == that.getExpiresInSecs() && this.tokenCreationEpochInSecs == that.getTokenCreationEpochInSecs()) {
                String str4 = this.fisError;
                if (str4 == null) {
                    if (that.getFisError() == null) {
                        return true;
                    }
                } else if (str4.equals(that.getFisError())) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        String str = this.firebaseInstallationId;
        int i = 0;
        int h$2 = (((h$ ^ (str == null ? 0 : str.hashCode())) * 1000003) ^ this.registrationStatus.hashCode()) * 1000003;
        String str2 = this.authToken;
        int h$3 = (h$2 ^ (str2 == null ? 0 : str2.hashCode())) * 1000003;
        String str3 = this.refreshToken;
        int hashCode = str3 == null ? 0 : str3.hashCode();
        long j = this.expiresInSecs;
        long j2 = this.tokenCreationEpochInSecs;
        int h$4 = (((((h$3 ^ hashCode) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003;
        String str4 = this.fisError;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return h$4 ^ i;
    }

    @Override // com.google.firebase.installations.local.PersistedInstallationEntry
    public PersistedInstallationEntry.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    /* loaded from: classes.dex */
    public static final class Builder extends PersistedInstallationEntry.Builder {
        private String authToken;
        private Long expiresInSecs;
        private String firebaseInstallationId;
        private String fisError;
        private String refreshToken;
        private PersistedInstallation.RegistrationStatus registrationStatus;
        private Long tokenCreationEpochInSecs;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(PersistedInstallationEntry source) {
            this.firebaseInstallationId = source.getFirebaseInstallationId();
            this.registrationStatus = source.getRegistrationStatus();
            this.authToken = source.getAuthToken();
            this.refreshToken = source.getRefreshToken();
            this.expiresInSecs = Long.valueOf(source.getExpiresInSecs());
            this.tokenCreationEpochInSecs = Long.valueOf(source.getTokenCreationEpochInSecs());
            this.fisError = source.getFisError();
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setFirebaseInstallationId(String firebaseInstallationId) {
            this.firebaseInstallationId = firebaseInstallationId;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setRegistrationStatus(PersistedInstallation.RegistrationStatus registrationStatus) {
            if (registrationStatus != null) {
                this.registrationStatus = registrationStatus;
                return this;
            }
            throw new NullPointerException("Null registrationStatus");
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setAuthToken(String authToken) {
            this.authToken = authToken;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setExpiresInSecs(long expiresInSecs) {
            this.expiresInSecs = Long.valueOf(expiresInSecs);
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setTokenCreationEpochInSecs(long tokenCreationEpochInSecs) {
            this.tokenCreationEpochInSecs = Long.valueOf(tokenCreationEpochInSecs);
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry.Builder setFisError(String fisError) {
            this.fisError = fisError;
            return this;
        }

        @Override // com.google.firebase.installations.local.PersistedInstallationEntry.Builder
        public PersistedInstallationEntry build() {
            String missing = "";
            if (this.registrationStatus == null) {
                missing = missing + " registrationStatus";
            }
            if (this.expiresInSecs == null) {
                missing = missing + " expiresInSecs";
            }
            if (this.tokenCreationEpochInSecs == null) {
                missing = missing + " tokenCreationEpochInSecs";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PersistedInstallationEntry(this.firebaseInstallationId, this.registrationStatus, this.authToken, this.refreshToken, this.expiresInSecs.longValue(), this.tokenCreationEpochInSecs.longValue(), this.fisError);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
