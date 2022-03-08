package com.google.firebase.crashlytics.internal.settings.network;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.crashlytics.internal.common.CommonUtils;
import com.google.firebase.crashlytics.internal.common.CrashlyticsCore;
import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import com.google.firebase.crashlytics.internal.settings.model.SettingsRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class DefaultSettingsSpiCall extends AbstractSpiCall implements SettingsSpiCall {
    static final String BUILD_VERSION_PARAM = "build_version";
    static final String DISPLAY_VERSION_PARAM = "display_version";
    static final String HEADER_DEVICE_MODEL = "X-CRASHLYTICS-DEVICE-MODEL";
    static final String HEADER_INSTALLATION_ID = "X-CRASHLYTICS-INSTALLATION-ID";
    static final String HEADER_OS_BUILD_VERSION = "X-CRASHLYTICS-OS-BUILD-VERSION";
    static final String HEADER_OS_DISPLAY_VERSION = "X-CRASHLYTICS-OS-DISPLAY-VERSION";
    static final String INSTANCE_PARAM = "instance";
    static final String SOURCE_PARAM = "source";
    private Logger logger;

    public DefaultSettingsSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory) {
        this(protocolAndHostOverride, url, requestFactory, HttpMethod.GET, Logger.getLogger());
    }

    DefaultSettingsSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method, Logger logger) {
        super(protocolAndHostOverride, url, requestFactory, method);
        this.logger = logger;
    }

    @Override // com.google.firebase.crashlytics.internal.settings.network.SettingsSpiCall
    public JSONObject invoke(SettingsRequest requestData, boolean dataCollectionToken) {
        if (dataCollectionToken) {
            try {
                Map<String, String> queryParams = getQueryParamsFor(requestData);
                HttpRequest httpRequest = getHttpRequest(queryParams);
                HttpRequest httpRequest2 = applyHeadersTo(httpRequest, requestData);
                Logger logger = this.logger;
                logger.d("Requesting settings from " + getUrl());
                Logger logger2 = this.logger;
                logger2.d("Settings query params were: " + queryParams);
                HttpResponse httpResponse = httpRequest2.execute();
                Logger logger3 = this.logger;
                logger3.d("Settings request ID: " + httpResponse.header(AbstractSpiCall.HEADER_REQUEST_ID));
                JSONObject toReturn = handleResponse(httpResponse);
                return toReturn;
            } catch (IOException e) {
                this.logger.e("Settings request failed.", e);
                return null;
            }
        } else {
            throw new RuntimeException("An invalid data collection token was used.");
        }
    }

    JSONObject handleResponse(HttpResponse httpResponse) {
        int statusCode = httpResponse.code();
        Logger logger = this.logger;
        logger.d("Settings result was: " + statusCode);
        if (requestWasSuccessful(statusCode)) {
            JSONObject toReturn = getJsonObjectFrom(httpResponse.body());
            return toReturn;
        }
        Logger logger2 = this.logger;
        logger2.e("Failed to retrieve settings from " + getUrl());
        return null;
    }

    boolean requestWasSuccessful(int httpStatusCode) {
        return httpStatusCode == 200 || httpStatusCode == 201 || httpStatusCode == 202 || httpStatusCode == 203;
    }

    private JSONObject getJsonObjectFrom(String httpRequestBody) {
        try {
            return new JSONObject(httpRequestBody);
        } catch (Exception e) {
            Logger logger = this.logger;
            logger.d("Failed to parse settings JSON from " + getUrl(), e);
            Logger logger2 = this.logger;
            logger2.d("Settings response " + httpRequestBody);
            return null;
        }
    }

    private Map<String, String> getQueryParamsFor(SettingsRequest requestData) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(BUILD_VERSION_PARAM, requestData.buildVersion);
        queryParams.put(DISPLAY_VERSION_PARAM, requestData.displayVersion);
        queryParams.put("source", Integer.toString(requestData.source));
        String instanceId = requestData.instanceId;
        if (!CommonUtils.isNullOrEmpty(instanceId)) {
            queryParams.put(INSTANCE_PARAM, instanceId);
        }
        return queryParams;
    }

    private HttpRequest applyHeadersTo(HttpRequest request, SettingsRequest requestData) {
        applyNonNullHeader(request, AbstractSpiCall.HEADER_GOOGLE_APP_ID, requestData.googleAppId);
        applyNonNullHeader(request, AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE);
        applyNonNullHeader(request, AbstractSpiCall.HEADER_CLIENT_VERSION, CrashlyticsCore.getVersion());
        applyNonNullHeader(request, AbstractSpiCall.HEADER_ACCEPT, AbstractSpiCall.ACCEPT_JSON_VALUE);
        applyNonNullHeader(request, HEADER_DEVICE_MODEL, requestData.deviceModel);
        applyNonNullHeader(request, HEADER_OS_BUILD_VERSION, requestData.osBuildVersion);
        applyNonNullHeader(request, HEADER_OS_DISPLAY_VERSION, requestData.osDisplayVersion);
        applyNonNullHeader(request, HEADER_INSTALLATION_ID, requestData.installIdProvider.getCrashlyticsInstallId());
        return request;
    }

    private void applyNonNullHeader(HttpRequest request, String key, String value) {
        if (value != null) {
            request.header(key, value);
        }
    }
}
