package com.google.android.gms.internal.firebase_auth;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzjz {
    private static final zzjz zza = new zzjz();
    private final ConcurrentMap<Class<?>, zzkd<?>> zzc = new ConcurrentHashMap();
    private final zzkg zzb = new zzje();

    public static zzjz zza() {
        return zza;
    }

    public final <T> zzkd<T> zza(Class<T> cls) {
        zzii.zza(cls, "messageType");
        zzkd<T> zzkdVar = (zzkd<T>) this.zzc.get(cls);
        if (zzkdVar != null) {
            return zzkdVar;
        }
        zzkd<T> zza2 = this.zzb.zza(cls);
        zzii.zza(cls, "messageType");
        zzii.zza(zza2, "schema");
        zzkd<T> zzkdVar2 = (zzkd<T>) this.zzc.putIfAbsent(cls, zza2);
        if (zzkdVar2 != null) {
            return zzkdVar2;
        }
        return zza2;
    }

    public final <T> zzkd<T> zza(T t) {
        return zza((Class) t.getClass());
    }

    private zzjz() {
    }
}
