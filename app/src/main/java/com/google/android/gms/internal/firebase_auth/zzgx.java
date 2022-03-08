package com.google.android.gms.internal.firebase_auth;

import java.util.Comparator;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzgx implements Comparator<zzgv> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzgv zzgvVar, zzgv zzgvVar2) {
        int zzb;
        int zzb2;
        zzgv zzgvVar3 = zzgvVar;
        zzgv zzgvVar4 = zzgvVar2;
        zzha zzhaVar = (zzha) zzgvVar3.iterator();
        zzha zzhaVar2 = (zzha) zzgvVar4.iterator();
        while (zzhaVar.hasNext() && zzhaVar2.hasNext()) {
            zzb = zzgv.zzb(zzhaVar.zza());
            zzb2 = zzgv.zzb(zzhaVar2.zza());
            int compare = Integer.compare(zzb, zzb2);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzgvVar3.zza(), zzgvVar4.zza());
    }
}
