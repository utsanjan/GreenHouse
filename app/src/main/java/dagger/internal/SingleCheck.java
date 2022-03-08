package dagger.internal;

import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SingleCheck<T> implements Provider<T> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider<T> provider;

    private SingleCheck(Provider<T> provider) {
        this.provider = provider;
    }

    @Override // javax.inject.Provider
    public T get() {
        T t = (T) this.instance;
        if (t != UNINITIALIZED) {
            return t;
        }
        Provider<T> providerReference = this.provider;
        if (providerReference == null) {
            Object local = this.instance;
            return (T) local;
        }
        T t2 = providerReference.get();
        this.instance = t2;
        this.provider = null;
        return t2;
    }

    public static <P extends Provider<T>, T> Provider<T> provider(P provider) {
        if ((provider instanceof SingleCheck) || (provider instanceof DoubleCheck)) {
            return provider;
        }
        return new SingleCheck((Provider) Preconditions.checkNotNull(provider));
    }
}
