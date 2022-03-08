package com.google.firebase.installations.local;

import com.google.firebase.installations.local.AutoValue_PersistedInstallationEntry;
import com.google.firebase.installations.local.PersistedInstallation;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
public abstract class PersistedInstallationEntry {
    public static PersistedInstallationEntry INSTANCE = builder().build();

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    /* loaded from: classes.dex */
    public static abstract class Builder {
        public abstract PersistedInstallationEntry build();

        public abstract Builder setAuthToken(String str);

        public abstract Builder setExpiresInSecs(long j);

        public abstract Builder setFirebaseInstallationId(String str);

        public abstract Builder setFisError(String str);

        public abstract Builder setRefreshToken(String str);

        public abstract Builder setRegistrationStatus(PersistedInstallation.RegistrationStatus registrationStatus);

        public abstract Builder setTokenCreationEpochInSecs(long j);
    }

    public abstract String getAuthToken();

    public abstract long getExpiresInSecs();

    public abstract String getFirebaseInstallationId();

    public abstract String getFisError();

    public abstract String getRefreshToken();

    public abstract PersistedInstallation.RegistrationStatus getRegistrationStatus();

    public abstract long getTokenCreationEpochInSecs();

    public abstract Builder toBuilder();

    public boolean isRegistered() {
        return getRegistrationStatus() == PersistedInstallation.RegistrationStatus.REGISTERED;
    }

    public boolean isErrored() {
        return getRegistrationStatus() == PersistedInstallation.RegistrationStatus.REGISTER_ERROR;
    }

    public boolean isUnregistered() {
        return getRegistrationStatus() == PersistedInstallation.RegistrationStatus.UNREGISTERED;
    }

    public boolean isNotGenerated() {
        return getRegistrationStatus() == PersistedInstallation.RegistrationStatus.NOT_GENERATED || getRegistrationStatus() == PersistedInstallation.RegistrationStatus.ATTEMPT_MIGRATION;
    }

    public boolean shouldAttemptMigration() {
        return getRegistrationStatus() == PersistedInstallation.RegistrationStatus.ATTEMPT_MIGRATION;
    }

    public PersistedInstallationEntry withUnregisteredFid(String fid) {
        return toBuilder().setFirebaseInstallationId(fid).setRegistrationStatus(PersistedInstallation.RegistrationStatus.UNREGISTERED).build();
    }

    public PersistedInstallationEntry withRegisteredFid(String fid, String refreshToken, long creationTime, String authToken, long authTokenExpiration) {
        return toBuilder().setFirebaseInstallationId(fid).setRegistrationStatus(PersistedInstallation.RegistrationStatus.REGISTERED).setAuthToken(authToken).setRefreshToken(refreshToken).setExpiresInSecs(authTokenExpiration).setTokenCreationEpochInSecs(creationTime).build();
    }

    public PersistedInstallationEntry withFisError(String message) {
        return toBuilder().setFisError(message).setRegistrationStatus(PersistedInstallation.RegistrationStatus.REGISTER_ERROR).build();
    }

    public PersistedInstallationEntry withNoGeneratedFid() {
        return toBuilder().setRegistrationStatus(PersistedInstallation.RegistrationStatus.NOT_GENERATED).build();
    }

    public PersistedInstallationEntry withAuthToken(String authToken, long authTokenExpiration, long creationTime) {
        return toBuilder().setAuthToken(authToken).setExpiresInSecs(authTokenExpiration).setTokenCreationEpochInSecs(creationTime).build();
    }

    public PersistedInstallationEntry withClearedAuthToken() {
        return toBuilder().setAuthToken(null).build();
    }

    public static Builder builder() {
        return new AutoValue_PersistedInstallationEntry.Builder().setTokenCreationEpochInSecs(0L).setRegistrationStatus(PersistedInstallation.RegistrationStatus.ATTEMPT_MIGRATION).setExpiresInSecs(0L);
    }
}
