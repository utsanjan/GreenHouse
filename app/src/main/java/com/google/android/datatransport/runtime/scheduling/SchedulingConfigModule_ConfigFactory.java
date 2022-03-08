package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchedulingConfigModule_ConfigFactory implements Factory<SchedulerConfig> {
    private final Provider<Clock> clockProvider;

    public SchedulingConfigModule_ConfigFactory(Provider<Clock> clockProvider) {
        this.clockProvider = clockProvider;
    }

    @Override // javax.inject.Provider
    public SchedulerConfig get() {
        return config(this.clockProvider.get());
    }

    public static SchedulingConfigModule_ConfigFactory create(Provider<Clock> clockProvider) {
        return new SchedulingConfigModule_ConfigFactory(clockProvider);
    }

    public static SchedulerConfig config(Clock clock) {
        return (SchedulerConfig) Preconditions.checkNotNull(SchedulingConfigModule.config(clock), "Cannot return null from a non-@Nullable @Provides method");
    }
}
