package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes.dex */
public enum DeliveryMechanism {
    DEVELOPER(1),
    USER_SIDELOAD(2),
    TEST_DISTRIBUTION(3),
    APP_STORE(4);
    
    private final int id;

    DeliveryMechanism(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override // java.lang.Enum
    public String toString() {
        return Integer.toString(this.id);
    }

    public static DeliveryMechanism determineFrom(String installerPackageName) {
        return installerPackageName != null ? APP_STORE : DEVELOPER;
    }
}
