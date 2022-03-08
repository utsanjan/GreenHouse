package com.google.firebase.crashlytics.internal.settings;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.common.DataCollectionArbiter;
import com.google.firebase.crashlytics.internal.common.DeliveryMechanism;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.firebase.crashlytics.internal.common.SystemCurrentTimeProvider;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SettingsRequest;
import com.google.firebase.crashlytics.internal.settings.network.DefaultSettingsSpiCall;
import com.google.firebase.crashlytics.internal.settings.network.SettingsSpiCall;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SettingsController implements SettingsDataProvider {
    private static final String PREFS_BUILD_INSTANCE_IDENTIFIER = "existing_instance_identifier";
    private static final String SETTINGS_URL_FORMAT = "https://firebase-settings.crashlytics.com/spi/v2/platforms/android/gmp/%s/settings";
    private final CachedSettingsIo cachedSettingsIo;
    private final Context context;
    private final CurrentTimeProvider currentTimeProvider;
    private final DataCollectionArbiter dataCollectionArbiter;
    private final SettingsJsonParser settingsJsonParser;
    private final SettingsRequest settingsRequest;
    private final SettingsSpiCall settingsSpiCall;
    private final AtomicReference<Settings> settings = new AtomicReference<>();
    private final AtomicReference<TaskCompletionSource<AppSettingsData>> appSettingsData = new AtomicReference<>(new TaskCompletionSource());

    SettingsController(Context context, SettingsRequest settingsRequest, CurrentTimeProvider currentTimeProvider, SettingsJsonParser settingsJsonParser, CachedSettingsIo cachedSettingsIo, SettingsSpiCall settingsSpiCall, DataCollectionArbiter dataCollectionArbiter) {
        this.context = context;
        this.settingsRequest = settingsRequest;
        this.currentTimeProvider = currentTimeProvider;
        this.settingsJsonParser = settingsJsonParser;
        this.cachedSettingsIo = cachedSettingsIo;
        this.settingsSpiCall = settingsSpiCall;
        this.dataCollectionArbiter = dataCollectionArbiter;
        this.settings.set(DefaultSettingsJsonTransform.defaultSettings(currentTimeProvider));
    }

    public static SettingsController create(Context context, String googleAppId, IdManager idManager, HttpRequestFactory httpRequestFactory, String versionCode, String versionName, String urlEndpoint, DataCollectionArbiter dataCollectionArbiter) {
        String installerPackageName = idManager.getInstallerPackageName();
        CurrentTimeProvider currentTimeProvider = new SystemCurrentTimeProvider();
        SettingsJsonParser settingsJsonParser = new SettingsJsonParser(currentTimeProvider);
        CachedSettingsIo cachedSettingsIo = new CachedSettingsIo(context);
        String settingsUrl = String.format(Locale.US, SETTINGS_URL_FORMAT, googleAppId);
        SettingsSpiCall settingsSpiCall = new DefaultSettingsSpiCall(urlEndpoint, settingsUrl, httpRequestFactory);
        String deviceModel = idManager.getModelName();
        String osBuildVersion = idManager.getOsBuildVersionString();
        String osDisplayVersion = idManager.getOsDisplayVersionString();
        String instanceId = CommonUtils.createInstanceIdFrom(CommonUtils.getMappingFileId(context), googleAppId, versionName, versionCode);
        int deliveryMechanismId = DeliveryMechanism.determineFrom(installerPackageName).getId();
        SettingsRequest settingsRequest = new SettingsRequest(googleAppId, deviceModel, osBuildVersion, osDisplayVersion, idManager, instanceId, versionName, versionCode, deliveryMechanismId);
        return new SettingsController(context, settingsRequest, currentTimeProvider, settingsJsonParser, cachedSettingsIo, settingsSpiCall, dataCollectionArbiter);
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsDataProvider
    public Settings getSettings() {
        return this.settings.get();
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsDataProvider
    public Task<AppSettingsData> getAppSettings() {
        return this.appSettingsData.get().getTask();
    }

    public Task<Void> loadSettingsData(Executor executor) {
        return loadSettingsData(SettingsCacheBehavior.USE_CACHE, executor);
    }

    public Task<Void> loadSettingsData(SettingsCacheBehavior cacheBehavior, Executor executor) {
        SettingsData cachedSettings;
        if (buildInstanceIdentifierChanged() || (cachedSettings = getCachedSettingsData(cacheBehavior)) == null) {
            SettingsData expiredSettings = getCachedSettingsData(SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION);
            if (expiredSettings != null) {
                this.settings.set(expiredSettings);
                this.appSettingsData.get().trySetResult(expiredSettings.getAppSettingsData());
            }
            return this.dataCollectionArbiter.waitForDataCollectionPermission().onSuccessTask(executor, new SuccessContinuation<Void, Void>() { // from class: com.google.firebase.crashlytics.internal.settings.SettingsController.1
                public Task<Void> then(Void aVoid) throws Exception {
                    JSONObject settingsJson = SettingsController.this.settingsSpiCall.invoke(SettingsController.this.settingsRequest, true);
                    if (settingsJson != null) {
                        SettingsData fetchedSettings = SettingsController.this.settingsJsonParser.parseSettingsJson(settingsJson);
                        SettingsController.this.cachedSettingsIo.writeCachedSettings(fetchedSettings.getExpiresAtMillis(), settingsJson);
                        SettingsController.this.logSettings(settingsJson, "Loaded settings: ");
                        SettingsController settingsController = SettingsController.this;
                        settingsController.setStoredBuildInstanceIdentifier(settingsController.settingsRequest.instanceId);
                        SettingsController.this.settings.set(fetchedSettings);
                        ((TaskCompletionSource) SettingsController.this.appSettingsData.get()).trySetResult(fetchedSettings.getAppSettingsData());
                        TaskCompletionSource<AppSettingsData> fetchedAppSettings = new TaskCompletionSource<>();
                        fetchedAppSettings.trySetResult(fetchedSettings.getAppSettingsData());
                        SettingsController.this.appSettingsData.set(fetchedAppSettings);
                    }
                    return Tasks.forResult(null);
                }
            });
        }
        this.settings.set(cachedSettings);
        this.appSettingsData.get().trySetResult(cachedSettings.getAppSettingsData());
        return Tasks.forResult(null);
    }

    private SettingsData getCachedSettingsData(SettingsCacheBehavior cacheBehavior) {
        SettingsData toReturn = null;
        try {
            if (!SettingsCacheBehavior.SKIP_CACHE_LOOKUP.equals(cacheBehavior)) {
                JSONObject settingsJson = this.cachedSettingsIo.readCachedSettings();
                if (settingsJson != null) {
                    SettingsData settingsData = this.settingsJsonParser.parseSettingsJson(settingsJson);
                    if (settingsData != null) {
                        logSettings(settingsJson, "Loaded cached settings: ");
                        long currentTimeMillis = this.currentTimeProvider.getCurrentTimeMillis();
                        if (!SettingsCacheBehavior.IGNORE_CACHE_EXPIRATION.equals(cacheBehavior) && settingsData.isExpired(currentTimeMillis)) {
                            Logger.getLogger().d("Cached settings have expired.");
                        }
                        toReturn = settingsData;
                        Logger.getLogger().d("Returning cached settings.");
                    } else {
                        Logger.getLogger().e("Failed to parse cached settings data.", null);
                    }
                } else {
                    Logger.getLogger().d("No cached settings data found.");
                }
            }
        } catch (Exception e) {
            Logger.getLogger().e("Failed to get cached settings", e);
        }
        return toReturn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logSettings(JSONObject json, String message) throws JSONException {
        Logger logger = Logger.getLogger();
        logger.d(message + json.toString());
    }

    private String getStoredBuildInstanceIdentifier() {
        SharedPreferences prefs = CommonUtils.getSharedPrefs(this.context);
        return prefs.getString(PREFS_BUILD_INSTANCE_IDENTIFIER, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setStoredBuildInstanceIdentifier(String buildInstanceIdentifier) {
        SharedPreferences prefs = CommonUtils.getSharedPrefs(this.context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PREFS_BUILD_INSTANCE_IDENTIFIER, buildInstanceIdentifier);
        editor.apply();
        return true;
    }

    boolean buildInstanceIdentifierChanged() {
        String existingInstanceIdentifier = getStoredBuildInstanceIdentifier();
        String currentInstanceIdentifier = this.settingsRequest.instanceId;
        return !existingInstanceIdentifier.equals(currentInstanceIdentifier);
    }
}
