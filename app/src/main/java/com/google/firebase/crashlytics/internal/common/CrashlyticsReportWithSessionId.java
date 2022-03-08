package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
public abstract class CrashlyticsReportWithSessionId {
    public abstract CrashlyticsReport getReport();

    public abstract String getSessionId();

    public static CrashlyticsReportWithSessionId create(CrashlyticsReport report, String sessionId) {
        return new AutoValue_CrashlyticsReportWithSessionId(report, sessionId);
    }
}
