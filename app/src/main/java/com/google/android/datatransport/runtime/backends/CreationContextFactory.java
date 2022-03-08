package com.google.android.datatransport.runtime.backends;

import android.content.Context;
import com.google.android.datatransport.runtime.time.Clock;
import javax.inject.Inject;

/* loaded from: classes.dex */
class CreationContextFactory {
    private final Context applicationContext;
    private final Clock monotonicClock;
    private final Clock wallClock;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Inject
    public CreationContextFactory(Context applicationContext, Clock wallClock, Clock monotonicClock) {
        this.applicationContext = applicationContext;
        this.wallClock = wallClock;
        this.monotonicClock = monotonicClock;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CreationContext create(String backendName) {
        return CreationContext.create(this.applicationContext, this.wallClock, this.monotonicClock, backendName);
    }
}
