package androidx.core.util;

/* loaded from: classes.dex */
public final class Pools {

    /* loaded from: classes.dex */
    public interface Pool<T> {
        T acquire();

        boolean release(T t);
    }

    private Pools() {
    }

    /* loaded from: classes.dex */
    public static class SimplePool<T> implements Pool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public SimplePool(int maxPoolSize) {
            if (maxPoolSize > 0) {
                this.mPool = new Object[maxPoolSize];
                return;
            }
            throw new IllegalArgumentException("The max pool size must be > 0");
        }

        @Override // androidx.core.util.Pools.Pool
        public T acquire() {
            int i = this.mPoolSize;
            if (i <= 0) {
                return null;
            }
            int lastPooledIndex = i - 1;
            Object[] objArr = this.mPool;
            T instance = (T) objArr[lastPooledIndex];
            objArr[lastPooledIndex] = null;
            this.mPoolSize = i - 1;
            return instance;
        }

        @Override // androidx.core.util.Pools.Pool
        public boolean release(T instance) {
            if (!isInPool(instance)) {
                int i = this.mPoolSize;
                Object[] objArr = this.mPool;
                if (i >= objArr.length) {
                    return false;
                }
                objArr[i] = instance;
                this.mPoolSize = i + 1;
                return true;
            }
            throw new IllegalStateException("Already in the pool!");
        }

        private boolean isInPool(T instance) {
            for (int i = 0; i < this.mPoolSize; i++) {
                if (this.mPool[i] == instance) {
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes.dex */
    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock = new Object();

        public SynchronizedPool(int maxPoolSize) {
            super(maxPoolSize);
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public T acquire() {
            T t;
            synchronized (this.mLock) {
                t = (T) super.acquire();
            }
            return t;
        }

        @Override // androidx.core.util.Pools.SimplePool, androidx.core.util.Pools.Pool
        public boolean release(T element) {
            boolean release;
            synchronized (this.mLock) {
                release = super.release(element);
            }
            return release;
        }
    }
}
