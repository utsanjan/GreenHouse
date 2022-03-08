package com.google.firebase.auth;

import android.net.Uri;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.firebase_auth.zzbk;
import com.google.android.gms.internal.firebase_auth.zzbl;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public class ActionCodeUrl {
    private static final zzbl<String, Integer> zzg = new zzbk().zza("recoverEmail", 2).zza("resetPassword", 0).zza("signIn", 4).zza("verifyEmail", 1).zza("verifyBeforeChangeEmail", 5).zza("revertSecondFactorAddition", 6).zza();
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;

    private ActionCodeUrl(String str) {
        this.zza = zza(str, "apiKey");
        this.zzb = zza(str, "oobCode");
        String zza = zza(str, "mode");
        this.zzc = zza;
        if (this.zza == null || this.zzb == null || zza == null) {
            throw new IllegalArgumentException(String.format("%s, %s and %s are required in a valid action code URL", "apiKey", "oobCode", "mode"));
        }
        this.zzd = zza(str, "continueUrl");
        this.zze = zza(str, "languageCode");
        this.zzf = zza(str, "tenantId");
    }

    public static ActionCodeUrl parseLink(String str) {
        Preconditions.checkNotEmpty(str);
        try {
            return new ActionCodeUrl(str);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String getApiKey() {
        return this.zza;
    }

    public int getOperation() {
        return zzg.getOrDefault(this.zzc, 3).intValue();
    }

    public String getCode() {
        return this.zzb;
    }

    public String getContinueUrl() {
        return this.zzd;
    }

    public String getLanguageCode() {
        return this.zze;
    }

    public final String zza() {
        return this.zzf;
    }

    private static String zza(String str, String str2) {
        Uri parse = Uri.parse(str);
        try {
            Set<String> queryParameterNames = parse.getQueryParameterNames();
            if (queryParameterNames.contains(str2)) {
                return parse.getQueryParameter(str2);
            }
            if (queryParameterNames.contains("link")) {
                return Uri.parse(parse.getQueryParameter("link")).getQueryParameter(str2);
            }
            return null;
        } catch (UnsupportedOperationException e) {
            return null;
        }
    }
}
