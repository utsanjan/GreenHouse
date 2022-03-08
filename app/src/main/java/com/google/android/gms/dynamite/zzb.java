package com.google.android.gms.dynamite;

import android.content.Context;
import com.google.android.gms.dynamite.DynamiteModule;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
final class zzb implements DynamiteModule.VersionPolicy {
    @Override // com.google.android.gms.dynamite.DynamiteModule.VersionPolicy
    public final DynamiteModule.VersionPolicy.zza zza(Context context, String str, DynamiteModule.VersionPolicy.zzb zzbVar) throws DynamiteModule.LoadingException {
        DynamiteModule.VersionPolicy.zza zzaVar = new DynamiteModule.VersionPolicy.zza();
        zzaVar.zzjg = zzbVar.getLocalVersion(context, str);
        if (zzaVar.zzjg != 0) {
            zzaVar.zzji = -1;
        } else {
            zzaVar.zzjh = zzbVar.zza(context, str, true);
            if (zzaVar.zzjh != 0) {
                zzaVar.zzji = 1;
            }
        }
        return zzaVar;
    }
}
