package com.firebase.ui.database;

import com.google.firebase.database.DataSnapshot;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class CachingObservableSnapshotArray<T> extends ObservableSnapshotArray<T> {
    private Map<String, T> mObjectCache = new HashMap();

    public CachingObservableSnapshotArray(Class<T> tClass) {
        super(tClass);
    }

    public CachingObservableSnapshotArray(SnapshotParser<T> parser) {
        super(parser);
    }

    @Override // com.firebase.ui.database.ObservableSnapshotArray
    public T getObject(int index) {
        String key = get(index).getKey();
        if (this.mObjectCache.containsKey(key)) {
            return this.mObjectCache.get(key);
        }
        T object = (T) super.getObject(index);
        this.mObjectCache.put(key, object);
        return object;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.firebase.ui.database.ObservableSnapshotArray
    public void onDestroy() {
        super.onDestroy();
        clearData();
    }

    @Deprecated
    protected void clearData() {
        getSnapshots().clear();
        this.mObjectCache.clear();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DataSnapshot removeData(int index) {
        DataSnapshot snapshot = getSnapshots().remove(index);
        if (snapshot != null) {
            this.mObjectCache.remove(snapshot.getKey());
        }
        return snapshot;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateData(int index, DataSnapshot snapshot) {
        getSnapshots().set(index, snapshot);
        this.mObjectCache.remove(snapshot.getKey());
    }
}
