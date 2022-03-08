package com.google.firebase.storage.network;

import android.net.Uri;
import com.google.firebase.FirebaseApp;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class DeleteNetworkRequest extends NetworkRequest {
    public DeleteNetworkRequest(Uri gsUri, FirebaseApp app) {
        super(gsUri, app);
    }

    @Override // com.google.firebase.storage.network.NetworkRequest
    protected String getAction() {
        return "DELETE";
    }
}
