package com.firebase.ui.database;

import com.firebase.ui.database.ChangeEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class FirebaseArray<T> extends CachingObservableSnapshotArray<T> implements ChildEventListener, ValueEventListener {
    private Query mQuery;
    private List<DataSnapshot> mSnapshots = new ArrayList();

    public FirebaseArray(Query query, Class<T> tClass) {
        super(tClass);
        init(query);
    }

    public FirebaseArray(Query query, SnapshotParser<T> parser) {
        super(parser);
        init(query);
    }

    private void init(Query query) {
        this.mQuery = query;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.firebase.ui.database.ObservableSnapshotArray
    public List<DataSnapshot> getSnapshots() {
        return this.mSnapshots;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.firebase.ui.database.ObservableSnapshotArray
    public void onCreate() {
        super.onCreate();
        this.mQuery.addChildEventListener(this);
        this.mQuery.addValueEventListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.firebase.ui.database.CachingObservableSnapshotArray, com.firebase.ui.database.ObservableSnapshotArray
    public void onDestroy() {
        super.onDestroy();
        this.mQuery.removeEventListener((ValueEventListener) this);
        this.mQuery.removeEventListener((ChildEventListener) this);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
        int index = 0;
        if (previousChildKey != null) {
            index = getIndexForKey(previousChildKey) + 1;
        }
        this.mSnapshots.add(index, snapshot);
        notifyChangeEventListeners(ChangeEventListener.EventType.ADDED, snapshot, index);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {
        int index = getIndexForKey(snapshot.getKey());
        updateData(index, snapshot);
        notifyChangeEventListeners(ChangeEventListener.EventType.CHANGED, snapshot, index);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildRemoved(DataSnapshot snapshot) {
        int index = getIndexForKey(snapshot.getKey());
        removeData(index);
        notifyChangeEventListeners(ChangeEventListener.EventType.REMOVED, snapshot, index);
    }

    @Override // com.google.firebase.database.ChildEventListener
    public void onChildMoved(DataSnapshot snapshot, String previousChildKey) {
        int oldIndex = getIndexForKey(snapshot.getKey());
        this.mSnapshots.remove(oldIndex);
        int newIndex = previousChildKey == null ? 0 : getIndexForKey(previousChildKey) + 1;
        this.mSnapshots.add(newIndex, snapshot);
        notifyChangeEventListeners(ChangeEventListener.EventType.MOVED, snapshot, newIndex, oldIndex);
    }

    @Override // com.google.firebase.database.ValueEventListener
    public void onDataChange(DataSnapshot dataSnapshot) {
        notifyListenersOnDataChanged();
    }

    @Override // com.google.firebase.database.ChildEventListener, com.google.firebase.database.ValueEventListener
    public void onCancelled(DatabaseError error) {
        notifyListenersOnCancelled(error);
    }

    private int getIndexForKey(String key) {
        int index = 0;
        for (DataSnapshot snapshot : this.mSnapshots) {
            if (snapshot.getKey().equals(key)) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("Key not found");
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FirebaseArray snapshots = (FirebaseArray) obj;
        return this.mQuery.equals(snapshots.mQuery) && this.mSnapshots.equals(snapshots.mSnapshots);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int result = this.mQuery.hashCode();
        return (result * 31) + this.mSnapshots.hashCode();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        if (!isListening()) {
            return "FirebaseArray is inactive";
        }
        return "FirebaseArray is listening at " + this.mQuery + ":\n" + this.mSnapshots;
    }
}
