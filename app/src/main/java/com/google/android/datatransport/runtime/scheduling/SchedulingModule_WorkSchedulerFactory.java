package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchedulingModule_WorkSchedulerFactory implements Factory<WorkScheduler> {
    private final Provider<Clock> clockProvider;
    private final Provider<SchedulerConfig> configProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventStore> eventStoreProvider;

    public SchedulingModule_WorkSchedulerFactory(Provider<Context> contextProvider, Provider<EventStore> eventStoreProvider, Provider<SchedulerConfig> configProvider, Provider<Clock> clockProvider) {
        this.contextProvider = contextProvider;
        this.eventStoreProvider = eventStoreProvider;
        this.configProvider = configProvider;
        this.clockProvider = clockProvider;
    }

    @Override // javax.inject.Provider
    public WorkScheduler get() {
        return workScheduler(this.contextProvider.get(), this.eventStoreProvider.get(), this.configProvider.get(), this.clockProvider.get());
    }

    public static SchedulingModule_WorkSchedulerFactory create(Provider<Context> contextProvider, Provider<EventStore> eventStoreProvider, Provider<SchedulerConfig> configProvider, Provider<Clock> clockProvider) {
        return new SchedulingModule_WorkSchedulerFactory(contextProvider, eventStoreProvider, configProvider, clockProvider);
    }

    public static WorkScheduler workScheduler(Context context, EventStore eventStore, SchedulerConfig config, Clock clock) {
        return (WorkScheduler) Preconditions.checkNotNull(SchedulingModule.workScheduler(context, eventStore, config, clock), "Cannot return null from a non-@Nullable @Provides method");
    }
}
