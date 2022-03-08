package com.squareup.picasso;

import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Utils;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class PicassoExecutorService extends ThreadPoolExecutor {
    private static final int DEFAULT_THREAD_COUNT = 3;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PicassoExecutorService() {
        super(3, 3, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new Utils.PicassoThreadFactory());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void adjustThreadCount(NetworkInfo info) {
        if (info == null || !info.isConnectedOrConnecting()) {
            setThreadCount(3);
            return;
        }
        int type = info.getType();
        if (type == 0) {
            int subtype = info.getSubtype();
            switch (subtype) {
                case 1:
                case 2:
                    setThreadCount(1);
                    return;
                case 3:
                case 4:
                case 5:
                case 6:
                    break;
                default:
                    switch (subtype) {
                        case 12:
                            break;
                        case 13:
                        case 14:
                        case 15:
                            setThreadCount(3);
                            return;
                        default:
                            setThreadCount(3);
                            return;
                    }
            }
            setThreadCount(2);
        } else if (type == 1 || type == 6 || type == 9) {
            setThreadCount(4);
        } else {
            setThreadCount(3);
        }
    }

    private void setThreadCount(int threadCount) {
        setCorePoolSize(threadCount);
        setMaximumPoolSize(threadCount);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable task) {
        PicassoFutureTask ftask = new PicassoFutureTask((BitmapHunter) task);
        execute(ftask);
        return ftask;
    }

    /* loaded from: classes.dex */
    private static final class PicassoFutureTask extends FutureTask<BitmapHunter> implements Comparable<PicassoFutureTask> {
        private final BitmapHunter hunter;

        public PicassoFutureTask(BitmapHunter hunter) {
            super(hunter, null);
            this.hunter = hunter;
        }

        public int compareTo(PicassoFutureTask other) {
            int i;
            int i2;
            Picasso.Priority p1 = this.hunter.getPriority();
            Picasso.Priority p2 = other.hunter.getPriority();
            if (p1 == p2) {
                i2 = this.hunter.sequence;
                i = other.hunter.sequence;
            } else {
                i2 = p2.ordinal();
                i = p1.ordinal();
            }
            return i2 - i;
        }
    }
}
