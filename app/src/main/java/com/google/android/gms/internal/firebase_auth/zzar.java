package com.google.android.gms.internal.firebase_auth;

import java.util.logging.Logger;
import java.util.regex.Pattern;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzar {
    private static final Logger zza = Logger.getLogger(zzar.class.getName());
    private static final zzas zzb = new zza();

    /* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
    /* loaded from: classes.dex */
    static final class zza implements zzas {
        private zza() {
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzas
        public final zzal zza(String str) {
            return new zzan(Pattern.compile(str));
        }
    }

    private zzar() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzal zza(String str) {
        zzav.zza(str);
        return zzb.zza(str);
    }
}
