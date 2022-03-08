package com.google.firebase.crashlytics.internal.settings;

import com.google.firebase.crashlytics.internal.common.CurrentTimeProvider;
import com.google.firebase.crashlytics.internal.settings.model.SettingsData;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class SettingsJsonParser {
    private final CurrentTimeProvider currentTimeProvider;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SettingsJsonParser(CurrentTimeProvider currentTimeProvider) {
        this.currentTimeProvider = currentTimeProvider;
    }

    public SettingsData parseSettingsJson(JSONObject settingsJson) throws JSONException {
        int version = settingsJson.getInt("settings_version");
        SettingsJsonTransform jsonTransform = getJsonTransformForVersion(version);
        return jsonTransform.buildFromJson(this.currentTimeProvider, settingsJson);
    }

    private static SettingsJsonTransform getJsonTransformForVersion(int settingsVersion) {
        if (settingsVersion != 3) {
            return new DefaultSettingsJsonTransform();
        }
        return new SettingsV3JsonTransform();
    }
}
