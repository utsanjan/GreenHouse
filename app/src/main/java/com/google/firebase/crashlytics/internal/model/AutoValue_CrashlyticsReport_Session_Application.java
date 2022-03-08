package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Application extends CrashlyticsReport.Session.Application {
    private final String displayVersion;
    private final String identifier;
    private final String installationUuid;
    private final CrashlyticsReport.Session.Application.Organization organization;
    private final String version;

    private AutoValue_CrashlyticsReport_Session_Application(String identifier, String version, String displayVersion, CrashlyticsReport.Session.Application.Organization organization, String installationUuid) {
        this.identifier = identifier;
        this.version = version;
        this.displayVersion = displayVersion;
        this.organization = organization;
        this.installationUuid = installationUuid;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getVersion() {
        return this.version;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getDisplayVersion() {
        return this.displayVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public CrashlyticsReport.Session.Application.Organization getOrganization() {
        return this.organization;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    public String getInstallationUuid() {
        return this.installationUuid;
    }

    public String toString() {
        return "Application{identifier=" + this.identifier + ", version=" + this.version + ", displayVersion=" + this.displayVersion + ", organization=" + this.organization + ", installationUuid=" + this.installationUuid + "}";
    }

    public boolean equals(Object o) {
        String str;
        CrashlyticsReport.Session.Application.Organization organization;
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReport.Session.Application)) {
            return false;
        }
        CrashlyticsReport.Session.Application that = (CrashlyticsReport.Session.Application) o;
        if (this.identifier.equals(that.getIdentifier()) && this.version.equals(that.getVersion()) && ((str = this.displayVersion) != null ? str.equals(that.getDisplayVersion()) : that.getDisplayVersion() == null) && ((organization = this.organization) != null ? organization.equals(that.getOrganization()) : that.getOrganization() == null)) {
            String str2 = this.installationUuid;
            if (str2 == null) {
                if (that.getInstallationUuid() == null) {
                    return true;
                }
            } else if (str2.equals(that.getInstallationUuid())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        int h$2 = (((h$ ^ this.identifier.hashCode()) * 1000003) ^ this.version.hashCode()) * 1000003;
        String str = this.displayVersion;
        int i = 0;
        int h$3 = (h$2 ^ (str == null ? 0 : str.hashCode())) * 1000003;
        CrashlyticsReport.Session.Application.Organization organization = this.organization;
        int h$4 = (h$3 ^ (organization == null ? 0 : organization.hashCode())) * 1000003;
        String str2 = this.installationUuid;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return h$4 ^ i;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application
    protected CrashlyticsReport.Session.Application.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends CrashlyticsReport.Session.Application.Builder {
        private String displayVersion;
        private String identifier;
        private String installationUuid;
        private CrashlyticsReport.Session.Application.Organization organization;
        private String version;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(CrashlyticsReport.Session.Application source) {
            this.identifier = source.getIdentifier();
            this.version = source.getVersion();
            this.displayVersion = source.getDisplayVersion();
            this.organization = source.getOrganization();
            this.installationUuid = source.getInstallationUuid();
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setIdentifier(String identifier) {
            if (identifier != null) {
                this.identifier = identifier;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setVersion(String version) {
            if (version != null) {
                this.version = version;
                return this;
            }
            throw new NullPointerException("Null version");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setDisplayVersion(String displayVersion) {
            this.displayVersion = displayVersion;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setOrganization(CrashlyticsReport.Session.Application.Organization organization) {
            this.organization = organization;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application.Builder setInstallationUuid(String installationUuid) {
            this.installationUuid = installationUuid;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Application.Builder
        public CrashlyticsReport.Session.Application build() {
            String missing = "";
            if (this.identifier == null) {
                missing = missing + " identifier";
            }
            if (this.version == null) {
                missing = missing + " version";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Application(this.identifier, this.version, this.displayVersion, this.organization, this.installationUuid);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
