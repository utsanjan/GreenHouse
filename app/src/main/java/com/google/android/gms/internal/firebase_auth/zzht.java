package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzif;
import java.io.IOException;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzht extends zzhu<zzif.zze> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final boolean zza(zzjn zzjnVar) {
        return zzjnVar instanceof zzif.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final zzhv<zzif.zze> zza(Object obj) {
        return ((zzif.zzb) obj).zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final zzhv<zzif.zze> zzb(Object obj) {
        zzif.zzb zzbVar = (zzif.zzb) obj;
        if (zzbVar.zzc.zzc()) {
            zzbVar.zzc = (zzhv) zzbVar.zzc.clone();
        }
        return zzbVar.zzc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final void zzc(Object obj) {
        zza(obj).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final <UT, UB> UB zza(zzke zzkeVar, Object obj, zzhs zzhsVar, zzhv<zzif.zze> zzhvVar, UB ub, zzkz<UT, UB> zzkzVar) throws IOException {
        zzif.zzd zzdVar = (zzif.zzd) obj;
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final int zza(Map.Entry<?, ?> entry) {
        zzif.zze zzeVar = (zzif.zze) entry.getKey();
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final void zza(zzls zzlsVar, Map.Entry<?, ?> entry) throws IOException {
        zzif.zze zzeVar = (zzif.zze) entry.getKey();
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final Object zza(zzhs zzhsVar, zzjn zzjnVar, int i) {
        return zzhsVar.zza(zzjnVar, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final void zza(zzke zzkeVar, Object obj, zzhs zzhsVar, zzhv<zzif.zze> zzhvVar) throws IOException {
        zzif.zzd zzdVar = (zzif.zzd) obj;
        throw new NoSuchMethodError();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzhu
    public final void zza(zzgv zzgvVar, Object obj, zzhs zzhsVar, zzhv<zzif.zze> zzhvVar) throws IOException {
        zzif.zzd zzdVar = (zzif.zzd) obj;
        throw new NoSuchMethodError();
    }
}
