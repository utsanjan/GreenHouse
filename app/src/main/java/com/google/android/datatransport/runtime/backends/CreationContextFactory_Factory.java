package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class CreationContextFactory_Factory implements Factory<CreationContextFactory> {
    private final Provider<Context> applicationContextProvider;
    private final Provider<Clock> monotonicClockProvider;
    private final Provider<Clock> wallClockProvider;

    public CreationContextFactory_Factory(Provider<Context> applicationContextProvider, Provider<Clock> wallClockProvider, Provider<Clock> monotonicClockProvider) {
        this.applicationContextProvider = applicationContextProvider;
        this.wallClockProvider = wallClockProvider;
        this.monotonicClockProvider = monotonicClockProvider;
    }

    @Override // javax.inject.Provider
    public CreationContextFactory get() {
        return new CreationContextFactory(this.applicationContextProvider.get(), this.wallClockProvider.get(), this.monotonicClockProvider.get());
    }

    public static CreationContextFactory_Factory create(Provider<Context> applicationContextProvider, Provider<Clock> wallClockProvider, Provider<Clock> monotonicClockProvider) {
        return new CreationContextFactory_Factory(applicationContextProvider, wallClockProvider, monotonicClockProvider);
    }

    public static CreationContextFactory newInstance(Context applicationContext, Clock wallClock, Clock monotonicClock) {
        return new CreationContextFactory(applicationContext, wallClock, monotonicClock);
    }
}
