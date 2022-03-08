package com.google.firebase.crashlytics.internal.report.model;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.report.model.Report;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class SessionReport implements Report {
    private final Map<String, String> customHeaders;
    private final File file;
    private final File[] files;

    public SessionReport(File file) {
        this(file, Collections.emptyMap());
    }

    public SessionReport(File file, Map<String, String> customHeaders) {
        this.file = file;
        this.files = new File[]{file};
        this.customHeaders = new HashMap(customHeaders);
    }

    @Override // com.google.firebase.crashlytics.internal.report.model.Report
    public File getFile() {
        return this.file;
    }

    @Override // com.google.firebase.crashlytics.internal.report.model.Report
    public File[] getFiles() {
        return this.files;
    }

    @Override // com.google.firebase.crashlytics.internal.report.model.Report
    public String getFileName() {
        return getFile().getName();
    }

    @Override // com.google.firebase.crashlytics.internal.report.model.Report
    public String getIdentifier() {
        String fileName = getFileName();
        return fileName.substring(0, fileName.lastIndexOf(46));
    }

    @Override // com.google.firebase.crashlytics.internal.report.model.Report
    public Map<String, String> getCustomHeaders() {
        return Collections.unmodifiableMap(this.customHeaders);
    }

    @Override // com.google.firebase.crashlytics.internal.report.model.Report
    public void remove() {
        Logger logger = Logger.getLogger();
        logger.d("Removing report at " + this.file.getPath());
        this.file.delete();
    }

    @Override // com.google.firebase.crashlytics.internal.report.model.Report
    public Report.Type getType() {
        return Report.Type.JAVA;
    }
}
