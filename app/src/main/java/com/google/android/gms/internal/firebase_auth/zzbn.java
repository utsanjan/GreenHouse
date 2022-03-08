package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzbn<E> extends zzbh<E> implements Set<E> {
    @NullableDecl
    private transient zzbg<E> zza;

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        return zzbs.zza(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzbs.zza(this);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public zzbg<E> zzc() {
        zzbg<E> zzbgVar = this.zza;
        if (zzbgVar != null) {
            return zzbgVar;
        }
        zzbg<E> zza = zza();
        this.zza = zza;
        return zza;
    }

    zzbg<E> zza() {
        return zzbg.zza(toArray());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
