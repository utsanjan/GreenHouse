package com.google.firebase.storage;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class FileDownloadTask extends StorageTask<TaskSnapshot> {
    static final int PREFERRED_CHUNK_SIZE = 262144;
    private static final String TAG = "FileDownloadTask";
    private long mBytesDownloaded;
    private final Uri mDestinationFile;
    private int mResultCode;
    private ExponentialBackoffSender mSender;
    private StorageReference mStorageRef;
    private long mTotalBytes = -1;
    private String mETagVerification = null;
    private volatile Exception mException = null;
    private long mResumeOffset = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileDownloadTask(StorageReference storageRef, Uri destinationFile) {
        this.mStorageRef = storageRef;
        this.mDestinationFile = destinationFile;
        FirebaseStorage storage = storageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxDownloadRetryTimeMillis());
    }

    long getDownloadedSizeInBytes() {
        return this.mBytesDownloaded;
    }

    long getTotalBytes() {
        return this.mTotalBytes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.firebase.storage.StorageTask
    public StorageReference getStorage() {
        return this.mStorageRef;
    }

    @Override // com.google.firebase.storage.StorageTask
    protected void schedule() {
        StorageTaskScheduler.getInstance().scheduleDownload(getRunnable());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.firebase.storage.StorageTask
    public TaskSnapshot snapStateImpl() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.mException, this.mResultCode), this.mBytesDownloaded + this.mResumeOffset);
    }

    private int fillBuffer(InputStream stream, byte[] data) {
        boolean readData = false;
        int offset = 0;
        while (offset != data.length) {
            try {
                int count = stream.read(data, offset, data.length - offset);
                if (count == -1) {
                    break;
                }
                readData = true;
                offset += count;
            } catch (IOException e) {
                this.mException = e;
            }
        }
        if (readData) {
            return offset;
        }
        return -1;
    }

    private boolean processResponse(NetworkRequest request) throws IOException {
        OutputStream output;
        boolean success = true;
        InputStream stream = request.getStream();
        if (stream != null) {
            File outputFile = new File(this.mDestinationFile.getPath());
            if (!outputFile.exists()) {
                if (this.mResumeOffset <= 0) {
                    boolean created = outputFile.createNewFile();
                    if (!created) {
                        Log.w(TAG, "unable to create file:" + outputFile.getAbsolutePath());
                    }
                } else {
                    Log.e(TAG, "The file downloading to has been deleted:" + outputFile.getAbsolutePath());
                    throw new IllegalStateException("expected a file to resume from.");
                }
            }
            if (this.mResumeOffset > 0) {
                Log.d(TAG, "Resuming download file " + outputFile.getAbsolutePath() + " at " + this.mResumeOffset);
                output = new FileOutputStream(outputFile, true);
            } else {
                output = new FileOutputStream(outputFile);
            }
            try {
                byte[] data = new byte[262144];
                while (success) {
                    int count = fillBuffer(stream, data);
                    if (count == -1) {
                        break;
                    }
                    output.write(data, 0, count);
                    this.mBytesDownloaded += count;
                    if (this.mException != null) {
                        Log.d(TAG, "Exception occurred during file download. Retrying.", this.mException);
                        this.mException = null;
                        success = false;
                    }
                    if (!tryChangeState(4, false)) {
                        success = false;
                    }
                }
                return success;
            } finally {
                output.flush();
                output.close();
                stream.close();
            }
        } else {
            this.mException = new IllegalStateException("Unable to open Firebase Storage stream.");
            return false;
        }
    }

    @Override // com.google.firebase.storage.StorageTask
    void run() {
        String str;
        if (this.mException != null) {
            tryChangeState(64, false);
        } else if (tryChangeState(4, false)) {
            do {
                this.mBytesDownloaded = 0L;
                this.mException = null;
                this.mSender.reset();
                NetworkRequest request = new GetNetworkRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp(), this.mResumeOffset);
                this.mSender.sendWithExponentialBackoff(request, false);
                this.mResultCode = request.getResultCode();
                this.mException = request.getException() != null ? request.getException() : this.mException;
                boolean success = true;
                boolean success2 = isValidHttpResponseCode(this.mResultCode) && this.mException == null && getInternalState() == 4;
                if (success2) {
                    this.mTotalBytes = request.getResultingContentLength();
                    String newEtag = request.getResultString("ETag");
                    if (TextUtils.isEmpty(newEtag) || (str = this.mETagVerification) == null || str.equals(newEtag)) {
                        this.mETagVerification = newEtag;
                        try {
                            success2 = processResponse(request);
                        } catch (IOException e) {
                            Log.e(TAG, "Exception occurred during file write.  Aborting.", e);
                            this.mException = e;
                        }
                    } else {
                        Log.w(TAG, "The file at the server has changed.  Restarting from the beginning.");
                        this.mResumeOffset = 0L;
                        this.mETagVerification = null;
                        request.performRequestEnd();
                        schedule();
                        return;
                    }
                }
                request.performRequestEnd();
                if (!(success2 && this.mException == null && getInternalState() == 4)) {
                    success = false;
                }
                if (success) {
                    tryChangeState(128, false);
                    return;
                }
                File outputFile = new File(this.mDestinationFile.getPath());
                if (outputFile.exists()) {
                    this.mResumeOffset = outputFile.length();
                } else {
                    this.mResumeOffset = 0L;
                }
                if (getInternalState() == 8) {
                    tryChangeState(16, false);
                    return;
                } else if (getInternalState() == 32) {
                    if (!tryChangeState(256, false)) {
                        Log.w(TAG, "Unable to change download task to final state from " + getInternalState());
                        return;
                    }
                    return;
                }
            } while (this.mBytesDownloaded > 0);
            tryChangeState(64, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.firebase.storage.StorageTask
    public void onCanceled() {
        this.mSender.cancel();
        this.mException = StorageException.fromErrorStatus(Status.RESULT_CANCELED);
    }

    private boolean isValidHttpResponseCode(int code) {
        return code == 308 || (code >= 200 && code < 300);
    }

    /* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
    /* loaded from: classes.dex */
    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long mBytesDownloaded;

        TaskSnapshot(Exception error, long bytesDownloaded) {
            super(error);
            this.mBytesDownloaded = bytesDownloaded;
        }

        public long getBytesTransferred() {
            return this.mBytesDownloaded;
        }

        public long getTotalByteCount() {
            return FileDownloadTask.this.getTotalBytes();
        }
    }
}
