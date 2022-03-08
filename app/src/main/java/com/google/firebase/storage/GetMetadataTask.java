package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.GetMetadataNetworkRequest;
import com.google.firebase.storage.network.NetworkRequest;
import org.json.JSONException;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
class GetMetadataTask implements Runnable {
    private static final String TAG = "GetMetadataTask";
    private TaskCompletionSource<StorageMetadata> mPendingResult;
    private StorageMetadata mResultMetadata;
    private ExponentialBackoffSender mSender;
    private StorageReference mStorageRef;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GetMetadataTask(StorageReference storageRef, TaskCompletionSource<StorageMetadata> pendingResult) {
        Preconditions.checkNotNull(storageRef);
        Preconditions.checkNotNull(pendingResult);
        this.mStorageRef = storageRef;
        this.mPendingResult = pendingResult;
        if (!storageRef.getRoot().getName().equals(storageRef.getName())) {
            FirebaseStorage storage = this.mStorageRef.getStorage();
            this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxDownloadRetryTimeMillis());
            return;
        }
        throw new IllegalArgumentException("getMetadata() is not supported at the root of the bucket.");
    }

    @Override // java.lang.Runnable
    public void run() {
        NetworkRequest request = new GetMetadataNetworkRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp());
        this.mSender.sendWithExponentialBackoff(request);
        if (request.isResultSuccess()) {
            try {
                this.mResultMetadata = new StorageMetadata.Builder(request.getResultBody(), this.mStorageRef).build();
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse resulting metadata. " + request.getRawResult(), e);
                this.mPendingResult.setException(StorageException.fromException(e));
                return;
            }
        }
        TaskCompletionSource<StorageMetadata> taskCompletionSource = this.mPendingResult;
        if (taskCompletionSource != null) {
            request.completeTask(taskCompletionSource, this.mResultMetadata);
        }
    }
}
