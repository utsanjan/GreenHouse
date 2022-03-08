package com.google.firebase.crashlytics.internal.settings;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.FeaturesSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SessionSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.Settings;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class DefaultSettingsJsonTransform implements SettingsJsonTransform {
    @Override // com.google.firebase.crashlytics.internal.settings.SettingsJsonTransform
    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject json) throws JSONException {
        int settingsVersion = json.optInt("settings_version", 0);
        int cacheDuration = json.optInt("cache_duration", 3600);
        AppSettingsData appData = buildAppDataFrom(json.getJSONObject("app"));
        SessionSettingsData settingsData = buildSessionDataFrom(json.getJSONObject("session"));
        FeaturesSettingsData featureData = buildFeaturesSessionDataFrom(json.getJSONObject("features"));
        long expiresAtMillis = getExpiresAtFrom(currentTimeProvider, cacheDuration, json);
        return new SettingsData(expiresAtMillis, appData, settingsData, featureData, settingsVersion, cacheDuration);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Settings defaultSettings(CurrentTimeProvider currentTimeProvider) {
        JSONObject empty = new JSONObject();
        SessionSettingsData settingsData = buildSessionDataFrom(empty);
        FeaturesSettingsData featureData = buildFeaturesSessionDataFrom(empty);
        long expiresAtMillis = getExpiresAtFrom(currentTimeProvider, 3600L, empty);
        return new SettingsData(expiresAtMillis, null, settingsData, featureData, 0, 3600);
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsJsonTransform
    public JSONObject toJson(SettingsData settingsData) throws JSONException {
        return new JSONObject().put("expires_at", settingsData.expiresAtMillis).put("cache_duration", settingsData.cacheDuration).put("settings_version", settingsData.settingsVersion).put("features", toFeaturesJson(settingsData.featuresData)).put("app", toAppJson(settingsData.appData)).put("session", toSessionJson(settingsData.sessionData));
    }

    private static AppSettingsData buildAppDataFrom(JSONObject json) throws JSONException {
        String status = json.getString(NotificationCompat.CATEGORY_STATUS);
        String url = json.getString(ImagesContract.URL);
        String reportsUrl = json.getString("reports_url");
        String ndkReportsUrl = json.getString("ndk_reports_url");
        boolean updateRequired = json.optBoolean("update_required", false);
        return new AppSettingsData(status, url, reportsUrl, ndkReportsUrl, updateRequired);
    }

    private static FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject json) {
        boolean collectReports = json.optBoolean("collect_reports", true);
        return new FeaturesSettingsData(collectReports);
    }

    private static SessionSettingsData buildSessionDataFrom(JSONObject json) {
        int maxCustomExceptionEvents = json.optInt("max_custom_exception_events", 8);
        return new SessionSettingsData(maxCustomExceptionEvents, 4);
    }

    private static long getExpiresAtFrom(CurrentTimeProvider currentTimeProvider, long cacheDurationSeconds, JSONObject json) {
        if (json.has("expires_at")) {
            long expiresAtMillis = json.optLong("expires_at");
            return expiresAtMillis;
        }
        long currentTimeMillis = currentTimeProvider.getCurrentTimeMillis();
        long expiresAtMillis2 = currentTimeMillis + (1000 * cacheDurationSeconds);
        return expiresAtMillis2;
    }

    private JSONObject toAppJson(AppSettingsData appData) throws JSONException {
        JSONObject json = new JSONObject().put(NotificationCompat.CATEGORY_STATUS, appData.status).put(ImagesContract.URL, appData.url).put("reports_url", appData.reportsUrl).put("ndk_reports_url", appData.ndkReportsUrl).put("update_required", appData.updateRequired);
        return json;
    }

    private JSONObject toFeaturesJson(FeaturesSettingsData features) throws JSONException {
        return new JSONObject().put("collect_reports", features.collectReports);
    }

    private JSONObject toSessionJson(SessionSettingsData data) throws JSONException {
        return new JSONObject().put("max_custom_exception_events", data.maxCustomExceptionEvents).put("max_complete_sessions_count", data.maxCompleteSessionsCount);
    }
}
