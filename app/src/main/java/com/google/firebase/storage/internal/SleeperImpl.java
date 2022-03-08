package com.google.firebase.storage.internal;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class SleeperImpl implements Sleeper {
    @Override // com.google.firebase.storage.internal.Sleeper
    public void sleep(int milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }
}
