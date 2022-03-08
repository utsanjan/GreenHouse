package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzjj implements zzjk {
    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final Map<?, ?> zza(Object obj) {
        return (zzjh) obj;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final zzji<?, ?> zzb(Object obj) {
        zzjf zzjfVar = (zzjf) obj;
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final Map<?, ?> zzc(Object obj) {
        return (zzjh) obj;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final boolean zzd(Object obj) {
        return !((zzjh) obj).zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final Object zze(Object obj) {
        ((zzjh) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final Object zzf(Object obj) {
        return zzjh.zza().zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final Object zza(Object obj, Object obj2) {
        zzjh zzjhVar = (zzjh) obj;
        zzjh zzjhVar2 = (zzjh) obj2;
        if (!zzjhVar2.isEmpty()) {
            if (!zzjhVar.zzd()) {
                zzjhVar = zzjhVar.zzb();
            }
            zzjhVar.zza(zzjhVar2);
        }
        return zzjhVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjk
    public final int zza(int i, Object obj, Object obj2) {
        zzjh zzjhVar = (zzjh) obj;
        zzjf zzjfVar = (zzjf) obj2;
        if (zzjhVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzjhVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
