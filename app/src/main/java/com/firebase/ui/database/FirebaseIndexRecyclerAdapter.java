package com.firebase.ui.database;

import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/* loaded from: classes.dex */
public abstract class FirebaseIndexRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends FirebaseRecyclerAdapter<T, VH> {
    public FirebaseIndexRecyclerAdapter(SnapshotParser<T> parser, int modelLayout, Class<VH> viewHolderClass, Query keyQuery, DatabaseReference dataRef, LifecycleOwner owner) {
        super(new FirebaseIndexArray(keyQuery, dataRef, parser), modelLayout, viewHolderClass, owner);
    }

    public FirebaseIndexRecyclerAdapter(SnapshotParser<T> parser, int modelLayout, Class<VH> viewHolderClass, Query keyQuery, DatabaseReference dataRef) {
        super(new FirebaseIndexArray(keyQuery, dataRef, parser), modelLayout, viewHolderClass);
    }

    public FirebaseIndexRecyclerAdapter(Class<T> modelClass, int modelLayout, Class<VH> viewHolderClass, Query keyQuery, DatabaseReference dataRef, LifecycleOwner owner) {
        this(new ClassSnapshotParser(modelClass), modelLayout, viewHolderClass, keyQuery, dataRef, owner);
    }

    public FirebaseIndexRecyclerAdapter(Class<T> modelClass, int modelLayout, Class<VH> viewHolderClass, Query keyQuery, DatabaseReference dataRef) {
        this(new ClassSnapshotParser(modelClass), modelLayout, viewHolderClass, keyQuery, dataRef);
    }
}
