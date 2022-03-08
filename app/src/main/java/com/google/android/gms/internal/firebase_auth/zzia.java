package com.google.android.gms.internal.firebase_auth;

import java.lang.reflect.Type;

/* compiled from: com.google.firebase:firebase-auth@@19.3.1 */
/* loaded from: classes.dex */
public enum zzia {
    DOUBLE(0, zzic.SCALAR, zzip.DOUBLE),
    FLOAT(1, zzic.SCALAR, zzip.FLOAT),
    INT64(2, zzic.SCALAR, zzip.LONG),
    UINT64(3, zzic.SCALAR, zzip.LONG),
    INT32(4, zzic.SCALAR, zzip.INT),
    FIXED64(5, zzic.SCALAR, zzip.LONG),
    FIXED32(6, zzic.SCALAR, zzip.INT),
    BOOL(7, zzic.SCALAR, zzip.BOOLEAN),
    STRING(8, zzic.SCALAR, zzip.STRING),
    MESSAGE(9, zzic.SCALAR, zzip.MESSAGE),
    BYTES(10, zzic.SCALAR, zzip.BYTE_STRING),
    UINT32(11, zzic.SCALAR, zzip.INT),
    ENUM(12, zzic.SCALAR, zzip.ENUM),
    SFIXED32(13, zzic.SCALAR, zzip.INT),
    SFIXED64(14, zzic.SCALAR, zzip.LONG),
    SINT32(15, zzic.SCALAR, zzip.INT),
    SINT64(16, zzic.SCALAR, zzip.LONG),
    GROUP(17, zzic.SCALAR, zzip.MESSAGE),
    DOUBLE_LIST(18, zzic.VECTOR, zzip.DOUBLE),
    FLOAT_LIST(19, zzic.VECTOR, zzip.FLOAT),
    INT64_LIST(20, zzic.VECTOR, zzip.LONG),
    UINT64_LIST(21, zzic.VECTOR, zzip.LONG),
    INT32_LIST(22, zzic.VECTOR, zzip.INT),
    FIXED64_LIST(23, zzic.VECTOR, zzip.LONG),
    FIXED32_LIST(24, zzic.VECTOR, zzip.INT),
    BOOL_LIST(25, zzic.VECTOR, zzip.BOOLEAN),
    STRING_LIST(26, zzic.VECTOR, zzip.STRING),
    MESSAGE_LIST(27, zzic.VECTOR, zzip.MESSAGE),
    BYTES_LIST(28, zzic.VECTOR, zzip.BYTE_STRING),
    UINT32_LIST(29, zzic.VECTOR, zzip.INT),
    ENUM_LIST(30, zzic.VECTOR, zzip.ENUM),
    SFIXED32_LIST(31, zzic.VECTOR, zzip.INT),
    SFIXED64_LIST(32, zzic.VECTOR, zzip.LONG),
    SINT32_LIST(33, zzic.VECTOR, zzip.INT),
    SINT64_LIST(34, zzic.VECTOR, zzip.LONG),
    DOUBLE_LIST_PACKED(35, zzic.PACKED_VECTOR, zzip.DOUBLE),
    FLOAT_LIST_PACKED(36, zzic.PACKED_VECTOR, zzip.FLOAT),
    INT64_LIST_PACKED(37, zzic.PACKED_VECTOR, zzip.LONG),
    UINT64_LIST_PACKED(38, zzic.PACKED_VECTOR, zzip.LONG),
    INT32_LIST_PACKED(39, zzic.PACKED_VECTOR, zzip.INT),
    FIXED64_LIST_PACKED(40, zzic.PACKED_VECTOR, zzip.LONG),
    FIXED32_LIST_PACKED(41, zzic.PACKED_VECTOR, zzip.INT),
    BOOL_LIST_PACKED(42, zzic.PACKED_VECTOR, zzip.BOOLEAN),
    UINT32_LIST_PACKED(43, zzic.PACKED_VECTOR, zzip.INT),
    ENUM_LIST_PACKED(44, zzic.PACKED_VECTOR, zzip.ENUM),
    SFIXED32_LIST_PACKED(45, zzic.PACKED_VECTOR, zzip.INT),
    SFIXED64_LIST_PACKED(46, zzic.PACKED_VECTOR, zzip.LONG),
    SINT32_LIST_PACKED(47, zzic.PACKED_VECTOR, zzip.INT),
    SINT64_LIST_PACKED(48, zzic.PACKED_VECTOR, zzip.LONG),
    GROUP_LIST(49, zzic.VECTOR, zzip.MESSAGE),
    MAP(50, zzic.MAP, zzip.VOID);
    
    private static final zzia[] zzbe;
    private static final Type[] zzbf = new Type[0];
    private final zzip zzaz;
    private final int zzba;
    private final zzic zzbb;
    private final Class<?> zzbc;
    private final boolean zzbd;

    zzia(int i, zzic zzicVar, zzip zzipVar) {
        int i2;
        this.zzba = i;
        this.zzbb = zzicVar;
        this.zzaz = zzipVar;
        int i3 = zzhz.zza[zzicVar.ordinal()];
        boolean z = true;
        if (i3 == 1) {
            this.zzbc = zzipVar.zza();
        } else if (i3 != 2) {
            this.zzbc = null;
        } else {
            this.zzbc = zzipVar.zza();
        }
        this.zzbd = (zzicVar != zzic.SCALAR || (i2 = zzhz.zzb[zzipVar.ordinal()]) == 1 || i2 == 2 || i2 == 3) ? false : z;
    }

    public final int zza() {
        return this.zzba;
    }

    static {
        zzia[] values = values();
        zzbe = new zzia[values.length];
        for (zzia zziaVar : values) {
            zzbe[zziaVar.zzba] = zziaVar;
        }
    }
}
