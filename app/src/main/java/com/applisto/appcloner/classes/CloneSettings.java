package com.applisto.appcloner.classes;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.applisto.appcloner.classes.util.SimpleCrypt;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CloneSettings {
    private static final String PREF_KEY_CLONE_TIMESTAMP = "com.applisto.appcloner.classes.cloneTimestamp";
    private static final String TAG = CloneSettings.class.getSimpleName();
    private static CloneSettings sCloneSettings;
    private File mCloneSettingsFile;
    private JSONObject mJsonObject;

    public static synchronized CloneSettings getInstance(Context context) {
        CloneSettings cloneSettings;
        synchronized (CloneSettings.class) {
            if (sCloneSettings == null) {
                Context applicationContext = context.getApplicationContext();
                if (applicationContext != null) {
                    context = applicationContext;
                }
                if (context instanceof Application) {
                    Field f = ContextWrapper.class.getDeclaredField("mBase");
                    f.setAccessible(true);
                    context = (Context) f.get(context);
                }
                sCloneSettings = new CloneSettings(context);
            }
            cloneSettings = sCloneSettings;
        }
        return cloneSettings;
    }

    private CloneSettings(Context context) {
        long cloneTimestamp;
        String json;
        this.mJsonObject = new JSONObject();
        try {
            this.mCloneSettingsFile = new File(context.getFilesDir(), "cloneSettings.json");
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            Bundle metaData = applicationInfo.metaData;
            String originalCloneTimestamp = metaData.getString("com.applisto.appcloner.originalCloneTimestamp");
            if (TextUtils.isEmpty(originalCloneTimestamp)) {
                cloneTimestamp = Long.parseLong(metaData.getString("com.applisto.appcloner.cloneTimestamp"));
            } else {
                cloneTimestamp = Long.parseLong(originalCloneTimestamp);
            }
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            long oldCloneTimestamp = preferences.getLong(PREF_KEY_CLONE_TIMESTAMP, 0L);
            if (oldCloneTimestamp != cloneTimestamp) {
                if (this.mCloneSettingsFile.delete()) {
                    Log.i(TAG, "CloneSettings; deleted old clone settings file");
                }
                preferences.edit().putLong(PREF_KEY_CLONE_TIMESTAMP, cloneTimestamp).apply();
            }
            if (this.mCloneSettingsFile.exists()) {
                json = toString(new FileInputStream(this.mCloneSettingsFile));
            } else {
                json = getDefaultCloneSettingsJson(context);
                try {
                    PrintWriter pw = new PrintWriter(this.mCloneSettingsFile, "UTF-8");
                    pw.write(json);
                    pw.close();
                } catch (Exception e) {
                    Log.w(TAG, e);
                }
            }
            this.mJsonObject = new JSONObject(json);
        } catch (Exception e2) {
            Log.w(TAG, e2);
        }
    }

    private CloneSettings() {
        this.mJsonObject = new JSONObject();
        this.mCloneSettingsFile = null;
        this.mJsonObject = new JSONObject();
    }

    private CloneSettings(JSONObject jsonObject) {
        this.mJsonObject = new JSONObject();
        this.mCloneSettingsFile = null;
        this.mJsonObject = jsonObject;
    }

    public File getCloneSettingsFile() {
        return this.mCloneSettingsFile;
    }

    public boolean has(String name) {
        return this.mJsonObject.has(name);
    }

    public String getString(String name, String defaultValue) {
        try {
            return this.mJsonObject.getString(name);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Integer getInteger(String name, Integer defaultValue) {
        try {
            return Integer.valueOf(this.mJsonObject.getInt(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Long getLong(String name, Long defaultValue) {
        try {
            return Long.valueOf(this.mJsonObject.getLong(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Float getFloat(String name, Float defaultValue) {
        try {
            return Float.valueOf((float) this.mJsonObject.getDouble(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Double getDouble(String name, Double defaultValue) {
        try {
            return Double.valueOf(this.mJsonObject.getDouble(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public Boolean getBoolean(String name, Boolean defaultValue) {
        try {
            return Boolean.valueOf(this.mJsonObject.getBoolean(name));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public CloneSettings forObject(String name) {
        try {
            return new CloneSettings(this.mJsonObject.getJSONObject(name));
        } catch (JSONException e) {
            return new CloneSettings();
        }
    }

    public List<CloneSettings> forObjectArray(String name) {
        try {
            List<CloneSettings> list = new ArrayList<>();
            JSONArray arr = this.mJsonObject.getJSONArray(name);
            for (int n = 0; n < arr.length(); n++) {
                list.add(new CloneSettings(arr.getJSONObject(n)));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public List<String> getStringList(String name) {
        try {
            List<String> list = new ArrayList<>();
            JSONArray arr = this.mJsonObject.getJSONArray(name);
            for (int n = 0; n < arr.length(); n++) {
                list.add(arr.getString(n));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, String> getStringMap(String name) {
        try {
            Map<String, String> map = new HashMap<>();
            JSONObject o = this.mJsonObject.getJSONObject(name);
            Iterator<String> it = o.keys();
            while (it.hasNext()) {
                String key = it.next();
                String value = o.getString(key);
                map.put(key, value);
            }
            return map;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Map<String, Object>> getMapList(String name) {
        try {
            List<Map<String, Object>> list = new ArrayList<>();
            JSONArray arr = this.mJsonObject.getJSONArray(name);
            for (int n = 0; n < arr.length(); n++) {
                Map<String, Object> map = new HashMap<>();
                JSONObject o = arr.getJSONObject(n);
                Iterator<String> it = o.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    Object value = o.get(key);
                    map.put(key, value);
                }
                list.add(map);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    private static String getDefaultCloneSettingsJson(Context context) throws IOException {
        Log.i(TAG, "getDefaultCloneSettingsJson; ");
        long ts = System.currentTimeMillis();
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            ZipFile zipFile = new ZipFile(ai.publicSourceDir);
            ZipEntry entry = zipFile.getEntry("cloneSettings.json");
            return new SimpleCrypt("UYGy723!Po-efjve").decrypt(toString(zipFile.getInputStream(entry)));
        } catch (Exception e) {
            Log.w(TAG, e);
            return toString(context.getAssets().open("cloneSettings.json"));
        } finally {
            String str = TAG;
            Log.i(str, "getDefaultCloneSettingsJson; took: " + (System.currentTimeMillis() - ts) + " millis");
        }
    }

    private static String toString(InputStream is) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        try {
            StringBuilder b = new StringBuilder();
            while (true) {
                String line = r.readLine();
                if (line == null) {
                    break;
                }
                b.append(line);
                b.append('\n');
            }
            return b.toString();
        } finally {
            try {
                r.close();
            } catch (Exception e) {
            }
        }
    }
}
