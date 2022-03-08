package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.firebase_auth.zzgg;
import com.google.firebase.auth.PhoneAuthCredential;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzfr {
    public static zzgg zza(PhoneAuthCredential phoneAuthCredential) {
        if (!TextUtils.isEmpty(phoneAuthCredential.zze())) {
            return zzgg.zzb(phoneAuthCredential.zzc(), phoneAuthCredential.zze(), phoneAuthCredential.zzd());
        }
        return zzgg.zza(phoneAuthCredential.zzb(), phoneAuthCredential.getSmsCode(), phoneAuthCredential.zzd());
    }
}
