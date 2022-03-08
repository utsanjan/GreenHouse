package com.google.firebase.crashlytics.internal.ndk;

import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.ndk.BinaryImagesConverter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
class Sha1FileIdStrategy implements BinaryImagesConverter.FileIdStrategy {
    @Override // com.google.firebase.crashlytics.internal.ndk.BinaryImagesConverter.FileIdStrategy
    public String createId(File file) throws IOException {
        return getFileSHA(file.getPath());
    }

    private static String getFileSHA(String path) throws IOException {
        InputStream data = null;
        try {
            data = new BufferedInputStream(new FileInputStream(path));
            String sha = CommonUtils.sha1(data);
            return sha;
        } finally {
            CommonUtils.closeQuietly(data);
        }
    }
}
