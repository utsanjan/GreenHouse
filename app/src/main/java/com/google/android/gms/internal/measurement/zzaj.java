package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzag;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.4.3 */
/* loaded from: classes.dex */
public final class zzaj extends zzag.zzb {
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ Context zze;
    private final /* synthetic */ Bundle zzf;
    private final /* synthetic */ zzag zzg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaj(zzag zzagVar, String str, String str2, Context context, Bundle bundle) {
        super(zzagVar);
        this.zzg = zzagVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = context;
        this.zzf = bundle;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0057 A[Catch: Exception -> 0x00a3, TryCatch #0 {Exception -> 0x00a3, blocks: (B:3:0x0002, B:5:0x001c, B:7:0x002d, B:13:0x0042, B:15:0x0057, B:17:0x0063, B:19:0x0071, B:30:0x0086), top: B:35:0x0002 }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0063 A[Catch: Exception -> 0x00a3, TryCatch #0 {Exception -> 0x00a3, blocks: (B:3:0x0002, B:5:0x001c, B:7:0x002d, B:13:0x0042, B:15:0x0057, B:17:0x0063, B:19:0x0071, B:30:0x0086), top: B:35:0x0002 }] */
    @Override // com.google.android.gms.internal.measurement.zzag.zzb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zza() {
        /*
            r14 = this;
            r0 = 0
            r1 = 1
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch: Exception -> 0x00a3
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch: Exception -> 0x00a3
            r3.<init>()     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag.zza(r2, r3)     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch: Exception -> 0x00a3
            java.lang.String r3 = r14.zzc     // Catch: Exception -> 0x00a3
            java.lang.String r4 = r14.zzd     // Catch: Exception -> 0x00a3
            boolean r2 = com.google.android.gms.internal.measurement.zzag.zza(r2, r3, r4)     // Catch: Exception -> 0x00a3
            r3 = 0
            if (r2 == 0) goto L_0x002a
            java.lang.String r3 = r14.zzd     // Catch: Exception -> 0x00a3
            java.lang.String r2 = r14.zzc     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag r4 = r14.zzg     // Catch: Exception -> 0x00a3
            java.lang.String r4 = com.google.android.gms.internal.measurement.zzag.zzb(r4)     // Catch: Exception -> 0x00a3
            r10 = r2
            r11 = r3
            r9 = r4
            goto L_0x002d
        L_0x002a:
            r9 = r3
            r10 = r9
            r11 = r10
        L_0x002d:
            android.content.Context r2 = r14.zze     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag.zzb(r2)     // Catch: Exception -> 0x00a3
            java.lang.Boolean r2 = com.google.android.gms.internal.measurement.zzag.zzj()     // Catch: Exception -> 0x00a3
            boolean r2 = r2.booleanValue()     // Catch: Exception -> 0x00a3
            if (r2 != 0) goto L_0x0041
            if (r10 == 0) goto L_0x003f
            goto L_0x0041
        L_0x003f:
            r2 = 0
            goto L_0x0042
        L_0x0041:
            r2 = 1
        L_0x0042:
            com.google.android.gms.internal.measurement.zzag r3 = r14.zzg     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag r4 = r14.zzg     // Catch: Exception -> 0x00a3
            android.content.Context r5 = r14.zze     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzv r4 = r4.zza(r5, r2)     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag.zza(r3, r4)     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag r3 = r14.zzg     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzv r3 = com.google.android.gms.internal.measurement.zzag.zzc(r3)     // Catch: Exception -> 0x00a3
            if (r3 != 0) goto L_0x0063
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch: Exception -> 0x00a3
            java.lang.String r2 = com.google.android.gms.internal.measurement.zzag.zzb(r2)     // Catch: Exception -> 0x00a3
            java.lang.String r3 = "Failed to connect to measurement client."
            android.util.Log.w(r2, r3)     // Catch: Exception -> 0x00a3
            return
        L_0x0063:
            android.content.Context r3 = r14.zze     // Catch: Exception -> 0x00a3
            int r3 = com.google.android.gms.internal.measurement.zzag.zzc(r3)     // Catch: Exception -> 0x00a3
            android.content.Context r4 = r14.zze     // Catch: Exception -> 0x00a3
            int r4 = com.google.android.gms.internal.measurement.zzag.zzd(r4)     // Catch: Exception -> 0x00a3
            if (r2 == 0) goto L_0x007c
            int r2 = java.lang.Math.max(r3, r4)     // Catch: Exception -> 0x00a3
            if (r4 >= r3) goto L_0x0079
            r3 = 1
            goto L_0x007a
        L_0x0079:
            r3 = 0
        L_0x007a:
            r8 = r3
            goto L_0x0086
        L_0x007c:
            if (r3 <= 0) goto L_0x007f
            r4 = r3
        L_0x007f:
            if (r3 <= 0) goto L_0x0083
            r2 = 1
            goto L_0x0084
        L_0x0083:
            r2 = 0
        L_0x0084:
            r8 = r2
            r2 = r4
        L_0x0086:
            com.google.android.gms.internal.measurement.zzae r13 = new com.google.android.gms.internal.measurement.zzae     // Catch: Exception -> 0x00a3
            r4 = 29000(0x7148, double:1.4328E-319)
            long r6 = (long) r2     // Catch: Exception -> 0x00a3
            android.os.Bundle r12 = r14.zzf     // Catch: Exception -> 0x00a3
            r3 = r13
            r3.<init>(r4, r6, r8, r9, r10, r11, r12)     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzag r2 = r14.zzg     // Catch: Exception -> 0x00a3
            com.google.android.gms.internal.measurement.zzv r2 = com.google.android.gms.internal.measurement.zzag.zzc(r2)     // Catch: Exception -> 0x00a3
            android.content.Context r3 = r14.zze     // Catch: Exception -> 0x00a3
            com.google.android.gms.dynamic.IObjectWrapper r3 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r3)     // Catch: Exception -> 0x00a3
            long r4 = r14.zza     // Catch: Exception -> 0x00a3
            r2.initialize(r3, r13, r4)     // Catch: Exception -> 0x00a3
            return
        L_0x00a3:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzag r3 = r14.zzg
            com.google.android.gms.internal.measurement.zzag.zza(r3, r2, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzaj.zza():void");
    }
}
