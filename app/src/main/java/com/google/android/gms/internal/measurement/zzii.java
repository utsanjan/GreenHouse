package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public final class zzii extends AbstractList<String> implements zzgh, RandomAccess {
    private final zzgh zza;

    public zzii(zzgh zzghVar) {
        this.zza = zzghVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzgh
    public final Object zzb(int i) {
        return this.zza.zzb(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.measurement.zzgh
    public final void zza(zzeg zzegVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zzil(this, i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzik(this);
    }

    @Override // com.google.android.gms.internal.measurement.zzgh
    public final List<?> zzd() {
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzgh
    public final zzgh zze() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.zza.get(i);
    }
}
