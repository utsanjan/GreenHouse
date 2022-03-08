package com.google.firebase.installations;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
final class AwaitListener implements OnCompleteListener<Void> {
    private final CountDownLatch latch = new CountDownLatch(1);

    AwaitListener() {
    }

    public void onSuccess() {
        this.latch.countDown();
    }

    public boolean await(long timeout, TimeUnit unit) throws InterruptedException {
        return this.latch.await(timeout, unit);
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public void onComplete(Task<Void> task) {
        this.latch.countDown();
    }
}
