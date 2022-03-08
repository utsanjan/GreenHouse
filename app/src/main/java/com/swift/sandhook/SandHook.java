package com.swift.sandhook;

import com.swift.sandhook.annotation.HookMode;
import com.swift.sandhook.blacklist.HookBlackList;
import com.swift.sandhook.utils.FileUtils;
import com.swift.sandhook.utils.ReflectionUtils;
import com.swift.sandhook.utils.Unsafe;
import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes2.dex */
public class SandHook {
    public static Class artMethodClass;
    private static HookModeCallBack hookModeCallBack;
    private static HookResultCallBack hookResultCallBack;
    public static Field nativePeerField;
    public static int testAccessFlag;
    public static Object testOffsetArtMethod1;
    public static Object testOffsetArtMethod2;
    public static Method testOffsetMethod1;
    public static Method testOffsetMethod2;
    static Map<Member, HookWrapper.HookEntity> globalHookEntityMap = new ConcurrentHashMap();
    static Map<Method, HookWrapper.HookEntity> globalBackupMap = new ConcurrentHashMap();

    @FunctionalInterface
    /* loaded from: classes2.dex */
    public interface HookModeCallBack {
        int hookMode(Member member);
    }

    @FunctionalInterface
    /* loaded from: classes2.dex */
    public interface HookResultCallBack {
        void hookResult(boolean z, HookWrapper.HookEntity hookEntity);
    }

    public static native boolean canGetObject();

    public static native boolean compileMethod(Member member);

    public static native boolean deCompileMethod(Member member, boolean z);

    public static native boolean disableDex2oatInline(boolean z);

    public static native boolean disableVMInline();

    public static native void ensureDeclareClass(Member member, Method method);

    public static native void ensureMethodCached(Method method, Method method2);

    public static native Object getObjectNative(long j, long j2);

    private static native int hookMethod(Member member, Method method, Method method2, int i);

    private static native boolean initNative(int i, boolean z);

    public static native boolean is64Bit();

    public static native void setHookMode(int i);

    public static native void setInlineSafeCheck(boolean z);

    public static native boolean setNativeEntry(Member member, Member member2, long j);

    public static native void skipAllSafeCheck(boolean z);

    static {
        SandHookConfig.libLoader.loadLib();
        init();
    }

    public static void setHookModeCallBack(HookModeCallBack hookModeCallBack2) {
        hookModeCallBack = hookModeCallBack2;
    }

    public static void setHookResultCallBack(HookResultCallBack hookResultCallBack2) {
        hookResultCallBack = hookResultCallBack2;
    }

    private static boolean init() {
        initTestOffset();
        initThreadPeer();
        SandHookMethodResolver.init();
        return initNative(SandHookConfig.SDK_INT, SandHookConfig.DEBUG);
    }

    private static void initThreadPeer() {
        try {
            nativePeerField = getField(Thread.class, "nativePeer");
        } catch (NoSuchFieldException e) {
        }
    }

    public static void addHookClass(Class... hookWrapperClass) throws HookErrorException {
        HookWrapper.addHookClass(hookWrapperClass);
    }

    public static void addHookClass(ClassLoader classLoader, Class... hookWrapperClass) throws HookErrorException {
        HookWrapper.addHookClass(classLoader, hookWrapperClass);
    }

    public static synchronized void hook(HookWrapper.HookEntity entity) throws HookErrorException {
        int res;
        synchronized (SandHook.class) {
            try {
                if (entity != null) {
                    Member target = entity.target;
                    Method hook = entity.hook;
                    Method backup = entity.backup;
                    if (target == null || hook == null) {
                        throw new HookErrorException("null input");
                    } else if (globalHookEntityMap.containsKey(entity.target)) {
                        throw new HookErrorException("method <" + entity.target.toString() + "> has been hooked!");
                    } else if (!HookBlackList.canNotHook(target)) {
                        resolveStaticMethod(target);
                        resolveStaticMethod(backup);
                        if (backup != null && entity.resolveDexCache) {
                            SandHookMethodResolver.resolveMethod(hook, backup);
                        }
                        if (target instanceof Method) {
                            ((Method) target).setAccessible(true);
                        }
                        int mode = 0;
                        if (hookModeCallBack != null) {
                            mode = hookModeCallBack.hookMode(target);
                        }
                        globalHookEntityMap.put(entity.target, entity);
                        boolean z = false;
                        if (mode != 0) {
                            res = hookMethod(target, hook, backup, mode);
                        } else {
                            HookMode hookMode = (HookMode) hook.getAnnotation(HookMode.class);
                            res = hookMethod(target, hook, backup, hookMode == null ? 0 : hookMode.value());
                        }
                        if (res > 0 && backup != null) {
                            backup.setAccessible(true);
                        }
                        entity.hookMode = res;
                        if (hookResultCallBack != null) {
                            HookResultCallBack hookResultCallBack2 = hookResultCallBack;
                            if (res > 0) {
                                z = true;
                            }
                            hookResultCallBack2.hookResult(z, entity);
                        }
                        if (res >= 0) {
                            if (entity.backup != null) {
                                globalBackupMap.put(entity.backup, entity);
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("method <");
                            sb.append(entity.target.toString());
                            sb.append("> hook <");
                            sb.append(res == 1 ? "inline" : "replacement");
                            sb.append("> success!");
                            HookLog.d(sb.toString());
                        } else {
                            globalHookEntityMap.remove(entity.target);
                            throw new HookErrorException("hook method <" + entity.target.toString() + "> error in native!");
                        }
                    } else {
                        throw new HookErrorException("method <" + entity.target.toString() + "> can not hook, because of in blacklist!");
                    }
                } else {
                    throw new HookErrorException("null hook entity");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static final Object callOriginMethod(Member originMethod, Object thiz, Object... args) throws Throwable {
        HookWrapper.HookEntity hookEntity = globalHookEntityMap.get(originMethod);
        if (hookEntity == null || hookEntity.backup == null) {
            return null;
        }
        return callOriginMethod(hookEntity.backupIsStub, originMethod, hookEntity.backup, thiz, args);
    }

    public static final Object callOriginByBackup(Method backupMethod, Object thiz, Object... args) throws Throwable {
        HookWrapper.HookEntity hookEntity = globalBackupMap.get(backupMethod);
        if (hookEntity == null) {
            return null;
        }
        return callOriginMethod(hookEntity.backupIsStub, hookEntity.target, backupMethod, thiz, args);
    }

    public static final Object callOriginMethod(Member originMethod, Method backupMethod, Object thiz, Object[] args) throws Throwable {
        return callOriginMethod(true, originMethod, backupMethod, thiz, args);
    }

    public static final Object callOriginMethod(boolean backupIsStub, Member originMethod, Method backupMethod, Object thiz, Object[] args) throws Throwable {
        if (!backupIsStub && SandHookConfig.SDK_INT >= 24) {
            originMethod.getDeclaringClass();
            ensureDeclareClass(originMethod, backupMethod);
        }
        if (Modifier.isStatic(originMethod.getModifiers())) {
            try {
                return backupMethod.invoke(null, args);
            } catch (InvocationTargetException throwable) {
                if (throwable.getCause() != null) {
                    throw throwable.getCause();
                }
                throw throwable;
            }
        } else {
            try {
                return backupMethod.invoke(thiz, args);
            } catch (InvocationTargetException throwable2) {
                if (throwable2.getCause() != null) {
                    throw throwable2.getCause();
                }
                throw throwable2;
            }
        }
    }

    public static final void ensureBackupMethod(Method backupMethod) {
        HookWrapper.HookEntity entity;
        if (SandHookConfig.SDK_INT >= 24 && (entity = globalBackupMap.get(backupMethod)) != null) {
            ensureDeclareClass(entity.target, backupMethod);
        }
    }

    public static void resolveStaticMethod(Member method) {
        if (method != null) {
            try {
                if ((method instanceof Method) && Modifier.isStatic(method.getModifiers())) {
                    ((Method) method).setAccessible(true);
                    ((Method) method).invoke(new Object(), getFakeArgs((Method) method));
                }
            } catch (Throwable th) {
            }
        }
    }

    private static Object[] getFakeArgs(Method method) {
        Class[] pars = method.getParameterTypes();
        if (pars == null || pars.length == 0) {
            return new Object[]{new Object()};
        }
        return null;
    }

    public static Object getObject(long address) {
        long threadSelf = getThreadId();
        if (address == 0 || threadSelf == 0) {
            return null;
        }
        return getObjectNative(threadSelf, address);
    }

    public static boolean canGetObjectAddress() {
        return Unsafe.support();
    }

    public static long getObjectAddress(Object object) {
        return Unsafe.getObjectAddress(object);
    }

    private static void initTestOffset() {
        ArtMethodSizeTest.method1();
        ArtMethodSizeTest.method2();
        try {
            testOffsetMethod1 = ArtMethodSizeTest.class.getDeclaredMethod("method1", new Class[0]);
            testOffsetMethod2 = ArtMethodSizeTest.class.getDeclaredMethod("method2", new Class[0]);
            initTestAccessFlag();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("SandHook init error", e);
        }
    }

    private static void initTestAccessFlag() {
        if (hasJavaArtMethod()) {
            try {
                loadArtMethod();
                Field fieldAccessFlags = getField(artMethodClass, "accessFlags");
                testAccessFlag = ((Integer) fieldAccessFlags.get(testOffsetArtMethod1)).intValue();
            } catch (Exception e) {
            }
        } else {
            try {
                Field fieldAccessFlags2 = getField(Method.class, "accessFlags");
                testAccessFlag = ((Integer) fieldAccessFlags2.get(testOffsetMethod1)).intValue();
            } catch (Exception e2) {
            }
        }
    }

    private static void loadArtMethod() {
        try {
            Field fieldArtMethod = getField(Method.class, "artMethod");
            testOffsetArtMethod1 = fieldArtMethod.get(testOffsetMethod1);
            testOffsetArtMethod2 = fieldArtMethod.get(testOffsetMethod2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public static boolean hasJavaArtMethod() {
        if (SandHookConfig.SDK_INT >= 26) {
            return false;
        }
        if (artMethodClass != null) {
            return true;
        }
        try {
            if (SandHookConfig.initClassLoader == null) {
                artMethodClass = Class.forName("java.lang.reflect.ArtMethod");
            } else {
                artMethodClass = Class.forName("java.lang.reflect.ArtMethod", true, SandHookConfig.initClassLoader);
            }
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static Field getField(Class topClass, String fieldName) throws NoSuchFieldException {
        while (topClass != null && topClass != Object.class) {
            try {
                Field field = topClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return field;
            } catch (Exception e) {
                topClass = topClass.getSuperclass();
            }
        }
        throw new NoSuchFieldException(fieldName);
    }

    public static long getThreadId() {
        Field field = nativePeerField;
        if (field == null) {
            return 0L;
        }
        try {
            if (field.getType() == Integer.TYPE) {
                return nativePeerField.getInt(Thread.currentThread());
            }
            return nativePeerField.getLong(Thread.currentThread());
        } catch (IllegalAccessException e) {
            return 0L;
        }
    }

    public static boolean passApiCheck() {
        return ReflectionUtils.passApiCheck();
    }

    public static boolean tryDisableProfile(String selfPackageName) {
        if (SandHookConfig.SDK_INT < 24) {
            return false;
        }
        try {
            File profile = new File("/data/misc/profiles/cur/" + SandHookConfig.curUse + "/" + selfPackageName + "/primary.prof");
            if (!profile.getParentFile().exists()) {
                return false;
            }
            try {
                profile.delete();
                profile.createNewFile();
            } catch (Throwable th) {
            }
            FileUtils.chmod(profile.getAbsolutePath(), 256);
            return true;
        } catch (Throwable th2) {
            return false;
        }
    }
}
