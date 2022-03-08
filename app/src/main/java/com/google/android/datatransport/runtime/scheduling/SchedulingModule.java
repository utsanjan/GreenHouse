package com.google.android.datatransport.runtime.scheduling;

import android.content.Context;
import android.os.Build;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.AlarmManagerScheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.JobInfoScheduler;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.SchedulerConfig;
import com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
/* loaded from: classes.dex */
public abstract class SchedulingModule {
    @Binds
    abstract Scheduler scheduler(DefaultScheduler defaultScheduler);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    public static WorkScheduler workScheduler(Context context, EventStore eventStore, SchedulerConfig config, Clock clock) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new JobInfoScheduler(context, eventStore, config);
        }
        return new AlarmManagerScheduler(context, eventStore, clock, config);
    }
}
