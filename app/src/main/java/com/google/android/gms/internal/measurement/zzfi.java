package com.google.android.gms.internal.measurement;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
final /* synthetic */ class zzfi {
    static final /* synthetic */ int[] zza;
    static final /* synthetic */ int[] zzb;

    static {
        int[] iArr = new int[zzfy.values().length];
        zzb = iArr;
        try {
            iArr[zzfy.BYTE_STRING.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            zzb[zzfy.MESSAGE.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            zzb[zzfy.STRING.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        int[] iArr2 = new int[zzfl.values().length];
        zza = iArr2;
        try {
            iArr2[zzfl.MAP.ordinal()] = 1;
        } catch (NoSuchFieldError e4) {
        }
        try {
            zza[zzfl.VECTOR.ordinal()] = 2;
        } catch (NoSuchFieldError e5) {
        }
        try {
            zza[zzfl.SCALAR.ordinal()] = 3;
        } catch (NoSuchFieldError e6) {
        }
    }
}
