package com.google.firebase.installations;

import com.google.firebase.FirebaseException;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
public class FirebaseInstallationsException extends FirebaseException {
    private final Status status;

    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    /* loaded from: classes.dex */
    public enum Status {
        BAD_CONFIG
    }

    public FirebaseInstallationsException(Status status) {
        this.status = status;
    }

    public FirebaseInstallationsException(String message, Status status) {
        super(message);
        this.status = status;
    }

    public FirebaseInstallationsException(String message, Status status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }
}
