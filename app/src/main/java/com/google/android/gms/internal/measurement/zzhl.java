package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzhl {
    private static final zzhl zza = new zzhl();
    private final ConcurrentMap<Class<?>, zzhp<?>> zzc = new ConcurrentHashMap();
    private final zzho zzb = new zzgn();

    public static zzhl zza() {
        return zza;
    }

    public final <T> zzhp<T> zza(Class<T> cls) {
        zzfr.zza(cls, "messageType");
        zzhp<T> zzhpVar = (zzhp<T>) this.zzc.get(cls);
        if (zzhpVar != null) {
            return zzhpVar;
        }
        zzhp<T> zza2 = this.zzb.zza(cls);
        zzfr.zza(cls, "messageType");
        zzfr.zza(zza2, "schema");
        zzhp<T> zzhpVar2 = (zzhp<T>) this.zzc.putIfAbsent(cls, zza2);
        if (zzhpVar2 != null) {
            return zzhpVar2;
        }
        return zza2;
    }

    public final <T> zzhp<T> zza(T t) {
        return zza((Class) t.getClass());
    }

    private zzhl() {
    }
}
