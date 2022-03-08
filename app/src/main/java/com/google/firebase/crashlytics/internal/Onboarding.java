package com.google.firebase.crashlytics.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.settings.SettingsCacheBehavior;
import com.google.firebase.crashlytics.internal.settings.SettingsController;
import com.google.firebase.crashlytics.internal.settings.model.AppRequestData;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.network.CreateAppSpiCall;
import com.google.firebase.crashlytics.internal.settings.network.UpdateAppSpiCall;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public class Onboarding {
    static final String CRASHLYTICS_API_ENDPOINT = "com.crashlytics.ApiEndpoint";
    private final FirebaseApp app;
    private String applicationLabel;
    private final Context context;
    private DataCollectionArbiter dataCollectionArbiter;
    private IdManager idManager;
    private String installerPackageName;
    private PackageInfo packageInfo;
    private PackageManager packageManager;
    private String packageName;
    private final HttpRequestFactory requestFactory = new HttpRequestFactory();
    private String targetAndroidSdkVersion;
    private String versionCode;
    private String versionName;

    public Onboarding(FirebaseApp app, Context context, IdManager idManager, DataCollectionArbiter dataCollectionArbiter) {
        this.app = app;
        this.context = context;
        this.idManager = idManager;
        this.dataCollectionArbiter = dataCollectionArbiter;
    }

    public Context getContext() {
        return this.context;
    }

    private static String getVersion() {
        return CrashlyticsCore.getVersion();
    }

    public boolean onPreExecute() {
        String str;
        try {
            this.installerPackageName = this.idManager.getInstallerPackageName();
            this.packageManager = this.context.getPackageManager();
            String packageName = this.context.getPackageName();
            this.packageName = packageName;
            PackageInfo packageInfo = this.packageManager.getPackageInfo(packageName, 0);
            this.packageInfo = packageInfo;
            this.versionCode = Integer.toString(packageInfo.versionCode);
            if (this.packageInfo.versionName == null) {
                str = IdManager.DEFAULT_VERSION_NAME;
            } else {
                str = this.packageInfo.versionName;
            }
            this.versionName = str;
            this.applicationLabel = this.packageManager.getApplicationLabel(this.context.getApplicationInfo()).toString();
            this.targetAndroidSdkVersion = Integer.toString(this.context.getApplicationInfo().targetSdkVersion);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.getLogger().e("Failed init", e);
            return false;
        }
    }

    public void doOnboarding(final Executor backgroundExecutor, final SettingsController settingsDataController) {
        final String googleAppId = this.app.getOptions().getApplicationId();
        this.dataCollectionArbiter.waitForDataCollectionPermission().onSuccessTask(backgroundExecutor, new SuccessContinuation<Void, AppSettingsData>() { // from class: com.google.firebase.crashlytics.internal.Onboarding.2
            public Task<AppSettingsData> then(Void ignored) throws Exception {
                return settingsDataController.getAppSettings();
            }
        }).onSuccessTask(backgroundExecutor, new SuccessContinuation<AppSettingsData, Void>() { // from class: com.google.firebase.crashlytics.internal.Onboarding.1
            public Task<Void> then(AppSettingsData appSettingsData) throws Exception {
                try {
                    Onboarding.this.performAutoConfigure(appSettingsData, googleAppId, settingsDataController, backgroundExecutor, true);
                    return null;
                } catch (Exception e) {
                    Logger.getLogger().e("Error performing auto configuration.", e);
                    throw e;
                }
            }
        });
    }

    public SettingsController retrieveSettingsData(Context context, FirebaseApp app, Executor backgroundExecutor) {
        String googleAppId = app.getOptions().getApplicationId();
        SettingsController controller = SettingsController.create(context, googleAppId, this.idManager, this.requestFactory, this.versionCode, this.versionName, getOverridenSpiEndpoint(), this.dataCollectionArbiter);
        controller.loadSettingsData(backgroundExecutor).continueWith(backgroundExecutor, new Continuation<Void, Object>() { // from class: com.google.firebase.crashlytics.internal.Onboarding.3
            @Override // com.google.android.gms.tasks.Continuation
            public Object then(Task<Void> task) throws Exception {
                if (task.isSuccessful()) {
                    return null;
                }
                Logger.getLogger().e("Error fetching settings.", task.getException());
                return null;
            }
        });
        return controller;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performAutoConfigure(AppSettingsData appSettings, String googleAppId, SettingsController settingsController, Executor backgroundExecutor, boolean dataCollectionToken) {
        if (AppSettingsData.STATUS_NEW.equals(appSettings.status)) {
            if (performCreateApp(appSettings, googleAppId, dataCollectionToken)) {
                settingsController.loadSettingsData(SettingsCacheBehavior.SKIP_CACHE_LOOKUP, backgroundExecutor);
            } else {
                Logger.getLogger().e("Failed to create app with Crashlytics service.", null);
            }
        } else if (AppSettingsData.STATUS_CONFIGURED.equals(appSettings.status)) {
            settingsController.loadSettingsData(SettingsCacheBehavior.SKIP_CACHE_LOOKUP, backgroundExecutor);
        } else if (appSettings.updateRequired) {
            Logger.getLogger().d("Server says an update is required - forcing a full App update.");
            performUpdateApp(appSettings, googleAppId, dataCollectionToken);
        }
    }

    private boolean performCreateApp(AppSettingsData appSettings, String googleAppId, boolean dataCollectionToken) {
        AppRequestData requestData = buildAppRequest(appSettings.organizationId, googleAppId);
        return new CreateAppSpiCall(getOverridenSpiEndpoint(), appSettings.url, this.requestFactory, getVersion()).invoke(requestData, dataCollectionToken);
    }

    private boolean performUpdateApp(AppSettingsData appSettings, String googleAppId, boolean dataCollectionToken) {
        AppRequestData requestData = buildAppRequest(appSettings.organizationId, googleAppId);
        return new UpdateAppSpiCall(getOverridenSpiEndpoint(), appSettings.url, this.requestFactory, getVersion()).invoke(requestData, dataCollectionToken);
    }

    private AppRequestData buildAppRequest(String organizationId, String googleAppId) {
        Context context = getContext();
        String mappingFileId = CommonUtils.getMappingFileId(context);
        String instanceId = CommonUtils.createInstanceIdFrom(mappingFileId, googleAppId, this.versionName, this.versionCode);
        int source = DeliveryMechanism.determineFrom(this.installerPackageName).getId();
        String appIdentifier = getIdManager().getAppIdentifier();
        return new AppRequestData(organizationId, googleAppId, appIdentifier, this.versionName, this.versionCode, instanceId, this.applicationLabel, source, this.targetAndroidSdkVersion, "0");
    }

    String getOverridenSpiEndpoint() {
        return CommonUtils.getStringsFileValue(this.context, CRASHLYTICS_API_ENDPOINT);
    }

    private IdManager getIdManager() {
        return this.idManager;
    }
}
