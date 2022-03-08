package com.google.firebase.storage.network;

import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class ResumableUploadStartRequest extends ResumableNetworkRequest {
    private final String contentType;
    private final JSONObject metadata;

    public ResumableUploadStartRequest(Uri gsUri, FirebaseApp app, JSONObject metadata, String contentType) {
        super(gsUri, app);
        this.metadata = metadata;
        this.contentType = contentType;
        if (TextUtils.isEmpty(contentType)) {
            this.mException = new IllegalArgumentException("mContentType is null or empty");
        }
        super.setCustomHeader("X-Goog-Upload-Protocol", "resumable");
        super.setCustomHeader("X-Goog-Upload-Command", "start");
        super.setCustomHeader("X-Goog-Upload-Header-Content-Type", this.contentType);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Uri getURL() {
        Uri.Builder uriBuilder = sNetworkRequestUrl.buildUpon();
        uriBuilder.appendPath("b");
        uriBuilder.appendPath(this.mGsUri.getAuthority());
        uriBuilder.appendPath("o");
        return uriBuilder.build();
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "POST";
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected Map<String, String> getQueryParameters() {
        Map<String, String> headers = new HashMap<>();
        headers.put("name", getPathWithoutBucket());
        headers.put("uploadType", "resumable");
        return headers;
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected JSONObject getOutputJSON() {
        return this.metadata;
    }
}
