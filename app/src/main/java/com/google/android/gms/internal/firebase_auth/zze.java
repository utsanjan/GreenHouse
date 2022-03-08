package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.common.Feature;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public final class zze {
    public static final Feature zzb;
    public static final Feature[] zzc;
    private static final Feature zzd = new Feature("firebase_auth", 11);
    public static final Feature zza = new Feature("firebase_auth_aidl_migration", 1);

    static {
        Feature feature = new Feature("firebase_auth_multi_factor_auth", 1L);
        zzb = feature;
        zzc = new Feature[]{zzd, zza, feature};
    }
}
