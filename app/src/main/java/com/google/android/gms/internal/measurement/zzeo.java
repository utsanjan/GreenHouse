package com.google.android.gms.internal.measurement;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzeo {
    private final zzev zza;
    private final byte[] zzb;

    private zzeo(int i) {
        byte[] bArr = new byte[i];
        this.zzb = bArr;
        this.zza = zzev.zza(bArr);
    }

    public final zzeg zza() {
        this.zza.zzb();
        return new zzeq(this.zzb);
    }

    public final zzev zzb() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzeo(int i, zzef zzefVar) {
        this(i);
    }
}
