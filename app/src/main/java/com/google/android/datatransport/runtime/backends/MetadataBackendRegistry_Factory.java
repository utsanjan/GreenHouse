package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class MetadataBackendRegistry_Factory implements Factory<MetadataBackendRegistry> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<CreationContextFactory> creationContextFactoryProvider;

    public MetadataBackendRegistry_Factory(Provider<Context> applicationContextProvider, Provider<CreationContextFactory> creationContextFactoryProvider) {
        this.applicationContextProvider = applicationContextProvider;
        this.creationContextFactoryProvider = creationContextFactoryProvider;
    }

    @Override // javax.inject.Provider
    public MetadataBackendRegistry get() {
        return new MetadataBackendRegistry(this.applicationContextProvider.get(), this.creationContextFactoryProvider.get());
    }

    public static MetadataBackendRegistry_Factory create(Provider<Context> applicationContextProvider, Provider<CreationContextFactory> creationContextFactoryProvider) {
        return new MetadataBackendRegistry_Factory(applicationContextProvider, creationContextFactoryProvider);
    }

    public static MetadataBackendRegistry newInstance(Context applicationContext, Object creationContextFactory) {
        return new MetadataBackendRegistry(applicationContext, (CreationContextFactory) creationContextFactory);
    }
}
