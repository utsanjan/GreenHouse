package com.google.firebase.crashlytics.internal.report;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.report.ReportUploader;
import com.google.firebase.crashlytics.internal.report.model.NativeSessionReport;
import com.google.firebase.crashlytics.internal.report.model.Report;
import com.google.firebase.crashlytics.internal.report.model.SessionReport;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes.dex */
public class ReportManager {
    private final ReportUploader.ReportFilesProvider reportFilesProvider;

    public ReportManager(ReportUploader.ReportFilesProvider reportFilesProvider) {
        this.reportFilesProvider = reportFilesProvider;
    }

    public boolean areReportsAvailable() {
        File[] clsFiles = this.reportFilesProvider.getCompleteSessionFiles();
        File[] nativeReportFiles = this.reportFilesProvider.getNativeReportFiles();
        if (clsFiles != null && clsFiles.length > 0) {
            return true;
        }
        if (nativeReportFiles == null || nativeReportFiles.length <= 0) {
            return false;
        }
        return true;
    }

    public List<Report> findReports() {
        Logger.getLogger().d("Checking for crash reports...");
        File[] clsFiles = this.reportFilesProvider.getCompleteSessionFiles();
        File[] nativeReportFiles = this.reportFilesProvider.getNativeReportFiles();
        List<Report> reports = new LinkedList<>();
        if (clsFiles != null) {
            for (File file : clsFiles) {
                Logger.getLogger().d("Found crash report " + file.getPath());
                reports.add(new SessionReport(file));
            }
        }
        if (nativeReportFiles != null) {
            for (File dir : nativeReportFiles) {
                reports.add(new NativeSessionReport(dir));
            }
        }
        if (reports.isEmpty()) {
            Logger.getLogger().d("No reports found.");
        }
        return reports;
    }

    public void deleteReport(Report report) {
        report.remove();
    }

    public void deleteReports(List<Report> reports) {
        for (Report report : reports) {
            deleteReport(report);
        }
    }
}
