package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.util.Comparator;

/* loaded from: classes.dex */
final /* synthetic */ class CrashlyticsReportPersistence$$Lambda$5 implements Comparator {
    private static final CrashlyticsReportPersistence$$Lambda$5 instance = new CrashlyticsReportPersistence$$Lambda$5();

    private CrashlyticsReportPersistence$$Lambda$5() {
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        return CrashlyticsReportPersistence.lambda$static$0((File) obj, (File) obj2);
    }
}
