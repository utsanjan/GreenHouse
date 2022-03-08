package com.google.firebase.crashlytics.internal.settings.model;

import com.google.firebase.crashlytics.internal.common.InstallIdProvider;

/* loaded from: classes.dex */
public class SettingsRequest {
    public final String buildVersion;
    public final String deviceModel;
    public final String displayVersion;
    public final String googleAppId;
    public final InstallIdProvider installIdProvider;
    public final String instanceId;
    public final String osBuildVersion;
    public final String osDisplayVersion;
    public final int source;

    public SettingsRequest(String googleAppId, String deviceModel, String osBuildVersion, String osDisplayVersion, InstallIdProvider installIdProvier, String instanceId, String displayVersion, String buildVersion, int source) {
        this.googleAppId = googleAppId;
        this.deviceModel = deviceModel;
        this.osBuildVersion = osBuildVersion;
        this.osDisplayVersion = osDisplayVersion;
        this.installIdProvider = installIdProvier;
        this.instanceId = instanceId;
        this.displayVersion = displayVersion;
        this.buildVersion = buildVersion;
        this.source = source;
    }
}
