package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzij extends zzih<zzig, zzig> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final boolean zza(zzhm zzhmVar) {
        return false;
    }

    /* renamed from: zza  reason: avoid collision after fix types in other method */
    private static void zza2(Object obj, zzig zzigVar) {
        ((zzfo) obj).zzb = zzigVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final void zzd(Object obj) {
        ((zzfo) obj).zzb.zzc();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ int zzf(zzig zzigVar) {
        return zzigVar.zze();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ int zze(zzig zzigVar) {
        return zzigVar.zzd();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ zzig zzc(zzig zzigVar, zzig zzigVar2) {
        zzig zzigVar3 = zzigVar;
        zzig zzigVar4 = zzigVar2;
        if (zzigVar4.equals(zzig.zza())) {
            return zzigVar3;
        }
        return zzig.zza(zzigVar3, zzigVar4);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ void zzb(zzig zzigVar, zzja zzjaVar) throws IOException {
        zzigVar.zza(zzjaVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ void zza(zzig zzigVar, zzja zzjaVar) throws IOException {
        zzigVar.zzb(zzjaVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ void zzb(Object obj, zzig zzigVar) {
        zza2(obj, zzigVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ zzig zzc(Object obj) {
        zzig zzigVar = ((zzfo) obj).zzb;
        if (zzigVar != zzig.zza()) {
            return zzigVar;
        }
        zzig zzb = zzig.zzb();
        zza2(obj, zzb);
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ zzig zzb(Object obj) {
        return ((zzfo) obj).zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* bridge */ /* synthetic */ void zza(Object obj, zzig zzigVar) {
        zza2(obj, zzigVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    final /* synthetic */ zzig zza(zzig zzigVar) {
        zzig zzigVar2 = zzigVar;
        zzigVar2.zzc();
        return zzigVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ zzig zza() {
        return zzig.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    final /* synthetic */ void zza(zzig zzigVar, int i, zzig zzigVar2) {
        zzigVar.zza((i << 3) | 3, zzigVar2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ void zza(zzig zzigVar, int i, zzeg zzegVar) {
        zzigVar.zza((i << 3) | 2, zzegVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    final /* synthetic */ void zzb(zzig zzigVar, int i, long j) {
        zzigVar.zza((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.zzih
    final /* synthetic */ void zza(zzig zzigVar, int i, int i2) {
        zzigVar.zza((i << 3) | 5, Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.measurement.zzih
    public final /* synthetic */ void zza(zzig zzigVar, int i, long j) {
        zzigVar.zza(i << 3, Long.valueOf(j));
    }
}
