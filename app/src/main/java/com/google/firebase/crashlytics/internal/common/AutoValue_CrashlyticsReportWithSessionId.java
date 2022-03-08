package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReportWithSessionId extends CrashlyticsReportWithSessionId {
    private final CrashlyticsReport report;
    private final String sessionId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_CrashlyticsReportWithSessionId(CrashlyticsReport report, String sessionId) {
        if (report != null) {
            this.report = report;
            if (sessionId != null) {
                this.sessionId = sessionId;
                return;
            }
            throw new NullPointerException("Null sessionId");
        }
        throw new NullPointerException("Null report");
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId
    public CrashlyticsReport getReport() {
        return this.report;
    }

    @Override // com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId
    public String getSessionId() {
        return this.sessionId;
    }

    public String toString() {
        return "CrashlyticsReportWithSessionId{report=" + this.report + ", sessionId=" + this.sessionId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReportWithSessionId)) {
            return false;
        }
        CrashlyticsReportWithSessionId that = (CrashlyticsReportWithSessionId) o;
        return this.report.equals(that.getReport()) && this.sessionId.equals(that.getSessionId());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((h$ ^ this.report.hashCode()) * 1000003) ^ this.sessionId.hashCode();
    }
}
