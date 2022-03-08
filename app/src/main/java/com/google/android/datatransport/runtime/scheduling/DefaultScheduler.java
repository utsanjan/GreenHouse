package com.google.android.datatransport.runtime.scheduling;

import com.google.android.datatransport.TransportScheduleCallback;
import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.TransportRuntime;
import com.google.android.datatransport.runtime.backends.BackendRegistry;
import com.google.android.datatransport.runtime.backends.TransportBackend;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import java.util.logging.Logger;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class DefaultScheduler implements Scheduler {
    private static final Logger LOGGER = Logger.getLogger(TransportRuntime.class.getName());
    private final BackendRegistry backendRegistry;
    private final EventStore eventStore;
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler workScheduler;

    @Inject
    public DefaultScheduler(Executor executor, BackendRegistry backendRegistry, WorkScheduler workScheduler, EventStore eventStore, SynchronizationGuard guard) {
        this.executor = executor;
        this.backendRegistry = backendRegistry;
        this.workScheduler = workScheduler;
        this.eventStore = eventStore;
        this.guard = guard;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.Scheduler
    public void schedule(TransportContext transportContext, EventInternal event, TransportScheduleCallback callback) {
        this.executor.execute(DefaultScheduler$$Lambda$1.lambdaFactory$(this, transportContext, callback, event));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$schedule$1(DefaultScheduler defaultScheduler, TransportContext transportContext, TransportScheduleCallback callback, EventInternal event) {
        try {
            TransportBackend transportBackend = defaultScheduler.backendRegistry.get(transportContext.getBackendName());
            if (transportBackend == null) {
                String errorMsg = String.format("Transport backend '%s' is not registered", transportContext.getBackendName());
                LOGGER.warning(errorMsg);
                callback.onSchedule(new IllegalArgumentException(errorMsg));
                return;
            }
            EventInternal decoratedEvent = transportBackend.decorate(event);
            defaultScheduler.guard.runCriticalSection(DefaultScheduler$$Lambda$2.lambdaFactory$(defaultScheduler, transportContext, decoratedEvent));
            callback.onSchedule(null);
        } catch (Exception e) {
            Logger logger = LOGGER;
            logger.warning("Error scheduling event " + e.getMessage());
            callback.onSchedule(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$schedule$0(DefaultScheduler defaultScheduler, TransportContext transportContext, EventInternal decoratedEvent) {
        defaultScheduler.eventStore.persist(transportContext, decoratedEvent);
        defaultScheduler.workScheduler.schedule(transportContext, 1);
        return null;
    }
}
