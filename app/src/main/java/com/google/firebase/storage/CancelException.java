package com.google.firebase.storage;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
class CancelException extends IOException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CancelException() {
        super("The operation was canceled.");
    }
}
