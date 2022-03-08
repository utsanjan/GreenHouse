package com.google.android.gms.common.config;

import com.google.android.gms.common.config.GservicesValue;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
final class zzd extends GservicesValue<String> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzd(String str, String str2) {
        super(str, str2);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ String zzd(String str) {
        GservicesValue.zza zzaVar = null;
        return zzaVar.getString(this.mKey, (String) this.zzcc);
    }
}
