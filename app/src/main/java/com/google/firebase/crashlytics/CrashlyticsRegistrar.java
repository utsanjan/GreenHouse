package com.google.firebase.crashlytics;

import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public class CrashlyticsRegistrar implements ComponentRegistrar {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FirebaseCrashlytics access$lambda$0(CrashlyticsRegistrar crashlyticsRegistrar, ComponentContainer componentContainer) {
        return crashlyticsRegistrar.buildCrashlytics(componentContainer);
    }

    @Override // com.google.firebase.components.ComponentRegistrar
    public List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder(FirebaseCrashlytics.class).add(Dependency.required(FirebaseApp.class)).add(Dependency.requiredProvider(FirebaseInstanceIdInternal.class)).add(Dependency.optional(AnalyticsConnector.class)).add(Dependency.optional(CrashlyticsNativeComponent.class)).factory(CrashlyticsRegistrar$$Lambda$1.lambdaFactory$(this)).eagerInDefaultApp().build(), LibraryVersionComponent.create("fire-cls", BuildConfig.VERSION_NAME));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FirebaseCrashlytics buildCrashlytics(ComponentContainer container) {
        FirebaseApp app = (FirebaseApp) container.get(FirebaseApp.class);
        CrashlyticsNativeComponent nativeComponent = (CrashlyticsNativeComponent) container.get(CrashlyticsNativeComponent.class);
        AnalyticsConnector analyticsConnector = (AnalyticsConnector) container.get(AnalyticsConnector.class);
        FirebaseInstanceIdInternal instanceId = (FirebaseInstanceIdInternal) container.getProvider(FirebaseInstanceIdInternal.class).get();
        return FirebaseCrashlytics.init(app, instanceId, nativeComponent, analyticsConnector);
    }
}
