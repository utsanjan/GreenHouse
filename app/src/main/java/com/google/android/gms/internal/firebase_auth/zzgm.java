package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzgm;
import com.google.android.gms.internal.firebase_auth.zzgn;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public abstract class zzgm<MessageType extends zzgn<MessageType, BuilderType>, BuilderType extends zzgm<MessageType, BuilderType>> implements zzjq {
    /* renamed from: zza */
    public abstract BuilderType clone();

    protected abstract BuilderType zza(MessageType messagetype);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.firebase_auth.zzjq
    public final /* synthetic */ zzjq zza(zzjn zzjnVar) {
        if (h_().getClass().isInstance(zzjnVar)) {
            return zza((zzgm<MessageType, BuilderType>) ((zzgn) zzjnVar));
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
