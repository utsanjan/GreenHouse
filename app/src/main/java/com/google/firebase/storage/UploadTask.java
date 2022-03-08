package com.google.firebase.storage;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.internal.AdaptiveStreamBuffer;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.internal.Util;
import com.google.firebase.storage.network.NetworkRequest;
import com.google.firebase.storage.network.ResumableUploadByteRequest;
import com.google.firebase.storage.network.ResumableUploadCancelRequest;
import com.google.firebase.storage.network.ResumableUploadQueryRequest;
import com.google.firebase.storage.network.ResumableUploadStartRequest;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class UploadTask extends StorageTask<TaskSnapshot> {
    private static final String APPLICATION_OCTET_STREAM = "application/octet-stream";
    private static final int MAXIMUM_CHUNK_SIZE = 33554432;
    static final int PREFERRED_CHUNK_SIZE = 262144;
    private static final String RESUMABLE_FINAL_STATUS = "final";
    private static final String TAG = "UploadTask";
    private static final String X_GOOG_UPLOAD_URL = "X-Goog-Upload-URL";
    private final InternalAuthProvider mAuthProvider;
    private final AtomicLong mBytesUploaded;
    private int mCurrentChunkSize;
    private volatile Exception mException;
    private boolean mIsStreamOwned;
    private volatile StorageMetadata mMetadata;
    private volatile int mResultCode;
    private ExponentialBackoffSender mSender;
    private volatile Exception mServerException;
    private volatile String mServerStatus;
    private final StorageReference mStorageRef;
    private final AdaptiveStreamBuffer mStreamBuffer;
    private final long mTotalByteCount;
    private volatile Uri mUploadUri;
    private final Uri mUri;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadTask(StorageReference targetRef, StorageMetadata metadata, byte[] bytes) {
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        Preconditions.checkNotNull(targetRef);
        Preconditions.checkNotNull(bytes);
        FirebaseStorage storage = targetRef.getStorage();
        this.mTotalByteCount = bytes.length;
        this.mStorageRef = targetRef;
        this.mMetadata = metadata;
        this.mAuthProvider = storage.getAuthProvider();
        this.mUri = null;
        this.mStreamBuffer = new AdaptiveStreamBuffer(new ByteArrayInputStream(bytes), 262144);
        this.mIsStreamOwned = true;
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadTask(StorageReference targetRef, StorageMetadata metadata, Uri file, Uri existingUploadUri) {
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        Preconditions.checkNotNull(targetRef);
        Preconditions.checkNotNull(file);
        FirebaseStorage storage = targetRef.getStorage();
        this.mStorageRef = targetRef;
        this.mMetadata = metadata;
        this.mAuthProvider = storage.getAuthProvider();
        this.mUri = file;
        InputStream inputStream = null;
        this.mSender = new ExponentialBackoffSender(this.mStorageRef.getApp().getApplicationContext(), this.mAuthProvider, storage.getMaxUploadRetryTimeMillis());
        long size = -1;
        try {
            ContentResolver resolver = this.mStorageRef.getStorage().getApp().getApplicationContext().getContentResolver();
            try {
                ParcelFileDescriptor fd = resolver.openFileDescriptor(this.mUri, "r");
                if (fd != null) {
                    size = fd.getStatSize();
                    fd.close();
                }
            } catch (IOException checkSizeError) {
                Log.w(TAG, "could not retrieve file size for upload " + this.mUri.toString(), checkSizeError);
            } catch (NullPointerException npe) {
                Log.w(TAG, "NullPointerException during file size calculation.", npe);
                size = -1;
            }
            inputStream = resolver.openInputStream(this.mUri);
            if (inputStream != null) {
                if (size == -1) {
                    try {
                        int streamSize = inputStream.available();
                        if (streamSize >= 0) {
                            size = streamSize;
                        }
                    } catch (IOException e) {
                    }
                }
                inputStream = new BufferedInputStream(inputStream);
            }
        } catch (FileNotFoundException e2) {
            Log.e(TAG, "could not locate file for uploading:" + this.mUri.toString());
            this.mException = e2;
        }
        this.mTotalByteCount = size;
        this.mStreamBuffer = new AdaptiveStreamBuffer(inputStream, 262144);
        this.mIsStreamOwned = true;
        this.mUploadUri = existingUploadUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UploadTask(StorageReference targetRef, StorageMetadata metadata, InputStream stream) {
        this.mBytesUploaded = new AtomicLong(0L);
        this.mCurrentChunkSize = 262144;
        this.mUploadUri = null;
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        Preconditions.checkNotNull(targetRef);
        Preconditions.checkNotNull(stream);
        FirebaseStorage storage = targetRef.getStorage();
        this.mTotalByteCount = -1L;
        this.mStorageRef = targetRef;
        this.mMetadata = metadata;
        this.mAuthProvider = storage.getAuthProvider();
        this.mStreamBuffer = new AdaptiveStreamBuffer(stream, 262144);
        this.mIsStreamOwned = false;
        this.mUri = null;
        this.mSender = new ExponentialBackoffSender(this.mStorageRef.getApp().getApplicationContext(), this.mAuthProvider, this.mStorageRef.getStorage().getMaxUploadRetryTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public StorageReference getStorage() {
        return this.mStorageRef;
    }

    long getTotalByteCount() {
        return this.mTotalByteCount;
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void schedule() {
        StorageTaskScheduler.getInstance().scheduleUpload(getRunnable());
    }

    @Override // com.google.firebase.storage.StorageTask
    void run() {
        this.mSender.reset();
        if (!tryChangeState(4, false)) {
            Log.d(TAG, "The upload cannot continue as it is not in a valid state.");
            return;
        }
        if (this.mStorageRef.getParent() == null) {
            this.mException = new IllegalArgumentException("Cannot upload to getRoot. You should upload to a storage location such as .getReference('image.png').putFile...");
        }
        if (this.mException == null) {
            if (this.mUploadUri == null) {
                beginResumableUpload();
            } else {
                recoverStatus(false);
            }
            boolean shouldContinueToRun = shouldContinue();
            while (shouldContinueToRun) {
                uploadChunk();
                shouldContinueToRun = shouldContinue();
                if (shouldContinueToRun) {
                    tryChangeState(4, false);
                }
            }
            if (this.mIsStreamOwned && getInternalState() != 16) {
                try {
                    this.mStreamBuffer.close();
                } catch (IOException e) {
                    Log.e(TAG, "Unable to close stream.", e);
                }
            }
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void resetState() {
        this.mException = null;
        this.mServerException = null;
        this.mResultCode = 0;
        this.mServerStatus = null;
    }

    private void beginResumableUpload() {
        String mimeType = null;
        if (this.mMetadata != null) {
            mimeType = this.mMetadata.getContentType();
        }
        if (this.mUri != null && TextUtils.isEmpty(mimeType)) {
            Context context = this.mStorageRef.getStorage().getApp().getApplicationContext();
            mimeType = context.getContentResolver().getType(this.mUri);
        }
        if (TextUtils.isEmpty(mimeType)) {
            mimeType = APPLICATION_OCTET_STREAM;
        }
        NetworkRequest startRequest = new ResumableUploadStartRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp(), this.mMetadata != null ? this.mMetadata.createJSONObject() : null, mimeType);
        if (sendWithRetry(startRequest)) {
            String uploadURL = startRequest.getResultString(X_GOOG_UPLOAD_URL);
            if (!TextUtils.isEmpty(uploadURL)) {
                this.mUploadUri = Uri.parse(uploadURL);
            }
        }
    }

    private boolean shouldContinue() {
        if (getInternalState() == 128) {
            return false;
        }
        if (Thread.interrupted()) {
            this.mException = new InterruptedException();
            tryChangeState(64, false);
            return false;
        } else if (getInternalState() == 32) {
            tryChangeState(256, false);
            return false;
        } else if (getInternalState() == 8) {
            tryChangeState(16, false);
            return false;
        } else if (!serverStateValid()) {
            return false;
        } else {
            if (this.mUploadUri == null) {
                if (this.mException == null) {
                    this.mException = new IllegalStateException("Unable to obtain an upload URL.");
                }
                tryChangeState(64, false);
                return false;
            } else if (this.mException != null) {
                tryChangeState(64, false);
                return false;
            } else {
                boolean inErrorState = this.mServerException != null || this.mResultCode < 200 || this.mResultCode >= 300;
                if (!inErrorState || recoverStatus(true)) {
                    return true;
                }
                if (serverStateValid()) {
                    tryChangeState(64, false);
                }
                return false;
            }
        }
    }

    private boolean serverStateValid() {
        if (!RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return true;
        }
        if (this.mException == null) {
            this.mException = new IOException("The server has terminated the upload session", this.mServerException);
        }
        tryChangeState(64, false);
        return false;
    }

    private boolean recoverStatus(boolean withRetry) {
        long newBytesUploaded;
        NetworkRequest queryRequest = new ResumableUploadQueryRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp(), this.mUploadUri);
        if (RESUMABLE_FINAL_STATUS.equals(this.mServerStatus)) {
            return false;
        }
        if (withRetry) {
            if (!sendWithRetry(queryRequest)) {
                return false;
            }
        } else if (!send(queryRequest)) {
            return false;
        }
        if (RESUMABLE_FINAL_STATUS.equals(queryRequest.getResultString("X-Goog-Upload-Status"))) {
            this.mException = new IOException("The server has terminated the upload session");
            return false;
        }
        String bytes = queryRequest.getResultString("X-Goog-Upload-Size-Received");
        if (!TextUtils.isEmpty(bytes)) {
            newBytesUploaded = Long.parseLong(bytes);
        } else {
            newBytesUploaded = 0;
        }
        long currentBytes = this.mBytesUploaded.get();
        if (currentBytes > newBytesUploaded) {
            this.mException = new IOException("Unexpected error. The server lost a chunk update.");
            return false;
        } else if (currentBytes >= newBytesUploaded) {
            return true;
        } else {
            try {
                if (this.mStreamBuffer.advance((int) (newBytesUploaded - currentBytes)) != newBytesUploaded - currentBytes) {
                    this.mException = new IOException("Unexpected end of stream encountered.");
                    return false;
                } else if (this.mBytesUploaded.compareAndSet(currentBytes, newBytesUploaded)) {
                    return true;
                } else {
                    Log.e(TAG, "Somehow, the uploaded bytes changed during an uploaded.  This should nothappen");
                    this.mException = new IllegalStateException("uploaded bytes changed unexpectedly.");
                    return false;
                }
            } catch (IOException e) {
                Log.e(TAG, "Unable to recover position in Stream during resumable upload", e);
                this.mException = e;
                return false;
            }
        }
    }

    private void uploadChunk() {
        try {
            this.mStreamBuffer.fill(this.mCurrentChunkSize);
            int bytesToUpload = Math.min(this.mCurrentChunkSize, this.mStreamBuffer.available());
            NetworkRequest uploadRequest = new ResumableUploadByteRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp(), this.mUploadUri, this.mStreamBuffer.get(), this.mBytesUploaded.get(), bytesToUpload, this.mStreamBuffer.isFinished());
            if (!send(uploadRequest)) {
                this.mCurrentChunkSize = 262144;
                Log.d(TAG, "Resetting chunk size to " + this.mCurrentChunkSize);
                return;
            }
            this.mBytesUploaded.getAndAdd(bytesToUpload);
            if (!this.mStreamBuffer.isFinished()) {
                this.mStreamBuffer.advance(bytesToUpload);
                if (this.mCurrentChunkSize < MAXIMUM_CHUNK_SIZE) {
                    this.mCurrentChunkSize *= 2;
                    Log.d(TAG, "Increasing chunk size to " + this.mCurrentChunkSize);
                }
            } else {
                try {
                    this.mMetadata = new StorageMetadata.Builder(uploadRequest.getResultBody(), this.mStorageRef).build();
                    tryChangeState(4, false);
                    tryChangeState(128, false);
                } catch (JSONException e) {
                    Log.e(TAG, "Unable to parse resulting metadata from upload:" + uploadRequest.getRawResult(), e);
                    this.mException = e;
                }
            }
        } catch (IOException e2) {
            Log.e(TAG, "Unable to read bytes for uploading", e2);
            this.mException = e2;
        }
    }

    private boolean isValidHttpResponseCode(int code) {
        return code == 308 || (code >= 200 && code < 300);
    }

    private boolean send(NetworkRequest request) {
        request.performRequest(Util.getCurrentAuthToken(this.mAuthProvider), this.mStorageRef.getApp().getApplicationContext());
        return processResultValid(request);
    }

    private boolean sendWithRetry(NetworkRequest request) {
        this.mSender.sendWithExponentialBackoff(request);
        return processResultValid(request);
    }

    private boolean processResultValid(NetworkRequest request) {
        int resultCode = request.getResultCode();
        this.mResultCode = this.mSender.isRetryableError(resultCode) ? -2 : resultCode;
        this.mServerException = request.getException();
        this.mServerStatus = request.getResultString("X-Goog-Upload-Status");
        return isValidHttpResponseCode(this.mResultCode) && this.mServerException == null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.storage.StorageTask
    public void onCanceled() {
        this.mSender.cancel();
        final NetworkRequest cancelRequest = null;
        if (this.mUploadUri != null) {
            cancelRequest = new ResumableUploadCancelRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp(), this.mUploadUri);
        }
        if (cancelRequest != null) {
            StorageTaskScheduler.getInstance().scheduleCommand(new Runnable() { // from class: com.google.firebase.storage.UploadTask.1
                @Override // java.lang.Runnable
                public void run() {
                    cancelRequest.performRequest(Util.getCurrentAuthToken(UploadTask.this.mAuthProvider), UploadTask.this.mStorageRef.getApp().getApplicationContext());
                }
            });
        }
        this.mException = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
        super.onCanceled();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public TaskSnapshot snapStateImpl() {
        Exception error = this.mException != null ? this.mException : this.mServerException;
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(error, this.mResultCode), this.mBytesUploaded.get(), this.mUploadUri, this.mMetadata);
    }

    /* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
    /* loaded from: classes.dex */
    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long mBytesUploaded;
        private final StorageMetadata mMetadata;
        private final Uri mUploadUri;

        TaskSnapshot(Exception error, long bytesuploaded, Uri uploadUri, StorageMetadata metadata) {
            super(error);
            this.mBytesUploaded = bytesuploaded;
            this.mUploadUri = uploadUri;
            this.mMetadata = metadata;
        }

        public long getBytesTransferred() {
            return this.mBytesUploaded;
        }

        public long getTotalByteCount() {
            return UploadTask.this.getTotalByteCount();
        }

        public Uri getUploadSessionUri() {
            return this.mUploadUri;
        }

        public StorageMetadata getMetadata() {
            return this.mMetadata;
        }
    }
}
