package com.firebase.ui.database;

/* loaded from: classes.dex */
class Preconditions {
    Preconditions() {
    }

    public static <T> T checkNotNull(T o) {
        if (o != null) {
            return o;
        }
        throw new IllegalArgumentException("Argument cannot be null.");
    }
}
