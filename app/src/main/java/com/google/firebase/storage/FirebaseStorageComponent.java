package com.google.firebase.storage;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.inject.Provider;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class FirebaseStorageComponent {
    private final FirebaseApp app;
    private final Provider<InternalAuthProvider> authProvider;
    private final Map<String, FirebaseStorage> instances = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseStorageComponent(FirebaseApp app, Provider<InternalAuthProvider> authProvider) {
        this.app = app;
        this.authProvider = authProvider;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized FirebaseStorage get(String bucketName) {
        FirebaseStorage storage;
        storage = this.instances.get(bucketName);
        if (storage == null) {
            storage = new FirebaseStorage(bucketName, this.app, this.authProvider);
            this.instances.put(bucketName, storage);
        }
        return storage;
    }

    synchronized void clearInstancesForTesting() {
        this.instances.clear();
    }
}
