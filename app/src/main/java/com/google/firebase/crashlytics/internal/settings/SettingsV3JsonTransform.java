package com.google.firebase.crashlytics.internal.settings;

import androidx.core.app.NotificationCompat;
import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.FeaturesSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SessionSettingsData;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import com.google.firebase.crashlytics.internal.settings.network.AbstractAppSpiCall;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class SettingsV3JsonTransform implements SettingsJsonTransform {
    private static final String CRASHLYTICS_APP_URL = "https://update.crashlytics.com/spi/v1/platforms/android/apps";
    private static final String CRASHLYTICS_APP_URL_FORMAT = "https://update.crashlytics.com/spi/v1/platforms/android/apps/%s";
    private static final String NDK_REPORTS_URL_FORMAT = "https://reports.crashlytics.com/sdk-api/v1/platforms/android/apps/%s/minidumps";
    private static final String REPORTS_URL_FORMAT = "https://reports.crashlytics.com/spi/v1/platforms/android/apps/%s/reports";

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsJsonTransform
    public SettingsData buildFromJson(CurrentTimeProvider currentTimeProvider, JSONObject json) throws JSONException {
        int settingsVersion = json.optInt("settings_version", 0);
        int cacheDuration = json.optInt("cache_duration", 3600);
        AppSettingsData appData = buildAppDataFrom(json.getJSONObject("fabric"), json.getJSONObject("app"));
        SessionSettingsData sessionData = defaultSessionData();
        FeaturesSettingsData featureData = buildFeaturesSessionDataFrom(json.getJSONObject("features"));
        long expiresAtMillis = getExpiresAtFrom(currentTimeProvider, cacheDuration, json);
        return new SettingsData(expiresAtMillis, appData, sessionData, featureData, settingsVersion, cacheDuration);
    }

    @Override // com.google.firebase.crashlytics.internal.settings.SettingsJsonTransform
    public JSONObject toJson(SettingsData settingsData) throws JSONException {
        return new JSONObject().put("expires_at", settingsData.expiresAtMillis).put("cache_duration", settingsData.cacheDuration).put("settings_version", settingsData.settingsVersion).put("features", toFeaturesJson(settingsData.featuresData)).put("app", toAppJson(settingsData.appData)).put("fabric", toFabricJson(settingsData.appData));
    }

    private static AppSettingsData buildAppDataFrom(JSONObject fabricJson, JSONObject appJson) throws JSONException {
        String url;
        String status = appJson.getString(NotificationCompat.CATEGORY_STATUS);
        boolean isNewApp = AppSettingsData.STATUS_NEW.equals(status);
        String bundleId = fabricJson.getString("bundle_id");
        String organizationId = fabricJson.getString(AbstractAppSpiCall.ORGANIZATION_ID_PARAM);
        if (isNewApp) {
            url = CRASHLYTICS_APP_URL;
        } else {
            url = String.format(Locale.US, CRASHLYTICS_APP_URL_FORMAT, bundleId);
        }
        String reportsUrl = String.format(Locale.US, REPORTS_URL_FORMAT, bundleId);
        String ndkReportsUrl = String.format(Locale.US, NDK_REPORTS_URL_FORMAT, bundleId);
        boolean updateRequired = appJson.optBoolean("update_required", false);
        int reportUploadVariant = appJson.optInt("report_upload_variant", 0);
        int nativeReportUploadVariant = appJson.optInt("native_report_upload_variant", 0);
        return new AppSettingsData(status, url, reportsUrl, ndkReportsUrl, bundleId, organizationId, updateRequired, reportUploadVariant, nativeReportUploadVariant);
    }

    private static FeaturesSettingsData buildFeaturesSessionDataFrom(JSONObject json) {
        boolean collectReports = json.optBoolean("collect_reports", true);
        return new FeaturesSettingsData(collectReports);
    }

    private static SessionSettingsData defaultSessionData() {
        return new SessionSettingsData(8, 4);
    }

    private JSONObject toFabricJson(AppSettingsData appData) throws JSONException {
        JSONObject json = new JSONObject().put("bundle_id", appData.bundleId).put(AbstractAppSpiCall.ORGANIZATION_ID_PARAM, appData.organizationId);
        return json;
    }

    private JSONObject toAppJson(AppSettingsData appData) throws JSONException {
        JSONObject json = new JSONObject().put(NotificationCompat.CATEGORY_STATUS, appData.status).put("update_required", appData.updateRequired).put("report_upload_variant", appData.reportUploadVariant).put("native_report_upload_variant", appData.nativeReportUploadVariant);
        return json;
    }

    private JSONObject toFeaturesJson(FeaturesSettingsData features) throws JSONException {
        return new JSONObject().put("collect_reports", features.collectReports);
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
}
