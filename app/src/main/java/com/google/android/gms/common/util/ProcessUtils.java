package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public class ProcessUtils {
    private static String zzhv = null;
    private static int zzhw = 0;

    private ProcessUtils() {
    }

    @Nullable
    public static String getMyProcessName() {
        if (zzhv == null) {
            if (zzhw == 0) {
                zzhw = Process.myPid();
            }
            zzhv = zzd(zzhw);
        }
        return zzhv;
    }

    @Nullable
    private static String zzd(int i) {
        BufferedReader bufferedReader;
        Throwable th;
        String str = null;
        if (i <= 0) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(25);
            sb.append("/proc/");
            sb.append(i);
            sb.append("/cmdline");
            bufferedReader = zzk(sb.toString());
        } catch (IOException e) {
            bufferedReader = null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        try {
            str = bufferedReader.readLine().trim();
            IOUtils.closeQuietly(bufferedReader);
        } catch (IOException e2) {
            IOUtils.closeQuietly(bufferedReader);
            return str;
        } catch (Throwable th3) {
            th = th3;
            IOUtils.closeQuietly(bufferedReader);
            throw th;
        }
        return str;
    }

    private static BufferedReader zzk(String str) throws IOException {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
