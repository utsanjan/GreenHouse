package com.google.android.gms.internal.measurement;

import java.util.Comparator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzei implements Comparator<zzeg> {
    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzeg zzegVar, zzeg zzegVar2) {
        int zzb;
        int zzb2;
        zzeg zzegVar3 = zzegVar;
        zzeg zzegVar4 = zzegVar2;
        zzel zzelVar = (zzel) zzegVar3.iterator();
        zzel zzelVar2 = (zzel) zzegVar4.iterator();
        while (zzelVar.hasNext() && zzelVar2.hasNext()) {
            zzb = zzeg.zzb(zzelVar.zza());
            zzb2 = zzeg.zzb(zzelVar2.zza());
            int compare = Integer.compare(zzb, zzb2);
            if (compare != 0) {
                return compare;
            }
        }
        return Integer.compare(zzegVar3.zza(), zzegVar4.zza());
    }
}
