package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzff {
    private static final zzfd<?> zza = new zzfc();
    private static final zzfd<?> zzb = zzc();

    private static zzfd<?> zzc() {
        try {
            return (zzfd) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfd<?> zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzfd<?> zzb() {
        zzfd<?> zzfdVar = zzb;
        if (zzfdVar != null) {
            return zzfdVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
