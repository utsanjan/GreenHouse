package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$19 implements SQLiteEventStore.Function {
    private static final SQLiteEventStore$$Lambda$19 instance = new SQLiteEventStore$$Lambda$19();

    private SQLiteEventStore$$Lambda$19() {
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$ensureBeginTransaction$16((Throwable) obj);
    }
}
