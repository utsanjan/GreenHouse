package com.google.firebase.crashlytics.internal.report.network;

import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.google.firebase.crashlytics.internal.common.ResponseParser;
import com.google.firebase.crashlytics.internal.network.HttpMethod;
import com.google.firebase.crashlytics.internal.network.HttpRequest;
import com.google.firebase.crashlytics.internal.network.HttpRequestFactory;
import com.google.firebase.crashlytics.internal.network.HttpResponse;
import com.google.firebase.crashlytics.internal.report.model.CreateReportRequest;
import com.google.firebase.crashlytics.internal.report.model.Report;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/* loaded from: classes.dex */
public class DefaultCreateReportSpiCall extends AbstractSpiCall implements CreateReportSpiCall {
    static final String FILE_CONTENT_TYPE = "application/octet-stream";
    static final String FILE_PARAM = "report[file]";
    static final String IDENTIFIER_PARAM = "report[identifier]";
    static final String MULTI_FILE_PARAM = "report[file";
    private final String version;

    public DefaultCreateReportSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, String version) {
        this(protocolAndHostOverride, url, requestFactory, HttpMethod.POST, version);
    }

    DefaultCreateReportSpiCall(String protocolAndHostOverride, String url, HttpRequestFactory requestFactory, HttpMethod method, String version) {
        super(protocolAndHostOverride, url, requestFactory, method);
        this.version = version;
    }

    @Override // com.google.firebase.crashlytics.internal.report.network.CreateReportSpiCall
    public boolean invoke(CreateReportRequest requestData, boolean dataCollectionToken) {
        if (dataCollectionToken) {
            HttpRequest httpRequest = getHttpRequest();
            HttpRequest httpRequest2 = applyMultipartDataTo(applyHeadersTo(httpRequest, requestData), requestData.report);
            Logger logger = Logger.getLogger();
            logger.d("Sending report to: " + getUrl());
            try {
                HttpResponse httpResponse = httpRequest2.execute();
                int statusCode = httpResponse.code();
                Logger logger2 = Logger.getLogger();
                logger2.d("Create report request ID: " + httpResponse.header(AbstractSpiCall.HEADER_REQUEST_ID));
                Logger logger3 = Logger.getLogger();
                logger3.d("Result was: " + statusCode);
                return ResponseParser.parse(statusCode) == 0;
            } catch (IOException ioe) {
                Logger.getLogger().e("Create report HTTP request failed.", ioe);
                throw new RuntimeException(ioe);
            }
        } else {
            throw new RuntimeException("An invalid data collection token was used.");
        }
    }

    private HttpRequest applyHeadersTo(HttpRequest request, CreateReportRequest requestData) {
        HttpRequest request2 = request.header(AbstractSpiCall.HEADER_GOOGLE_APP_ID, requestData.googleAppId).header(AbstractSpiCall.HEADER_CLIENT_TYPE, AbstractSpiCall.ANDROID_CLIENT_TYPE).header(AbstractSpiCall.HEADER_CLIENT_VERSION, this.version);
        Map<String, String> customHeaders = requestData.report.getCustomHeaders();
        for (Map.Entry<String, String> entry : customHeaders.entrySet()) {
            request2 = request2.header(entry);
        }
        return request2;
    }

    private HttpRequest applyMultipartDataTo(HttpRequest request, Report report) {
        File[] files;
        HttpRequest request2 = request.part(IDENTIFIER_PARAM, report.getIdentifier());
        if (report.getFiles().length == 1) {
            Logger.getLogger().d("Adding single file " + report.getFileName() + " to report " + report.getIdentifier());
            return request2.part(FILE_PARAM, report.getFileName(), FILE_CONTENT_TYPE, report.getFile());
        }
        int i = 0;
        for (File file : report.getFiles()) {
            Logger.getLogger().d("Adding file " + file.getName() + " to report " + report.getIdentifier());
            StringBuilder sb = new StringBuilder();
            sb.append(MULTI_FILE_PARAM);
            sb.append(i);
            sb.append("]");
            request2 = request2.part(sb.toString(), file.getName(), FILE_CONTENT_TYPE, file);
            i++;
        }
        return request2;
    }
}
