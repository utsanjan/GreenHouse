package com.firebase.ui.database;

import android.util.Log;
import com.firebase.ui.database.ChangeEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class FirebaseIndexArray<T> extends CachingObservableSnapshotArray<T> implements ChangeEventListener {
    private static final String TAG = "FirebaseIndexArray";
    private DatabaseReference mDataRef;
    private boolean mHasPendingMoveOrDelete;
    private FirebaseArray<String> mKeySnapshots;
    private Map<DatabaseReference, ValueEventListener> mRefs = new HashMap();
    private List<DataSnapshot> mDataSnapshots = new ArrayList();
    private List<String> mKeysWithPendingData = new ArrayList();

    public FirebaseIndexArray(Query keyQuery, DatabaseReference dataRef, Class<T> tClass) {
        super(tClass);
        init(keyQuery, dataRef);
    }

    public FirebaseIndexArray(Query keyQuery, DatabaseReference dataRef, SnapshotParser<T> parser) {
        super(parser);
        init(keyQuery, dataRef);
    }

    private void init(Query keyQuery, DatabaseReference dataRef) {
        this.mDataRef = dataRef;
        this.mKeySnapshots = new FirebaseArray<>(keyQuery, new SnapshotParser<String>() { // from class: com.firebase.ui.database.FirebaseIndexArray.1
            @Override // com.firebase.ui.database.SnapshotParser
            public String parseSnapshot(DataSnapshot snapshot) {
                return snapshot.getKey();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.firebase.ui.database.ObservableSnapshotArray
    public void onCreate() {
        super.onCreate();
        this.mKeySnapshots.addChangeEventListener(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.firebase.ui.database.CachingObservableSnapshotArray, com.firebase.ui.database.ObservableSnapshotArray
    public void onDestroy() {
        super.onDestroy();
        this.mKeySnapshots.removeChangeEventListener(this);
        for (DatabaseReference ref : this.mRefs.keySet()) {
            ref.removeEventListener(this.mRefs.get(ref));
        }
        this.mRefs.clear();
    }

    /* renamed from: com.firebase.ui.database.FirebaseIndexArray$2  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType;

        static {
            int[] iArr = new int[ChangeEventListener.EventType.values().length];
            $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType = iArr;
            try {
                iArr[ChangeEventListener.EventType.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[ChangeEventListener.EventType.MOVED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[ChangeEventListener.EventType.CHANGED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[ChangeEventListener.EventType.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onChildChanged(ChangeEventListener.EventType type, DataSnapshot snapshot, int index, int oldIndex) {
        int i = AnonymousClass2.$SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[type.ordinal()];
        if (i == 1) {
            onKeyAdded(snapshot);
        } else if (i == 2) {
            onKeyMoved(snapshot, index, oldIndex);
        } else if (i == 4) {
            onKeyRemoved(snapshot, index);
        }
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onDataChanged() {
        if (this.mHasPendingMoveOrDelete || this.mKeySnapshots.isEmpty()) {
            notifyListenersOnDataChanged();
            this.mHasPendingMoveOrDelete = false;
        }
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onCancelled(DatabaseError error) {
        Log.e(TAG, "A fatal error occurred retrieving the necessary keys to populate your adapter.");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.firebase.ui.database.ObservableSnapshotArray
    public List<DataSnapshot> getSnapshots() {
        return this.mDataSnapshots;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getIndexForKey(String key) {
        int dataCount = size();
        int index = 0;
        int keyIndex = 0;
        while (index < dataCount) {
            String superKey = this.mKeySnapshots.getObject(keyIndex);
            if (key.equals(superKey)) {
                break;
            }
            if (this.mDataSnapshots.get(index).getKey().equals(superKey)) {
                index++;
            }
            keyIndex++;
        }
        return index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isKeyAtIndex(String key, int index) {
        return index >= 0 && index < size() && this.mDataSnapshots.get(index).getKey().equals(key);
    }

    protected void onKeyAdded(DataSnapshot data) {
        String key = data.getKey();
        DatabaseReference ref = this.mDataRef.child(key);
        this.mKeysWithPendingData.add(key);
        this.mRefs.put(ref, ref.addValueEventListener(new DataRefListener()));
    }

    protected void onKeyMoved(DataSnapshot data, int index, int oldIndex) {
        String key = data.getKey();
        if (isKeyAtIndex(key, oldIndex)) {
            DataSnapshot snapshot = removeData(oldIndex);
            this.mHasPendingMoveOrDelete = true;
            this.mDataSnapshots.add(index, snapshot);
            notifyChangeEventListeners(ChangeEventListener.EventType.MOVED, snapshot, index, oldIndex);
        }
    }

    protected void onKeyRemoved(DataSnapshot data, int index) {
        String key = data.getKey();
        ValueEventListener listener = this.mRefs.remove(this.mDataRef.getRef().child(key));
        if (listener != null) {
            this.mDataRef.child(key).removeEventListener(listener);
        }
        if (isKeyAtIndex(key, index)) {
            DataSnapshot snapshot = removeData(index);
            this.mHasPendingMoveOrDelete = true;
            notifyChangeEventListeners(ChangeEventListener.EventType.REMOVED, snapshot, index);
        }
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        FirebaseIndexArray array = (FirebaseIndexArray) obj;
        return this.mDataRef.equals(array.mDataRef) && this.mDataSnapshots.equals(array.mDataSnapshots);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public int hashCode() {
        int result = super.hashCode();
        return (((result * 31) + this.mDataRef.hashCode()) * 31) + this.mDataSnapshots.hashCode();
    }

    @Override // java.util.AbstractCollection
    public String toString() {
        if (!isListening()) {
            return "FirebaseIndexArray is inactive";
        }
        return "FirebaseIndexArray is listening at " + this.mDataRef + ":\n" + this.mDataSnapshots;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes.dex */
    public class DataRefListener implements ValueEventListener {
        protected DataRefListener() {
        }

        @Override // com.google.firebase.database.ValueEventListener
        public void onDataChange(DataSnapshot snapshot) {
            String key = snapshot.getKey();
            int index = FirebaseIndexArray.this.getIndexForKey(key);
            if (snapshot.getValue() != null) {
                if (FirebaseIndexArray.this.isKeyAtIndex(key, index)) {
                    FirebaseIndexArray.this.updateData(index, snapshot);
                    FirebaseIndexArray.this.notifyChangeEventListeners(ChangeEventListener.EventType.CHANGED, snapshot, index);
                    FirebaseIndexArray.this.notifyListenersOnDataChanged();
                    return;
                }
                FirebaseIndexArray.this.mDataSnapshots.add(index, snapshot);
                FirebaseIndexArray.this.notifyChangeEventListeners(ChangeEventListener.EventType.ADDED, snapshot, index);
                FirebaseIndexArray.this.mKeysWithPendingData.remove(key);
                if (FirebaseIndexArray.this.mKeysWithPendingData.isEmpty()) {
                    FirebaseIndexArray.this.notifyListenersOnDataChanged();
                }
            } else if (FirebaseIndexArray.this.isKeyAtIndex(key, index)) {
                FirebaseIndexArray.this.removeData(index);
                FirebaseIndexArray.this.notifyChangeEventListeners(ChangeEventListener.EventType.REMOVED, snapshot, index);
                FirebaseIndexArray.this.notifyListenersOnDataChanged();
            } else {
                Log.w(FirebaseIndexArray.TAG, "Key not found at ref: " + snapshot.getRef());
            }
        }

        @Override // com.google.firebase.database.ValueEventListener
        public void onCancelled(DatabaseError error) {
            FirebaseIndexArray.this.notifyListenersOnCancelled(error);
        }
    }
}
