package com.squareup.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public interface Downloader {
    Response load(Uri uri, int i) throws IOException;

    void shutdown();

    /* loaded from: classes.dex */
    public static class ResponseException extends IOException {
        final boolean localCacheOnly;
        final int responseCode;

        public ResponseException(String message, int networkPolicy, int responseCode) {
            super(message);
            this.localCacheOnly = NetworkPolicy.isOfflineOnly(networkPolicy);
            this.responseCode = responseCode;
        }
    }

    /* loaded from: classes.dex */
    public static class Response {
        final Bitmap bitmap;
        final boolean cached;
        final long contentLength;
        final InputStream stream;

        @Deprecated
        public Response(Bitmap bitmap, boolean loadedFromCache) {
            if (bitmap != null) {
                this.stream = null;
                this.bitmap = bitmap;
                this.cached = loadedFromCache;
                this.contentLength = -1L;
                return;
            }
            throw new IllegalArgumentException("Bitmap may not be null.");
        }

        @Deprecated
        public Response(InputStream stream, boolean loadedFromCache) {
            this(stream, loadedFromCache, -1L);
        }

        @Deprecated
        public Response(Bitmap bitmap, boolean loadedFromCache, long contentLength) {
            this(bitmap, loadedFromCache);
        }

        public Response(InputStream stream, boolean loadedFromCache, long contentLength) {
            if (stream != null) {
                this.stream = stream;
                this.bitmap = null;
                this.cached = loadedFromCache;
                this.contentLength = contentLength;
                return;
            }
            throw new IllegalArgumentException("Stream may not be null.");
        }

        public InputStream getInputStream() {
            return this.stream;
        }

        @Deprecated
        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public long getContentLength() {
            return this.contentLength;
        }
    }
}
