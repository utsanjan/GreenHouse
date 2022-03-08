package com.squareup.picasso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.squareup.picasso.NetworkRequestHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Dispatcher {
    static final int AIRPLANE_MODE_CHANGE = 10;
    private static final int AIRPLANE_MODE_OFF = 0;
    private static final int AIRPLANE_MODE_ON = 1;
    private static final int BATCH_DELAY = 200;
    private static final String DISPATCHER_THREAD_NAME = "Dispatcher";
    static final int HUNTER_BATCH_COMPLETE = 8;
    static final int HUNTER_COMPLETE = 4;
    static final int HUNTER_DECODE_FAILED = 6;
    static final int HUNTER_DELAY_NEXT_BATCH = 7;
    static final int HUNTER_RETRY = 5;
    static final int NETWORK_STATE_CHANGE = 9;
    static final int REQUEST_BATCH_RESUME = 13;
    static final int REQUEST_CANCEL = 2;
    static final int REQUEST_GCED = 3;
    static final int REQUEST_SUBMIT = 1;
    private static final int RETRY_DELAY = 500;
    static final int TAG_PAUSE = 11;
    static final int TAG_RESUME = 12;
    boolean airplaneMode;
    final Cache cache;
    final Context context;
    final DispatcherThread dispatcherThread;
    final Downloader downloader;
    final Handler handler;
    final Handler mainThreadHandler;
    final NetworkBroadcastReceiver receiver;
    final boolean scansNetworkChanges;
    final ExecutorService service;
    final Stats stats;
    final Map<String, BitmapHunter> hunterMap = new LinkedHashMap();
    final Map<Object, Action> failedActions = new WeakHashMap();
    final Map<Object, Action> pausedActions = new WeakHashMap();
    final Set<Object> pausedTags = new HashSet();
    final List<BitmapHunter> batch = new ArrayList(4);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Dispatcher(Context context, ExecutorService service, Handler mainThreadHandler, Downloader downloader, Cache cache, Stats stats) {
        DispatcherThread dispatcherThread = new DispatcherThread();
        this.dispatcherThread = dispatcherThread;
        dispatcherThread.start();
        Utils.flushStackLocalLeaks(this.dispatcherThread.getLooper());
        this.context = context;
        this.service = service;
        this.handler = new DispatcherHandler(this.dispatcherThread.getLooper(), this);
        this.downloader = downloader;
        this.mainThreadHandler = mainThreadHandler;
        this.cache = cache;
        this.stats = stats;
        this.airplaneMode = Utils.isAirplaneModeOn(this.context);
        this.scansNetworkChanges = Utils.hasPermission(context, "android.permission.ACCESS_NETWORK_STATE");
        NetworkBroadcastReceiver networkBroadcastReceiver = new NetworkBroadcastReceiver(this);
        this.receiver = networkBroadcastReceiver;
        networkBroadcastReceiver.register();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void shutdown() {
        ExecutorService executorService = this.service;
        if (executorService instanceof PicassoExecutorService) {
            executorService.shutdown();
        }
        this.downloader.shutdown();
        this.dispatcherThread.quit();
        Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.Dispatcher.1
            @Override // java.lang.Runnable
            public void run() {
                Dispatcher.this.receiver.unregister();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchSubmit(Action action) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(1, action));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchCancel(Action action) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(2, action));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchPauseTag(Object tag) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(11, tag));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchResumeTag(Object tag) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(12, tag));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchComplete(BitmapHunter hunter) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(4, hunter));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchRetry(BitmapHunter hunter) {
        Handler handler = this.handler;
        handler.sendMessageDelayed(handler.obtainMessage(5, hunter), 500L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void dispatchFailed(BitmapHunter hunter) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(6, hunter));
    }

    void dispatchNetworkStateChange(NetworkInfo info) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(9, info));
    }

    void dispatchAirplaneModeChange(boolean airplaneMode) {
        Handler handler = this.handler;
        handler.sendMessage(handler.obtainMessage(10, airplaneMode ? 1 : 0, 0));
    }

    void performSubmit(Action action) {
        performSubmit(action, true);
    }

    void performSubmit(Action action, boolean dismissFailed) {
        if (this.pausedTags.contains(action.getTag())) {
            this.pausedActions.put(action.getTarget(), action);
            if (action.getPicasso().loggingEnabled) {
                String logId = action.request.logId();
                Utils.log(DISPATCHER_THREAD_NAME, "paused", logId, "because tag '" + action.getTag() + "' is paused");
                return;
            }
            return;
        }
        BitmapHunter hunter = this.hunterMap.get(action.getKey());
        if (hunter != null) {
            hunter.attach(action);
        } else if (!this.service.isShutdown()) {
            BitmapHunter hunter2 = BitmapHunter.forRequest(action.getPicasso(), this, this.cache, this.stats, action);
            hunter2.future = this.service.submit(hunter2);
            this.hunterMap.put(action.getKey(), hunter2);
            if (dismissFailed) {
                this.failedActions.remove(action.getTarget());
            }
            if (action.getPicasso().loggingEnabled) {
                Utils.log(DISPATCHER_THREAD_NAME, "enqueued", action.request.logId());
            }
        } else if (action.getPicasso().loggingEnabled) {
            Utils.log(DISPATCHER_THREAD_NAME, "ignored", action.request.logId(), "because shut down");
        }
    }

    void performCancel(Action action) {
        String key = action.getKey();
        BitmapHunter hunter = this.hunterMap.get(key);
        if (hunter != null) {
            hunter.detach(action);
            if (hunter.cancel()) {
                this.hunterMap.remove(key);
                if (action.getPicasso().loggingEnabled) {
                    Utils.log(DISPATCHER_THREAD_NAME, "canceled", action.getRequest().logId());
                }
            }
        }
        if (this.pausedTags.contains(action.getTag())) {
            this.pausedActions.remove(action.getTarget());
            if (action.getPicasso().loggingEnabled) {
                Utils.log(DISPATCHER_THREAD_NAME, "canceled", action.getRequest().logId(), "because paused request got canceled");
            }
        }
        Action remove = this.failedActions.remove(action.getTarget());
        if (remove != null && remove.getPicasso().loggingEnabled) {
            Utils.log(DISPATCHER_THREAD_NAME, "canceled", remove.getRequest().logId(), "from replaying");
        }
    }

    void performPauseTag(Object tag) {
        if (this.pausedTags.add(tag)) {
            Iterator<BitmapHunter> it = this.hunterMap.values().iterator();
            while (it.hasNext()) {
                BitmapHunter hunter = it.next();
                boolean loggingEnabled = hunter.getPicasso().loggingEnabled;
                Action single = hunter.getAction();
                List<Action> joined = hunter.getActions();
                boolean hasMultiple = joined != null && !joined.isEmpty();
                if (single != null || hasMultiple) {
                    if (single != null && single.getTag().equals(tag)) {
                        hunter.detach(single);
                        this.pausedActions.put(single.getTarget(), single);
                        if (loggingEnabled) {
                            Utils.log(DISPATCHER_THREAD_NAME, "paused", single.request.logId(), "because tag '" + tag + "' was paused");
                        }
                    }
                    if (hasMultiple) {
                        for (int i = joined.size() - 1; i >= 0; i--) {
                            Action action = joined.get(i);
                            if (action.getTag().equals(tag)) {
                                hunter.detach(action);
                                this.pausedActions.put(action.getTarget(), action);
                                if (loggingEnabled) {
                                    Utils.log(DISPATCHER_THREAD_NAME, "paused", action.request.logId(), "because tag '" + tag + "' was paused");
                                }
                            }
                        }
                    }
                    if (hunter.cancel()) {
                        it.remove();
                        if (loggingEnabled) {
                            Utils.log(DISPATCHER_THREAD_NAME, "canceled", Utils.getLogIdsForHunter(hunter), "all actions paused");
                        }
                    }
                }
            }
        }
    }

    void performResumeTag(Object tag) {
        if (this.pausedTags.remove(tag)) {
            List<Action> batch = null;
            Iterator<Action> i = this.pausedActions.values().iterator();
            while (i.hasNext()) {
                Action action = i.next();
                if (action.getTag().equals(tag)) {
                    if (batch == null) {
                        batch = new ArrayList<>();
                    }
                    batch.add(action);
                    i.remove();
                }
            }
            if (batch != null) {
                Handler handler = this.mainThreadHandler;
                handler.sendMessage(handler.obtainMessage(13, batch));
            }
        }
    }

    void performRetry(BitmapHunter hunter) {
        if (!hunter.isCancelled()) {
            boolean willReplay = false;
            if (this.service.isShutdown()) {
                performError(hunter, false);
                return;
            }
            NetworkInfo networkInfo = null;
            if (this.scansNetworkChanges) {
                ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getService(this.context, "connectivity");
                networkInfo = connectivityManager.getActiveNetworkInfo();
            }
            boolean hasConnectivity = networkInfo != null && networkInfo.isConnected();
            boolean shouldRetryHunter = hunter.shouldRetry(this.airplaneMode, networkInfo);
            boolean supportsReplay = hunter.supportsReplay();
            if (!shouldRetryHunter) {
                if (this.scansNetworkChanges && supportsReplay) {
                    willReplay = true;
                }
                performError(hunter, willReplay);
                if (willReplay) {
                    markForReplay(hunter);
                }
            } else if (!this.scansNetworkChanges || hasConnectivity) {
                if (hunter.getPicasso().loggingEnabled) {
                    Utils.log(DISPATCHER_THREAD_NAME, "retrying", Utils.getLogIdsForHunter(hunter));
                }
                if (hunter.getException() instanceof NetworkRequestHandler.ContentLengthException) {
                    hunter.networkPolicy |= NetworkPolicy.NO_CACHE.index;
                }
                hunter.future = this.service.submit(hunter);
            } else {
                performError(hunter, supportsReplay);
                if (supportsReplay) {
                    markForReplay(hunter);
                }
            }
        }
    }

    void performComplete(BitmapHunter hunter) {
        if (MemoryPolicy.shouldWriteToMemoryCache(hunter.getMemoryPolicy())) {
            this.cache.set(hunter.getKey(), hunter.getResult());
        }
        this.hunterMap.remove(hunter.getKey());
        batch(hunter);
        if (hunter.getPicasso().loggingEnabled) {
            Utils.log(DISPATCHER_THREAD_NAME, "batched", Utils.getLogIdsForHunter(hunter), "for completion");
        }
    }

    void performBatchComplete() {
        List<BitmapHunter> copy = new ArrayList<>(this.batch);
        this.batch.clear();
        Handler handler = this.mainThreadHandler;
        handler.sendMessage(handler.obtainMessage(8, copy));
        logBatch(copy);
    }

    void performError(BitmapHunter hunter, boolean willReplay) {
        if (hunter.getPicasso().loggingEnabled) {
            String logIdsForHunter = Utils.getLogIdsForHunter(hunter);
            StringBuilder sb = new StringBuilder();
            sb.append("for error");
            sb.append(willReplay ? " (will replay)" : "");
            Utils.log(DISPATCHER_THREAD_NAME, "batched", logIdsForHunter, sb.toString());
        }
        this.hunterMap.remove(hunter.getKey());
        batch(hunter);
    }

    void performAirplaneModeChange(boolean airplaneMode) {
        this.airplaneMode = airplaneMode;
    }

    void performNetworkStateChange(NetworkInfo info) {
        ExecutorService executorService = this.service;
        if (executorService instanceof PicassoExecutorService) {
            ((PicassoExecutorService) executorService).adjustThreadCount(info);
        }
        if (info != null && info.isConnected()) {
            flushFailedActions();
        }
    }

    private void flushFailedActions() {
        if (!this.failedActions.isEmpty()) {
            Iterator<Action> iterator = this.failedActions.values().iterator();
            while (iterator.hasNext()) {
                Action action = iterator.next();
                iterator.remove();
                if (action.getPicasso().loggingEnabled) {
                    Utils.log(DISPATCHER_THREAD_NAME, "replaying", action.getRequest().logId());
                }
                performSubmit(action, false);
            }
        }
    }

    private void markForReplay(BitmapHunter hunter) {
        Action action = hunter.getAction();
        if (action != null) {
            markForReplay(action);
        }
        List<Action> joined = hunter.getActions();
        if (joined != null) {
            int n = joined.size();
            for (int i = 0; i < n; i++) {
                Action join = joined.get(i);
                markForReplay(join);
            }
        }
    }

    private void markForReplay(Action action) {
        Object target = action.getTarget();
        if (target != null) {
            action.willReplay = true;
            this.failedActions.put(target, action);
        }
    }

    private void batch(BitmapHunter hunter) {
        if (!hunter.isCancelled()) {
            this.batch.add(hunter);
            if (!this.handler.hasMessages(7)) {
                this.handler.sendEmptyMessageDelayed(7, 200L);
            }
        }
    }

    private void logBatch(List<BitmapHunter> copy) {
        if (!(copy == null || copy.isEmpty())) {
            BitmapHunter hunter = copy.get(0);
            Picasso picasso = hunter.getPicasso();
            if (picasso.loggingEnabled) {
                StringBuilder builder = new StringBuilder();
                for (BitmapHunter bitmapHunter : copy) {
                    if (builder.length() > 0) {
                        builder.append(", ");
                    }
                    builder.append(Utils.getLogIdsForHunter(bitmapHunter));
                }
                Utils.log(DISPATCHER_THREAD_NAME, "delivered", builder.toString());
            }
        }
    }

    /* loaded from: classes.dex */
    private static class DispatcherHandler extends Handler {
        private final Dispatcher dispatcher;

        public DispatcherHandler(Looper looper, Dispatcher dispatcher) {
            super(looper);
            this.dispatcher = dispatcher;
        }

        @Override // android.os.Handler
        public void handleMessage(final Message msg) {
            boolean z = false;
            switch (msg.what) {
                case 1:
                    Action action = (Action) msg.obj;
                    this.dispatcher.performSubmit(action);
                    return;
                case 2:
                    Action action2 = (Action) msg.obj;
                    this.dispatcher.performCancel(action2);
                    return;
                case 3:
                case 8:
                default:
                    Picasso.HANDLER.post(new Runnable() { // from class: com.squareup.picasso.Dispatcher.DispatcherHandler.1
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new AssertionError("Unknown handler message received: " + msg.what);
                        }
                    });
                    return;
                case 4:
                    BitmapHunter hunter = (BitmapHunter) msg.obj;
                    this.dispatcher.performComplete(hunter);
                    return;
                case 5:
                    BitmapHunter hunter2 = (BitmapHunter) msg.obj;
                    this.dispatcher.performRetry(hunter2);
                    return;
                case 6:
                    BitmapHunter hunter3 = (BitmapHunter) msg.obj;
                    this.dispatcher.performError(hunter3, false);
                    return;
                case 7:
                    this.dispatcher.performBatchComplete();
                    return;
                case 9:
                    NetworkInfo info = (NetworkInfo) msg.obj;
                    this.dispatcher.performNetworkStateChange(info);
                    return;
                case 10:
                    Dispatcher dispatcher = this.dispatcher;
                    if (msg.arg1 == 1) {
                        z = true;
                    }
                    dispatcher.performAirplaneModeChange(z);
                    return;
                case 11:
                    Object tag = msg.obj;
                    this.dispatcher.performPauseTag(tag);
                    return;
                case 12:
                    Object tag2 = msg.obj;
                    this.dispatcher.performResumeTag(tag2);
                    return;
            }
        }
    }

    /* loaded from: classes.dex */
    static class DispatcherThread extends HandlerThread {
        DispatcherThread() {
            super("Picasso-Dispatcher", 10);
        }
    }

    /* loaded from: classes.dex */
    static class NetworkBroadcastReceiver extends BroadcastReceiver {
        static final String EXTRA_AIRPLANE_STATE = "state";
        private final Dispatcher dispatcher;

        NetworkBroadcastReceiver(Dispatcher dispatcher) {
            this.dispatcher = dispatcher;
        }

        void register() {
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.intent.action.AIRPLANE_MODE");
            if (this.dispatcher.scansNetworkChanges) {
                filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            }
            this.dispatcher.context.registerReceiver(this, filter);
        }

        void unregister() {
            this.dispatcher.context.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.AIRPLANE_MODE".equals(action)) {
                    if (intent.hasExtra(EXTRA_AIRPLANE_STATE)) {
                        this.dispatcher.dispatchAirplaneModeChange(intent.getBooleanExtra(EXTRA_AIRPLANE_STATE, false));
                    }
                } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
                    ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getService(context, "connectivity");
                    this.dispatcher.dispatchNetworkStateChange(connectivityManager.getActiveNetworkInfo());
                }
            }
        }
    }
}
