package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes.dex */
final /* synthetic */ class CrashlyticsReportPersistence$$Lambda$1 implements FilenameFilter {
    private final String arg$1;

    private CrashlyticsReportPersistence$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static FilenameFilter lambdaFactory$(String str) {
        return new CrashlyticsReportPersistence$$Lambda$1(str);
    }

    @Override // java.io.FilenameFilter
    public boolean accept(File file, String str) {
        return CrashlyticsReportPersistence.lambda$deleteFinalizedReport$2(this.arg$1, file, str);
    }
}
