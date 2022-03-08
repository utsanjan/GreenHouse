package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
final class zzn extends zzl {
    private final Callable<String> zzar;

    private zzn(Callable<String> callable) {
        super(false, null, null);
        this.zzar = callable;
    }

    @Override // com.google.android.gms.common.zzl
    final String getErrorMessage() {
        try {
            return this.zzar.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
