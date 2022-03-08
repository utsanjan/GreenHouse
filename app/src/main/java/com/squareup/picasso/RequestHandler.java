package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public abstract class RequestHandler {
    public abstract boolean canHandleRequest(Request request);

    public abstract Result load(Request request, int i) throws IOException;

    /* loaded from: classes.dex */
    public static final class Result {
        private final Bitmap bitmap;
        private final int exifOrientation;
        private final Picasso.LoadedFrom loadedFrom;
        private final InputStream stream;

        public Result(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
            this((Bitmap) Utils.checkNotNull(bitmap, "bitmap == null"), null, loadedFrom, 0);
        }

        public Result(InputStream stream, Picasso.LoadedFrom loadedFrom) {
            this(null, (InputStream) Utils.checkNotNull(stream, "stream == null"), loadedFrom, 0);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Result(Bitmap bitmap, InputStream stream, Picasso.LoadedFrom loadedFrom, int exifOrientation) {
            boolean z = true;
            if ((stream == null ? false : z) ^ (bitmap != null)) {
                this.bitmap = bitmap;
                this.stream = stream;
                this.loadedFrom = (Picasso.LoadedFrom) Utils.checkNotNull(loadedFrom, "loadedFrom == null");
                this.exifOrientation = exifOrientation;
                return;
            }
            throw new AssertionError();
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public InputStream getStream() {
            return this.stream;
        }

        public Picasso.LoadedFrom getLoadedFrom() {
            return this.loadedFrom;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int getExifOrientation() {
            return this.exifOrientation;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getRetryCount() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean shouldRetry(boolean airplaneMode, NetworkInfo info) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean supportsReplay() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static BitmapFactory.Options createBitmapOptions(Request data) {
        boolean justBounds = data.hasSize();
        boolean hasConfig = data.config != null;
        BitmapFactory.Options options = null;
        if (justBounds || hasConfig) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = justBounds;
            if (hasConfig) {
                options.inPreferredConfig = data.config;
            }
        }
        return options;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean requiresInSampleSize(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void calculateInSampleSize(int reqWidth, int reqHeight, BitmapFactory.Options options, Request request) {
        calculateInSampleSize(reqWidth, reqHeight, options.outWidth, options.outHeight, options, request);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void calculateInSampleSize(int reqWidth, int reqHeight, int width, int height, BitmapFactory.Options options, Request request) {
        int sampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            if (reqHeight == 0) {
                sampleSize = (int) Math.floor(width / reqWidth);
            } else if (reqWidth == 0) {
                sampleSize = (int) Math.floor(height / reqHeight);
            } else {
                int heightRatio = (int) Math.floor(height / reqHeight);
                int widthRatio = (int) Math.floor(width / reqWidth);
                if (request.centerInside) {
                    sampleSize = Math.max(heightRatio, widthRatio);
                } else {
                    sampleSize = Math.min(heightRatio, widthRatio);
                }
            }
        }
        options.inSampleSize = sampleSize;
        options.inJustDecodeBounds = false;
    }
}
