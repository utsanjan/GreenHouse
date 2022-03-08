package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import com.google.firebase.storage.internal.Util;
import java.io.UnsupportedEncodingException;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class FirebaseStorage {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String STORAGE_BUCKET_WITH_PATH_EXCEPTION = "The storage Uri cannot contain a path element.";
    private static final String STORAGE_URI_PARSE_EXCEPTION = "The storage Uri could not be parsed.";
    private static final String TAG = "FirebaseStorage";
    private final FirebaseApp mApp;
    private final Provider<InternalAuthProvider> mAuthProvider;
    private final String mBucketName;
    private long sMaxUploadRetry = 600000;
    private long sMaxDownloadRetry = 600000;
    private long sMaxQueryRetry = 120000;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseStorage(String bucketName, FirebaseApp app, Provider<InternalAuthProvider> authProvider) {
        this.mBucketName = bucketName;
        this.mApp = app;
        this.mAuthProvider = authProvider;
    }

    private static FirebaseStorage getInstanceImpl(FirebaseApp app, Uri url) {
        String bucketName = url != null ? url.getHost() : null;
        if (url == null || TextUtils.isEmpty(url.getPath())) {
            Preconditions.checkNotNull(app, "Provided FirebaseApp must not be null.");
            FirebaseStorageComponent component = (FirebaseStorageComponent) app.get(FirebaseStorageComponent.class);
            Preconditions.checkNotNull(component, "Firebase Storage component is not present.");
            return component.get(bucketName);
        }
        throw new IllegalArgumentException(STORAGE_BUCKET_WITH_PATH_EXCEPTION);
    }

    public static FirebaseStorage getInstance() {
        FirebaseApp app = FirebaseApp.getInstance();
        Preconditions.checkArgument(app != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(app);
    }

    public static FirebaseStorage getInstance(String url) {
        FirebaseApp app = FirebaseApp.getInstance();
        Preconditions.checkArgument(app != null, "You must call FirebaseApp.initialize() first.");
        return getInstance(app, url);
    }

    public static FirebaseStorage getInstance(FirebaseApp app) {
        Preconditions.checkArgument(app != null, "Null is not a valid value for the FirebaseApp.");
        String storageBucket = app.getOptions().getStorageBucket();
        if (storageBucket == null) {
            return getInstanceImpl(app, null);
        }
        try {
            return getInstanceImpl(app, Util.normalize(app, "gs://" + app.getOptions().getStorageBucket()));
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unable to parse bucket:" + storageBucket, e);
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    public static FirebaseStorage getInstance(FirebaseApp app, String url) {
        boolean z = true;
        Preconditions.checkArgument(app != null, "Null is not a valid value for the FirebaseApp.");
        if (url == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "Null is not a valid value for the Firebase Storage URL.");
        if (url.toLowerCase().startsWith("gs://")) {
            try {
                return getInstanceImpl(app, Util.normalize(app, url));
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Unable to parse url:" + url, e);
                throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
            }
        } else {
            throw new IllegalArgumentException("Please use a gs:// URL for your Firebase Storage bucket.");
        }
    }

    public long getMaxDownloadRetryTimeMillis() {
        return this.sMaxDownloadRetry;
    }

    public void setMaxDownloadRetryTimeMillis(long maxTransferRetryMillis) {
        this.sMaxDownloadRetry = maxTransferRetryMillis;
    }

    public long getMaxUploadRetryTimeMillis() {
        return this.sMaxUploadRetry;
    }

    public void setMaxUploadRetryTimeMillis(long maxTransferRetryMillis) {
        this.sMaxUploadRetry = maxTransferRetryMillis;
    }

    public long getMaxOperationRetryTimeMillis() {
        return this.sMaxQueryRetry;
    }

    public void setMaxOperationRetryTimeMillis(long maxTransferRetryMillis) {
        this.sMaxQueryRetry = maxTransferRetryMillis;
    }

    private String getBucketName() {
        return this.mBucketName;
    }

    public StorageReference getReference() {
        String bucketName = getBucketName();
        if (!TextUtils.isEmpty(bucketName)) {
            Uri uri = new Uri.Builder().scheme("gs").authority(getBucketName()).path("/").build();
            return getReference(uri);
        }
        throw new IllegalStateException("FirebaseApp was not initialized with a bucket name.");
    }

    public StorageReference getReferenceFromUrl(String fullUrl) {
        Preconditions.checkArgument(!TextUtils.isEmpty(fullUrl), "location must not be null or empty");
        String lowerCaseLocation = fullUrl.toLowerCase();
        if (lowerCaseLocation.startsWith("gs://") || lowerCaseLocation.startsWith("https://") || lowerCaseLocation.startsWith("http://")) {
            try {
                Uri uri = Util.normalize(this.mApp, fullUrl);
                if (uri != null) {
                    return getReference(uri);
                }
                throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Unable to parse location:" + fullUrl, e);
                throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
            }
        } else {
            throw new IllegalArgumentException(STORAGE_URI_PARSE_EXCEPTION);
        }
    }

    public StorageReference getReference(String location) {
        Preconditions.checkArgument(!TextUtils.isEmpty(location), "location must not be null or empty");
        String lowerCaseLocation = location.toLowerCase();
        if (!lowerCaseLocation.startsWith("gs://") && !lowerCaseLocation.startsWith("https://") && !lowerCaseLocation.startsWith("http://")) {
            return getReference().child(location);
        }
        throw new IllegalArgumentException("location should not be a full URL.");
    }

    private StorageReference getReference(Uri uri) {
        Preconditions.checkNotNull(uri, "uri must not be null");
        String bucketName = getBucketName();
        Preconditions.checkArgument(TextUtils.isEmpty(bucketName) || uri.getAuthority().equalsIgnoreCase(bucketName), "The supplied bucketname does not match the storage bucket of the current instance.");
        return new StorageReference(uri, this);
    }

    public FirebaseApp getApp() {
        return this.mApp;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InternalAuthProvider getAuthProvider() {
        Provider<InternalAuthProvider> provider = this.mAuthProvider;
        if (provider != null) {
            return provider.get();
        }
        return null;
    }
}
