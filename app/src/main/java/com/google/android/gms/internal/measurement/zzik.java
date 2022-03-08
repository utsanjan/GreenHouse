package com.google.android.gms.internal.measurement;

import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzik implements Iterator<String> {
    private Iterator<String> zza;
    private final /* synthetic */ zzii zzb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzik(zzii zziiVar) {
        zzgh zzghVar;
        this.zzb = zziiVar;
        zzghVar = this.zzb.zza;
        this.zza = zzghVar.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza.hasNext();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public final /* synthetic */ String next() {
        return this.zza.next();
    }
}
