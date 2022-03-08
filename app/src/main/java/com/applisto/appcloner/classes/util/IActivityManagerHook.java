package com.applisto.appcloner.classes.util;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: classes2.dex */
public abstract class IActivityManagerHook {
    private static final String TAG = IActivityManagerHook.class.getSimpleName();

    protected abstract InvocationHandler getInvocationHandler(Object obj);

    public void install() throws Exception {
        Object singleton;
        Class<?> activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
        Method m = activityManagerNativeClass.getMethod("getDefault", new Class[0]);
        Object originalActivityManager = m.invoke(null, new Object[0]);
        String str = TAG;
        Log.i(str, "install; originalActivityManager: " + originalActivityManager);
        InvocationHandler invocationHandler = getInvocationHandler(originalActivityManager);
        Class<?> iActivityManagerClass = Class.forName("android.app.IActivityManager");
        Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{iActivityManagerClass}, invocationHandler);
        try {
            Field defaultField = activityManagerNativeClass.getDeclaredField("gDefault");
            defaultField.setAccessible(true);
            singleton = defaultField.get(null);
        } catch (Exception e) {
            Class<?> activityManagerClass = Class.forName("android.app.ActivityManager");
            Field f = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
            f.setAccessible(true);
            singleton = f.get(null);
        }
        Field instanceField = Class.forName("android.util.Singleton").getDeclaredField("mInstance");
        instanceField.setAccessible(true);
        instanceField.set(singleton, proxy);
    }
}
