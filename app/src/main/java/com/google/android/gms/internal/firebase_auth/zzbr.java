package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractMap;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzbr extends zzbg<Map.Entry<K, V>> {
    private final /* synthetic */ zzbo zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzbr(zzbo zzboVar) {
        this.zza = zzboVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzd;
        return i;
    }

    @Override // java.util.List
    public final /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzd;
        zzav.zza(i, i2);
        objArr = this.zza.zzb;
        int i3 = i * 2;
        zzbo zzboVar = this.zza;
        Object obj = objArr[i3];
        objArr2 = zzboVar.zzb;
        return new AbstractMap.SimpleImmutableEntry(obj, objArr2[i3 + 1]);
    }
}
