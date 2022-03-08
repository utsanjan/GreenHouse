package com.swift.sandhook.wrapper;

import andhook.lib.HookHelper;
import android.text.TextUtils;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.SandHookConfig;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.MethodReflectParams;
import com.swift.sandhook.annotation.Param;
import com.swift.sandhook.annotation.SkipParamCheck;
import com.swift.sandhook.annotation.ThisObject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/* loaded from: classes2.dex */
public class HookWrapper {
    public static void addHookClass(Class<?>... classes) throws HookErrorException {
        addHookClass((ClassLoader) null, classes);
    }

    public static void addHookClass(ClassLoader classLoader, Class<?>... classes) throws HookErrorException {
        for (Class<?> cls : classes) {
            addHookClass(classLoader, cls);
        }
    }

    public static void addHookClass(ClassLoader classLoader, Class<?> clazz) throws HookErrorException {
        Class targetHookClass = getTargetHookClass(classLoader, clazz);
        if (targetHookClass != null) {
            Map<Member, HookEntity> hookEntityMap = getHookMethods(classLoader, targetHookClass, clazz);
            try {
                fillBackupMethod(classLoader, clazz, hookEntityMap);
                for (HookEntity entity : hookEntityMap.values()) {
                    SandHook.hook(entity);
                }
            } catch (Throwable throwable) {
                throw new HookErrorException("fillBackupMethod error!", throwable);
            }
        } else {
            throw new HookErrorException("error hook wrapper class :" + clazz.getName());
        }
    }

    private static void fillBackupMethod(ClassLoader classLoader, Class<?> clazz, Map<Member, HookEntity> hookEntityMap) {
        HookMethodBackup hookMethodBackup;
        Field[] fields = null;
        try {
            fields = clazz.getDeclaredFields();
        } catch (Throwable th) {
        }
        if (!(fields == null || fields.length == 0 || hookEntityMap.isEmpty())) {
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) && (hookMethodBackup = (HookMethodBackup) field.getAnnotation(HookMethodBackup.class)) != null) {
                    for (HookEntity hookEntity : hookEntityMap.values()) {
                        if (TextUtils.equals(hookEntity.isCtor() ? HookHelper.constructorName : hookEntity.target.getName(), hookMethodBackup.value()) && samePars(classLoader, field, hookEntity.pars)) {
                            field.setAccessible(true);
                            if (hookEntity.backup == null) {
                                hookEntity.backup = StubMethodsFactory.getStubMethod();
                                hookEntity.hookIsStub = true;
                                hookEntity.resolveDexCache = false;
                            }
                            if (hookEntity.backup != null) {
                                try {
                                    if (field.getType() == Method.class) {
                                        field.set(null, hookEntity.backup);
                                    } else if (field.getType() == HookEntity.class) {
                                        field.set(null, hookEntity);
                                    }
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.Map<java.lang.reflect.Member, com.swift.sandhook.wrapper.HookWrapper.HookEntity> getHookMethods(java.lang.ClassLoader r13, java.lang.Class r14, java.lang.Class<?> r15) throws com.swift.sandhook.wrapper.HookErrorException {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.swift.sandhook.wrapper.HookWrapper.getHookMethods(java.lang.ClassLoader, java.lang.Class, java.lang.Class):java.util.Map");
    }

    private static Class[] parseMethodPars(ClassLoader classLoader, Method method) throws HookErrorException {
        MethodParams methodParams = (MethodParams) method.getAnnotation(MethodParams.class);
        MethodReflectParams methodReflectParams = (MethodReflectParams) method.getAnnotation(MethodReflectParams.class);
        if (methodParams != null) {
            return methodParams.value();
        }
        if (methodReflectParams != null) {
            if (methodReflectParams.value().length == 0) {
                return null;
            }
            Class[] pars = new Class[methodReflectParams.value().length];
            for (int i = 0; i < methodReflectParams.value().length; i++) {
                try {
                    pars[i] = classNameToClass(methodReflectParams.value()[i], classLoader);
                } catch (ClassNotFoundException e) {
                    throw new HookErrorException("hook method pars error: " + method.getName(), e);
                }
            }
            return pars;
        } else if (getParsCount(method) <= 0) {
            return null;
        } else {
            if (getParsCount(method) != 1) {
                return parseMethodParsNew(classLoader, method);
            }
            if (hasThisObject(method)) {
                return parseMethodParsNew(classLoader, method);
            }
            return null;
        }
    }

    private static Class[] parseMethodPars(ClassLoader classLoader, Field field) throws HookErrorException {
        MethodParams methodParams = (MethodParams) field.getAnnotation(MethodParams.class);
        MethodReflectParams methodReflectParams = (MethodReflectParams) field.getAnnotation(MethodReflectParams.class);
        if (methodParams != null) {
            return methodParams.value();
        }
        if (methodReflectParams == null || methodReflectParams.value().length == 0) {
            return null;
        }
        Class[] pars = new Class[methodReflectParams.value().length];
        for (int i = 0; i < methodReflectParams.value().length; i++) {
            try {
                pars[i] = classNameToClass(methodReflectParams.value()[i], classLoader);
            } catch (ClassNotFoundException e) {
                throw new HookErrorException("hook method pars error: " + field.getName(), e);
            }
        }
        return pars;
    }

    private static Class[] parseMethodParsNew(ClassLoader classLoader, Method method) throws HookErrorException {
        Class[] hookMethodPars = method.getParameterTypes();
        if (hookMethodPars == null || hookMethodPars.length == 0) {
            return null;
        }
        Annotation[][] annotations = method.getParameterAnnotations();
        Class[] realPars = null;
        int parIndex = 0;
        for (int i = 0; i < annotations.length; i++) {
            Class hookPar = hookMethodPars[i];
            Annotation[] methodAnnos = annotations[i];
            try {
                if (i == 0) {
                    if (isThisObject(methodAnnos)) {
                        realPars = new Class[annotations.length - 1];
                    } else {
                        realPars = new Class[annotations.length];
                    }
                }
                realPars[parIndex] = getRealParType(classLoader, hookPar, methodAnnos, method.isAnnotationPresent(SkipParamCheck.class));
                parIndex++;
            } catch (Exception e) {
                throw new HookErrorException("hook method <" + method.getName() + "> parser pars error", e);
            }
        }
        return realPars;
    }

    private static Class getRealParType(ClassLoader classLoader, Class hookPar, Annotation[] annotations, boolean skipCheck) throws Exception {
        if (annotations == null || annotations.length == 0) {
            return hookPar;
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof Param) {
                Param param = (Param) annotation;
                if (TextUtils.isEmpty(param.value())) {
                    return hookPar;
                }
                Class realPar = classNameToClass(param.value(), classLoader);
                if (skipCheck || realPar.equals(hookPar) || hookPar.isAssignableFrom(realPar)) {
                    return realPar;
                }
                throw new ClassCastException("hook method par cast error!");
            }
        }
        return hookPar;
    }

    private static boolean hasThisObject(Method method) {
        Annotation[][] annotations = method.getParameterAnnotations();
        if (annotations == null || annotations.length == 0) {
            return false;
        }
        Annotation[] thisParAnno = annotations[0];
        return isThisObject(thisParAnno);
    }

    private static boolean isThisObject(Annotation[] annotations) {
        if (annotations == null || annotations.length == 0) {
            return false;
        }
        for (Annotation annotation : annotations) {
            if (annotation instanceof ThisObject) {
                return true;
            }
        }
        return false;
    }

    private static int getParsCount(Method method) {
        Class[] hookMethodPars = method.getParameterTypes();
        if (hookMethodPars == null) {
            return 0;
        }
        return hookMethodPars.length;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private static Class classNameToClass(String name, ClassLoader classLoader) throws ClassNotFoundException {
        char c;
        switch (name.hashCode()) {
            case -1325958191:
                if (name.equals(MethodReflectParams.DOUBLE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 104431:
                if (name.equals(MethodReflectParams.INT)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3039496:
                if (name.equals(MethodReflectParams.BYTE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3052374:
                if (name.equals(MethodReflectParams.CHAR)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 3327612:
                if (name.equals(MethodReflectParams.LONG)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 64711720:
                if (name.equals(MethodReflectParams.BOOLEAN)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 97526364:
                if (name.equals(MethodReflectParams.FLOAT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 109413500:
                if (name.equals(MethodReflectParams.SHORT)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                Class clazz = Boolean.TYPE;
                return clazz;
            case 1:
                Class clazz2 = Byte.TYPE;
                return clazz2;
            case 2:
                Class clazz3 = Character.TYPE;
                return clazz3;
            case 3:
                Class clazz4 = Double.TYPE;
                return clazz4;
            case 4:
                Class clazz5 = Float.TYPE;
                return clazz5;
            case 5:
                Class clazz6 = Integer.TYPE;
                return clazz6;
            case 6:
                Class clazz7 = Long.TYPE;
                return clazz7;
            case 7:
                Class clazz8 = Short.TYPE;
                return clazz8;
            default:
                if (name.startsWith(".")) {
                    name = SandHookConfig.SELF_PACKAGE_NAME + name;
                }
                if (classLoader == null) {
                    Class clazz9 = Class.forName(name);
                    return clazz9;
                }
                Class clazz10 = Class.forName(name, true, classLoader);
                return clazz10;
        }
    }

    private static boolean samePars(ClassLoader classLoader, Field field, Class[] par) {
        try {
            Class[] parsOnField = parseMethodPars(classLoader, field);
            if (parsOnField == null && field.isAnnotationPresent(SkipParamCheck.class)) {
                return true;
            }
            if (par == null) {
                par = new Class[0];
            }
            if (parsOnField == null) {
                parsOnField = new Class[0];
            }
            if (par.length != parsOnField.length) {
                return false;
            }
            for (int i = 0; i < par.length; i++) {
                if (par[i] != parsOnField[i]) {
                    return false;
                }
            }
            return true;
        } catch (HookErrorException e) {
            return false;
        }
    }

    public static Class getTargetHookClass(ClassLoader classLoader, Class<?> hookWrapperClass) {
        HookClass hookClass = (HookClass) hookWrapperClass.getAnnotation(HookClass.class);
        HookReflectClass hookReflectClass = (HookReflectClass) hookWrapperClass.getAnnotation(HookReflectClass.class);
        if (hookClass != null) {
            return hookClass.value();
        }
        if (hookReflectClass == null) {
            return null;
        }
        try {
            String className = hookReflectClass.value();
            if (className.length() == 0) {
                try {
                    return (Class) hookWrapperClass.getMethod("getHookClass", new Class[0]).invoke(null, new Object[0]);
                } catch (Exception e) {
                    System.err.println(e);
                    return null;
                }
            } else {
                if (className.startsWith(".")) {
                    className = SandHookConfig.SELF_PACKAGE_NAME + className;
                }
                if (classLoader == null) {
                    return Class.forName(className);
                }
                return Class.forName(className, true, classLoader);
            }
        } catch (ClassNotFoundException e2) {
            System.err.println(e2);
            return null;
        }
    }

    public static void checkSignature(Member origin, Method fake, Class[] originPars) throws HookErrorException {
        Class originRet;
        if (Modifier.isStatic(fake.getModifiers())) {
            if (origin instanceof Constructor) {
                if (!fake.getReturnType().equals(Void.TYPE)) {
                    throw new HookErrorException("error return type! - " + fake.getName());
                }
            } else if ((origin instanceof Method) && (originRet = ((Method) origin).getReturnType()) != fake.getReturnType() && !originRet.isAssignableFrom(originRet)) {
                throw new HookErrorException("error return type! - " + fake.getName());
            }
            Class<?>[] parameterTypes = fake.getParameterTypes();
            if (parameterTypes == null) {
                parameterTypes = new Class[0];
            }
            if (originPars == null) {
                originPars = new Class[0];
            }
            if (!(originPars.length == 0 && parameterTypes.length == 0)) {
                int parOffset = 0;
                if (!Modifier.isStatic(origin.getModifiers())) {
                    parOffset = 1;
                    if (parameterTypes.length == 0) {
                        throw new HookErrorException("first par must be this! " + fake.getName());
                    } else if (parameterTypes[0] != origin.getDeclaringClass() && !parameterTypes[0].isAssignableFrom(origin.getDeclaringClass())) {
                        throw new HookErrorException("first par must be this! " + fake.getName());
                    } else if (parameterTypes.length != originPars.length + 1) {
                        throw new HookErrorException("hook method pars must match the origin method! " + fake.getName());
                    }
                } else if (parameterTypes.length != originPars.length) {
                    throw new HookErrorException("hook method pars must match the origin method! " + fake.getName());
                }
                for (int i = 0; i < originPars.length; i++) {
                    if (parameterTypes[i + parOffset] != originPars[i] && !parameterTypes[i + parOffset].isAssignableFrom(originPars[i])) {
                        throw new HookErrorException("hook method pars must match the origin method! " + fake.getName());
                    }
                }
                return;
            }
            return;
        }
        throw new HookErrorException("hook method must static! - " + fake.getName());
    }

    /* loaded from: classes2.dex */
    public static class HookEntity {
        public Method backup;
        public boolean backupIsStub;
        public Method hook;
        public boolean hookIsStub;
        public int hookMode;
        public Class[] pars;
        public boolean resolveDexCache;
        public Member target;

        public HookEntity(Member target) {
            this.hookIsStub = false;
            this.resolveDexCache = true;
            this.backupIsStub = true;
            this.target = target;
        }

        public HookEntity(Member target, Method hook, Method backup) {
            this.hookIsStub = false;
            this.resolveDexCache = true;
            this.backupIsStub = true;
            this.target = target;
            this.hook = hook;
            this.backup = backup;
        }

        public HookEntity(Member target, Method hook, Method backup, boolean resolveDexCache) {
            this.hookIsStub = false;
            this.resolveDexCache = true;
            this.backupIsStub = true;
            this.target = target;
            this.hook = hook;
            this.backup = backup;
            this.resolveDexCache = resolveDexCache;
        }

        public boolean isCtor() {
            return this.target instanceof Constructor;
        }

        public Object callOrigin(Object thiz, Object... args) throws Throwable {
            return SandHook.callOriginMethod(this.backupIsStub, this.target, this.backup, thiz, args);
        }
    }
}
