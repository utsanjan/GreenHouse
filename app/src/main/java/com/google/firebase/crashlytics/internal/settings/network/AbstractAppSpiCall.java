package com.google.firebase.crashlytics.internal.settings.network;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.ResponseParser;
import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import com.google.firebase.crashlytics.internal.settings.model.AppRequestData;
import java.io.IOException;

/* loaded from: classes.dex */
abstract class AbstractAppSpiCall extends AbstractSpiCall implements AppSpiCall {
    public static final String APP_BUILD_VERSION_PARAM = "app[build_version]";
    public static final String APP_BUILT_SDK_VERSION_PARAM = "app[built_sdk_version]";
    public static final String APP_DISPLAY_VERSION_PARAM = "app[display_version]";
    public static final String APP_IDENTIFIER_PARAM = "app[identifier]";
    public static final String APP_INSTANCE_IDENTIFIER_PARAM = "app[instance_identifier]";
    public static final String APP_MIN_SDK_VERSION_PARAM = "app[minimum_sdk_version]";
    public static final String APP_NAME_PARAM = "app[name]";
    public static final String APP_SOURCE_PARAM = "app[source]";
    public static final String ORGANIZATION_ID_PARAM = "org_id";
    private final String version;

    public AbstractAppSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method, String version) {
        super(protocolAndHostOverride, url, requestFactory, method);
        this.version = version;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.network.AppSpiCall
    public boolean invoke(AppRequestData requestData, boolean dataCollectionToken) {
        if (dataCollectionToken) {
            HttpRequest httpRequest = applyMultipartDataTo(applyHeadersTo(getHttpRequest(), requestData), requestData);
            Logger logger = Logger.getLogger();
            logger.d("Sending app info to " + getUrl());
            try {
                HttpResponse httpResponse = httpRequest.execute();
                int statusCode = httpResponse.code();
                String kind = "POST".equalsIgnoreCase(httpRequest.method()) ? "Create" : "Update";
                Logger logger2 = Logger.getLogger();
                logger2.d(kind + " app request ID: " + httpResponse.header(AbstractSpiCall.HEADER_REQUEST_ID));
                Logger logger3 = Logger.getLogger();
                logger3.d("Result was " + statusCode);
                return ResponseParser.parse(statusCode) == 0;
            } catch (IOException ioe) {
                Logger.getLogger().e("HTTP request failed.", ioe);
                throw new RuntimeException(ioe);
            }
        } else {
            throw new RuntimeException("An invalid data collection token was used.");
        }
    }

    private HttpRequest applyHeadersTo(HttpRequest request, AppRequestData requestData) {
        return request.header(AbstractSpiCall.HEADER_ORG_ID, requestData.organizationId).header(AbstractSpiCall.HEADER_GOOGLE_APP_ID, requestData.googleAppId).header(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.version);
    }

    private HttpRequest applyMultipartDataTo(HttpRequest request, AppRequestData requestData) {
        HttpRequest request2 = request.part(ORGANIZATION_ID_PARAM, requestData.organizationId).part(APP_IDENTIFIER_PARAM, requestData.appId).part(APP_NAME_PARAM, requestData.name).part(APP_DISPLAY_VERSION_PARAM, requestData.displayVersion).part(APP_BUILD_VERSION_PARAM, requestData.buildVersion).part(APP_SOURCE_PARAM, Integer.toString(requestData.source)).part(APP_MIN_SDK_VERSION_PARAM, requestData.minSdkVersion).part(APP_BUILT_SDK_VERSION_PARAM, requestData.builtSdkVersion);
        if (!CommonUtils.isNullOrEmpty(requestData.instanceIdentifier)) {
            request2.part(APP_INSTANCE_IDENTIFIER_PARAM, requestData.instanceIdentifier);
        }
        return request2;
    }
}
