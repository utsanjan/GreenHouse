package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.internal.Slashes;
import com.google.firebase.storage.internal.Util;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class StorageMetadata {
    private static final String BUCKET_KEY = "bucket";
    private static final String CACHE_CONTROL = "cacheControl";
    private static final String CONTENT_DISPOSITION = "contentDisposition";
    private static final String CONTENT_ENCODING = "contentEncoding";
    private static final String CONTENT_LANGUAGE = "contentLanguage";
    private static final String CONTENT_TYPE_KEY = "contentType";
    private static final String CUSTOM_METADATA_KEY = "metadata";
    private static final String GENERATION_KEY = "generation";
    private static final String MD5_HASH_KEY = "md5Hash";
    private static final String META_GENERATION_KEY = "metageneration";
    private static final String NAME_KEY = "name";
    private static final String SIZE_KEY = "size";
    private static final String TAG = "StorageMetadata";
    private static final String TIME_CREATED_KEY = "timeCreated";
    private static final String TIME_UPDATED_KEY = "updated";
    private String mBucket;
    private MetadataValue<String> mCacheControl;
    private MetadataValue<String> mContentDisposition;
    private MetadataValue<String> mContentEncoding;
    private MetadataValue<String> mContentLanguage;
    private MetadataValue<String> mContentType;
    private String mCreationTime;
    private MetadataValue<Map<String, String>> mCustomMetadata;
    private String mGeneration;
    private String mMD5Hash;
    private String mMetadataGeneration;
    private String mPath;
    private long mSize;
    private FirebaseStorage mStorage;
    private StorageReference mStorageRef;
    private String mUpdatedTime;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
    /* loaded from: classes.dex */
    public static class MetadataValue<T> {
        private final boolean userProvided;
        private final T value;

        MetadataValue(T value, boolean userProvided) {
            this.userProvided = userProvided;
            this.value = value;
        }

        static <T> MetadataValue<T> withDefaultValue(T value) {
            return new MetadataValue<>(value, false);
        }

        static <T> MetadataValue<T> withUserValue(T value) {
            return new MetadataValue<>(value, true);
        }

        boolean isUserProvided() {
            return this.userProvided;
        }

        T getValue() {
            return this.value;
        }
    }

    public StorageMetadata() {
        this.mPath = null;
        this.mStorage = null;
        this.mStorageRef = null;
        this.mBucket = null;
        this.mGeneration = null;
        this.mContentType = MetadataValue.withDefaultValue("");
        this.mMetadataGeneration = null;
        this.mCreationTime = null;
        this.mUpdatedTime = null;
        this.mMD5Hash = null;
        this.mCacheControl = MetadataValue.withDefaultValue("");
        this.mContentDisposition = MetadataValue.withDefaultValue("");
        this.mContentEncoding = MetadataValue.withDefaultValue("");
        this.mContentLanguage = MetadataValue.withDefaultValue("");
        this.mCustomMetadata = MetadataValue.withDefaultValue(Collections.emptyMap());
    }

    private StorageMetadata(StorageMetadata original, boolean fullClone) {
        this.mPath = null;
        this.mStorage = null;
        this.mStorageRef = null;
        this.mBucket = null;
        this.mGeneration = null;
        this.mContentType = MetadataValue.withDefaultValue("");
        this.mMetadataGeneration = null;
        this.mCreationTime = null;
        this.mUpdatedTime = null;
        this.mMD5Hash = null;
        this.mCacheControl = MetadataValue.withDefaultValue("");
        this.mContentDisposition = MetadataValue.withDefaultValue("");
        this.mContentEncoding = MetadataValue.withDefaultValue("");
        this.mContentLanguage = MetadataValue.withDefaultValue("");
        this.mCustomMetadata = MetadataValue.withDefaultValue(Collections.emptyMap());
        Preconditions.checkNotNull(original);
        this.mPath = original.mPath;
        this.mStorage = original.mStorage;
        this.mStorageRef = original.mStorageRef;
        this.mBucket = original.mBucket;
        this.mContentType = original.mContentType;
        this.mCacheControl = original.mCacheControl;
        this.mContentDisposition = original.mContentDisposition;
        this.mContentEncoding = original.mContentEncoding;
        this.mContentLanguage = original.mContentLanguage;
        this.mCustomMetadata = original.mCustomMetadata;
        if (fullClone) {
            this.mMD5Hash = original.mMD5Hash;
            this.mSize = original.mSize;
            this.mUpdatedTime = original.mUpdatedTime;
            this.mCreationTime = original.mCreationTime;
            this.mMetadataGeneration = original.mMetadataGeneration;
            this.mGeneration = original.mGeneration;
        }
    }

    public String getContentType() {
        return this.mContentType.getValue();
    }

    public String getCustomMetadata(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        Map<String, String> metadata = this.mCustomMetadata.getValue();
        return metadata.get(key);
    }

    public Set<String> getCustomMetadataKeys() {
        Map<String, String> metadata = this.mCustomMetadata.getValue();
        return metadata.keySet();
    }

    public String getPath() {
        String str = this.mPath;
        return str != null ? str : "";
    }

    public String getName() {
        String path = getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        int lastIndex = path.lastIndexOf(47);
        if (lastIndex != -1) {
            return path.substring(lastIndex + 1);
        }
        return path;
    }

    public String getBucket() {
        return this.mBucket;
    }

    public String getGeneration() {
        return this.mGeneration;
    }

    public String getMetadataGeneration() {
        return this.mMetadataGeneration;
    }

    public long getCreationTimeMillis() {
        return Util.parseDateTime(this.mCreationTime);
    }

    public long getUpdatedTimeMillis() {
        return Util.parseDateTime(this.mUpdatedTime);
    }

    public long getSizeBytes() {
        return this.mSize;
    }

    public String getMd5Hash() {
        return this.mMD5Hash;
    }

    public String getCacheControl() {
        return this.mCacheControl.getValue();
    }

    public String getContentDisposition() {
        return this.mContentDisposition.getValue();
    }

    public String getContentEncoding() {
        return this.mContentEncoding.getValue();
    }

    public String getContentLanguage() {
        return this.mContentLanguage.getValue();
    }

    public StorageReference getReference() {
        if (this.mStorageRef != null || this.mStorage == null) {
            return this.mStorageRef;
        }
        String bucket = getBucket();
        String path = getPath();
        if (TextUtils.isEmpty(bucket) || TextUtils.isEmpty(path)) {
            return null;
        }
        Uri uri = new Uri.Builder().scheme("gs").authority(bucket).encodedPath(Slashes.preserveSlashEncode(path)).build();
        return new StorageReference(uri, this.mStorage);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject createJSONObject() {
        Map<String, Object> jsonData = new HashMap<>();
        if (this.mContentType.isUserProvided()) {
            jsonData.put(CONTENT_TYPE_KEY, getContentType());
        }
        if (this.mCustomMetadata.isUserProvided()) {
            jsonData.put(CUSTOM_METADATA_KEY, new JSONObject(this.mCustomMetadata.getValue()));
        }
        if (this.mCacheControl.isUserProvided()) {
            jsonData.put(CACHE_CONTROL, getCacheControl());
        }
        if (this.mContentDisposition.isUserProvided()) {
            jsonData.put(CONTENT_DISPOSITION, getContentDisposition());
        }
        if (this.mContentEncoding.isUserProvided()) {
            jsonData.put(CONTENT_ENCODING, getContentEncoding());
        }
        if (this.mContentLanguage.isUserProvided()) {
            jsonData.put(CONTENT_LANGUAGE, getContentLanguage());
        }
        return new JSONObject(jsonData);
    }

    /* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
    /* loaded from: classes.dex */
    public static class Builder {
        boolean mFromJSON;
        StorageMetadata mMetadata;

        public Builder() {
            this.mMetadata = new StorageMetadata();
        }

        public Builder(StorageMetadata original) {
            this.mMetadata = new StorageMetadata(false);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Builder(JSONObject resultBody, StorageReference thisStorageRef) throws JSONException {
            this(resultBody);
            this.mMetadata.mStorageRef = thisStorageRef;
        }

        Builder(JSONObject resultBody) throws JSONException {
            this.mMetadata = new StorageMetadata();
            if (resultBody != null) {
                parseJSON(resultBody);
                this.mFromJSON = true;
            }
        }

        private String extractString(JSONObject jsonObject, String key) throws JSONException {
            if (!jsonObject.has(key) || jsonObject.isNull(key)) {
                return null;
            }
            return jsonObject.getString(key);
        }

        private void parseJSON(JSONObject jsonObject) throws JSONException {
            this.mMetadata.mGeneration = jsonObject.optString(StorageMetadata.GENERATION_KEY);
            this.mMetadata.mPath = jsonObject.optString("name");
            this.mMetadata.mBucket = jsonObject.optString(StorageMetadata.BUCKET_KEY);
            this.mMetadata.mMetadataGeneration = jsonObject.optString(StorageMetadata.META_GENERATION_KEY);
            this.mMetadata.mCreationTime = jsonObject.optString(StorageMetadata.TIME_CREATED_KEY);
            this.mMetadata.mUpdatedTime = jsonObject.optString(StorageMetadata.TIME_UPDATED_KEY);
            this.mMetadata.mSize = jsonObject.optLong(StorageMetadata.SIZE_KEY);
            this.mMetadata.mMD5Hash = jsonObject.optString(StorageMetadata.MD5_HASH_KEY);
            if (jsonObject.has(StorageMetadata.CUSTOM_METADATA_KEY) && !jsonObject.isNull(StorageMetadata.CUSTOM_METADATA_KEY)) {
                JSONObject customMetadata = jsonObject.getJSONObject(StorageMetadata.CUSTOM_METADATA_KEY);
                Iterator<String> keyIterator = customMetadata.keys();
                while (keyIterator.hasNext()) {
                    String key = keyIterator.next();
                    setCustomMetadata(key, customMetadata.getString(key));
                }
            }
            String value = extractString(jsonObject, StorageMetadata.CONTENT_TYPE_KEY);
            if (value != null) {
                setContentType(value);
            }
            String value2 = extractString(jsonObject, StorageMetadata.CACHE_CONTROL);
            if (value2 != null) {
                setCacheControl(value2);
            }
            String value3 = extractString(jsonObject, StorageMetadata.CONTENT_DISPOSITION);
            if (value3 != null) {
                setContentDisposition(value3);
            }
            String value4 = extractString(jsonObject, StorageMetadata.CONTENT_ENCODING);
            if (value4 != null) {
                setContentEncoding(value4);
            }
            String value5 = extractString(jsonObject, StorageMetadata.CONTENT_LANGUAGE);
            if (value5 != null) {
                setContentLanguage(value5);
            }
        }

        public StorageMetadata build() {
            return new StorageMetadata(this.mFromJSON);
        }

        public Builder setContentLanguage(String contentLanguage) {
            this.mMetadata.mContentLanguage = MetadataValue.withUserValue(contentLanguage);
            return this;
        }

        public String getContentLanguage() {
            return (String) this.mMetadata.mContentLanguage.getValue();
        }

        public Builder setContentEncoding(String contentEncoding) {
            this.mMetadata.mContentEncoding = MetadataValue.withUserValue(contentEncoding);
            return this;
        }

        public String getContentEncoding() {
            return (String) this.mMetadata.mContentEncoding.getValue();
        }

        public Builder setContentDisposition(String contentDisposition) {
            this.mMetadata.mContentDisposition = MetadataValue.withUserValue(contentDisposition);
            return this;
        }

        public String getContentDisposition() {
            return (String) this.mMetadata.mContentDisposition.getValue();
        }

        public Builder setCacheControl(String cacheControl) {
            this.mMetadata.mCacheControl = MetadataValue.withUserValue(cacheControl);
            return this;
        }

        public String getCacheControl() {
            return (String) this.mMetadata.mCacheControl.getValue();
        }

        public Builder setCustomMetadata(String key, String value) {
            if (!this.mMetadata.mCustomMetadata.isUserProvided()) {
                this.mMetadata.mCustomMetadata = MetadataValue.withUserValue(new HashMap());
            }
            ((Map) this.mMetadata.mCustomMetadata.getValue()).put(key, value);
            return this;
        }

        public Builder setContentType(String contentType) {
            this.mMetadata.mContentType = MetadataValue.withUserValue(contentType);
            return this;
        }

        public String getContentType() {
            return (String) this.mMetadata.mContentType.getValue();
        }
    }
}
