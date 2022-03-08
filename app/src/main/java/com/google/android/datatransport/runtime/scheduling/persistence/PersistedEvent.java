package com.google.android.datatransport.runtime.scheduling.persistence;

import com.google.android.datatransport.runtime.EventInternal;
import com.google.android.datatransport.runtime.TransportContext;

/* loaded from: classes.dex */
public abstract class PersistedEvent {
    public abstract EventInternal getEvent();

    public abstract long getId();

    public abstract TransportContext getTransportContext();

    public static PersistedEvent create(long id, TransportContext transportContext, EventInternal object) {
        return new AutoValue_PersistedEvent(id, transportContext, object);
    }
}
