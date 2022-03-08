package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzef extends zzeh {
    private int zza = 0;
    private final int zzb;
    private final /* synthetic */ zzeg zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzef(zzeg zzegVar) {
        this.zzc = zzegVar;
        this.zzb = this.zzc.zza();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.zza < this.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzel
    public final byte zza() {
        int i = this.zza;
        if (i < this.zzb) {
            this.zza = i + 1;
            return this.zzc.zzb(i);
        }
        throw new NoSuchElementException();
    }
}
