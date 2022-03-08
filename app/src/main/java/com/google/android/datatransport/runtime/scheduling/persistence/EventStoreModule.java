package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.synchronization.SynchronizationGuard;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
/* loaded from: classes.dex */
public abstract class EventStoreModule {
    @Binds
    abstract EventStore eventStore(SQLiteEventStore sQLiteEventStore);

    @Binds
    abstract SynchronizationGuard synchronizationGuard(SQLiteEventStore sQLiteEventStore);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static EventStoreConfig storeConfig() {
        return EventStoreConfig.DEFAULT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Named("SCHEMA_VERSION")
    public static int schemaVersion() {
        return SchemaManager.SCHEMA_VERSION;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Named("SQLITE_DB_NAME")
    public static String dbName() {
        return "com.google.android.datatransport.events";
    }
}
