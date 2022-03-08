package com.google.android.gms.internal.firebase_auth;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
final class zzay extends zzbb {
    private final /* synthetic */ zzam zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzay(zzaz zzazVar, zzau zzauVar, CharSequence charSequence, zzam zzamVar) {
        super(zzauVar, charSequence);
        this.zzb = zzamVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbb
    public final int zza(int i) {
        if (this.zzb.zza(i)) {
            return this.zzb.zzc();
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzbb
    public final int zzb(int i) {
        return this.zzb.zzb();
    }
}
