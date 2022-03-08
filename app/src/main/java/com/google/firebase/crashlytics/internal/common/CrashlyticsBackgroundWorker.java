package com.google.firebase.crashlytics.internal.common;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class CrashlyticsBackgroundWorker {
    private final ExecutorService executorService;
    private Task<Void> tail = Tasks.forResult(null);
    private final Object tailLock = new Object();
    private ThreadLocal<Boolean> isExecutorThread = new ThreadLocal<>();

    public CrashlyticsBackgroundWorker(ExecutorService executorService) {
        this.executorService = executorService;
        executorService.submit(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.1
            @Override // java.lang.Runnable
            public void run() {
                CrashlyticsBackgroundWorker.this.isExecutorThread.set(true);
            }
        });
    }

    public Executor getExecutor() {
        return this.executorService;
    }

    private boolean isRunningOnThread() {
        return Boolean.TRUE.equals(this.isExecutorThread.get());
    }

    public void checkRunningOnThread() {
        if (!isRunningOnThread()) {
            throw new IllegalStateException("Not running on background worker thread as intended.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Task<Void> submit(final Runnable runnable) {
        return submit(new Callable<Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.2
            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                runnable.run();
                return null;
            }
        });
    }

    private <T> Continuation<Void, T> newContinuation(final Callable<T> callable) {
        return new Continuation<Void, T>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.3
            /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
            @Override // com.google.android.gms.tasks.Continuation
            public T then(Task<Void> task) throws Exception {
                return callable.call();
            }
        };
    }

    private <T> Task<Void> ignoreResult(Task<T> task) {
        return task.continueWith(this.executorService, (Continuation<T, Void>) new Continuation<T, Void>() { // from class: com.google.firebase.crashlytics.internal.common.CrashlyticsBackgroundWorker.4
            @Override // com.google.android.gms.tasks.Continuation
            public Void then(Task<T> task2) throws Exception {
                return null;
            }
        });
    }

    public <T> Task<T> submit(Callable<T> callable) {
        Task<T> toReturn;
        synchronized (this.tailLock) {
            toReturn = this.tail.continueWith(this.executorService, newContinuation(callable));
            this.tail = ignoreResult(toReturn);
        }
        return toReturn;
    }

    public <T> Task<T> submitTask(Callable<Task<T>> callable) {
        Task<T> toReturn;
        synchronized (this.tailLock) {
            toReturn = this.tail.continueWithTask(this.executorService, newContinuation(callable));
            this.tail = ignoreResult(toReturn);
        }
        return toReturn;
    }
}
