package com.swift.sandhook.utils;

import android.util.Log;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class ReflectionUtils {
    static Method addWhiteListMethod;
    public static Method forNameMethod;
    public static Method getMethodMethod;
    static Object vmRuntime;
    static Class vmRuntimeClass;

    static {
        try {
            getMethodMethod = Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
            forNameMethod = Class.class.getDeclaredMethod("forName", String.class);
            vmRuntimeClass = (Class) forNameMethod.invoke(null, "dalvik.system.VMRuntime");
            addWhiteListMethod = (Method) getMethodMethod.invoke(vmRuntimeClass, "setHiddenApiExemptions", new Class[]{String[].class});
            Method getVMRuntimeMethod = (Method) getMethodMethod.invoke(vmRuntimeClass, "getRuntime", null);
            vmRuntime = getVMRuntimeMethod.invoke(null, new Object[0]);
        } catch (Exception e) {
            Log.e("ReflectionUtils", "error get methods", e);
        }
    }

    public static boolean passApiCheck() {
        try {
            addReflectionWhiteList("Landroid/", "Lcom/android/", "Ljava/lang/", "Ldalvik/system/", "Llibcore/io/");
            return true;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return false;
        }
    }

    public static void addReflectionWhiteList(String... memberSigs) throws Throwable {
        addWhiteListMethod.invoke(vmRuntime, memberSigs);
    }
}
