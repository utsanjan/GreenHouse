package com.google.firebase.storage;

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

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class StorageRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        ComponentFactory componentFactory;
        Component.Builder add = Component.builder(FirebaseStorageComponent.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.optionalProvider(InternalAuthProvider.class));
        componentFactory = StorageRegistrar$$Lambda$1.instance;
        return Arrays.asList(add.factory(componentFactory).build(), LibraryVersionComponent.create("fire-gcs", BuildConfig.VERSION_NAME));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirebaseStorageComponent lambda$getComponents$0(ComponentContainer c) {
        return new FirebaseStorageComponent((FirebaseApp) c.get(FirebaseApp.class), c.getProvider(InternalAuthProvider.class));
    }
}
