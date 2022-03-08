package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
public final class zzdm {
    public static <T> zzdj<T> zza(zzdj<T> zzdjVar) {
        if ((zzdjVar instanceof zzdo) || (zzdjVar instanceof zzdl)) {
            return zzdjVar;
        }
        if (zzdjVar instanceof Serializable) {
            return new zzdl(zzdjVar);
        }
        return new zzdo(zzdjVar);
    }

    public static <T> zzdj<T> zza(@NullableDecl T t) {
        return new zzdn(t);
    }
}
