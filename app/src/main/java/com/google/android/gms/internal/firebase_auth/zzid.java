package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzid implements zzjo {
    private static final zzid zza = new zzid();

    private zzid() {
    }

    public static zzid zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjo
    public final boolean zza(Class<?> cls) {
        return zzif.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjo
    public final zzjl zzb(Class<?> cls) {
        if (!zzif.class.isAssignableFrom(cls)) {
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? "Unsupported message type: ".concat(valueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzjl) zzif.zza((Class<zzif>) cls.asSubclass(zzif.class)).zza(zzif.zzf.zzc, (Object) null, (Object) null);
        } catch (Exception e) {
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? "Unable to get message info for ".concat(valueOf2) : new String("Unable to get message info for "), e);
        }
    }
}
