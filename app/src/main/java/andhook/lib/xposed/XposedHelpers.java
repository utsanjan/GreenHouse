package andhook.lib.xposed;

import andhook.lib.xposed.XC_MethodHook;
import android.content.res.Resources;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.ZipFile;

/* loaded from: classes2.dex */
public final class XposedHelpers {
    private static final String LOGTAG = "XposedHelpers";
    private static final HashMap<String, Field> fieldCache = new HashMap<>();
    private static final HashMap<String, Method> methodCache = new HashMap<>();
    private static final HashMap<String, Constructor<?>> constructorCache = new HashMap<>();
    private static final WeakHashMap<Object, HashMap<String, Object>> additionalFields = new WeakHashMap<>();
    private static final HashMap<String, ThreadLocal<AtomicInteger>> sMethodDepth = new HashMap<>();

    public static Class<?> findClass(String className, ClassLoader classLoader) {
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        try {
            return classLoader.loadClass(className);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundError(e);
        }
    }

    public static Class<?> findClassIfExists(String className, ClassLoader classLoader) {
        try {
            return findClass(className, classLoader);
        } catch (ClassNotFoundError e) {
            return null;
        }
    }

    public static Field findField(Class<?> clazz, String fieldName) {
        String fullFieldName = clazz.getName() + '#' + fieldName;
        if (fieldCache.containsKey(fullFieldName)) {
            Field field = fieldCache.get(fullFieldName);
            if (field != null) {
                return field;
            }
            throw new NoSuchFieldError(fullFieldName);
        }
        try {
            Field field2 = findFieldRecursiveImpl(clazz, fieldName);
            field2.setAccessible(true);
            fieldCache.put(fullFieldName, field2);
            return field2;
        } catch (NoSuchFieldException e) {
            fieldCache.put(fullFieldName, null);
            throw new NoSuchFieldError(fullFieldName);
        }
    }

    public static Field findFieldIfExists(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName);
        } catch (NoSuchFieldError e) {
            return null;
        }
    }

    private static Field findFieldRecursiveImpl(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            while (true) {
                clazz = clazz.getSuperclass();
                if (clazz == null || clazz.equals(Object.class)) {
                    break;
                }
                try {
                    return clazz.getDeclaredField(fieldName);
                } catch (NoSuchFieldException e2) {
                }
            }
            throw e;
        }
    }

    public static Field findFirstFieldByExactType(Class<?> clazz, Class<?> type) {
        Field[] declaredFields;
        Class<? super Object> superclass;
        Class<?> clz = clazz;
        do {
            for (Field field : clz.getDeclaredFields()) {
                if (field.getType() == type) {
                    field.setAccessible(true);
                    return field;
                }
            }
            superclass = clz.getSuperclass();
            clz = superclass;
        } while (superclass != null);
        throw new NoSuchFieldError("Field of type " + type.getName() + " in class " + clazz.getName());
    }

    public static XC_MethodHook.Unhook findAndHookMethod(Class<?> clazz, String methodName, Object... parameterTypesAndCallback) {
        if (parameterTypesAndCallback.length == 0 || !(parameterTypesAndCallback[parameterTypesAndCallback.length - 1] instanceof XC_MethodHook)) {
            throw new IllegalArgumentException("no callback defined");
        }
        XC_MethodHook callback = (XC_MethodHook) parameterTypesAndCallback[parameterTypesAndCallback.length - 1];
        Method m = findMethodExact(clazz, methodName, getParameterClasses(clazz.getClassLoader(), parameterTypesAndCallback));
        return XposedBridge.hookMethod(m, callback);
    }

    public static XC_MethodHook.Unhook findAndHookMethod(String className, ClassLoader classLoader, String methodName, Object... parameterTypesAndCallback) {
        return findAndHookMethod(findClass(className, classLoader), methodName, parameterTypesAndCallback);
    }

    public static Method findMethodExact(Class<?> clazz, String methodName, Object... parameterTypes) {
        return findMethodExact(clazz, methodName, getParameterClasses(clazz.getClassLoader(), parameterTypes));
    }

    public static Method findMethodExactIfExists(Class<?> clazz, String methodName, Object... parameterTypes) {
        try {
            return findMethodExact(clazz, methodName, parameterTypes);
        } catch (ClassNotFoundError | NoSuchMethodError e) {
            return null;
        }
    }

    public static Method findMethodExact(String className, ClassLoader classLoader, String methodName, Object... parameterTypes) {
        return findMethodExact(findClass(className, classLoader), methodName, getParameterClasses(classLoader, parameterTypes));
    }

    public static Method findMethodExactIfExists(String className, ClassLoader classLoader, String methodName, Object... parameterTypes) {
        try {
            return findMethodExact(className, classLoader, methodName, parameterTypes);
        } catch (ClassNotFoundError | NoSuchMethodError e) {
            return null;
        }
    }

    public static Method findMethodExact(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        String fullMethodName = clazz.getName() + '#' + methodName + getParametersString(parameterTypes) + "#exact";
        if (methodCache.containsKey(fullMethodName)) {
            Method method = methodCache.get(fullMethodName);
            if (method != null) {
                return method;
            }
            throw new NoSuchMethodError(fullMethodName);
        }
        try {
            Method method2 = clazz.getDeclaredMethod(methodName, parameterTypes);
            method2.setAccessible(true);
            methodCache.put(fullMethodName, method2);
            return method2;
        } catch (NoSuchMethodException e) {
            methodCache.put(fullMethodName, null);
            throw new NoSuchMethodError(fullMethodName);
        }
    }

    public static Method[] findMethodsByExactParameters(Class<?> clazz, Class<?> returnType, Class<?>... parameterTypes) {
        Method[] declaredMethods;
        LinkedList<Method> result = new LinkedList<>();
        for (Method method : clazz.getDeclaredMethods()) {
            if (returnType == null || returnType == method.getReturnType()) {
                Class<?>[] methodParameterTypes = method.getParameterTypes();
                if (parameterTypes.length == methodParameterTypes.length) {
                    boolean match = true;
                    int i = 0;
                    while (true) {
                        if (i >= parameterTypes.length) {
                            break;
                        } else if (parameterTypes[i] != methodParameterTypes[i]) {
                            match = false;
                            break;
                        } else {
                            i++;
                        }
                    }
                    if (match) {
                        method.setAccessible(true);
                        result.add(method);
                    }
                }
            }
        }
        return (Method[]) result.toArray(new Method[result.size()]);
    }

    public static Method findMethodBestMatch(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        Method[] declaredMethods;
        Class<? super Object> superclass;
        String fullMethodName = clazz.getName() + '#' + methodName + getParametersString(parameterTypes) + "#bestmatch";
        if (methodCache.containsKey(fullMethodName)) {
            Method method = methodCache.get(fullMethodName);
            if (method != null) {
                return method;
            }
            throw new NoSuchMethodError(fullMethodName);
        }
        try {
            Method method2 = findMethodExact(clazz, methodName, parameterTypes);
            methodCache.put(fullMethodName, method2);
            return method2;
        } catch (NoSuchMethodError e) {
            Method bestMatch = null;
            Class<?> clz = clazz;
            boolean considerPrivateMethods = true;
            do {
                for (Method method3 : clz.getDeclaredMethods()) {
                    if ((considerPrivateMethods || !Modifier.isPrivate(method3.getModifiers())) && method3.getName().equals(methodName) && ClassUtils.isAssignable(parameterTypes, method3.getParameterTypes(), true) && (bestMatch == null || MemberUtils.compareParameterTypes(method3.getParameterTypes(), bestMatch.getParameterTypes(), parameterTypes) < 0)) {
                        bestMatch = method3;
                    }
                }
                considerPrivateMethods = false;
                superclass = clz.getSuperclass();
                clz = superclass;
            } while (superclass != null);
            if (bestMatch != null) {
                bestMatch.setAccessible(true);
                methodCache.put(fullMethodName, bestMatch);
                return bestMatch;
            }
            NoSuchMethodError e2 = new NoSuchMethodError(fullMethodName);
            methodCache.put(fullMethodName, null);
            throw e2;
        }
    }

    public static Method findMethodBestMatch(Class<?> clazz, String methodName, Object... args) {
        return findMethodBestMatch(clazz, methodName, getParameterTypes(args));
    }

    public static Method findMethodBestMatch(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object[] args) {
        Class<?>[] argsClasses = null;
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == null) {
                if (argsClasses == null) {
                    argsClasses = getParameterTypes(args);
                }
                parameterTypes[i] = argsClasses[i];
            }
        }
        return findMethodBestMatch(clazz, methodName, parameterTypes);
    }

    public static Class<?>[] getParameterTypes(Object... args) {
        Class<?>[] clazzes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            clazzes[i] = args[i] != null ? args[i].getClass() : null;
        }
        return clazzes;
    }

    private static Class<?>[] getParameterClasses(ClassLoader classLoader, Object[] parameterTypesAndCallback) {
        Class<?>[] parameterClasses = null;
        for (int i = parameterTypesAndCallback.length - 1; i >= 0; i--) {
            Object type = parameterTypesAndCallback[i];
            if (type != null) {
                if (!(type instanceof XC_MethodHook)) {
                    if (parameterClasses == null) {
                        parameterClasses = new Class[i + 1];
                    }
                    if (type instanceof Class) {
                        parameterClasses[i] = (Class) type;
                    } else if (type instanceof String) {
                        parameterClasses[i] = findClass((String) type, classLoader);
                    } else {
                        throw new ClassNotFoundError("parameter type must either be specified as Class or String", null);
                    }
                }
            } else {
                throw new ClassNotFoundError("parameter type must not be null", null);
            }
        }
        if (parameterClasses == null) {
            return new Class[0];
        }
        return parameterClasses;
    }

    public static Class<?>[] getClassesAsArray(Class<?>... clazzes) {
        return clazzes;
    }

    private static String getParametersString(Class<?>... clazzes) {
        StringBuilder sb = new StringBuilder("(");
        boolean first = true;
        for (Class<?> clazz : clazzes) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            if (clazz != null) {
                sb.append(clazz.getCanonicalName());
            } else {
                sb.append("null");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public static Constructor<?> findConstructorExact(Class<?> clazz, Object... parameterTypes) {
        return findConstructorExact(clazz, getParameterClasses(clazz.getClassLoader(), parameterTypes));
    }

    public static Constructor<?> findConstructorExactIfExists(Class<?> clazz, Object... parameterTypes) {
        try {
            return findConstructorExact(clazz, parameterTypes);
        } catch (ClassNotFoundError | NoSuchMethodError e) {
            return null;
        }
    }

    public static Constructor<?> findConstructorExact(String className, ClassLoader classLoader, Object... parameterTypes) {
        return findConstructorExact(findClass(className, classLoader), getParameterClasses(classLoader, parameterTypes));
    }

    public static Constructor<?> findConstructorExactIfExists(String className, ClassLoader classLoader, Object... parameterTypes) {
        try {
            return findConstructorExact(className, classLoader, parameterTypes);
        } catch (ClassNotFoundError | NoSuchMethodError e) {
            return null;
        }
    }

    public static Constructor<?> findConstructorExact(Class<?> clazz, Class<?>... parameterTypes) {
        String fullConstructorName = clazz.getName() + getParametersString(parameterTypes) + "#exact";
        if (constructorCache.containsKey(fullConstructorName)) {
            Constructor<?> constructor = constructorCache.get(fullConstructorName);
            if (constructor != null) {
                return constructor;
            }
            throw new NoSuchMethodError(fullConstructorName);
        }
        try {
            Constructor<?> constructor2 = clazz.getDeclaredConstructor(parameterTypes);
            constructor2.setAccessible(true);
            constructorCache.put(fullConstructorName, constructor2);
            return constructor2;
        } catch (NoSuchMethodException e) {
            constructorCache.put(fullConstructorName, null);
            throw new NoSuchMethodError(fullConstructorName);
        }
    }

    public static XC_MethodHook.Unhook findAndHookConstructor(Class<?> clazz, Object... parameterTypesAndCallback) {
        if (parameterTypesAndCallback.length == 0 || !(parameterTypesAndCallback[parameterTypesAndCallback.length - 1] instanceof XC_MethodHook)) {
            throw new IllegalArgumentException("no callback defined");
        }
        XC_MethodHook callback = (XC_MethodHook) parameterTypesAndCallback[parameterTypesAndCallback.length - 1];
        Constructor<?> m = findConstructorExact(clazz, getParameterClasses(clazz.getClassLoader(), parameterTypesAndCallback));
        return XposedBridge.hookMethod(m, callback);
    }

    public static XC_MethodHook.Unhook findAndHookConstructor(String className, ClassLoader classLoader, Object... parameterTypesAndCallback) {
        return findAndHookConstructor(findClass(className, classLoader), parameterTypesAndCallback);
    }

    public static Constructor<?> findConstructorBestMatch(Class<?> clazz, Class<?>... parameterTypes) {
        String fullConstructorName = clazz.getName() + getParametersString(parameterTypes) + "#bestmatch";
        if (constructorCache.containsKey(fullConstructorName)) {
            Constructor<?> constructor = constructorCache.get(fullConstructorName);
            if (constructor != null) {
                return constructor;
            }
            throw new NoSuchMethodError(fullConstructorName);
        }
        try {
            Constructor<?> constructor2 = findConstructorExact(clazz, parameterTypes);
            constructorCache.put(fullConstructorName, constructor2);
            return constructor2;
        } catch (NoSuchMethodError e) {
            Constructor<?> bestMatch = null;
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor<?> constructor3 : constructors) {
                if (ClassUtils.isAssignable(parameterTypes, constructor3.getParameterTypes(), true) && (bestMatch == null || MemberUtils.compareParameterTypes(constructor3.getParameterTypes(), bestMatch.getParameterTypes(), parameterTypes) < 0)) {
                    bestMatch = constructor3;
                }
            }
            if (bestMatch != null) {
                bestMatch.setAccessible(true);
                constructorCache.put(fullConstructorName, bestMatch);
                return bestMatch;
            }
            constructorCache.put(fullConstructorName, null);
            throw new NoSuchMethodError(fullConstructorName);
        }
    }

    public static Constructor<?> findConstructorBestMatch(Class<?> clazz, Object... args) {
        return findConstructorBestMatch(clazz, getParameterTypes(args));
    }

    public static Constructor<?> findConstructorBestMatch(Class<?> clazz, Class<?>[] parameterTypes, Object[] args) {
        Class<?>[] argsClasses = null;
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == null) {
                if (argsClasses == null) {
                    argsClasses = getParameterTypes(args);
                }
                parameterTypes[i] = argsClasses[i];
            }
        }
        return findConstructorBestMatch(clazz, parameterTypes);
    }

    /* loaded from: classes2.dex */
    public static final class ClassNotFoundError extends Error {
        private static final long serialVersionUID = -1070936889459514628L;

        public ClassNotFoundError(Throwable cause) {
            super(cause);
        }

        public ClassNotFoundError(String detailMessage, Throwable cause) {
            super(detailMessage, cause);
        }
    }

    public static int getFirstParameterIndexByType(Member method, Class<?> type) {
        Class<?>[] classes = method instanceof Method ? ((Method) method).getParameterTypes() : ((Constructor) method).getParameterTypes();
        for (int i = 0; i < classes.length; i++) {
            if (classes[i] == type) {
                return i;
            }
        }
        throw new NoSuchFieldError("No parameter of type " + type + " found in " + method);
    }

    public static int getParameterIndexByType(Member method, Class<?> type) {
        Class<?>[] classes = method instanceof Method ? ((Method) method).getParameterTypes() : ((Constructor) method).getParameterTypes();
        int idx = -1;
        for (int i = 0; i < classes.length; i++) {
            if (classes[i] == type) {
                if (idx == -1) {
                    idx = i;
                } else {
                    throw new NoSuchFieldError("More than one parameter of type " + type + " found in " + method);
                }
            }
        }
        if (idx != -1) {
            return idx;
        }
        throw new NoSuchFieldError("No parameter of type " + type + " found in " + method);
    }

    public static void setObjectField(Object obj, String fieldName, Object value) {
        try {
            findField(obj.getClass(), fieldName).set(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setBooleanField(Object obj, String fieldName, boolean value) {
        try {
            findField(obj.getClass(), fieldName).setBoolean(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setByteField(Object obj, String fieldName, byte value) {
        try {
            findField(obj.getClass(), fieldName).setByte(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setCharField(Object obj, String fieldName, char value) {
        try {
            findField(obj.getClass(), fieldName).setChar(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setDoubleField(Object obj, String fieldName, double value) {
        try {
            findField(obj.getClass(), fieldName).setDouble(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setFloatField(Object obj, String fieldName, float value) {
        try {
            findField(obj.getClass(), fieldName).setFloat(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setIntField(Object obj, String fieldName, int value) {
        try {
            findField(obj.getClass(), fieldName).setInt(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setLongField(Object obj, String fieldName, long value) {
        try {
            findField(obj.getClass(), fieldName).setLong(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setShortField(Object obj, String fieldName, short value) {
        try {
            findField(obj.getClass(), fieldName).setShort(obj, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static Object getObjectField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).get(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static Object getSurroundingThis(Object obj) {
        return getObjectField(obj, "this$0");
    }

    public static boolean getBooleanField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getBoolean(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static byte getByteField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getByte(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static char getCharField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getChar(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static double getDoubleField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getDouble(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static float getFloatField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getFloat(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static int getIntField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getInt(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static long getLongField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getLong(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static short getShortField(Object obj, String fieldName) {
        try {
            return findField(obj.getClass(), fieldName).getShort(obj);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticObjectField(Class<?> clazz, String fieldName, Object value) {
        try {
            findField(clazz, fieldName).set(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticBooleanField(Class<?> clazz, String fieldName, boolean value) {
        try {
            findField(clazz, fieldName).setBoolean(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticByteField(Class<?> clazz, String fieldName, byte value) {
        try {
            findField(clazz, fieldName).setByte(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticCharField(Class<?> clazz, String fieldName, char value) {
        try {
            findField(clazz, fieldName).setChar(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticDoubleField(Class<?> clazz, String fieldName, double value) {
        try {
            findField(clazz, fieldName).setDouble(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticFloatField(Class<?> clazz, String fieldName, float value) {
        try {
            findField(clazz, fieldName).setFloat(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticIntField(Class<?> clazz, String fieldName, int value) {
        try {
            findField(clazz, fieldName).setInt(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticLongField(Class<?> clazz, String fieldName, long value) {
        try {
            findField(clazz, fieldName).setLong(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static void setStaticShortField(Class<?> clazz, String fieldName, short value) {
        try {
            findField(clazz, fieldName).setShort(null, value);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static Object getStaticObjectField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).get(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static boolean getStaticBooleanField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getBoolean(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static byte getStaticByteField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getByte(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static char getStaticCharField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getChar(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static double getStaticDoubleField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getDouble(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static float getStaticFloatField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getFloat(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static int getStaticIntField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getInt(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static long getStaticLongField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getLong(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static short getStaticShortField(Class<?> clazz, String fieldName) {
        try {
            return findField(clazz, fieldName).getShort(null);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        }
    }

    public static Object callMethod(Object obj, String methodName, Object... args) {
        try {
            return findMethodBestMatch(obj.getClass(), methodName, args).invoke(obj, args);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (InvocationTargetException e3) {
            throw new InvocationTargetError(e3.getCause());
        }
    }

    public static Object callMethod(Object obj, String methodName, Class<?>[] parameterTypes, Object... args) {
        try {
            return findMethodBestMatch(obj.getClass(), methodName, parameterTypes, args).invoke(obj, args);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (InvocationTargetException e3) {
            throw new InvocationTargetError(e3.getCause());
        }
    }

    public static Object callStaticMethod(Class<?> clazz, String methodName, Object... args) {
        try {
            return findMethodBestMatch(clazz, methodName, args).invoke(null, args);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (InvocationTargetException e3) {
            throw new InvocationTargetError(e3.getCause());
        }
    }

    public static Object callStaticMethod(Class<?> clazz, String methodName, Class<?>[] parameterTypes, Object... args) {
        try {
            return findMethodBestMatch(clazz, methodName, parameterTypes, args).invoke(null, args);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (InvocationTargetException e3) {
            throw new InvocationTargetError(e3.getCause());
        }
    }

    /* loaded from: classes2.dex */
    public static final class InvocationTargetError extends Error {
        private static final long serialVersionUID = -1070936889459514628L;

        public InvocationTargetError(Throwable cause) {
            super(cause);
        }
    }

    public static Object newInstance(Class<?> clazz, Object... args) {
        try {
            return findConstructorBestMatch(clazz, args).newInstance(args);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (InstantiationException e3) {
            throw new InstantiationError(e3.getMessage());
        } catch (InvocationTargetException e4) {
            throw new InvocationTargetError(e4.getCause());
        }
    }

    public static Object newInstance(Class<?> clazz, Class<?>[] parameterTypes, Object... args) {
        try {
            return findConstructorBestMatch(clazz, parameterTypes, args).newInstance(args);
        } catch (IllegalAccessException e) {
            Log.w(LOGTAG, e);
            throw new IllegalAccessError(e.getMessage());
        } catch (IllegalArgumentException e2) {
            throw e2;
        } catch (InstantiationException e3) {
            throw new InstantiationError(e3.getMessage());
        } catch (InvocationTargetException e4) {
            throw new InvocationTargetError(e4.getCause());
        }
    }

    public static Object setAdditionalInstanceField(Object obj, String key, Object value) {
        HashMap<String, Object> objectFields;
        Object put;
        if (obj == null) {
            throw new NullPointerException("object must not be null");
        } else if (key != null) {
            synchronized (additionalFields) {
                objectFields = additionalFields.get(obj);
                if (objectFields == null) {
                    objectFields = new HashMap<>();
                    additionalFields.put(obj, objectFields);
                }
            }
            synchronized (objectFields) {
                put = objectFields.put(key, value);
            }
            return put;
        } else {
            throw new NullPointerException("key must not be null");
        }
    }

    public static Object getAdditionalInstanceField(Object obj, String key) {
        Object obj2;
        if (obj == null) {
            throw new NullPointerException("object must not be null");
        } else if (key != null) {
            synchronized (additionalFields) {
                HashMap<String, Object> objectFields = additionalFields.get(obj);
                if (objectFields == null) {
                    return null;
                }
                synchronized (objectFields) {
                    obj2 = objectFields.get(key);
                }
                return obj2;
            }
        } else {
            throw new NullPointerException("key must not be null");
        }
    }

    public static Object removeAdditionalInstanceField(Object obj, String key) {
        Object remove;
        if (obj == null) {
            throw new NullPointerException("object must not be null");
        } else if (key != null) {
            synchronized (additionalFields) {
                HashMap<String, Object> objectFields = additionalFields.get(obj);
                if (objectFields == null) {
                    return null;
                }
                synchronized (objectFields) {
                    remove = objectFields.remove(key);
                }
                return remove;
            }
        } else {
            throw new NullPointerException("key must not be null");
        }
    }

    public static Object setAdditionalStaticField(Object obj, String key, Object value) {
        return setAdditionalInstanceField(obj.getClass(), key, value);
    }

    public static Object getAdditionalStaticField(Object obj, String key) {
        return getAdditionalInstanceField(obj.getClass(), key);
    }

    public static Object removeAdditionalStaticField(Object obj, String key) {
        return removeAdditionalInstanceField(obj.getClass(), key);
    }

    public static Object setAdditionalStaticField(Class<?> clazz, String key, Object value) {
        return setAdditionalInstanceField(clazz, key, value);
    }

    public static Object getAdditionalStaticField(Class<?> clazz, String key) {
        return getAdditionalInstanceField(clazz, key);
    }

    public static Object removeAdditionalStaticField(Class<?> clazz, String key) {
        return removeAdditionalInstanceField(clazz, key);
    }

    public static byte[] assetAsByteArray(Resources res, String path) throws IOException {
        return inputStreamToByteArray(res.getAssets().open(path));
    }

    static byte[] inputStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];
        while (true) {
            int read = is.read(temp);
            if (read > 0) {
                buf.write(temp, 0, read);
            } else {
                is.close();
                return buf.toByteArray();
            }
        }
    }

    static void closeSilently(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }

    static void closeSilently(DexFile dexFile) {
        if (dexFile != null) {
            try {
                dexFile.close();
            } catch (IOException e) {
            }
        }
    }

    static void closeSilently(ZipFile zipFile) {
        if (zipFile != null) {
            try {
                zipFile.close();
            } catch (IOException e) {
            }
        }
    }

    public static String getMD5Sum(String file) throws IOException {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            InputStream is = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            while (true) {
                int read = is.read(buffer);
                if (read > 0) {
                    digest.update(buffer, 0, read);
                } else {
                    is.close();
                    byte[] md5sum = digest.digest();
                    BigInteger bigInt = new BigInteger(1, md5sum);
                    return bigInt.toString(16);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public static int incrementMethodDepth(String method) {
        return getMethodDepthCounter(method).get().incrementAndGet();
    }

    public static int decrementMethodDepth(String method) {
        return getMethodDepthCounter(method).get().decrementAndGet();
    }

    public static int getMethodDepth(String method) {
        return getMethodDepthCounter(method).get().get();
    }

    private static ThreadLocal<AtomicInteger> getMethodDepthCounter(String method) {
        ThreadLocal<AtomicInteger> counter;
        synchronized (sMethodDepth) {
            counter = sMethodDepth.get(method);
            if (counter == null) {
                counter = new ThreadLocal<AtomicInteger>() { // from class: andhook.lib.xposed.XposedHelpers.1
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // java.lang.ThreadLocal
                    public AtomicInteger initialValue() {
                        return new AtomicInteger();
                    }
                };
                sMethodDepth.put(method, counter);
            }
        }
        return counter;
    }

    static boolean fileContains(File file, String str) throws IOException {
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            do {
                line = in.readLine();
                if (line == null) {
                    return false;
                }
            } while (!line.contains(str));
            return true;
        } finally {
            closeSilently(in);
        }
    }

    static Method getOverriddenMethod(Method method) {
        int modifiers = method.getModifiers();
        if (Modifier.isStatic(modifiers) || Modifier.isPrivate(modifiers)) {
            return null;
        }
        String name = method.getName();
        Class<?>[] parameters = method.getParameterTypes();
        for (Class<?> clazz = method.getDeclaringClass().getSuperclass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Method superMethod = clazz.getDeclaredMethod(name, parameters);
                int modifiers2 = superMethod.getModifiers();
                if (!Modifier.isPrivate(modifiers2)) {
                    if (!Modifier.isAbstract(modifiers2)) {
                        return superMethod;
                    }
                }
                return null;
            } catch (NoSuchMethodException e) {
            }
        }
        return null;
    }

    static HashSet<Method> getOverriddenMethods(Class<?> clazz) {
        Method[] declaredMethods;
        HashSet<Method> methods = new HashSet<>();
        for (Method method : clazz.getDeclaredMethods()) {
            Method overridden = getOverriddenMethod(method);
            if (overridden != null) {
                methods.add(overridden);
            }
        }
        return methods;
    }
}
