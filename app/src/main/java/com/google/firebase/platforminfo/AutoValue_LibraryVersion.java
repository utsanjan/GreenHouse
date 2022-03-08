package com.google.firebase.platforminfo;

import javax.annotation.Nonnull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes.dex */
public final class AutoValue_LibraryVersion extends LibraryVersion {
    private final String libraryName;
    private final String version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_LibraryVersion(String libraryName, String version) {
        if (libraryName != null) {
            this.libraryName = libraryName;
            if (version != null) {
                this.version = version;
                return;
            }
            throw new NullPointerException("Null version");
        }
        throw new NullPointerException("Null libraryName");
    }

    @Override // com.google.firebase.platforminfo.LibraryVersion
    @Nonnull
    public String getLibraryName() {
        return this.libraryName;
    }

    @Override // com.google.firebase.platforminfo.LibraryVersion
    @Nonnull
    public String getVersion() {
        return this.version;
    }

    public String toString() {
        return "LibraryVersion{libraryName=" + this.libraryName + ", version=" + this.version + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LibraryVersion)) {
            return false;
        }
        LibraryVersion that = (LibraryVersion) o;
        return this.libraryName.equals(that.getLibraryName()) && this.version.equals(that.getVersion());
    }

    public int hashCode() {
        int h$ = 1 * 1000003;
        return ((h$ ^ this.libraryName.hashCode()) * 1000003) ^ this.version.hashCode();
    }
}
