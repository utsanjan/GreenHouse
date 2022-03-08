package com.squareup.picasso;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Stats {
    private static final int BITMAP_DECODE_FINISHED = 2;
    private static final int BITMAP_TRANSFORMED_FINISHED = 3;
    private static final int CACHE_HIT = 0;
    private static final int CACHE_MISS = 1;
    private static final int DOWNLOAD_FINISHED = 4;
    private static final String STATS_THREAD_NAME = "Picasso-Stats";
    long averageDownloadSize;
    long averageOriginalBitmapSize;
    long averageTransformedBitmapSize;
    final Cache cache;
    long cacheHits;
    long cacheMisses;
    int downloadCount;
    final Handler handler;
    int originalBitmapCount;
    final HandlerThread statsThread;
    long totalDownloadSize;
    long totalOriginalBitmapSize;
    long totalTransformedBitmapSize;
    int transformedBitmapCount;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Stats(Cache cache) {
        this.cache = cache;
        HandlerThread handlerThread = new HandlerThread(STATS_THREAD_NAME, 10);
        this.statsThread = handlerThread;
        handlerThread.start();
        Utils.flushStackLocalLeaks(this.statsThread.getLooper());
        this.handler = new StatsHandler(this.statsThread.getLooper(), this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchBitmapDecoded(Bitmap bitmap) {
        processBitmap(bitmap, 2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchBitmapTransformed(Bitmap bitmap) {
        processBitmap(bitmap, 3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchDownloadFinished(long size) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, Long.valueOf(size)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchCacheHit() {
        this.handler.sendEmptyMessage(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchCacheMiss() {
        this.handler.sendEmptyMessage(1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void shutdown() {
        this.statsThread.quit();
    }

    void performCacheHit() {
        this.cacheHits++;
    }

    void performCacheMiss() {
        this.cacheMisses++;
    }

    void performDownloadFinished(Long size) {
        this.downloadCount++;
        long longValue = this.totalDownloadSize + size.longValue();
        this.totalDownloadSize = longValue;
        this.averageDownloadSize = getAverage(this.downloadCount, longValue);
    }

    void performBitmapDecoded(long size) {
        int i = this.originalBitmapCount + 1;
        this.originalBitmapCount = i;
        long j = this.totalOriginalBitmapSize + size;
        this.totalOriginalBitmapSize = j;
        this.averageOriginalBitmapSize = getAverage(i, j);
    }

    void performBitmapTransformed(long size) {
        this.transformedBitmapCount++;
        long j = this.totalTransformedBitmapSize + size;
        this.totalTransformedBitmapSize = j;
        this.averageTransformedBitmapSize = getAverage(this.originalBitmapCount, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StatsSnapshot createSnapshot() {
        return new StatsSnapshot(this.cache.maxSize(), this.cache.size(), this.cacheHits, this.cacheMisses, this.totalDownloadSize, this.totalOriginalBitmapSize, this.totalTransformedBitmapSize, this.averageDownloadSize, this.averageOriginalBitmapSize, this.averageTransformedBitmapSize, this.downloadCount, this.originalBitmapCount, this.transformedBitmapCount, System.currentTimeMillis());
    }

    private void processBitmap(Bitmap bitmap, int what) {
        int bitmapSize = Utils.getBitmapBytes(bitmap);
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(what, bitmapSize, 0));
    }

    private static long getAverage(int count, long totalSize) {
        return totalSize / count;
    }

    /* loaded from: classes.dex */
    private static class StatsHandler extends Handler {
        private final Stats stats;

        public StatsHandler(Looper looper, Stats stats) {
            super(looper);
            this.stats = stats;
        }

        @Override // android.os.Handler
        public void handleMessage(final Message msg) {
            int i = msg.what;
            if (i == 0) {
                this.stats.performCacheHit();
            } else if (i == 1) {
                this.stats.performCacheMiss();
            } else if (i == 2) {
                this.stats.performBitmapDecoded(msg.arg1);
            } else if (i == 3) {
                this.stats.performBitmapTransformed(msg.arg1);
            } else if (i != 4) {
                Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.Stats.StatsHandler.1
                    @Override // java.lang.Runnable
                    public void run() {
                        throw new AssertionError("Unhandled stats message." + msg.what);
                    }
                });
            } else {
                this.stats.performDownloadFinished((Long) msg.obj);
            }
        }
    }
}
