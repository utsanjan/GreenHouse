package com.google.firebase.crashlytics.internal.settings.model;

/* loaded from: classes.dex */
public class AppRequestData {
    public final String appId;
    public final String buildVersion;
    public final String builtSdkVersion;
    public final String displayVersion;
    public final String googleAppId;
    public final String instanceIdentifier;
    public final String minSdkVersion;
    public final String name;
    public final String organizationId;
    public final int source;

    public AppRequestData(String organizationId, String googleAppId, String appId, String displayVersion, String buildVersion, String instanceIdentifier, String name, int source, String minSdkVersion, String builtSdkVersion) {
        this.organizationId = organizationId;
        this.googleAppId = googleAppId;
        this.appId = appId;
        this.displayVersion = displayVersion;
        this.buildVersion = buildVersion;
        this.instanceIdentifier = instanceIdentifier;
        this.name = name;
        this.source = source;
        this.minSdkVersion = minSdkVersion;
        this.builtSdkVersion = builtSdkVersion;
    }
}
