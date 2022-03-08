package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPOutputStream;

/* loaded from: classes.dex */
class BytesBackedNativeSessionFile implements NativeSessionFile {
    private final byte[] bytes;
    private final String dataTransportFilename;
    private final String reportsEndpointFilename;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BytesBackedNativeSessionFile(String dataTransportFilename, String reportsEndpointFilename, byte[] bytes) {
        this.dataTransportFilename = dataTransportFilename;
        this.reportsEndpointFilename = reportsEndpointFilename;
        this.bytes = bytes;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public String getReportsEndpointFilename() {
        return this.reportsEndpointFilename;
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public InputStream getStream() {
        if (isEmpty()) {
            return null;
        }
        return new ByteArrayInputStream(this.bytes);
    }

    @Override // com.google.firebase.crashlytics.internal.common.NativeSessionFile
    public CrashlyticsReport.FilesPayload.File asFilePayload() {
        byte[] gzippedBytes = asGzippedBytes();
        if (gzippedBytes == null) {
            return null;
        }
        return CrashlyticsReport.FilesPayload.File.builder().setContents(gzippedBytes).setFilename(this.dataTransportFilename).build();
    }

    private boolean isEmpty() {
        byte[] bArr = this.bytes;
        return bArr == null || bArr.length == 0;
    }

    private byte[] asGzippedBytes() {
        if (isEmpty()) {
            return null;
        }
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gos = new GZIPOutputStream(bos);
            try {
                gos.write(this.bytes);
                gos.finish();
                byte[] byteArray = bos.toByteArray();
                gos.close();
                bos.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    gos.close();
                } catch (Throwable th2) {
                }
                throw th;
            }
        } catch (IOException e) {
            return null;
        }
    }
}
