package com.google.android.datatransport.runtime;

import android.content.Context;
import com.google.android.datatransport.runtime.backends.BackendRegistryModule;
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule;
import com.google.android.datatransport.runtime.scheduling.SchedulingModule;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStoreModule;
import com.google.android.datatransport.runtime.time.TimeModule;
import dagger.BindsInstance;
import dagger.Component;
import java.io.Closeable;
import java.io.IOException;
import javax.inject.Singleton;

/* JADX INFO: Access modifiers changed from: package-private */
@Component(modules = {BackendRegistryModule.class, EventStoreModule.class, ExecutionModule.class, SchedulingModule.class, SchedulingConfigModule.class, TimeModule.class})
@Singleton
/* loaded from: classes.dex */
public abstract class TransportRuntimeComponent implements Closeable {

    @Component.Builder
    /* loaded from: classes.dex */
    interface Builder {
        TransportRuntimeComponent build();

        @BindsInstance
        Builder setApplicationContext(Context context);
    }

    abstract EventStore getEventStore();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract TransportRuntime getTransportRuntime();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        getEventStore().close();
    }
}
