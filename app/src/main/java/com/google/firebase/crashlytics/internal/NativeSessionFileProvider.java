package com.google.firebase.crashlytics.internal;

import java.io.File;

/* loaded from: classes.dex */
public interface NativeSessionFileProvider {
    File getAppFile();

    File getBinaryImagesFile();

    File getDeviceFile();

    File getMetadataFile();

    File getMinidumpFile();

    File getOsFile();

    File getSessionFile();
}
