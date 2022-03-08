package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

/* loaded from: classes.dex */
public final class MultipartBody extends RequestBody {
    private final ByteString boundary;
    private long contentLength = -1;
    private final MediaType contentType;
    private final MediaType originalType;
    private final List<Part> parts;
    public static final MediaType MIXED = MediaType.get("multipart/mixed");
    public static final MediaType ALTERNATIVE = MediaType.get("multipart/alternative");
    public static final MediaType DIGEST = MediaType.get("multipart/digest");
    public static final MediaType PARALLEL = MediaType.get("multipart/parallel");
    public static final MediaType FORM = MediaType.get("multipart/form-data");
    private static final byte[] COLONSPACE = {58, 32};
    private static final byte[] CRLF = {13, 10};
    private static final byte[] DASHDASH = {45, 45};

    MultipartBody(ByteString boundary, MediaType type, List<Part> parts) {
        this.boundary = boundary;
        this.originalType = type;
        this.contentType = MediaType.get(type + "; boundary=" + boundary.utf8());
        this.parts = Util.immutableList(parts);
    }

    public MediaType type() {
        return this.originalType;
    }

    public String boundary() {
        return this.boundary.utf8();
    }

    public int size() {
        return this.parts.size();
    }

    public List<Part> parts() {
        return this.parts;
    }

    public Part part(int index) {
        return this.parts.get(index);
    }

    @Override // okhttp3.RequestBody
    public MediaType contentType() {
        return this.contentType;
    }

    @Override // okhttp3.RequestBody
    public long contentLength() throws IOException {
        long result = this.contentLength;
        if (result != -1) {
            return result;
        }
        long writeOrCountBytes = writeOrCountBytes(null, true);
        this.contentLength = writeOrCountBytes;
        return writeOrCountBytes;
    }

    @Override // okhttp3.RequestBody
    public void writeTo(BufferedSink sink) throws IOException {
        writeOrCountBytes(sink, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long writeOrCountBytes(@Nullable BufferedSink sink, boolean countBytes) throws IOException {
        BufferedSink sink2;
        long byteCount = 0;
        Buffer byteCountBuffer = 0;
        if (countBytes) {
            sink2 = new Buffer();
            byteCountBuffer = sink2;
        } else {
            sink2 = sink;
        }
        int partCount = this.parts.size();
        for (int p = 0; p < partCount; p++) {
            Part part = this.parts.get(p);
            Headers headers = part.headers;
            RequestBody body = part.body;
            sink2.write(DASHDASH);
            sink2.write(this.boundary);
            sink2.write(CRLF);
            if (headers != null) {
                int headerCount = headers.size();
                for (int h = 0; h < headerCount; h++) {
                    sink2.writeUtf8(headers.name(h)).write(COLONSPACE).writeUtf8(headers.value(h)).write(CRLF);
                }
            }
            MediaType contentType = body.contentType();
            if (contentType != null) {
                sink2.writeUtf8("Content-Type: ").writeUtf8(contentType.toString()).write(CRLF);
            }
            long contentLength = body.contentLength();
            if (contentLength != -1) {
                sink2.writeUtf8("Content-Length: ").writeDecimalLong(contentLength).write(CRLF);
            } else if (countBytes) {
                byteCountBuffer.clear();
                return -1L;
            }
            sink2.write(CRLF);
            if (countBytes) {
                byteCount += contentLength;
            } else {
                body.writeTo(sink2);
            }
            sink2.write(CRLF);
        }
        sink2.write(DASHDASH);
        sink2.write(this.boundary);
        sink2.write(DASHDASH);
        sink2.write(CRLF);
        if (!countBytes) {
            return byteCount;
        }
        long byteCount2 = byteCount + byteCountBuffer.size();
        byteCountBuffer.clear();
        return byteCount2;
    }

    static StringBuilder appendQuotedString(StringBuilder target, String key) {
        target.append('\"');
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char ch = key.charAt(i);
            if (ch == '\n') {
                target.append("%0A");
            } else if (ch == '\r') {
                target.append("%0D");
            } else if (ch != '\"') {
                target.append(ch);
            } else {
                target.append("%22");
            }
        }
        target.append('\"');
        return target;
    }

    /* loaded from: classes.dex */
    public static final class Part {
        final RequestBody body;
        @Nullable
        final Headers headers;

        public static Part create(RequestBody body) {
            return create(null, body);
        }

        public static Part create(@Nullable Headers headers, RequestBody body) {
            if (body == null) {
                throw new NullPointerException("body == null");
            } else if (headers != null && headers.get("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            } else if (headers == null || headers.get("Content-Length") == null) {
                return new Part(headers, body);
            } else {
                throw new IllegalArgumentException("Unexpected header: Content-Length");
            }
        }

        public static Part createFormData(String name, String value) {
            return createFormData(name, null, RequestBody.create((MediaType) null, value));
        }

        public static Part createFormData(String name, @Nullable String filename, RequestBody body) {
            if (name != null) {
                StringBuilder disposition = new StringBuilder("form-data; name=");
                MultipartBody.appendQuotedString(disposition, name);
                if (filename != null) {
                    disposition.append("; filename=");
                    MultipartBody.appendQuotedString(disposition, filename);
                }
                return create(Headers.of("Content-Disposition", disposition.toString()), body);
            }
            throw new NullPointerException("name == null");
        }

        private Part(@Nullable Headers headers, RequestBody body) {
            this.headers = headers;
            this.body = body;
        }

        @Nullable
        public Headers headers() {
            return this.headers;
        }

        public RequestBody body() {
            return this.body;
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        private final ByteString boundary;
        private final List<Part> parts;
        private MediaType type;

        public Builder() {
            this(UUID.randomUUID().toString());
        }

        public Builder(String boundary) {
            this.type = MultipartBody.MIXED;
            this.parts = new ArrayList();
            this.boundary = ByteString.encodeUtf8(boundary);
        }

        public Builder setType(MediaType type) {
            if (type == null) {
                throw new NullPointerException("type == null");
            } else if (type.type().equals("multipart")) {
                this.type = type;
                return this;
            } else {
                throw new IllegalArgumentException("multipart != " + type);
            }
        }

        public Builder addPart(RequestBody body) {
            return addPart(Part.create(body));
        }

        public Builder addPart(@Nullable Headers headers, RequestBody body) {
            return addPart(Part.create(headers, body));
        }

        public Builder addFormDataPart(String name, String value) {
            return addPart(Part.createFormData(name, value));
        }

        public Builder addFormDataPart(String name, @Nullable String filename, RequestBody body) {
            return addPart(Part.createFormData(name, filename, body));
        }

        public Builder addPart(Part part) {
            if (part != null) {
                this.parts.add(part);
                return this;
            }
            throw new NullPointerException("part == null");
        }

        public MultipartBody build() {
            if (!this.parts.isEmpty()) {
                return new MultipartBody(this.boundary, this.type, this.parts);
            }
            throw new IllegalStateException("Multipart body must have at least one part.");
        }
    }
}
