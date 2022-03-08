package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class AppData {
    public final String buildId;
    public final String googleAppId;
    public final String installerPackageName;
    public final String packageName;
    public final String versionCode;
    public final String versionName;

    public static AppData create(Context context, IdManager idManager, String googleAppId, String buildId) throws PackageManager.NameNotFoundException {
        String packageName = context.getPackageName();
        String installerPackageName = idManager.getInstallerPackageName();
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
        String versionCode = Integer.toString(packageInfo.versionCode);
        String versionName = packageInfo.versionName == null ? IdManager.DEFAULT_VERSION_NAME : packageInfo.versionName;
        return new AppData(googleAppId, buildId, installerPackageName, packageName, versionCode, versionName);
    }

    public AppData(String googleAppId, String buildId, String installerPackageName, String packageName, String versionCode, String versionName) {
        this.googleAppId = googleAppId;
        this.buildId = buildId;
        this.installerPackageName = installerPackageName;
        this.packageName = packageName;
        this.versionCode = versionCode;
        this.versionName = versionName;
    }
}
