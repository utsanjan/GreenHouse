package com.firebase.ui.database;

import com.firebase.ui.database.ChangeEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import java.util.AbstractList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public abstract class ObservableSnapshotArray<E> extends AbstractList<DataSnapshot> {
    private boolean mHasDataChanged;
    protected final List<ChangeEventListener> mListeners;
    protected final SnapshotParser<E> mParser;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract List<DataSnapshot> getSnapshots();

    public ObservableSnapshotArray(Class<E> clazz) {
        this(new ClassSnapshotParser(clazz));
    }

    public ObservableSnapshotArray(SnapshotParser<E> parser) {
        this.mListeners = new CopyOnWriteArrayList();
        this.mHasDataChanged = false;
        this.mParser = (SnapshotParser) Preconditions.checkNotNull(parser);
    }

    public ChangeEventListener addChangeEventListener(ChangeEventListener listener) {
        Preconditions.checkNotNull(listener);
        boolean wasListening = isListening();
        this.mListeners.add(listener);
        for (int i = 0; i < size(); i++) {
            listener.onChildChanged(ChangeEventListener.EventType.ADDED, get(i), i, -1);
        }
        if (this.mHasDataChanged) {
            listener.onDataChanged();
        }
        if (!wasListening) {
            onCreate();
        }
        return listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onCreate() {
    }

    public void removeChangeEventListener(ChangeEventListener listener) {
        this.mListeners.remove(listener);
        if (!isListening()) {
            onDestroy();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDestroy() {
        this.mHasDataChanged = false;
    }

    public void removeAllListeners() {
        for (ChangeEventListener listener : this.mListeners) {
            removeChangeEventListener(listener);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyChangeEventListeners(ChangeEventListener.EventType type, DataSnapshot snapshot, int index) {
        notifyChangeEventListeners(type, snapshot, index, -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyChangeEventListeners(ChangeEventListener.EventType type, DataSnapshot snapshot, int index, int oldIndex) {
        for (ChangeEventListener listener : this.mListeners) {
            listener.onChildChanged(type, snapshot, index, oldIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyListenersOnDataChanged() {
        this.mHasDataChanged = true;
        for (ChangeEventListener listener : this.mListeners) {
            listener.onDataChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void notifyListenersOnCancelled(DatabaseError error) {
        for (ChangeEventListener listener : this.mListeners) {
            listener.onCancelled(error);
        }
    }

    public final boolean isListening() {
        return !this.mListeners.isEmpty();
    }

    public final boolean isListening(ChangeEventListener listener) {
        return this.mListeners.contains(listener);
    }

    public E getObject(int index) {
        return this.mParser.parseSnapshot(get(index));
    }

    @Override // java.util.AbstractList, java.util.List
    public DataSnapshot get(int index) {
        return getSnapshots().get(index);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return getSnapshots().size();
    }
}
