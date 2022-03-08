package com.google.firebase.crashlytics.internal.report.model;

/* loaded from: classes.dex */
public class CreateReportRequest {
    public final String googleAppId;
    public final String organizationId;
    public final Report report;

    public CreateReportRequest(String organizationId, String googleAppId, Report report) {
        this.organizationId = organizationId;
        this.googleAppId = googleAppId;
        this.report = report;
    }
}
