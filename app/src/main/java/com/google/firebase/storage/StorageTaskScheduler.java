package com.google.firebase.storage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class StorageTaskScheduler {
    public static StorageTaskScheduler sInstance = new StorageTaskScheduler();
    private static BlockingQueue<Runnable> mCommandQueue = new LinkedBlockingQueue();
    private static final ThreadPoolExecutor COMMAND_POOL_EXECUTOR = new ThreadPoolExecutor(5, 5, 5, TimeUnit.SECONDS, mCommandQueue, new StorageThreadFactory("Command-"));
    private static BlockingQueue<Runnable> mUploadQueue = new LinkedBlockingQueue();
    private static final ThreadPoolExecutor UPLOAD_QUEUE_EXECUTOR = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, mUploadQueue, new StorageThreadFactory("Upload-"));
    private static BlockingQueue<Runnable> mDownloadQueue = new LinkedBlockingQueue();
    private static final ThreadPoolExecutor DOWNLOAD_QUEUE_EXECUTOR = new ThreadPoolExecutor(3, 3, 5, TimeUnit.SECONDS, mDownloadQueue, new StorageThreadFactory("Download-"));
    private static BlockingQueue<Runnable> mCallbackQueue = new LinkedBlockingQueue();
    private static final ThreadPoolExecutor CALLBACK_QUEUE_EXECUTOR = new ThreadPoolExecutor(1, 1, 5, TimeUnit.SECONDS, mCallbackQueue, new StorageThreadFactory("Callbacks-"));

    static {
        COMMAND_POOL_EXECUTOR.allowCoreThreadTimeOut(true);
        UPLOAD_QUEUE_EXECUTOR.allowCoreThreadTimeOut(true);
        DOWNLOAD_QUEUE_EXECUTOR.allowCoreThreadTimeOut(true);
        CALLBACK_QUEUE_EXECUTOR.allowCoreThreadTimeOut(true);
    }

    public static StorageTaskScheduler getInstance() {
        return sInstance;
    }

    public void scheduleCommand(Runnable task) {
        COMMAND_POOL_EXECUTOR.execute(task);
    }

    public void scheduleUpload(Runnable task) {
        UPLOAD_QUEUE_EXECUTOR.execute(task);
    }

    public void scheduleDownload(Runnable task) {
        DOWNLOAD_QUEUE_EXECUTOR.execute(task);
    }

    public void scheduleCallback(Runnable task) {
        CALLBACK_QUEUE_EXECUTOR.execute(task);
    }

    public Executor getCommandPoolExecutor() {
        return COMMAND_POOL_EXECUTOR;
    }

    /* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
    /* loaded from: classes.dex */
    static class StorageThreadFactory implements ThreadFactory {
        private final String mNameSuffix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        StorageThreadFactory(String suffix) {
            this.mNameSuffix = suffix;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "FirebaseStorage-" + this.mNameSuffix + this.threadNumber.getAndIncrement());
            t.setDaemon(false);
            t.setPriority(9);
            return t;
        }
    }
}
