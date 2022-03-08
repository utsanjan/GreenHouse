package com.applisto.appcloner.classes;

import android.content.Context;
import android.util.Log;
import com.applisto.appcloner.hooking.Hooking;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.MethodParams;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class LoadLibraryWorkaround {
    private static final String TAG = LoadLibraryWorkaround.class.getSimpleName();
    private static String sOriginalPackageName;
    private static String sPackageName;

    public static void install(Context context, String originalPackageName) {
        String str = TAG;
        Log.i(str, "install; originalPackageName: " + originalPackageName);
        sPackageName = context.getPackageName();
        sOriginalPackageName = originalPackageName;
        Hooking.initHooking(context);
        Hooking.addHookClass(Hook.class);
        Log.i(TAG, "install; hooks installed");
    }

    @HookClass(Runtime.class)
    /* loaded from: classes2.dex */
    public static class Hook {
        @HookMethodBackup("loadLibrary0")
        @MethodParams({ClassLoader.class, String.class})
        static Method loadLibrary0Backup;

        @MethodParams({ClassLoader.class, String.class})
        @HookMethod("loadLibrary0")
        public static void loadLibrary0Hook(Runtime thiz, ClassLoader classLoader, String libname) throws Throwable {
            try {
                Hooking.callInstanceOrigin(loadLibrary0Backup, thiz, classLoader, libname);
            } catch (Throwable t) {
                Log.w(LoadLibraryWorkaround.TAG, t.toString());
                int pos = LoadLibraryWorkaround.sPackageName.indexOf(libname);
                if (pos != -1) {
                    String libname2 = LoadLibraryWorkaround.sOriginalPackageName.substring(pos);
                    String str = LoadLibraryWorkaround.TAG;
                    Log.i(str, "loadLibrary0Hook; new libname: " + libname2);
                    Hooking.callInstanceOrigin(loadLibrary0Backup, thiz, classLoader, libname2);
                    return;
                }
                throw t;
            }
        }
    }
}
