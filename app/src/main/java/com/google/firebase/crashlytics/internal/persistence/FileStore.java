package com.google.firebase.crashlytics.internal.persistence;

import java.io.File;

/* loaded from: classes.dex */
public interface FileStore {
    File getFilesDir();

    String getFilesDirPath();
}
