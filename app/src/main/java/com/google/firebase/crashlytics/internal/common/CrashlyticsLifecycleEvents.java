package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes.dex */
interface CrashlyticsLifecycleEvents {
    void onBeginSession(String str, long j);

    void onCustomKey(String str, String str2);

    void onEndSession();

    void onLog(long j, String str);

    void onUserId(String str);
}
