package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzaw extends zzbb {
    private final /* synthetic */ zzax zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzaw(zzax zzaxVar, zzau zzauVar, CharSequence charSequence) {
        super(zzauVar, charSequence);
        this.zzb = zzaxVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbb
    final int zza(int i) {
        return this.zzb.zza.zza(this.zza, i);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbb
    final int zzb(int i) {
        return i + 1;
    }
}
