package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import com.google.android.datatransport.Priority;
import java.util.EnumMap;

/* loaded from: classes.dex */
public final class PriorityMapping {
    private static EnumMap<Priority, Integer> PRIORITY_INT_MAP;
    private static SparseArray<Priority> PRIORITY_MAP = new SparseArray<>();

    static {
        EnumMap<Priority, Integer> enumMap = new EnumMap<>(Priority.class);
        PRIORITY_INT_MAP = enumMap;
        enumMap.put((EnumMap<Priority, Integer>) Priority.DEFAULT, (Priority) 0);
        PRIORITY_INT_MAP.put((EnumMap<Priority, Integer>) Priority.VERY_LOW, (Priority) 1);
        PRIORITY_INT_MAP.put((EnumMap<Priority, Integer>) Priority.HIGHEST, (Priority) 2);
        for (Priority p : PRIORITY_INT_MAP.keySet()) {
            PRIORITY_MAP.append(PRIORITY_INT_MAP.get(p).intValue(), p);
        }
    }

    public static Priority valueOf(int value) {
        Priority priority = PRIORITY_MAP.get(value);
        if (priority != null) {
            return priority;
        }
        throw new IllegalArgumentException("Unknown Priority for value " + value);
    }

    public static int toInt(Priority priority) {
        Integer integer = PRIORITY_INT_MAP.get(priority);
        if (integer != null) {
            return integer.intValue();
        }
        throw new IllegalStateException("PriorityMapping is missing known Priority value " + priority);
    }
}
