package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.auth.api.accounttransfer.AccountTransferClient;

/* loaded from: classes.dex */
final class zzh extends AccountTransferClient.zza<DeviceMetaData> {
    private final /* synthetic */ zzg zzas;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzh(zzg zzgVar, AccountTransferClient.zzb zzbVar) {
        super(zzbVar);
        this.zzas = zzgVar;
    }

    @Override // com.google.android.gms.internal.auth.zzs, com.google.android.gms.internal.auth.zzx
    public final void zza(DeviceMetaData deviceMetaData) {
        this.zzas.setResult(deviceMetaData);
    }
}
