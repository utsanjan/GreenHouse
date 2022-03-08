package com.firebase.ui.database;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.firebase.ui.database.ChangeEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/* loaded from: classes.dex */
public abstract class FirebaseListAdapter<T> extends BaseAdapter implements FirebaseAdapter<T> {
    private static final String TAG = "FirebaseListAdapter";
    protected final Context mContext;
    protected final int mLayout;
    protected final ObservableSnapshotArray<T> mSnapshots;

    protected abstract void populateView(View view, T t, int i);

    public FirebaseListAdapter(Context context, ObservableSnapshotArray<T> snapshots, int modelLayout, LifecycleOwner owner) {
        this.mContext = context;
        this.mSnapshots = snapshots;
        this.mLayout = modelLayout;
        if (owner != null) {
            owner.getLifecycle().addObserver(this);
        }
    }

    public FirebaseListAdapter(Context context, ObservableSnapshotArray<T> snapshots, int modelLayout) {
        this(context, snapshots, modelLayout, (LifecycleOwner) null);
        startListening();
    }

    public FirebaseListAdapter(Context context, SnapshotParser<T> parser, int modelLayout, Query query) {
        this(context, new FirebaseArray(query, parser), modelLayout);
    }

    public FirebaseListAdapter(Context context, SnapshotParser<T> parser, int modelLayout, Query query, LifecycleOwner owner) {
        this(context, new FirebaseArray(query, parser), modelLayout, owner);
    }

    public FirebaseListAdapter(Context context, Class<T> modelClass, int modelLayout, Query query) {
        this(context, new ClassSnapshotParser(modelClass), modelLayout, query);
    }

    public FirebaseListAdapter(Context context, Class<T> modelClass, int modelLayout, Query query, LifecycleOwner owner) {
        this(context, new ClassSnapshotParser(modelClass), modelLayout, query, owner);
    }

    @Override // com.firebase.ui.database.FirebaseAdapter
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startListening() {
        if (!this.mSnapshots.isListening(this)) {
            this.mSnapshots.addChangeEventListener(this);
        }
    }

    @Override // com.firebase.ui.database.FirebaseAdapter
    public void cleanup() {
        this.mSnapshots.removeChangeEventListener(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void cleanup(LifecycleOwner source, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_STOP) {
            cleanup();
        } else if (event == Lifecycle.Event.ON_DESTROY) {
            source.getLifecycle().removeObserver(this);
        }
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onChildChanged(ChangeEventListener.EventType type, DataSnapshot snapshot, int index, int oldIndex) {
        notifyDataSetChanged();
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onDataChanged() {
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onCancelled(DatabaseError error) {
        Log.w(TAG, error.toException());
    }

    @Override // android.widget.Adapter, com.firebase.ui.database.FirebaseAdapter
    public T getItem(int position) {
        return this.mSnapshots.getObject(position);
    }

    @Override // com.firebase.ui.database.FirebaseAdapter
    public DatabaseReference getRef(int position) {
        return this.mSnapshots.get(position).getRef();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mSnapshots.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return this.mSnapshots.get(i).getKey().hashCode();
    }

    @Override // android.widget.Adapter
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(this.mLayout, viewGroup, false);
        }
        T model = getItem(position);
        populateView(view, model, position);
        return view;
    }
}
