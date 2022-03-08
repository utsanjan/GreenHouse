package com.google.firebase.crashlytics.internal.settings.model;

/* loaded from: classes.dex */
public class SessionSettingsData {
    public final int maxCompleteSessionsCount;
    public final int maxCustomExceptionEvents;

    public SessionSettingsData(int maxCustomExceptionEvents, int maxCompleteSessionsCount) {
        this.maxCustomExceptionEvents = maxCustomExceptionEvents;
        this.maxCompleteSessionsCount = maxCompleteSessionsCount;
    }
}
