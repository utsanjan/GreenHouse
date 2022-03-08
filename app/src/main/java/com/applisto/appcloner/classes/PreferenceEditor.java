package com.applisto.appcloner.classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class PreferenceEditor {
    private static final String TAG = PreferenceEditor.class.getSimpleName();
    private static final String TYPE_BOOLEAN = "Boolean";
    private static final String TYPE_FLOAT = "Float";
    private static final String TYPE_INTEGER = "Integer";
    private static final String TYPE_LONG = "Long";
    private static final String TYPE_STRING = "String";
    private static final String TYPE_STRING_SET = "String set";

    public static String[] getPreferenceFiles(Context context) {
        Log.i(TAG, "getPreferenceFiles; ");
        File directory = new File(context.getApplicationInfo().dataDir, "shared_prefs");
        String str = TAG;
        Log.i(str, "getPreferenceFiles; directory: " + directory);
        if (directory.exists() && directory.isDirectory()) {
            String[] files = directory.list();
            String str2 = TAG;
            Log.i(str2, "getPreferenceFiles; files: " + Arrays.toString(files));
            if (files != null) {
                Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);
                return files;
            }
        }
        return new String[0];
    }

    public static Map<String, String> getPreferences(Context context, String preferenceFile) {
        String str = TAG;
        Log.i(str, "getPreferenceKeys; preferenceFile: " + preferenceFile);
        SharedPreferences preferences = getSharedPreferences(context, preferenceFile);
        Map<String, String> map = new HashMap<>();
        Map<String, ?> all = preferences.getAll();
        for (String key : all.keySet()) {
            if (key != null && !key.startsWith("com.applisto.appcloner")) {
                Object value = all.get(key);
                String s = valueToString(value);
                if (s != null) {
                    map.put(key, s);
                }
            }
        }
        return map;
    }

    public static void setPreference(Context context, String preferenceFile, String key, String preference) {
        String str = TAG;
        Log.i(str, "getPreferenceValue; preferenceFile: " + preferenceFile + ", key: " + key + ", preference: " + preference);
        SharedPreferences.Editor editor = getSharedPreferences(context, preferenceFile).edit();
        if (preference == null) {
            editor.remove(key);
        } else {
            String[] arr = preference.split(":", 2);
            String type = arr[0];
            String value = arr[1];
            String str2 = TAG;
            Log.i(str2, "setPreference; type: " + type + ", value: " + value);
            if (TYPE_STRING.equals(type)) {
                editor.putString(key, value);
            } else if (TYPE_INTEGER.equals(type)) {
                editor.putInt(key, Integer.parseInt(value));
            } else if (TYPE_LONG.equals(type)) {
                editor.putLong(key, Long.parseLong(value));
            } else if (TYPE_FLOAT.equals(type)) {
                editor.putFloat(key, Float.parseFloat(value));
            } else if (TYPE_BOOLEAN.equals(type)) {
                editor.putBoolean(key, Boolean.parseBoolean(value));
            } else if (TYPE_STRING_SET.equals(type)) {
                editor.putStringSet(key, new HashSet(Arrays.asList(value.split(","))));
            }
        }
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences(Context context, String preferenceFile) {
        return context.getSharedPreferences(preferenceFile.replace(".xml", ""), 0);
    }

    private static String valueToString(Object value) {
        if (value instanceof String) {
            return "String:" + value;
        } else if (value instanceof Integer) {
            return "Integer:" + value;
        } else if (value instanceof Long) {
            return "Long:" + value;
        } else if (value instanceof Float) {
            return "Float:" + value;
        } else if (value instanceof Boolean) {
            return "Boolean:" + value;
        } else if (!(value instanceof Set)) {
            return null;
        } else {
            return "String set:" + TextUtils.join(",", (Set) value);
        }
    }
}
