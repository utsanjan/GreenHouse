package com.firebase.ui.database;

import com.google.firebase.database.DataSnapshot;

/* loaded from: classes.dex */
public interface SnapshotParser<T> {
    T parseSnapshot(DataSnapshot dataSnapshot);
}
