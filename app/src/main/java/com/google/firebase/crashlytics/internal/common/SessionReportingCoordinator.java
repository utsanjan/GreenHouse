package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.send.DataTransportCrashlyticsReportSender;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SessionReportingCoordinator implements CrashlyticsLifecycleEvents {
    private static final int EVENT_THREAD_IMPORTANCE = 4;
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    private static final int MAX_CHAINED_EXCEPTION_DEPTH = 8;
    private String currentSessionId;
    private final CrashlyticsReportDataCapture dataCapture;
    private final LogFileManager logFileManager;
    private final UserMetadata reportMetadata;
    private final CrashlyticsReportPersistence reportPersistence;
    private final DataTransportCrashlyticsReportSender reportsSender;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean access$lambda$0(SessionReportingCoordinator sessionReportingCoordinator, Task task) {
        return sessionReportingCoordinator.onReportSendComplete(task);
    }

    public static SessionReportingCoordinator create(Context context, IdManager idManager, FileStore fileStore, AppData appData, LogFileManager logFileManager, UserMetadata userMetadata, StackTraceTrimmingStrategy stackTraceTrimmingStrategy, SettingsDataProvider settingsProvider) {
        File rootFilesDirectory = new File(fileStore.getFilesDirPath());
        CrashlyticsReportDataCapture dataCapture = new CrashlyticsReportDataCapture(context, idManager, appData, stackTraceTrimmingStrategy);
        CrashlyticsReportPersistence reportPersistence = new CrashlyticsReportPersistence(rootFilesDirectory, settingsProvider);
        DataTransportCrashlyticsReportSender reportSender = DataTransportCrashlyticsReportSender.create(context);
        return new SessionReportingCoordinator(dataCapture, reportPersistence, reportSender, logFileManager, userMetadata);
    }

    SessionReportingCoordinator(CrashlyticsReportDataCapture dataCapture, CrashlyticsReportPersistence reportPersistence, DataTransportCrashlyticsReportSender reportsSender, LogFileManager logFileManager, UserMetadata reportMetadata) {
        this.dataCapture = dataCapture;
        this.reportPersistence = reportPersistence;
        this.reportsSender = reportsSender;
        this.logFileManager = logFileManager;
        this.reportMetadata = reportMetadata;
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsLifecycleEvents
    public void onBeginSession(String sessionId, long timestamp) {
        this.currentSessionId = sessionId;
        CrashlyticsReport capturedReport = this.dataCapture.captureReportData(sessionId, timestamp);
        this.reportPersistence.persistReport(capturedReport);
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsLifecycleEvents
    public void onLog(long timestamp, String log) {
        this.logFileManager.writeToLog(timestamp, log);
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsLifecycleEvents
    public void onCustomKey(String key, String value) {
        this.reportMetadata.setCustomKey(key, value);
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsLifecycleEvents
    public void onUserId(String userId) {
        this.reportMetadata.setUserId(userId);
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsLifecycleEvents
    public void onEndSession() {
        this.currentSessionId = null;
    }

    public void persistFatalEvent(Throwable event, Thread thread, long timestamp) {
        persistEvent(event, thread, "crash", timestamp, true);
    }

    public void persistNonFatalEvent(Throwable event, Thread thread, long timestamp) {
        persistEvent(event, thread, EVENT_TYPE_LOGGED, timestamp, false);
    }

    public void finalizeSessionWithNativeEvent(String sessionId, List<NativeSessionFile> nativeSessionFiles) {
        ArrayList<CrashlyticsReport.FilesPayload.File> nativeFiles = new ArrayList<>();
        for (NativeSessionFile nativeSessionFile : nativeSessionFiles) {
            CrashlyticsReport.FilesPayload.File filePayload = nativeSessionFile.asFilePayload();
            if (filePayload != null) {
                nativeFiles.add(filePayload);
            }
        }
        this.reportPersistence.finalizeSessionWithNativeEvent(sessionId, CrashlyticsReport.FilesPayload.builder().setFiles(ImmutableList.from(nativeFiles)).build());
    }

    public void persistUserId() {
        String sessionId = this.currentSessionId;
        if (sessionId == null) {
            Logger.getLogger().d("Could not persist user ID; no current session");
            return;
        }
        String userId = this.reportMetadata.getUserId();
        if (userId == null) {
            Logger.getLogger().d("Could not persist user ID; no user ID available");
        } else {
            this.reportPersistence.persistUserIdForSession(userId, sessionId);
        }
    }

    public void finalizeSessions(long timestamp) {
        this.reportPersistence.finalizeReports(this.currentSessionId, timestamp);
    }

    public void removeAllReports() {
        this.reportPersistence.deleteAllReports();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> sendReports(Executor reportSendCompleteExecutor, DataTransportState dataTransportState) {
        if (dataTransportState == DataTransportState.NONE) {
            Logger.getLogger().d("Send via DataTransport disabled. Removing DataTransport reports.");
            this.reportPersistence.deleteAllReports();
            return Tasks.forResult(null);
        }
        List<CrashlyticsReportWithSessionId> reportsToSend = this.reportPersistence.loadFinalizedReports();
        ArrayList arrayList = new ArrayList();
        for (CrashlyticsReportWithSessionId reportToSend : reportsToSend) {
            if (reportToSend.getReport().getType() != CrashlyticsReport.Type.NATIVE || dataTransportState == DataTransportState.ALL) {
                arrayList.add(this.reportsSender.sendReport(reportToSend).continueWith(reportSendCompleteExecutor, SessionReportingCoordinator$$Lambda$1.lambdaFactory$(this)));
            } else {
                Logger.getLogger().d("Send native reports via DataTransport disabled. Removing DataTransport reports.");
                this.reportPersistence.deleteFinalizedReport(reportToSend.getSessionId());
            }
        }
        return Tasks.whenAll(arrayList);
    }

    private void persistEvent(Throwable event, Thread thread, String eventType, long timestamp, boolean includeAllThreads) {
        String sessionId = this.currentSessionId;
        if (sessionId == null) {
            Logger.getLogger().d("Cannot persist event, no currently open session");
            return;
        }
        boolean isHighPriority = eventType.equals("crash");
        CrashlyticsReport.Session.Event capturedEvent = this.dataCapture.captureEventData(event, thread, eventType, timestamp, 4, 8, includeAllThreads);
        CrashlyticsReport.Session.Event.Builder eventBuilder = capturedEvent.toBuilder();
        String content = this.logFileManager.getLogString();
        if (content != null) {
            eventBuilder.setLog(CrashlyticsReport.Session.Event.Log.builder().setContent(content).build());
        } else {
            Logger.getLogger().d("No log data to include with this event.");
        }
        List<CrashlyticsReport.CustomAttribute> sortedCustomAttributes = getSortedCustomAttributes(this.reportMetadata.getCustomKeys());
        if (!sortedCustomAttributes.isEmpty()) {
            eventBuilder.setApp(capturedEvent.getApp().toBuilder().setCustomAttributes(ImmutableList.from(sortedCustomAttributes)).build());
        }
        this.reportPersistence.persistEvent(eventBuilder.build(), sessionId, isHighPriority);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onReportSendComplete(Task<CrashlyticsReportWithSessionId> task) {
        if (task.isSuccessful()) {
            CrashlyticsReportWithSessionId report = task.getResult();
            Logger logger = Logger.getLogger();
            logger.d("Crashlytics report successfully enqueued to DataTransport: " + report.getSessionId());
            this.reportPersistence.deleteFinalizedReport(report.getSessionId());
            return true;
        }
        Logger.getLogger().d("Crashlytics report could not be enqueued to DataTransport", task.getException());
        return false;
    }

    private static List<CrashlyticsReport.CustomAttribute> getSortedCustomAttributes(Map<String, String> attributes) {
        Comparator comparator;
        ArrayList<CrashlyticsReport.CustomAttribute> attributesList = new ArrayList<>();
        attributesList.ensureCapacity(attributes.size());
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            attributesList.add(CrashlyticsReport.CustomAttribute.builder().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        comparator = SessionReportingCoordinator$$Lambda$2.instance;
        Collections.sort(attributesList, comparator);
        return attributesList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$getSortedCustomAttributes$0(CrashlyticsReport.CustomAttribute attr1, CrashlyticsReport.CustomAttribute attr2) {
        return attr1.getKey().compareTo(attr2.getKey());
    }
}
