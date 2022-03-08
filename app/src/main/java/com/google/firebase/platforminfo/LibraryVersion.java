package com.google.firebase.platforminfo;

import javax.annotation.Nonnull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes.dex */
public abstract class LibraryVersion {
    @Nonnull
    public abstract String getLibraryName();

    @Nonnull
    public abstract String getVersion();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LibraryVersion create(String name, String version) {
        return new AutoValue_LibraryVersion(name, version);
    }
}
