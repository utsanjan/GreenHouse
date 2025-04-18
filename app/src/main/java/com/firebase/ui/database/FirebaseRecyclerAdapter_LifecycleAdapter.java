package com.firebase.ui.database;

import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/* loaded from: classes.dex */
public class FirebaseRecyclerAdapter_LifecycleAdapter implements GenericLifecycleObserver {
    final FirebaseRecyclerAdapter mReceiver;

    FirebaseRecyclerAdapter_LifecycleAdapter(FirebaseRecyclerAdapter receiver) {
        this.mReceiver = receiver;
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner owner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_START) {
            this.mReceiver.startListening();
        }
        this.mReceiver.cleanup(owner, event);
    }

    public Object getReceiver() {
        return this.mReceiver;
    }
}
