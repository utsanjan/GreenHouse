package com.applisto.appcloner.classes;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.UiModeManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Icon;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Display;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.Toast;
import com.applisto.appcloner.classes.util.SimpleCrypt;
import dalvik.system.DexClassLoader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;
import javax.security.cert.X509Certificate;

/* loaded from: classes2.dex */
public class Utils {
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID = "AppCloner";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE = "AppClonerHighImportance";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME = "App Cloner";
    private static final String APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE = "App Cloner High Importance";
    private static final String TAG = Utils.class.getSimpleName();
    private static Application sApplication;
    private static boolean sNotificationChannelCreated;
    private static boolean sNotificationChannelCreatedHighImportance;
    private static Icon sNotificationIcon;
    private static ClassLoader secondaryClassLoader;

    public static AlertDialog.Builder getDialogBuilder(Context context) {
        Context context2;
        if (!(context instanceof Activity)) {
            Activity runningActivity = getRunningActivity();
            String str = TAG;
            Log.i(str, "getDialogBuilder; runningActivity: " + runningActivity);
            if (runningActivity != null) {
                context = runningActivity;
            } else {
                context = context.getApplicationContext();
            }
        }
        String str2 = TAG;
        Log.i(str2, "getDialogBuilder; context: " + context);
        final boolean isActivity = getActivityContext(context) instanceof Activity;
        if (Build.VERSION.SDK_INT >= 21) {
            context2 = new ContextThemeWrapper(context, 16974123);
        } else {
            context2 = new ContextThemeWrapper(context, 16974120);
        }
        return new AlertDialog.Builder(context2) { // from class: com.applisto.appcloner.classes.Utils.1
            @Override // android.app.AlertDialog.Builder
            public AlertDialog create() {
                AlertDialog dialog = super.create();
                if (!isActivity) {
                    Log.i(Utils.TAG, "create; setting TYPE_SYSTEM_ALERT");
                    dialog.getWindow().setType(2003);
                } else {
                    Log.i(Utils.TAG, "create; not setting TYPE_SYSTEM_ALERT");
                }
                return dialog;
            }
        };
    }

    public static AlertDialog showDialog(Context context, CharSequence title, CharSequence message) {
        try {
            AlertDialog.Builder dialogBuilder = getDialogBuilder(context);
            if (!TextUtils.isEmpty(title)) {
                dialogBuilder.setTitle(title);
            }
            return dialogBuilder.setMessage(message).setPositiveButton(17039370, (DialogInterface.OnClickListener) null).show();
        } catch (Throwable t) {
            Log.w(TAG, t);
            try {
                Toast.makeText(context, message, 1).show();
            } catch (Throwable t1) {
                Log.w(TAG, t1);
            }
            return null;
        }
    }

    public static Application getApplication() {
        Class<?> activityThreadClass;
        Method[] methods;
        Method[] methods2;
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        if (Build.VERSION.SDK_INT >= 9) {
            Method m1 = activityThreadClass.getMethod("currentApplication", new Class[0]);
            sApplication = (Application) m1.invoke(null, new Object[0]);
            return sApplication;
        }
        for (Method method : activityThreadClass.getMethods()) {
            if ("currentActivityThread".equals(method.getName())) {
                Object currentActivityThread = method.invoke(null, new Object[0]);
                for (Method m2 : activityThreadClass.getMethods()) {
                    if ("getApplication".equals(m2.getName())) {
                        sApplication = (Application) m2.invoke(currentActivityThread, new Object[0]);
                        return sApplication;
                    }
                }
                continue;
            }
        }
        return null;
    }

    public static Activity getRunningActivity() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    return (Activity) activityField.get(activityRecord);
                }
            }
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
        return null;
    }

    public static String getAppName(Context context) {
        String name = "app";
        try {
            name = context.getApplicationInfo().packageName;
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        try {
            CharSequence label = context.getApplicationInfo().loadLabel(context.getPackageManager());
            if (TextUtils.isEmpty(label)) {
                return name;
            }
            String name2 = label.toString();
            return name2;
        } catch (Exception e2) {
            Log.w(TAG, e2);
            return name;
        }
    }

    public static String getVersionName(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle metaData = applicationInfo.metaData;
            return metaData.getString("com.applisto.appcloner.versionName");
        } catch (Exception e) {
            Log.w(TAG, e);
            return null;
        }
    }

    public static int dp2px(Context context, float dp) {
        return dp2px(context.getResources().getDisplayMetrics(), dp);
    }

    public static int dp2px(DisplayMetrics displayMetrics, float dp) {
        return (int) ((displayMetrics.densityDpi / 160.0f) * dp);
    }

    public static void keepDialogOpenOnOrientationChange(Dialog dialog) {
        try {
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = -2;
            lp.height = -2;
            dialog.getWindow().setAttributes(lp);
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    public static CharSequence getAppClonerResourceText(Context context, String resName, CharSequence defaultText) {
        try {
            Resources res = context.getPackageManager().getResourcesForApplication("com.applisto.appcloner");
            int resId = res.getIdentifier(resName, "string", "com.applisto.appcloner");
            return res.getText(resId, defaultText);
        } catch (Exception e) {
            Log.w(TAG, e);
            return defaultText;
        }
    }

    public static int getTargetSdkVersion(Context context) {
        return getTargetSdkVersion(context, context.getPackageName());
    }

    public static int getTargetSdkVersion(Context context, String packageName) {
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(packageName, 0);
            return ai.targetSdkVersion;
        } catch (Exception e) {
            Log.w(TAG, e);
            return -1;
        }
    }

    public static Context getActivityContext(Context context) {
        if ((context instanceof Activity) || !(context instanceof ContextThemeWrapper)) {
            return context;
        }
        return getActivityContext(((ContextThemeWrapper) context).getBaseContext());
    }

    public static String toString(InputStream inputStream, String encoding) throws IOException {
        return new String(readFully(inputStream, false), encoding);
    }

    public static byte[] readFully(InputStream inputStream, boolean close) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (true) {
                int length = inputStream.read(buffer);
                if (length == -1) {
                    break;
                }
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } finally {
            if (close) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static boolean deleteDirectory(File directory) {
        File[] files;
        File[] files2;
        boolean res = true;
        if (directory.getPath().contains("/cache/oat/")) {
            return true;
        }
        if (directory.exists() && directory.isDirectory() && (files2 = directory.listFiles()) != null) {
            boolean res2 = true;
            for (File file : files2) {
                if (file.isDirectory()) {
                    if (!deleteDirectory(file)) {
                        res2 = false;
                    }
                } else if (!deleteFile(file)) {
                    res2 = false;
                }
            }
            res = res2;
        }
        if (directory.delete() || (files = directory.listFiles()) == null || files.length <= 0) {
            return res;
        }
        for (File file2 : files) {
            file2.deleteOnExit();
        }
        return false;
    }

    public static boolean deleteFile(File file) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rws");
            byte[] buffer = new byte[102400];
            for (int length = (int) file.length(); length > 0; length -= buffer.length) {
                int size = Math.min(buffer.length, length);
                raf.write(buffer, 0, size);
            }
            raf.close();
        } catch (Exception e) {
            Log.w(TAG, e);
        }
        int length2 = file.getName().length();
        StringBuilder b = new StringBuilder();
        for (int n = 0; n < length2; n++) {
            b.append('A');
        }
        File renamedFile = new File(file.getParent(), b.toString());
        if (file.renameTo(renamedFile)) {
            return renamedFile.delete();
        }
        return file.delete();
    }

    public static void copy(InputStream from, OutputStream to) throws IOException {
        byte[] buf = new byte[4096];
        while (true) {
            int r = from.read(buf);
            if (r != -1) {
                to.write(buf, 0, r);
            } else {
                return;
            }
        }
    }

    public static void forceMkdir(File directory) throws IOException {
        if (directory.exists()) {
            if (!directory.isDirectory()) {
                String message = "File " + directory + " exists and is not a directory. Unable to create directory.";
                throw new IOException(message);
            }
        } else if (!directory.mkdirs() && !directory.isDirectory()) {
            String message2 = "Unable to create directory " + directory;
            throw new IOException(message2);
        }
    }

    public static Double getDouble(Bundle bundle, String key) {
        try {
            String s = bundle.getString(key).trim();
            return Double.valueOf(Double.parseDouble(s));
        } catch (Exception e) {
            try {
                return Double.valueOf(bundle.getDouble(key));
            } catch (Exception e2) {
                return null;
            }
        }
    }

    public static List<ViewParent> getViewRoots() {
        Object windowManager;
        List<ViewParent> viewParents;
        List<ViewParent> viewRoots = new ArrayList<>();
        try {
            if (Build.VERSION.SDK_INT >= 17) {
                windowManager = Class.forName("android.view.WindowManagerGlobal").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            } else {
                Field f = Class.forName("android.view.WindowManagerImpl").getDeclaredField("sWindowManager");
                f.setAccessible(true);
                windowManager = f.get(null);
            }
            Field rootsField = windowManager.getClass().getDeclaredField("mRoots");
            rootsField.setAccessible(true);
            Field stoppedField = Class.forName("android.view.ViewRootImpl").getDeclaredField("mStopped");
            stoppedField.setAccessible(true);
            if (Build.VERSION.SDK_INT >= 17) {
                Object roots = rootsField.get(windowManager);
                if (roots != null) {
                    if (roots.getClass().isArray()) {
                        viewParents = Arrays.asList((ViewParent[]) roots);
                    } else {
                        viewParents = (List) roots;
                    }
                    for (ViewParent viewParent : viewParents) {
                        boolean stopped = ((Boolean) stoppedField.get(viewParent)).booleanValue();
                        if (!stopped) {
                            viewRoots.add(viewParent);
                        }
                    }
                }
            } else {
                ViewParent[] viewParents2 = (ViewParent[]) rootsField.get(windowManager);
                for (ViewParent viewParent2 : viewParents2) {
                    boolean stopped2 = ((Boolean) stoppedField.get(viewParent2)).booleanValue();
                    if (!stopped2) {
                        viewRoots.add(viewParent2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return viewRoots;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Finally extract failed */
    public static synchronized ClassLoader getSecondaryClassLoader(Context context) throws Exception {
        ClassLoader classLoader;
        synchronized (Utils.class) {
            if (secondaryClassLoader == null) {
                File cacheDir = context.getCacheDir();
                File tempFile = File.createTempFile("natives_sec_blob", ".dex", cacheDir);
                FileOutputStream out = new FileOutputStream(tempFile);
                InputStream in = null;
                try {
                    try {
                        if (isDevDevice()) {
                            PackageInfo pi = context.getPackageManager().getPackageInfo(new String(Base64.decode("Y29tLmFwcGxpc3RvLmFwcGNsb25lci5jbGFzc2VzLnNlY29uZGFyeQ==", 0)), 64);
                            if (X509Certificate.getInstance(pi.signatures[0].toByteArray()).getPublicKey().hashCode() == 175676095) {
                                ZipFile zf = new ZipFile(pi.applicationInfo.publicSourceDir);
                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                copy(zf.getInputStream(zf.getEntry("classes.dex")), baos);
                                in = new ByteArrayInputStream(new SimpleCrypt("veXR89fv5n8vdJRVbc8hNsrpJsNQfGyZ").encrypt(baos.toByteArray()));
                                zf.close();
                                Log.w(TAG, "WARNING: Loaded non-bundled secondary classes for testing");
                                Toast.makeText(context, "WARNING: Loaded non-bundled secondary classes for testing", 1).show();
                            }
                        }
                    } catch (Exception e) {
                    }
                    if (in == null) {
                        in = context.getAssets().open("natives_sec_blob.dat");
                    }
                    ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
                    copy(in, baos2);
                    copy(new ByteArrayInputStream(new SimpleCrypt("veXR89fv5n8vdJRVbc8hNsrpJsNQfGyZ").decrypt(baos2.toByteArray())), out);
                    in.close();
                    out.close();
                    File optimizedDirectory = context.getDir("opt", 0);
                    deleteDirectory(optimizedDirectory);
                    optimizedDirectory.mkdirs();
                    secondaryClassLoader = new DexClassLoader(tempFile.getAbsolutePath(), optimizedDirectory.getAbsolutePath(), null, context.getClassLoader());
                    tempFile.delete();
                } catch (Throwable th) {
                    out.close();
                    throw th;
                }
            }
            classLoader = secondaryClassLoader;
        }
        return classLoader;
    }

    public static String getBuildSerial() {
        String buildSerial = Build.SERIAL;
        if (!"unknown".equals(buildSerial)) {
            return buildSerial;
        }
        try {
            return Settings.Secure.getString(getApplication().getContentResolver(), "android_id");
        } catch (Exception e) {
            Log.w(TAG, e);
            return buildSerial;
        }
    }

    public static boolean isDevDevice() {
        String buildSerial = getBuildSerial();
        return "6129001759".equals(buildSerial) || "ce011711c4259a1205".equals(buildSerial) || "ZX1G522SR8".equals(buildSerial) || "9SAYF6EAWKQO6TTC".equals(buildSerial) || "022AQQ7N36083999".equals(buildSerial) || "015d2578341ff40f".equals(buildSerial) || "049ed51a251d4fa1".equals(buildSerial) || "112141000751".equals(buildSerial) || "504KPWQ0034257".equals(buildSerial) || "G090ME067423036J".equals(buildSerial) || "0324517083444".equals(buildSerial) || "OZH6OVS8AISCDQSK".equals(buildSerial) || "CB512B8AKQ".equals(buildSerial) || "HT64EBN02546".equals(buildSerial) || "e40cd6bf0704".equals(buildSerial) || "WCR7N18328001594".equals(buildSerial) || "f083b076".equals(buildSerial) || "ce12160c3c1ce51904".equals(buildSerial) || "HT79S1A03867".equals(buildSerial) || "71888131273a816b".equals(buildSerial) || "02157df2b40d042d".equals(buildSerial) || "1478167907b8f4c9".equals(buildSerial) || "2881930614047ece".equals(buildSerial) || "R58M22SH41J".equals(buildSerial) || "ef21cc30035bc03e".equals(buildSerial) || isEmulator();
    }

    private static boolean isEmulator() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }

    public static void setSmallNotificationIcon(Notification.Builder b) {
        setSmallNotificationIcon(b, false);
    }

    public static void setSmallNotificationIcon(Notification.Builder b, boolean highImportance) {
        NotificationManager notificationManager;
        NotificationManager notificationManager2;
        if (Build.VERSION.SDK_INT >= 23) {
            Icon icon = getNotificationIcon();
            if (icon != null) {
                b.setSmallIcon(icon);
            } else {
                b.setSmallIcon(17301569);
            }
            b.setColor(-16537100);
        } else {
            b.setSmallIcon(17301569);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                if (highImportance) {
                    if (!sNotificationChannelCreatedHighImportance) {
                        NotificationChannel channel = new NotificationChannel(APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE, APP_CLONER_NOTIFICATION_CHANNEL_NAME_HIGH_IMPORTANCE, 4);
                        Application application = getApplication();
                        if (!(application == null || (notificationManager2 = (NotificationManager) application.getSystemService("notification")) == null)) {
                            notificationManager2.createNotificationChannel(channel);
                            sNotificationChannelCreatedHighImportance = true;
                            Log.i(TAG, "setSmallNotificationIcon; notification channel created: AppClonerHighImportance");
                        }
                    }
                    b.setChannelId(APP_CLONER_NOTIFICATION_CHANNEL_ID_HIGH_IMPORTANCE);
                    b.setPriority(1);
                    return;
                }
                if (!sNotificationChannelCreated) {
                    NotificationChannel channel2 = new NotificationChannel(APP_CLONER_NOTIFICATION_CHANNEL_ID, APP_CLONER_NOTIFICATION_CHANNEL_NAME, 2);
                    Application application2 = getApplication();
                    if (!(application2 == null || (notificationManager = (NotificationManager) application2.getSystemService("notification")) == null)) {
                        notificationManager.createNotificationChannel(channel2);
                        sNotificationChannelCreated = true;
                        Log.i(TAG, "setSmallNotificationIcon; notification channel created: AppCloner");
                    }
                }
                b.setChannelId(APP_CLONER_NOTIFICATION_CHANNEL_ID);
            } catch (Throwable t) {
                Log.w(TAG, t);
            }
        } else if (highImportance && Build.VERSION.SDK_INT >= 16) {
            b.setPriority(1);
        }
    }

    private static Icon getNotificationIcon() {
        if (sNotificationIcon == null) {
            try {
                byte[] data = Base64.decode("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\n", 0);
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                sNotificationIcon = Icon.createWithBitmap(bitmap);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
        return sNotificationIcon;
    }

    public static Intent getLaunchIntent(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        Intent i = pm.getLaunchIntentForPackage(packageName);
        if (i != null) {
            return i;
        }
        Intent i2 = getFallbackLaunchIntent(pm, packageName);
        if (i2 != null || !isAndroidTv(context)) {
            return i2;
        }
        return pm.getLeanbackLaunchIntentForPackage(packageName);
    }

    private static Intent getFallbackLaunchIntent(PackageManager pm, String packageName) {
        Intent i = new Intent("android.intent.action.MAIN");
        i.setPackage(packageName);
        if (i.resolveActivity(pm) == null) {
            return null;
        }
        return i;
    }

    public static boolean isAndroidTv(Context context) {
        try {
            UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
            if (uiModeManager != null) {
                return uiModeManager.getCurrentModeType() == 4;
            }
            return false;
        } catch (Exception e) {
            Log.w(TAG, e);
            return false;
        }
    }

    public static boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static String getPackageSeededRandomString(Context context, String key) {
        return String.format("%08x", Long.valueOf(longHash(context.getPackageName() + key)));
    }

    private static long longHash(String string) {
        long hash = 1125899906842597L;
        int l = string.length();
        for (int i = 0; i < l; i++) {
            hash = (31 * hash) + string.charAt(i);
        }
        return hash;
    }

    public static void closeAndroidPieApiCompatibilityDialog() {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                Class<?> clazz = Class.forName("android.app.ActivityThread");
                Method declaredMethod = clazz.getDeclaredMethod("currentActivityThread", new Class[0]);
                declaredMethod.setAccessible(true);
                Object activityThread = declaredMethod.invoke(null, new Object[0]);
                Field field = clazz.getDeclaredField("mHiddenApiWarningShown");
                field.setAccessible(true);
                field.setBoolean(activityThread, true);
            }
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }

    public static void allowHiddenApiAccess() {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                Method forName = Class.class.getDeclaredMethod("forName", String.class);
                Method getDeclaredMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
                Class<?> vmRuntimeClass = (Class) forName.invoke(null, "dalvik.system.VMRuntime");
                Method getRuntime = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "getRuntime", null);
                Method setHiddenApiExemptions = (Method) getDeclaredMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
                Object vmRuntime = getRuntime.invoke(null, new Object[0]);
                setHiddenApiExemptions.invoke(vmRuntime, new String[]{"L"});
            }
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
    }

    public static boolean checkCaller(Context context) {
        int callingUid = Binder.getCallingUid();
        String[] packageNames = context.getPackageManager().getPackagesForUid(callingUid);
        return packageNames != null && packageNames.length == 1 && "com.applisto.appcloner".equals(packageNames[0]);
    }

    public static Point getRealScreenSize(Display d) {
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                widthPixels = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(d, new Object[0])).intValue();
                heightPixels = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(d, new Object[0])).intValue();
            } catch (Exception e) {
            }
        }
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception e2) {
            }
        }
        return new Point(widthPixels, heightPixels);
    }

    public static int getApplicationVersionCode(Context context) {
        return getApplicationVersionCode(context, context.getPackageName());
    }

    public static int getApplicationVersionCode(Context context, String packageName) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName, 0);
            return pi.versionCode;
        } catch (Exception e) {
            return -1;
        }
    }
}
