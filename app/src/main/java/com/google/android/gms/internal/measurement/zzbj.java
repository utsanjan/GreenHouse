package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzag;
import com.google.android.gms.measurement.internal.zzha;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.3 */
/* loaded from: classes.dex */
public final class zzbj extends zzag.zzb {
    private final /* synthetic */ zzha zzc;
    private final /* synthetic */ zzag zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzbj(zzag zzagVar, zzha zzhaVar) {
        super(zzagVar);
        this.zzd = zzagVar;
        this.zzc = zzhaVar;
    }

    /* JADX WARN: Incorrect condition in loop: B:4:0x000b */
    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final void zza() throws android.os.RemoteException {
        /*
            r4 = this;
            r0 = 0
        L_0x0001:
            com.google.android.gms.internal.measurement.zzag r1 = r4.zzd
            java.util.List r1 = com.google.android.gms.internal.measurement.zzag.zzd(r1)
            int r1 = r1.size()
            if (r0 >= r1) goto L_0x0032
            com.google.android.gms.measurement.internal.zzha r1 = r4.zzc
            com.google.android.gms.internal.measurement.zzag r2 = r4.zzd
            java.util.List r2 = com.google.android.gms.internal.measurement.zzag.zzd(r2)
            java.lang.Object r2 = r2.get(r0)
            android.util.Pair r2 = (android.util.Pair) r2
            java.lang.Object r2 = r2.first
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x002f
            com.google.android.gms.internal.measurement.zzag r0 = r4.zzd
            java.lang.String r0 = com.google.android.gms.internal.measurement.zzag.zzb(r0)
            java.lang.String r1 = "OnEventListener already registered."
            android.util.Log.w(r0, r1)
            return
        L_0x002f:
            int r0 = r0 + 1
            goto L_0x0001
        L_0x0032:
            com.google.android.gms.internal.measurement.zzag$zzd r0 = new com.google.android.gms.internal.measurement.zzag$zzd
            com.google.android.gms.measurement.internal.zzha r1 = r4.zzc
            r0.<init>(r1)
            com.google.android.gms.internal.measurement.zzag r1 = r4.zzd
            java.util.List r1 = com.google.android.gms.internal.measurement.zzag.zzd(r1)
            android.util.Pair r2 = new android.util.Pair
            com.google.android.gms.measurement.internal.zzha r3 = r4.zzc
            r2.<init>(r3, r0)
            r1.add(r2)
            com.google.android.gms.internal.measurement.zzag r1 = r4.zzd
            com.google.android.gms.internal.measurement.zzv r1 = com.google.android.gms.internal.measurement.zzag.zzc(r1)
            r1.registerOnMeasurementEventListener(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzbj.zza():void");
    }
}
