package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AnalyticsConnectorReceiver implements AnalyticsConnector.AnalyticsConnectorListener, AnalyticsReceiver {
    public static final String APP_EXCEPTION_EVENT_NAME = "_ae";
    private static final String BREADCRUMB_PARAMS_KEY = "parameters";
    private static final String BREADCRUMB_PREFIX = "$A$:";
    static final String CRASHLYTICS_ORIGIN = "clx";
    public static final String EVENT_NAME_KEY = "name";
    private static final String EVENT_ORIGIN_KEY = "_o";
    private static final String EVENT_PARAMS_KEY = "params";
    static final String LEGACY_CRASH_ORIGIN = "crash";
    private final AnalyticsConnector analyticsConnector;
    private AnalyticsConnector.AnalyticsConnectorHandle analyticsConnectorHandle;
    private final BreadcrumbHandler breadcrumbHandler;
    private AnalyticsReceiver.CrashlyticsOriginEventListener crashOriginEventListener;

    /* loaded from: classes.dex */
    public interface BreadcrumbHandler {
        void dropBreadcrumb(String str);
    }

    public AnalyticsConnectorReceiver(AnalyticsConnector analyticsConnector, BreadcrumbHandler breadcrumbHandler) {
        this.analyticsConnector = analyticsConnector;
        this.breadcrumbHandler = breadcrumbHandler;
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver
    public boolean register() {
        AnalyticsConnector analyticsConnector = this.analyticsConnector;
        if (analyticsConnector == null) {
            Logger.getLogger().d("Firebase Analytics is not present; you will not see automatic logging of events before a crash occurs.");
            return false;
        }
        AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener = analyticsConnector.registerAnalyticsConnectorListener(CRASHLYTICS_ORIGIN, this);
        this.analyticsConnectorHandle = registerAnalyticsConnectorListener;
        if (registerAnalyticsConnectorListener == null) {
            Logger.getLogger().d("Could not register AnalyticsConnectorListener with Crashlytics origin.");
            AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener2 = this.analyticsConnector.registerAnalyticsConnectorListener("crash", this);
            this.analyticsConnectorHandle = registerAnalyticsConnectorListener2;
            if (registerAnalyticsConnectorListener2 != null) {
                Logger.getLogger().w("A new version of the Google Analytics for Firebase SDK is now available. For improved performance and compatibility with Crashlytics, please update to the latest version.");
            }
        }
        return this.analyticsConnectorHandle != null;
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver
    public void unregister() {
        AnalyticsConnector.AnalyticsConnectorHandle analyticsConnectorHandle = this.analyticsConnectorHandle;
        if (analyticsConnectorHandle != null) {
            analyticsConnectorHandle.unregister();
        }
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver
    public void setCrashlyticsOriginEventListener(AnalyticsReceiver.CrashlyticsOriginEventListener listener) {
        this.crashOriginEventListener = listener;
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsReceiver
    public AnalyticsReceiver.CrashlyticsOriginEventListener getCrashlyticsOriginEventListener() {
        return this.crashOriginEventListener;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener
    public void onMessageTriggered(int id, Bundle extras) {
        Logger logger = Logger.getLogger();
        logger.d("AnalyticsConnectorReceiver received message: " + id + " " + extras);
        if (extras != null) {
            Bundle params = extras.getBundle(EVENT_PARAMS_KEY);
            if (params == null) {
                params = new Bundle();
            }
            String origin = params.getString(EVENT_ORIGIN_KEY);
            if (CRASHLYTICS_ORIGIN.equals(origin)) {
                dispatchCrashlyticsOriginEvent(id, extras);
                return;
            }
            String name = extras.getString("name");
            if (name != null) {
                dispatchBreadcrumbEvent(name, params);
            }
        }
    }

    private void dispatchCrashlyticsOriginEvent(int id, Bundle extras) {
        AnalyticsReceiver.CrashlyticsOriginEventListener crashlyticsOriginEventListener = this.crashOriginEventListener;
        if (crashlyticsOriginEventListener != null) {
            crashlyticsOriginEventListener.onCrashlyticsOriginEvent(id, extras);
        }
    }

    private void dispatchBreadcrumbEvent(String name, Bundle params) {
        try {
            String serializedEvent = BREADCRUMB_PREFIX + serializeEvent(name, params);
            this.breadcrumbHandler.dropBreadcrumb(serializedEvent);
        } catch (JSONException e) {
            Logger.getLogger().w("Unable to serialize Firebase Analytics event.");
        }
    }

    private static String serializeEvent(String name, Bundle params) throws JSONException {
        JSONObject enclosingObject = new JSONObject();
        JSONObject paramsObject = new JSONObject();
        for (String key : params.keySet()) {
            paramsObject.put(key, params.get(key));
        }
        enclosingObject.put("name", name);
        enclosingObject.put(BREADCRUMB_PARAMS_KEY, paramsObject);
        return enclosingObject.toString();
    }
}
