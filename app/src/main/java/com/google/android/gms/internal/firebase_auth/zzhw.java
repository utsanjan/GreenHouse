package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzhw {
    private static final zzhu<?> zza = new zzht();
    private static final zzhu<?> zzb = zzc();

    private static zzhu<?> zzc() {
        try {
            return (zzhu) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhu<?> zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhu<?> zzb() {
        zzhu<?> zzhuVar = zzb;
        if (zzhuVar != null) {
            return zzhuVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
