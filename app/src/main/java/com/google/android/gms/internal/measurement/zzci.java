package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build;
import android.os.UserManager;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public class zzci {
    private static UserManager zza;
    private static volatile boolean zzb = !zza();
    private static boolean zzc = false;

    private zzci() {
    }

    public static boolean zza() {
        return Build.VERSION.SDK_INT >= 24;
    }

    public static boolean zza(Context context) {
        return !zza() || zzc(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0033, code lost:
        r4 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0042, code lost:
        if (r4 == false) goto L_0x0046;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0044, code lost:
        com.google.android.gms.internal.measurement.zzci.zza = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean zzb(android.content.Context r6) {
        /*
            r0 = 1
            r1 = 1
        L_0x0003:
            r2 = 2
            r3 = 0
            r4 = 0
            if (r1 > r2) goto L_0x0042
            android.os.UserManager r2 = com.google.android.gms.internal.measurement.zzci.zza
            if (r2 != 0) goto L_0x0017
            java.lang.Class<android.os.UserManager> r2 = android.os.UserManager.class
            java.lang.Object r2 = r6.getSystemService(r2)
            android.os.UserManager r2 = (android.os.UserManager) r2
            com.google.android.gms.internal.measurement.zzci.zza = r2
        L_0x0017:
            android.os.UserManager r2 = com.google.android.gms.internal.measurement.zzci.zza
            if (r2 != 0) goto L_0x001e
            return r0
        L_0x001e:
            boolean r5 = r2.isUserUnlocked()     // Catch: NullPointerException -> 0x0035
            if (r5 != 0) goto L_0x0032
            android.os.UserHandle r5 = android.os.Process.myUserHandle()     // Catch: NullPointerException -> 0x0035
            boolean r6 = r2.isUserRunning(r5)     // Catch: NullPointerException -> 0x0035
            if (r6 != 0) goto L_0x0030
            goto L_0x0032
        L_0x0030:
            r0 = 0
            goto L_0x0033
        L_0x0032:
        L_0x0033:
            r4 = r0
            goto L_0x0042
        L_0x0035:
            r2 = move-exception
            java.lang.String r4 = "DirectBootUtils"
            java.lang.String r5 = "Failed to check if user is unlocked."
            android.util.Log.w(r4, r5, r2)
            com.google.android.gms.internal.measurement.zzci.zza = r3
            int r1 = r1 + 1
            goto L_0x0003
        L_0x0042:
            if (r4 == 0) goto L_0x0046
            com.google.android.gms.internal.measurement.zzci.zza = r3
        L_0x0046:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzci.zzb(android.content.Context):boolean");
    }

    private static boolean zzc(Context context) {
        if (zzb) {
            return true;
        }
        synchronized (zzci.class) {
            if (zzb) {
                return true;
            }
            boolean zzb2 = zzb(context);
            if (zzb2) {
                zzb = zzb2;
            }
            return zzb2;
        }
    }
}
