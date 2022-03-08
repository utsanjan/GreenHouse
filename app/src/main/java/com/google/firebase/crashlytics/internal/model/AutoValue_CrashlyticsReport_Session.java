package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.annotations.Encodable;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session extends CrashlyticsReport.Session {
    private final CrashlyticsReport.Session.Application app;
    private final boolean crashed;
    private final CrashlyticsReport.Session.Device device;
    private final Long endedAt;
    private final ImmutableList<CrashlyticsReport.Session.Event> events;
    private final String generator;
    private final int generatorType;
    private final String identifier;
    private final CrashlyticsReport.Session.OperatingSystem os;
    private final long startedAt;
    private final CrashlyticsReport.Session.User user;

    private AutoValue_CrashlyticsReport_Session(String generator, String identifier, long startedAt, Long endedAt, boolean crashed, CrashlyticsReport.Session.Application app, CrashlyticsReport.Session.User user, CrashlyticsReport.Session.OperatingSystem os, CrashlyticsReport.Session.Device device, ImmutableList<CrashlyticsReport.Session.Event> events, int generatorType) {
        this.generator = generator;
        this.identifier = identifier;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.crashed = crashed;
        this.app = app;
        this.user = user;
        this.os = os;
        this.device = device;
        this.events = events;
        this.generatorType = generatorType;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public String getGenerator() {
        return this.generator;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    @Encodable.Ignore
    public String getIdentifier() {
        return this.identifier;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public long getStartedAt() {
        return this.startedAt;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public Long getEndedAt() {
        return this.endedAt;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public boolean isCrashed() {
        return this.crashed;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.Application getApp() {
        return this.app;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.User getUser() {
        return this.user;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.OperatingSystem getOs() {
        return this.os;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.Device getDevice() {
        return this.device;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public ImmutableList<CrashlyticsReport.Session.Event> getEvents() {
        return this.events;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public int getGeneratorType() {
        return this.generatorType;
    }

    public String toString() {
        return "Session{generator=" + this.generator + ", identifier=" + this.identifier + ", startedAt=" + this.startedAt + ", endedAt=" + this.endedAt + ", crashed=" + this.crashed + ", app=" + this.app + ", user=" + this.user + ", os=" + this.os + ", device=" + this.device + ", events=" + this.events + ", generatorType=" + this.generatorType + "}";
    }

    public boolean equals(Object o) {
        Long l;
        CrashlyticsReport.Session.User user;
        CrashlyticsReport.Session.OperatingSystem operatingSystem;
        CrashlyticsReport.Session.Device device;
        ImmutableList<CrashlyticsReport.Session.Event> immutableList;
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReport.Session)) {
            return false;
        }
        CrashlyticsReport.Session that = (CrashlyticsReport.Session) o;
        return this.generator.equals(that.getGenerator()) && this.identifier.equals(that.getIdentifier()) && this.startedAt == that.getStartedAt() && ((l = this.endedAt) != null ? l.equals(that.getEndedAt()) : that.getEndedAt() == null) && this.crashed == that.isCrashed() && this.app.equals(that.getApp()) && ((user = this.user) != null ? user.equals(that.getUser()) : that.getUser() == null) && ((operatingSystem = this.os) != null ? operatingSystem.equals(that.getOs()) : that.getOs() == null) && ((device = this.device) != null ? device.equals(that.getDevice()) : that.getDevice() == null) && ((immutableList = this.events) != null ? immutableList.equals(that.getEvents()) : that.getEvents() == null) && this.generatorType == that.getGeneratorType();
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.startedAt;
        int h$2 = (((((h$ ^ this.generator.hashCode()) * 1000003) ^ this.identifier.hashCode()) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        Long l = this.endedAt;
        int i = 0;
        int h$3 = (((((h$2 ^ (l == null ? 0 : l.hashCode())) * 1000003) ^ (this.crashed ? 1231 : 1237)) * 1000003) ^ this.app.hashCode()) * 1000003;
        CrashlyticsReport.Session.User user = this.user;
        int h$4 = (h$3 ^ (user == null ? 0 : user.hashCode())) * 1000003;
        CrashlyticsReport.Session.OperatingSystem operatingSystem = this.os;
        int h$5 = (h$4 ^ (operatingSystem == null ? 0 : operatingSystem.hashCode())) * 1000003;
        CrashlyticsReport.Session.Device device = this.device;
        int h$6 = (h$5 ^ (device == null ? 0 : device.hashCode())) * 1000003;
        ImmutableList<CrashlyticsReport.Session.Event> immutableList = this.events;
        if (immutableList != null) {
            i = immutableList.hashCode();
        }
        return ((h$6 ^ i) * 1000003) ^ this.generatorType;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session
    public CrashlyticsReport.Session.Builder toBuilder() {
        return new Builder(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends CrashlyticsReport.Session.Builder {
        private CrashlyticsReport.Session.Application app;
        private Boolean crashed;
        private CrashlyticsReport.Session.Device device;
        private Long endedAt;
        private ImmutableList<CrashlyticsReport.Session.Event> events;
        private String generator;
        private Integer generatorType;
        private String identifier;
        private CrashlyticsReport.Session.OperatingSystem os;
        private Long startedAt;
        private CrashlyticsReport.Session.User user;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder() {
        }

        private Builder(CrashlyticsReport.Session source) {
            this.generator = source.getGenerator();
            this.identifier = source.getIdentifier();
            this.startedAt = Long.valueOf(source.getStartedAt());
            this.endedAt = source.getEndedAt();
            this.crashed = Boolean.valueOf(source.isCrashed());
            this.app = source.getApp();
            this.user = source.getUser();
            this.os = source.getOs();
            this.device = source.getDevice();
            this.events = source.getEvents();
            this.generatorType = Integer.valueOf(source.getGeneratorType());
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setGenerator(String generator) {
            if (generator != null) {
                this.generator = generator;
                return this;
            }
            throw new NullPointerException("Null generator");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setIdentifier(String identifier) {
            if (identifier != null) {
                this.identifier = identifier;
                return this;
            }
            throw new NullPointerException("Null identifier");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setStartedAt(long startedAt) {
            this.startedAt = Long.valueOf(startedAt);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setEndedAt(Long endedAt) {
            this.endedAt = endedAt;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setCrashed(boolean crashed) {
            this.crashed = Boolean.valueOf(crashed);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setApp(CrashlyticsReport.Session.Application app) {
            if (app != null) {
                this.app = app;
                return this;
            }
            throw new NullPointerException("Null app");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setUser(CrashlyticsReport.Session.User user) {
            this.user = user;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setOs(CrashlyticsReport.Session.OperatingSystem os) {
            this.os = os;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setDevice(CrashlyticsReport.Session.Device device) {
            this.device = device;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setEvents(ImmutableList<CrashlyticsReport.Session.Event> events) {
            this.events = events;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session.Builder setGeneratorType(int generatorType) {
            this.generatorType = Integer.valueOf(generatorType);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Builder
        public CrashlyticsReport.Session build() {
            String missing = "";
            if (this.generator == null) {
                missing = missing + " generator";
            }
            if (this.identifier == null) {
                missing = missing + " identifier";
            }
            if (this.startedAt == null) {
                missing = missing + " startedAt";
            }
            if (this.crashed == null) {
                missing = missing + " crashed";
            }
            if (this.app == null) {
                missing = missing + " app";
            }
            if (this.generatorType == null) {
                missing = missing + " generatorType";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session(this.generator, this.identifier, this.startedAt.longValue(), this.endedAt, this.crashed.booleanValue(), this.app, this.user, this.os, this.device, this.events, this.generatorType.intValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
