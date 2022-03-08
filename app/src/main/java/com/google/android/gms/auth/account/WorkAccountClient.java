package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.internal.auth.zzh;
import com.google.android.gms.tasks.Task;

/* loaded from: classes.dex */
public class WorkAccountClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private final WorkAccountApi zzac = new zzh();

    /* JADX INFO: Access modifiers changed from: package-private */
    public WorkAccountClient(Context context) {
        super(context, WorkAccount.API, (Api.ApiOptions) null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public WorkAccountClient(Activity activity) {
        super(activity, (Api<Api.ApiOptions>) WorkAccount.API, (Api.ApiOptions) null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> setWorkAuthenticatorEnabled(boolean z) {
        return PendingResultUtil.toVoidTask(this.zzac.setWorkAuthenticatorEnabledWithResult(asGoogleApiClient(), z));
    }

    public Task<Account> addWorkAccount(String str) {
        return PendingResultUtil.toTask(this.zzac.addWorkAccount(asGoogleApiClient(), str), new zzg(this));
    }

    public Task<Void> removeWorkAccount(Account account) {
        return PendingResultUtil.toVoidTask(this.zzac.removeWorkAccount(asGoogleApiClient(), account));
    }
}
