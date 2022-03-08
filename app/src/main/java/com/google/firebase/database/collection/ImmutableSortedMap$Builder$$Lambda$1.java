package com.google.firebase.database.collection;

import com.google.firebase.database.collection.ImmutableSortedMap;

/* compiled from: com.google.firebase:firebase-database-collection@@17.0.1 */
/* loaded from: classes.dex */
final /* synthetic */ class ImmutableSortedMap$Builder$$Lambda$1 implements ImmutableSortedMap.Builder.KeyTranslator {
    private static final ImmutableSortedMap$Builder$$Lambda$1 instance = new ImmutableSortedMap$Builder$$Lambda$1();

    private ImmutableSortedMap$Builder$$Lambda$1() {
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap.Builder.KeyTranslator
    public Object translate(Object obj) {
        return ImmutableSortedMap.Builder.lambda$static$0(obj);
    }
}
