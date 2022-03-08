package com.google.firebase.crashlytics.internal.settings.model;

/* loaded from: classes.dex */
public class AppSettingsData {
    public static final String STATUS_ACTIVATED = "activated";
    public static final String STATUS_CONFIGURED = "configured";
    public static final String STATUS_NEW = "new";
    public final String bundleId;
    public final int nativeReportUploadVariant;
    public final String ndkReportsUrl;
    public final String organizationId;
    public final int reportUploadVariant;
    public final String reportsUrl;
    public final String status;
    public final boolean updateRequired;
    public final String url;

    public AppSettingsData(String status, String url, String reportsUrl, String ndkReportsUrl, String bundleId, String organizationId, boolean updateRequired, int reportUploadVariant, int nativeReportUploadVariant) {
        this.status = status;
        this.url = url;
        this.reportsUrl = reportsUrl;
        this.ndkReportsUrl = ndkReportsUrl;
        this.bundleId = bundleId;
        this.organizationId = organizationId;
        this.updateRequired = updateRequired;
        this.reportUploadVariant = reportUploadVariant;
        this.nativeReportUploadVariant = nativeReportUploadVariant;
    }

    public AppSettingsData(String status, String url, String reportsUrl, String ndkReportsUrl, boolean updateRequired) {
        this(status, url, reportsUrl, ndkReportsUrl, null, null, updateRequired, 0, 0);
    }
}
