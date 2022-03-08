package com.google.firebase.storage.internal;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.storage.StorageTaskScheduler;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class SmartHandler {
    static boolean testMode = false;
    private final Executor executor;
    private final Handler handler;

    public SmartHandler(Executor executor) {
        this.executor = executor;
        if (executor != null) {
            this.handler = null;
        } else if (!testMode) {
            this.handler = new Handler(Looper.getMainLooper());
        } else {
            this.handler = null;
        }
    }

    public void callBack(Runnable runnable) {
        Preconditions.checkNotNull(runnable);
        Handler handler = this.handler;
        if (handler == null) {
            Executor executor = this.executor;
            if (executor != null) {
                executor.execute(runnable);
            } else {
                StorageTaskScheduler.getInstance().scheduleCallback(runnable);
            }
        } else {
            handler.post(runnable);
        }
    }
}
