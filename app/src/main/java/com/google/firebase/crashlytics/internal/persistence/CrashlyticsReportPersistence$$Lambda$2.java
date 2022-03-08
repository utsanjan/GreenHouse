package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;
import java.io.FileFilter;

/* loaded from: classes.dex */
final /* synthetic */ class CrashlyticsReportPersistence$$Lambda$2 implements FileFilter {
    private final String arg$1;

    private CrashlyticsReportPersistence$$Lambda$2(String str) {
        this.arg$1 = str;
    }

    public static FileFilter lambdaFactory$(String str) {
        return new CrashlyticsReportPersistence$$Lambda$2(str);
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        return CrashlyticsReportPersistence.lambda$capAndGetOpenSessions$3(this.arg$1, file);
    }
}
