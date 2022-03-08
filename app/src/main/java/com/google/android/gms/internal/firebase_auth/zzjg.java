package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzjg implements zzjo {
    private zzjo[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzjg(zzjo... zzjoVarArr) {
        this.zza = zzjoVarArr;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjo
    public final boolean zza(Class<?> cls) {
        for (zzjo zzjoVar : this.zza) {
            if (zzjoVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjo
    public final zzjl zzb(Class<?> cls) {
        zzjo[] zzjoVarArr;
        for (zzjo zzjoVar : this.zza) {
            if (zzjoVar.zza(cls)) {
                return zzjoVar.zzb(cls);
            }
        }
        String valueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(valueOf.length() != 0 ? "No factory is available for message type: ".concat(valueOf) : new String("No factory is available for message type: "));
    }
}
