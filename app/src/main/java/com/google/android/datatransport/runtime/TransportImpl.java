package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transformer;
import com.google.android.datatransport.Transport;
import com.google.android.datatransport.TransportScheduleCallback;

/* loaded from: classes.dex */
final class TransportImpl<T> implements Transport<T> {
    private final String name;
    private final Encoding payloadEncoding;
    private final Transformer<T, byte[]> transformer;
    private final TransportContext transportContext;
    private final TransportInternal transportInternal;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransportImpl(TransportContext transportContext, String name, Encoding payloadEncoding, Transformer<T, byte[]> transformer, TransportInternal transportInternal) {
        this.transportContext = transportContext;
        this.name = name;
        this.payloadEncoding = payloadEncoding;
        this.transformer = transformer;
        this.transportInternal = transportInternal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$send$0(Exception e) {
    }

    @Override // com.google.android.datatransport.Transport
    public void send(Event<T> event) {
        TransportScheduleCallback transportScheduleCallback;
        transportScheduleCallback = TransportImpl$$Lambda$1.instance;
        schedule(event, transportScheduleCallback);
    }

    @Override // com.google.android.datatransport.Transport
    public void schedule(Event<T> event, TransportScheduleCallback callback) {
        this.transportInternal.send(SendRequest.builder().setTransportContext(this.transportContext).setEvent(event).setTransportName(this.name).setTransformer(this.transformer).setEncoding(this.payloadEncoding).build(), callback);
    }
}
