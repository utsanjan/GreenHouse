package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.common.zzl;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public class AndroidUtilsLight {
    private static volatile int zzgu = -1;

    public static byte[] getPackageCertificateHashBytes(Context context, String str) throws PackageManager.NameNotFoundException {
        MessageDigest zzj;
        PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str, 64);
        if (packageInfo.signatures == null || packageInfo.signatures.length != 1 || (zzj = zzj("SHA1")) == null) {
            return null;
        }
        return zzj.digest(packageInfo.signatures[0].toByteArray());
    }

    public static MessageDigest zzj(String str) {
        MessageDigest instance;
        for (int i = 0; i < 2; i++) {
            try {
                instance = MessageDigest.getInstance(str);
            } catch (NoSuchAlgorithmException e) {
            }
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

    @Deprecated
    public static Context getDeviceProtectedStorageContext(Context context) {
        if (zzl.zzan()) {
            return zzl.getDeviceProtectedStorageContext(context);
        }
        return context;
    }
}
