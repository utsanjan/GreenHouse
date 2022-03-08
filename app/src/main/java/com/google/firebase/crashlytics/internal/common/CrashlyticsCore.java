package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.BuildConfig;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsConnectorReceiver;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.persistence.FileStore;
import com.google.firebase.crashlytics.internal.persistence.FileStoreImpl;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.unity.ResourceUnityVersionProvider;
import com.google.firebase.crashlytics.internal.unity.UnityVersionProvider;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public class CrashlyticsCore {
    private static final float CLS_DEFAULT_PROCESS_DELAY = 1.0f;
    static final String CRASHLYTICS_REQUIRE_BUILD_ID = "com.crashlytics.RequireBuildId";
    static final boolean CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT = true;
    static final String CRASH_MARKER_FILE_NAME = "crash_marker";
    static final int DEFAULT_MAIN_HANDLER_TIMEOUT_SEC = 4;
    private static final String INITIALIZATION_MARKER_FILE_NAME = "initialization_marker";
    private static final String MISSING_BUILD_ID_MSG = "The Crashlytics build ID is missing. This occurs when Crashlytics tooling is absent from your app's build configuration. Please review Crashlytics onboarding instructions and ensure you have a valid Crashlytics account.";
    private final AnalyticsConnector analyticsConnector;
    private final FirebaseApp app;
    private CrashlyticsBackgroundWorker backgroundWorker;
    private final Context context;
    private CrashlyticsController controller;
    private ExecutorService crashHandlerExecutor;
    private CrashlyticsFileMarker crashMarker;
    private final DataCollectionArbiter dataCollectionArbiter;
    private boolean didCrashOnPreviousExecution;
    private final IdManager idManager;
    private CrashlyticsFileMarker initializationMarker;
    private CrashlyticsNativeComponent nativeComponent;
    private final long startTime;

    public CrashlyticsCore(FirebaseApp app, IdManager idManager, CrashlyticsNativeComponent nativeComponent, DataCollectionArbiter dataCollectionArbiter, AnalyticsConnector analyticsConnector) {
        this(app, idManager, nativeComponent, dataCollectionArbiter, analyticsConnector, ExecutorUtils.buildSingleThreadExecutorService("Crashlytics Exception Handler"));
    }

    CrashlyticsCore(FirebaseApp app, IdManager idManager, CrashlyticsNativeComponent nativeComponent, DataCollectionArbiter dataCollectionArbiter, AnalyticsConnector analyticsConnector, ExecutorService crashHandlerExecutor) {
        this.app = app;
        this.dataCollectionArbiter = dataCollectionArbiter;
        this.context = app.getApplicationContext();
        this.idManager = idManager;
        this.nativeComponent = nativeComponent;
        this.analyticsConnector = analyticsConnector;
        this.crashHandlerExecutor = crashHandlerExecutor;
        this.backgroundWorker = new CrashlyticsBackgroundWorker(crashHandlerExecutor);
        this.startTime = System.currentTimeMillis();
    }

    public boolean onPreExecute(SettingsDataProvider settingsProvider) {
        Exception e;
        boolean initializeSynchronously;
        String mappingFileId = CommonUtils.getMappingFileId(this.context);
        Logger logger = Logger.getLogger();
        logger.d("Mapping file ID is: " + mappingFileId);
        boolean requiresBuildId = CommonUtils.getBooleanResourceValue(this.context, CRASHLYTICS_REQUIRE_BUILD_ID, CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT);
        if (isBuildIdValid(mappingFileId, requiresBuildId)) {
            String googleAppId = this.app.getOptions().getApplicationId();
            try {
                Logger logger2 = Logger.getLogger();
                logger2.i("Initializing Crashlytics " + getVersion());
                FileStore fileStore = new FileStoreImpl(this.context);
                this.crashMarker = new CrashlyticsFileMarker(CRASH_MARKER_FILE_NAME, fileStore);
                this.initializationMarker = new CrashlyticsFileMarker(INITIALIZATION_MARKER_FILE_NAME, fileStore);
                HttpRequestFactory httpRequestFactory = new HttpRequestFactory();
                AppData appData = AppData.create(this.context, this.idManager, googleAppId, mappingFileId);
                UnityVersionProvider unityVersionProvider = new ResourceUnityVersionProvider(this.context);
                AnalyticsReceiver analyticsReceiver = new AnalyticsConnectorReceiver(this.analyticsConnector, new AnalyticsConnectorReceiver.BreadcrumbHandler() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsCore.1
                    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsConnectorReceiver.BreadcrumbHandler
                    public void dropBreadcrumb(String breadcrumb) {
                        CrashlyticsCore.this.log(breadcrumb);
                    }
                });
                Logger logger3 = Logger.getLogger();
                logger3.d("Installer package name is: " + appData.installerPackageName);
                this.controller = new CrashlyticsController(this.context, this.backgroundWorker, httpRequestFactory, this.idManager, this.dataCollectionArbiter, fileStore, this.crashMarker, appData, null, null, this.nativeComponent, unityVersionProvider, analyticsReceiver, this.analyticsConnector, settingsProvider);
                initializeSynchronously = didPreviousInitializationFail();
                checkForPreviousCrash();
            } catch (Exception e2) {
                e = e2;
            }
            try {
                this.controller.enableExceptionHandling(Thread.getDefaultUncaughtExceptionHandler(), settingsProvider);
                if (!initializeSynchronously || !CommonUtils.canTryConnection(this.context)) {
                    Logger.getLogger().d("Exception handling initialization successful");
                    return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
                }
                Logger.getLogger().d("Crashlytics did not finish previous background initialization. Initializing synchronously.");
                finishInitSynchronously(settingsProvider);
                return false;
            } catch (Exception e3) {
                e = e3;
                Logger.getLogger().e("Crashlytics was not started due to an exception during initialization", e);
                this.controller = null;
                return false;
            }
        } else {
            throw new IllegalStateException(MISSING_BUILD_ID_MSG);
        }
    }

    public Task<Void> doBackgroundInitializationAsync(final SettingsDataProvider settingsProvider) {
        return Utils.callTask(this.crashHandlerExecutor, new Callable<Task<Void>>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsCore.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Task<Void> call() throws Exception {
                return CrashlyticsCore.this.doBackgroundInitialization(settingsProvider);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> doBackgroundInitialization(SettingsDataProvider settingsProvider) {
        markInitializationStarted();
        this.controller.cleanInvalidTempFiles();
        try {
            this.controller.registerAnalyticsListener();
            Settings settingsData = settingsProvider.getSettings();
            if (!settingsData.getFeaturesData().collectReports) {
                Logger.getLogger().d("Collection of crash reports disabled in Crashlytics settings.");
                return Tasks.forException(new RuntimeException("Collection of crash reports disabled in Crashlytics settings."));
            }
            if (!this.controller.finalizeSessions(settingsData.getSessionData().maxCustomExceptionEvents)) {
                Logger.getLogger().d("Could not finalize previous sessions.");
            }
            return this.controller.submitAllReports(1.0f, settingsProvider.getAppSettings());
        } catch (Exception e) {
            Logger.getLogger().e("Crashlytics encountered a problem during asynchronous initialization.", e);
            return Tasks.forException(e);
        } finally {
            markInitializationComplete();
        }
    }

    public void setCrashlyticsCollectionEnabled(boolean enabled) {
        this.dataCollectionArbiter.setCrashlyticsDataCollectionEnabled(enabled);
    }

    public Task<Boolean> checkForUnsentReports() {
        return this.controller.checkForUnsentReports();
    }

    public Task<Void> sendUnsentReports() {
        return this.controller.sendUnsentReports();
    }

    public Task<Void> deleteUnsentReports() {
        return this.controller.deleteUnsentReports();
    }

    public static String getVersion() {
        return BuildConfig.VERSION_NAME;
    }

    public void logException(Throwable throwable) {
        this.controller.writeNonFatalException(Thread.currentThread(), throwable);
    }

    public void log(String msg) {
        long timestamp = System.currentTimeMillis() - this.startTime;
        this.controller.writeToLog(timestamp, msg);
    }

    public void setUserId(String identifier) {
        this.controller.setUserId(identifier);
    }

    public void setCustomKey(String key, String value) {
        this.controller.setCustomKey(key, value);
    }

    CrashlyticsController getController() {
        return this.controller;
    }

    private void finishInitSynchronously(final SettingsDataProvider settingsDataProvider) {
        Runnable runnable = new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsCore.3
            @Override // java.lang.Runnable
            public void run() {
                CrashlyticsCore.this.doBackgroundInitialization(settingsDataProvider);
            }
        };
        Future<?> future = this.crashHandlerExecutor.submit(runnable);
        Logger.getLogger().d("Crashlytics detected incomplete initialization on previous app launch. Will initialize synchronously.");
        try {
            future.get(4L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Logger.getLogger().e("Crashlytics was interrupted during initialization.", e);
        } catch (ExecutionException e2) {
            Logger.getLogger().e("Problem encountered during Crashlytics initialization.", e2);
        } catch (TimeoutException e3) {
            Logger.getLogger().e("Crashlytics timed out during initialization.", e3);
        }
    }

    void markInitializationStarted() {
        this.backgroundWorker.checkRunningOnThread();
        this.initializationMarker.create();
        Logger.getLogger().d("Initialization marker file created.");
    }

    void markInitializationComplete() {
        this.backgroundWorker.submit(new Callable<Boolean>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsCore.4
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                try {
                    boolean removed = CrashlyticsCore.this.initializationMarker.remove();
                    Logger logger = Logger.getLogger();
                    logger.d("Initialization marker file removed: " + removed);
                    return Boolean.valueOf(removed);
                } catch (Exception e) {
                    Logger.getLogger().e("Problem encountered deleting Crashlytics initialization marker.", e);
                    return false;
                }
            }
        });
    }

    boolean didPreviousInitializationFail() {
        return this.initializationMarker.isPresent();
    }

    private void checkForPreviousCrash() {
        Task<Boolean> task = this.backgroundWorker.submit(new Callable<Boolean>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsCore.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Boolean call() throws Exception {
                return Boolean.valueOf(CrashlyticsCore.this.controller.didCrashOnPreviousExecution());
            }
        });
        try {
            Boolean result = (Boolean) Utils.awaitEvenIfOnMainThread(task);
            this.didCrashOnPreviousExecution = Boolean.TRUE.equals(result);
        } catch (Exception e) {
            this.didCrashOnPreviousExecution = false;
        }
    }

    public boolean didCrashOnPreviousExecution() {
        return this.didCrashOnPreviousExecution;
    }

    static boolean isBuildIdValid(String buildId, boolean requiresBuildId) {
        if (!requiresBuildId) {
            Logger.getLogger().d("Configured not to require a build ID.");
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else if (!CommonUtils.isNullOrEmpty(buildId)) {
            return CRASHLYTICS_REQUIRE_BUILD_ID_DEFAULT;
        } else {
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, ".     |  | ");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".   \\ |  | /");
            Log.e(Logger.TAG, ".    \\    /");
            Log.e(Logger.TAG, ".     \\  /");
            Log.e(Logger.TAG, ".      \\/");
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, MISSING_BUILD_ID_MSG);
            Log.e(Logger.TAG, ".");
            Log.e(Logger.TAG, ".      /\\");
            Log.e(Logger.TAG, ".     /  \\");
            Log.e(Logger.TAG, ".    /    \\");
            Log.e(Logger.TAG, ".   / |  | \\");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".     |  |");
            Log.e(Logger.TAG, ".");
            return false;
        }
    }
}
