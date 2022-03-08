package com.google.firebase.database.tubesock;

import com.google.firebase.database.tubesock.MessageBuilderFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
class WebSocketReceiver {
    private MessageBuilderFactory.Builder pendingBuilder;
    private WebSocket websocket;
    private DataInputStream input = null;
    private WebSocketEventHandler eventHandler = null;
    private byte[] inputHeader = new byte[112];
    private volatile boolean stop = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketReceiver(WebSocket websocket) {
        this.websocket = null;
        this.websocket = websocket;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setInput(DataInputStream input) {
        this.input = input;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void run() {
        int offset;
        boolean fin;
        boolean rsv;
        this.eventHandler = this.websocket.getEventHandler();
        while (!this.stop) {
            try {
                offset = 0 + read(this.inputHeader, 0, 1);
                fin = (this.inputHeader[0] & 128) != 0;
                rsv = (this.inputHeader[0] & 112) != 0;
            } catch (WebSocketException e) {
                handleError(e);
            } catch (SocketTimeoutException e2) {
            } catch (IOException ioe) {
                handleError(new WebSocketException("IO Error", ioe));
            }
            if (!rsv) {
                byte opcode = (byte) (this.inputHeader[0] & 15);
                int offset2 = offset + read(this.inputHeader, offset, 1);
                byte length = this.inputHeader[1];
                long payload_length = 0;
                if (length < 126) {
                    payload_length = length;
                } else if (length == 126) {
                    int read = offset2 + read(this.inputHeader, offset2, 2);
                    payload_length = ((this.inputHeader[2] & 255) << 8) | (this.inputHeader[3] & 255);
                } else if (length == Byte.MAX_VALUE) {
                    payload_length = parseLong(this.inputHeader, (offset2 + read(this.inputHeader, offset2, 8)) - 8);
                }
                byte[] payload = new byte[(int) payload_length];
                read(payload, 0, (int) payload_length);
                if (opcode == 8) {
                    this.websocket.onCloseOpReceived();
                } else if (opcode != 10) {
                    if (!(opcode == 1 || opcode == 2 || opcode == 9 || opcode == 0)) {
                        throw new WebSocketException("Unsupported opcode: " + ((int) opcode));
                    }
                    appendBytes(fin, opcode, payload);
                }
            } else {
                throw new WebSocketException("Invalid frame received");
            }
        }
    }

    private void appendBytes(boolean fin, byte opcode, byte[] data) {
        if (opcode == 9) {
            if (fin) {
                handlePing(data);
                return;
            }
            throw new WebSocketException("PING must not fragment across frames");
        } else if (this.pendingBuilder != null && opcode != 0) {
            throw new WebSocketException("Failed to continue outstanding frame");
        } else if (this.pendingBuilder == null && opcode == 0) {
            throw new WebSocketException("Received continuing frame, but there's nothing to continue");
        } else {
            if (this.pendingBuilder == null) {
                this.pendingBuilder = MessageBuilderFactory.builder(opcode);
            }
            if (!this.pendingBuilder.appendBytes(data)) {
                throw new WebSocketException("Failed to decode frame");
            } else if (fin) {
                WebSocketMessage message = this.pendingBuilder.toMessage();
                this.pendingBuilder = null;
                if (message != null) {
                    this.eventHandler.onMessage(message);
                    return;
                }
                throw new WebSocketException("Failed to decode whole message");
            }
        }
    }

    private void handlePing(byte[] payload) {
        if (payload.length <= 125) {
            this.websocket.pong(payload);
            return;
        }
        throw new WebSocketException("PING frame too long");
    }

    private long parseLong(byte[] buffer, int offset) {
        return (buffer[offset + 0] << 56) + ((buffer[offset + 1] & 255) << 48) + ((buffer[offset + 2] & 255) << 40) + ((buffer[offset + 3] & 255) << 32) + ((buffer[offset + 4] & 255) << 24) + ((buffer[offset + 5] & 255) << 16) + ((buffer[offset + 6] & 255) << 8) + ((buffer[offset + 7] & 255) << 0);
    }

    private int read(byte[] buffer, int offset, int length) throws IOException {
        this.input.readFully(buffer, offset, length);
        return length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopit() {
        this.stop = true;
    }

    boolean isRunning() {
        return !this.stop;
    }

    private void handleError(WebSocketException e) {
        stopit();
        this.websocket.handleReceiverError(e);
    }
}
