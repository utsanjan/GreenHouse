package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
abstract class zzae<T> implements Iterator<T> {
    private int zza = zzag.zzb;
    @NullableDecl
    private T zzb;

    protected abstract T zza();

    /* JADX INFO: Access modifiers changed from: protected */
    @NullableDecl
    public final T zzb() {
        this.zza = zzag.zzc;
        return null;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.zza != zzag.zzd) {
            int i = zzad.zza[this.zza - 1];
            if (i == 1) {
                return false;
            }
            if (i == 2) {
                return true;
            }
            this.zza = zzag.zzd;
            this.zzb = zza();
            if (this.zza == zzag.zzc) {
                return false;
            }
            this.zza = zzag.zza;
            return true;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Iterator
    public final T next() {
        if (hasNext()) {
            this.zza = zzag.zzb;
            T t = this.zzb;
            this.zzb = null;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
