package okio;

import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Segment {
    static final int SHARE_MINIMUM = 1024;
    static final int SIZE = 8192;
    final byte[] data;
    int limit;
    Segment next;
    boolean owner;
    int pos;
    Segment prev;
    boolean shared;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Segment(byte[] data, int pos, int limit, boolean shared, boolean owner) {
        this.data = data;
        this.pos = pos;
        this.limit = limit;
        this.shared = shared;
        this.owner = owner;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Segment unsharedCopy() {
        return new Segment((byte[]) this.data.clone(), this.pos, this.limit, false, true);
    }

    @Nullable
    public final Segment pop() {
        Segment result = this.next;
        if (result == this) {
            result = null;
        }
        Segment segment = this.prev;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = null;
        this.prev = null;
        return result;
    }

    public final Segment push(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }

    public final Segment split(int byteCount) {
        Segment prefix;
        if (byteCount <= 0 || byteCount > this.limit - this.pos) {
            throw new IllegalArgumentException();
        }
        if (byteCount >= 1024) {
            prefix = sharedCopy();
        } else {
            prefix = SegmentPool.take();
            System.arraycopy(this.data, this.pos, prefix.data, 0, byteCount);
        }
        prefix.limit = prefix.pos + byteCount;
        this.pos += byteCount;
        this.prev.push(prefix);
        return prefix;
    }

    public final void compact() {
        Segment segment = this.prev;
        if (segment == this) {
            throw new IllegalStateException();
        } else if (segment.owner) {
            int byteCount = this.limit - this.pos;
            int availableByteCount = (8192 - segment.limit) + (segment.shared ? 0 : segment.pos);
            if (byteCount <= availableByteCount) {
                writeTo(this.prev, byteCount);
                pop();
                SegmentPool.recycle(this);
            }
        }
    }

    public final void writeTo(Segment sink, int byteCount) {
        if (sink.owner) {
            int i = sink.limit;
            if (i + byteCount > 8192) {
                if (!sink.shared) {
                    int i2 = sink.pos;
                    if ((i + byteCount) - i2 <= 8192) {
                        byte[] bArr = sink.data;
                        System.arraycopy(bArr, i2, bArr, 0, i - i2);
                        sink.limit -= sink.pos;
                        sink.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.data, this.pos, sink.data, sink.limit, byteCount);
            sink.limit += byteCount;
            this.pos += byteCount;
            return;
        }
        throw new IllegalArgumentException();
    }
}
