package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes.dex */
final /* synthetic */ class CrashlyticsReportPersistence$$Lambda$6 implements FilenameFilter {
    private static final CrashlyticsReportPersistence$$Lambda$6 instance = new CrashlyticsReportPersistence$$Lambda$6();

    private CrashlyticsReportPersistence$$Lambda$6() {
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return CrashlyticsReportPersistence.lambda$static$1(file, str);
    }
}
