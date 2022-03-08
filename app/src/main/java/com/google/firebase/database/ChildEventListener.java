package com.google.firebase.database;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public interface ChildEventListener {
    void onCancelled(DatabaseError databaseError);

    void onChildAdded(DataSnapshot dataSnapshot, String str);

    void onChildChanged(DataSnapshot dataSnapshot, String str);

    void onChildMoved(DataSnapshot dataSnapshot, String str);

    void onChildRemoved(DataSnapshot dataSnapshot);
}
