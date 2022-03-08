package com.google.firebase.crashlytics.internal.common;

import android.os.Looper;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class Utils {
    private static final FilenameFilter ALL_FILES_FILTER = new FilenameFilter() { // from class: com.google.firebase.crashlytics.internal.common.Utils.1
        @Override // java.io.FilenameFilter
        public boolean accept(File dir, String filename) {
            return true;
        }
    };
    private static final ExecutorService TASK_CONTINUATION_EXECUTOR_SERVICE = ExecutorUtils.buildSingleThreadExecutorService("awaitEvenIfOnMainThread task continuation executor");

    private Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int capSessionCount(File nativeDirectory, File fatalDirectory, int maxAllowed, Comparator<File> sortComparator) {
        List<File> allFiles = new ArrayList<>();
        File[] nativeFiles = nativeDirectory.listFiles();
        File[] fatalFiles = fatalDirectory.listFiles(ALL_FILES_FILTER);
        File[] nativeFiles2 = nativeFiles != null ? nativeFiles : new File[0];
        File[] fatalFiles2 = fatalFiles != null ? fatalFiles : new File[0];
        allFiles.addAll(Arrays.asList(nativeFiles2));
        allFiles.addAll(Arrays.asList(fatalFiles2));
        return capFileCount(allFiles, maxAllowed, sortComparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int capFileCount(File directory, int maxAllowed, Comparator<File> sortComparator) {
        return capFileCount(directory, ALL_FILES_FILTER, maxAllowed, sortComparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int capFileCount(File directory, FilenameFilter filter, int maxAllowed, Comparator<File> sortComparator) {
        File[] sessionFiles = directory.listFiles(filter);
        if (sessionFiles == null) {
            return 0;
        }
        return capFileCount(Arrays.asList(sessionFiles), maxAllowed, sortComparator);
    }

    static int capFileCount(List<File> files, int maxAllowed, Comparator<File> sortComparator) {
        int numRetained = files.size();
        Collections.sort(files, sortComparator);
        for (File file : files) {
            if (numRetained <= maxAllowed) {
                return numRetained;
            }
            recursiveDelete(file);
            numRetained--;
        }
        return numRetained;
    }

    public static <T> Task<T> race(Task<T> t1, Task<T> t2) {
        final TaskCompletionSource<T> result = new TaskCompletionSource<>();
        Continuation continuation = (Continuation<T, Void>) new Continuation<T, Void>() { // from class: com.google.firebase.crashlytics.internal.common.Utils.2
            @Override // com.google.android.gms.tasks.Continuation
            public Void then(Task<T> task) throws Exception {
                if (task.isSuccessful()) {
                    TaskCompletionSource.this.trySetResult(task.getResult());
                    return null;
                }
                TaskCompletionSource.this.trySetException(task.getException());
                return null;
            }
        };
        t1.continueWith(continuation);
        t2.continueWith(continuation);
        return result.getTask();
    }

    public static <T> Task<T> callTask(Executor executor, final Callable<Task<T>> callable) {
        final TaskCompletionSource<T> tcs = new TaskCompletionSource<>();
        executor.execute(new Runnable() { // from class: com.google.firebase.crashlytics.internal.common.Utils.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ((Task) callable.call()).continueWith(new Continuation<T, Void>() { // from class: com.google.firebase.crashlytics.internal.common.Utils.3.1
                        @Override // com.google.android.gms.tasks.Continuation
                        public Void then(Task<T> task) throws Exception {
                            if (task.isSuccessful()) {
                                tcs.setResult(task.getResult());
                                return null;
                            }
                            tcs.setException(task.getException());
                            return null;
                        }
                    });
                } catch (Exception e) {
                    tcs.setException(e);
                }
            }
        });
        return tcs.getTask();
    }

    public static <T> T awaitEvenIfOnMainThread(Task<T> task) throws InterruptedException, TimeoutException {
        final CountDownLatch latch = new CountDownLatch(1);
        task.continueWith(TASK_CONTINUATION_EXECUTOR_SERVICE, (Continuation<T, Object>) new Continuation<T, Object>() { // from class: com.google.firebase.crashlytics.internal.common.Utils.4
            @Override // com.google.android.gms.tasks.Continuation
            public Object then(Task<T> task2) throws Exception {
                latch.countDown();
                return null;
            }
        });
        if (Looper.getMainLooper() == Looper.myLooper()) {
            latch.await(4L, TimeUnit.SECONDS);
        } else {
            latch.await();
        }
        if (task.isComplete()) {
            return task.getResult();
        }
        throw new TimeoutException();
    }

    private static void recursiveDelete(File f) {
        File[] listFiles;
        if (f.isDirectory()) {
            for (File s : f.listFiles()) {
                recursiveDelete(s);
            }
        }
        f.delete();
    }
}
