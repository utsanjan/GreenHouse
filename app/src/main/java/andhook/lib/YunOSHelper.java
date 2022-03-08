package andhook.lib;

import andhook.lib.xposed.ClassUtils;
import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;

/* loaded from: classes2.dex */
public final class YunOSHelper {
    private static final HashMap<Class<?>, String> PRIMITIVE_TO_SIGNATURE = new HashMap<>(9);

    static {
        PRIMITIVE_TO_SIGNATURE.put(Byte.TYPE, "B");
        PRIMITIVE_TO_SIGNATURE.put(Character.TYPE, "C");
        PRIMITIVE_TO_SIGNATURE.put(Short.TYPE, ExifInterface.LATITUDE_SOUTH);
        PRIMITIVE_TO_SIGNATURE.put(Integer.TYPE, "I");
        PRIMITIVE_TO_SIGNATURE.put(Long.TYPE, "J");
        PRIMITIVE_TO_SIGNATURE.put(Float.TYPE, "F");
        PRIMITIVE_TO_SIGNATURE.put(Double.TYPE, "D");
        PRIMITIVE_TO_SIGNATURE.put(Void.TYPE, ExifInterface.GPS_MEASUREMENT_INTERRUPTED);
        PRIMITIVE_TO_SIGNATURE.put(Boolean.TYPE, "Z");
    }

    public static String getSignature(Class<?> clazz) {
        String primitiveSignature = PRIMITIVE_TO_SIGNATURE.get(clazz);
        if (primitiveSignature != null) {
            return primitiveSignature;
        }
        if (clazz.isArray()) {
            return "[" + getSignature(clazz.getComponentType());
        }
        return "L" + clazz.getName().replace(ClassUtils.PACKAGE_SEPARATOR_CHAR, '/') + ";";
    }

    public static String getNativeTypeCode(Class<?> clazz) {
        String primitiveSignature = PRIMITIVE_TO_SIGNATURE.get(clazz);
        if (primitiveSignature != null) {
            return primitiveSignature;
        }
        return "L";
    }

    private static String getSignature(Class<?> retType, Class<?>[] parameterTypes) {
        StringBuilder result = new StringBuilder();
        result.append('(');
        for (Class<?> parameterType : parameterTypes) {
            result.append(getSignature(parameterType));
        }
        result.append(")");
        result.append(getSignature(retType));
        return result.toString();
    }

    public static String getSignature(Member m) {
        if (m instanceof Method) {
            Method md = (Method) m;
            return getSignature(md.getReturnType(), md.getParameterTypes());
        } else if (!(m instanceof Constructor)) {
            return null;
        } else {
            Constructor<?> c = (Constructor) m;
            return getSignature(Void.TYPE, c.getParameterTypes());
        }
    }

    private static String getShorty(Class<?> retType, Class<?>[] parameterTypes) {
        StringBuilder result = new StringBuilder();
        result.append(getNativeTypeCode(retType));
        for (Class<?> parameterType : parameterTypes) {
            result.append(getNativeTypeCode(parameterType));
        }
        return result.toString();
    }

    public static String getShorty(Member m) {
        if (m instanceof Method) {
            Method md = (Method) m;
            return getShorty(md.getReturnType(), md.getParameterTypes());
        } else if (!(m instanceof Constructor)) {
            return null;
        } else {
            Constructor<?> c = (Constructor) m;
            return getShorty(Void.TYPE, c.getParameterTypes());
        }
    }
}
