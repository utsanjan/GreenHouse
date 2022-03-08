package androidx.collection;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int maxSize) {
        if (maxSize > 0) {
            this.maxSize = maxSize;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public void resize(int maxSize) {
        if (maxSize > 0) {
            synchronized (this) {
                this.maxSize = maxSize;
            }
            trimToSize(maxSize);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final V get(K key) {
        V mapValue;
        if (key != null) {
            synchronized (this) {
                V mapValue2 = this.map.get(key);
                if (mapValue2 != null) {
                    this.hitCount++;
                    return mapValue2;
                }
                this.missCount++;
                V createdValue = create(key);
                if (createdValue == null) {
                    return null;
                }
                synchronized (this) {
                    this.createCount++;
                    mapValue = this.map.put(key, createdValue);
                    if (mapValue != null) {
                        this.map.put(key, mapValue);
                    } else {
                        this.size += safeSizeOf(key, createdValue);
                    }
                }
                if (mapValue != null) {
                    entryRemoved(false, key, createdValue, mapValue);
                    return mapValue;
                }
                trimToSize(this.maxSize);
                return createdValue;
            }
        }
        throw new NullPointerException("key == null");
    }

    public final V put(K key, V value) {
        V previous;
        if (key == null || value == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(key, value);
            previous = this.map.put(key, value);
            if (previous != null) {
                this.size -= safeSizeOf(key, previous);
            }
        }
        if (previous != null) {
            entryRemoved(false, key, previous, value);
        }
        trimToSize(this.maxSize);
        return previous;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void trimToSize(int r6) {
        /*
            r5 = this;
        L_0x0000:
            monitor-enter(r5)
            int r0 = r5.size     // Catch: all -> 0x0071
            if (r0 < 0) goto L_0x0052
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: all -> 0x0071
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0071
            if (r0 == 0) goto L_0x0011
            int r0 = r5.size     // Catch: all -> 0x0071
            if (r0 != 0) goto L_0x0052
        L_0x0011:
            int r0 = r5.size     // Catch: all -> 0x0071
            if (r0 <= r6) goto L_0x0050
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: all -> 0x0071
            boolean r0 = r0.isEmpty()     // Catch: all -> 0x0071
            if (r0 == 0) goto L_0x001e
            goto L_0x0050
        L_0x001e:
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: all -> 0x0071
            java.util.Set r0 = r0.entrySet()     // Catch: all -> 0x0071
            java.util.Iterator r0 = r0.iterator()     // Catch: all -> 0x0071
            java.lang.Object r0 = r0.next()     // Catch: all -> 0x0071
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: all -> 0x0071
            java.lang.Object r1 = r0.getKey()     // Catch: all -> 0x0071
            java.lang.Object r2 = r0.getValue()     // Catch: all -> 0x0071
            java.util.LinkedHashMap<K, V> r3 = r5.map     // Catch: all -> 0x0071
            r3.remove(r1)     // Catch: all -> 0x0071
            int r3 = r5.size     // Catch: all -> 0x0071
            int r4 = r5.safeSizeOf(r1, r2)     // Catch: all -> 0x0071
            int r3 = r3 - r4
            r5.size = r3     // Catch: all -> 0x0071
            int r3 = r5.evictionCount     // Catch: all -> 0x0071
            r4 = 1
            int r3 = r3 + r4
            r5.evictionCount = r3     // Catch: all -> 0x0071
            monitor-exit(r5)     // Catch: all -> 0x0071
            r0 = 0
            r5.entryRemoved(r4, r1, r2, r0)
            goto L_0x0000
        L_0x0050:
            monitor-exit(r5)     // Catch: all -> 0x0071
            return
        L_0x0052:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: all -> 0x0071
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: all -> 0x0071
            r1.<init>()     // Catch: all -> 0x0071
            java.lang.Class r2 = r5.getClass()     // Catch: all -> 0x0071
            java.lang.String r2 = r2.getName()     // Catch: all -> 0x0071
            r1.append(r2)     // Catch: all -> 0x0071
            java.lang.String r2 = ".sizeOf() is reporting inconsistent results!"
            r1.append(r2)     // Catch: all -> 0x0071
            java.lang.String r1 = r1.toString()     // Catch: all -> 0x0071
            r0.<init>(r1)     // Catch: all -> 0x0071
            throw r0     // Catch: all -> 0x0071
        L_0x0071:
            r0 = move-exception
            monitor-exit(r5)     // Catch: all -> 0x0071
            goto L_0x0075
        L_0x0074:
            throw r0
        L_0x0075:
            goto L_0x0074
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.trimToSize(int):void");
    }

    public final V remove(K key) {
        V previous;
        if (key != null) {
            synchronized (this) {
                previous = this.map.remove(key);
                if (previous != null) {
                    this.size -= safeSizeOf(key, previous);
                }
            }
            if (previous != null) {
                entryRemoved(false, key, previous, null);
            }
            return previous;
        }
        throw new NullPointerException("key == null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void entryRemoved(boolean evicted, K key, V oldValue, V newValue) {
    }

    protected V create(K key) {
        return null;
    }

    private int safeSizeOf(K key, V value) {
        int result = sizeOf(key, value);
        if (result >= 0) {
            return result;
        }
        throw new IllegalStateException("Negative size: " + key + "=" + value);
    }

    protected int sizeOf(K key, V value) {
        return 1;
    }

    public final void evictAll() {
        trimToSize(-1);
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int hitPercent;
        int accesses = this.hitCount + this.missCount;
        hitPercent = accesses != 0 ? (this.hitCount * 100) / accesses : 0;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(hitPercent));
    }
}
