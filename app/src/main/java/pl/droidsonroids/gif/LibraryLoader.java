package pl.droidsonroids.gif;

import android.content.Context;
import java.lang.reflect.Method;
import pl.droidsonroids.relinker.ReLinker;

/* loaded from: classes.dex */
public class LibraryLoader {
    private static final String BASE_LIBRARY_NAME = "pl_droidsonroids_gif";
    private static Context sAppContext;

    private LibraryLoader() {
    }

    public static void initialize(Context context) {
        sAppContext = context.getApplicationContext();
    }

    private static Context getContext() {
        if (sAppContext == null) {
            try {
                Class<?> activityThread = Class.forName("android.app.ActivityThread");
                Method currentApplicationMethod = activityThread.getDeclaredMethod("currentApplication", new Class[0]);
                sAppContext = (Context) currentApplicationMethod.invoke(null, new Object[0]);
            } catch (Exception e) {
                throw new IllegalStateException("LibraryLoader not initialized. Call LibraryLoader.initialize() before using library classes.", e);
            }
        }
        return sAppContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void loadLibrary() {
        try {
            System.loadLibrary(BASE_LIBRARY_NAME);
        } catch (UnsatisfiedLinkError e) {
            ReLinker.loadLibrary(getContext(), BASE_LIBRARY_NAME);
        }
    }
}
