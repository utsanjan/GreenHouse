package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.internal.ExponentialBackoffSender;
import com.google.firebase.storage.network.NetworkRequest;
import com.google.firebase.storage.network.UpdateMetadataNetworkRequest;
import org.json.JSONException;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
class UpdateMetadataTask implements Runnable {
    private static final String TAG = "UpdateMetadataTask";
    private final StorageMetadata mNewMetadata;
    private final TaskCompletionSource<StorageMetadata> mPendingResult;
    private StorageMetadata mResultMetadata = null;
    private ExponentialBackoffSender mSender;
    private final StorageReference mStorageRef;

    public UpdateMetadataTask(StorageReference storageRef, TaskCompletionSource<StorageMetadata> pendingResult, StorageMetadata newMetadata) {
        this.mStorageRef = storageRef;
        this.mPendingResult = pendingResult;
        this.mNewMetadata = newMetadata;
        FirebaseStorage storage = storageRef.getStorage();
        this.mSender = new ExponentialBackoffSender(storage.getApp().getApplicationContext(), storage.getAuthProvider(), storage.getMaxOperationRetryTimeMillis());
    }

    @Override // java.lang.Runnable
    public void run() {
        NetworkRequest request = new UpdateMetadataNetworkRequest(this.mStorageRef.getStorageUri(), this.mStorageRef.getApp(), this.mNewMetadata.createJSONObject());
        this.mSender.sendWithExponentialBackoff(request);
        if (request.isResultSuccess()) {
            try {
                this.mResultMetadata = new StorageMetadata.Builder(request.getResultBody(), this.mStorageRef).build();
            } catch (JSONException e) {
                Log.e(TAG, "Unable to parse a valid JSON object from resulting metadata:" + request.getRawResult(), e);
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
