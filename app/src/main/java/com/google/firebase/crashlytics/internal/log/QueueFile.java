package com.google.firebase.crashlytics.internal.log;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
class QueueFile implements Closeable {
    static final int HEADER_LENGTH = 16;
    private static final int INITIAL_LENGTH = 4096;
    private static final Logger LOGGER = Logger.getLogger(QueueFile.class.getName());
    private final byte[] buffer;
    private int elementCount;
    int fileLength;
    private Element first;
    private Element last;
    private final RandomAccessFile raf;

    /* loaded from: classes.dex */
    public interface ElementReader {
        void read(InputStream inputStream, int i) throws IOException;
    }

    public QueueFile(File file) throws IOException {
        this.buffer = new byte[16];
        if (!file.exists()) {
            initialize(file);
        }
        this.raf = open(file);
        readHeader();
    }

    QueueFile(RandomAccessFile raf) throws IOException {
        this.buffer = new byte[16];
        this.raf = raf;
        readHeader();
    }

    private static void writeInt(byte[] buffer, int offset, int value) {
        buffer[offset] = (byte) (value >> 24);
        buffer[offset + 1] = (byte) (value >> 16);
        buffer[offset + 2] = (byte) (value >> 8);
        buffer[offset + 3] = (byte) value;
    }

    private static void writeInts(byte[] buffer, int... values) {
        int offset = 0;
        for (int value : values) {
            writeInt(buffer, offset, value);
            offset += 4;
        }
    }

    private static int readInt(byte[] buffer, int offset) {
        return ((buffer[offset] & 255) << 24) + ((buffer[offset + 1] & 255) << 16) + ((buffer[offset + 2] & 255) << 8) + (buffer[offset + 3] & 255);
    }

    private void readHeader() throws IOException {
        this.raf.seek(0L);
        this.raf.readFully(this.buffer);
        int readInt = readInt(this.buffer, 0);
        this.fileLength = readInt;
        if (readInt <= this.raf.length()) {
            this.elementCount = readInt(this.buffer, 4);
            int firstOffset = readInt(this.buffer, 8);
            int lastOffset = readInt(this.buffer, 12);
            this.first = readElement(firstOffset);
            this.last = readElement(lastOffset);
            return;
        }
        throw new IOException("File is truncated. Expected length: " + this.fileLength + ", Actual length: " + this.raf.length());
    }

    private void writeHeader(int fileLength, int elementCount, int firstPosition, int lastPosition) throws IOException {
        writeInts(this.buffer, fileLength, elementCount, firstPosition, lastPosition);
        this.raf.seek(0L);
        this.raf.write(this.buffer);
    }

    private Element readElement(int position) throws IOException {
        if (position == 0) {
            return Element.NULL;
        }
        this.raf.seek(position);
        return new Element(position, this.raf.readInt());
    }

    /* JADX WARN: Finally extract failed */
    private static void initialize(File file) throws IOException {
        File tempFile = new File(file.getPath() + ".tmp");
        RandomAccessFile raf = open(tempFile);
        try {
            raf.setLength(PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
            raf.seek(0L);
            byte[] headerBuffer = new byte[16];
            writeInts(headerBuffer, 4096, 0, 0, 0);
            raf.write(headerBuffer);
            raf.close();
            if (!tempFile.renameTo(file)) {
                throw new IOException("Rename failed!");
            }
        } catch (Throwable th) {
            raf.close();
            throw th;
        }
    }

    private static RandomAccessFile open(File file) throws FileNotFoundException {
        return new RandomAccessFile(file, "rwd");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int wrapPosition(int position) {
        int i = this.fileLength;
        return position < i ? position : (position + 16) - i;
    }

    private void ringWrite(int position, byte[] buffer, int offset, int count) throws IOException {
        int position2 = wrapPosition(position);
        int i = position2 + count;
        int i2 = this.fileLength;
        if (i <= i2) {
            this.raf.seek(position2);
            this.raf.write(buffer, offset, count);
            return;
        }
        int beforeEof = i2 - position2;
        this.raf.seek(position2);
        this.raf.write(buffer, offset, beforeEof);
        this.raf.seek(16L);
        this.raf.write(buffer, offset + beforeEof, count - beforeEof);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ringRead(int position, byte[] buffer, int offset, int count) throws IOException {
        int position2 = wrapPosition(position);
        int i = position2 + count;
        int i2 = this.fileLength;
        if (i <= i2) {
            this.raf.seek(position2);
            this.raf.readFully(buffer, offset, count);
            return;
        }
        int beforeEof = i2 - position2;
        this.raf.seek(position2);
        this.raf.readFully(buffer, offset, beforeEof);
        this.raf.seek(16L);
        this.raf.readFully(buffer, offset + beforeEof, count - beforeEof);
    }

    public void add(byte[] data) throws IOException {
        add(data, 0, data.length);
    }

    public synchronized void add(byte[] data, int offset, int count) throws IOException {
        int position;
        nonNull(data, "buffer");
        if ((offset | count) < 0 || count > data.length - offset) {
            throw new IndexOutOfBoundsException();
        }
        expandIfNecessary(count);
        boolean wasEmpty = isEmpty();
        if (wasEmpty) {
            position = 16;
        } else {
            position = wrapPosition(this.last.position + 4 + this.last.length);
        }
        Element newLast = new Element(position, count);
        writeInt(this.buffer, 0, count);
        ringWrite(newLast.position, this.buffer, 0, 4);
        ringWrite(newLast.position + 4, data, offset, count);
        int firstPosition = wasEmpty ? newLast.position : this.first.position;
        writeHeader(this.fileLength, this.elementCount + 1, firstPosition, newLast.position);
        this.last = newLast;
        this.elementCount++;
        if (wasEmpty) {
            this.first = newLast;
        }
    }

    public int usedBytes() {
        if (this.elementCount == 0) {
            return 16;
        }
        if (this.last.position >= this.first.position) {
            return (this.last.position - this.first.position) + 4 + this.last.length + 16;
        }
        return (((this.last.position + 4) + this.last.length) + this.fileLength) - this.first.position;
    }

    private int remainingBytes() {
        return this.fileLength - usedBytes();
    }

    public synchronized boolean isEmpty() {
        return this.elementCount == 0;
    }

    private void expandIfNecessary(int dataLength) throws IOException {
        int newLength;
        int elementLength = dataLength + 4;
        int remainingBytes = remainingBytes();
        if (remainingBytes < elementLength) {
            int previousLength = this.fileLength;
            do {
                remainingBytes += previousLength;
                newLength = previousLength << 1;
                previousLength = newLength;
            } while (remainingBytes < elementLength);
            setLength(newLength);
            int endOfLastElement = wrapPosition(this.last.position + 4 + this.last.length);
            if (endOfLastElement < this.first.position) {
                FileChannel channel = this.raf.getChannel();
                channel.position(this.fileLength);
                int count = endOfLastElement - 4;
                if (channel.transferTo(16L, count, channel) != count) {
                    throw new AssertionError("Copied insufficient number of bytes!");
                }
            }
            if (this.last.position < this.first.position) {
                int newLastPosition = (this.fileLength + this.last.position) - 16;
                writeHeader(newLength, this.elementCount, this.first.position, newLastPosition);
                this.last = new Element(newLastPosition, this.last.length);
            } else {
                writeHeader(newLength, this.elementCount, this.first.position, this.last.position);
            }
            this.fileLength = newLength;
        }
    }

    private void setLength(int newLength) throws IOException {
        this.raf.setLength(newLength);
        this.raf.getChannel().force(true);
    }

    public synchronized byte[] peek() throws IOException {
        if (isEmpty()) {
            return null;
        }
        int length = this.first.length;
        byte[] data = new byte[length];
        ringRead(this.first.position + 4, data, 0, length);
        return data;
    }

    public synchronized void peek(ElementReader reader) throws IOException {
        if (this.elementCount > 0) {
            reader.read(new ElementInputStream(this.first), this.first.length);
        }
    }

    public synchronized void forEach(ElementReader reader) throws IOException {
        int position = this.first.position;
        for (int i = 0; i < this.elementCount; i++) {
            Element current = readElement(position);
            reader.read(new ElementInputStream(current), current.length);
            position = wrapPosition(current.position + 4 + current.length);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T nonNull(T t, String name) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(name);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public final class ElementInputStream extends InputStream {
        private int position;
        private int remaining;

        private ElementInputStream(Element element) {
            this.position = QueueFile.this.wrapPosition(element.position + 4);
            this.remaining = element.length;
        }

        @Override // java.io.InputStream
        public int read(byte[] buffer, int offset, int length) throws IOException {
            QueueFile.nonNull(buffer, "buffer");
            if ((offset | length) < 0 || length > buffer.length - offset) {
                throw new ArrayIndexOutOfBoundsException();
            }
            int i = this.remaining;
            if (i <= 0) {
                return -1;
            }
            if (length > i) {
                length = this.remaining;
            }
            QueueFile.this.ringRead(this.position, buffer, offset, length);
            this.position = QueueFile.this.wrapPosition(this.position + length);
            this.remaining -= length;
            return length;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            if (this.remaining == 0) {
                return -1;
            }
            QueueFile.this.raf.seek(this.position);
            int b = QueueFile.this.raf.read();
            this.position = QueueFile.this.wrapPosition(this.position + 1);
            this.remaining--;
            return b;
        }
    }

    public synchronized int size() {
        return this.elementCount;
    }

    public synchronized void remove() throws IOException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else if (this.elementCount == 1) {
            clear();
        } else {
            int newFirstPosition = wrapPosition(this.first.position + 4 + this.first.length);
            ringRead(newFirstPosition, this.buffer, 0, 4);
            int length = readInt(this.buffer, 0);
            writeHeader(this.fileLength, this.elementCount - 1, newFirstPosition, this.last.position);
            this.elementCount--;
            this.first = new Element(newFirstPosition, length);
        }
    }

    public synchronized void clear() throws IOException {
        writeHeader(4096, 0, 0, 0);
        this.elementCount = 0;
        this.first = Element.NULL;
        this.last = Element.NULL;
        if (this.fileLength > 4096) {
            setLength(4096);
        }
        this.fileLength = 4096;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.raf.close();
    }

    public boolean hasSpaceFor(int dataSizeBytes, int maxSizeBytes) {
        return (usedBytes() + 4) + dataSizeBytes <= maxSizeBytes;
    }

    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append('[');
        builder.append("fileLength=");
        builder.append(this.fileLength);
        builder.append(", size=");
        builder.append(this.elementCount);
        builder.append(", first=");
        builder.append(this.first);
        builder.append(", last=");
        builder.append(this.last);
        builder.append(", element lengths=[");
        try {
            forEach(new ElementReader() { // from class: com.google.firebase.crashlytics.internal.log.QueueFile.1
                boolean first = true;

                @Override // com.google.firebase.crashlytics.internal.log.QueueFile.ElementReader
                public void read(InputStream in, int length) throws IOException {
                    if (this.first) {
                        this.first = false;
                    } else {
                        builder.append(", ");
                    }
                    builder.append(length);
                }
            });
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "read error", (Throwable) e);
        }
        builder.append("]]");
        return builder.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class Element {
        static final int HEADER_LENGTH = 4;
        static final Element NULL = new Element(0, 0);
        final int length;
        final int position;

        Element(int position, int length) {
            this.position = position;
            this.length = length;
        }

        public String toString() {
            return getClass().getSimpleName() + "[position = " + this.position + ", length = " + this.length + "]";
        }
    }
}
