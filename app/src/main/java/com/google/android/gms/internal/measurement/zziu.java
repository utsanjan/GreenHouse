package com.google.android.gms.internal.measurement;

/* JADX WARN: Init of enum zzi can be incorrect */
/* JADX WARN: Init of enum zzj can be incorrect */
/* JADX WARN: Init of enum zzk can be incorrect */
/* JADX WARN: Init of enum zzl can be incorrect */
/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public enum zziu {
    DOUBLE(zzjb.DOUBLE, 1),
    FLOAT(zzjb.FLOAT, 5),
    INT64(zzjb.LONG, 0),
    UINT64(zzjb.LONG, 0),
    INT32(zzjb.INT, 0),
    FIXED64(zzjb.LONG, 1),
    FIXED32(zzjb.INT, 5),
    BOOL(zzjb.BOOLEAN, 0),
    STRING(r1, 2) { // from class: com.google.android.gms.internal.measurement.zzix
    },
    GROUP(r1, 3) { // from class: com.google.android.gms.internal.measurement.zziw
    },
    MESSAGE(r1, 2) { // from class: com.google.android.gms.internal.measurement.zziz
    },
    BYTES(r1, 2) { // from class: com.google.android.gms.internal.measurement.zziy
    },
    UINT32(zzjb.INT, 0),
    ENUM(zzjb.ENUM, 0),
    SFIXED32(zzjb.INT, 5),
    SFIXED64(zzjb.LONG, 1),
    SINT32(zzjb.INT, 0),
    SINT64(zzjb.LONG, 0);
    
    private final zzjb zzs;
    private final int zzt;

    zziu(zzjb zzjbVar, int i) {
        this.zzs = zzjbVar;
        this.zzt = i;
    }

    public final zzjb zza() {
        return this.zzs;
    }

    public final int zzb() {
        return this.zzt;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* synthetic */ zziu(zzjb zzjbVar, int i, zziv zzivVar) {
        this(zzjbVar, i);
    }

    static {
        final zzjb zzjbVar = zzjb.STRING;
        final zzjb zzjbVar2 = zzjb.MESSAGE;
        final zzjb zzjbVar3 = zzjb.MESSAGE;
        final zzjb zzjbVar4 = zzjb.BYTE_STRING;
    }
}
