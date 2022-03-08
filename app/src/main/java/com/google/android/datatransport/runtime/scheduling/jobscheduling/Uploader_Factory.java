package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class Uploader_Factory implements Factory<Uploader> {
    private final Provider<BackendRegistry> backendRegistryProvider;
    private final Provider<Clock> clockProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EventStore> eventStoreProvider;
    private final Provider<Executor> executorProvider;
    private final Provider<SynchronizationGuard> guardProvider;
    private final Provider<WorkScheduler> workSchedulerProvider;

    public Uploader_Factory(Provider<Context> contextProvider, Provider<BackendRegistry> backendRegistryProvider, Provider<EventStore> eventStoreProvider, Provider<WorkScheduler> workSchedulerProvider, Provider<Executor> executorProvider, Provider<SynchronizationGuard> guardProvider, Provider<Clock> clockProvider) {
        this.contextProvider = contextProvider;
        this.backendRegistryProvider = backendRegistryProvider;
        this.eventStoreProvider = eventStoreProvider;
        this.workSchedulerProvider = workSchedulerProvider;
        this.executorProvider = executorProvider;
        this.guardProvider = guardProvider;
        this.clockProvider = clockProvider;
    }

    @Override // javax.inject.Provider
    public Uploader get() {
        return new Uploader(this.contextProvider.get(), this.backendRegistryProvider.get(), this.eventStoreProvider.get(), this.workSchedulerProvider.get(), this.executorProvider.get(), this.guardProvider.get(), this.clockProvider.get());
    }

    public static Uploader_Factory create(Provider<Context> contextProvider, Provider<BackendRegistry> backendRegistryProvider, Provider<EventStore> eventStoreProvider, Provider<WorkScheduler> workSchedulerProvider, Provider<Executor> executorProvider, Provider<SynchronizationGuard> guardProvider, Provider<Clock> clockProvider) {
        return new Uploader_Factory(contextProvider, backendRegistryProvider, eventStoreProvider, workSchedulerProvider, executorProvider, guardProvider, clockProvider);
    }

    public static Uploader newInstance(Context context, BackendRegistry backendRegistry, EventStore eventStore, WorkScheduler workScheduler, Executor executor, SynchronizationGuard guard, Clock clock) {
        return new Uploader(context, backendRegistry, eventStore, workScheduler, executor, guard, clock);
    }
}
