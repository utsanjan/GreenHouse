package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.firebase_auth.zzbg;
import com.google.android.gms.internal.firebase_auth.zzfh;
import com.google.firebase.auth.MultiFactorInfo;
import com.google.firebase.auth.PhoneMultiFactorInfo;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzat {
    public static MultiFactorInfo zza(zzfh zzfhVar) {
        if (zzfhVar != null && !TextUtils.isEmpty(zzfhVar.zza())) {
            return new PhoneMultiFactorInfo(zzfhVar.zzb(), zzfhVar.zzc(), zzfhVar.zzd(), zzfhVar.zza());
        }
        return null;
    }

    public static List<MultiFactorInfo> zza(List<zzfh> list) {
        if (list == null || list.isEmpty()) {
            return zzbg.zza();
        }
        ArrayList arrayList = new ArrayList();
        for (zzfh zzfhVar : list) {
            MultiFactorInfo zza = zza(zzfhVar);
            if (zza != null) {
                arrayList.add(zza);
            }
        }
        return arrayList;
    }
}
