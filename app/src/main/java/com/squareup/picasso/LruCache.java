package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class LruCache implements Cache {
    private int evictionCount;
    private int hitCount;
    final LinkedHashMap<String, Bitmap> map;
    private final int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(Context context) {
        this(Utils.calculateMemoryCacheSize(context));
    }

    public LruCache(int maxSize) {
        if (maxSize > 0) {
            this.maxSize = maxSize;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("Max size must be positive.");
    }

    @Override // com.squareup.picasso.Cache
    public Bitmap get(String key) {
        if (key != null) {
            synchronized (this) {
                try {
                    try {
                        Bitmap mapValue = this.map.get(key);
                        if (mapValue != null) {
                            this.hitCount++;
                            return mapValue;
                        }
                        this.missCount++;
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    throw th;
                }
            }
        } else {
            throw new NullPointerException("key == null");
        }
    }

    @Override // com.squareup.picasso.Cache
    public void set(String key, Bitmap bitmap) {
        if (key == null || bitmap == null) {
            throw new NullPointerException("key == null || bitmap == null");
        }
        synchronized (this) {
            try {
                try {
                    this.putCount++;
                    this.size += Utils.getBitmapBytes(bitmap);
                    Bitmap previous = this.map.put(key, bitmap);
                    if (previous != null) {
                        this.size -= Utils.getBitmapBytes(previous);
                    }
                    trimToSize(this.maxSize);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0078, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void trimToSize(int r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = r0
        L_0x0002:
            monitor-enter(r5)
            int r2 = r5.size     // Catch: all -> 0x0079
            if (r2 < 0) goto L_0x005a
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r5.map     // Catch: all -> 0x0079
            boolean r2 = r2.isEmpty()     // Catch: all -> 0x0079
            if (r2 == 0) goto L_0x0013
            int r2 = r5.size     // Catch: all -> 0x0079
            if (r2 != 0) goto L_0x005a
        L_0x0013:
            int r2 = r5.size     // Catch: all -> 0x0079
            if (r2 <= r6) goto L_0x0058
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r5.map     // Catch: all -> 0x0079
            boolean r2 = r2.isEmpty()     // Catch: all -> 0x0079
            if (r2 == 0) goto L_0x0020
            goto L_0x0058
        L_0x0020:
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r5.map     // Catch: all -> 0x0079
            java.util.Set r2 = r2.entrySet()     // Catch: all -> 0x0079
            java.util.Iterator r2 = r2.iterator()     // Catch: all -> 0x0079
            java.lang.Object r2 = r2.next()     // Catch: all -> 0x0079
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch: all -> 0x0079
            java.lang.Object r3 = r2.getKey()     // Catch: all -> 0x0079
            java.lang.String r3 = (java.lang.String) r3     // Catch: all -> 0x0079
            r0 = r3
            java.lang.Object r3 = r2.getValue()     // Catch: all -> 0x0056
            android.graphics.Bitmap r3 = (android.graphics.Bitmap) r3     // Catch: all -> 0x0056
            r1 = r3
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r3 = r5.map     // Catch: all -> 0x0054
            r3.remove(r0)     // Catch: all -> 0x0054
            int r3 = r5.size     // Catch: all -> 0x0054
            int r4 = com.squareup.picasso.Utils.getBitmapBytes(r1)     // Catch: all -> 0x0054
            int r3 = r3 - r4
            r5.size = r3     // Catch: all -> 0x0054
            int r3 = r5.evictionCount     // Catch: all -> 0x0054
            int r3 = r3 + 1
            r5.evictionCount = r3     // Catch: all -> 0x0054
            monitor-exit(r5)     // Catch: all -> 0x0054
            goto L_0x0002
        L_0x0054:
            r2 = move-exception
            goto L_0x007a
        L_0x0056:
            r2 = move-exception
            goto L_0x007a
        L_0x0058:
            monitor-exit(r5)     // Catch: all -> 0x0079
            return
        L_0x005a:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch: all -> 0x0079
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: all -> 0x0079
            r3.<init>()     // Catch: all -> 0x0079
            java.lang.Class r4 = r5.getClass()     // Catch: all -> 0x0079
            java.lang.String r4 = r4.getName()     // Catch: all -> 0x0079
            r3.append(r4)     // Catch: all -> 0x0079
            java.lang.String r4 = ".sizeOf() is reporting inconsistent results!"
            r3.append(r4)     // Catch: all -> 0x0079
            java.lang.String r3 = r3.toString()     // Catch: all -> 0x0079
            r2.<init>(r3)     // Catch: all -> 0x0079
            throw r2     // Catch: all -> 0x0079
        L_0x0079:
            r2 = move-exception
        L_0x007a:
            monitor-exit(r5)     // Catch: all -> 0x007c
            throw r2
        L_0x007c:
            r2 = move-exception
            goto L_0x007a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.picasso.LruCache.trimToSize(int):void");
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    @Override // com.squareup.picasso.Cache
    public final synchronized int size() {
        return this.size;
    }

    @Override // com.squareup.picasso.Cache
    public final synchronized int maxSize() {
        return this.maxSize;
    }

    @Override // com.squareup.picasso.Cache
    public final synchronized void clear() {
        evictAll();
    }

    @Override // com.squareup.picasso.Cache
    public final synchronized void clearKeyUri(String uri) {
        boolean sizeChanged = false;
        int uriLength = uri.length();
        Iterator<Map.Entry<String, Bitmap>> i = this.map.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, Bitmap> entry = i.next();
            String key = entry.getKey();
            Bitmap value = entry.getValue();
            int newlineIndex = key.indexOf(10);
            if (newlineIndex == uriLength && key.substring(0, newlineIndex).equals(uri)) {
                i.remove();
                this.size -= Utils.getBitmapBytes(value);
                sizeChanged = true;
            }
        }
        if (sizeChanged) {
            trimToSize(this.maxSize);
        }
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }
}
