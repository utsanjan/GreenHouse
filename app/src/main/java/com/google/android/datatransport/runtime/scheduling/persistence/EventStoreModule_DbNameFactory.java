package com.google.android.datatransport.runtime.scheduling.persistence;

import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* loaded from: classes.dex */
public final class EventStoreModule_DbNameFactory implements Factory<String> {
    private static final EventStoreModule_DbNameFactory INSTANCE = new EventStoreModule_DbNameFactory();

    @Override // javax.inject.Provider
    public String get() {
        return dbName();
    }

    public static EventStoreModule_DbNameFactory create() {
        return INSTANCE;
    }

    public static String dbName() {
        return (String) Preconditions.checkNotNull(EventStoreModule.dbName(), "Cannot return null from a non-@Nullable @Provides method");
    }
}
