package com.google.firebase.crashlytics.internal.log;

import android.content.Context;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import java.io.File;
import java.util.Set;

/* loaded from: classes.dex */
public class LogFileManager {
    private static final String COLLECT_CUSTOM_LOGS = "com.crashlytics.CollectCustomLogs";
    private static final String LOGFILE_EXT = ".temp";
    private static final String LOGFILE_PREFIX = "crashlytics-userlog-";
    static final int MAX_LOG_SIZE = 65536;
    private static final NoopLogStore NOOP_LOG_STORE = new NoopLogStore();
    private final Context context;
    private FileLogStore currentLog;
    private final DirectoryProvider directoryProvider;

    /* loaded from: classes.dex */
    public interface DirectoryProvider {
        File getLogFileDir();
    }

    public LogFileManager(Context context, DirectoryProvider directoryProvider) {
        this(context, directoryProvider, null);
    }

    public LogFileManager(Context context, DirectoryProvider directoryProvider, String currentSessionId) {
        this.context = context;
        this.directoryProvider = directoryProvider;
        this.currentLog = NOOP_LOG_STORE;
        setCurrentSession(currentSessionId);
    }

    public final void setCurrentSession(String sessionId) {
        this.currentLog.closeLogFile();
        this.currentLog = NOOP_LOG_STORE;
        if (sessionId != null) {
            boolean isLoggingEnabled = CommonUtils.getBooleanResourceValue(this.context, COLLECT_CUSTOM_LOGS, true);
            if (!isLoggingEnabled) {
                Logger.getLogger().d("Preferences requested no custom logs. Aborting log file creation.");
            } else {
                setLogFile(getWorkingFileForSession(sessionId), 65536);
            }
        }
    }

    public void writeToLog(long timestamp, String msg) {
        this.currentLog.writeToLog(timestamp, msg);
    }

    public byte[] getBytesForLog() {
        return this.currentLog.getLogAsBytes();
    }

    public String getLogString() {
        return this.currentLog.getLogAsString();
    }

    public void clearLog() {
        this.currentLog.deleteLogFile();
    }

    public void discardOldLogFiles(Set<String> sessionIdsToKeep) {
        File[] logFiles = this.directoryProvider.getLogFileDir().listFiles();
        if (logFiles != null) {
            for (File file : logFiles) {
                if (!sessionIdsToKeep.contains(getSessionIdForFile(file))) {
                    file.delete();
                }
            }
        }
    }

    void setLogFile(File workingFile, int maxLogSize) {
        this.currentLog = new QueueFileLogStore(workingFile, maxLogSize);
    }

    private File getWorkingFileForSession(String sessionId) {
        String fileName = LOGFILE_PREFIX + sessionId + LOGFILE_EXT;
        return new File(this.directoryProvider.getLogFileDir(), fileName);
    }

    private String getSessionIdForFile(File workingFile) {
        String filename = workingFile.getName();
        int indexOfExtension = filename.lastIndexOf(LOGFILE_EXT);
        if (indexOfExtension == -1) {
            return filename;
        }
        return filename.substring(LOGFILE_PREFIX.length(), indexOfExtension);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class NoopLogStore implements FileLogStore {
        private NoopLogStore() {
        }

        @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
        public void writeToLog(long timestamp, String msg) {
        }

        @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
        public byte[] getLogAsBytes() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
        public String getLogAsString() {
            return null;
        }

        @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
        public void closeLogFile() {
        }

        @Override // com.google.firebase.crashlytics.internal.log.FileLogStore
        public void deleteLogFile() {
        }
    }
}
