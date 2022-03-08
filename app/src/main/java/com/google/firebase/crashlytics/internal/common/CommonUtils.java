package com.google.firebase.crashlytics.internal.common;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Debug;
import android.os.StatFs;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.google.firebase.crashlytics.internal.Logger;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public class CommonUtils {
    static final int BYTES_IN_A_GIGABYTE = 1073741824;
    static final int BYTES_IN_A_KILOBYTE = 1024;
    static final int BYTES_IN_A_MEGABYTE = 1048576;
    private static final boolean CLS_TRACE_DEFAULT = false;
    private static final String CLS_TRACE_PREFERENCE_NAME = "com.crashlytics.Trace";
    public static final int DEVICE_STATE_BETAOS = 8;
    public static final int DEVICE_STATE_COMPROMISEDLIBRARIES = 32;
    public static final int DEVICE_STATE_DEBUGGERATTACHED = 4;
    public static final int DEVICE_STATE_ISSIMULATOR = 1;
    public static final int DEVICE_STATE_JAILBROKEN = 2;
    public static final int DEVICE_STATE_VENDORINTERNAL = 16;
    private static final String GOOGLE_SDK = "google_sdk";
    static final String LEGACY_MAPPING_FILE_ID_RESOURCE_NAME = "com.crashlytics.android.build_id";
    public static final String LEGACY_SHARED_PREFS_NAME = "com.crashlytics.prefs";
    private static final String LOG_PRIORITY_NAME_ASSERT = "A";
    private static final String LOG_PRIORITY_NAME_DEBUG = "D";
    private static final String LOG_PRIORITY_NAME_ERROR = "E";
    private static final String LOG_PRIORITY_NAME_INFO = "I";
    private static final String LOG_PRIORITY_NAME_UNKNOWN = "?";
    private static final String LOG_PRIORITY_NAME_VERBOSE = "V";
    private static final String LOG_PRIORITY_NAME_WARN = "W";
    static final String MAPPING_FILE_ID_RESOURCE_NAME = "com.google.firebase.crashlytics.mapping_file_id";
    private static final String SDK = "sdk";
    private static final String SHA1_INSTANCE = "SHA-1";
    private static final String SHA256_INSTANCE = "SHA-256";
    public static final String SHARED_PREFS_NAME = "com.google.firebase.crashlytics";
    private static final long UNCALCULATED_TOTAL_RAM = -1;
    private static final String UNITY_EDITOR_VERSION = "com.google.firebase.crashlytics.unity_version";
    private static Boolean clsTrace = null;
    private static final char[] HEX_VALUES = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static long totalRamInBytes = -1;
    public static final Comparator<File> FILE_MODIFIED_COMPARATOR = new Comparator<File>() { // from class: com.google.firebase.crashlytics.internal.common.CommonUtils.1
        public int compare(File file0, File file1) {
            return (int) (file0.lastModified() - file1.lastModified());
        }
    };

    public static SharedPreferences getSharedPrefs(Context context) {
        return context.getSharedPreferences("com.google.firebase.crashlytics", 0);
    }

    public static SharedPreferences getLegacySharedPrefs(Context context) {
        return context.getSharedPreferences(LEGACY_SHARED_PREFS_NAME, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
        r1 = r5[1];
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String extractFieldFromSystemFile(java.io.File r8, java.lang.String r9) {
        /*
            java.lang.String r0 = "Failed to close system file reader."
            r1 = 0
            boolean r2 = r8.exists()
            if (r2 == 0) goto L_0x005f
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: all -> 0x003f, Exception -> 0x0041
            java.io.FileReader r4 = new java.io.FileReader     // Catch: all -> 0x003f, Exception -> 0x0041
            r4.<init>(r8)     // Catch: all -> 0x003f, Exception -> 0x0041
            r5 = 1024(0x400, float:1.435E-42)
            r3.<init>(r4, r5)     // Catch: all -> 0x003f, Exception -> 0x0041
            r2 = r3
        L_0x0017:
            java.lang.String r3 = r2.readLine()     // Catch: all -> 0x003f, Exception -> 0x0041
            r4 = r3
            if (r3 == 0) goto L_0x003b
            java.lang.String r3 = "\\s*:\\s*"
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3)     // Catch: all -> 0x003f, Exception -> 0x0041
            r5 = 2
            java.lang.String[] r5 = r3.split(r4, r5)     // Catch: all -> 0x003f, Exception -> 0x0041
            int r6 = r5.length     // Catch: all -> 0x003f, Exception -> 0x0041
            r7 = 1
            if (r6 <= r7) goto L_0x003a
            r6 = 0
            r6 = r5[r6]     // Catch: all -> 0x003f, Exception -> 0x0041
            boolean r6 = r6.equals(r9)     // Catch: all -> 0x003f, Exception -> 0x0041
            if (r6 == 0) goto L_0x003a
            r6 = r5[r7]     // Catch: all -> 0x003f, Exception -> 0x0041
            r1 = r6
            goto L_0x003b
        L_0x003a:
            goto L_0x0017
        L_0x003b:
            closeOrLog(r2, r0)
            goto L_0x005f
        L_0x003f:
            r3 = move-exception
            goto L_0x005b
        L_0x0041:
            r3 = move-exception
            com.google.firebase.crashlytics.internal.Logger r4 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch: all -> 0x003f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: all -> 0x003f
            r5.<init>()     // Catch: all -> 0x003f
            java.lang.String r6 = "Error parsing "
            r5.append(r6)     // Catch: all -> 0x003f
            r5.append(r8)     // Catch: all -> 0x003f
            java.lang.String r5 = r5.toString()     // Catch: all -> 0x003f
            r4.e(r5, r3)     // Catch: all -> 0x003f
            goto L_0x003b
        L_0x005b:
            closeOrLog(r2, r0)
            throw r3
        L_0x005f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.CommonUtils.extractFieldFromSystemFile(java.io.File, java.lang.String):java.lang.String");
    }

    public static int getCpuArchitectureInt() {
        return Architecture.getValue().ordinal();
    }

    /* loaded from: classes.dex */
    enum Architecture {
        X86_32,
        X86_64,
        ARM_UNKNOWN,
        PPC,
        PPC64,
        ARMV6,
        ARMV7,
        UNKNOWN,
        ARMV7S,
        ARM64;
        
        private static final Map<String, Architecture> matcher;

        static {
            HashMap hashMap = new HashMap(4);
            matcher = hashMap;
            hashMap.put("armeabi-v7a", ARMV7);
            matcher.put("armeabi", ARMV6);
            matcher.put("arm64-v8a", ARM64);
            matcher.put("x86", X86_32);
        }

        static Architecture getValue() {
            String arch = Build.CPU_ABI;
            if (TextUtils.isEmpty(arch)) {
                Logger.getLogger().d("Architecture#getValue()::Build.CPU_ABI returned null or empty");
                return UNKNOWN;
            }
            Architecture value = matcher.get(arch.toLowerCase(Locale.US));
            if (value == null) {
                return UNKNOWN;
            }
            return value;
        }
    }

    public static synchronized long getTotalRamInBytes() {
        long bytes;
        synchronized (CommonUtils.class) {
            if (totalRamInBytes == -1) {
                long bytes2 = 0;
                String result = extractFieldFromSystemFile(new File("/proc/meminfo"), "MemTotal");
                if (!TextUtils.isEmpty(result)) {
                    String result2 = result.toUpperCase(Locale.US);
                    try {
                        if (result2.endsWith("KB")) {
                            bytes2 = convertMemInfoToBytes(result2, "KB", 1024);
                        } else if (result2.endsWith("MB")) {
                            bytes2 = convertMemInfoToBytes(result2, "MB", 1048576);
                        } else if (result2.endsWith("GB")) {
                            bytes2 = convertMemInfoToBytes(result2, "GB", BYTES_IN_A_GIGABYTE);
                        } else {
                            Logger logger = Logger.getLogger();
                            logger.d("Unexpected meminfo format while computing RAM: " + result2);
                        }
                    } catch (NumberFormatException e) {
                        Logger logger2 = Logger.getLogger();
                        logger2.e("Unexpected meminfo format while computing RAM: " + result2, e);
                    }
                }
                totalRamInBytes = bytes2;
            }
            bytes = totalRamInBytes;
        }
        return bytes;
    }

    static long convertMemInfoToBytes(String memInfo, String notation, int notationMultiplier) {
        return Long.parseLong(memInfo.split(notation)[0].trim()) * notationMultiplier;
    }

    public static ActivityManager.RunningAppProcessInfo getAppProcessInfo(String packageName, Context context) {
        ActivityManager actman = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> processes = actman.getRunningAppProcesses();
        if (processes == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo info : processes) {
            if (info.processName.equals(packageName)) {
                return info;
            }
        }
        return null;
    }

    public static String streamToString(InputStream is) throws IOException {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String sha1(String source) {
        return hash(source, SHA1_INSTANCE);
    }

    public static String sha256(String source) {
        return hash(source, SHA256_INSTANCE);
    }

    public static String sha1(InputStream source) {
        return hash(source, SHA1_INSTANCE);
    }

    private static String hash(String s, String algorithm) {
        return hash(s.getBytes(), algorithm);
    }

    private static String hash(InputStream source, String sha1Instance) {
        try {
            MessageDigest digest = MessageDigest.getInstance(sha1Instance);
            byte[] buffer = new byte[1024];
            while (true) {
                int length = source.read(buffer);
                if (length == -1) {
                    return hexify(digest.digest());
                }
                digest.update(buffer, 0, length);
            }
        } catch (Exception e) {
            Logger.getLogger().e("Could not calculate hash for app icon.", e);
            return "";
        }
    }

    private static String hash(byte[] bytes, String algorithm) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.update(bytes);
            return hexify(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            Logger logger = Logger.getLogger();
            logger.e("Could not create hashing algorithm: " + algorithm + ", returning empty string.", e);
            return "";
        }
    }

    public static String createInstanceIdFrom(String... sliceIds) {
        if (sliceIds == null || sliceIds.length == 0) {
            return null;
        }
        List<String> sliceIdList = new ArrayList<>();
        for (String id : sliceIds) {
            if (id != null) {
                sliceIdList.add(id.replace("-", "").toLowerCase(Locale.US));
            }
        }
        Collections.sort(sliceIdList);
        StringBuilder sb = new StringBuilder();
        for (String id2 : sliceIdList) {
            sb.append(id2);
        }
        String concatValue = sb.toString();
        if (concatValue.length() > 0) {
            return sha1(concatValue);
        }
        return null;
    }

    public static long calculateFreeRamInBytes(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(mi);
        return mi.availMem;
    }

    public static long calculateUsedDiskSpaceInBytes(String path) {
        StatFs statFs = new StatFs(path);
        long blockSizeBytes = statFs.getBlockSize();
        long totalSpaceBytes = statFs.getBlockCount() * blockSizeBytes;
        long availableSpaceBytes = statFs.getAvailableBlocks() * blockSizeBytes;
        return totalSpaceBytes - availableSpaceBytes;
    }

    public static boolean getProximitySensorEnabled(Context context) {
        if (isEmulator(context)) {
            return false;
        }
        SensorManager sm = (SensorManager) context.getSystemService("sensor");
        Sensor prox = sm.getDefaultSensor(8);
        return prox != null;
    }

    public static void logControlled(Context context, String msg) {
        if (isClsTrace(context)) {
            Logger.getLogger().d(msg);
        }
    }

    public static void logControlledError(Context context, String msg, Throwable tr) {
        if (isClsTrace(context)) {
            Logger.getLogger().e(msg);
        }
    }

    public static void logControlled(Context context, int level, String tag, String msg) {
        if (isClsTrace(context)) {
            Logger.getLogger().log(level, msg);
        }
    }

    @Deprecated
    public static boolean isLoggingEnabled(Context context) {
        return false;
    }

    public static boolean isClsTrace(Context context) {
        if (clsTrace == null) {
            clsTrace = Boolean.valueOf(getBooleanResourceValue(context, CLS_TRACE_PREFERENCE_NAME, false));
        }
        return clsTrace.booleanValue();
    }

    public static boolean getBooleanResourceValue(Context context, String key, boolean defaultValue) {
        Resources resources;
        if (!(context == null || (resources = context.getResources()) == null)) {
            int id = getResourcesIdentifier(context, key, "bool");
            if (id > 0) {
                return resources.getBoolean(id);
            }
            int id2 = getResourcesIdentifier(context, key, "string");
            if (id2 > 0) {
                return Boolean.parseBoolean(context.getString(id2));
            }
        }
        return defaultValue;
    }

    public static int getResourcesIdentifier(Context context, String key, String resourceType) {
        Resources resources = context.getResources();
        return resources.getIdentifier(key, resourceType, getResourcePackageName(context));
    }

    public static boolean isEmulator(Context context) {
        String androidId = Settings.Secure.getString(context.getContentResolver(), "android_id");
        return SDK.equals(Build.PRODUCT) || GOOGLE_SDK.equals(Build.PRODUCT) || androidId == null;
    }

    public static boolean isRooted(Context context) {
        boolean isEmulator = isEmulator(context);
        String buildTags = Build.TAGS;
        if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
            return true;
        }
        File file = new File("/system/app/Superuser.apk");
        if (file.exists()) {
            return true;
        }
        File file2 = new File("/system/xbin/su");
        if (isEmulator || !file2.exists()) {
            return false;
        }
        return true;
    }

    public static boolean isDebuggerAttached() {
        return Debug.isDebuggerConnected() || Debug.waitingForDebugger();
    }

    public static int getDeviceState(Context context) {
        int deviceState = 0;
        if (isEmulator(context)) {
            deviceState = 0 | 1;
        }
        if (isRooted(context)) {
            deviceState |= 2;
        }
        if (isDebuggerAttached()) {
            return deviceState | 4;
        }
        return deviceState;
    }

    @Deprecated
    public static Cipher createCipher(int mode, String key) throws InvalidKeyException {
        throw new InvalidKeyException("This method is deprecated");
    }

    public static String hexify(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 255;
            char[] cArr = HEX_VALUES;
            hexChars[i * 2] = cArr[v >>> 4];
            hexChars[(i * 2) + 1] = cArr[v & 15];
        }
        return new String(hexChars);
    }

    public static byte[] dehexify(String string) {
        int len = string.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(string.charAt(i), 16) << 4) + Character.digit(string.charAt(i + 1), 16));
        }
        return data;
    }

    public static boolean isAppDebuggable(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    public static String getStringsFileValue(Context context, String key) {
        int id = getResourcesIdentifier(context, key, "string");
        if (id > 0) {
            return context.getString(id);
        }
        return "";
    }

    public static void closeOrLog(Closeable c, String message) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
                Logger.getLogger().e(message, e);
            }
        }
    }

    public static void flushOrLog(Flushable f, String message) {
        if (f != null) {
            try {
                f.flush();
            } catch (IOException e) {
                Logger.getLogger().e(message, e);
            }
        }
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static String padWithZerosToMaxIntWidth(int value) {
        if (value >= 0) {
            return String.format(Locale.US, "%1$10s", Integer.valueOf(value)).replace(' ', '0');
        }
        throw new IllegalArgumentException("value must be zero or greater");
    }

    public static boolean stringsEqualIncludingNull(String s1, String s2) {
        if (s1 == s2) {
            return true;
        }
        if (s1 != null) {
            return s1.equals(s2);
        }
        return false;
    }

    public static String getResourcePackageName(Context context) {
        int iconId = context.getApplicationContext().getApplicationInfo().icon;
        if (iconId > 0) {
            try {
                String resourcePackageName = context.getResources().getResourcePackageName(iconId);
                return resourcePackageName;
            } catch (Resources.NotFoundException e) {
                String resourcePackageName2 = context.getPackageName();
                return resourcePackageName2;
            }
        } else {
            String resourcePackageName3 = context.getPackageName();
            return resourcePackageName3;
        }
    }

    public static void copyStream(InputStream is, OutputStream os, byte[] buffer) throws IOException {
        while (true) {
            int count = is.read(buffer);
            if (count != -1) {
                os.write(buffer, 0, count);
            } else {
                return;
            }
        }
    }

    public static String logPriorityToString(int priority) {
        switch (priority) {
            case 2:
                return "V";
            case 3:
                return LOG_PRIORITY_NAME_DEBUG;
            case 4:
                return LOG_PRIORITY_NAME_INFO;
            case 5:
                return "W";
            case 6:
                return "E";
            case 7:
                return "A";
            default:
                return LOG_PRIORITY_NAME_UNKNOWN;
        }
    }

    public static String getAppIconHashOrNull(Context context) {
        InputStream is = null;
        String str = null;
        try {
            is = context.getResources().openRawResource(getAppIconResourceId(context));
            String sha1 = sha1(is);
            if (!isNullOrEmpty(sha1)) {
                str = sha1;
            }
            return str;
        } catch (Exception e) {
            Logger logger = Logger.getLogger();
            logger.w("Could not calculate hash for app icon:" + e.getMessage());
            return null;
        } finally {
            closeOrLog(is, "Failed to close icon input stream.");
        }
    }

    public static int getAppIconResourceId(Context context) {
        return context.getApplicationContext().getApplicationInfo().icon;
    }

    public static String getMappingFileId(Context context) {
        int id = getResourcesIdentifier(context, MAPPING_FILE_ID_RESOURCE_NAME, "string");
        if (id == 0) {
            id = getResourcesIdentifier(context, LEGACY_MAPPING_FILE_ID_RESOURCE_NAME, "string");
        }
        if (id == 0) {
            return null;
        }
        String mappingFileId = context.getResources().getString(id);
        return mappingFileId;
    }

    public static String resolveUnityEditorVersion(Context context) {
        int id = getResourcesIdentifier(context, UNITY_EDITOR_VERSION, "string");
        if (id == 0) {
            return null;
        }
        String version = context.getResources().getString(id);
        Logger logger = Logger.getLogger();
        logger.d("Unity Editor version is: " + version);
        return version;
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static boolean checkPermission(Context context, String permission) {
        int res = context.checkCallingOrSelfPermission(permission);
        return res == 0;
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void openKeyboard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService("input_method");
        if (imm != null) {
            imm.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    public static void finishAffinity(Context context, int resultCode) {
        if (context instanceof Activity) {
            finishAffinity((Activity) context, resultCode);
        }
    }

    public static void finishAffinity(Activity activity, int resultCode) {
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                activity.finishAffinity();
                return;
            }
            activity.setResult(resultCode);
            activity.finish();
        }
    }

    public static boolean canTryConnection(Context context) {
        if (!checkPermission(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
