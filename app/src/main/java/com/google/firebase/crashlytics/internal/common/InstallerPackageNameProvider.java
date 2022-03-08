package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes.dex */
class InstallerPackageNameProvider {
    private static final String NO_INSTALLER_PACKAGE_NAME = "";
    private String installerPackageName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized String getInstallerPackageName(Context appContext) {
        if (this.installerPackageName == null) {
            this.installerPackageName = loadInstallerPackageName(appContext);
        }
        return "".equals(this.installerPackageName) ? null : this.installerPackageName;
    }

    private static String loadInstallerPackageName(Context appContext) {
        PackageManager pm = appContext.getPackageManager();
        String hostAppPackageName = appContext.getPackageName();
        String installerPackageName = pm.getInstallerPackageName(hostAppPackageName);
        return installerPackageName == null ? "" : installerPackageName;
    }
}
