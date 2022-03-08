package com.google.firebase.crashlytics.internal.persistence;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.CrashlyticsReportWithSessionId;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.model.ImmutableList;
import com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform;
import com.google.firebase.crashlytics.internal.settings.SettingsDataProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class CrashlyticsReportPersistence {
    private static final String EVENT_COUNTER_FORMAT = "%010d";
    private static final int EVENT_COUNTER_WIDTH = 10;
    private static final FilenameFilter EVENT_FILE_FILTER;
    private static final String EVENT_FILE_NAME_PREFIX = "event";
    private static final Comparator<? super File> LATEST_SESSION_ID_FIRST_COMPARATOR;
    private static final int MAX_OPEN_SESSIONS = 8;
    private static final String NATIVE_REPORTS_DIRECTORY = "native-reports";
    private static final String NORMAL_EVENT_SUFFIX = "";
    private static final String OPEN_SESSIONS_DIRECTORY_NAME = "sessions";
    private static final String PRIORITY_EVENT_SUFFIX = "_";
    private static final String PRIORITY_REPORTS_DIRECTORY = "priority-reports";
    private static final String REPORTS_DIRECTORY = "reports";
    private static final String REPORT_FILE_NAME = "report";
    private static final String USER_FILE_NAME = "user";
    private static final String WORKING_DIRECTORY_NAME = "report-persistence";
    private final AtomicInteger eventCounter = new AtomicInteger(0);
    private final File nativeReportsDirectory;
    private final File openSessionsDirectory;
    private final File priorityReportsDirectory;
    private final File reportsDirectory;
    private final SettingsDataProvider settingsDataProvider;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final int EVENT_NAME_LENGTH = "event".length() + 10;
    private static final CrashlyticsReportJsonTransform TRANSFORM = new CrashlyticsReportJsonTransform();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean access$lambda$0(File file, String str) {
        return isNormalPriorityEventFile(file, str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int access$lambda$1(File file, File file2) {
        return oldestEventFileFirst(file, file2);
    }

    static {
        Comparator<? super File> comparator;
        FilenameFilter filenameFilter;
        comparator = CrashlyticsReportPersistence$$Lambda$5.instance;
        LATEST_SESSION_ID_FIRST_COMPARATOR = comparator;
        filenameFilter = CrashlyticsReportPersistence$$Lambda$6.instance;
        EVENT_FILE_FILTER = filenameFilter;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$0(File f1, File f2) {
        return f2.getName().compareTo(f1.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$1(File f, String name) {
        return name.startsWith("event");
    }

    public CrashlyticsReportPersistence(File rootDirectory, SettingsDataProvider settingsDataProvider) {
        File workingDirectory = new File(rootDirectory, WORKING_DIRECTORY_NAME);
        this.openSessionsDirectory = new File(workingDirectory, OPEN_SESSIONS_DIRECTORY_NAME);
        this.priorityReportsDirectory = new File(workingDirectory, PRIORITY_REPORTS_DIRECTORY);
        this.reportsDirectory = new File(workingDirectory, REPORTS_DIRECTORY);
        this.nativeReportsDirectory = new File(workingDirectory, NATIVE_REPORTS_DIRECTORY);
        this.settingsDataProvider = settingsDataProvider;
    }

    public void persistReport(CrashlyticsReport report) {
        CrashlyticsReport.Session session = report.getSession();
        if (session == null) {
            Logger.getLogger().d("Could not get session for report");
            return;
        }
        String sessionId = session.getIdentifier();
        try {
            File sessionDirectory = prepareDirectory(getSessionDirectoryById(sessionId));
            String json = TRANSFORM.reportToJson(report);
            writeTextFile(new File(sessionDirectory, REPORT_FILE_NAME), json);
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.d("Could not persist report for session " + sessionId, e);
        }
    }

    public void persistEvent(CrashlyticsReport.Session.Event event, String sessionId) {
        persistEvent(event, sessionId, false);
    }

    public void persistEvent(CrashlyticsReport.Session.Event event, String sessionId, boolean isHighPriority) {
        int maxEventsToKeep = this.settingsDataProvider.getSettings().getSessionData().maxCustomExceptionEvents;
        File sessionDirectory = getSessionDirectoryById(sessionId);
        String json = TRANSFORM.eventToJson(event);
        String fileName = generateEventFilename(this.eventCounter.getAndIncrement(), isHighPriority);
        try {
            writeTextFile(new File(sessionDirectory, fileName), json);
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.d("Could not persist event for session " + sessionId, e);
        }
        trimEvents(sessionDirectory, maxEventsToKeep);
    }

    public void persistUserIdForSession(String userId, String sessionId) {
        File sessionDirectory = getSessionDirectoryById(sessionId);
        try {
            writeTextFile(new File(sessionDirectory, USER_FILE_NAME), userId);
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.d("Could not persist user ID for session " + sessionId, e);
        }
    }

    public void deleteAllReports() {
        for (File reportFile : getAllFinalizedReportFiles()) {
            reportFile.delete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$deleteFinalizedReport$2(String sessionId, File d, String f) {
        return f.startsWith(sessionId);
    }

    public void deleteFinalizedReport(String sessionId) {
        FilenameFilter filter = CrashlyticsReportPersistence$$Lambda$1.lambdaFactory$(sessionId);
        List<File> filteredReports = combineReportFiles(getFilesInDirectory(this.priorityReportsDirectory, filter), getFilesInDirectory(this.nativeReportsDirectory, filter), getFilesInDirectory(this.reportsDirectory, filter));
        for (File reportFile : filteredReports) {
            reportFile.delete();
        }
    }

    public void finalizeReports(String currentSessionId, long sessionEndTime) {
        List<File> sessionDirectories = capAndGetOpenSessions(currentSessionId);
        for (File sessionDirectory : sessionDirectories) {
            synthesizeReport(sessionDirectory, sessionEndTime);
            recursiveDelete(sessionDirectory);
        }
        capFinalizedReports();
    }

    public void finalizeSessionWithNativeEvent(String previousSessionId, CrashlyticsReport.FilesPayload ndkPayload) {
        File reportFile = new File(getSessionDirectoryById(previousSessionId), REPORT_FILE_NAME);
        synthesizeNativeReportFile(reportFile, this.nativeReportsDirectory, ndkPayload, previousSessionId);
    }

    public List<CrashlyticsReportWithSessionId> loadFinalizedReports() {
        List<File> allReportFiles = getAllFinalizedReportFiles();
        ArrayList<CrashlyticsReportWithSessionId> allReports = new ArrayList<>();
        allReports.ensureCapacity(allReportFiles.size());
        for (File reportFile : getAllFinalizedReportFiles()) {
            try {
                CrashlyticsReport jsonReport = TRANSFORM.reportFromJson(readTextFile(reportFile));
                allReports.add(CrashlyticsReportWithSessionId.create(jsonReport, reportFile.getName()));
            } catch (IOException e) {
                Logger logger = Logger.getLogger();
                logger.d("Could not load report file " + reportFile + "; deleting", e);
                reportFile.delete();
            }
        }
        return allReports;
    }

    private List<File> capAndGetOpenSessions(String currentSessionId) {
        FileFilter sessionDirectoryFilter = CrashlyticsReportPersistence$$Lambda$2.lambdaFactory$(currentSessionId);
        List<File> openSessionDirectories = getFilesInDirectory(this.openSessionsDirectory, sessionDirectoryFilter);
        Collections.sort(openSessionDirectories, LATEST_SESSION_ID_FIRST_COMPARATOR);
        if (openSessionDirectories.size() <= 8) {
            return openSessionDirectories;
        }
        List<File> openSessionDirectoriesToRemove = openSessionDirectories.subList(8, openSessionDirectories.size());
        for (File openSessionDirectory : openSessionDirectoriesToRemove) {
            recursiveDelete(openSessionDirectory);
        }
        return openSessionDirectories.subList(0, 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$capAndGetOpenSessions$3(String currentSessionId, File f) {
        return f.isDirectory() && !f.getName().equals(currentSessionId);
    }

    private void capFinalizedReports() {
        int maxReportsToKeep = this.settingsDataProvider.getSettings().getSessionData().maxCompleteSessionsCount;
        List<File> finalizedReportFiles = getAllFinalizedReportFiles();
        int fileCount = finalizedReportFiles.size();
        if (fileCount > maxReportsToKeep) {
            List<File> filesToRemove = finalizedReportFiles.subList(maxReportsToKeep, fileCount);
            for (File reportFile : filesToRemove) {
                reportFile.delete();
            }
        }
    }

    private List<File> getAllFinalizedReportFiles() {
        return sortAndCombineReportFiles(combineReportFiles(getAllFilesInDirectory(this.priorityReportsDirectory), getAllFilesInDirectory(this.nativeReportsDirectory)), getAllFilesInDirectory(this.reportsDirectory));
    }

    private File getSessionDirectoryById(String sessionId) {
        return new File(this.openSessionsDirectory, sessionId);
    }

    /* JADX WARN: Incorrect condition in loop: B:7:0x001f */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void synthesizeReport(java.io.File r12, long r13) {
        /*
            r11 = this;
            java.io.FilenameFilter r0 = com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence.EVENT_FILE_FILTER
            java.util.List r0 = getFilesInDirectory(r12, r0)
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x000d
            return
        L_0x000d:
            java.util.Collections.sort(r0)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r2 = 0
            java.util.Iterator r3 = r0.iterator()
            r9 = r2
        L_0x001b:
            boolean r2 = r3.hasNext()
            if (r2 == 0) goto L_0x0060
            java.lang.Object r2 = r3.next()
            java.io.File r2 = (java.io.File) r2
            com.google.firebase.crashlytics.internal.model.serialization.CrashlyticsReportJsonTransform r4 = com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence.TRANSFORM     // Catch: IOException -> 0x0046
            java.lang.String r5 = readTextFile(r2)     // Catch: IOException -> 0x0046
            com.google.firebase.crashlytics.internal.model.CrashlyticsReport$Session$Event r4 = r4.eventFromJson(r5)     // Catch: IOException -> 0x0046
            r1.add(r4)     // Catch: IOException -> 0x0046
            if (r9 != 0) goto L_0x0043
            java.lang.String r4 = r2.getName()     // Catch: IOException -> 0x0046
            boolean r4 = isHighPriorityEventFile(r4)     // Catch: IOException -> 0x0046
            if (r4 == 0) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r4 = 0
            goto L_0x0044
        L_0x0043:
            r4 = 1
        L_0x0044:
            r9 = r4
            goto L_0x005f
        L_0x0046:
            r4 = move-exception
            com.google.firebase.crashlytics.internal.Logger r5 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Could not add event to report for "
            r6.append(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            r5.d(r6, r4)
        L_0x005f:
            goto L_0x001b
        L_0x0060:
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch: IOException -> 0x006f
            java.lang.String r4 = "user"
            r3.<init>(r12, r4)     // Catch: IOException -> 0x006f
            java.lang.String r3 = readTextFile(r3)     // Catch: IOException -> 0x006f
            r2 = r3
            r10 = r2
            goto L_0x008d
        L_0x006f:
            r3 = move-exception
            com.google.firebase.crashlytics.internal.Logger r4 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Could not read user ID file in "
            r5.append(r6)
            java.lang.String r6 = r12.getName()
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.d(r5, r3)
            r10 = r2
        L_0x008d:
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "report"
            r2.<init>(r12, r3)
            if (r9 == 0) goto L_0x0099
            java.io.File r3 = r11.priorityReportsDirectory
            goto L_0x009b
        L_0x0099:
            java.io.File r3 = r11.reportsDirectory
        L_0x009b:
            r4 = r1
            r5 = r13
            r7 = r9
            r8 = r10
            synthesizeReportFile(r2, r3, r4, r5, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.persistence.CrashlyticsReportPersistence.synthesizeReport(java.io.File, long):void");
    }

    private static void synthesizeNativeReportFile(File reportFile, File outputDirectory, CrashlyticsReport.FilesPayload ndkPayload, String previousSessionId) {
        try {
            CrashlyticsReport report = TRANSFORM.reportFromJson(readTextFile(reportFile)).withNdkPayload(ndkPayload);
            writeTextFile(new File(prepareDirectory(outputDirectory), previousSessionId), TRANSFORM.reportToJson(report));
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.d("Could not synthesize final native report file for " + reportFile, e);
        }
    }

    private static void synthesizeReportFile(File reportFile, File outputDirectory, List<CrashlyticsReport.Session.Event> events, long sessionEndTime, boolean isCrashed, String userId) {
        try {
            CrashlyticsReport report = TRANSFORM.reportFromJson(readTextFile(reportFile)).withSessionEndFields(sessionEndTime, isCrashed, userId).withEvents(ImmutableList.from(events));
            CrashlyticsReport.Session session = report.getSession();
            if (session != null) {
                writeTextFile(new File(prepareDirectory(outputDirectory), session.getIdentifier()), TRANSFORM.reportToJson(report));
            }
        } catch (IOException e) {
            Logger logger = Logger.getLogger();
            logger.d("Could not synthesize final report file for " + reportFile, e);
        }
    }

    private static List<File> sortAndCombineReportFiles(List<File>... reports) {
        for (List<File> reportList : reports) {
            Collections.sort(reportList, LATEST_SESSION_ID_FIRST_COMPARATOR);
        }
        return combineReportFiles(reports);
    }

    private static List<File> combineReportFiles(List<File>... reports) {
        ArrayList<File> allReportsFiles = new ArrayList<>();
        int totalReports = 0;
        for (List<File> reportList : reports) {
            totalReports += reportList.size();
        }
        allReportsFiles.ensureCapacity(totalReports);
        for (List<File> reportList2 : reports) {
            allReportsFiles.addAll(reportList2);
        }
        return allReportsFiles;
    }

    private static boolean isHighPriorityEventFile(String fileName) {
        return fileName.startsWith("event") && fileName.endsWith(PRIORITY_EVENT_SUFFIX);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isNormalPriorityEventFile(File dir, String name) {
        return name.startsWith("event") && !name.endsWith(PRIORITY_EVENT_SUFFIX);
    }

    private static String generateEventFilename(int eventNumber, boolean isHighPriority) {
        String paddedEventNumber = String.format(Locale.US, EVENT_COUNTER_FORMAT, Integer.valueOf(eventNumber));
        String prioritySuffix = isHighPriority ? PRIORITY_EVENT_SUFFIX : "";
        return "event" + paddedEventNumber + prioritySuffix;
    }

    private static int trimEvents(File sessionDirectory, int maximum) {
        FilenameFilter filenameFilter;
        Comparator comparator;
        filenameFilter = CrashlyticsReportPersistence$$Lambda$3.instance;
        List<File> normalPriorityEventFiles = getFilesInDirectory(sessionDirectory, filenameFilter);
        comparator = CrashlyticsReportPersistence$$Lambda$4.instance;
        Collections.sort(normalPriorityEventFiles, comparator);
        return capFilesCount(normalPriorityEventFiles, maximum);
    }

    private static String getEventNameWithoutPriority(String eventFileName) {
        return eventFileName.substring(0, EVENT_NAME_LENGTH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int oldestEventFileFirst(File f1, File f2) {
        String name1 = getEventNameWithoutPriority(f1.getName());
        String name2 = getEventNameWithoutPriority(f2.getName());
        return name1.compareTo(name2);
    }

    private static List<File> getAllFilesInDirectory(File directory) {
        return getFilesInDirectory(directory, (FileFilter) null);
    }

    private static List<File> getFilesInDirectory(File directory, FilenameFilter filter) {
        if (!directory.isDirectory()) {
            return Collections.emptyList();
        }
        File[] files = filter == null ? directory.listFiles() : directory.listFiles(filter);
        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    private static List<File> getFilesInDirectory(File directory, FileFilter filter) {
        if (!directory.isDirectory()) {
            return Collections.emptyList();
        }
        File[] files = filter == null ? directory.listFiles() : directory.listFiles(filter);
        return files != null ? Arrays.asList(files) : Collections.emptyList();
    }

    private static File prepareDirectory(File directory) throws IOException {
        if (makeDirectory(directory)) {
            return directory;
        }
        throw new IOException("Could not create directory " + directory);
    }

    private static boolean makeDirectory(File directory) {
        return directory.exists() || directory.mkdirs();
    }

    private static void writeTextFile(File file, String text) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), UTF_8);
        try {
            writer.write(text);
            writer.close();
        } catch (Throwable th) {
            try {
                writer.close();
            } catch (Throwable th2) {
            }
            throw th;
        }
    }

    private static String readTextFile(File file) throws IOException {
        byte[] readBuffer = new byte[8192];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        FileInputStream fileInput = new FileInputStream(file);
        while (true) {
            try {
                int read = fileInput.read(readBuffer);
                if (read > 0) {
                    bos.write(readBuffer, 0, read);
                } else {
                    String str = new String(bos.toByteArray(), UTF_8);
                    fileInput.close();
                    return str;
                }
            } catch (Throwable th) {
                try {
                    fileInput.close();
                } catch (Throwable th2) {
                }
                throw th;
            }
        }
    }

    private static int capFilesCount(List<File> files, int maximum) {
        int numRetained = files.size();
        for (File f : files) {
            if (numRetained <= maximum) {
                return numRetained;
            }
            recursiveDelete(f);
            numRetained--;
        }
        return numRetained;
    }

    private static void recursiveDelete(File file) {
        File[] listFiles;
        if (file != null) {
            if (file.isDirectory()) {
                for (File f : file.listFiles()) {
                    recursiveDelete(f);
                }
            }
            file.delete();
        }
    }
}
