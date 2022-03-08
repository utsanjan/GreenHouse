package com.google.firebase.storage.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.storage.network.NetworkRequest;
import java.util.Random;

/* compiled from: com.google.firebase:firebase-storage@@19.1.1 */
/* loaded from: classes.dex */
public class ExponentialBackoffSender {
    private static final int MAXIMUM_WAIT_TIME_MILLI = 30000;
    private static final int NETWORK_STATUS_POLL_INTERVAL = 1000;
    public static final int RND_MAX = 250;
    private static final String TAG = "ExponenentialBackoff";
    private final InternalAuthProvider authProvider;
    private volatile boolean canceled;
    private final Context context;
    private long retryTime;
    private static final Random random = new Random();
    static Sleeper sleeper = new SleeperImpl();
    static Clock clock = DefaultClock.getInstance();

    public ExponentialBackoffSender(Context context, InternalAuthProvider authProvider, long retryTime) {
        this.context = context;
        this.authProvider = authProvider;
        this.retryTime = retryTime;
    }

    public boolean isRetryableError(int resultCode) {
        return (resultCode >= 500 && resultCode < 600) || resultCode == -2 || resultCode == 429 || resultCode == 408;
    }

    public void sendWithExponentialBackoff(NetworkRequest request) {
        sendWithExponentialBackoff(request, true);
    }

    public void sendWithExponentialBackoff(NetworkRequest request, boolean closeRequest) {
        Preconditions.checkNotNull(request);
        long deadLine = clock.elapsedRealtime() + this.retryTime;
        if (closeRequest) {
            request.performRequest(Util.getCurrentAuthToken(this.authProvider), this.context);
        } else {
            request.performRequestStart(Util.getCurrentAuthToken(this.authProvider));
        }
        int currentSleepTime = 1000;
        while (clock.elapsedRealtime() + currentSleepTime <= deadLine && !request.isResultSuccess() && isRetryableError(request.getResultCode())) {
            try {
                sleeper.sleep(random.nextInt(250) + currentSleepTime);
                if (currentSleepTime < MAXIMUM_WAIT_TIME_MILLI) {
                    if (request.getResultCode() != -2) {
                        currentSleepTime *= 2;
                        Log.w(TAG, "network error occurred, backing off/sleeping.");
                    } else {
                        currentSleepTime = 1000;
                        Log.w(TAG, "network unavailable, sleeping.");
                    }
                }
                if (!this.canceled) {
                    request.reset();
                    if (closeRequest) {
                        request.performRequest(Util.getCurrentAuthToken(this.authProvider), this.context);
                    } else {
                        request.performRequestStart(Util.getCurrentAuthToken(this.authProvider));
                    }
                } else {
                    return;
                }
            } catch (InterruptedException e) {
                Log.w(TAG, "thread interrupted during exponential backoff.");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void cancel() {
        this.canceled = true;
    }

    public void reset() {
        this.canceled = false;
    }
}
