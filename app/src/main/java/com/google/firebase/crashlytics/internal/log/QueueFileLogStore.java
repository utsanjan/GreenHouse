package com.google.firebase.crashlytics.internal.log;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.log.QueueFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Locale;

/* loaded from: classes.dex */
class QueueFileLogStore implements FileLogStore {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private QueueFile logFile;
    private final int maxLogSize;
    private final File workingFile;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class LogBytes {
        public final byte[] bytes;
        public final int offset;

        LogBytes(byte[] bytes, int offset) {
            this.bytes = bytes;
            this.offset = offset;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public QueueFileLogStore(File workingFile, int maxLogSize) {
        this.workingFile = workingFile;
        this.maxLogSize = maxLogSize;
    }

    @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
    public void writeToLog(long timestamp, String msg) {
        openLogFile();
        doWriteToLog(timestamp, msg);
    }

    @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
    public byte[] getLogAsBytes() {
        LogBytes logBytes = getLogBytes();
        if (logBytes == null) {
            return null;
        }
        byte[] rawBytes = new byte[logBytes.offset];
        System.arraycopy(logBytes.bytes, 0, rawBytes, 0, logBytes.offset);
        return rawBytes;
    }

    @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
    public String getLogAsString() {
        byte[] logBytes = getLogAsBytes();
        if (logBytes != null) {
            return new String(logBytes, UTF_8);
        }
        return null;
    }

    private LogBytes getLogBytes() {
        if (!this.workingFile.exists()) {
            return null;
        }
        openLogFile();
        QueueFile queueFile = this.logFile;
        if (queueFile == null) {
            return null;
        }
        final int[] offsetHolder = {0};
        final byte[] logBytes = new byte[queueFile.usedBytes()];
        try {
            this.logFile.forEach(new QueueFile.ElementReader() { // from class: com.google.firebase.crashlytics.internal.log.QueueFileLogStore.1
                @Override // com.google.firebase.crashlytics.internal.log.QueueFile.ElementReader
                public void read(InputStream in, int length) throws IOException {
                    try {
                        in.read(logBytes, offsetHolder[0], length);
                        int[] iArr = offsetHolder;
                        iArr[0] = iArr[0] + length;
                    } finally {
                        in.close();
                    }
                }
            });
        } catch (IOException e) {
            Logger.getLogger().e("A problem occurred while reading the Crashlytics log file.", e);
        }
        return new LogBytes(logBytes, offsetHolder[0]);
    }

    @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
    public void closeLogFile() {
        CommonUtils.closeOrLog(this.logFile, "There was a problem closing the Crashlytics log file.");
        this.logFile = null;
    }

    @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
    public void deleteLogFile() {
        closeLogFile();
        this.workingFile.delete();
    }

    private void openLogFile() {
        if (this.logFile == null) {
            try {
                this.logFile = new QueueFile(this.workingFile);
            } catch (IOException e) {
                Logger logger = Logger.getLogger();
                logger.e("Could not open log file: " + this.workingFile, e);
            }
        }
    }

    private void doWriteToLog(long timestamp, String msg) {
        if (this.logFile != null) {
            if (msg == null) {
                msg = "null";
            }
            try {
                int quarterMaxLogSize = this.maxLogSize / 4;
                if (msg.length() > quarterMaxLogSize) {
                    msg = "..." + msg.substring(msg.length() - quarterMaxLogSize);
                }
                byte[] msgBytes = String.format(Locale.US, "%d %s%n", Long.valueOf(timestamp), msg.replaceAll("\r", " ").replaceAll("\n", " ")).getBytes(UTF_8);
                this.logFile.add(msgBytes);
                while (!this.logFile.isEmpty() && this.logFile.usedBytes() > this.maxLogSize) {
                    this.logFile.remove();
                }
            } catch (IOException e) {
                Logger.getLogger().e("There was a problem writing to the Crashlytics log.", e);
            }
        }
    }
}
