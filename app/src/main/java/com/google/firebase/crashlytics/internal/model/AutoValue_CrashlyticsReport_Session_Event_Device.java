package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Device extends CrashlyticsReport.Session.Event.Device {
    private final Double batteryLevel;
    private final int batteryVelocity;
    private final long diskUsed;
    private final int orientation;
    private final boolean proximityOn;
    private final long ramUsed;

    private AutoValue_CrashlyticsReport_Session_Event_Device(Double batteryLevel, int batteryVelocity, boolean proximityOn, int orientation, long ramUsed, long diskUsed) {
        this.batteryLevel = batteryLevel;
        this.batteryVelocity = batteryVelocity;
        this.proximityOn = proximityOn;
        this.orientation = orientation;
        this.ramUsed = ramUsed;
        this.diskUsed = diskUsed;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public Double getBatteryLevel() {
        return this.batteryLevel;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public int getBatteryVelocity() {
        return this.batteryVelocity;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public boolean isProximityOn() {
        return this.proximityOn;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public int getOrientation() {
        return this.orientation;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public long getRamUsed() {
        return this.ramUsed;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device
    public long getDiskUsed() {
        return this.diskUsed;
    }

    public String toString() {
        return "Device{batteryLevel=" + this.batteryLevel + ", batteryVelocity=" + this.batteryVelocity + ", proximityOn=" + this.proximityOn + ", orientation=" + this.orientation + ", ramUsed=" + this.ramUsed + ", diskUsed=" + this.diskUsed + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReport.Session.Event.Device)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Device that = (CrashlyticsReport.Session.Event.Device) o;
        Double d = this.batteryLevel;
        if (d != null ? d.equals(that.getBatteryLevel()) : that.getBatteryLevel() == null) {
            if (this.batteryVelocity == that.getBatteryVelocity() && this.proximityOn == that.isProximityOn() && this.orientation == that.getOrientation() && this.ramUsed == that.getRamUsed() && this.diskUsed == that.getDiskUsed()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        Double d = this.batteryLevel;
        int h$2 = (((h$ ^ (d == null ? 0 : d.hashCode())) * 1000003) ^ this.batteryVelocity) * 1000003;
        int i = this.proximityOn ? 1231 : 1237;
        long j = this.ramUsed;
        long j2 = this.diskUsed;
        return ((((((h$2 ^ i) * 1000003) ^ this.orientation) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Device.Builder {
        private Double batteryLevel;
        private Integer batteryVelocity;
        private Long diskUsed;
        private Integer orientation;
        private Boolean proximityOn;
        private Long ramUsed;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setBatteryLevel(Double batteryLevel) {
            this.batteryLevel = batteryLevel;
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setBatteryVelocity(int batteryVelocity) {
            this.batteryVelocity = Integer.valueOf(batteryVelocity);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setProximityOn(boolean proximityOn) {
            this.proximityOn = Boolean.valueOf(proximityOn);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setOrientation(int orientation) {
            this.orientation = Integer.valueOf(orientation);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setRamUsed(long ramUsed) {
            this.ramUsed = Long.valueOf(ramUsed);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device.Builder setDiskUsed(long diskUsed) {
            this.diskUsed = Long.valueOf(diskUsed);
            return this;
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Device.Builder
        public CrashlyticsReport.Session.Event.Device build() {
            String missing = "";
            if (this.batteryVelocity == null) {
                missing = missing + " batteryVelocity";
            }
            if (this.proximityOn == null) {
                missing = missing + " proximityOn";
            }
            if (this.orientation == null) {
                missing = missing + " orientation";
            }
            if (this.ramUsed == null) {
                missing = missing + " ramUsed";
            }
            if (this.diskUsed == null) {
                missing = missing + " diskUsed";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Event_Device(this.batteryLevel, this.batteryVelocity.intValue(), this.proximityOn.booleanValue(), this.orientation.intValue(), this.ramUsed.longValue(), this.diskUsed.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
