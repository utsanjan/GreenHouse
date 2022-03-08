package com.swift.sandhook;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class SandHookMethodResolver {
    public static Field artMethodField;
    public static Field dexCacheField;
    public static Field dexMethodIndexField;
    public static Field fieldEntryPointFromCompiledCode;
    public static Field fieldEntryPointFromInterpreter;
    public static Field resolvedMethodsField;
    public static Object testArtMethod;
    public static Method testMethod;
    public static boolean canResolvedInJava = false;
    public static boolean isArtMethod = false;
    public static long resolvedMethodsAddress = 0;
    public static int dexMethodIndex = 0;
    public static long entryPointFromCompiledCode = 0;
    public static long entryPointFromInterpreter = 0;

    public static void init() {
        testMethod = SandHook.testOffsetMethod1;
        checkSupport();
    }

    private static void checkSupport() {
        try {
            artMethodField = SandHook.getField(Method.class, "artMethod");
            testArtMethod = artMethodField.get(testMethod);
            if (SandHook.hasJavaArtMethod() && testArtMethod.getClass() == SandHook.artMethodClass) {
                checkSupportForArtMethod();
                isArtMethod = true;
            } else if (testArtMethod instanceof Long) {
                checkSupportForArtMethodId();
                isArtMethod = false;
            } else {
                canResolvedInJava = false;
            }
        } catch (Exception e) {
        }
    }

    private static void checkSupportForArtMethod() throws Exception {
        try {
            dexMethodIndexField = SandHook.getField(SandHook.artMethodClass, "dexMethodIndex");
        } catch (NoSuchFieldException e) {
            dexMethodIndexField = SandHook.getField(SandHook.artMethodClass, "methodDexIndex");
        }
        dexCacheField = SandHook.getField(Class.class, "dexCache");
        Object dexCache = dexCacheField.get(testMethod.getDeclaringClass());
        resolvedMethodsField = SandHook.getField(dexCache.getClass(), "resolvedMethods");
        if (resolvedMethodsField.get(dexCache) instanceof Object[]) {
            canResolvedInJava = true;
        }
        try {
            try {
                fieldEntryPointFromCompiledCode = SandHook.getField(SandHook.artMethodClass, "entryPointFromQuickCompiledCode");
            } catch (Throwable th) {
                return;
            }
        } catch (Exception e2) {
            fieldEntryPointFromCompiledCode = SandHook.getField(SandHook.artMethodClass, "entryPointFromCompiledCode");
        }
        if (fieldEntryPointFromCompiledCode.getType() == Integer.TYPE) {
            entryPointFromCompiledCode = fieldEntryPointFromCompiledCode.getInt(testArtMethod);
        } else if (fieldEntryPointFromCompiledCode.getType() == Long.TYPE) {
            entryPointFromCompiledCode = fieldEntryPointFromCompiledCode.getLong(testArtMethod);
        }
        fieldEntryPointFromInterpreter = SandHook.getField(SandHook.artMethodClass, "entryPointFromInterpreter");
        if (fieldEntryPointFromInterpreter.getType() == Integer.TYPE) {
            entryPointFromInterpreter = fieldEntryPointFromInterpreter.getInt(testArtMethod);
        } else if (fieldEntryPointFromCompiledCode.getType() == Long.TYPE) {
            entryPointFromInterpreter = fieldEntryPointFromInterpreter.getLong(testArtMethod);
        }
    }

    private static void checkSupportForArtMethodId() throws Exception {
        dexMethodIndexField = SandHook.getField(Method.class, "dexMethodIndex");
        dexMethodIndex = ((Integer) dexMethodIndexField.get(testMethod)).intValue();
        dexCacheField = SandHook.getField(Class.class, "dexCache");
        Object dexCache = dexCacheField.get(testMethod.getDeclaringClass());
        resolvedMethodsField = SandHook.getField(dexCache.getClass(), "resolvedMethods");
        Object resolvedMethods = resolvedMethodsField.get(dexCache);
        if (resolvedMethods instanceof Long) {
            canResolvedInJava = false;
            resolvedMethodsAddress = ((Long) resolvedMethods).longValue();
        } else if (resolvedMethods instanceof long[]) {
            canResolvedInJava = true;
        } else if (resolvedMethods instanceof int[]) {
            canResolvedInJava = true;
        }
    }

    public static void resolveMethod(Method hook, Method backup) {
        if (!canResolvedInJava || artMethodField == null) {
            resolveInNative(hook, backup);
            return;
        }
        try {
            resolveInJava(hook, backup);
        } catch (Exception e) {
            resolveInNative(hook, backup);
        }
    }

    private static void resolveInJava(Method hook, Method backup) throws Exception {
        Object dexCache = dexCacheField.get(hook.getDeclaringClass());
        if (isArtMethod) {
            Object artMethod = artMethodField.get(backup);
            ((Object[]) resolvedMethodsField.get(dexCache))[((Integer) dexMethodIndexField.get(artMethod)).intValue()] = artMethod;
            return;
        }
        int dexMethodIndex2 = ((Integer) dexMethodIndexField.get(backup)).intValue();
        Object resolvedMethods = resolvedMethodsField.get(dexCache);
        if (resolvedMethods instanceof long[]) {
            long artMethod2 = ((Long) artMethodField.get(backup)).longValue();
            ((long[]) resolvedMethods)[dexMethodIndex2] = artMethod2;
        } else if (resolvedMethods instanceof int[]) {
            int artMethod3 = Long.valueOf(((Long) artMethodField.get(backup)).longValue()).intValue();
            ((int[]) resolvedMethods)[dexMethodIndex2] = artMethod3;
        } else {
            throw new UnsupportedOperationException("un support");
        }
    }

    private static void resolveInNative(Method hook, Method backup) {
        SandHook.ensureMethodCached(hook, backup);
    }
}
