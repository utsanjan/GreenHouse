package com.google.android.gms.internal.firebase_auth;

/* JADX WARN: Init of enum zzi can be incorrect */
/* JADX WARN: Init of enum zzj can be incorrect */
/* JADX WARN: Init of enum zzk can be incorrect */
/* JADX WARN: Init of enum zzl can be incorrect */
/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public enum zzlm {
    DOUBLE(zzlt.DOUBLE, 1),
    FLOAT(zzlt.FLOAT, 5),
    INT64(zzlt.LONG, 0),
    UINT64(zzlt.LONG, 0),
    INT32(zzlt.INT, 0),
    FIXED64(zzlt.LONG, 1),
    FIXED32(zzlt.INT, 5),
    BOOL(zzlt.BOOLEAN, 0),
    STRING(r1, 2) { // from class: com.google.android.gms.internal.firebase_auth.zzlp
    },
    GROUP(r1, 3) { // from class: com.google.android.gms.internal.firebase_auth.zzlo
    },
    MESSAGE(r1, 2) { // from class: com.google.android.gms.internal.firebase_auth.zzlr
    },
    BYTES(r1, 2) { // from class: com.google.android.gms.internal.firebase_auth.zzlq
    },
    UINT32(zzlt.INT, 0),
    ENUM(zzlt.ENUM, 0),
    SFIXED32(zzlt.INT, 5),
    SFIXED64(zzlt.LONG, 1),
    SINT32(zzlt.INT, 0),
    SINT64(zzlt.LONG, 0);
    
    private final zzlt zzs;
    private final int zzt;

    zzlm(zzlt zzltVar, int i) {
        this.zzs = zzltVar;
        this.zzt = i;
    }

    public final zzlt zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* synthetic */ zzlm(zzlt zzltVar, int i, zzln zzlnVar) {
        this(zzltVar, i);
    }

    static {
        final zzlt zzltVar = zzlt.STRING;
        final zzlt zzltVar2 = zzlt.MESSAGE;
        final zzlt zzltVar3 = zzlt.MESSAGE;
        final zzlt zzltVar4 = zzlt.BYTE_STRING;
    }
}
