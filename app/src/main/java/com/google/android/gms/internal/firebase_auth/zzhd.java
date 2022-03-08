package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzhd {
    private final zzhm zza;
    private final byte[] zzb;

    private zzhd(int i) {
        byte[] bArr = new byte[i];
        this.zzb = bArr;
        this.zza = zzhm.zza(bArr);
    }

    public final zzgv zza() {
        this.zza.zzb();
        return new zzhf(this.zzb);
    }

    public final zzhm zzb() {
        return this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhd(int i, zzgu zzguVar) {
        this(i);
    }
}
