package com.google.firebase.storage;

import android.app.Activity;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public abstract class CancellableTask<StateT> extends Task<StateT> {
    public abstract CancellableTask<StateT> addOnProgressListener(Activity activity, OnProgressListener<? super StateT> onProgressListener);

    public abstract CancellableTask<StateT> addOnProgressListener(OnProgressListener<? super StateT> onProgressListener);

    public abstract CancellableTask<StateT> addOnProgressListener(Executor executor, OnProgressListener<? super StateT> onProgressListener);

    public abstract boolean cancel();

    @Override // com.google.android.gms.tasks.Task
    public abstract boolean isCanceled();

    public abstract boolean isInProgress();
}
