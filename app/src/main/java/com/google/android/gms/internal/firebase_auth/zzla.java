package com.google.android.gms.internal.firebase_auth;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzla extends AbstractList<String> implements zziy, RandomAccess {
    private final zziy zza;

    public zzla(zziy zziyVar) {
        this.zza = zziyVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziy
    public final Object zzb(int i) {
        return this.zza.zzb(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zza.size();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziy
    public final void zza(zzgv zzgvVar) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator<String> listIterator(int i) {
        return new zzld(this, i);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator<String> iterator() {
        return new zzlc(this);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziy
    public final List<?> zzd() {
        return this.zza.zzd();
    }

    @Override // com.google.android.gms.internal.firebase_auth.zziy
    public final zziy zze() {
        return this;
    }

    @Override // java.util.AbstractList, java.util.List
    public final /* synthetic */ Object get(int i) {
        return (String) this.zza.get(i);
    }
}
