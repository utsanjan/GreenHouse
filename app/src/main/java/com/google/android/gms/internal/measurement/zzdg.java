package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.gms:play-services-measurement-impl@@17.4.3 */
/* loaded from: classes.dex */
final class zzdg<T> extends zzdi<T> {
    static final zzdg<Object> zza = new zzdg<>();

    private zzdg() {
    }

    @Override // com.google.android.gms.internal.measurement.zzdi
    public final boolean zza() {
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzdi
    public final T zzb() {
        throw new IllegalStateException("Optional.get() cannot be called on an absent value");
    }

    public final boolean equals(@NullableDecl Object obj) {
        return obj == this;
    }

    public final int hashCode() {
        return 2040732332;
    }

    public final String toString() {
        return "Optional.absent()";
    }
}
