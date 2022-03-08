package com.google.android.datatransport.runtime.scheduling.jobscheduling;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.logging.Logging;
import com.google.android.datatransport.runtime.scheduling.persistence.EventStore;
import com.google.android.datatransport.runtime.time.Clock;
import com.google.android.datatransport.runtime.util.PriorityMapping;

/* loaded from: classes.dex */
public class AlarmManagerScheduler implements WorkScheduler {
    static final String ATTEMPT_NUMBER = "attemptNumber";
    static final String BACKEND_NAME = "backendName";
    static final String EVENT_PRIORITY = "priority";
    static final String EXTRAS = "extras";
    private static final String LOG_TAG = "AlarmManagerScheduler";
    private AlarmManager alarmManager;
    private final Clock clock;
    private final SchedulerConfig config;
    private final Context context;
    private final EventStore eventStore;

    public AlarmManagerScheduler(Context applicationContext, EventStore eventStore, Clock clock, SchedulerConfig config) {
        this(applicationContext, eventStore, (AlarmManager) applicationContext.getSystemService(NotificationCompat.CATEGORY_ALARM), clock, config);
    }

    AlarmManagerScheduler(Context applicationContext, EventStore eventStore, AlarmManager alarmManager, Clock clock, SchedulerConfig config) {
        this.context = applicationContext;
        this.eventStore = eventStore;
        this.alarmManager = alarmManager;
        this.clock = clock;
        this.config = config;
    }

    boolean isJobServiceOn(Intent intent) {
        return PendingIntent.getBroadcast(this.context, 0, intent, 536870912) != null;
    }

    @Override // com.google.android.datatransport.runtime.scheduling.jobscheduling.WorkScheduler
    public void schedule(TransportContext transportContext, int attemptNumber) {
        Uri.Builder intentDataBuilder = new Uri.Builder();
        intentDataBuilder.appendQueryParameter(BACKEND_NAME, transportContext.getBackendName());
        intentDataBuilder.appendQueryParameter(EVENT_PRIORITY, String.valueOf(PriorityMapping.toInt(transportContext.getPriority())));
        if (transportContext.getExtras() != null) {
            intentDataBuilder.appendQueryParameter(EXTRAS, Base64.encodeToString(transportContext.getExtras(), 0));
        }
        Intent intent = new Intent(this.context, AlarmManagerSchedulerBroadcastReceiver.class);
        intent.setData(intentDataBuilder.build());
        intent.putExtra(ATTEMPT_NUMBER, attemptNumber);
        if (isJobServiceOn(intent)) {
            Logging.d(LOG_TAG, "Upload for context %s is already scheduled. Returning...", transportContext);
            return;
        }
        long backendTime = this.eventStore.getNextCallTime(transportContext);
        long scheduleDelay = this.config.getScheduleDelay(transportContext.getPriority(), backendTime, attemptNumber);
        Logging.d(LOG_TAG, "Scheduling upload for context %s in %dms(Backend next call timestamp %d). Attempt %d", transportContext, Long.valueOf(scheduleDelay), Long.valueOf(backendTime), Integer.valueOf(attemptNumber));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.context, 0, intent, 0);
        this.alarmManager.set(3, this.clock.getTime() + scheduleDelay, pendingIntent);
    }
}
