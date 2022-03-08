package okhttp3.internal.cache2;

import java.io.IOException;
import java.nio.channels.FileChannel;
import okio.Buffer;

/* loaded from: classes.dex */
final class FileOperator {
    private final FileChannel fileChannel;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileOperator(FileChannel fileChannel) {
        this.fileChannel = fileChannel;
    }

    public void write(long pos, Buffer source, long byteCount) throws IOException {
        if (byteCount < 0 || byteCount > source.size()) {
            throw new IndexOutOfBoundsException();
        }
        long pos2 = pos;
        long byteCount2 = byteCount;
        while (byteCount2 > 0) {
            long bytesWritten = this.fileChannel.transferFrom(source, pos2, byteCount2);
            pos2 += bytesWritten;
            byteCount2 -= bytesWritten;
        }
    }

    public void read(long pos, Buffer sink, long byteCount) throws IOException {
        if (byteCount >= 0) {
            while (byteCount > 0) {
                long bytesRead = this.fileChannel.transferTo(pos, byteCount, sink);
                pos += bytesRead;
                byteCount -= bytesRead;
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
