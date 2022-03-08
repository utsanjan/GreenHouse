package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfg;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
abstract class zzfd<T extends zzfg<T>> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zza(Map.Entry<?, ?> entry);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzfe<T> zza(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Object zza(zzfb zzfbVar, zzgw zzgwVar, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract <UT, UB> UB zza(zzhm zzhmVar, Object obj, zzfb zzfbVar, zzfe<T> zzfeVar, UB ub, zzih<UT, UB> zzihVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzeg zzegVar, Object obj, zzfb zzfbVar, zzfe<T> zzfeVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzhm zzhmVar, Object obj, zzfb zzfbVar, zzfe<T> zzfeVar) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zza(zzja zzjaVar, Map.Entry<?, ?> entry) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean zza(zzgw zzgwVar);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract zzfe<T> zzb(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void zzc(Object obj);
}
