package pl.droidsonroids.relinker;

import android.os.Build;
import pl.droidsonroids.relinker.ReLinker;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class SystemLibraryLoader implements ReLinker.LibraryLoader {
    @Override // pl.droidsonroids.relinker.ReLinker.LibraryLoader
    public void loadLibrary(String libraryName) {
        System.loadLibrary(libraryName);
    }

    @Override // pl.droidsonroids.relinker.ReLinker.LibraryLoader
    public void loadPath(String libraryPath) {
        System.load(libraryPath);
    }

    @Override // pl.droidsonroids.relinker.ReLinker.LibraryLoader
    public String mapLibraryName(String libraryName) {
        if (!libraryName.startsWith("lib") || !libraryName.endsWith(".so")) {
            return System.mapLibraryName(libraryName);
        }
        return libraryName;
    }

    @Override // pl.droidsonroids.relinker.ReLinker.LibraryLoader
    public String unmapLibraryName(String mappedLibraryName) {
        return mappedLibraryName.substring(3, mappedLibraryName.length() - 3);
    }

    @Override // pl.droidsonroids.relinker.ReLinker.LibraryLoader
    public String[] supportedAbis() {
        if (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) {
            return !TextUtils.isEmpty(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI};
        }
        return Build.SUPPORTED_ABIS;
    }
}
