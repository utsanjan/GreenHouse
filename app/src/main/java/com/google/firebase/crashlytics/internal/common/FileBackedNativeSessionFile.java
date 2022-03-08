package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
class FileBackedNativeSessionFile implements NativeSessionFile {
    private final String dataTransportFilename;
    private final File file;
    private final String reportsEndpointFilename;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FileBackedNativeSessionFile(String dataTransportFilename, String reportsEndpointFilename, File file) {
        this.dataTransportFilename = dataTransportFilename;
        this.reportsEndpointFilename = reportsEndpointFilename;
        this.file = file;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public InputStream getStream() {
        if (!this.file.exists() || !this.file.isFile()) {
            return null;
        }
        try {
            return new FileInputStream(this.file);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public CrashlyticsReport.FilesPayload.File asFilePayload() {
        byte[] bytes = asGzippedBytes();
        if (bytes != null) {
            return CrashlyticsReport.FilesPayload.File.builder().setContents(bytes).setFilename(this.dataTransportFilename).build();
        }
        return null;
    }

    private byte[] asGzippedBytes() {
        byte[] readBuffer = new byte[8192];
        try {
            InputStream stream = getStream();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                GZIPOutputStream gos = new GZIPOutputStream(bos);
                if (stream == null) {
                    gos.close();
                    bos.close();
                    if (stream != null) {
                        stream.close();
                    }
                    return null;
                }
                while (true) {
                    int read = stream.read(readBuffer);
                    if (read <= 0) {
                        break;
                    }
                    gos.write(readBuffer, 0, read);
                }
                gos.finish();
                byte[] byteArray = bos.toByteArray();
                gos.close();
                bos.close();
                if (stream != null) {
                    stream.close();
                }
                return byteArray;
            } catch (Throwable th) {
                try {
                    bos.close();
                } catch (Throwable th2) {
                }
                throw th;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
