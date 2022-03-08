package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzdz {
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
