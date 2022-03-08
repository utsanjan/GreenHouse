package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zar;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* loaded from: classes.dex */
public final class zacm extends zar {
    private final /* synthetic */ zack zaky;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zacm(zack zackVar, Looper looper) {
        super(looper);
        this.zaky = zackVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Object obj;
        zack zackVar;
        zack zackVar2;
        zack zackVar3;
        int i = message.what;
        if (i == 0) {
            PendingResult<?> pendingResult = (PendingResult) message.obj;
            obj = this.zaky.zadp;
            synchronized (obj) {
                if (pendingResult == null) {
                    zackVar3 = this.zaky.zaks;
                    zackVar3.zad(new Status(13, "Transform returned null"));
                } else if (pendingResult instanceof zacc) {
                    zackVar2 = this.zaky.zaks;
                    zackVar2.zad(((zacc) pendingResult).getStatus());
                } else {
                    zackVar = this.zaky.zaks;
                    zackVar.zaa(pendingResult);
                }
            }
        } else if (i != 1) {
            int i2 = message.what;
            StringBuilder sb = new StringBuilder(70);
            sb.append("TransformationResultHandler received unknown message type: ");
            sb.append(i2);
            Log.e("TransformedResultImpl", sb.toString());
        } else {
            RuntimeException runtimeException = (RuntimeException) message.obj;
            String valueOf = String.valueOf(runtimeException.getMessage());
            Log.e("TransformedResultImpl", valueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(valueOf) : new String("Runtime exception on the transformation worker thread: "));
            throw runtimeException;
        }
    }
}
