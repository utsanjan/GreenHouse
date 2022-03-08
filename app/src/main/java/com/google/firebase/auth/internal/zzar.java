package com.google.firebase.auth.internal;

import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.firebase_auth.zzbl;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.api.zza;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzar {
    private static final Logger zza = new Logger("GetTokenResultFactory", new String[0]);

    public static GetTokenResult zza(String str) {
        Map map;
        try {
            map = zzaq.zza(str);
        } catch (zza e) {
            zza.e("Error parsing token claims", e, new Object[0]);
            map = zzbl.zza();
        }
        return new GetTokenResult(str, map);
    }
}
