package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzkj extends zzkp {
    private final /* synthetic */ zzki zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzkj(zzki zzkiVar) {
        super(zzkiVar, null);
        this.zza = zzkiVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkp, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzkk(this.zza, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzkj(zzki zzkiVar, zzkh zzkhVar) {
        this(zzkiVar);
    }
}
