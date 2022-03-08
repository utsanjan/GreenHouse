package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.internal.auth.zzt;
import com.google.android.gms.internal.auth.zzu;

/* loaded from: classes.dex */
public final class AccountTransfer {
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    public static final String KEY_EXTRA_ACCOUNT_TYPE = "key_extra_account_type";
    private static final Api.ClientKey<zzu> zzaj = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzu, zzn> zzak = new zza();
    private static final Api<zzn> zzal = new Api<>("AccountTransfer.ACCOUNT_TRANSFER_API", zzak, zzaj);
    @Deprecated
    private static final zzb zzam = new zzt();
    private static final zzq zzan = new zzt();

    public static AccountTransferClient getAccountTransferClient(Activity activity) {
        return new AccountTransferClient(activity);
    }

    public static AccountTransferClient getAccountTransferClient(Context context) {
        return new AccountTransferClient(context);
    }

    private AccountTransfer() {
    }
}
