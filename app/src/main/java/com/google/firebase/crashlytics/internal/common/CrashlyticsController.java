package com.google.firebase.crashlytics.internal.common;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.NativeSessionFileProvider;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver;
import com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler;
import com.google.firebase.crashlytics.internal.log.LogFileManager;
import com.google.firebase.crashlytics.internal.ndk.NativeFileUtils;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.proto.ClsFileOutputStream;
import com.google.firebase.crashlytics.internal.proto.CodedOutputStream;
import com.google.firebase.crashlytics.internal.proto.SessionProtobufHelper;
import com.google.firebase.crashlytics.internal.report.ReportManager;
import com.google.firebase.crashlytics.internal.report.ReportUploader;
import com.google.firebase.crashlytics.internal.report.model.Report;
import com.google.firebase.crashlytics.internal.report.model.SessionReport;
import com.google.firebase.crashlytics.internal.report.network.CompositeCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.CreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.DefaultCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.report.network.NativeCreateReportSpiCall;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.stacktrace.MiddleOutFallbackStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.RemoveRepeatsStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.StackTraceTrimmingStrategy;
import com.google.firebase.crashlytics.internal.stacktrace.TrimmedThrowableData;
import com.google.firebase.crashlytics.internal.unity.UnityVersionProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class CrashlyticsController {
    private static final int ANALYZER_VERSION = 1;
    private static final String COLLECT_CUSTOM_KEYS = "com.crashlytics.CollectCustomKeys";
    private static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private static final String EVENT_TYPE_CRASH = "crash";
    private static final String EVENT_TYPE_LOGGED = "error";
    static final String FATAL_SESSION_DIR = "fatal-sessions";
    static final String FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS = "clx";
    static final String FIREBASE_APPLICATION_EXCEPTION = "_ae";
    static final String FIREBASE_CRASH_TYPE = "fatal";
    static final int FIREBASE_CRASH_TYPE_FATAL = 1;
    static final String FIREBASE_TIMESTAMP = "timestamp";
    private static final String GENERATOR_FORMAT = "Crashlytics Android SDK/%s";
    private static final int MAX_CHAINED_EXCEPTION_DEPTH = 8;
    private static final int MAX_LOCAL_LOGGED_EXCEPTIONS = 64;
    static final int MAX_OPEN_SESSIONS = 8;
    static final int MAX_STACK_SIZE = 1024;
    static final String NATIVE_SESSION_DIR = "native-sessions";
    static final String NONFATAL_SESSION_DIR = "nonfatal-sessions";
    static final int NUM_STACK_REPETITIONS_ALLOWED = 10;
    static final String SESSION_EVENT_MISSING_BINARY_IMGS_TAG = "SessionMissingBinaryImages";
    static final String SESSION_FATAL_TAG = "SessionCrash";
    private static final int SESSION_ID_LENGTH = 35;
    static final String SESSION_NON_FATAL_TAG = "SessionEvent";
    private final AnalyticsConnector analyticsConnector;
    private final AnalyticsReceiver analyticsReceiver;
    private final AppData appData;
    private final CrashlyticsBackgroundWorker backgroundWorker;
    private final Context context;
    private CrashlyticsUncaughtExceptionHandler crashHandler;
    private final CrashlyticsFileMarker crashMarker;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final FileStore fileStore;
    private final ReportUploader.HandlingExceptionCheck handlingExceptionCheck;
    private final HttpRequestFactory httpRequestFactory;
    private final IdManager idManager;
    private final LogFileDirectoryProvider logFileDirectoryProvider;
    private final LogFileManager logFileManager;
    private final CrashlyticsNativeComponent nativeComponent;
    private final ReportManager reportManager;
    private final ReportUploader.Provider reportUploaderProvider;
    private final SessionReportingCoordinator reportingCoordinator;
    private final StackTraceTrimmingStrategy stackTraceTrimmingStrategy;
    private final String unityVersion;
    private final UserMetadata userMetadata;
    static final String SESSION_BEGIN_TAG = "BeginSession";
    static final FilenameFilter SESSION_BEGIN_FILE_FILTER = new FileNameContainsFilter(SESSION_BEGIN_TAG) { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.1
        @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsController.FileNameContainsFilter, java.io.FilenameFilter
        public boolean accept(File dir, String filename) {
            return super.accept(dir, filename) && filename.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    static final FilenameFilter SESSION_FILE_FILTER = new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.2
        @Override // java.io.FilenameFilter
        public boolean accept(File dir, String filename) {
            return filename.length() == ClsFileOutputStream.SESSION_FILE_EXTENSION.length() + 35 && filename.endsWith(ClsFileOutputStream.SESSION_FILE_EXTENSION);
        }
    };
    static final Comparator<File> LARGEST_FILE_NAME_FIRST = new Comparator<File>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.3
        public int compare(File file1, File file2) {
            return file2.getName().compareTo(file1.getName());
        }
    };
    static final Comparator<File> SMALLEST_FILE_NAME_FIRST = new Comparator<File>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.4
        public int compare(File file1, File file2) {
            return file1.getName().compareTo(file2.getName());
        }
    };
    private static final Pattern SESSION_FILE_PATTERN = Pattern.compile("([\\d|A-Z|a-z]{12}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{4}\\-[\\d|A-Z|a-z]{12}).+");
    private static final Map<String, String> SEND_AT_CRASHTIME_HEADER = Collections.singletonMap("X-CRASHLYTICS-SEND-FLAGS", "1");
    static final String SESSION_USER_TAG = "SessionUser";
    static final String SESSION_APP_TAG = "SessionApp";
    static final String SESSION_OS_TAG = "SessionOS";
    static final String SESSION_DEVICE_TAG = "SessionDevice";
    private static final String[] INITIAL_SESSION_PART_TAGS = {SESSION_USER_TAG, SESSION_APP_TAG, SESSION_OS_TAG, SESSION_DEVICE_TAG};
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    TaskCompletionSource<Boolean> unsentReportsAvailable = new TaskCompletionSource<>();
    TaskCompletionSource<Boolean> reportActionProvided = new TaskCompletionSource<>();
    TaskCompletionSource<Void> unsentReportsHandled = new TaskCompletionSource<>();
    AtomicBoolean checkForUnsentReportsCalled = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface CodedOutputStreamWriteAction {
        void writeTo(CodedOutputStream codedOutputStream) throws Exception;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class FileNameContainsFilter implements FilenameFilter {
        private final String string;

        public FileNameContainsFilter(String s) {
            this.string = s;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File dir, String filename) {
            return filename.contains(this.string) && !filename.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SessionPartFileFilter implements FilenameFilter {
        private final String sessionId;

        public SessionPartFileFilter(String sessionId) {
            this.sessionId = sessionId;
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String fileName) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.sessionId);
            sb.append(ClsFileOutputStream.SESSION_FILE_EXTENSION);
            return !fileName.equals(sb.toString()) && fileName.contains(this.sessionId) && !fileName.endsWith(ClsFileOutputStream.IN_PROGRESS_SESSION_FILE_EXTENSION);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class AnySessionPartFileFilter implements FilenameFilter {
        private AnySessionPartFileFilter() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String fileName) {
            return !CrashlyticsController.SESSION_FILE_FILTER.accept(file, fileName) && CrashlyticsController.SESSION_FILE_PATTERN.matcher(fileName).matches();
        }
    }

    /* loaded from: classes.dex */
    static class InvalidPartFileFilter implements FilenameFilter {
        InvalidPartFileFilter() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String fileName) {
            return ClsFileOutputStream.TEMP_FILENAME_FILTER.accept(file, fileName) || fileName.contains(CrashlyticsController.SESSION_EVENT_MISSING_BINARY_IMGS_TAG);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public CrashlyticsController(Context context, CrashlyticsBackgroundWorker backgroundWorker, HttpRequestFactory httpRequestFactory, IdManager idManager, DataCollectionArbiter dataCollectionArbiter, FileStore fileStore, CrashlyticsFileMarker crashMarker, AppData appData, ReportManager reportManager, ReportUploader.Provider reportUploaderProvider, CrashlyticsNativeComponent nativeComponent, UnityVersionProvider unityVersionProvider, AnalyticsReceiver analyticsReceiver, AnalyticsConnector analyticsConnector, SettingsDataProvider settingsDataProvider) {
        ReportManager reportManager2;
        this.context = context;
        this.backgroundWorker = backgroundWorker;
        this.httpRequestFactory = httpRequestFactory;
        this.idManager = idManager;
        this.dataCollectionArbiter = dataCollectionArbiter;
        this.fileStore = fileStore;
        this.crashMarker = crashMarker;
        this.appData = appData;
        if (reportUploaderProvider != null) {
            this.reportUploaderProvider = reportUploaderProvider;
        } else {
            this.reportUploaderProvider = defaultReportUploader();
        }
        this.nativeComponent = nativeComponent;
        this.unityVersion = unityVersionProvider.getUnityVersion();
        this.analyticsReceiver = analyticsReceiver;
        this.analyticsConnector = analyticsConnector;
        this.userMetadata = new UserMetadata();
        this.logFileDirectoryProvider = new LogFileDirectoryProvider(fileStore);
        this.logFileManager = new LogFileManager(context, this.logFileDirectoryProvider);
        if (reportManager == null) {
            reportManager2 = new ReportManager(new ReportUploaderFilesProvider());
        } else {
            reportManager2 = reportManager;
        }
        this.reportManager = reportManager2;
        this.handlingExceptionCheck = new ReportUploaderHandlingExceptionCheck();
        MiddleOutFallbackStrategy middleOutFallbackStrategy = new MiddleOutFallbackStrategy(1024, new RemoveRepeatsStrategy(10));
        this.stackTraceTrimmingStrategy = middleOutFallbackStrategy;
        this.reportingCoordinator = SessionReportingCoordinator.create(context, idManager, fileStore, appData, this.logFileManager, this.userMetadata, middleOutFallbackStrategy, settingsDataProvider);
    }

    private Context getContext() {
        return this.context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enableExceptionHandling(Thread.UncaughtExceptionHandler defaultHandler, SettingsDataProvider settingsProvider) {
        openSession();
        CrashlyticsUncaughtExceptionHandler.CrashListener crashListener = new CrashlyticsUncaughtExceptionHandler.CrashListener() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.5
            @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler.CrashListener
            public void onUncaughtException(SettingsDataProvider settingsDataProvider, Thread thread, Throwable ex) {
                CrashlyticsController.this.handleUncaughtException(settingsDataProvider, thread, ex);
            }
        };
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = new CrashlyticsUncaughtExceptionHandler(crashListener, settingsProvider, defaultHandler);
        this.crashHandler = crashlyticsUncaughtExceptionHandler;
        Thread.setDefaultUncaughtExceptionHandler(crashlyticsUncaughtExceptionHandler);
    }

    synchronized void handleUncaughtException(final SettingsDataProvider settingsDataProvider, final Thread thread, final Throwable ex) {
        Logger logger = Logger.getLogger();
        logger.d("Crashlytics is handling uncaught exception \"" + ex + "\" from thread " + thread.getName());
        final Date time = new Date();
        final Task<Void> recordFatalFirebaseEventTask = recordFatalFirebaseEvent(time.getTime());
        Task<Void> handleUncaughtExceptionTask = this.backgroundWorker.submitTask(new Callable<Task<Void>>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.6
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Task<Void> call() throws Exception {
                CrashlyticsController.this.crashMarker.create();
                long timestampSeconds = CrashlyticsController.getTimestampSeconds(time);
                CrashlyticsController.this.reportingCoordinator.persistFatalEvent(ex, thread, timestampSeconds);
                CrashlyticsController.this.writeFatal(thread, ex, timestampSeconds);
                Settings settings = settingsDataProvider.getSettings();
                int maxCustomExceptionEvents = settings.getSessionData().maxCustomExceptionEvents;
                int maxCompleteSessionsCount = settings.getSessionData().maxCompleteSessionsCount;
                CrashlyticsController.this.doCloseSessions(maxCustomExceptionEvents);
                CrashlyticsController.this.doOpenSession();
                CrashlyticsController.this.trimSessionFiles(maxCompleteSessionsCount);
                if (!CrashlyticsController.this.dataCollectionArbiter.isAutomaticDataCollectionEnabled()) {
                    return Tasks.forResult(null);
                }
                final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                return settingsDataProvider.getAppSettings().onSuccessTask(executor, new SuccessContinuation<AppSettingsData, Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.6.1
                    public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                        if (appSettingsData == null) {
                            Logger.getLogger().w("Received null app settings, cannot send reports at crash time.");
                            return Tasks.forResult(null);
                        }
                        CrashlyticsController.this.sendSessionReports(appSettingsData, true);
                        return Tasks.whenAll(CrashlyticsController.this.reportingCoordinator.sendReports(executor, DataTransportState.getState(appSettingsData)), recordFatalFirebaseEventTask);
                    }
                });
            }
        });
        try {
            Utils.awaitEvenIfOnMainThread(handleUncaughtExceptionTask);
        } catch (Exception e) {
        }
    }

    private Task<Boolean> waitForReportAction() {
        if (this.dataCollectionArbiter.isAutomaticDataCollectionEnabled()) {
            Logger.getLogger().d("Automatic data collection is enabled. Allowing upload.");
            this.unsentReportsAvailable.trySetResult(false);
            return Tasks.forResult(true);
        }
        Logger.getLogger().d("Automatic data collection is disabled.");
        Logger.getLogger().d("Notifying that unsent reports are available.");
        this.unsentReportsAvailable.trySetResult(true);
        Task<TContinuationResult> onSuccessTask = this.dataCollectionArbiter.waitForAutomaticDataCollectionEnabled().onSuccessTask(new SuccessContinuation<Void, Boolean>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.7
            public Task<Boolean> then(Void aVoid) throws Exception {
                return Tasks.forResult(true);
            }
        });
        Logger.getLogger().d("Waiting for send/deleteUnsentReports to be called.");
        return Utils.race(onSuccessTask, this.reportActionProvided.getTask());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean didCrashOnPreviousExecution() {
        if (!this.crashMarker.isPresent()) {
            String sessionId = getCurrentSessionId();
            return sessionId != null && this.nativeComponent.hasCrashDataForSession(sessionId);
        }
        Logger.getLogger().d("Found previous crash marker.");
        this.crashMarker.remove();
        return Boolean.TRUE.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Boolean> checkForUnsentReports() {
        if (this.checkForUnsentReportsCalled.compareAndSet(false, true)) {
            return this.unsentReportsAvailable.getTask();
        }
        Logger.getLogger().d("checkForUnsentReports should only be called once per execution.");
        return Tasks.forResult(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> sendUnsentReports() {
        this.reportActionProvided.trySetResult(true);
        return this.unsentReportsHandled.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> deleteUnsentReports() {
        this.reportActionProvided.trySetResult(false);
        return this.unsentReportsHandled.getTask();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> submitAllReports(float delay, Task<AppSettingsData> appSettingsDataTask) {
        if (!this.reportManager.areReportsAvailable()) {
            Logger.getLogger().d("No reports are available.");
            this.unsentReportsAvailable.trySetResult(false);
            return Tasks.forResult(null);
        }
        Logger.getLogger().d("Unsent reports are available.");
        return waitForReportAction().onSuccessTask(new AnonymousClass8(appSettingsDataTask, delay));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.firebase.crashlytics.internal.common.CrashlyticsController$8  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass8 implements SuccessContinuation<Boolean, Void> {
        final /* synthetic */ Task val$appSettingsDataTask;
        final /* synthetic */ float val$delay;

        AnonymousClass8(Task task, float f) {
            this.val$appSettingsDataTask = task;
            this.val$delay = f;
        }

        public Task<Void> then(final Boolean send) throws Exception {
            return CrashlyticsController.this.backgroundWorker.submitTask(new Callable<Task<Void>>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.8.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.concurrent.Callable
                public Task<Void> call() throws Exception {
                    final List<Report> reports = CrashlyticsController.this.reportManager.findReports();
                    if (!send.booleanValue()) {
                        Logger.getLogger().d("Reports are being deleted.");
                        CrashlyticsController.this.reportManager.deleteReports(reports);
                        CrashlyticsController.this.reportingCoordinator.removeAllReports();
                        CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                        return Tasks.forResult(null);
                    }
                    Logger.getLogger().d("Reports are being sent.");
                    final boolean dataCollectionToken = send.booleanValue();
                    CrashlyticsController.this.dataCollectionArbiter.grantDataCollectionPermission(dataCollectionToken);
                    final Executor executor = CrashlyticsController.this.backgroundWorker.getExecutor();
                    return AnonymousClass8.this.val$appSettingsDataTask.onSuccessTask(executor, new SuccessContinuation<AppSettingsData, Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.8.1.1
                        public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                            if (appSettingsData == null) {
                                Logger.getLogger().w("Received null app settings, cannot send reports during app startup.");
                                return Tasks.forResult(null);
                            }
                            for (Report report : reports) {
                                if (report.getType() == Report.Type.JAVA) {
                                    CrashlyticsController.appendOrganizationIdToSessionFile(appSettingsData.organizationId, report.getFile());
                                }
                            }
                            ReportUploader uploader = CrashlyticsController.this.reportUploaderProvider.createReportUploader(appSettingsData);
                            uploader.uploadReportsAsync(reports, dataCollectionToken, AnonymousClass8.this.val$delay);
                            CrashlyticsController.this.reportingCoordinator.sendReports(executor, DataTransportState.getState(appSettingsData));
                            CrashlyticsController.this.unsentReportsHandled.trySetResult(null);
                            return Tasks.forResult(null);
                        }
                    });
                }
            });
        }
    }

    private ReportUploader.Provider defaultReportUploader() {
        return new ReportUploader.Provider() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.9
            @Override // com.google.firebase.crashlytics.internal.report.ReportUploader.Provider
            public ReportUploader createReportUploader(AppSettingsData appSettingsData) {
                String reportsUrl = appSettingsData.reportsUrl;
                String ndkReportsUrl = appSettingsData.ndkReportsUrl;
                String organizationId = appSettingsData.organizationId;
                CreateReportSpiCall call = CrashlyticsController.this.getCreateReportSpiCall(reportsUrl, ndkReportsUrl);
                return new ReportUploader(organizationId, CrashlyticsController.this.appData.googleAppId, DataTransportState.getState(appSettingsData), CrashlyticsController.this.reportManager, call, CrashlyticsController.this.handlingExceptionCheck);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeToLog(final long timestamp, final String msg) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.10
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                if (CrashlyticsController.this.isHandlingException()) {
                    return null;
                }
                CrashlyticsController.this.logFileManager.writeToLog(timestamp, msg);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeNonFatalException(final Thread thread, final Throwable ex) {
        final Date time = new Date();
        this.backgroundWorker.submit(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.11
            @Override // java.lang.Runnable
            public void run() {
                if (!CrashlyticsController.this.isHandlingException()) {
                    long timestampSeconds = CrashlyticsController.getTimestampSeconds(time);
                    CrashlyticsController.this.reportingCoordinator.persistNonFatalEvent(ex, thread, timestampSeconds);
                    CrashlyticsController.this.doWriteNonFatal(thread, ex, timestampSeconds);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUserId(String identifier) {
        this.userMetadata.setUserId(identifier);
        cacheUserData(this.userMetadata);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCustomKey(String key, String value) {
        try {
            this.userMetadata.setCustomKey(key, value);
            cacheKeyData(this.userMetadata.getCustomKeys());
        } catch (IllegalArgumentException ex) {
            Context context = this.context;
            if (context == null || !CommonUtils.isAppDebuggable(context)) {
                Logger.getLogger().e("Attempting to set custom attribute with null key, ignoring.");
                return;
            }
            throw ex;
        }
    }

    private void cacheUserData(final UserMetadata userMetaData) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.12
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                CrashlyticsController.this.reportingCoordinator.persistUserId();
                String currentSessionId = CrashlyticsController.this.getCurrentSessionId();
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeUserData(currentSessionId, userMetaData);
                return null;
            }
        });
    }

    private void cacheKeyData(final Map<String, String> keyData) {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.13
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                String currentSessionId = CrashlyticsController.this.getCurrentSessionId();
                new MetaDataStore(CrashlyticsController.this.getFilesDir()).writeKeyData(currentSessionId, keyData);
                return null;
            }
        });
    }

    void openSession() {
        this.backgroundWorker.submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.14
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                CrashlyticsController.this.doOpenSession();
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCurrentSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length > 0) {
            return getSessionIdFromSessionFile(sessionBeginFiles[0]);
        }
        return null;
    }

    private String getPreviousSessionId() {
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length > 1) {
            return getSessionIdFromSessionFile(sessionBeginFiles[1]);
        }
        return null;
    }

    static String getSessionIdFromSessionFile(File sessionFile) {
        return sessionFile.getName().substring(0, 35);
    }

    boolean hasOpenSession() {
        return listSessionBeginFiles().length > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean finalizeSessions(int maxCustomExceptionEvents) {
        this.backgroundWorker.checkRunningOnThread();
        if (isHandlingException()) {
            Logger.getLogger().d("Skipping session finalization because a crash has already occurred.");
            return Boolean.FALSE.booleanValue();
        }
        Logger.getLogger().d("Finalizing previously open sessions.");
        try {
            doCloseSessions(maxCustomExceptionEvents, false);
            Logger.getLogger().d("Closed all previously open sessions");
            return true;
        } catch (Exception e) {
            Logger.getLogger().e("Unable to finalize previously open sessions.", e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doOpenSession() throws Exception {
        long startedAtSeconds = getCurrentTimestampSeconds();
        String sessionIdentifier = new CLSUUID(this.idManager).toString();
        Logger logger = Logger.getLogger();
        logger.d("Opening a new session with ID " + sessionIdentifier);
        this.nativeComponent.openSession(sessionIdentifier);
        writeBeginSession(sessionIdentifier, startedAtSeconds);
        writeSessionApp(sessionIdentifier);
        writeSessionOS(sessionIdentifier);
        writeSessionDevice(sessionIdentifier);
        this.logFileManager.setCurrentSession(sessionIdentifier);
        this.reportingCoordinator.onBeginSession(makeFirebaseSessionIdentifier(sessionIdentifier), startedAtSeconds);
    }

    void doCloseSessions(int maxCustomExceptionEvents) throws Exception {
        doCloseSessions(maxCustomExceptionEvents, true);
    }

    private void doCloseSessions(int maxCustomExceptionEvents, boolean includeCurrent) throws Exception {
        int offset = !includeCurrent ? 1 : 0;
        trimOpenSessions(offset + 8);
        File[] sessionBeginFiles = listSortedSessionBeginFiles();
        if (sessionBeginFiles.length <= offset) {
            Logger.getLogger().d("No open sessions to be closed.");
            return;
        }
        String mostRecentSessionIdToClose = getSessionIdFromSessionFile(sessionBeginFiles[offset]);
        writeSessionUser(mostRecentSessionIdToClose);
        if (includeCurrent) {
            this.reportingCoordinator.onEndSession();
        } else if (this.nativeComponent.hasCrashDataForSession(mostRecentSessionIdToClose)) {
            finalizePreviousNativeSession(mostRecentSessionIdToClose);
            if (!this.nativeComponent.finalizeSession(mostRecentSessionIdToClose)) {
                Logger logger = Logger.getLogger();
                logger.d("Could not finalize native session: " + mostRecentSessionIdToClose);
            }
        }
        closeOpenSessions(sessionBeginFiles, offset, maxCustomExceptionEvents);
        this.reportingCoordinator.finalizeSessions(getCurrentTimestampSeconds());
    }

    private void closeOpenSessions(File[] sessionBeginFiles, int beginIndex, int maxLoggedExceptionsCount) {
        Logger.getLogger().d("Closing open sessions.");
        for (int i = beginIndex; i < sessionBeginFiles.length; i++) {
            File sessionBeginFile = sessionBeginFiles[i];
            String sessionIdentifier = getSessionIdFromSessionFile(sessionBeginFile);
            Logger logger = Logger.getLogger();
            logger.d("Closing session: " + sessionIdentifier);
            writeSessionPartsToSessionFile(sessionBeginFile, sessionIdentifier, maxLoggedExceptionsCount);
        }
    }

    private void closeWithoutRenamingOrLog(ClsFileOutputStream fos) {
        if (fos != null) {
            try {
                fos.closeInProgressStream();
            } catch (IOException ex) {
                Logger.getLogger().e("Error closing session file stream in the presence of an exception", ex);
            }
        }
    }

    private void deleteSessionPartFilesFor(String sessionId) {
        File[] listSessionPartFilesFor;
        for (File file : listSessionPartFilesFor(sessionId)) {
            file.delete();
        }
    }

    private File[] listSessionPartFilesFor(String sessionId) {
        return listFilesMatching(new SessionPartFileFilter(sessionId));
    }

    File[] listCompleteSessionFiles() {
        List<File> completeSessionFiles = new LinkedList<>();
        Collections.addAll(completeSessionFiles, listFilesMatching(getFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getNonFatalSessionFilesDir(), SESSION_FILE_FILTER));
        Collections.addAll(completeSessionFiles, listFilesMatching(getFilesDir(), SESSION_FILE_FILTER));
        return (File[]) completeSessionFiles.toArray(new File[completeSessionFiles.size()]);
    }

    File[] listNativeSessionFileDirectories() {
        return ensureFileArrayNotNull(getNativeSessionFilesDir().listFiles());
    }

    File[] listSessionBeginFiles() {
        return listFilesMatching(SESSION_BEGIN_FILE_FILTER);
    }

    private File[] listSortedSessionBeginFiles() {
        File[] sessionBeginFiles = listSessionBeginFiles();
        Arrays.sort(sessionBeginFiles, LARGEST_FILE_NAME_FIRST);
        return sessionBeginFiles;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File[] listFilesMatching(FilenameFilter filter) {
        return listFilesMatching(getFilesDir(), filter);
    }

    private File[] listFilesMatching(File directory, FilenameFilter filter) {
        return ensureFileArrayNotNull(directory.listFiles(filter));
    }

    private File[] listFiles(File directory) {
        return ensureFileArrayNotNull(directory.listFiles());
    }

    private File[] ensureFileArrayNotNull(File[] files) {
        return files == null ? new File[0] : files;
    }

    private void trimSessionEventFiles(String sessionId, int limit) {
        File filesDir = getFilesDir();
        Utils.capFileCount(filesDir, new FileNameContainsFilter(sessionId + SESSION_NON_FATAL_TAG), limit, SMALLEST_FILE_NAME_FIRST);
    }

    void trimSessionFiles(int maxCompleteSessionsCount) {
        int remaining = maxCompleteSessionsCount - Utils.capSessionCount(getNativeSessionFilesDir(), getFatalSessionFilesDir(), maxCompleteSessionsCount, SMALLEST_FILE_NAME_FIRST);
        Utils.capFileCount(getFilesDir(), SESSION_FILE_FILTER, remaining - Utils.capFileCount(getNonFatalSessionFilesDir(), remaining, SMALLEST_FILE_NAME_FIRST), SMALLEST_FILE_NAME_FIRST);
    }

    private void trimOpenSessions(int maxOpenSessionCount) {
        Set<String> sessionIdsToKeep = new HashSet<>();
        File[] beginSessionFiles = listSortedSessionBeginFiles();
        int count = Math.min(maxOpenSessionCount, beginSessionFiles.length);
        for (int i = 0; i < count; i++) {
            String sessionId = getSessionIdFromSessionFile(beginSessionFiles[i]);
            sessionIdsToKeep.add(sessionId);
        }
        this.logFileManager.discardOldLogFiles(sessionIdsToKeep);
        retainSessions(listFilesMatching(new AnySessionPartFileFilter()), sessionIdsToKeep);
    }

    private void retainSessions(File[] files, Set<String> sessionIdsToKeep) {
        for (File sessionPartFile : files) {
            String fileName = sessionPartFile.getName();
            Matcher matcher = SESSION_FILE_PATTERN.matcher(fileName);
            if (!matcher.matches()) {
                Logger.getLogger().d("Deleting unknown file: " + fileName);
                sessionPartFile.delete();
            } else {
                String sessionId = matcher.group(1);
                if (!sessionIdsToKeep.contains(sessionId)) {
                    Logger.getLogger().d("Trimming session file: " + fileName);
                    sessionPartFile.delete();
                }
            }
        }
    }

    private File[] getTrimmedNonFatalFiles(String sessionId, File[] nonFatalFiles, int maxLoggedExceptionsCount) {
        if (nonFatalFiles.length <= maxLoggedExceptionsCount) {
            return nonFatalFiles;
        }
        Logger.getLogger().d(String.format(Locale.US, "Trimming down to %d logged exceptions.", Integer.valueOf(maxLoggedExceptionsCount)));
        trimSessionEventFiles(sessionId, maxLoggedExceptionsCount);
        return listFilesMatching(new FileNameContainsFilter(sessionId + SESSION_NON_FATAL_TAG));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cleanInvalidTempFiles() {
        this.backgroundWorker.submit(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.15
            @Override // java.lang.Runnable
            public void run() {
                CrashlyticsController crashlyticsController = CrashlyticsController.this;
                crashlyticsController.doCleanInvalidTempFiles(crashlyticsController.listFilesMatching(new InvalidPartFileFilter()));
            }
        });
    }

    void doCleanInvalidTempFiles(File[] invalidFiles) {
        File[] listFilesMatching;
        final Set<String> invalidSessionIds = new HashSet<>();
        for (File invalidFile : invalidFiles) {
            Logger.getLogger().d("Found invalid session part file: " + invalidFile);
            invalidSessionIds.add(getSessionIdFromSessionFile(invalidFile));
        }
        if (!invalidSessionIds.isEmpty()) {
            FilenameFilter invalidSessionFilter = new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.16
                @Override // java.io.FilenameFilter
                public boolean accept(File dir, String filename) {
                    if (filename.length() < 35) {
                        return false;
                    }
                    return invalidSessionIds.contains(filename.substring(0, 35));
                }
            };
            for (File sessionFile : listFilesMatching(invalidSessionFilter)) {
                Logger.getLogger().d("Deleting invalid session file: " + sessionFile);
                sessionFile.delete();
            }
        }
    }

    private void finalizePreviousNativeSession(String previousSessionId) {
        Logger logger = Logger.getLogger();
        logger.d("Finalizing native report for session " + previousSessionId);
        NativeSessionFileProvider nativeSessionFileProvider = this.nativeComponent.getSessionFileProvider(previousSessionId);
        File minidumpFile = nativeSessionFileProvider.getMinidumpFile();
        if (minidumpFile == null || !minidumpFile.exists()) {
            Logger logger2 = Logger.getLogger();
            logger2.w("No minidump data found for session " + previousSessionId);
            return;
        }
        LogFileManager previousSessionLogManager = new LogFileManager(this.context, this.logFileDirectoryProvider, previousSessionId);
        File nativeSessionDirectory = new File(getNativeSessionFilesDir(), previousSessionId);
        if (!nativeSessionDirectory.mkdirs()) {
            Logger.getLogger().d("Couldn't create native sessions directory");
            return;
        }
        List<NativeSessionFile> nativeSessionFiles = getNativeSessionFiles(nativeSessionFileProvider, previousSessionId, getContext(), getFilesDir(), previousSessionLogManager.getBytesForLog());
        NativeSessionFileGzipper.processNativeSessions(nativeSessionDirectory, nativeSessionFiles);
        this.reportingCoordinator.finalizeSessionWithNativeEvent(makeFirebaseSessionIdentifier(previousSessionId), nativeSessionFiles);
        previousSessionLogManager.clearLog();
    }

    private static long getCurrentTimestampSeconds() {
        return getTimestampSeconds(new Date());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getTimestampSeconds(Date date) {
        return date.getTime() / 1000;
    }

    private static String makeFirebaseSessionIdentifier(String sessionIdentifier) {
        return sessionIdentifier.replaceAll("-", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeFatal(Thread thread, Throwable ex, long eventTime) {
        String currentSessionId;
        ClsFileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            try {
                currentSessionId = getCurrentSessionId();
            } catch (Exception e) {
                Logger.getLogger().e("An error occurred in the fatal exception logger", e);
            }
            if (currentSessionId == null) {
                Logger.getLogger().e("Tried to write a fatal exception while no session was open.");
                return;
            }
            File filesDir = getFilesDir();
            fos = new ClsFileOutputStream(filesDir, currentSessionId + SESSION_FATAL_TAG);
            cos = CodedOutputStream.newInstance(fos);
            writeSessionEvent(cos, thread, ex, eventTime, "crash", true);
        } finally {
            CommonUtils.flushOrLog(null, "Failed to flush to session begin file.");
            CommonUtils.closeOrLog(null, "Failed to close fatal exception file output stream.");
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:19:0x008f
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* JADX INFO: Access modifiers changed from: private */
    public void doWriteNonFatal(java.lang.Thread r18, java.lang.Throwable r19, long r20) {
        /*
            Method dump skipped, instructions count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.CrashlyticsController.doWriteNonFatal(java.lang.Thread, java.lang.Throwable, long):void");
    }

    private void writeSessionPartFile(String sessionId, String tag, CodedOutputStreamWriteAction writeAction) throws Exception {
        FileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            File filesDir = getFilesDir();
            fos = new ClsFileOutputStream(filesDir, sessionId + tag);
            cos = CodedOutputStream.newInstance(fos);
            writeAction.writeTo(cos);
        } finally {
            CommonUtils.flushOrLog(cos, "Failed to flush to session " + tag + " file.");
            CommonUtils.closeOrLog(fos, "Failed to close session " + tag + " file.");
        }
    }

    private static void appendToProtoFile(File file, CodedOutputStreamWriteAction writeAction) throws Exception {
        FileOutputStream fos = null;
        CodedOutputStream cos = null;
        try {
            fos = new FileOutputStream(file, true);
            cos = CodedOutputStream.newInstance(fos);
            writeAction.writeTo(cos);
        } finally {
            CommonUtils.flushOrLog(cos, "Failed to flush to append to " + file.getPath());
            CommonUtils.closeOrLog(fos, "Failed to close " + file.getPath());
        }
    }

    private void writeBeginSession(final String sessionId, final long startedAtSeconds) throws Exception {
        final String generator = String.format(Locale.US, GENERATOR_FORMAT, CrashlyticsCore.getVersion());
        writeSessionPartFile(sessionId, SESSION_BEGIN_TAG, new CodedOutputStreamWriteAction() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.17
            @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeBeginSession(arg, sessionId, generator, startedAtSeconds);
            }
        });
        this.nativeComponent.writeBeginSession(sessionId, generator, startedAtSeconds);
    }

    private void writeSessionApp(String sessionId) throws Exception {
        final String appIdentifier = this.idManager.getAppIdentifier();
        final String versionCode = this.appData.versionCode;
        final String versionName = this.appData.versionName;
        final String installUuid = this.idManager.getCrashlyticsInstallId();
        final int deliveryMechanism = DeliveryMechanism.determineFrom(this.appData.installerPackageName).getId();
        writeSessionPartFile(sessionId, SESSION_APP_TAG, new CodedOutputStreamWriteAction() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.18
            @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionApp(arg, appIdentifier, versionCode, versionName, installUuid, deliveryMechanism, CrashlyticsController.this.unityVersion);
            }
        });
        this.nativeComponent.writeSessionApp(sessionId, appIdentifier, versionCode, versionName, installUuid, deliveryMechanism, this.unityVersion);
    }

    private void writeSessionOS(String sessionId) throws Exception {
        final String osRelease = Build.VERSION.RELEASE;
        final String osCodeName = Build.VERSION.CODENAME;
        final boolean isRooted = CommonUtils.isRooted(getContext());
        writeSessionPartFile(sessionId, SESSION_OS_TAG, new CodedOutputStreamWriteAction() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.19
            @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionOS(arg, osRelease, osCodeName, isRooted);
            }
        });
        this.nativeComponent.writeSessionOs(sessionId, osRelease, osCodeName, isRooted);
    }

    private void writeSessionDevice(String sessionId) throws Exception {
        Context context = getContext();
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        final int arch = CommonUtils.getCpuArchitectureInt();
        final String model = Build.MODEL;
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        final long totalRam = CommonUtils.getTotalRamInBytes();
        final long diskSpace = statFs.getBlockCount() * statFs.getBlockSize();
        final boolean isEmulator = CommonUtils.isEmulator(context);
        final int state = CommonUtils.getDeviceState(context);
        final String manufacturer = Build.MANUFACTURER;
        final String modelClass = Build.PRODUCT;
        writeSessionPartFile(sessionId, SESSION_DEVICE_TAG, new CodedOutputStreamWriteAction() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.20
            @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionDevice(arg, arch, model, availableProcessors, totalRam, diskSpace, isEmulator, state, manufacturer, modelClass);
            }
        });
        this.nativeComponent.writeSessionDevice(sessionId, arch, model, availableProcessors, totalRam, diskSpace, isEmulator, state, manufacturer, modelClass);
    }

    private void writeSessionUser(String sessionId) throws Exception {
        final UserMetadata metadata = getUserMetadata(sessionId);
        writeSessionPartFile(sessionId, SESSION_USER_TAG, new CodedOutputStreamWriteAction() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.21
            @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsController.CodedOutputStreamWriteAction
            public void writeTo(CodedOutputStream arg) throws Exception {
                SessionProtobufHelper.writeSessionUser(arg, metadata.getUserId(), null, null);
            }
        });
    }

    private void writeSessionEvent(CodedOutputStream cos, Thread thread, Throwable ex, long eventTime, String eventType, boolean includeAllThreads) throws Exception {
        Thread[] threads;
        Map<String, String> attributes;
        TrimmedThrowableData trimmedEx = new TrimmedThrowableData(ex, this.stackTraceTrimmingStrategy);
        Context context = getContext();
        BatteryState battery = BatteryState.get(context);
        Float batteryLevel = battery.getBatteryLevel();
        int batteryVelocity = battery.getBatteryVelocity();
        boolean proximityEnabled = CommonUtils.getProximitySensorEnabled(context);
        int orientation = context.getResources().getConfiguration().orientation;
        long usedRamBytes = CommonUtils.getTotalRamInBytes() - CommonUtils.calculateFreeRamInBytes(context);
        long diskUsedBytes = CommonUtils.calculateUsedDiskSpaceInBytes(Environment.getDataDirectory().getPath());
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = CommonUtils.getAppProcessInfo(context.getPackageName(), context);
        List<StackTraceElement[]> stacks = new LinkedList<>();
        StackTraceElement[] exceptionStack = trimmedEx.stacktrace;
        String buildId = this.appData.buildId;
        String appIdentifier = this.idManager.getAppIdentifier();
        if (includeAllThreads) {
            Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
            Thread[] threads2 = new Thread[allStackTraces.size()];
            int i = 0;
            for (Map.Entry<Thread, StackTraceElement[]> entry : allStackTraces.entrySet()) {
                threads2[i] = entry.getKey();
                stacks.add(this.stackTraceTrimmingStrategy.getTrimmedStackTrace(entry.getValue()));
                i++;
            }
            threads = threads2;
        } else {
            threads = new Thread[0];
        }
        if (!CommonUtils.getBooleanResourceValue(context, COLLECT_CUSTOM_KEYS, true)) {
            attributes = new TreeMap<>();
        } else {
            Map<String, String> attributes2 = this.userMetadata.getCustomKeys();
            if (attributes2 == null || attributes2.size() <= 1) {
                attributes = attributes2;
            } else {
                attributes = new TreeMap<>(attributes2);
            }
        }
        SessionProtobufHelper.writeSessionEvent(cos, eventTime, eventType, trimmedEx, thread, exceptionStack, threads, stacks, 8, attributes, this.logFileManager.getBytesForLog(), runningAppProcessInfo, orientation, appIdentifier, buildId, batteryLevel, batteryVelocity, proximityEnabled, usedRamBytes, diskUsedBytes);
        this.logFileManager.clearLog();
    }

    private void writeSessionPartsToSessionFile(File sessionBeginFile, String sessionId, int maxLoggedExceptionsCount) {
        Logger logger = Logger.getLogger();
        logger.d("Collecting session parts for ID " + sessionId);
        File[] fatalFiles = listFilesMatching(new FileNameContainsFilter(sessionId + SESSION_FATAL_TAG));
        boolean hasFatal = fatalFiles != null && fatalFiles.length > 0;
        Logger.getLogger().d(String.format(Locale.US, "Session %s has fatal exception: %s", sessionId, Boolean.valueOf(hasFatal)));
        File[] nonFatalFiles = listFilesMatching(new FileNameContainsFilter(sessionId + SESSION_NON_FATAL_TAG));
        boolean hasNonFatal = nonFatalFiles != null && nonFatalFiles.length > 0;
        Logger.getLogger().d(String.format(Locale.US, "Session %s has non-fatal exceptions: %s", sessionId, Boolean.valueOf(hasNonFatal)));
        if (hasFatal || hasNonFatal) {
            File[] trimmedNonFatalFiles = getTrimmedNonFatalFiles(sessionId, nonFatalFiles, maxLoggedExceptionsCount);
            File fatalFile = hasFatal ? fatalFiles[0] : null;
            synthesizeSessionFile(sessionBeginFile, sessionId, trimmedNonFatalFiles, fatalFile);
        } else {
            Logger logger2 = Logger.getLogger();
            logger2.d("No events present for session ID " + sessionId);
        }
        Logger logger3 = Logger.getLogger();
        logger3.d("Removing session part files for ID " + sessionId);
        deleteSessionPartFilesFor(sessionId);
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0095, code lost:
        if (1 == 0) goto L_0x0072;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0098, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void synthesizeSessionFile(java.io.File r12, java.lang.String r13, java.io.File[] r14, java.io.File r15) {
        /*
            r11 = this;
            java.lang.String r0 = "Failed to close CLS file"
            java.lang.String r1 = "Error flushing session file stream"
            r2 = 1
            if (r15 == 0) goto L_0x0009
            r3 = 1
            goto L_0x000a
        L_0x0009:
            r3 = 0
        L_0x000a:
            r4 = 0
            if (r3 == 0) goto L_0x0012
            java.io.File r5 = r11.getFatalSessionFilesDir()
            goto L_0x0016
        L_0x0012:
            java.io.File r5 = r11.getNonFatalSessionFilesDir()
        L_0x0016:
            boolean r6 = r5.exists()
            if (r6 != 0) goto L_0x001f
            r5.mkdirs()
        L_0x001f:
            r6 = 0
            r7 = 0
            com.google.firebase.crashlytics.internal.proto.ClsFileOutputStream r8 = new com.google.firebase.crashlytics.internal.proto.ClsFileOutputStream     // Catch: all -> 0x0076, Exception -> 0x0078
            r8.<init>(r5, r13)     // Catch: all -> 0x0076, Exception -> 0x0078
            r6 = r8
            com.google.firebase.crashlytics.internal.proto.CodedOutputStream r8 = com.google.firebase.crashlytics.internal.proto.CodedOutputStream.newInstance(r6)     // Catch: all -> 0x0076, Exception -> 0x0078
            r7 = r8
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch: all -> 0x0076, Exception -> 0x0078
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: all -> 0x0076, Exception -> 0x0078
            r9.<init>()     // Catch: all -> 0x0076, Exception -> 0x0078
            java.lang.String r10 = "Collecting SessionStart data for session ID "
            r9.append(r10)     // Catch: all -> 0x0076, Exception -> 0x0078
            r9.append(r13)     // Catch: all -> 0x0076, Exception -> 0x0078
            java.lang.String r9 = r9.toString()     // Catch: all -> 0x0076, Exception -> 0x0078
            r8.d(r9)     // Catch: all -> 0x0076, Exception -> 0x0078
            writeToCosFromFile(r7, r12)     // Catch: all -> 0x0076, Exception -> 0x0078
            r8 = 4
            long r9 = getCurrentTimestampSeconds()     // Catch: all -> 0x0076, Exception -> 0x0078
            r7.writeUInt64(r8, r9)     // Catch: all -> 0x0076, Exception -> 0x0078
            r8 = 5
            r7.writeBool(r8, r3)     // Catch: all -> 0x0076, Exception -> 0x0078
            r8 = 11
            r7.writeUInt32(r8, r2)     // Catch: all -> 0x0076, Exception -> 0x0078
            r2 = 12
            r8 = 3
            r7.writeEnum(r2, r8)     // Catch: all -> 0x0076, Exception -> 0x0078
            r11.writeInitialPartsTo(r7, r13)     // Catch: all -> 0x0076, Exception -> 0x0078
            writeNonFatalEventsTo(r7, r14, r13)     // Catch: all -> 0x0076, Exception -> 0x0078
            if (r3 == 0) goto L_0x0069
            writeToCosFromFile(r7, r15)     // Catch: all -> 0x0076, Exception -> 0x0078
        L_0x0069:
            com.google.firebase.crashlytics.internal.common.CommonUtils.flushOrLog(r7, r1)
            if (r4 == 0) goto L_0x0072
        L_0x006e:
            r11.closeWithoutRenamingOrLog(r6)
            goto L_0x0098
        L_0x0072:
            com.google.firebase.crashlytics.internal.common.CommonUtils.closeOrLog(r6, r0)
            goto L_0x0098
        L_0x0076:
            r2 = move-exception
            goto L_0x0099
        L_0x0078:
            r2 = move-exception
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch: all -> 0x0076
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: all -> 0x0076
            r9.<init>()     // Catch: all -> 0x0076
            java.lang.String r10 = "Failed to write session file for session ID: "
            r9.append(r10)     // Catch: all -> 0x0076
            r9.append(r13)     // Catch: all -> 0x0076
            java.lang.String r9 = r9.toString()     // Catch: all -> 0x0076
            r8.e(r9, r2)     // Catch: all -> 0x0076
            r4 = 1
            com.google.firebase.crashlytics.internal.common.CommonUtils.flushOrLog(r7, r1)
            if (r4 == 0) goto L_0x0072
            goto L_0x006e
        L_0x0098:
            return
        L_0x0099:
            com.google.firebase.crashlytics.internal.common.CommonUtils.flushOrLog(r7, r1)
            if (r4 == 0) goto L_0x00a2
            r11.closeWithoutRenamingOrLog(r6)
            goto L_0x00a5
        L_0x00a2:
            com.google.firebase.crashlytics.internal.common.CommonUtils.closeOrLog(r6, r0)
        L_0x00a5:
            goto L_0x00a7
        L_0x00a6:
            throw r2
        L_0x00a7:
            goto L_0x00a6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.CrashlyticsController.synthesizeSessionFile(java.io.File, java.lang.String, java.io.File[], java.io.File):void");
    }

    private static void writeNonFatalEventsTo(CodedOutputStream cos, File[] nonFatalFiles, String sessionId) {
        Arrays.sort(nonFatalFiles, CommonUtils.FILE_MODIFIED_COMPARATOR);
        for (File nonFatalFile : nonFatalFiles) {
            try {
                Logger.getLogger().d(String.format(Locale.US, "Found Non Fatal for session ID %s in %s ", sessionId, nonFatalFile.getName()));
                writeToCosFromFile(cos, nonFatalFile);
            } catch (Exception e) {
                Logger.getLogger().e("Error writting non-fatal to session.", e);
            }
        }
    }

    private void writeInitialPartsTo(CodedOutputStream cos, String sessionId) throws IOException {
        String[] strArr;
        for (String tag : INITIAL_SESSION_PART_TAGS) {
            File[] sessionPartFiles = listFilesMatching(new FileNameContainsFilter(sessionId + tag + ClsFileOutputStream.SESSION_FILE_EXTENSION));
            if (sessionPartFiles.length == 0) {
                Logger.getLogger().d("Can't find " + tag + " data for session ID " + sessionId);
            } else {
                Logger.getLogger().d("Collecting " + tag + " data for session ID " + sessionId);
                writeToCosFromFile(cos, sessionPartFiles[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendOrganizationIdToSessionFile(final String organizationId, File file) throws Exception {
        if (organizationId != null) {
            appendToProtoFile(file, new CodedOutputStreamWriteAction() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.22
                @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsController.CodedOutputStreamWriteAction
                public void writeTo(CodedOutputStream cos) throws Exception {
                    SessionProtobufHelper.writeSessionAppClsId(cos, organizationId);
                }
            });
        }
    }

    private static void writeToCosFromFile(CodedOutputStream cos, File file) throws IOException {
        if (!file.exists()) {
            Logger logger = Logger.getLogger();
            logger.e("Tried to include a file that doesn't exist: " + file.getName());
            return;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            copyToCodedOutputStream(fis, cos, (int) file.length());
        } finally {
            CommonUtils.closeOrLog(fis, "Failed to close file input stream.");
        }
    }

    private static void copyToCodedOutputStream(InputStream inStream, CodedOutputStream cos, int bufferLength) throws IOException {
        int numRead;
        byte[] buffer = new byte[bufferLength];
        int offset = 0;
        while (offset < buffer.length && (numRead = inStream.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        cos.writeRawBytes(buffer);
    }

    UserMetadata getUserMetadata() {
        return this.userMetadata;
    }

    private UserMetadata getUserMetadata(String sessionId) {
        if (isHandlingException()) {
            return this.userMetadata;
        }
        return new MetaDataStore(getFilesDir()).readUserData(sessionId);
    }

    boolean isHandlingException() {
        CrashlyticsUncaughtExceptionHandler crashlyticsUncaughtExceptionHandler = this.crashHandler;
        return crashlyticsUncaughtExceptionHandler != null && crashlyticsUncaughtExceptionHandler.isHandlingException();
    }

    File getFilesDir() {
        return this.fileStore.getFilesDir();
    }

    File getNativeSessionFilesDir() {
        return new File(getFilesDir(), NATIVE_SESSION_DIR);
    }

    File getFatalSessionFilesDir() {
        return new File(getFilesDir(), FATAL_SESSION_DIR);
    }

    File getNonFatalSessionFilesDir() {
        return new File(getFilesDir(), NONFATAL_SESSION_DIR);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void registerAnalyticsListener() {
        boolean analyticsRegistered = this.analyticsReceiver.register();
        Logger logger = Logger.getLogger();
        logger.d("Registered Firebase Analytics event listener for breadcrumbs: " + analyticsRegistered);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CreateReportSpiCall getCreateReportSpiCall(String reportsUrl, String ndkReportsUrl) {
        Context context = getContext();
        String overriddenHost = CommonUtils.getStringsFileValue(context, CRASHLYTICS_API_ENDPOINT);
        DefaultCreateReportSpiCall defaultCreateReportSpiCall = new DefaultCreateReportSpiCall(overriddenHost, reportsUrl, this.httpRequestFactory, CrashlyticsCore.getVersion());
        NativeCreateReportSpiCall nativeCreateReportSpiCall = new NativeCreateReportSpiCall(overriddenHost, ndkReportsUrl, this.httpRequestFactory, CrashlyticsCore.getVersion());
        return new CompositeCreateReportSpiCall(defaultCreateReportSpiCall, nativeCreateReportSpiCall);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSessionReports(AppSettingsData appSettings, boolean dataCollectionToken) throws Exception {
        File[] listCompleteSessionFiles;
        Context context = getContext();
        ReportUploader reportUploader = this.reportUploaderProvider.createReportUploader(appSettings);
        for (File finishedSessionFile : listCompleteSessionFiles()) {
            appendOrganizationIdToSessionFile(appSettings.organizationId, finishedSessionFile);
            Report report = new SessionReport(finishedSessionFile, SEND_AT_CRASHTIME_HEADER);
            this.backgroundWorker.submit(new SendReportRunnable(context, report, reportUploader, dataCollectionToken));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BlockingCrashEventListener implements AnalyticsReceiver.CrashlyticsOriginEventListener {
        private static final int APP_EXCEPTION_CALLBACK_TIMEOUT_MS = 2000;
        private final CountDownLatch eventLatch;

        private BlockingCrashEventListener() {
            this.eventLatch = new CountDownLatch(1);
        }

        public void awaitEvent() throws InterruptedException {
            Logger.getLogger().d("Background thread awaiting app exception callback from FA...");
            if (this.eventLatch.await(2000L, TimeUnit.MILLISECONDS)) {
                Logger.getLogger().d("App exception callback received from FA listener.");
            } else {
                Logger.getLogger().d("Timeout exceeded while awaiting app exception callback from FA listener.");
            }
        }

        @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver.CrashlyticsOriginEventListener
        public void onCrashlyticsOriginEvent(int id, Bundle extras) {
            String eventName = extras.getString("name");
            if ("_ae".equals(eventName)) {
                this.eventLatch.countDown();
            }
        }
    }

    private Task<Void> recordFatalFirebaseEvent(final long timestamp) {
        ThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        return Tasks.call(executor, new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsController.23
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                if (CrashlyticsController.this.firebaseCrashExists()) {
                    Logger.getLogger().d("Skipping logging Crashlytics event to Firebase, FirebaseCrash exists");
                    return null;
                } else if (CrashlyticsController.this.analyticsConnector == null) {
                    Logger.getLogger().d("Skipping logging Crashlytics event to Firebase, no Firebase Analytics");
                    return null;
                } else {
                    BlockingCrashEventListener blockingListener = new BlockingCrashEventListener();
                    CrashlyticsController.this.analyticsReceiver.setCrashlyticsOriginEventListener(blockingListener);
                    Logger.getLogger().d("Logging Crashlytics event to Firebase");
                    Bundle params = new Bundle();
                    params.putInt("fatal", 1);
                    params.putLong("timestamp", timestamp);
                    CrashlyticsController.this.analyticsConnector.logEvent(CrashlyticsController.FIREBASE_ANALYTICS_ORIGIN_CRASHLYTICS, "_ae", params);
                    blockingListener.awaitEvent();
                    CrashlyticsController.this.analyticsReceiver.setCrashlyticsOriginEventListener(null);
                    return null;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean firebaseCrashExists() {
        try {
            Class.forName("com.google.firebase.crash.FirebaseCrash");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /* loaded from: classes.dex */
    private final class ReportUploaderHandlingExceptionCheck implements ReportUploader.HandlingExceptionCheck {
        private ReportUploaderHandlingExceptionCheck() {
        }

        @Override // com.google.firebase.crashlytics.internal.report.ReportUploader.HandlingExceptionCheck
        public boolean isHandlingException() {
            return CrashlyticsController.this.isHandlingException();
        }
    }

    /* loaded from: classes.dex */
    private final class ReportUploaderFilesProvider implements ReportUploader.ReportFilesProvider {
        private ReportUploaderFilesProvider() {
        }

        @Override // com.google.firebase.crashlytics.internal.report.ReportUploader.ReportFilesProvider
        public File[] getCompleteSessionFiles() {
            return CrashlyticsController.this.listCompleteSessionFiles();
        }

        @Override // com.google.firebase.crashlytics.internal.report.ReportUploader.ReportFilesProvider
        public File[] getNativeReportFiles() {
            return CrashlyticsController.this.listNativeSessionFileDirectories();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class SendReportRunnable implements Runnable {
        private final Context context;
        private final boolean dataCollectionToken;
        private final Report report;
        private final ReportUploader reportUploader;

        public SendReportRunnable(Context context, Report report, ReportUploader reportUploader, boolean dataCollectionToken) {
            this.context = context;
            this.report = report;
            this.reportUploader = reportUploader;
            this.dataCollectionToken = dataCollectionToken;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (CommonUtils.canTryConnection(this.context)) {
                Logger.getLogger().d("Attempting to send crash report at time of crash...");
                this.reportUploader.uploadReport(this.report, this.dataCollectionToken);
            }
        }
    }

    static List<NativeSessionFile> getNativeSessionFiles(NativeSessionFileProvider fileProvider, String previousSessionId, Context context, File filesDir, byte[] logBytes) {
        MetaDataStore metaDataStore = new MetaDataStore(filesDir);
        File userFile = metaDataStore.getUserDataFileForSession(previousSessionId);
        File keysFile = metaDataStore.getKeysFileForSession(previousSessionId);
        byte[] binaryImageBytes = null;
        try {
            binaryImageBytes = NativeFileUtils.binaryImagesJsonFromMapsFile(fileProvider.getBinaryImagesFile(), context);
        } catch (Exception e) {
        }
        List<NativeSessionFile> nativeSessionFiles = new ArrayList<>();
        nativeSessionFiles.add(new BytesBackedNativeSessionFile("logs_file", "logs", logBytes));
        nativeSessionFiles.add(new BytesBackedNativeSessionFile("binary_images_file", "binaryImages", binaryImageBytes));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("crash_meta_file", "metadata", fileProvider.getMetadataFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("session_meta_file", "session", fileProvider.getSessionFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("app_meta_file", "app", fileProvider.getAppFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("device_meta_file", "device", fileProvider.getDeviceFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("os_meta_file", "os", fileProvider.getOsFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("minidump_file", "minidump", fileProvider.getMinidumpFile()));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("user_meta_file", "user", userFile));
        nativeSessionFiles.add(new FileBackedNativeSessionFile("keys_file", "keys", keysFile));
        return nativeSessionFiles;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class LogFileDirectoryProvider implements LogFileManager.DirectoryProvider {
        private static final String LOG_FILES_DIR = "log-files";
        private final FileStore rootFileStore;

        public LogFileDirectoryProvider(FileStore rootFileStore) {
            this.rootFileStore = rootFileStore;
        }

        @Override // com.google.firebase.crashlytics.internal.log.LogFileManager.DirectoryProvider
        public File getLogFileDir() {
            File logFileDir = new File(this.rootFileStore.getFilesDir(), LOG_FILES_DIR);
            if (!logFileDir.exists()) {
                logFileDir.mkdirs();
            }
            return logFileDir;
        }
    }
}
