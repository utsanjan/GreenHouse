package com.swift.sandhook.utils;

import com.swift.sandhook.SandHook;

/* loaded from: classes2.dex */
public class ParamWrapper {
    private static boolean is64Bit = SandHook.is64Bit();

    public static boolean support(Class objectType) {
        return is64Bit ? (objectType == Float.TYPE || objectType == Double.TYPE) ? false : true : (objectType == Float.TYPE || objectType == Double.TYPE || objectType == Long.TYPE) ? false : true;
    }

    public static Object addressToObject(Class objectType, long address) {
        if (is64Bit) {
            return addressToObject64(objectType, address);
        }
        return addressToObject32(objectType, (int) address);
    }

    public static Object addressToObject64(Class objectType, long address) {
        if (objectType == null) {
            return null;
        }
        if (!objectType.isPrimitive()) {
            return SandHook.getObject(address);
        }
        if (objectType == Integer.TYPE) {
            return Integer.valueOf((int) address);
        }
        if (objectType == Long.TYPE) {
            return Long.valueOf(address);
        }
        if (objectType == Short.TYPE) {
            return Short.valueOf((short) address);
        }
        if (objectType == Byte.TYPE) {
            return Byte.valueOf((byte) address);
        }
        if (objectType == Character.TYPE) {
            return Character.valueOf((char) address);
        }
        if (objectType == Boolean.TYPE) {
            return Boolean.valueOf(address != 0);
        }
        throw new RuntimeException("unknown type: " + objectType.toString());
    }

    public static Object addressToObject32(Class objectType, int address) {
        if (objectType == null) {
            return null;
        }
        if (!objectType.isPrimitive()) {
            return SandHook.getObject(address);
        }
        if (objectType == Integer.TYPE) {
            return Integer.valueOf(address);
        }
        if (objectType == Short.TYPE) {
            return Short.valueOf((short) address);
        }
        if (objectType == Byte.TYPE) {
            return Byte.valueOf((byte) address);
        }
        if (objectType == Character.TYPE) {
            return Character.valueOf((char) address);
        }
        if (objectType == Boolean.TYPE) {
            return Boolean.valueOf(address != 0);
        }
        throw new RuntimeException("unknown type: " + objectType.toString());
    }

    public static long objectToAddress(Class objectType, Object object) {
        if (is64Bit) {
            return objectToAddress64(objectType, object);
        }
        return objectToAddress32(objectType, object);
    }

    public static int objectToAddress32(Class objectType, Object object) {
        if (object == null) {
            return 0;
        }
        if (!objectType.isPrimitive()) {
            return (int) SandHook.getObjectAddress(object);
        }
        if (objectType == Integer.TYPE) {
            return ((Integer) object).intValue();
        }
        if (objectType == Short.TYPE) {
            return ((Short) object).shortValue();
        }
        if (objectType == Byte.TYPE) {
            return ((Byte) object).byteValue();
        }
        if (objectType == Character.TYPE) {
            return ((Character) object).charValue();
        }
        if (objectType == Boolean.TYPE) {
            return Boolean.TRUE.equals(object) ? 1 : 0;
        }
        throw new RuntimeException("unknown type: " + objectType.toString());
    }

    public static long objectToAddress64(Class objectType, Object object) {
        if (object == null) {
            return 0L;
        }
        if (!objectType.isPrimitive()) {
            return SandHook.getObjectAddress(object);
        }
        if (objectType == Integer.TYPE) {
            return ((Integer) object).intValue();
        }
        if (objectType == Long.TYPE) {
            return ((Long) object).longValue();
        }
        if (objectType == Short.TYPE) {
            return ((Short) object).shortValue();
        }
        if (objectType == Byte.TYPE) {
            return ((Byte) object).byteValue();
        }
        if (objectType == Character.TYPE) {
            return ((Character) object).charValue();
        }
        if (objectType == Boolean.TYPE) {
            return Boolean.TRUE.equals(object) ? 1L : 0L;
        }
        throw new RuntimeException("unknown type: " + objectType.toString());
    }
}
