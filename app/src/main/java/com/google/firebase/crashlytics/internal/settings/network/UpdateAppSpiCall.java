package com.google.firebase.crashlytics.internal.settings.network;

import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.settings.model.AppRequestData;

/* loaded from: classes.dex */
public class UpdateAppSpiCall extends AbstractAppSpiCall {
    @Override // com.google.firebase.crashlytics.internal.settings.network.AbstractAppSpiCall, com.google.firebase.crashlytics.internal.settings.network.AppSpiCall
    public /* bridge */ /* synthetic */ boolean invoke(AppRequestData appRequestData, boolean z) {
        return super.invoke(appRequestData, z);
    }

    public UpdateAppSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, String version) {
        super(protocolAndHostOverride, url, requestFactory, HttpMethod.PUT, version);
    }
}
