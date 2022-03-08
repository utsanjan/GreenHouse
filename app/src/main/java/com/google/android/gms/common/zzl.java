package com.google.android.gms.common;

import android.util.Log;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import java.util.concurrent.Callable;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
@CheckReturnValue
/* loaded from: classes.dex */
public class zzl {
    private static final zzl zzao = new zzl(true, null, null);
    private final Throwable cause;
    final boolean zzap;
    private final String zzaq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzl(boolean z, @Nullable String str, @Nullable Throwable th) {
        this.zzap = z;
        this.zzaq = str;
        this.cause = th;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzl zze() {
        return zzao;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzl zza(Callable<String> callable) {
        return new zzn(callable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzl zzb(String str) {
        return new zzl(false, str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzl zza(String str, Throwable th) {
        return new zzl(false, str, th);
    }

    @Nullable
    String getErrorMessage() {
        return this.zzaq;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzf() {
        if (!this.zzap && Log.isLoggable("GoogleCertificatesRslt", 3)) {
            if (this.cause != null) {
                Log.d("GoogleCertificatesRslt", getErrorMessage(), this.cause);
            } else {
                Log.d("GoogleCertificatesRslt", getErrorMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzc(String str, zzd zzdVar, boolean z, boolean z2) {
        return String.format("%s: pkg=%s, sha1=%s, atk=%s, ver=%s", z2 ? "debug cert rejected" : "not whitelisted", str, Hex.bytesToStringLowercase(AndroidUtilsLight.zzj("SHA-1").digest(zzdVar.getBytes())), Boolean.valueOf(z), "12451009.false");
    }
}
