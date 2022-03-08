package com.google.android.gms.internal.firebase_auth;

import andhook.lib.xposed.ClassUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zzau {
    private final zzaf zza;
    private final boolean zzb;
    private final zzba zzc;
    private final int zzd;

    private zzau(zzba zzbaVar) {
        this(zzbaVar, false, zzaj.zza, Integer.MAX_VALUE);
    }

    private zzau(zzba zzbaVar, boolean z, zzaf zzafVar, int i) {
        this.zzc = zzbaVar;
        this.zzb = false;
        this.zza = zzafVar;
        this.zzd = Integer.MAX_VALUE;
    }

    public static zzau zza(char c) {
        zzah zzahVar = new zzah(ClassUtils.PACKAGE_SEPARATOR_CHAR);
        zzav.zza(zzahVar);
        return new zzau(new zzax(zzahVar));
    }

    public static zzau zza(String str) {
        zzal zza = zzar.zza(str);
        if (!zza.zza("").zza()) {
            return new zzau(new zzaz(zza));
        }
        throw new IllegalArgumentException(zzbd.zza("The pattern may not match the empty string: %s", zza));
    }

    public final List<String> zza(CharSequence charSequence) {
        zzav.zza(charSequence);
        Iterator<String> zza = this.zzc.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (zza.hasNext()) {
            arrayList.add(zza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
