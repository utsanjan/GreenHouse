package com.google.firebase.installations;

import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
public class FirebaseInstallationsRegistrar implements ComponentRegistrar {
    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        ComponentFactory componentFactory;
        Component.Builder add = Component.builder(FirebaseInstallationsApi.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.required(HeartBeatInfo.class)).add(Dependency.required(UserAgentPublisher.class));
        componentFactory = FirebaseInstallationsRegistrar$$Lambda$1.instance;
        return Arrays.asList(add.factory(componentFactory).build(), LibraryVersionComponent.create("fire-installations", BuildConfig.VERSION_NAME));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirebaseInstallationsApi lambda$getComponents$0(ComponentContainer c) {
        return new FirebaseInstallations((FirebaseApp) c.get(FirebaseApp.class), (UserAgentPublisher) c.get(UserAgentPublisher.class), (HeartBeatInfo) c.get(HeartBeatInfo.class));
    }
}
