package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.job.JobInfo;
import com.google.android.datatransport.Priority;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AutoValue_SchedulerConfig_ConfigValue;
import com.google.android.datatransport.runtime.time.Clock;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public abstract class SchedulerConfig {
    private static final long ONE_SECOND = 1000;
    private static final long THIRTY_SECONDS = 30000;
    private static final long TWENTY_FOUR_HOURS = 86400000;

    /* loaded from: classes.dex */
    public enum Flag {
        NETWORK_UNMETERED,
        DEVICE_IDLE,
        DEVICE_CHARGING
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Clock getClock();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Map<Priority, ConfigValue> getValues();

    /* loaded from: classes.dex */
    public static abstract class ConfigValue {

        /* loaded from: classes.dex */
        public static abstract class Builder {
            public abstract ConfigValue build();

            public abstract Builder setDelta(long j);

            public abstract Builder setFlags(Set<Flag> set);

            public abstract Builder setMaxAllowedDelay(long j);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long getDelta();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract Set<Flag> getFlags();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract long getMaxAllowedDelay();

        public static Builder builder() {
            return new AutoValue_SchedulerConfig_ConfigValue.Builder().setFlags(Collections.emptySet());
        }
    }

    public static SchedulerConfig getDefault(Clock clock) {
        return builder().addConfig(Priority.DEFAULT, ConfigValue.builder().setDelta(THIRTY_SECONDS).setMaxAllowedDelay(TWENTY_FOUR_HOURS).build()).addConfig(Priority.HIGHEST, ConfigValue.builder().setDelta(ONE_SECOND).setMaxAllowedDelay(TWENTY_FOUR_HOURS).build()).addConfig(Priority.VERY_LOW, ConfigValue.builder().setDelta(TWENTY_FOUR_HOURS).setMaxAllowedDelay(TWENTY_FOUR_HOURS).setFlags(immutableSetOf(Flag.NETWORK_UNMETERED, Flag.DEVICE_IDLE)).build()).setClock(clock).build();
    }

    public static Builder builder() {
        return new Builder();
    }

    static SchedulerConfig create(Clock clock, Map<Priority, ConfigValue> values) {
        return new AutoValue_SchedulerConfig(clock, values);
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Clock clock;
        private Map<Priority, ConfigValue> values = new HashMap();

        public Builder setClock(Clock clock) {
            this.clock = clock;
            return this;
        }

        public Builder addConfig(Priority priority, ConfigValue value) {
            this.values.put(priority, value);
            return this;
        }

        public SchedulerConfig build() {
            if (this.clock == null) {
                throw new NullPointerException("missing required property: clock");
            } else if (this.values.keySet().size() >= Priority.values().length) {
                Map<Priority, ConfigValue> values = this.values;
                this.values = new HashMap();
                return SchedulerConfig.create(this.clock, values);
            } else {
                throw new IllegalStateException("Not all priorities have been configured");
            }
        }
    }

    public long getScheduleDelay(Priority priority, long minTimestamp, int attemptNumber) {
        long timeDiff = minTimestamp - getClock().getTime();
        ConfigValue config = getValues().get(priority);
        long delay = Math.max(((long) Math.pow(2.0d, attemptNumber - 1)) * config.getDelta(), timeDiff);
        return Math.min(delay, config.getMaxAllowedDelay());
    }

    public JobInfo.Builder configureJob(JobInfo.Builder builder, Priority priority, long minimumTimestamp, int attemptNumber) {
        long latency = getScheduleDelay(priority, minimumTimestamp, attemptNumber);
        builder.setMinimumLatency(latency);
        populateFlags(builder, getValues().get(priority).getFlags());
        return builder;
    }

    private void populateFlags(JobInfo.Builder builder, Set<Flag> flags) {
        if (flags.contains(Flag.NETWORK_UNMETERED)) {
            builder.setRequiredNetworkType(2);
        } else {
            builder.setRequiredNetworkType(1);
        }
        if (flags.contains(Flag.DEVICE_CHARGING)) {
            builder.setRequiresCharging(true);
        }
        if (flags.contains(Flag.DEVICE_IDLE)) {
            builder.setRequiresDeviceIdle(true);
        }
    }

    public Set<Flag> getFlags(Priority priority) {
        return getValues().get(priority).getFlags();
    }

    private static <T> Set<T> immutableSetOf(T... values) {
        return Collections.unmodifiableSet(new HashSet(Arrays.asList(values)));
    }
}
