package com.google.android.datatransport.runtime.scheduling.persistence;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SchemaManager_Factory implements Factory<SchemaManager> {
    private final Provider<Context> contextProvider;
    private final Provider<String> dbNameProvider;
    private final Provider<Integer> schemaVersionProvider;

    public SchemaManager_Factory(Provider<Context> contextProvider, Provider<String> dbNameProvider, Provider<Integer> schemaVersionProvider) {
        this.contextProvider = contextProvider;
        this.dbNameProvider = dbNameProvider;
        this.schemaVersionProvider = schemaVersionProvider;
    }

    @Override // javax.inject.Provider
    public SchemaManager get() {
        return new SchemaManager(this.contextProvider.get(), this.dbNameProvider.get(), this.schemaVersionProvider.get().intValue());
    }

    public static SchemaManager_Factory create(Provider<Context> contextProvider, Provider<String> dbNameProvider, Provider<Integer> schemaVersionProvider) {
        return new SchemaManager_Factory(contextProvider, dbNameProvider, schemaVersionProvider);
    }

    public static SchemaManager newInstance(Context context, String dbName, int schemaVersion) {
        return new SchemaManager(context, dbName, schemaVersion);
    }
}
