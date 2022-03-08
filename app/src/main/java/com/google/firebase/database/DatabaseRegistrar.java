package com.google.firebase.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class DatabaseRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        ComponentFactory componentFactory;
        Component.Builder add = Component.builder(FirebaseDatabaseComponent.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.optional(InternalAuthProvider.class));
        componentFactory = DatabaseRegistrar$$Lambda$1.instance;
        return Arrays.asList(add.factory(componentFactory).build(), LibraryVersionComponent.create("fire-rtdb", "19.3.0"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirebaseDatabaseComponent lambda$getComponents$0(ComponentContainer c) {
        return new FirebaseDatabaseComponent((FirebaseApp) c.get(FirebaseApp.class), (InternalAuthProvider) c.get(InternalAuthProvider.class));
    }
}
