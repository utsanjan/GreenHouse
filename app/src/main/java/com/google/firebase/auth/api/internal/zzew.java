package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.LibraryVersion;
import com.google.android.gms.internal.firebase_auth.zzau;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzew {
    private final String zza;
    private final int zzb;
    private final int zzc = -1;

    private zzew(String str, int i) {
        this.zza = str;
        this.zzb = zzd(str);
    }

    public final String zza() {
        int i = this.zzb;
        return i != -1 ? String.format("X%s", Integer.toString(i)) : Integer.toString(this.zzc);
    }

    public static zzew zzb() {
        return new zzew(zzc("firebase-auth-compat"), -1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zza(String str) {
        return this.zzb >= zzd(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzb(String str) {
        return this.zzb >= zzd(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzc() {
        return zzc("firebase-auth");
    }

    private static String zzc(String str) {
        String version = LibraryVersion.getInstance().getVersion(str);
        if (TextUtils.isEmpty(version) || version.equals("UNKNOWN")) {
            return "-1";
        }
        return version;
    }

    private static int zzd(String str) {
        try {
            List<String> zza = zzau.zza("[.-]").zza((CharSequence) str);
            if (zza.size() == 1) {
                return Integer.parseInt(str);
            }
            if (zza.size() >= 3) {
                return (Integer.parseInt(zza.get(0)) * 1000000) + (Integer.parseInt(zza.get(1)) * 1000) + Integer.parseInt(zza.get(2));
            }
            return -1;
        } catch (IllegalArgumentException e) {
            if (!Log.isLoggable("LibraryVersionContainer", 3)) {
                return -1;
            }
            Log.d("LibraryVersionContainer", String.format("Version code parsing failed for: %s with exception %s.", str, e));
            return -1;
        }
    }
}
