package com.applisto.appcloner.hooking;

import andhook.lib.AndHook;
import andhook.lib.HookHelper;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.SandHookConfig;
import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/* loaded from: classes2.dex */
public class Hooking {
    private static final String TAG = Hooking.class.getSimpleName();
    private static ExecutorService sHookExecutor;
    private static boolean sHookingInited;
    private static Boolean sUseSandHook;

    /* JADX WARN: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0064 A[Catch: all -> 0x007c, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0014, B:10:0x001e, B:12:0x0026, B:14:0x002f, B:19:0x003b, B:22:0x005c, B:24:0x0064, B:25:0x0074), top: B:30:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized boolean useSandHook() {
        /*
            java.lang.Class<com.applisto.appcloner.hooking.Hooking> r0 = com.applisto.appcloner.hooking.Hooking.class
            monitor-enter(r0)
            java.lang.Boolean r1 = com.applisto.appcloner.hooking.Hooking.sUseSandHook     // Catch: all -> 0x007c
            if (r1 != 0) goto L_0x0074
            r1 = 0
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)     // Catch: all -> 0x007c
            com.applisto.appcloner.hooking.Hooking.sUseSandHook = r2     // Catch: all -> 0x007c
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: all -> 0x007c
            r3 = 21
            if (r2 < r3) goto L_0x0074
            java.util.List r2 = getAbis()     // Catch: all -> 0x007c
            int r3 = r2.size()     // Catch: all -> 0x007c
            if (r3 <= 0) goto L_0x0025
            java.lang.Object r3 = r2.get(r1)     // Catch: all -> 0x007c
            java.lang.String r3 = (java.lang.String) r3     // Catch: all -> 0x007c
            goto L_0x0026
        L_0x0025:
            r3 = 0
        L_0x0026:
            java.lang.String r4 = "x86"
            boolean r4 = r4.equals(r3)     // Catch: all -> 0x007c
            r5 = 1
            if (r4 != 0) goto L_0x003a
            java.lang.String r4 = "x86_64"
            boolean r4 = r4.equals(r3)     // Catch: all -> 0x007c
            if (r4 == 0) goto L_0x0038
            goto L_0x003a
        L_0x0038:
            r4 = 0
            goto L_0x003b
        L_0x003a:
            r4 = 1
        L_0x003b:
            java.lang.String r6 = com.applisto.appcloner.hooking.Hooking.TAG     // Catch: all -> 0x007c
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: all -> 0x007c
            r7.<init>()     // Catch: all -> 0x007c
            java.lang.String r8 = "useSandHook; primaryAbi: "
            r7.append(r8)     // Catch: all -> 0x007c
            r7.append(r3)     // Catch: all -> 0x007c
            java.lang.String r8 = ", x86: "
            r7.append(r8)     // Catch: all -> 0x007c
            r7.append(r4)     // Catch: all -> 0x007c
            java.lang.String r7 = r7.toString()     // Catch: all -> 0x007c
            android.util.Log.i(r6, r7)     // Catch: all -> 0x007c
            if (r4 != 0) goto L_0x005c
            r1 = 1
        L_0x005c:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)     // Catch: all -> 0x007c
            com.applisto.appcloner.hooking.Hooking.sUseSandHook = r1     // Catch: all -> 0x007c
            if (r4 == 0) goto L_0x0074
            java.util.concurrent.ExecutorService r1 = java.util.concurrent.Executors.newSingleThreadExecutor()     // Catch: all -> 0x007c
            com.applisto.appcloner.hooking.Hooking.sHookExecutor = r1     // Catch: all -> 0x007c
            java.util.concurrent.ExecutorService r1 = com.applisto.appcloner.hooking.Hooking.sHookExecutor     // Catch: all -> 0x007c
            com.applisto.appcloner.hooking.Hooking$1 r5 = new com.applisto.appcloner.hooking.Hooking$1     // Catch: all -> 0x007c
            r5.<init>()     // Catch: all -> 0x007c
            r1.submit(r5)     // Catch: all -> 0x007c
        L_0x0074:
            java.lang.Boolean r1 = com.applisto.appcloner.hooking.Hooking.sUseSandHook     // Catch: all -> 0x007c
            boolean r1 = r1.booleanValue()     // Catch: all -> 0x007c
            monitor-exit(r0)
            return r1
        L_0x007c:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applisto.appcloner.hooking.Hooking.useSandHook():boolean");
    }

    private static List<String> getAbis() {
        if (Build.VERSION.SDK_INT >= 21) {
            return Arrays.asList(Build.SUPPORTED_ABIS);
        }
        return Collections.singletonList(Build.CPU_ABI);
    }

    public static synchronized void initHooking(Context context) {
        synchronized (Hooking.class) {
            if (!sHookingInited) {
                sHookingInited = true;
                if (useSandHook()) {
                    Log.i(TAG, "initHooking; SandHook");
                    if ("Q".equals(Build.VERSION.CODENAME)) {
                        SandHookConfig.SDK_INT = 29;
                    }
                    SandHookConfig.SELF_PACKAGE_NAME = context.getPackageName();
                    SandHook.disableVMInline();
                    SandHook.tryDisableProfile(SandHookConfig.SELF_PACKAGE_NAME);
                    SandHook.disableDex2oatInline(false);
                    if (SandHookConfig.SDK_INT >= 28) {
                        SandHook.passApiCheck();
                    }
                    return;
                }
                Log.i(TAG, "initHooking; AndHook");
                SandHookConfig.SELF_PACKAGE_NAME = context.getPackageName();
                AndHook.ensureNativeLibraryLoaded(null);
            }
        }
    }

    public static void addHookClass(Class hookWrapperClass) {
        String str = TAG;
        Log.i(str, "addHookClass; hookWrapperClass: " + hookWrapperClass);
        try {
            if (useSandHook()) {
                SandHook.addHookClass(hookWrapperClass);
            } else {
                Class targetHookClass = HookWrapper.getTargetHookClass(null, hookWrapperClass);
                if (targetHookClass != null) {
                    AndHook.ensureClassInitialized(targetHookClass);
                    Map<Member, HookWrapper.HookEntity> hookEntityMap = HookWrapper.getHookMethods(null, targetHookClass, hookWrapperClass);
                    for (final HookWrapper.HookEntity entity : hookEntityMap.values()) {
                        Class<?> declaringClass = entity.target.getDeclaringClass();
                        AndHook.ensureClassInitialized(declaringClass);
                        Method targetMethod = (Method) entity.target;
                        if (Modifier.isStatic(targetMethod.getModifiers())) {
                            try {
                                ArrayList<Class<?>> params = new ArrayList<>(Arrays.asList(targetMethod.getParameterTypes()));
                                params.add(0, Class.class);
                                Class<?>[] parameterTypes = (Class[]) params.toArray(new Class[0]);
                                Method newHookMethod = entity.hook.getDeclaringClass().getMethod(entity.hook.getName(), parameterTypes);
                                entity.hook = newHookMethod;
                            } catch (Exception e) {
                                Log.w(TAG, e);
                                System.exit(1);
                            }
                        }
                        if (sHookExecutor != null) {
                            sHookExecutor.submit(new Runnable() { // from class: com.applisto.appcloner.hooking.Hooking.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    Log.i(Hooking.TAG, "addHookClass; hooking using thread executor...");
                                    HookHelper.hook(HookWrapper.HookEntity.this.target, HookWrapper.HookEntity.this.hook);
                                }
                            });
                        } else {
                            HookHelper.hook(entity.target, entity.hook);
                        }
                    }
                } else {
                    throw new HookErrorException("Failed to find target hook class for " + hookWrapperClass.getName());
                }
            }
        } catch (Throwable t) {
            String str2 = TAG;
            Log.w(str2, "addHookClass; t: " + t);
        }
    }

    public static <T> T callStaticOrigin(Method backupMethod, Object... args) throws Throwable {
        if (useSandHook()) {
            return (T) SandHook.callOriginByBackup(backupMethod, null, args);
        }
        return (T) HookHelper.invokeObjectOrigin(null, args);
    }

    public static <T> T callInstanceOrigin(Method backupMethod, Object thiz, Object... args) throws Throwable {
        if (useSandHook()) {
            return (T) SandHook.callOriginByBackup(backupMethod, thiz, args);
        }
        return (T) HookHelper.invokeObjectOrigin(thiz, args);
    }
}
