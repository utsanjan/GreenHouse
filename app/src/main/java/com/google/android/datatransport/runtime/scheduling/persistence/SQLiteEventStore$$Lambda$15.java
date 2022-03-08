package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.TransportContext;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.List;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$15 implements SQLiteEventStore.Function {
    private final SQLiteEventStore arg$1;
    private final List arg$2;
    private final TransportContext arg$3;

    private SQLiteEventStore$$Lambda$15(SQLiteEventStore sQLiteEventStore, List list, TransportContext transportContext) {
        this.arg$1 = sQLiteEventStore;
        this.arg$2 = list;
        this.arg$3 = transportContext;
    }

    public static SQLiteEventStore.Function lambdaFactory$(SQLiteEventStore sQLiteEventStore, List list, TransportContext transportContext) {
        return new SQLiteEventStore$$Lambda$15(sQLiteEventStore, list, transportContext);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadEvents$12(this.arg$1, this.arg$2, this.arg$3, (Cursor) obj);
    }
}
