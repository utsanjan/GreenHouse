package okio;

import java.io.IOException;
import java.util.zip.Inflater;

/* loaded from: classes.dex */
public final class InflaterSource implements Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final Inflater inflater;
    private final BufferedSource source;

    public InflaterSource(Source source, Inflater inflater) {
        this(Okio.buffer(source), inflater);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public InflaterSource(BufferedSource source, Inflater inflater) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater != null) {
            this.source = source;
            this.inflater = inflater;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0058, code lost:
        releaseInflatedBytes();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x005f, code lost:
        if (r1.pos != r1.limit) goto L_?;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0061, code lost:
        r9.head = r1.pop();
        okio.SegmentPool.recycle(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006a, code lost:
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:?, code lost:
        return -1;
     */
    @Override // okio.Source
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long read(okio.Buffer r9, long r10) throws java.io.IOException {
        /*
            r8 = this;
            r0 = 0
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 < 0) goto L_0x007c
            boolean r2 = r8.closed
            if (r2 != 0) goto L_0x0074
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 != 0) goto L_0x000f
            return r0
        L_0x000f:
            boolean r0 = r8.refill()
            r1 = 1
            okio.Segment r1 = r9.writableSegment(r1)     // Catch: DataFormatException -> 0x006d
            int r2 = r1.limit     // Catch: DataFormatException -> 0x006d
            int r2 = 8192 - r2
            long r2 = (long) r2     // Catch: DataFormatException -> 0x006d
            long r2 = java.lang.Math.min(r10, r2)     // Catch: DataFormatException -> 0x006d
            int r3 = (int) r2     // Catch: DataFormatException -> 0x006d
            java.util.zip.Inflater r2 = r8.inflater     // Catch: DataFormatException -> 0x006d
            byte[] r4 = r1.data     // Catch: DataFormatException -> 0x006d
            int r5 = r1.limit     // Catch: DataFormatException -> 0x006d
            int r2 = r2.inflate(r4, r5, r3)     // Catch: DataFormatException -> 0x006d
            if (r2 <= 0) goto L_0x003b
            int r4 = r1.limit     // Catch: DataFormatException -> 0x006d
            int r4 = r4 + r2
            r1.limit = r4     // Catch: DataFormatException -> 0x006d
            long r4 = r9.size     // Catch: DataFormatException -> 0x006d
            long r6 = (long) r2     // Catch: DataFormatException -> 0x006d
            long r4 = r4 + r6
            r9.size = r4     // Catch: DataFormatException -> 0x006d
            long r4 = (long) r2     // Catch: DataFormatException -> 0x006d
            return r4
        L_0x003b:
            java.util.zip.Inflater r4 = r8.inflater     // Catch: DataFormatException -> 0x006d
            boolean r4 = r4.finished()     // Catch: DataFormatException -> 0x006d
            if (r4 != 0) goto L_0x0058
            java.util.zip.Inflater r4 = r8.inflater     // Catch: DataFormatException -> 0x006d
            boolean r4 = r4.needsDictionary()     // Catch: DataFormatException -> 0x006d
            if (r4 == 0) goto L_0x004c
            goto L_0x0058
        L_0x004c:
            if (r0 != 0) goto L_0x0050
            goto L_0x000f
        L_0x0050:
            java.io.EOFException r4 = new java.io.EOFException     // Catch: DataFormatException -> 0x006d
            java.lang.String r5 = "source exhausted prematurely"
            r4.<init>(r5)     // Catch: DataFormatException -> 0x006d
            throw r4     // Catch: DataFormatException -> 0x006d
        L_0x0058:
            r8.releaseInflatedBytes()     // Catch: DataFormatException -> 0x006d
            int r4 = r1.pos     // Catch: DataFormatException -> 0x006d
            int r5 = r1.limit     // Catch: DataFormatException -> 0x006d
            if (r4 != r5) goto L_0x006a
            okio.Segment r4 = r1.pop()     // Catch: DataFormatException -> 0x006d
            r9.head = r4     // Catch: DataFormatException -> 0x006d
            okio.SegmentPool.recycle(r1)     // Catch: DataFormatException -> 0x006d
        L_0x006a:
            r4 = -1
            return r4
        L_0x006d:
            r1 = move-exception
            java.io.IOException r2 = new java.io.IOException
            r2.<init>(r1)
            throw r2
        L_0x0074:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "closed"
            r0.<init>(r1)
            throw r0
        L_0x007c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "byteCount < 0: "
            r1.append(r2)
            r1.append(r10)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            goto L_0x0094
        L_0x0093:
            throw r0
        L_0x0094:
            goto L_0x0093
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.InflaterSource.read(okio.Buffer, long):long");
    }

    public final boolean refill() throws IOException {
        if (!this.inflater.needsInput()) {
            return false;
        }
        releaseInflatedBytes();
        if (this.inflater.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.source.exhausted()) {
            return true;
        } else {
            Segment head = this.source.buffer().head;
            this.bufferBytesHeldByInflater = head.limit - head.pos;
            this.inflater.setInput(head.data, head.pos, this.bufferBytesHeldByInflater);
            return false;
        }
    }

    private void releaseInflatedBytes() throws IOException {
        int i = this.bufferBytesHeldByInflater;
        if (i != 0) {
            int toRelease = i - this.inflater.getRemaining();
            this.bufferBytesHeldByInflater -= toRelease;
            this.source.skip(toRelease);
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.inflater.end();
            this.closed = true;
            this.source.close();
        }
    }
}
