package com.google.android.gms.common.config;

import com.google.android.gms.common.config.GservicesValue;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
final class zzc extends GservicesValue<Long> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzc(String str, Long l) {
        super(str, l);
    }

    @Override // com.google.android.gms.common.config.GservicesValue
    protected final /* synthetic */ Long zzd(String str) {
        GservicesValue.zza zzaVar = null;
        return zzaVar.getLong(this.mKey, (Long) this.zzcc);
    }
}
