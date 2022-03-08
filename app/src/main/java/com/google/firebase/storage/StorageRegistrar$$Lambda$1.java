package com.google.firebase.storage;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
final /* synthetic */ class StorageRegistrar$$Lambda$1 implements ComponentFactory {
    private static final StorageRegistrar$$Lambda$1 instance = new StorageRegistrar$$Lambda$1();

    private StorageRegistrar$$Lambda$1() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public Object create(ComponentContainer componentContainer) {
        return StorageRegistrar.lambda$getComponents$0(componentContainer);
    }
}
