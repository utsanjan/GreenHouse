package com.google.firebase.crashlytics.internal.unity;

import android.content.Context;
import com.google.firebase.crashlytics.internal.common.CommonUtils;

/* loaded from: classes.dex */
public class ResourceUnityVersionProvider implements UnityVersionProvider {
    private final Context context;
    private boolean hasRead = false;
    private String unityVersion;

    public ResourceUnityVersionProvider(Context context) {
        this.context = context;
    }

    @Override // com.google.firebase.crashlytics.internal.unity.UnityVersionProvider
    public String getUnityVersion() {
        if (!this.hasRead) {
            this.unityVersion = CommonUtils.resolveUnityEditorVersion(this.context);
            this.hasRead = true;
        }
        String str = this.unityVersion;
        if (str != null) {
            return str;
        }
        return null;
    }
}
