package com.google.firebase.storage.network;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactory;
import com.google.firebase.storage.network.connection.HttpURLConnectionFactoryImpl;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public abstract class NetworkRequest {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final String CONTENT_TYPE = "Content-Type";
    static final String DELETE = "DELETE";
    static final String GET = "GET";
    public static final int INITIALIZATION_EXCEPTION = -1;
    private static final int MAXIMUM_TOKEN_WAIT_TIME_MS = 30000;
    public static final int NETWORK_UNAVAILABLE = -2;
    static final String PATCH = "PATCH";
    static final String POST = "POST";
    static final String PUT = "PUT";
    private static final String TAG = "NetworkRequest";
    private static final String UTF_8 = "UTF-8";
    private static final String X_FIREBASE_GMPID = "x-firebase-gmpid";
    private static String gmsCoreVersion;
    private HttpURLConnection connection;
    private Context context;
    protected Exception mException;
    protected final Uri mGsUri;
    private String rawStringResponse;
    private Map<String, String> requestHeaders = new HashMap();
    private int resultCode;
    private Map<String, List<String>> resultHeaders;
    private InputStream resultInputStream;
    private int resultingContentLength;
    static Uri sNetworkRequestUrl = Uri.parse("https://firebasestorage.googleapis.com/v0");
    static HttpURLConnectionFactory connectionFactory = new HttpURLConnectionFactoryImpl();

    protected abstract String getAction();

    public NetworkRequest(Uri gsUri, FirebaseApp app) {
        Preconditions.checkNotNull(gsUri);
        Preconditions.checkNotNull(app);
        this.mGsUri = gsUri;
        this.context = app.getApplicationContext();
        setCustomHeader(X_FIREBASE_GMPID, app.getOptions().getApplicationId());
    }

    public static String getAuthority() {
        return sNetworkRequestUrl.getAuthority();
    }

    public static Uri getDefaultURL(Uri gsUri) {
        Preconditions.checkNotNull(gsUri);
        String pathWithoutBucket = getPathWithoutBucket(gsUri);
        Uri.Builder uriBuilder = sNetworkRequestUrl.buildUpon();
        uriBuilder.appendPath("b");
        uriBuilder.appendPath(gsUri.getAuthority());
        uriBuilder.appendPath("o");
        uriBuilder.appendPath(pathWithoutBucket);
        return uriBuilder.build();
    }

    private static String getPathWithoutBucket(Uri gsUri) {
        String path = gsUri.getPath();
        if (path == null) {
            return "";
        }
        return path.startsWith("/") ? path.substring(1) : path;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getPathWithoutBucket() {
        return getPathWithoutBucket(this.mGsUri);
    }

    protected Uri getURL() {
        return getDefaultURL(this.mGsUri);
    }

    protected JSONObject getOutputJSON() {
        return null;
    }

    protected byte[] getOutputRaw() {
        return null;
    }

    protected int getOutputRawSize() {
        return 0;
    }

    protected Map<String, String> getQueryParameters() {
        return null;
    }

    public final void reset() {
        this.mException = null;
        this.resultCode = 0;
    }

    public void setCustomHeader(String key, String value) {
        this.requestHeaders.put(key, value);
    }

    public InputStream getStream() {
        return this.resultInputStream;
    }

    public JSONObject getResultBody() {
        if (!TextUtils.isEmpty(this.rawStringResponse)) {
            try {
                JSONObject resultBody = new JSONObject(this.rawStringResponse);
                return resultBody;
            } catch (JSONException e) {
                Log.e(TAG, "error parsing result into JSON:" + this.rawStringResponse, e);
                JSONObject resultBody2 = new JSONObject();
                return resultBody2;
            }
        } else {
            JSONObject resultBody3 = new JSONObject();
            return resultBody3;
        }
    }

    public void performRequestStart(String token) {
        if (this.mException != null) {
            this.resultCode = -1;
            return;
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "sending network request " + getAction() + " " + getURL());
        }
        ConnectivityManager connMgr = (ConnectivityManager) this.context.getSystemService("connectivity");
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            this.resultCode = -2;
            this.mException = new SocketException("Network subsystem is unavailable");
            return;
        }
        try {
            HttpURLConnection createConnection = createConnection();
            this.connection = createConnection;
            createConnection.setRequestMethod(getAction());
            constructMessage(this.connection, token);
            parseResponse(this.connection);
            if (Log.isLoggable(TAG, 3)) {
                Log.d(TAG, "network request result " + this.resultCode);
            }
        } catch (IOException e) {
            Log.w(TAG, "error sending network request " + getAction() + " " + getURL(), e);
            this.mException = e;
            this.resultCode = -2;
        }
    }

    public void performRequestEnd() {
        HttpURLConnection httpURLConnection = this.connection;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    private final void performRequest(String token) {
        performRequestStart(token);
        try {
            processResponseStream();
        } catch (IOException e) {
            Log.w(TAG, "error sending network request " + getAction() + " " + getURL(), e);
            this.mException = e;
            this.resultCode = -2;
        }
        performRequestEnd();
    }

    public void performRequest(String authToken, Context applicationContext) {
        if (ensureNetworkAvailable(applicationContext)) {
            performRequest(authToken);
        }
    }

    private boolean ensureNetworkAvailable(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        this.mException = new SocketException("Network subsystem is unavailable");
        this.resultCode = -2;
        return false;
    }

    private HttpURLConnection createConnection() throws IOException {
        Uri connectionUri = getURL();
        Map<String, String> queryParams = getQueryParameters();
        if (queryParams != null) {
            Uri.Builder uriBuilder = connectionUri.buildUpon();
            for (Map.Entry<String, String> param : queryParams.entrySet()) {
                uriBuilder.appendQueryParameter(param.getKey(), param.getValue());
            }
            connectionUri = uriBuilder.build();
        }
        HttpURLConnection conn = connectionFactory.createInstance(new URL(connectionUri.toString()));
        return conn;
    }

    private static String getGmsCoreVersion(Context context) {
        if (gmsCoreVersion == null) {
            PackageManager packageManager = context.getPackageManager();
            try {
                PackageInfo info = packageManager.getPackageInfo("com.google.android.gms", 0);
                gmsCoreVersion = info.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                Log.e(TAG, "Unable to find gmscore in package manager", e);
            }
            if (gmsCoreVersion == null) {
                gmsCoreVersion = "[No Gmscore]";
            }
        }
        return gmsCoreVersion;
    }

    private void constructMessage(HttpURLConnection conn, String token) throws IOException {
        int rawSize;
        byte[] rawOutput;
        Preconditions.checkNotNull(conn);
        if (!TextUtils.isEmpty(token)) {
            conn.setRequestProperty("Authorization", "Firebase " + token);
        } else {
            Log.w(TAG, "no auth token for request");
        }
        StringBuilder userAgent = new StringBuilder("Android/");
        String gmsCore = getGmsCoreVersion(this.context);
        if (!TextUtils.isEmpty(gmsCore)) {
            userAgent.append(gmsCore);
        }
        conn.setRequestProperty("X-Firebase-Storage-Version", userAgent.toString());
        Map<String, String> requestProperties = this.requestHeaders;
        for (Map.Entry<String, String> entry : requestProperties.entrySet()) {
            conn.setRequestProperty(entry.getKey(), entry.getValue());
        }
        JSONObject jsonObject = getOutputJSON();
        if (jsonObject != null) {
            rawOutput = jsonObject.toString().getBytes(UTF_8);
            rawSize = rawOutput.length;
        } else {
            rawOutput = getOutputRaw();
            rawSize = getOutputRawSize();
            if (rawSize == 0 && rawOutput != null) {
                rawSize = rawOutput.length;
            }
        }
        if (rawOutput == null || rawOutput.length <= 0) {
            conn.setRequestProperty(CONTENT_LENGTH, "0");
        } else {
            if (jsonObject != null) {
                conn.setRequestProperty(CONTENT_TYPE, "application/json");
            }
            conn.setDoOutput(true);
            conn.setRequestProperty(CONTENT_LENGTH, Integer.toString(rawSize));
        }
        conn.setUseCaches(false);
        conn.setDoInput(true);
        if (rawOutput != null && rawOutput.length > 0) {
            OutputStream outputStream = conn.getOutputStream();
            if (outputStream != null) {
                BufferedOutputStream bufferedStream = new BufferedOutputStream(outputStream);
                try {
                    bufferedStream.write(rawOutput, 0, rawSize);
                } finally {
                    bufferedStream.close();
                }
            } else {
                Log.e(TAG, "Unable to write to the http request!");
            }
        }
    }

    private void parseResponse(HttpURLConnection conn) throws IOException {
        Preconditions.checkNotNull(conn);
        this.resultCode = conn.getResponseCode();
        this.resultHeaders = conn.getHeaderFields();
        this.resultingContentLength = conn.getContentLength();
        if (isResultSuccess()) {
            this.resultInputStream = conn.getInputStream();
        } else {
            this.resultInputStream = conn.getErrorStream();
        }
    }

    private void parseResponse(InputStream resultStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (resultStream != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(resultStream, UTF_8));
            while (true) {
                try {
                    String input = br.readLine();
                    if (input == null) {
                        break;
                    }
                    sb.append(input);
                } finally {
                    br.close();
                }
            }
        }
        this.rawStringResponse = sb.toString();
        if (!isResultSuccess()) {
            this.mException = new IOException(this.rawStringResponse);
        }
    }

    private void processResponseStream() throws IOException {
        if (isResultSuccess()) {
            parseSuccessulResponse(this.resultInputStream);
        } else {
            parseErrorResponse(this.resultInputStream);
        }
    }

    protected void parseSuccessulResponse(InputStream resultStream) throws IOException {
        parseResponse(resultStream);
    }

    protected void parseErrorResponse(InputStream resultStream) throws IOException {
        parseResponse(resultStream);
    }

    public String getRawResult() {
        return this.rawStringResponse;
    }

    public Map<String, String> getResultHeaders() {
        return this.requestHeaders;
    }

    public Exception getException() {
        return this.mException;
    }

    public Map<String, List<String>> getResultHeadersImpl() {
        return this.resultHeaders;
    }

    public int getResultCode() {
        return this.resultCode;
    }

    public boolean isResultSuccess() {
        int i = this.resultCode;
        return i >= 200 && i < 300;
    }

    public String getResultString(String key) {
        List<String> urlList;
        Map<String, List<String>> resultHeaders = getResultHeadersImpl();
        if (resultHeaders == null || (urlList = resultHeaders.get(key)) == null || urlList.size() <= 0) {
            return null;
        }
        return urlList.get(0);
    }

    public int getResultingContentLength() {
        return this.resultingContentLength;
    }

    public <TResult> void completeTask(TaskCompletionSource<TResult> source, TResult result) {
        Exception exception = getException();
        if (!isResultSuccess() || exception != null) {
            StorageException se = StorageException.fromExceptionAndHttpCode(exception, getResultCode());
            source.setException(se);
            return;
        }
        source.setResult(result);
    }
}
