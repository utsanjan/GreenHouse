package com.google.android.gms.internal.auth;

import android.accounts.Account;
import com.google.android.gms.common.api.Status;

/* loaded from: classes.dex */
final class zzk extends zzn {
    private final /* synthetic */ zzj zzaf;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzk(zzj zzjVar) {
        this.zzaf = zzjVar;
    }

    @Override // com.google.android.gms.internal.auth.zzn, com.google.android.gms.auth.account.zza
    public final void zzc(Account account) {
        this.zzaf.setResult((zzj) new zzo(account != null ? Status.RESULT_SUCCESS : zzh.zzad, account));
    }
}
