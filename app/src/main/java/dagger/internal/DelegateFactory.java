package dagger.internal;

import javax.inject.Provider;

/* loaded from: classes.dex */
public final class DelegateFactory<T> implements Factory<T> {
    private Provider<T> delegate;

    @Override // javax.inject.Provider
    public T get() {
        Provider<T> provider = this.delegate;
        if (provider != null) {
            return provider.get();
        }
        throw new IllegalStateException();
    }

    @Deprecated
    public void setDelegatedProvider(Provider<T> delegate) {
        setDelegate(this, delegate);
    }

    public static <T> void setDelegate(Provider<T> delegateFactory, Provider<T> delegate) {
        Preconditions.checkNotNull(delegate);
        DelegateFactory<T> asDelegateFactory = (DelegateFactory) delegateFactory;
        if (((DelegateFactory) asDelegateFactory).delegate == null) {
            ((DelegateFactory) asDelegateFactory).delegate = delegate;
            return;
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Provider<T> getDelegate() {
        return (Provider) Preconditions.checkNotNull(this.delegate);
    }
}
