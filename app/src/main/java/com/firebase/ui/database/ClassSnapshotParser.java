package com.firebase.ui.database;

import com.google.firebase.database.DataSnapshot;

/* loaded from: classes.dex */
public class ClassSnapshotParser<T> implements SnapshotParser<T> {
    private Class<T> mClass;

    public ClassSnapshotParser(Class<T> clazz) {
        this.mClass = (Class) Preconditions.checkNotNull(clazz);
    }

    @Override // com.firebase.ui.database.SnapshotParser
    public T parseSnapshot(DataSnapshot snapshot) {
        return (T) snapshot.getValue(this.mClass);
    }
}
