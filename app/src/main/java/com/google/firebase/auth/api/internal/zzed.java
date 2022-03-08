package com.google.firebase.auth.api.internal;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApi;
import com.google.firebase.FirebaseExceptionMapper;
import java.util.concurrent.Callable;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzed implements Callable<zzal<zzeu>> {
    private final zzeu zza;
    private final Context zzb;

    public zzed(zzeu zzeuVar, Context context) {
        this.zza = zzeuVar;
        this.zzb = context;
    }

    private final GoogleApi<zzeu> zza(boolean z, Context context) {
        zzeu zzeuVar = (zzeu) this.zza.clone();
        zzeuVar.zza = z;
        return new zzaq(context, zzet.zza, zzeuVar, new FirebaseExceptionMapper());
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x000e, code lost:
        if (r0 == (-1)) goto L_0x0010;
     */
    @Override // java.util.concurrent.Callable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzal<com.google.firebase.auth.api.internal.zzeu> call() throws java.lang.Exception {
        /*
            r6 = this;
            int r0 = com.google.firebase.auth.api.internal.zzee.zzb()
            r1 = 0
            r2 = 1
            r3 = -1
            if (r0 == r3) goto L_0x0010
            int r0 = com.google.firebase.auth.api.internal.zzee.zzc()
            if (r0 != r3) goto L_0x003f
        L_0x0010:
            android.content.Context r0 = r6.zzb
            java.lang.String r3 = "com.google.firebase.auth"
            int r0 = com.google.android.gms.dynamite.DynamiteModule.getLocalVersion(r0, r3)
            if (r0 != 0) goto L_0x001c
            r3 = 1
            goto L_0x0039
        L_0x001c:
            com.google.android.gms.common.GoogleApiAvailability r3 = com.google.android.gms.common.GoogleApiAvailability.getInstance()
            android.content.Context r4 = r6.zzb
            r5 = 12451000(0xbdfcb8, float:1.7447567E-38)
            int r3 = r3.isGooglePlayServicesAvailable(r4, r5)
            if (r3 == 0) goto L_0x0030
            r4 = 2
            if (r3 == r4) goto L_0x0030
            r3 = 0
            goto L_0x0039
        L_0x0030:
            android.content.Context r3 = r6.zzb
            java.lang.String r4 = "com.google.android.gms.firebase_auth"
            int r3 = com.google.android.gms.dynamite.DynamiteModule.getRemoteVersion(r3, r4)
        L_0x0039:
            com.google.firebase.auth.api.internal.zzee.zza(r3)
            com.google.firebase.auth.api.internal.zzee.zzb(r0)
        L_0x003f:
            int r0 = com.google.firebase.auth.api.internal.zzee.zzc()
            r3 = 0
            if (r0 == 0) goto L_0x004d
            android.content.Context r0 = r6.zzb
            com.google.android.gms.common.api.GoogleApi r0 = r6.zza(r2, r0)
            goto L_0x004e
        L_0x004d:
            r0 = r3
        L_0x004e:
            int r2 = com.google.firebase.auth.api.internal.zzee.zzb()
            if (r2 != 0) goto L_0x0055
            goto L_0x005b
        L_0x0055:
            android.content.Context r2 = r6.zzb
            com.google.android.gms.common.api.GoogleApi r3 = r6.zza(r1, r2)
        L_0x005b:
            com.google.firebase.auth.api.internal.zzan r1 = new com.google.firebase.auth.api.internal.zzan
            int r2 = com.google.firebase.auth.api.internal.zzee.zzb()
            int r4 = com.google.firebase.auth.api.internal.zzee.zzc()
            java.util.Map r5 = java.util.Collections.emptyMap()
            r1.<init>(r2, r4, r5)
            com.google.firebase.auth.api.internal.zzal r2 = new com.google.firebase.auth.api.internal.zzal
            r2.<init>(r3, r0, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.api.internal.zzed.call():java.lang.Object");
    }
}
