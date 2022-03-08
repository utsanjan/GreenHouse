package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;

/* loaded from: classes.dex */
public enum DataTransportState {
    NONE,
    JAVA_ONLY,
    ALL;
    
    static final int REPORT_UPLOAD_VARIANT_DATATRANSPORT = 2;
    static final int REPORT_UPLOAD_VARIANT_LEGACY = 1;

    static DataTransportState getState(boolean dataTransportState, boolean dataTransportNativeState) {
        if (!dataTransportState) {
            return NONE;
        }
        if (!dataTransportNativeState) {
            return JAVA_ONLY;
        }
        return ALL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static DataTransportState getState(AppSettingsData appSettingsData) {
        boolean dataTransportNativeState = true;
        boolean dataTransportState = appSettingsData.reportUploadVariant == 2;
        if (appSettingsData.nativeReportUploadVariant != 2) {
            dataTransportNativeState = false;
        }
        return getState(dataTransportState, dataTransportNativeState);
    }
}
