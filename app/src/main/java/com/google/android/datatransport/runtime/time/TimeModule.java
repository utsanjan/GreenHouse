package com.google.android.datatransport.runtime.time;

import dagger.Module;
import dagger.Provides;

@Module
/* loaded from: classes.dex */
public abstract class TimeModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static Clock eventClock() {
        return new WallTimeClock();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static Clock uptimeClock() {
        return new UptimeClock();
    }
}
