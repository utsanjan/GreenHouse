package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement@@17.4.3 */
/* loaded from: classes.dex */
public final class zzkl implements zzfd {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzkj zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkl(zzkj zzkjVar, String str) {
        this.zzb = zzkjVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzfd
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zza(i, th, bArr, this.zza);
    }
}
