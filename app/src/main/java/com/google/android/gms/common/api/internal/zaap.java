package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zad;
import com.google.android.gms.signin.internal.zak;
import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
final class zaap extends zad {
    private final WeakReference<zaak> zago;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaap(zaak zaakVar) {
        this.zago = new WeakReference<>(zaakVar);
    }

    @Override // com.google.android.gms.signin.internal.zad, com.google.android.gms.signin.internal.zac
    public final void zab(zak zakVar) {
        zabe zabeVar;
        zaak zaakVar = this.zago.get();
        if (zaakVar != null) {
            zabeVar = zaakVar.zafv;
            zabeVar.zaa(new zaas(this, zaakVar, zaakVar, zakVar));
        }
    }
}
