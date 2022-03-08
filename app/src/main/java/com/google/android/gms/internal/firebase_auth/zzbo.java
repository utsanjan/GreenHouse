package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzbo<K, V> extends zzbn<Map.Entry<K, V>> {
    private final transient zzbl<K, V> zza;
    private final transient Object[] zzb;
    private final transient int zzc = 0;
    private final transient int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbo(zzbl<K, V> zzblVar, Object[] objArr, int i, int i2) {
        this.zza = zzblVar;
        this.zzb = objArr;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final zzbv<Map.Entry<K, V>> zzb() {
        return (zzbv) zzc().iterator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzbh
    public final int zza(Object[] objArr, int i) {
        return zzc().zza(objArr, i);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbn
    final zzbg<Map.Entry<K, V>> zza() {
        return new zzbr(this);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbh, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object value = entry.getValue();
        return value != null && value.equals(this.zza.get(key));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbn, com.google.android.gms.internal.firebase_auth.zzbh, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
