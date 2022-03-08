package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class ResumableUploadByteRequest extends ResumableNetworkRequest {
    private final int bytesToWrite;
    private final byte[] chunk;
    private final boolean isFinal;
    private final long offset;
    private final Uri uploadURL;

    public ResumableUploadByteRequest(Uri gsUri, FirebaseApp app, Uri uploadURL, byte[] chunk, long offset, int bytesToWrite, boolean isFinal) {
        super(gsUri, app);
        if (chunk == null && bytesToWrite != -1) {
            this.mException = new IllegalArgumentException("contentType is null or empty");
        }
        if (offset < 0) {
            this.mException = new IllegalArgumentException("offset cannot be negative");
        }
        this.bytesToWrite = bytesToWrite;
        this.uploadURL = uploadURL;
        this.chunk = bytesToWrite <= 0 ? null : chunk;
        this.offset = offset;
        this.isFinal = isFinal;
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        if (this.isFinal && this.bytesToWrite > 0) {
            super.setCustomHeader("X-Goog-Upload-Command", "upload, finalize");
        } else if (this.isFinal) {
            super.setCustomHeader("X-Goog-Upload-Command", "finalize");
        } else {
            super.setCustomHeader("X-Goog-Upload-Command", "upload");
        }
        super.setCustomHeader("X-Goog-Upload-Offset", Long.toString(this.offset));
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "POST";
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Uri getURL() {
        return this.uploadURL;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected byte[] getOutputRaw() {
        return this.chunk;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected int getOutputRawSize() {
        int i = this.bytesToWrite;
        if (i > 0) {
            return i;
        }
        return 0;
    }
}
