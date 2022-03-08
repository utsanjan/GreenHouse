package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport extends CrashlyticsReport {
    private final String buildVersion;
    private final String displayVersion;
    private final String gmpAppId;
    private final String installationUuid;
    private final CrashlyticsReport.FilesPayload ndkPayload;
    private final int platform;
    private final String sdkVersion;
    private final CrashlyticsReport.Session session;

    private AutoValue_CrashlyticsReport(String sdkVersion, String gmpAppId, int platform, String installationUuid, String buildVersion, String displayVersion, CrashlyticsReport.Session session, CrashlyticsReport.FilesPayload ndkPayload) {
        this.sdkVersion = sdkVersion;
        this.gmpAppId = gmpAppId;
        this.platform = platform;
        this.installationUuid = installationUuid;
        this.buildVersion = buildVersion;
        this.displayVersion = displayVersion;
        this.session = session;
        this.ndkPayload = ndkPayload;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getSdkVersion() {
        return this.sdkVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getGmpAppId() {
        return this.gmpAppId;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public int getPlatform() {
        return this.platform;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getInstallationUuid() {
        return this.installationUuid;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getBuildVersion() {
        return this.buildVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public String getDisplayVersion() {
        return this.displayVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public CrashlyticsReport.Session getSession() {
        return this.session;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    public CrashlyticsReport.FilesPayload getNdkPayload() {
        return this.ndkPayload;
    }

    public String toString() {
        return "CrashlyticsReport{sdkVersion=" + this.sdkVersion + ", gmpAppId=" + this.gmpAppId + ", platform=" + this.platform + ", installationUuid=" + this.installationUuid + ", buildVersion=" + this.buildVersion + ", displayVersion=" + this.displayVersion + ", session=" + this.session + ", ndkPayload=" + this.ndkPayload + "}";
    }

    public boolean equals(Object o) {
        CrashlyticsReport.Session session;
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReport)) {
            return false;
        }
        CrashlyticsReport that = (CrashlyticsReport) o;
        if (this.sdkVersion.equals(that.getSdkVersion()) && this.gmpAppId.equals(that.getGmpAppId()) && this.platform == that.getPlatform() && this.installationUuid.equals(that.getInstallationUuid()) && this.buildVersion.equals(that.getBuildVersion()) && this.displayVersion.equals(that.getDisplayVersion()) && ((session = this.session) != null ? session.equals(that.getSession()) : that.getSession() == null)) {
            CrashlyticsReport.FilesPayload filesPayload = this.ndkPayload;
            if (filesPayload == null) {
                if (that.getNdkPayload() == null) {
                    return true;
                }
            } else if (filesPayload.equals(that.getNdkPayload())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        int h$2 = (((((((((((h$ ^ this.sdkVersion.hashCode()) * 1000003) ^ this.gmpAppId.hashCode()) * 1000003) ^ this.platform) * 1000003) ^ this.installationUuid.hashCode()) * 1000003) ^ this.buildVersion.hashCode()) * 1000003) ^ this.displayVersion.hashCode()) * 1000003;
        CrashlyticsReport.Session session = this.session;
        int i = 0;
        int h$3 = (h$2 ^ (session == null ? 0 : session.hashCode())) * 1000003;
        CrashlyticsReport.FilesPayload filesPayload = this.ndkPayload;
        if (filesPayload != null) {
            i = filesPayload.hashCode();
        }
        return h$3 ^ i;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport
    protected CrashlyticsReport.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends CrashlyticsReport.Builder {
        private String buildVersion;
        private String displayVersion;
        private String gmpAppId;
        private String installationUuid;
        private CrashlyticsReport.FilesPayload ndkPayload;
        private Integer platform;
        private String sdkVersion;
        private CrashlyticsReport.Session session;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(CrashlyticsReport source) {
            this.sdkVersion = source.getSdkVersion();
            this.gmpAppId = source.getGmpAppId();
            this.platform = Integer.valueOf(source.getPlatform());
            this.installationUuid = source.getInstallationUuid();
            this.buildVersion = source.getBuildVersion();
            this.displayVersion = source.getDisplayVersion();
            this.session = source.getSession();
            this.ndkPayload = source.getNdkPayload();
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setSdkVersion(String sdkVersion) {
            if (sdkVersion != null) {
                this.sdkVersion = sdkVersion;
                return this;
            }
            throw new NullPointerException("Null sdkVersion");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setGmpAppId(String gmpAppId) {
            if (gmpAppId != null) {
                this.gmpAppId = gmpAppId;
                return this;
            }
            throw new NullPointerException("Null gmpAppId");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setPlatform(int platform) {
            this.platform = Integer.valueOf(platform);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setInstallationUuid(String installationUuid) {
            if (installationUuid != null) {
                this.installationUuid = installationUuid;
                return this;
            }
            throw new NullPointerException("Null installationUuid");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setBuildVersion(String buildVersion) {
            if (buildVersion != null) {
                this.buildVersion = buildVersion;
                return this;
            }
            throw new NullPointerException("Null buildVersion");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setDisplayVersion(String displayVersion) {
            if (displayVersion != null) {
                this.displayVersion = displayVersion;
                return this;
            }
            throw new NullPointerException("Null displayVersion");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setSession(CrashlyticsReport.Session session) {
            this.session = session;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport.Builder setNdkPayload(CrashlyticsReport.FilesPayload ndkPayload) {
            this.ndkPayload = ndkPayload;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Builder
        public CrashlyticsReport build() {
            String missing = "";
            if (this.sdkVersion == null) {
                missing = missing + " sdkVersion";
            }
            if (this.gmpAppId == null) {
                missing = missing + " gmpAppId";
            }
            if (this.platform == null) {
                missing = missing + " platform";
            }
            if (this.installationUuid == null) {
                missing = missing + " installationUuid";
            }
            if (this.buildVersion == null) {
                missing = missing + " buildVersion";
            }
            if (this.displayVersion == null) {
                missing = missing + " displayVersion";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport(this.sdkVersion, this.gmpAppId, this.platform.intValue(), this.installationUuid, this.buildVersion, this.displayVersion, this.session, this.ndkPayload);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
