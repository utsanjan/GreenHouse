package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzjy {
    private static final zzjw zza = zzc();
    private static final zzjw zzb = new zzjv();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjw zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzjw zzb() {
        return zzb;
    }

    private static zzjw zzc() {
        try {
            return (zzjw) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
