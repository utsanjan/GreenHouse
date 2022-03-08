package java.io;

import android.content.Context;
import android.util.Log;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class ByteArrayOutputStrean extends ByteArrayOutputStream {
    private static byte[] REPLACE_BYTES;
    private static byte[] SEARCH_BYTES;
    private static final String TAG = ByteArrayOutputStrean.class.getSimpleName();

    public static void init(Context context, String originalPackageName) {
        String str = TAG;
        Log.i(str, "init; originalPackageName: " + originalPackageName);
        try {
            SEARCH_BYTES = context.getPackageName().getBytes("UTF-8");
            REPLACE_BYTES = originalPackageName.getBytes("UTF-8");
            String str2 = TAG;
            Log.i(str2, "static initializer; SEARCH_BYTES: " + new String(SEARCH_BYTES, "UTF-8"));
            String str3 = TAG;
            Log.i(str3, "static initializer; REPLACE_BYTES: " + new String(REPLACE_BYTES, "UTF-8"));
        } catch (Exception e) {
            Log.w(TAG, e);
        }
    }

    public ByteArrayOutputStrean() {
    }

    public ByteArrayOutputStrean(int size) {
        super(size);
    }

    @Override // java.io.OutputStream
    public void write(byte[] b) throws IOException {
        byte[] bArr = SEARCH_BYTES;
        if (bArr == null || !Arrays.equals(b, bArr)) {
            super.write(b);
            return;
        }
        super.write(REPLACE_BYTES);
        Log.i(TAG, "write; written replaced bytes");
    }
}
