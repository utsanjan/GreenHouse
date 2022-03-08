package com.google.firebase.crashlytics.internal.report;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.BackgroundPriorityRunnable;
import com.google.firebase.crashlytics.internal.common.DataTransportState;
import com.google.firebase.crashlytics.internal.report.model.CreateReportRequest;
import com.google.firebase.crashlytics.internal.report.model.Report;
import com.google.firebase.crashlytics.internal.report.network.CreateReportSpiCall;
import com.google.firebase.crashlytics.internal.settings.model.AppSettingsData;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class ReportUploader {
    private static final short[] RETRY_INTERVALS = {10, 20, 30, 60, 120, 300};
    private final CreateReportSpiCall createReportCall;
    private final DataTransportState dataTransportState;
    private final String googleAppId;
    private final HandlingExceptionCheck handlingExceptionCheck;
    private final String organizationId;
    private final ReportManager reportManager;
    private Thread uploadThread;

    /* loaded from: classes.dex */
    public interface HandlingExceptionCheck {
        boolean isHandlingException();
    }

    /* loaded from: classes.dex */
    public interface Provider {
        ReportUploader createReportUploader(AppSettingsData appSettingsData);
    }

    /* loaded from: classes.dex */
    public interface ReportFilesProvider {
        File[] getCompleteSessionFiles();

        File[] getNativeReportFiles();
    }

    public ReportUploader(String organizationId, String googleAppId, DataTransportState dataTransportState, ReportManager reportManager, CreateReportSpiCall createReportCall, HandlingExceptionCheck handlingExceptionCheck) {
        if (createReportCall != null) {
            this.createReportCall = createReportCall;
            this.organizationId = organizationId;
            this.googleAppId = googleAppId;
            this.dataTransportState = dataTransportState;
            this.reportManager = reportManager;
            this.handlingExceptionCheck = handlingExceptionCheck;
            return;
        }
        throw new IllegalArgumentException("createReportCall must not be null.");
    }

    public synchronized void uploadReportsAsync(List<Report> reports, boolean dataCollectionToken, float delay) {
        if (this.uploadThread != null) {
            Logger.getLogger().d("Report upload has already been started.");
            return;
        }
        Worker uploadWorker = new Worker(reports, dataCollectionToken, delay);
        Thread thread = new Thread(uploadWorker, "Crashlytics Report Uploader");
        this.uploadThread = thread;
        thread.start();
    }

    boolean isUploading() {
        return this.uploadThread != null;
    }

    public boolean uploadReport(Report report, boolean dataCollectionToken) {
        try {
            CreateReportRequest requestData = new CreateReportRequest(this.organizationId, this.googleAppId, report);
            boolean shouldDeleteReport = true;
            if (this.dataTransportState == DataTransportState.ALL) {
                Logger.getLogger().d("Send to Reports Endpoint disabled. Removing Reports Endpoint report.");
            } else if (this.dataTransportState == DataTransportState.JAVA_ONLY && report.getType() == Report.Type.JAVA) {
                Logger.getLogger().d("Send to Reports Endpoint for non-native reports disabled. Removing Reports Uploader report.");
            } else {
                boolean sent = this.createReportCall.invoke(requestData, dataCollectionToken);
                Logger logger = Logger.getLogger();
                StringBuilder sb = new StringBuilder();
                sb.append("Crashlytics Reports Endpoint upload ");
                sb.append(sent ? "complete: " : "FAILED: ");
                sb.append(report.getIdentifier());
                logger.i(sb.toString());
                shouldDeleteReport = sent;
            }
            if (!shouldDeleteReport) {
                return false;
            }
            this.reportManager.deleteReport(report);
            return true;
        } catch (Exception e) {
            Logger logger2 = Logger.getLogger();
            logger2.e("Error occurred sending report " + report, e);
            return false;
        }
    }

    /* loaded from: classes.dex */
    private class Worker extends BackgroundPriorityRunnable {
        private final boolean dataCollectionToken;
        private final float delay;
        private final List<Report> reports;

        Worker(List<Report> reports, boolean dataCollectionToken, float delay) {
            this.reports = reports;
            this.dataCollectionToken = dataCollectionToken;
            this.delay = delay;
        }

        @Override // com.google.firebase.crashlytics.internal.common.BackgroundPriorityRunnable
        public void onRun() {
            try {
                attemptUploadWithRetry(this.reports, this.dataCollectionToken);
            } catch (Exception e) {
                Logger.getLogger().e("An unexpected error occurred while attempting to upload crash reports.", e);
            }
            ReportUploader.this.uploadThread = null;
        }

        private void attemptUploadWithRetry(List<Report> reports, boolean dataCollectionToken) {
            float f;
            Logger.getLogger().d("Starting report processing in " + this.delay + " second(s)...");
            if (this.delay > 0.0f) {
                try {
                    Thread.sleep(f * 1000.0f);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            if (!ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                int retryCount = 0;
                while (reports.size() > 0 && !ReportUploader.this.handlingExceptionCheck.isHandlingException()) {
                    Logger.getLogger().d("Attempting to send " + reports.size() + " report(s)");
                    ArrayList<Report> remaining = new ArrayList<>();
                    for (Report report : reports) {
                        boolean removed = ReportUploader.this.uploadReport(report, dataCollectionToken);
                        if (!removed) {
                            remaining.add(report);
                        }
                    }
                    reports = remaining;
                    if (reports.size() > 0) {
                        int retryCount2 = retryCount + 1;
                        long interval = ReportUploader.RETRY_INTERVALS[Math.min(retryCount, ReportUploader.RETRY_INTERVALS.length - 1)];
                        Logger.getLogger().d("Report submission: scheduling delayed retry in " + interval + " seconds");
                        try {
                            Thread.sleep(1000 * interval);
                            retryCount = retryCount2;
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
            }
        }
    }
}
