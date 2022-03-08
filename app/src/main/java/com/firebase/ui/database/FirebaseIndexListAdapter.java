package com.firebase.ui.database;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

/* loaded from: classes.dex */
public abstract class FirebaseIndexListAdapter<T> extends FirebaseListAdapter<T> {
    public FirebaseIndexListAdapter(Context context, SnapshotParser<T> parser, int modelLayout, Query keyQuery, DatabaseReference dataRef, LifecycleOwner owner) {
        super(context, new FirebaseIndexArray(keyQuery, dataRef, parser), modelLayout, owner);
    }

    public FirebaseIndexListAdapter(Context context, SnapshotParser<T> parser, int modelLayout, Query keyQuery, DatabaseReference dataRef) {
        super(context, new FirebaseIndexArray(keyQuery, dataRef, parser), modelLayout);
    }

    public FirebaseIndexListAdapter(Context context, Class<T> modelClass, int modelLayout, Query keyQuery, DatabaseReference dataRef, LifecycleOwner owner) {
        this(context, new ClassSnapshotParser(modelClass), modelLayout, keyQuery, dataRef, owner);
    }

    public FirebaseIndexListAdapter(Context context, Class<T> modelClass, int modelLayout, Query keyQuery, DatabaseReference dataRef) {
        this(context, new ClassSnapshotParser(modelClass), modelLayout, keyQuery, dataRef);
    }
}
