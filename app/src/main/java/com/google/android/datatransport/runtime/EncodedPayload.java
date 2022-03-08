package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class EncodedPayload {
    private final byte[] bytes;
    private final Encoding encoding;

    public EncodedPayload(Encoding encoding, byte[] bytes) {
        if (encoding == null) {
            throw new NullPointerException("encoding is null");
        } else if (bytes != null) {
            this.encoding = encoding;
            this.bytes = bytes;
        } else {
            throw new NullPointerException("bytes is null");
        }
    }

    public Encoding getEncoding() {
        return this.encoding;
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EncodedPayload)) {
            return false;
        }
        EncodedPayload that = (EncodedPayload) o;
        if (!this.encoding.equals(that.encoding)) {
            return false;
        }
        return Arrays.equals(this.bytes, that.bytes);
    }

    public int hashCode() {
        int h = 1000003 ^ this.encoding.hashCode();
        return (h * 1000003) ^ Arrays.hashCode(this.bytes);
    }

    public String toString() {
        return "EncodedPayload{encoding=" + this.encoding + ", bytes=[...]}";
    }
}
