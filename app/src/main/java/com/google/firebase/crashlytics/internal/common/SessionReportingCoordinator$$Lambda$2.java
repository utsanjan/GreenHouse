package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.Comparator;

/* loaded from: classes.dex */
final /* synthetic */ class SessionReportingCoordinator$$Lambda$2 implements Comparator {
    private static final SessionReportingCoordinator$$Lambda$2 instance = new SessionReportingCoordinator$$Lambda$2();

    private SessionReportingCoordinator$$Lambda$2() {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return SessionReportingCoordinator.lambda$getSortedCustomAttributes$0((CrashlyticsReport.CustomAttribute) obj, (CrashlyticsReport.CustomAttribute) obj2);
    }
}
