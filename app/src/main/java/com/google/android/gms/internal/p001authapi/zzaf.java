package com.google.android.gms.internal.p001authapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.identity.SignInOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import com.google.android.gms.common.api.internal.RemoteCall;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* renamed from: com.google.android.gms.internal.auth-api.zzaf  reason: invalid package */
/* loaded from: classes.dex */
public final class zzaf extends GoogleApi<SignInOptions> implements SignInClient {
    private static final Api.ClientKey<zzak> CLIENT_KEY = new Api.ClientKey<>();
    private static final Api.AbstractClientBuilder<zzak, SignInOptions> zzbj = new zzag();
    private static final Api<SignInOptions> API = new Api<>("Auth.Api.Identity.SignIn.API", zzbj, CLIENT_KEY);

    public zzaf(Context context, SignInOptions signInOptions) {
        super(context, API, SignInOptions.Builder.zzc(signInOptions).zze(zzal.zzr()).build(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public zzaf(Activity activity, SignInOptions signInOptions) {
        super(activity, API, SignInOptions.Builder.zzc(signInOptions).zze(zzal.zzr()).build(), GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<BeginSignInResult> beginSignIn(BeginSignInRequest beginSignInRequest) {
        final BeginSignInRequest build = BeginSignInRequest.zzc(beginSignInRequest).zzd(getApiOptions().zzf()).build();
        return doRead(TaskApiCall.builder().setFeatures(zzam.zzcw).run(new RemoteCall(this, build) { // from class: com.google.android.gms.internal.auth-api.zzae
            private final zzaf zzbh;
            private final BeginSignInRequest zzbi;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzbh = this;
                this.zzbi = build;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzaf zzafVar = this.zzbh;
                BeginSignInRequest beginSignInRequest2 = this.zzbi;
                ((zzad) ((zzak) obj).getService()).zzc(new zzaj(zzafVar, (TaskCompletionSource) obj2), (BeginSignInRequest) Preconditions.checkNotNull(beginSignInRequest2));
            }
        }).setAutoResolveMissingFeatures(false).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final Task<Void> signOut() {
        getApplicationContext().getSharedPreferences("com.google.android.gms.signin", 0).edit().clear().apply();
        for (GoogleApiClient googleApiClient : GoogleApiClient.getAllClients()) {
            googleApiClient.maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
        return doRead(TaskApiCall.builder().setFeatures(zzam.zzcx).run(new RemoteCall(this) { // from class: com.google.android.gms.internal.auth-api.zzah
            private final zzaf zzbh;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.zzbh = this;
            }

            @Override // com.google.android.gms.common.api.internal.RemoteCall
            public final void accept(Object obj, Object obj2) {
                zzaf zzafVar = this.zzbh;
                ((zzad) ((zzak) obj).getService()).zzc(new zzai(zzafVar, (TaskCompletionSource) obj2), zzafVar.getApiOptions().zzf());
            }
        }).setAutoResolveMissingFeatures(false).build());
    }

    @Override // com.google.android.gms.auth.api.identity.SignInClient
    public final SignInCredential getSignInCredentialFromIntent(Intent intent) throws ApiException {
        if (intent != null) {
            Status status = (Status) SafeParcelableSerializer.deserializeFromIntentExtra(intent, NotificationCompat.CATEGORY_STATUS, Status.CREATOR);
            if (status == null) {
                throw new ApiException(Status.RESULT_CANCELED);
            } else if (status.isSuccess()) {
                SignInCredential signInCredential = (SignInCredential) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "sign_in_credential", SignInCredential.CREATOR);
                if (signInCredential != null) {
                    return signInCredential;
                }
                throw new ApiException(Status.RESULT_INTERNAL_ERROR);
            } else {
                throw new ApiException(status);
            }
        } else {
            throw new ApiException(Status.RESULT_INTERNAL_ERROR);
        }
    }
}
