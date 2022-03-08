package com.google.firebase.crashlytics.internal.settings.model;

/* loaded from: classes.dex */
public class SettingsData implements Settings {
    public final AppSettingsData appData;
    public final int cacheDuration;
    public final long expiresAtMillis;
    public final FeaturesSettingsData featuresData;
    public final SessionSettingsData sessionData;
    public final int settingsVersion;

    public SettingsData(long expiresAtMillis, AppSettingsData appData, SessionSettingsData sessionData, FeaturesSettingsData featuresData, int settingsVersion, int cacheDuration) {
        this.expiresAtMillis = expiresAtMillis;
        this.appData = appData;
        this.sessionData = sessionData;
        this.featuresData = featuresData;
        this.settingsVersion = settingsVersion;
        this.cacheDuration = cacheDuration;
    }

    public AppSettingsData getAppSettingsData() {
        return this.appData;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.model.Settings
    public boolean isExpired(long currentTimeMillis) {
        return this.expiresAtMillis < currentTimeMillis;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.model.Settings
    public SessionSettingsData getSessionData() {
        return this.sessionData;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.model.Settings
    public FeaturesSettingsData getFeaturesData() {
        return this.featuresData;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.model.Settings
    public long getExpiresAtMillis() {
        return this.expiresAtMillis;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.model.Settings
    public int getSettingsVersion() {
        return this.settingsVersion;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.model.Settings
    public int getCacheDuration() {
        return this.cacheDuration;
    }
}
