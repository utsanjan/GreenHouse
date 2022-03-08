package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Build;
import com.squareup.picasso.Downloader;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public class UrlConnectionDownloader implements Downloader {
    private static final String FORCE_CACHE = "only-if-cached,max-age=2147483647";
    static final String RESPONSE_SOURCE = "X-Android-Response-Source";
    static volatile Object cache;
    private final Context context;
    private static final Object lock = new Object();
    private static final ThreadLocal<StringBuilder> CACHE_HEADER_BUILDER = new ThreadLocal<StringBuilder>() { // from class: com.squareup.picasso.UrlConnectionDownloader.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public StringBuilder initialValue() {
            return new StringBuilder();
        }
    };

    public UrlConnectionDownloader(Context context) {
        this.context = context.getApplicationContext();
    }

    protected HttpURLConnection openConnection(Uri path) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(path.toString()).openConnection();
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(20000);
        return connection;
    }

    @Override // com.squareup.picasso.Downloader
    public Downloader.Response load(Uri uri, int networkPolicy) throws IOException {
        String headerValue;
        if (Build.VERSION.SDK_INT >= 14) {
            installCacheIfNeeded(this.context);
        }
        HttpURLConnection connection = openConnection(uri);
        connection.setUseCaches(true);
        if (networkPolicy != 0) {
            if (NetworkPolicy.isOfflineOnly(networkPolicy)) {
                headerValue = FORCE_CACHE;
            } else {
                StringBuilder builder = CACHE_HEADER_BUILDER.get();
                builder.setLength(0);
                if (!NetworkPolicy.shouldReadFromDiskCache(networkPolicy)) {
                    builder.append("no-cache");
                }
                if (!NetworkPolicy.shouldWriteToDiskCache(networkPolicy)) {
                    if (builder.length() > 0) {
                        builder.append(',');
                    }
                    builder.append("no-store");
                }
                headerValue = builder.toString();
            }
            connection.setRequestProperty("Cache-Control", headerValue);
        }
        int responseCode = connection.getResponseCode();
        if (responseCode < 300) {
            long contentLength = connection.getHeaderFieldInt("Content-Length", -1);
            boolean fromCache = Utils.parseResponseSourceHeader(connection.getHeaderField(RESPONSE_SOURCE));
            return new Downloader.Response(connection.getInputStream(), fromCache, contentLength);
        }
        connection.disconnect();
        throw new Downloader.ResponseException(responseCode + " " + connection.getResponseMessage(), networkPolicy, responseCode);
    }

    @Override // com.squareup.picasso.Downloader
    public void shutdown() {
        if (Build.VERSION.SDK_INT >= 14 && cache != null) {
            ResponseCacheIcs.close(cache);
        }
    }

    private static void installCacheIfNeeded(Context context) {
        if (cache == null) {
            try {
                synchronized (lock) {
                    if (cache == null) {
                        cache = ResponseCacheIcs.install(context);
                    }
                }
            } catch (IOException e) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ResponseCacheIcs {
        private ResponseCacheIcs() {
        }

        static Object install(Context context) throws IOException {
            File cacheDir = Utils.createDefaultCacheDir(context);
            HttpResponseCache cache = HttpResponseCache.getInstalled();
            if (cache != null) {
                return cache;
            }
            long maxSize = Utils.calculateDiskCacheSize(cacheDir);
            return HttpResponseCache.install(cacheDir, maxSize);
        }

        static void close(Object cache) {
            try {
                ((HttpResponseCache) cache).close();
            } catch (IOException e) {
            }
        }
    }
}
