package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import java.util.concurrent.Executor;
import javax.inject.Inject;

/* loaded from: classes.dex */
public class WorkInitializer {
    private final Executor executor;
    private final SynchronizationGuard guard;
    private final WorkScheduler scheduler;
    private final EventStore store;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public WorkInitializer(Executor executor, EventStore store, WorkScheduler scheduler, SynchronizationGuard guard) {
        this.executor = executor;
        this.store = store;
        this.scheduler = scheduler;
        this.guard = guard;
    }

    public void ensureContextsScheduled() {
        this.executor.execute(WorkInitializer$$Lambda$1.lambdaFactory$(this));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$ensureContextsScheduled$1(WorkInitializer workInitializer) {
        workInitializer.guard.runCriticalSection(WorkInitializer$$Lambda$2.lambdaFactory$(workInitializer));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$ensureContextsScheduled$0(WorkInitializer workInitializer) {
        for (TransportContext context : workInitializer.store.loadActiveContexts()) {
            workInitializer.scheduler.schedule(context, 1);
        }
        return null;
    }
}
