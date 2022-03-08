package com.firebase.ui.database;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

/* loaded from: classes.dex */
public interface ChangeEventListener {

    /* loaded from: classes.dex */
    public enum EventType {
        ADDED,
        CHANGED,
        REMOVED,
        MOVED
    }

    void onCancelled(DatabaseError databaseError);

    void onChildChanged(EventType eventType, DataSnapshot dataSnapshot, int i, int i2);

    void onDataChanged();
}
