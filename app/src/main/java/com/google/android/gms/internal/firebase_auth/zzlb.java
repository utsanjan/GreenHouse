package com.google.android.gms.internal.firebase_auth;

import java.io.IOException;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzlb extends zzkz<zzky, zzky> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final boolean zza(zzke zzkeVar) {
        return false;
    }

    /* renamed from: zza  reason: avoid collision after fix types in other method */
    private static void zza2(Object obj, zzky zzkyVar) {
        ((zzif) obj).zzb = zzkyVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final void zzd(Object obj) {
        ((zzif) obj).zzb.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ int zzf(zzky zzkyVar) {
        return zzkyVar.zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ int zze(zzky zzkyVar) {
        return zzkyVar.zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ zzky zzc(zzky zzkyVar, zzky zzkyVar2) {
        zzky zzkyVar3 = zzkyVar;
        zzky zzkyVar4 = zzkyVar2;
        if (zzkyVar4.equals(zzky.zza())) {
            return zzkyVar3;
        }
        return zzky.zza(zzkyVar3, zzkyVar4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ void zzb(zzky zzkyVar, zzls zzlsVar) throws IOException {
        zzkyVar.zza(zzlsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ void zza(zzky zzkyVar, zzls zzlsVar) throws IOException {
        zzkyVar.zzb(zzlsVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ void zzb(Object obj, zzky zzkyVar) {
        zza2(obj, zzkyVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ zzky zzc(Object obj) {
        zzky zzkyVar = ((zzif) obj).zzb;
        if (zzkyVar != zzky.zza()) {
            return zzkyVar;
        }
        zzky zzb = zzky.zzb();
        zza2(obj, zzb);
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ zzky zzb(Object obj) {
        return ((zzif) obj).zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* bridge */ /* synthetic */ void zza(Object obj, zzky zzkyVar) {
        zza2(obj, zzkyVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    final /* synthetic */ zzky zza(zzky zzkyVar) {
        zzky zzkyVar2 = zzkyVar;
        zzkyVar2.zzc();
        return zzkyVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ zzky zza() {
        return zzky.zzb();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    final /* synthetic */ void zza(zzky zzkyVar, int i, zzky zzkyVar2) {
        zzkyVar.zza((i << 3) | 3, zzkyVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ void zza(zzky zzkyVar, int i, zzgv zzgvVar) {
        zzkyVar.zza((i << 3) | 2, zzgvVar);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    final /* synthetic */ void zzb(zzky zzkyVar, int i, long j) {
        zzkyVar.zza((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    final /* synthetic */ void zza(zzky zzkyVar, int i, int i2) {
        zzkyVar.zza((i << 3) | 5, Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.firebase_auth.zzkz
    public final /* synthetic */ void zza(zzky zzkyVar, int i, long j) {
        zzkyVar.zza(i << 3, Long.valueOf(j));
    }
}
