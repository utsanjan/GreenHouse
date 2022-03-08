package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final class zzhv extends zzib {
    private final /* synthetic */ zzhq zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzhv(zzhq zzhqVar) {
        super(zzhqVar, null);
        this.zza = zzhqVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzib, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator<Map.Entry<K, V>> iterator() {
        return new zzhs(this.zza, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzhv(zzhq zzhqVar, zzht zzhtVar) {
        this(zzhqVar);
    }
}
