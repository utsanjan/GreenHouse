package com.google.firebase.storage;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.TaskListenerImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public final /* synthetic */ class StorageTask$$Lambda$5 implements TaskListenerImpl.OnRaise {
    private final StorageTask arg$1;

    private StorageTask$$Lambda$5(StorageTask storageTask) {
        this.arg$1 = storageTask;
    }

    public static TaskListenerImpl.OnRaise lambdaFactory$(StorageTask storageTask) {
        return new StorageTask$$Lambda$5(storageTask);
    }

    @Override // com.google.firebase.storage.TaskListenerImpl.OnRaise
    public void raise(Object obj, Object obj2) {
        StorageTask.lambda$new$2(this.arg$1, (OnCompleteListener) obj, (StorageTask.ProvideError) obj2);
    }
}
