package com.google.firebase.installations;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
class CrossProcessLock {
    private static final String TAG = "CrossProcessLock";
    private final FileChannel channel;
    private final FileLock lock;

    private CrossProcessLock(FileChannel channel, FileLock lock) {
        this.channel = channel;
        this.lock = lock;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CrossProcessLock acquire(Context appContext, String lockName) {
        FileChannel channel = null;
        FileLock lock = null;
        try {
            File file = new File(appContext.getFilesDir(), lockName);
            channel = new RandomAccessFile(file, "rw").getChannel();
            lock = channel.lock();
            return new CrossProcessLock(channel, lock);
        } catch (IOException e) {
            Log.e(TAG, "encountered error while creating and acquiring the lock, ignoring", e);
            if (lock != null) {
                try {
                    lock.release();
                } catch (IOException e2) {
                }
            }
            if (channel == null) {
                return null;
            }
            try {
                channel.close();
                return null;
            } catch (IOException e3) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void releaseAndClose() {
        try {
            this.lock.release();
            this.channel.close();
        } catch (IOException e) {
            Log.e(TAG, "encountered error while releasing, ignoring", e);
        }
    }
}
