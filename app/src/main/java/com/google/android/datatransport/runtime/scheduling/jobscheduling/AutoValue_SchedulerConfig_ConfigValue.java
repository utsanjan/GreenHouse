package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import java.util.Set;

/* loaded from: classes.dex */
final class AutoValue_SchedulerConfig_ConfigValue extends SchedulerConfig.ConfigValue {
    private final long delta;
    private final Set<SchedulerConfig.Flag> flags;
    private final long maxAllowedDelay;

    private AutoValue_SchedulerConfig_ConfigValue(long delta, long maxAllowedDelay, Set<SchedulerConfig.Flag> flags) {
        this.delta = delta;
        this.maxAllowedDelay = maxAllowedDelay;
        this.flags = flags;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    long getDelta() {
        return this.delta;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    long getMaxAllowedDelay() {
        return this.maxAllowedDelay;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue
    Set<SchedulerConfig.Flag> getFlags() {
        return this.flags;
    }

    public String toString() {
        return "ConfigValue{delta=" + this.delta + ", maxAllowedDelay=" + this.maxAllowedDelay + ", flags=" + this.flags + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SchedulerConfig.ConfigValue)) {
            return false;
        }
        SchedulerConfig.ConfigValue that = (SchedulerConfig.ConfigValue) o;
        return this.delta == that.getDelta() && this.maxAllowedDelay == that.getMaxAllowedDelay() && this.flags.equals(that.getFlags());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        long j = this.delta;
        long j2 = this.maxAllowedDelay;
        return ((((h$ ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.flags.hashCode();
    }

    /* loaded from: classes.dex */
    static final class Builder extends SchedulerConfig.ConfigValue.Builder {
        private Long delta;
        private Set<SchedulerConfig.Flag> flags;
        private Long maxAllowedDelay;

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder setDelta(long delta) {
            this.delta = Long.valueOf(delta);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder setMaxAllowedDelay(long maxAllowedDelay) {
            this.maxAllowedDelay = Long.valueOf(maxAllowedDelay);
            return this;
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue.Builder setFlags(Set<SchedulerConfig.Flag> flags) {
            if (flags != null) {
                this.flags = flags;
                return this;
            }
            throw new NullPointerException("Null flags");
        }

        @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig.ConfigValue.Builder
        public SchedulerConfig.ConfigValue build() {
            String missing = "";
            if (this.delta == null) {
                missing = missing + " delta";
            }
            if (this.maxAllowedDelay == null) {
                missing = missing + " maxAllowedDelay";
            }
            if (this.flags == null) {
                missing = missing + " flags";
            }
            if (missing.isEmpty()) {
                return new AutoValue_SchedulerConfig_ConfigValue(this.delta.longValue(), this.maxAllowedDelay.longValue(), this.flags);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
