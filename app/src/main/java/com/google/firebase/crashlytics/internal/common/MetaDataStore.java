package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
class MetaDataStore {
    private static final String KEYDATA_SUFFIX = "keys";
    private static final String KEY_USER_ID = "userId";
    private static final String METADATA_EXT = ".meta";
    private static final String USERDATA_SUFFIX = "user";
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final File filesDir;

    public MetaDataStore(File filesDir) {
        this.filesDir = filesDir;
    }

    public void writeUserData(String sessionId, UserMetadata data) {
        File f = getUserDataFileForSession(sessionId);
        Writer writer = null;
        try {
            try {
                String userDataString = userDataToJson(data);
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), UTF_8));
                writer.write(userDataString);
                writer.flush();
            } catch (Exception e) {
                Logger.getLogger().e("Error serializing user metadata.", e);
            }
        } finally {
            CommonUtils.closeOrLog(writer, "Failed to close user metadata file.");
        }
    }

    /* JADX WARN: Finally extract failed */
    public UserMetadata readUserData(String sessionId) {
        File f = getUserDataFileForSession(sessionId);
        if (!f.exists()) {
            return new UserMetadata();
        }
        InputStream is = null;
        try {
            try {
                is = new FileInputStream(f);
                UserMetadata jsonToUserData = jsonToUserData(CommonUtils.streamToString(is));
                CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
                return jsonToUserData;
            } catch (Exception e) {
                Logger.getLogger().e("Error deserializing user metadata.", e);
                CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
                return new UserMetadata();
            }
        } catch (Throwable th) {
            CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
            throw th;
        }
    }

    public void writeKeyData(String sessionId, Map<String, String> keyData) {
        File f = getKeysFileForSession(sessionId);
        Writer writer = null;
        try {
            try {
                String keyDataString = keysDataToJson(keyData);
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), UTF_8));
                writer.write(keyDataString);
                writer.flush();
            } catch (Exception e) {
                Logger.getLogger().e("Error serializing key/value metadata.", e);
            }
        } finally {
            CommonUtils.closeOrLog(writer, "Failed to close key/value metadata file.");
        }
    }

    /* JADX WARN: Finally extract failed */
    public Map<String, String> readKeyData(String sessionId) {
        File f = getKeysFileForSession(sessionId);
        if (!f.exists()) {
            return Collections.emptyMap();
        }
        InputStream is = null;
        try {
            try {
                is = new FileInputStream(f);
                Map<String, String> jsonToKeysData = jsonToKeysData(CommonUtils.streamToString(is));
                CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
                return jsonToKeysData;
            } catch (Exception e) {
                Logger.getLogger().e("Error deserializing user metadata.", e);
                CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
                return Collections.emptyMap();
            }
        } catch (Throwable th) {
            CommonUtils.closeOrLog(is, "Failed to close user metadata file.");
            throw th;
        }
    }

    public File getUserDataFileForSession(String sessionId) {
        File file = this.filesDir;
        return new File(file, sessionId + USERDATA_SUFFIX + METADATA_EXT);
    }

    public File getKeysFileForSession(String sessionId) {
        File file = this.filesDir;
        return new File(file, sessionId + KEYDATA_SUFFIX + METADATA_EXT);
    }

    private static UserMetadata jsonToUserData(String json) throws JSONException {
        JSONObject dataObj = new JSONObject(json);
        UserMetadata metadata = new UserMetadata();
        metadata.setUserId(valueOrNull(dataObj, KEY_USER_ID));
        return metadata;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.google.firebase.crashlytics.internal.common.MetaDataStore$1] */
    private static String userDataToJson(final UserMetadata userData) throws JSONException {
        return new JSONObject() { // from class: com.google.firebase.crashlytics.internal.common.MetaDataStore.1
            {
                put(MetaDataStore.KEY_USER_ID, UserMetadata.this.getUserId());
            }
        }.toString();
    }

    private static Map<String, String> jsonToKeysData(String json) throws JSONException {
        JSONObject dataObj = new JSONObject(json);
        Map<String, String> keyData = new HashMap<>();
        Iterator<String> keyIter = dataObj.keys();
        while (keyIter.hasNext()) {
            String key = keyIter.next();
            keyData.put(key, valueOrNull(dataObj, key));
        }
        return keyData;
    }

    private static String keysDataToJson(Map<String, String> keyData) throws JSONException {
        return new JSONObject(keyData).toString();
    }

    private static String valueOrNull(JSONObject json, String key) {
        if (!json.isNull(key)) {
            return json.optString(key, null);
        }
        return null;
    }
}
