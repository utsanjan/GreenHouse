package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

/* loaded from: classes.dex */
final class AutoValue_CrashlyticsReport_Session_Event_Log extends CrashlyticsReport.Session.Event.Log {
    private final String content;

    private AutoValue_CrashlyticsReport_Session_Event_Log(String content) {
        this.content = content;
    }

    @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log
    public String getContent() {
        return this.content;
    }

    public String toString() {
        return "Log{content=" + this.content + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CrashlyticsReport.Session.Event.Log)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Log that = (CrashlyticsReport.Session.Event.Log) o;
        return this.content.equals(that.getContent());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return h$ ^ this.content.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder extends CrashlyticsReport.Session.Event.Log.Builder {
        private String content;

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder
        public CrashlyticsReport.Session.Event.Log.Builder setContent(String content) {
            if (content != null) {
                this.content = content;
                return this;
            }
            throw new NullPointerException("Null content");
        }

        @Override // com.google.firebase.crashlytics.internal.model.CrashlyticsReport.Session.Event.Log.Builder
        public CrashlyticsReport.Session.Event.Log build() {
            String missing = "";
            if (this.content == null) {
                missing = missing + " content";
            }
            if (missing.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Event_Log(this.content);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }
}
