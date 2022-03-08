package com.firebase.ui.database;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.firebase.ui.database.ChangeEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public abstract class FirebaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> implements FirebaseAdapter<T> {
    private static final String TAG = "FirebaseRecyclerAdapter";
    protected final int mModelLayout;
    protected final ObservableSnapshotArray<T> mSnapshots;
    protected final Class<VH> mViewHolderClass;

    protected abstract void populateViewHolder(VH vh, T t, int i);

    public FirebaseRecyclerAdapter(ObservableSnapshotArray<T> snapshots, int modelLayout, Class<VH> viewHolderClass, LifecycleOwner owner) {
        this.mSnapshots = snapshots;
        this.mViewHolderClass = viewHolderClass;
        this.mModelLayout = modelLayout;
        if (owner != null) {
            owner.getLifecycle().addObserver(this);
        }
    }

    public FirebaseRecyclerAdapter(ObservableSnapshotArray<T> snapshots, int modelLayout, Class<VH> viewHolderClass) {
        this(snapshots, modelLayout, viewHolderClass, (LifecycleOwner) null);
        startListening();
    }

    public FirebaseRecyclerAdapter(SnapshotParser<T> parser, int modelLayout, Class<VH> viewHolderClass, Query query) {
        this(new FirebaseArray(query, parser), modelLayout, viewHolderClass);
    }

    public FirebaseRecyclerAdapter(SnapshotParser<T> parser, int modelLayout, Class<VH> viewHolderClass, Query query, LifecycleOwner owner) {
        this(new FirebaseArray(query, parser), modelLayout, viewHolderClass, owner);
    }

    public FirebaseRecyclerAdapter(Class<T> modelClass, int modelLayout, Class<VH> viewHolderClass, Query query) {
        this(new ClassSnapshotParser(modelClass), modelLayout, viewHolderClass, query);
    }

    public FirebaseRecyclerAdapter(Class<T> modelClass, int modelLayout, Class<VH> viewHolderClass, Query query, LifecycleOwner owner) {
        this(new ClassSnapshotParser(modelClass), modelLayout, viewHolderClass, query, owner);
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
        notifyDataSetChanged();
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

    /* renamed from: com.firebase.ui.database.FirebaseRecyclerAdapter$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType;

        static {
            int[] iArr = new int[ChangeEventListener.EventType.values().length];
            $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType = iArr;
            try {
                iArr[ChangeEventListener.EventType.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[ChangeEventListener.EventType.CHANGED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[ChangeEventListener.EventType.REMOVED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[ChangeEventListener.EventType.MOVED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onChildChanged(ChangeEventListener.EventType type, DataSnapshot snapshot, int index, int oldIndex) {
        int i = AnonymousClass1.$SwitchMap$com$firebase$ui$database$ChangeEventListener$EventType[type.ordinal()];
        if (i == 1) {
            notifyItemInserted(index);
        } else if (i == 2) {
            notifyItemChanged(index);
        } else if (i == 3) {
            notifyItemRemoved(index);
        } else if (i == 4) {
            notifyItemMoved(oldIndex, index);
        } else {
            throw new IllegalStateException("Incomplete case statement");
        }
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onDataChanged() {
    }

    @Override // com.firebase.ui.database.ChangeEventListener
    public void onCancelled(DatabaseError error) {
        Log.w(TAG, error.toException());
    }

    @Override // com.firebase.ui.database.FirebaseAdapter
    public T getItem(int position) {
        return this.mSnapshots.getObject(position);
    }

    @Override // com.firebase.ui.database.FirebaseAdapter
    public DatabaseReference getRef(int position) {
        return this.mSnapshots.get(position).getRef();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mSnapshots.isListening(this)) {
            return this.mSnapshots.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        try {
            Constructor<VH> constructor = this.mViewHolderClass.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return this.mModelLayout;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VH viewHolder, int position) {
        T model = getItem(position);
        populateViewHolder(viewHolder, model, position);
    }
}
