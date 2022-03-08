package com.applisto.appcloner.classes;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* loaded from: classes2.dex */
public class BundleObb {
    private static final String TAG = BundleObb.class.getSimpleName();
    private boolean mBundleObb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BundleObb(CloneSettings cloneSettings) {
        this.mBundleObb = cloneSettings.getBoolean("bundleObb", false).booleanValue();
        String str = TAG;
        Log.i(str, "BundleObb; mBundleObb: " + this.mBundleObb);
    }

    public void init(Context context) {
        if (this.mBundleObb) {
            try {
                File packageObbDirectory = getPackageObbDirectory(context.getPackageName());
                File versionCodeFile = new File(packageObbDirectory, Integer.toString(Utils.getApplicationVersionCode(context)));
                if (packageObbDirectory.exists() && versionCodeFile.exists()) {
                    Log.i(TAG, "init; not unbundling OBB files");
                    return;
                }
                Log.i(TAG, "init; unbundling OBB files");
                long ts = System.currentTimeMillis();
                deleteDirectory(packageObbDirectory);
                Utils.forceMkdir(packageObbDirectory);
                ZipInputStream zis = new ZipInputStream(context.getAssets().open("obb.zip"));
                while (true) {
                    ZipEntry entry = zis.getNextEntry();
                    if (entry != null) {
                        String name = entry.getName();
                        String str = TAG;
                        Log.i(str, "init; name: " + name);
                        File file = new File(packageObbDirectory, name);
                        FileOutputStream fos = new FileOutputStream(file);
                        Utils.copy(zis, fos);
                        fos.close();
                        String str2 = TAG;
                        Log.i(str2, "init; file: " + file + ", file.length(): " + file.length());
                    } else {
                        zis.close();
                        FileOutputStream fos2 = new FileOutputStream(versionCodeFile);
                        fos2.write(0);
                        fos2.close();
                        String str3 = TAG;
                        Log.i(str3, "init; took: " + (System.currentTimeMillis() - ts) + " millis");
                        return;
                    }
                }
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }
    }

    private static File getObbDirectory() {
        return new File(Environment.getExternalStorageDirectory(), "/Android/obb/");
    }

    private static File getPackageObbDirectory(String packageName) {
        return new File(getObbDirectory(), packageName);
    }

    private static void cleanDirectory(File directory) throws IOException {
        if (!directory.exists()) {
            String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        } else if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                IOException exception = null;
                for (File file : files) {
                    try {
                        forceDelete(file);
                    } catch (IOException ioe) {
                        exception = ioe;
                    }
                }
                if (exception != null) {
                    throw exception;
                }
                return;
            }
            throw new IOException("Failed to list contents of " + directory);
        } else {
            String message2 = directory + " is not a directory";
            throw new IllegalArgumentException(message2);
        }
    }

    private static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        boolean filePresent = file.exists();
        if (file.delete()) {
            return;
        }
        if (!filePresent) {
            throw new FileNotFoundException("File does not exist: " + file);
        }
        throw new IOException("Unable to delete file: " + file);
    }

    private static void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            if (!isSymlink(directory)) {
                cleanDirectory(directory);
            }
            if (!directory.delete()) {
                String message = "Unable to delete directory " + directory + ".";
                throw new IOException(message);
            }
        }
    }

    private static boolean isSymlink(File file) throws IOException {
        File canonicalDir;
        if (file.getParent() == null) {
            canonicalDir = file;
        } else {
            File fileInCanonicalDir = file.getParentFile();
            canonicalDir = new File(fileInCanonicalDir.getCanonicalFile(), file.getName());
        }
        return !canonicalDir.getCanonicalFile().equals(canonicalDir.getAbsoluteFile());
    }
}
