package pl.droidsonroids.relinker;

import android.content.Context;
import java.io.File;

/* loaded from: classes.dex */
public class ReLinker {

    /* loaded from: classes.dex */
    public interface LibraryInstaller {
        void installLibrary(Context context, String[] strArr, String str, File file, ReLinkerInstance reLinkerInstance);
    }

    /* loaded from: classes.dex */
    public interface LibraryLoader {
        void loadLibrary(String str);

        void loadPath(String str);

        String mapLibraryName(String str);

        String[] supportedAbis();

        String unmapLibraryName(String str);
    }

    /* loaded from: classes.dex */
    public interface LoadListener {
        void failure(Throwable th);

        void success();
    }

    /* loaded from: classes.dex */
    public interface Logger {
        void log(String str);
    }

    public static void loadLibrary(Context context, String library) {
        loadLibrary(context, library, null, null);
    }

    public static void loadLibrary(Context context, String library, String version) {
        loadLibrary(context, library, version, null);
    }

    public static void loadLibrary(Context context, String library, LoadListener listener) {
        loadLibrary(context, library, null, listener);
    }

    public static void loadLibrary(Context context, String library, String version, LoadListener listener) {
        new ReLinkerInstance().loadLibrary(context, library, version, listener);
    }

    public static ReLinkerInstance force() {
        return new ReLinkerInstance().force();
    }

    public static ReLinkerInstance log(Logger logger) {
        return new ReLinkerInstance().log(logger);
    }

    public static ReLinkerInstance recursively() {
        return new ReLinkerInstance().recursively();
    }

    private ReLinker() {
    }
}
