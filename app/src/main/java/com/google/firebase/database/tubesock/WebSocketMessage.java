package com.google.firebase.database.tubesock;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class WebSocketMessage {
    private byte[] byteMessage;
    private byte opcode = 1;
    private String stringMessage;

    public WebSocketMessage(byte[] message) {
        this.byteMessage = message;
    }

    public WebSocketMessage(String message) {
        this.stringMessage = message;
    }

    public boolean isText() {
        return this.opcode == 1;
    }

    public boolean isBinary() {
        return this.opcode == 2;
    }

    public byte[] getBytes() {
        return this.byteMessage;
    }

    public String getText() {
        return this.stringMessage;
    }
}
