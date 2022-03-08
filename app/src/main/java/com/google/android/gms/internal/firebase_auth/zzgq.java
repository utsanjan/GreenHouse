package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzgq {
    private static final Class<?> zza = zza("libcore.io.Memory");
    private static final boolean zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zza() {
        return zza != null && !zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> zzb() {
        return zza;
    }

    private static <T> Class<T> zza(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }

    static {
        zzb = zza("org.robolectric.Robolectric") != null;
    }
}
