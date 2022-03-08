package okhttp3.internal.ws;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

/* loaded from: classes.dex */
final class WebSocketWriter {
    boolean activeWriter;
    final Buffer buffer = new Buffer();
    final FrameSink frameSink = new FrameSink();
    final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    final Random random;
    final BufferedSink sink;
    final Buffer sinkBuffer;
    boolean writerClosed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WebSocketWriter(boolean isClient, BufferedSink sink, Random random) {
        if (sink == null) {
            throw new NullPointerException("sink == null");
        } else if (random != null) {
            this.isClient = isClient;
            this.sink = sink;
            this.sinkBuffer = sink.buffer();
            this.random = random;
            Buffer.UnsafeCursor unsafeCursor = null;
            this.maskKey = isClient ? new byte[4] : null;
            this.maskCursor = isClient ? new Buffer.UnsafeCursor() : unsafeCursor;
        } else {
            throw new NullPointerException("random == null");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePing(ByteString payload) throws IOException {
        writeControlFrame(9, payload);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writePong(ByteString payload) throws IOException {
        writeControlFrame(10, payload);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeClose(int code, ByteString reason) throws IOException {
        ByteString payload = ByteString.EMPTY;
        if (!(code == 0 && reason == null)) {
            if (code != 0) {
                WebSocketProtocol.validateCloseCode(code);
            }
            Buffer buffer = new Buffer();
            buffer.writeShort(code);
            if (reason != null) {
                buffer.write(reason);
            }
            payload = buffer.readByteString();
        }
        try {
            writeControlFrame(8, payload);
        } finally {
            this.writerClosed = true;
        }
    }

    private void writeControlFrame(int opcode, ByteString payload) throws IOException {
        if (!this.writerClosed) {
            int length = payload.size();
            if (length <= 125) {
                int b0 = opcode | 128;
                this.sinkBuffer.writeByte(b0);
                if (this.isClient) {
                    int b1 = length | 128;
                    this.sinkBuffer.writeByte(b1);
                    this.random.nextBytes(this.maskKey);
                    this.sinkBuffer.write(this.maskKey);
                    if (length > 0) {
                        long payloadStart = this.sinkBuffer.size();
                        this.sinkBuffer.write(payload);
                        this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                        this.maskCursor.seek(payloadStart);
                        WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                        this.maskCursor.close();
                    }
                } else {
                    this.sinkBuffer.writeByte(length);
                    this.sinkBuffer.write(payload);
                }
                this.sink.flush();
                return;
            }
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        throw new IOException("closed");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Sink newMessageSink(int formatOpcode, long contentLength) {
        if (!this.activeWriter) {
            this.activeWriter = true;
            this.frameSink.formatOpcode = formatOpcode;
            this.frameSink.contentLength = contentLength;
            this.frameSink.isFirstFrame = true;
            this.frameSink.closed = false;
            return this.frameSink;
        }
        throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }

    void writeMessageFrame(int formatOpcode, long byteCount, boolean isFirstFrame, boolean isFinal) throws IOException {
        if (!this.writerClosed) {
            int b0 = isFirstFrame ? formatOpcode : 0;
            if (isFinal) {
                b0 |= 128;
            }
            this.sinkBuffer.writeByte(b0);
            int b1 = 0;
            if (this.isClient) {
                b1 = 0 | 128;
            }
            if (byteCount <= 125) {
                this.sinkBuffer.writeByte(b1 | ((int) byteCount));
            } else if (byteCount <= 65535) {
                this.sinkBuffer.writeByte(b1 | 126);
                this.sinkBuffer.writeShort((int) byteCount);
            } else {
                this.sinkBuffer.writeByte(b1 | 127);
                this.sinkBuffer.writeLong(byteCount);
            }
            if (this.isClient) {
                this.random.nextBytes(this.maskKey);
                this.sinkBuffer.write(this.maskKey);
                if (byteCount > 0) {
                    long bufferStart = this.sinkBuffer.size();
                    this.sinkBuffer.write(this.buffer, byteCount);
                    this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
                    this.maskCursor.seek(bufferStart);
                    WebSocketProtocol.toggleMask(this.maskCursor, this.maskKey);
                    this.maskCursor.close();
                }
            } else {
                this.sinkBuffer.write(this.buffer, byteCount);
            }
            this.sink.emit();
            return;
        }
        throw new IOException("closed");
    }

    /* loaded from: classes.dex */
    final class FrameSink implements Sink {
        boolean closed;
        long contentLength;
        int formatOpcode;
        boolean isFirstFrame;

        FrameSink() {
        }

        @Override // okio.Sink
        public void write(Buffer source, long byteCount) throws IOException {
            if (!this.closed) {
                WebSocketWriter.this.buffer.write(source, byteCount);
                boolean deferWrite = this.isFirstFrame && this.contentLength != -1 && WebSocketWriter.this.buffer.size() > this.contentLength - PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                long emitCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
                if (emitCount > 0 && !deferWrite) {
                    WebSocketWriter.this.writeMessageFrame(this.formatOpcode, emitCount, this.isFirstFrame, false);
                    this.isFirstFrame = false;
                    return;
                }
                return;
            }
            throw new IOException("closed");
        }

        @Override // okio.Sink, java.io.Flushable
        public void flush() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, false);
                this.isFirstFrame = false;
                return;
            }
            throw new IOException("closed");
        }

        @Override // okio.Sink
        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (!this.closed) {
                WebSocketWriter webSocketWriter = WebSocketWriter.this;
                webSocketWriter.writeMessageFrame(this.formatOpcode, webSocketWriter.buffer.size(), this.isFirstFrame, true);
                this.closed = true;
                WebSocketWriter.this.activeWriter = false;
                return;
            }
            throw new IOException("closed");
        }
    }
}
