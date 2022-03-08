package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfo;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzfm implements zzgx {
    private static final zzfm zza = new zzfm();

    private zzfm() {
    }

    public static zzfm zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final boolean zza(Class<?> cls) {
        return zzfo.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.measurement.zzgx
    public final zzgu zzb(Class<?> cls) {
        if (!zzfo.class.isAssignableFrom(cls)) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unsupported message type: ".concat(valueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzgu) zzfo.zza((Class<zzfo>) cls.asSubclass(zzfo.class)).zza(zzfo.zzf.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? "Unable to get message info for ".concat(valueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
