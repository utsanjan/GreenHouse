package com.google.firebase.installations;

import android.util.Base64;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
public class RandomFidGenerator {
    private static final int FID_LENGTH = 22;
    private static final byte FID_4BIT_PREFIX = Byte.parseByte("01110000", 2);
    private static final byte REMOVE_PREFIX_MASK = Byte.parseByte("00001111", 2);

    public String createRandomFid() {
        byte[] uuidBytes = getBytesFromUUID(UUID.randomUUID(), new byte[17]);
        uuidBytes[16] = uuidBytes[0];
        uuidBytes[0] = (byte) ((REMOVE_PREFIX_MASK & uuidBytes[0]) | FID_4BIT_PREFIX);
        return encodeFidBase64UrlSafe(uuidBytes);
    }

    private static String encodeFidBase64UrlSafe(byte[] rawValue) {
        return new String(Base64.encode(rawValue, 11), Charset.defaultCharset()).substring(0, 22);
    }

    private static byte[] getBytesFromUUID(UUID uuid, byte[] output) {
        ByteBuffer bb = ByteBuffer.wrap(output);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return bb.array();
    }
}
