package com.google.firebase.database.tubesock;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
class MessageBuilderFactory {

    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* loaded from: classes.dex */
    interface Builder {
        boolean appendBytes(byte[] bArr);

        WebSocketMessage toMessage();
    }

    MessageBuilderFactory() {
    }

    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* loaded from: classes.dex */
    static class BinaryBuilder implements Builder {
        private int pendingByteCount = 0;
        private List<byte[]> pendingBytes = new ArrayList();

        BinaryBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bytes) {
            this.pendingBytes.add(bytes);
            this.pendingByteCount += bytes.length;
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            byte[] payload = new byte[this.pendingByteCount];
            int offset = 0;
            for (int i = 0; i < this.pendingBytes.size(); i++) {
                byte[] segment = this.pendingBytes.get(i);
                System.arraycopy(segment, 0, payload, offset, segment.length);
                offset += segment.length;
            }
            return new WebSocketMessage(payload);
        }
    }

    /* compiled from: com.google.firebase:firebase-database@@19.3.0 */
    /* loaded from: classes.dex */
    static class TextBuilder implements Builder {
        private static ThreadLocal<CharsetDecoder> localDecoder = new ThreadLocal<CharsetDecoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetDecoder initialValue() {
                Charset utf8 = Charset.forName("UTF8");
                CharsetDecoder decoder = utf8.newDecoder();
                decoder.onMalformedInput(CodingErrorAction.REPORT);
                decoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return decoder;
            }
        };
        private static ThreadLocal<CharsetEncoder> localEncoder = new ThreadLocal<CharsetEncoder>() { // from class: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // java.lang.ThreadLocal
            public CharsetEncoder initialValue() {
                Charset utf8 = Charset.forName("UTF8");
                CharsetEncoder encoder = utf8.newEncoder();
                encoder.onMalformedInput(CodingErrorAction.REPORT);
                encoder.onUnmappableCharacter(CodingErrorAction.REPORT);
                return encoder;
            }
        };
        private StringBuilder builder = new StringBuilder();
        private ByteBuffer carryOver;

        TextBuilder() {
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public boolean appendBytes(byte[] bytes) {
            String nextFrame = decodeString(bytes);
            if (nextFrame == null) {
                return false;
            }
            this.builder.append(nextFrame);
            return true;
        }

        @Override // com.google.firebase.database.tubesock.MessageBuilderFactory.Builder
        public WebSocketMessage toMessage() {
            if (this.carryOver != null) {
                return null;
            }
            return new WebSocketMessage(this.builder.toString());
        }

        private String decodeString(byte[] bytes) {
            try {
                ByteBuffer input = ByteBuffer.wrap(bytes);
                CharBuffer buf = localDecoder.get().decode(input);
                String text = buf.toString();
                return text;
            } catch (CharacterCodingException e) {
                return null;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x003c, code lost:
            if (r1.remaining() <= 0) goto L_0x0040;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
            r6.carryOver = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0040, code lost:
            r4 = java.nio.CharBuffer.wrap(r3);
            com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localEncoder.get().encode(r4);
            r3.flip();
            r0 = r3.toString();
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0056, code lost:
            return r0;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.String decodeStringStreaming(byte[] r7) {
            /*
                r6 = this;
                r0 = 0
                java.nio.ByteBuffer r1 = r6.getBuffer(r7)     // Catch: CharacterCodingException -> 0x006e
                int r2 = r1.remaining()     // Catch: CharacterCodingException -> 0x006e
                float r2 = (float) r2     // Catch: CharacterCodingException -> 0x006e
                java.lang.ThreadLocal<java.nio.charset.CharsetDecoder> r3 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localDecoder     // Catch: CharacterCodingException -> 0x006e
                java.lang.Object r3 = r3.get()     // Catch: CharacterCodingException -> 0x006e
                java.nio.charset.CharsetDecoder r3 = (java.nio.charset.CharsetDecoder) r3     // Catch: CharacterCodingException -> 0x006e
                float r3 = r3.averageCharsPerByte()     // Catch: CharacterCodingException -> 0x006e
                float r2 = r2 * r3
                int r2 = (int) r2     // Catch: CharacterCodingException -> 0x006e
                java.nio.CharBuffer r3 = java.nio.CharBuffer.allocate(r2)     // Catch: CharacterCodingException -> 0x006e
            L_0x001d:
                java.lang.ThreadLocal<java.nio.charset.CharsetDecoder> r4 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localDecoder     // Catch: CharacterCodingException -> 0x006e
                java.lang.Object r4 = r4.get()     // Catch: CharacterCodingException -> 0x006e
                java.nio.charset.CharsetDecoder r4 = (java.nio.charset.CharsetDecoder) r4     // Catch: CharacterCodingException -> 0x006e
                r5 = 0
                java.nio.charset.CoderResult r4 = r4.decode(r1, r3, r5)     // Catch: CharacterCodingException -> 0x006e
                boolean r5 = r4.isError()     // Catch: CharacterCodingException -> 0x006e
                if (r5 == 0) goto L_0x0031
                return r0
            L_0x0031:
                boolean r5 = r4.isUnderflow()     // Catch: CharacterCodingException -> 0x006e
                if (r5 == 0) goto L_0x0057
                int r4 = r1.remaining()     // Catch: CharacterCodingException -> 0x006e
                if (r4 <= 0) goto L_0x0040
                r6.carryOver = r1     // Catch: CharacterCodingException -> 0x006e
            L_0x0040:
                java.nio.CharBuffer r4 = java.nio.CharBuffer.wrap(r3)     // Catch: CharacterCodingException -> 0x006e
                java.lang.ThreadLocal<java.nio.charset.CharsetEncoder> r5 = com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.localEncoder     // Catch: CharacterCodingException -> 0x006e
                java.lang.Object r5 = r5.get()     // Catch: CharacterCodingException -> 0x006e
                java.nio.charset.CharsetEncoder r5 = (java.nio.charset.CharsetEncoder) r5     // Catch: CharacterCodingException -> 0x006e
                r5.encode(r4)     // Catch: CharacterCodingException -> 0x006e
                r3.flip()     // Catch: CharacterCodingException -> 0x006e
                java.lang.String r0 = r3.toString()     // Catch: CharacterCodingException -> 0x006e
                return r0
            L_0x0057:
                boolean r5 = r4.isOverflow()     // Catch: CharacterCodingException -> 0x006e
                if (r5 == 0) goto L_0x006d
                int r5 = r2 * 2
                int r5 = r5 + 1
                java.nio.CharBuffer r2 = java.nio.CharBuffer.allocate(r5)     // Catch: CharacterCodingException -> 0x006e
                r3.flip()     // Catch: CharacterCodingException -> 0x006e
                r2.put(r3)     // Catch: CharacterCodingException -> 0x006e
                r3 = r2
                r2 = r5
            L_0x006d:
                goto L_0x001d
            L_0x006e:
                r1 = move-exception
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.database.tubesock.MessageBuilderFactory.TextBuilder.decodeStringStreaming(byte[]):java.lang.String");
        }

        private ByteBuffer getBuffer(byte[] bytes) {
            ByteBuffer byteBuffer = this.carryOver;
            if (byteBuffer == null) {
                return ByteBuffer.wrap(bytes);
            }
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length + byteBuffer.remaining());
            buffer.put(this.carryOver);
            this.carryOver = null;
            buffer.put(bytes);
            buffer.flip();
            return buffer;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Builder builder(byte opcode) {
        if (opcode == 2) {
            return new BinaryBuilder();
        }
        return new TextBuilder();
    }
}
