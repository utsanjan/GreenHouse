package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

/* loaded from: classes.dex */
final class Relay {
    private static final long FILE_HEADER_SIZE = 32;
    static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
    static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    final long bufferMaxSize;
    boolean complete;
    RandomAccessFile file;
    private final ByteString metadata;
    int sourceCount;
    Source upstream;
    long upstreamPos;
    Thread upstreamReader;
    final Buffer upstreamBuffer = new Buffer();
    final Buffer buffer = new Buffer();

    private Relay(RandomAccessFile file, Source upstream, long upstreamPos, ByteString metadata, long bufferMaxSize) {
        this.file = file;
        this.upstream = upstream;
        this.complete = upstream == null;
        this.upstreamPos = upstreamPos;
        this.metadata = metadata;
        this.bufferMaxSize = bufferMaxSize;
    }

    public static Relay edit(File file, Source upstream, ByteString metadata, long bufferMaxSize) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        Relay result = new Relay(randomAccessFile, upstream, 0L, metadata, bufferMaxSize);
        randomAccessFile.setLength(0L);
        result.writeHeader(PREFIX_DIRTY, -1L, -1L);
        return result;
    }

    public static Relay read(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileOperator fileOperator = new FileOperator(randomAccessFile.getChannel());
        Buffer header = new Buffer();
        fileOperator.read(0L, header, 32L);
        ByteString prefix = header.readByteString(PREFIX_CLEAN.size());
        if (prefix.equals(PREFIX_CLEAN)) {
            long upstreamSize = header.readLong();
            long metadataSize = header.readLong();
            Buffer metadataBuffer = new Buffer();
            fileOperator.read(upstreamSize + 32, metadataBuffer, metadataSize);
            ByteString metadata = metadataBuffer.readByteString();
            return new Relay(randomAccessFile, null, upstreamSize, metadata, 0L);
        }
        throw new IOException("unreadable cache file");
    }

    private void writeHeader(ByteString prefix, long upstreamSize, long metadataSize) throws IOException {
        Buffer header = new Buffer();
        header.write(prefix);
        header.writeLong(upstreamSize);
        header.writeLong(metadataSize);
        if (header.size() == 32) {
            FileOperator fileOperator = new FileOperator(this.file.getChannel());
            fileOperator.write(0L, header, 32L);
            return;
        }
        throw new IllegalArgumentException();
    }

    private void writeMetadata(long upstreamSize) throws IOException {
        Buffer metadataBuffer = new Buffer();
        metadataBuffer.write(this.metadata);
        FileOperator fileOperator = new FileOperator(this.file.getChannel());
        fileOperator.write(32 + upstreamSize, metadataBuffer, this.metadata.size());
    }

    void commit(long upstreamSize) throws IOException {
        writeMetadata(upstreamSize);
        this.file.getChannel().force(false);
        writeHeader(PREFIX_CLEAN, upstreamSize, this.metadata.size());
        this.file.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
        }
        Util.closeQuietly(this.upstream);
        this.upstream = null;
    }

    boolean isClosed() {
        return this.file == null;
    }

    public ByteString metadata() {
        return this.metadata;
    }

    public Source newSource() {
        synchronized (this) {
            if (this.file == null) {
                return null;
            }
            this.sourceCount++;
            return new RelaySource();
        }
    }

    /* loaded from: classes.dex */
    class RelaySource implements Source {
        private FileOperator fileOperator;
        private long sourcePos;
        private final Timeout timeout = new Timeout();

        RelaySource() {
            this.fileOperator = new FileOperator(Relay.this.file.getChannel());
        }

        /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
            if (r5 != 2) goto L_0x006c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
            r11 = java.lang.Math.min(r23, r7 - r21.sourcePos);
            r21.fileOperator.read(r21.sourcePos + 32, r22, r11);
            r21.sourcePos += r11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x006b, code lost:
            return r11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x006d, code lost:
            r13 = r21.this$0.upstream.read(r21.this$0.upstreamBuffer, r21.this$0.bufferMaxSize);
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x007f, code lost:
            if (r13 != (-1)) goto L_0x009c;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x0081, code lost:
            r21.this$0.commit(r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0087, code lost:
            r6 = r21.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0089, code lost:
            monitor-enter(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x008a, code lost:
            r21.this$0.upstreamReader = null;
            r21.this$0.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0093, code lost:
            monitor-exit(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0094, code lost:
            return -1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0098, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
            r11 = java.lang.Math.min(r13, r23);
            r21.this$0.upstreamBuffer.copyTo(r22, 0, r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00ae, code lost:
            r21.sourcePos += r11;
            r21.fileOperator.write(r7 + 32, r21.this$0.upstreamBuffer.clone(), r13);
            r4 = r21.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00c6, code lost:
            monitor-enter(r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00c7, code lost:
            r21.this$0.buffer.write(r21.this$0.upstreamBuffer, r13);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00e1, code lost:
            if (r21.this$0.buffer.size() <= r21.this$0.bufferMaxSize) goto L_0x00f7;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00e3, code lost:
            r21.this$0.buffer.skip(r21.this$0.buffer.size() - r21.this$0.bufferMaxSize);
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00f7, code lost:
            r21.this$0.upstreamPos += r13;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00fe, code lost:
            monitor-exit(r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x0100, code lost:
            r5 = r21.this$0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0102, code lost:
            monitor-enter(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0103, code lost:
            r21.this$0.upstreamReader = null;
            r21.this$0.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x010d, code lost:
            monitor-exit(r5);
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x010e, code lost:
            return r11;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x0112, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x0114, code lost:
            monitor-exit(r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x0115, code lost:
            throw r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x0116, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x0118, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x011a, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x011d, code lost:
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x0121, code lost:
            monitor-enter(r21.this$0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0122, code lost:
            r21.this$0.upstreamReader = null;
            r21.this$0.notifyAll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x012d, code lost:
            throw r0;
         */
        /* JADX WARN: Removed duplicated region for block: B:100:0x0122 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // okio.Source
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public long read(okio.Buffer r22, long r23) throws java.io.IOException {
            /*
                Method dump skipped, instructions count: 348
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache2.Relay.RelaySource.read(okio.Buffer, long):long");
        }

        @Override // okio.Source
        public Timeout timeout() {
            return this.timeout;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.fileOperator != null) {
                this.fileOperator = null;
                RandomAccessFile fileToClose = null;
                synchronized (Relay.this) {
                    Relay relay = Relay.this;
                    relay.sourceCount--;
                    if (Relay.this.sourceCount == 0) {
                        fileToClose = Relay.this.file;
                        Relay.this.file = null;
                    }
                }
                if (fileToClose != null) {
                    Util.closeQuietly(fileToClose);
                }
            }
        }
    }
}
