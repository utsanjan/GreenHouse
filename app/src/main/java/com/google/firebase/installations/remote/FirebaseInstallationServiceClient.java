package com.google.firebase.installations.remote;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.JsonReader;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.firebase.FirebaseException;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
public class FirebaseInstallationServiceClient {
    private static final String ACCEPT_HEADER_KEY = "Accept";
    private static final String API_KEY_HEADER = "x-goog-api-key";
    private static final String CONTENT_ENCODING_HEADER_KEY = "Content-Encoding";
    private static final String CONTENT_TYPE_HEADER_KEY = "Content-Type";
    private static final String CREATE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations";
    private static final String DELETE_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s";
    private static final String FIREBASE_INSTALLATIONS_API_DOMAIN = "firebaseinstallations.googleapis.com";
    private static final String FIREBASE_INSTALLATIONS_API_VERSION = "v1";
    private static final String FIREBASE_INSTALLATIONS_ID_HEARTBEAT_TAG = "fire-installations-id";
    private static final String FIREBASE_INSTALLATION_AUTH_VERSION = "FIS_v2";
    private static final String GENERATE_AUTH_TOKEN_REQUEST_RESOURCE_NAME_FORMAT = "projects/%s/installations/%s/authTokens:generate";
    private static final String GZIP_CONTENT_ENCODING = "gzip";
    private static final String HEART_BEAT_HEADER = "x-firebase-client-log-type";
    private static final String JSON_CONTENT_TYPE = "application/json";
    private static final int MAX_RETRIES = 1;
    private static final int NETWORK_TIMEOUT_MILLIS = 10000;
    static final String PARSING_EXPIRATION_TIME_ERROR_MESSAGE = "Invalid Expiration Timestamp.";
    private static final String SDK_VERSION_PREFIX = "a:";
    private static final String USER_AGENT_HEADER = "x-firebase-client";
    private static final String X_ANDROID_CERT_HEADER_KEY = "X-Android-Cert";
    private static final String X_ANDROID_IID_MIGRATION_KEY = "x-goog-fis-android-iid-migration-auth";
    private static final String X_ANDROID_PACKAGE_HEADER_KEY = "X-Android-Package";
    private final Context context;
    private final HeartBeatInfo heartbeatInfo;
    private final UserAgentPublisher userAgentPublisher;
    private static final Pattern EXPIRATION_TIMESTAMP_PATTERN = Pattern.compile("[0-9]+s");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public FirebaseInstallationServiceClient(Context context, UserAgentPublisher publisher, HeartBeatInfo heartbeatInfo) {
        this.context = context;
        this.userAgentPublisher = publisher;
        this.heartbeatInfo = heartbeatInfo;
    }

    public InstallationResponse createFirebaseInstallation(String apiKey, String fid, String projectID, String appId, String iidToken) throws IOException {
        String resourceName = String.format(CREATE_REQUEST_RESOURCE_NAME_FORMAT, projectID);
        int retryCount = 0;
        URL url = new URL(String.format("https://%s/%s/%s", FIREBASE_INSTALLATIONS_API_DOMAIN, FIREBASE_INSTALLATIONS_API_VERSION, resourceName));
        while (retryCount <= 1) {
            HttpURLConnection httpURLConnection = openHttpURLConnection(url, apiKey);
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                if (iidToken != null) {
                    httpURLConnection.addRequestProperty(X_ANDROID_IID_MIGRATION_KEY, iidToken);
                }
                writeFIDCreateRequestBodyToOutputStream(httpURLConnection, fid, appId);
                int httpResponseCode = httpURLConnection.getResponseCode();
                if (httpResponseCode == 200) {
                    return readCreateResponse(httpURLConnection);
                }
                if (httpResponseCode != 429 && (httpResponseCode < 500 || httpResponseCode >= 600)) {
                    return InstallationResponse.builder().setResponseCode(InstallationResponse.ResponseCode.BAD_CONFIG).build();
                }
                retryCount++;
            } finally {
                httpURLConnection.disconnect();
            }
        }
        throw new IOException();
    }

    private void writeFIDCreateRequestBodyToOutputStream(HttpURLConnection httpURLConnection, String fid, String appId) throws IOException {
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if (outputStream != null) {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            try {
                try {
                    gzipOutputStream.write(buildCreateFirebaseInstallationRequestBody(fid, appId).toString().getBytes("UTF-8"));
                } catch (JSONException e) {
                    throw new IllegalStateException(e);
                }
            } finally {
                gzipOutputStream.close();
            }
        } else {
            throw new IOException("Cannot send CreateInstallation request to FIS. No OutputStream available.");
        }
    }

    private static JSONObject buildCreateFirebaseInstallationRequestBody(String fid, String appId) throws JSONException {
        JSONObject firebaseInstallationData = new JSONObject();
        firebaseInstallationData.put("fid", fid);
        firebaseInstallationData.put("appId", appId);
        firebaseInstallationData.put("authVersion", FIREBASE_INSTALLATION_AUTH_VERSION);
        firebaseInstallationData.put("sdkVersion", "a:16.2.1");
        return firebaseInstallationData;
    }

    private void writeGenerateAuthTokenRequestBodyToOutputStream(HttpURLConnection httpURLConnection) throws IOException {
        OutputStream outputStream = httpURLConnection.getOutputStream();
        if (outputStream != null) {
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            try {
                try {
                    gzipOutputStream.write(buildGenerateAuthTokenRequestBody().toString().getBytes("UTF-8"));
                } catch (JSONException e) {
                    throw new IllegalStateException(e);
                }
            } finally {
                gzipOutputStream.close();
            }
        } else {
            throw new IOException("Cannot send GenerateAuthToken request to FIS. No OutputStream available.");
        }
    }

    private static JSONObject buildGenerateAuthTokenRequestBody() throws JSONException {
        JSONObject sdkVersionData = new JSONObject();
        sdkVersionData.put("sdkVersion", "a:16.2.1");
        JSONObject firebaseInstallationData = new JSONObject();
        firebaseInstallationData.put("installation", sdkVersionData);
        return firebaseInstallationData;
    }

    public void deleteFirebaseInstallation(String apiKey, String fid, String projectID, String refreshToken) throws FirebaseException, IOException {
        String resourceName = String.format(DELETE_REQUEST_RESOURCE_NAME_FORMAT, projectID, fid);
        URL url = new URL(String.format("https://%s/%s/%s", FIREBASE_INSTALLATIONS_API_DOMAIN, FIREBASE_INSTALLATIONS_API_VERSION, resourceName));
        for (int retryCount = 0; retryCount <= 1; retryCount++) {
            HttpURLConnection httpURLConnection = openHttpURLConnection(url, apiKey);
            httpURLConnection.setRequestMethod("DELETE");
            httpURLConnection.addRequestProperty("Authorization", "FIS_v2 " + refreshToken);
            int httpResponseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            if (httpResponseCode != 200 && httpResponseCode != 401 && httpResponseCode != 404) {
                if (httpResponseCode != 429 && (httpResponseCode < 500 || httpResponseCode >= 600)) {
                    throw new FirebaseInstallationsException("bad config while trying to delete FID", FirebaseInstallationsException.Status.BAD_CONFIG);
                }
            } else {
                return;
            }
        }
        throw new IOException();
    }

    public TokenResult generateAuthToken(String apiKey, String fid, String projectID, String refreshToken) throws IOException {
        String resourceName = String.format(GENERATE_AUTH_TOKEN_REQUEST_RESOURCE_NAME_FORMAT, projectID, fid);
        int retryCount = 0;
        URL url = new URL(String.format("https://%s/%s/%s", FIREBASE_INSTALLATIONS_API_DOMAIN, FIREBASE_INSTALLATIONS_API_VERSION, resourceName));
        while (retryCount <= 1) {
            HttpURLConnection httpURLConnection = openHttpURLConnection(url, apiKey);
            try {
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.addRequestProperty("Authorization", "FIS_v2 " + refreshToken);
                writeGenerateAuthTokenRequestBodyToOutputStream(httpURLConnection);
                int httpResponseCode = httpURLConnection.getResponseCode();
                if (httpResponseCode == 200) {
                    return readGenerateAuthTokenResponse(httpURLConnection);
                }
                if (httpResponseCode == 401 || httpResponseCode == 404) {
                    return TokenResult.builder().setResponseCode(TokenResult.ResponseCode.AUTH_ERROR).build();
                }
                if (httpResponseCode != 429 && (httpResponseCode < 500 || httpResponseCode >= 600)) {
                    return TokenResult.builder().setResponseCode(TokenResult.ResponseCode.BAD_CONFIG).build();
                }
                retryCount++;
            } finally {
                httpURLConnection.disconnect();
            }
        }
        throw new IOException();
    }

    private HttpURLConnection openHttpURLConnection(URL url, String apiKey) throws IOException {
        HeartBeatInfo.HeartBeat heartbeat;
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        httpURLConnection.addRequestProperty(CONTENT_TYPE_HEADER_KEY, "application/json");
        httpURLConnection.addRequestProperty("Accept", "application/json");
        httpURLConnection.addRequestProperty(CONTENT_ENCODING_HEADER_KEY, GZIP_CONTENT_ENCODING);
        httpURLConnection.addRequestProperty(X_ANDROID_PACKAGE_HEADER_KEY, this.context.getPackageName());
        HeartBeatInfo heartBeatInfo = this.heartbeatInfo;
        if (!(heartBeatInfo == null || this.userAgentPublisher == null || (heartbeat = heartBeatInfo.getHeartBeatCode(FIREBASE_INSTALLATIONS_ID_HEARTBEAT_TAG)) == HeartBeatInfo.HeartBeat.NONE)) {
            httpURLConnection.addRequestProperty(USER_AGENT_HEADER, this.userAgentPublisher.getUserAgent());
            httpURLConnection.addRequestProperty(HEART_BEAT_HEADER, Integer.toString(heartbeat.getCode()));
        }
        httpURLConnection.addRequestProperty(X_ANDROID_CERT_HEADER_KEY, getFingerprintHashForPackage());
        httpURLConnection.addRequestProperty(API_KEY_HEADER, apiKey);
        return httpURLConnection;
    }

    private InstallationResponse readCreateResponse(HttpURLConnection conn) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream(), UTF_8));
        TokenResult.Builder tokenResult = TokenResult.builder();
        InstallationResponse.Builder builder = InstallationResponse.builder();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("name")) {
                builder.setUri(reader.nextString());
            } else if (name.equals("fid")) {
                builder.setFid(reader.nextString());
            } else if (name.equals("refreshToken")) {
                builder.setRefreshToken(reader.nextString());
            } else if (name.equals("authToken")) {
                reader.beginObject();
                while (reader.hasNext()) {
                    String key = reader.nextName();
                    if (key.equals("token")) {
                        tokenResult.setToken(reader.nextString());
                    } else if (key.equals("expiresIn")) {
                        tokenResult.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(reader.nextString()));
                    } else {
                        reader.skipValue();
                    }
                }
                builder.setAuthToken(tokenResult.build());
                reader.endObject();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.close();
        return builder.setResponseCode(InstallationResponse.ResponseCode.OK).build();
    }

    private TokenResult readGenerateAuthTokenResponse(HttpURLConnection conn) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(conn.getInputStream(), UTF_8));
        TokenResult.Builder builder = TokenResult.builder();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("token")) {
                builder.setToken(reader.nextString());
            } else if (name.equals("expiresIn")) {
                builder.setTokenExpirationTimestamp(parseTokenExpirationTimestamp(reader.nextString()));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        reader.close();
        return builder.setResponseCode(TokenResult.ResponseCode.OK).build();
    }

    private String getFingerprintHashForPackage() {
        try {
            byte[] hash = AndroidUtilsLight.getPackageCertificateHashBytes(this.context, this.context.getPackageName());
            if (hash != null) {
                return Hex.bytesToStringUppercase(hash, false);
            }
            Log.e("ContentValues", "Could not get fingerprint hash for package: " + this.context.getPackageName());
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("ContentValues", "No such package: " + this.context.getPackageName(), e);
            return null;
        }
    }

    static long parseTokenExpirationTimestamp(String expiresIn) {
        Preconditions.checkArgument(EXPIRATION_TIMESTAMP_PATTERN.matcher(expiresIn).matches(), PARSING_EXPIRATION_TIME_ERROR_MESSAGE);
        if (expiresIn == null || expiresIn.length() == 0) {
            return 0L;
        }
        return Long.parseLong(expiresIn.substring(0, expiresIn.length() - 1));
    }
}
