package com.google.firebase.crashlytics.internal;

import java.io.File;

/* loaded from: classes.dex */
public final class MissingNativeComponent implements CrashlyticsNativeComponent {
    private static final NativeSessionFileProvider MISSING_NATIVE_SESSION_FILE_PROVIDER = new MissingNativeSessionFileProvider();

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public boolean hasCrashDataForSession(String sessionId) {
        return false;
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public boolean openSession(String sessionId) {
        return true;
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public boolean finalizeSession(String sessionId) {
        return true;
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public NativeSessionFileProvider getSessionFileProvider(String sessionId) {
        return MISSING_NATIVE_SESSION_FILE_PROVIDER;
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public void writeBeginSession(String sessionId, String generator, long startedAtSeconds) {
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public void writeSessionApp(String sessionId, String appIdentifier, String versionCode, String versionName, String installUuid, int deliveryMechanism, String unityVersion) {
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public void writeSessionOs(String sessionId, String osRelease, String osCodeName, boolean isRooted) {
    }

    @Override // com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent
    public void writeSessionDevice(String sessionId, int arch, String model, int availableProcessors, long totalRam, long diskSpace, boolean isEmulator, int state, String manufacturer, String modelClass) {
    }

    /* loaded from: classes.dex */
    private static final class MissingNativeSessionFileProvider implements NativeSessionFileProvider {
        private MissingNativeSessionFileProvider() {
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getMinidumpFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getBinaryImagesFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getMetadataFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getSessionFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getAppFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getDeviceFile() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.NativeSessionFileProvider
        public File getOsFile() {
            return null;
        }
    }
}
