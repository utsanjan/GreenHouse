package com.google.firebase.database;

import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
final /* synthetic */ class DatabaseRegistrar$$Lambda$1 implements ComponentFactory {
    private static final DatabaseRegistrar$$Lambda$1 instance = new DatabaseRegistrar$$Lambda$1();

    private DatabaseRegistrar$$Lambda$1() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public Object create(ComponentContainer componentContainer) {
        return DatabaseRegistrar.lambda$getComponents$0(componentContainer);
    }
}
