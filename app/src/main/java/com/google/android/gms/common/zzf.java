package com.google.android.gms.common;

import java.lang.ref.WeakReference;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
abstract class zzf extends zzd {
    private static final WeakReference<byte[]> zzah = new WeakReference<>(null);
    private WeakReference<byte[]> zzag = zzah;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzf(byte[] bArr) {
        super(bArr);
    }

    protected abstract byte[] zzd();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.common.zzd
    public final byte[] getBytes() {
        byte[] bArr;
        synchronized (this) {
            bArr = this.zzag.get();
            if (bArr == null) {
                bArr = zzd();
                this.zzag = new WeakReference<>(bArr);
            }
        }
        return bArr;
    }
}
