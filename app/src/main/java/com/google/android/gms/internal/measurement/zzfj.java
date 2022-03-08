package com.google.android.gms.internal.measurement;

import java.lang.reflect.Type;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.4.3 */
/* loaded from: classes.dex */
public enum zzfj {
    DOUBLE(0, zzfl.SCALAR, zzfy.DOUBLE),
    FLOAT(1, zzfl.SCALAR, zzfy.FLOAT),
    INT64(2, zzfl.SCALAR, zzfy.LONG),
    UINT64(3, zzfl.SCALAR, zzfy.LONG),
    INT32(4, zzfl.SCALAR, zzfy.INT),
    FIXED64(5, zzfl.SCALAR, zzfy.LONG),
    FIXED32(6, zzfl.SCALAR, zzfy.INT),
    BOOL(7, zzfl.SCALAR, zzfy.BOOLEAN),
    STRING(8, zzfl.SCALAR, zzfy.STRING),
    MESSAGE(9, zzfl.SCALAR, zzfy.MESSAGE),
    BYTES(10, zzfl.SCALAR, zzfy.BYTE_STRING),
    UINT32(11, zzfl.SCALAR, zzfy.INT),
    ENUM(12, zzfl.SCALAR, zzfy.ENUM),
    SFIXED32(13, zzfl.SCALAR, zzfy.INT),
    SFIXED64(14, zzfl.SCALAR, zzfy.LONG),
    SINT32(15, zzfl.SCALAR, zzfy.INT),
    SINT64(16, zzfl.SCALAR, zzfy.LONG),
    GROUP(17, zzfl.SCALAR, zzfy.MESSAGE),
    DOUBLE_LIST(18, zzfl.VECTOR, zzfy.DOUBLE),
    FLOAT_LIST(19, zzfl.VECTOR, zzfy.FLOAT),
    INT64_LIST(20, zzfl.VECTOR, zzfy.LONG),
    UINT64_LIST(21, zzfl.VECTOR, zzfy.LONG),
    INT32_LIST(22, zzfl.VECTOR, zzfy.INT),
    FIXED64_LIST(23, zzfl.VECTOR, zzfy.LONG),
    FIXED32_LIST(24, zzfl.VECTOR, zzfy.INT),
    BOOL_LIST(25, zzfl.VECTOR, zzfy.BOOLEAN),
    STRING_LIST(26, zzfl.VECTOR, zzfy.STRING),
    MESSAGE_LIST(27, zzfl.VECTOR, zzfy.MESSAGE),
    BYTES_LIST(28, zzfl.VECTOR, zzfy.BYTE_STRING),
    UINT32_LIST(29, zzfl.VECTOR, zzfy.INT),
    ENUM_LIST(30, zzfl.VECTOR, zzfy.ENUM),
    SFIXED32_LIST(31, zzfl.VECTOR, zzfy.INT),
    SFIXED64_LIST(32, zzfl.VECTOR, zzfy.LONG),
    SINT32_LIST(33, zzfl.VECTOR, zzfy.INT),
    SINT64_LIST(34, zzfl.VECTOR, zzfy.LONG),
    DOUBLE_LIST_PACKED(35, zzfl.PACKED_VECTOR, zzfy.DOUBLE),
    FLOAT_LIST_PACKED(36, zzfl.PACKED_VECTOR, zzfy.FLOAT),
    INT64_LIST_PACKED(37, zzfl.PACKED_VECTOR, zzfy.LONG),
    UINT64_LIST_PACKED(38, zzfl.PACKED_VECTOR, zzfy.LONG),
    INT32_LIST_PACKED(39, zzfl.PACKED_VECTOR, zzfy.INT),
    FIXED64_LIST_PACKED(40, zzfl.PACKED_VECTOR, zzfy.LONG),
    FIXED32_LIST_PACKED(41, zzfl.PACKED_VECTOR, zzfy.INT),
    BOOL_LIST_PACKED(42, zzfl.PACKED_VECTOR, zzfy.BOOLEAN),
    UINT32_LIST_PACKED(43, zzfl.PACKED_VECTOR, zzfy.INT),
    ENUM_LIST_PACKED(44, zzfl.PACKED_VECTOR, zzfy.ENUM),
    SFIXED32_LIST_PACKED(45, zzfl.PACKED_VECTOR, zzfy.INT),
    SFIXED64_LIST_PACKED(46, zzfl.PACKED_VECTOR, zzfy.LONG),
    SINT32_LIST_PACKED(47, zzfl.PACKED_VECTOR, zzfy.INT),
    SINT64_LIST_PACKED(48, zzfl.PACKED_VECTOR, zzfy.LONG),
    GROUP_LIST(49, zzfl.VECTOR, zzfy.MESSAGE),
    MAP(50, zzfl.MAP, zzfy.VOID);
    
    private static final zzfj[] zzbe;
    private static final Type[] zzbf = new Type[0];
    private final zzfy zzaz;
    private final int zzba;
    private final zzfl zzbb;
    private final Class<?> zzbc;
    private final boolean zzbd;

    zzfj(int i, zzfl zzflVar, zzfy zzfyVar) {
        int i2;
        this.zzba = i;
        this.zzbb = zzflVar;
        this.zzaz = zzfyVar;
        int i3 = zzfi.zza[zzflVar.ordinal()];
        boolean z = true;
        if (i3 == 1) {
            this.zzbc = zzfyVar.zza();
        } else if (i3 != 2) {
            this.zzbc = null;
        } else {
            this.zzbc = zzfyVar.zza();
        }
        this.zzbd = (zzflVar != zzfl.SCALAR || (i2 = zzfi.zzb[zzfyVar.ordinal()]) == 1 || i2 == 2 || i2 == 3) ? false : z;
    }

    public final int zza() {
        return this.zzba;
    }

    static {
        zzfj[] values = values();
        zzbe = new zzfj[values.length];
        for (zzfj zzfjVar : values) {
            zzbe[zzfjVar.zzba] = zzfjVar;
        }
    }
}
