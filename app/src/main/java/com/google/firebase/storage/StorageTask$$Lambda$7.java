package com.google.firebase.storage;

import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.TaskListenerImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public final /* synthetic */ class StorageTask$$Lambda$7 implements TaskListenerImpl.OnRaise {
    private static final StorageTask$$Lambda$7 instance = new StorageTask$$Lambda$7();

    private StorageTask$$Lambda$7() {
    }

    public static TaskListenerImpl.OnRaise lambdaFactory$() {
        return instance;
    }

    @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
    public void raise(Object obj, Object obj2) {
        ((OnProgressListener) obj).onProgress((StorageTask.ProvideError) obj2);
    }
}
