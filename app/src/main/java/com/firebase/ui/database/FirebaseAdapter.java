package com.firebase.ui.database;

import androidx.lifecycle.LifecycleObserver;
import com.google.firebase.database.DatabaseReference;

/* loaded from: classes.dex */
interface FirebaseAdapter<T> extends ChangeEventListener, LifecycleObserver {
    void cleanup();

    T getItem(int i);

    DatabaseReference getRef(int i);

    void startListening();
}
