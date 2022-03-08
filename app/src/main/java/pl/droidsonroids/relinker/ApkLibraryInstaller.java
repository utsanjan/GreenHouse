package pl.droidsonroids.relinker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import pl.droidsonroids.relinker.ReLinker;

/* loaded from: classes.dex */
public class ApkLibraryInstaller implements ReLinker.LibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    private String[] sourceDirectories(Context context) {
        ApplicationInfo appInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || appInfo.splitSourceDirs == null || appInfo.splitSourceDirs.length == 0) {
            return new String[]{appInfo.sourceDir};
        }
        String[] apks = new String[appInfo.splitSourceDirs.length + 1];
        apks[0] = appInfo.sourceDir;
        System.arraycopy(appInfo.splitSourceDirs, 0, apks, 1, appInfo.splitSourceDirs.length);
        return apks;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile, ZipEntry zipEntry) {
            this.zipFile = zipFile;
            this.zipEntry = zipEntry;
        }
    }

    private ZipFileInZipEntry findAPKWithLibrary(Context context, String[] abis, String mappedLibraryName, ReLinkerInstance instance) {
        int i;
        char c;
        String[] sourceDirectories = sourceDirectories(context);
        int length = sourceDirectories.length;
        char c2 = 0;
        ZipFile zipFile = null;
        int i2 = 0;
        while (i2 < length) {
            String sourceDir = sourceDirectories[i2];
            int tries = 0;
            while (true) {
                int tries2 = tries + 1;
                i = 5;
                c = 1;
                if (tries >= 5) {
                    break;
                }
                try {
                    ZipFile zipFile2 = new ZipFile(new File(sourceDir), 1);
                    zipFile = zipFile2;
                    break;
                } catch (IOException e) {
                    tries = tries2;
                }
            }
            if (zipFile != null) {
                int tries3 = 0;
                while (true) {
                    int tries4 = tries3 + 1;
                    if (tries3 < i) {
                        int length2 = abis.length;
                        int i3 = 0;
                        while (i3 < length2) {
                            String abi = abis[i3];
                            String jniNameInApk = "lib" + File.separatorChar + abi + File.separatorChar + mappedLibraryName;
                            Object[] objArr = new Object[2];
                            objArr[c2] = jniNameInApk;
                            objArr[c] = sourceDir;
                            instance.log("Looking for %s in APK %s...", objArr);
                            ZipEntry libraryEntry = zipFile.getEntry(jniNameInApk);
                            if (libraryEntry != null) {
                                return new ZipFileInZipEntry(zipFile, libraryEntry);
                            }
                            i3++;
                            c2 = 0;
                            c = 1;
                        }
                        tries3 = tries4;
                        c2 = 0;
                        i = 5;
                        c = 1;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException e2) {
                        }
                    }
                }
            }
            i2++;
            c2 = 0;
        }
        return null;
    }

    @Override // pl.droidsonroids.relinker.ReLinker.LibraryInstaller
    public void installLibrary(Context context, String[] abis, String mappedLibraryName, File destination, ReLinkerInstance instance) {
        ZipFileInZipEntry found = null;
        try {
            found = findAPKWithLibrary(context, abis, mappedLibraryName, instance);
            if (found != null) {
                int tries = 0;
                while (true) {
                    int tries2 = tries + 1;
                    if (tries < 5) {
                        instance.log("Found %s! Extracting...", mappedLibraryName);
                        try {
                            if (destination.exists() || destination.createNewFile()) {
                                InputStream inputStream = null;
                                FileOutputStream fileOut = null;
                                try {
                                    inputStream = found.zipFile.getInputStream(found.zipEntry);
                                    fileOut = new FileOutputStream(destination);
                                    long written = copy(inputStream, fileOut);
                                    fileOut.getFD().sync();
                                    if (written != destination.length()) {
                                        closeSilently(inputStream);
                                        closeSilently(fileOut);
                                    } else {
                                        closeSilently(inputStream);
                                        closeSilently(fileOut);
                                        destination.setReadable(true, false);
                                        destination.setExecutable(true, false);
                                        destination.setWritable(true);
                                        if (found != null) {
                                            try {
                                                if (found.zipFile != null) {
                                                    found.zipFile.close();
                                                    return;
                                                }
                                                return;
                                            } catch (IOException e) {
                                                return;
                                            }
                                        } else {
                                            return;
                                        }
                                    }
                                } catch (FileNotFoundException e2) {
                                    closeSilently(inputStream);
                                    closeSilently(fileOut);
                                } catch (IOException e3) {
                                    closeSilently(inputStream);
                                    closeSilently(fileOut);
                                }
                            }
                        } catch (IOException e4) {
                        }
                        tries = tries2;
                    } else {
                        instance.log("FATAL! Couldn't extract the library from the APK!");
                        if (found != null) {
                            try {
                                if (found.zipFile != null) {
                                    found.zipFile.close();
                                    return;
                                }
                                return;
                            } catch (IOException e5) {
                                return;
                            }
                        } else {
                            return;
                        }
                    }
                }
            } else {
                throw new MissingLibraryException(mappedLibraryName);
            }
        } catch (Throwable th) {
            if (found != null) {
                try {
                    if (found.zipFile != null) {
                        found.zipFile.close();
                    }
                } catch (IOException e6) {
                }
            }
            throw th;
        }
    }

    private long copy(InputStream in, OutputStream out) throws IOException {
        long copied = 0;
        byte[] buf = new byte[4096];
        while (true) {
            int read = in.read(buf);
            if (read == -1) {
                out.flush();
                return copied;
            }
            out.write(buf, 0, read);
            copied += read;
        }
    }

    private void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }
}
