package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzhg {
    private static final zzhe zza = zzc();
    private static final zzhe zzb = new zzhh();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhe zza() {
        return zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzhe zzb() {
        return zzb;
    }

    private static zzhe zzc() {
        try {
            return (zzhe) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }
}
