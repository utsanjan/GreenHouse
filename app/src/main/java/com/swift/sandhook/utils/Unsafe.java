package com.swift.sandhook.utils;

import android.util.Log;
import com.swift.sandhook.HookLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public final class Unsafe {
    private static final String TAG = "Unsafe";
    private static Method arrayBaseOffsetMethod;
    private static Method arrayIndexScaleMethod;
    private static Method getIntMethod;
    private static Method getLongMethod;
    private static Class objectArrayClass = Object[].class;
    private static volatile boolean supported;
    private static Object unsafe;
    private static Class unsafeClass;

    static {
        supported = false;
        try {
            unsafeClass = Class.forName("sun.misc.Unsafe");
            Field theUnsafe = unsafeClass.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = theUnsafe.get(null);
        } catch (Exception e) {
            try {
                Field theUnsafe2 = unsafeClass.getDeclaredField("THE_ONE");
                theUnsafe2.setAccessible(true);
                unsafe = theUnsafe2.get(null);
            } catch (Exception e2) {
                Log.w(TAG, "Unsafe not found o.O");
            }
        }
        if (unsafe != null) {
            try {
                arrayBaseOffsetMethod = unsafeClass.getDeclaredMethod("arrayBaseOffset", Class.class);
                arrayIndexScaleMethod = unsafeClass.getDeclaredMethod("arrayIndexScale", Class.class);
                getIntMethod = unsafeClass.getDeclaredMethod("getInt", Object.class, Long.TYPE);
                getLongMethod = unsafeClass.getDeclaredMethod("getLong", Object.class, Long.TYPE);
                supported = true;
            } catch (Exception e3) {
            }
        }
    }

    public static boolean support() {
        return supported;
    }

    private Unsafe() {
    }

    public static int arrayBaseOffset(Class cls) {
        try {
            return ((Integer) arrayBaseOffsetMethod.invoke(unsafe, cls)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int arrayIndexScale(Class cls) {
        try {
            return ((Integer) arrayIndexScaleMethod.invoke(unsafe, cls)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getInt(Object array, long offset) {
        try {
            return ((Integer) getIntMethod.invoke(unsafe, array, Long.valueOf(offset))).intValue();
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getLong(Object array, long offset) {
        try {
            return ((Long) getLongMethod.invoke(unsafe, array, Long.valueOf(offset))).longValue();
        } catch (Exception e) {
            return 0L;
        }
    }

    public static long getObjectAddress(Object obj) {
        try {
            Object[] array = {obj};
            if (arrayIndexScale(objectArrayClass) == 8) {
                return getLong(array, arrayBaseOffset(objectArrayClass));
            }
            return 4294967295L & getInt(array, arrayBaseOffset(objectArrayClass));
        } catch (Exception e) {
            HookLog.e("get object address error", e);
            return -1L;
        }
    }
}
