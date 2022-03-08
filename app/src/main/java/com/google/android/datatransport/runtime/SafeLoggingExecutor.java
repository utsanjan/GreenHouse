package com.google.android.datatransport.runtime;

import com.google.android.datatransport.runtime.logging.Logging;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
class SafeLoggingExecutor implements Executor {
    private final Executor delegate;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafeLoggingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable command) {
        this.delegate.execute(new SafeLoggingRunnable(command));
    }

    /* loaded from: classes.dex */
    static class SafeLoggingRunnable implements Runnable {
        private final Runnable delegate;

        SafeLoggingRunnable(Runnable delegate) {
            this.delegate = delegate;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.delegate.run();
            } catch (Exception e) {
                Logging.e("Executor", "Background execution failure.", e);
            }
        }
    }
}
