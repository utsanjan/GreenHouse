package com.google.android.gms.auth.api.identity;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.p001authapi.zzaf;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public final class Identity {
    private Identity() {
    }

    public static SignInClient getSignInClient(Activity activity) {
        return new zzaf((Activity) Preconditions.checkNotNull(activity), SignInOptions.builder().build());
    }

    public static SignInClient getSignInClient(Context context) {
        return new zzaf((Context) Preconditions.checkNotNull(context), SignInOptions.builder().build());
    }
}
