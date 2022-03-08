package com.google.android.gms.auth.api.credentials;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.auth.api.Auth;

/* compiled from: com.google.android.gms:play-services-auth@@18.0.0 */
/* loaded from: classes.dex */
public class Credentials {
    public static CredentialsClient getClient(Activity activity) {
        return new CredentialsClient(activity, (Auth.AuthCredentialsOptions) CredentialsOptions.DEFAULT);
    }

    public static CredentialsClient getClient(Activity activity, CredentialsOptions credentialsOptions) {
        return new CredentialsClient(activity, (Auth.AuthCredentialsOptions) credentialsOptions);
    }

    public static CredentialsClient getClient(Context context) {
        return new CredentialsClient(context, CredentialsOptions.DEFAULT);
    }

    public static CredentialsClient getClient(Context context, CredentialsOptions credentialsOptions) {
        return new CredentialsClient(context, credentialsOptions);
    }
}
