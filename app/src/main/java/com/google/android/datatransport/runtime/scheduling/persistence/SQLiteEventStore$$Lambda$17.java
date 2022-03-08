package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore;
import java.util.Map;

/* loaded from: classes.dex */
final /* synthetic */ class SQLiteEventStore$$Lambda$17 implements SQLiteEventStore.Function {
    private final Map arg$1;

    private SQLiteEventStore$$Lambda$17(Map map) {
        this.arg$1 = map;
    }

    public static SQLiteEventStore.Function lambdaFactory$(Map map) {
        return new SQLiteEventStore$$Lambda$17(map);
    }

    @Override // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore.Function
    public Object apply(Object obj) {
        return SQLiteEventStore.lambda$loadMetadata$14(this.arg$1, (Cursor) obj);
    }
}
