package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class WorkInitializer_Factory implements Factory<WorkInitializer> {
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<WorkScheduler> schedulerProvider;
    private final Provider<EventStore> storeProvider;

    public WorkInitializer_Factory(Provider<Executor> executorProvider, Provider<EventStore> storeProvider, Provider<WorkScheduler> schedulerProvider, Provider<SynchronizationGuard> guardProvider) {
        this.executorProvider = executorProvider;
        this.storeProvider = storeProvider;
        this.schedulerProvider = schedulerProvider;
        this.guardProvider = guardProvider;
    }

    @Override // javax.inject.Provider
    public WorkInitializer get() {
        return new WorkInitializer(this.executorProvider.get(), this.storeProvider.get(), this.schedulerProvider.get(), this.guardProvider.get());
    }

    public static WorkInitializer_Factory create(Provider<Executor> executorProvider, Provider<EventStore> storeProvider, Provider<WorkScheduler> schedulerProvider, Provider<SynchronizationGuard> guardProvider) {
        return new WorkInitializer_Factory(executorProvider, storeProvider, schedulerProvider, guardProvider);
    }

    public static WorkInitializer newInstance(Executor executor, EventStore store, WorkScheduler scheduler, SynchronizationGuard guard) {
        return new WorkInitializer(executor, store, scheduler, guard);
    }
}
