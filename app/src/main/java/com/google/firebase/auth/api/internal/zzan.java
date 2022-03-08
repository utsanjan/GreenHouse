package com.google.firebase.auth.api.internal;

import android.util.Log;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzan implements zzas {
    private final int zza;
    private final int zzb;
    private final Map<String, Integer> zzc;

    public zzan(int i, int i2, Map<String, Integer> map) {
        this.zza = zza() ? 0 : i;
        this.zzb = i2;
        this.zzc = (Map) Preconditions.checkNotNull(map);
        zza();
    }

    @Override // com.google.firebase.auth.api.internal.zzas
    public final boolean zza(String str) {
        int i = this.zza;
        if (i == 0) {
            return true;
        }
        if (this.zzb <= i) {
            return false;
        }
        Integer num = this.zzc.get(str);
        if (num == null) {
            num = 0;
        }
        return num.intValue() > this.zza && this.zzb >= num.intValue();
    }

    private static boolean zza() {
        boolean equals = ImagesContract.LOCAL.equals(zzfx.zza("firebear.preference"));
        if (equals) {
            Log.e("BiChannelGoogleApi", "Found local preference, will always use local service instance");
        }
        return equals;
    }
}
