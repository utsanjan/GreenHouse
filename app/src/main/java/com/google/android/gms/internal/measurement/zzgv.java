package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzgv {
    private static final zzgt zza = zzc();
    private static final zzgt zzb = new zzgs();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgt zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzgt zzb() {
        return zzb;
    }

    private static zzgt zzc() {
        try {
            return (zzgt) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
