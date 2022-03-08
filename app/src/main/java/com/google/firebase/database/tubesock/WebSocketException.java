package com.google.firebase.database.tubesock;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class WebSocketException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public WebSocketException(String message) {
        super(message);
    }

    public WebSocketException(String message, Throwable t) {
        super(message, t);
    }
}
