package com.google.firebase.storage;

import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnCanceledListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public final /* synthetic */ class StorageTask$$Lambda$15 implements OnCanceledListener {
    private final CancellationTokenSource arg$1;

    private StorageTask$$Lambda$15(CancellationTokenSource cancellationTokenSource) {
        this.arg$1 = cancellationTokenSource;
    }

    public static OnCanceledListener lambdaFactory$(CancellationTokenSource cancellationTokenSource) {
        return new StorageTask$$Lambda$15(cancellationTokenSource);
    }

    @Override // com.google.android.gms.tasks.OnCanceledListener
    public void onCanceled() {
        this.arg$1.cancel();
    }
}
