package dagger.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Provider;

/* loaded from: classes.dex */
public final class SetFactory<T> implements Factory<Set<T>> {
    private static final Factory<Set<Object>> EMPTY_FACTORY = InstanceFactory.create(Collections.emptySet());
    private final List<Provider<Collection<T>>> collectionProviders;
    private final List<Provider<T>> individualProviders;

    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>) EMPTY_FACTORY;
    }

    public static <T> Builder<T> builder(int individualProviderSize, int collectionProviderSize) {
        return new Builder<>(individualProviderSize, collectionProviderSize);
    }

    /* loaded from: classes.dex */
    public static final class Builder<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final List<Provider<Collection<T>>> collectionProviders;
        private final List<Provider<T>> individualProviders;

        private Builder(int individualProviderSize, int collectionProviderSize) {
            this.individualProviders = DaggerCollections.presizedList(individualProviderSize);
            this.collectionProviders = DaggerCollections.presizedList(collectionProviderSize);
        }

        public Builder<T> addProvider(Provider<? extends T> individualProvider) {
            this.individualProviders.add(individualProvider);
            return this;
        }

        public Builder<T> addCollectionProvider(Provider<? extends Collection<? extends T>> collectionProvider) {
            this.collectionProviders.add(collectionProvider);
            return this;
        }

        public SetFactory<T> build() {
            return new SetFactory<>(this.individualProviders, this.collectionProviders);
        }
    }

    private SetFactory(List<Provider<T>> individualProviders, List<Provider<Collection<T>>> collectionProviders) {
        this.individualProviders = individualProviders;
        this.collectionProviders = collectionProviders;
    }

    @Override // javax.inject.Provider
    public Set<T> get() {
        int size = this.individualProviders.size();
        List<Collection<T>> providedCollections = new ArrayList<>(this.collectionProviders.size());
        int c = this.collectionProviders.size();
        for (int i = 0; i < c; i++) {
            Collection<T> providedCollection = this.collectionProviders.get(i).get();
            size += providedCollection.size();
            providedCollections.add(providedCollection);
        }
        HashSet newHashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        int c2 = this.individualProviders.size();
        for (int i2 = 0; i2 < c2; i2++) {
            newHashSetWithExpectedSize.add(Preconditions.checkNotNull(this.individualProviders.get(i2).get()));
        }
        int c3 = providedCollections.size();
        for (int i3 = 0; i3 < c3; i3++) {
            for (T element : providedCollections.get(i3)) {
                newHashSetWithExpectedSize.add(Preconditions.checkNotNull(element));
            }
        }
        return Collections.unmodifiableSet(newHashSetWithExpectedSize);
    }
}
