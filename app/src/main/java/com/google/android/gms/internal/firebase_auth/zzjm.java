package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzjm {
    private static final zzjk zza = zzc();
    private static final zzjk zzb = new zzjj();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjk zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjk zzb() {
        return zzb;
    }

    private static zzjk zzc() {
        try {
            return (zzjk) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
