package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzbq<K> extends zzbn<K> {
    private final transient zzbl<K, ?> zza;
    private final transient zzbg<K> zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbq(zzbl<K, ?> zzblVar, zzbg<K> zzbgVar) {
        this.zza = zzblVar;
        this.zzb = zzbgVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final zzbv<K> zzb() {
        return (zzbv) zzc().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbn, com.google.android.gms.internal.firebase_auth.zzbh
    public final zzbg<K> zzc() {
        return this.zzb;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(@NullableDecl Object obj) {
        return this.zza.get(obj) != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbn, com.google.android.gms.internal.firebase_auth.zzbh, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
