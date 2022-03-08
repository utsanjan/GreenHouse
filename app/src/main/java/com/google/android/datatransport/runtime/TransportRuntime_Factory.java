package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.scheduling.Scheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.Uploader;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkInitializer;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class TransportRuntime_Factory implements Factory<TransportRuntime> {
    private final Provider<Clock> eventClockProvider;
    private final Provider<WorkInitializer> initializerProvider;
    private final Provider<Scheduler> schedulerProvider;
    private final Provider<Uploader> uploaderProvider;
    private final Provider<Clock> uptimeClockProvider;

    public TransportRuntime_Factory(Provider<Clock> eventClockProvider, Provider<Clock> uptimeClockProvider, Provider<Scheduler> schedulerProvider, Provider<Uploader> uploaderProvider, Provider<WorkInitializer> initializerProvider) {
        this.eventClockProvider = eventClockProvider;
        this.uptimeClockProvider = uptimeClockProvider;
        this.schedulerProvider = schedulerProvider;
        this.uploaderProvider = uploaderProvider;
        this.initializerProvider = initializerProvider;
    }

    @Override // javax.inject.Provider
    public TransportRuntime get() {
        return new TransportRuntime(this.eventClockProvider.get(), this.uptimeClockProvider.get(), this.schedulerProvider.get(), this.uploaderProvider.get(), this.initializerProvider.get());
    }

    public static TransportRuntime_Factory create(Provider<Clock> eventClockProvider, Provider<Clock> uptimeClockProvider, Provider<Scheduler> schedulerProvider, Provider<Uploader> uploaderProvider, Provider<WorkInitializer> initializerProvider) {
        return new TransportRuntime_Factory(eventClockProvider, uptimeClockProvider, schedulerProvider, uploaderProvider, initializerProvider);
    }

    public static TransportRuntime newInstance(Clock eventClock, Clock uptimeClock, Scheduler scheduler, Uploader uploader, WorkInitializer initializer) {
        return new TransportRuntime(eventClock, uptimeClock, scheduler, uploader, initializer);
    }
}
