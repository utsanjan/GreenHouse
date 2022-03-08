package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions> {
    private static final zzc zzbm = new zzc(null);
    private static int zzbn = zzd.zzbq;

    /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
    /* loaded from: classes.dex */
    private static class zzc implements PendingResultUtil.ResultConverter<GoogleSignInResult, GoogleSignInAccount> {
        private zzc() {
        }

        @Override // com.google.android.gms.common.internal.PendingResultUtil.ResultConverter
        public final /* synthetic */ GoogleSignInAccount convert(GoogleSignInResult googleSignInResult) {
            return googleSignInResult.getSignInAccount();
        }

        /* synthetic */ zzc(zzc zzcVar) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
    /* loaded from: classes.dex */
    public static final class zzd {
        public static final int zzbq = 1;
        public static final int zzbr = 2;
        public static final int zzbs = 3;
        public static final int zzbt = 4;
        private static final /* synthetic */ int[] zzbu = {1, 2, 3, 4};

        public static int[] zzh() {
            return (int[]) zzbu.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInClient(Context context, GoogleSignInOptions googleSignInOptions) {
        super(context, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, new ApiExceptionMapper());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleSignInClient(Activity activity, GoogleSignInOptions googleSignInOptions) {
        super(activity, Auth.GOOGLE_SIGN_IN_API, googleSignInOptions, (StatusExceptionMapper) new ApiExceptionMapper());
    }

    private final synchronized int zzg() {
        if (zzbn == zzd.zzbq) {
            Context applicationContext = getApplicationContext();
            GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
            int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(applicationContext, 12451000);
            if (isGooglePlayServicesAvailable == 0) {
                zzbn = zzd.zzbt;
            } else if (instance.getErrorResolutionIntent(applicationContext, isGooglePlayServicesAvailable, null) != null || DynamiteModule.getLocalVersion(applicationContext, "com.google.android.gms.auth.api.fallback") == 0) {
                zzbn = zzd.zzbr;
            } else {
                zzbn = zzd.zzbs;
            }
        }
        return zzbn;
    }

    public Intent getSignInIntent() {
        Context applicationContext = getApplicationContext();
        int i = zzc.zzbo[zzg() - 1];
        if (i == 1) {
            return zzg.zzd(applicationContext, getApiOptions());
        }
        if (i != 2) {
            return zzg.zze(applicationContext, getApiOptions());
        }
        return zzg.zzc(applicationContext, getApiOptions());
    }

    public Task<GoogleSignInAccount> silentSignIn() {
        return PendingResultUtil.toTask(zzg.zzc(asGoogleApiClient(), getApplicationContext(), getApiOptions(), zzg() == zzd.zzbs), zzbm);
    }

    public Task<Void> signOut() {
        return PendingResultUtil.toVoidTask(zzg.zzc(asGoogleApiClient(), getApplicationContext(), zzg() == zzd.zzbs));
    }

    public Task<Void> revokeAccess() {
        return PendingResultUtil.toVoidTask(zzg.zzd(asGoogleApiClient(), getApplicationContext(), zzg() == zzd.zzbs));
    }
}
