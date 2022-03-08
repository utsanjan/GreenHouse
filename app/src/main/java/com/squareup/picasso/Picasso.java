package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.widget.ImageView;
import android.widget.RemoteViews;
import androidx.core.internal.view.SupportMenu;
import com.squareup.picasso.Action;
import com.squareup.picasso.RemoteViewsAction;
import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;

/* loaded from: classes.dex */
public class Picasso {
    static final String TAG = "Picasso";
    final Cache cache;
    private final CleanupThread cleanupThread;
    final Context context;
    final Bitmap.Config defaultBitmapConfig;
    final Dispatcher dispatcher;
    boolean indicatorsEnabled;
    private final Listener listener;
    volatile boolean loggingEnabled;
    final ReferenceQueue<Object> referenceQueue;
    private final List<RequestHandler> requestHandlers;
    private final RequestTransformer requestTransformer;
    boolean shutdown;
    final Stats stats;
    final Map<Object, Action> targetToAction;
    final Map<ImageView, DeferredRequestCreator> targetToDeferredRequestCreator;
    static final Handler HANDLER = new Handler(Looper.getMainLooper()) { // from class: com.squareup.picasso.Picasso.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int i = msg.what;
            if (i == 3) {
                Action action = (Action) msg.obj;
                if (action.getPicasso().loggingEnabled) {
                    Utils.log("Main", "canceled", action.request.logId(), "target got garbage collected");
                }
                action.picasso.cancelExistingRequest(action.getTarget());
            } else if (i == 8) {
                List<BitmapHunter> batch = (List) msg.obj;
                int n = batch.size();
                for (int i2 = 0; i2 < n; i2++) {
                    BitmapHunter hunter = batch.get(i2);
                    hunter.picasso.complete(hunter);
                }
            } else if (i == 13) {
                List<Action> batch2 = (List) msg.obj;
                int n2 = batch2.size();
                for (int i3 = 0; i3 < n2; i3++) {
                    Action action2 = batch2.get(i3);
                    action2.picasso.resumeAction(action2);
                }
            } else {
                throw new AssertionError("Unknown handler message received: " + msg.what);
            }
        }
    };
    static volatile Picasso singleton = null;

    /* loaded from: classes.dex */
    public interface Listener {
        void onImageLoadFailed(Picasso picasso, Uri uri, Exception exc);
    }

    /* loaded from: classes.dex */
    public enum Priority {
        LOW,
        NORMAL,
        HIGH
    }

    /* loaded from: classes.dex */
    public interface RequestTransformer {
        public static final RequestTransformer IDENTITY = new RequestTransformer() { // from class: com.squareup.picasso.Picasso.RequestTransformer.1
            @Override // com.squareup.picasso.Picasso.RequestTransformer
            public Request transformRequest(Request request) {
                return request;
            }
        };

        Request transformRequest(Request request);
    }

    Picasso(Context context, Dispatcher dispatcher, Cache cache, Listener listener, RequestTransformer requestTransformer, List<RequestHandler> extraRequestHandlers, Stats stats, Bitmap.Config defaultBitmapConfig, boolean indicatorsEnabled, boolean loggingEnabled) {
        this.context = context;
        this.dispatcher = dispatcher;
        this.cache = cache;
        this.listener = listener;
        this.requestTransformer = requestTransformer;
        this.defaultBitmapConfig = defaultBitmapConfig;
        int extraCount = extraRequestHandlers != null ? extraRequestHandlers.size() : 0;
        List<RequestHandler> allRequestHandlers = new ArrayList<>(7 + extraCount);
        allRequestHandlers.add(new ResourceRequestHandler(context));
        if (extraRequestHandlers != null) {
            allRequestHandlers.addAll(extraRequestHandlers);
        }
        allRequestHandlers.add(new ContactsPhotoRequestHandler(context));
        allRequestHandlers.add(new MediaStoreRequestHandler(context));
        allRequestHandlers.add(new ContentStreamRequestHandler(context));
        allRequestHandlers.add(new AssetRequestHandler(context));
        allRequestHandlers.add(new FileRequestHandler(context));
        allRequestHandlers.add(new NetworkRequestHandler(dispatcher.downloader, stats));
        this.requestHandlers = Collections.unmodifiableList(allRequestHandlers);
        this.stats = stats;
        this.targetToAction = new WeakHashMap();
        this.targetToDeferredRequestCreator = new WeakHashMap();
        this.indicatorsEnabled = indicatorsEnabled;
        this.loggingEnabled = loggingEnabled;
        this.referenceQueue = new ReferenceQueue<>();
        CleanupThread cleanupThread = new CleanupThread(this.referenceQueue, HANDLER);
        this.cleanupThread = cleanupThread;
        cleanupThread.start();
    }

    public void cancelRequest(ImageView view) {
        cancelExistingRequest(view);
    }

    public void cancelRequest(Target target) {
        cancelExistingRequest(target);
    }

    public void cancelRequest(RemoteViews remoteViews, int viewId) {
        cancelExistingRequest(new RemoteViewsAction.RemoteViewsTarget(remoteViews, viewId));
    }

    public void cancelTag(Object tag) {
        Utils.checkMain();
        List<Action> actions = new ArrayList<>(this.targetToAction.values());
        int n = actions.size();
        for (int i = 0; i < n; i++) {
            Action action = actions.get(i);
            if (action.getTag().equals(tag)) {
                cancelExistingRequest(action.getTarget());
            }
        }
    }

    public void pauseTag(Object tag) {
        this.dispatcher.dispatchPauseTag(tag);
    }

    public void resumeTag(Object tag) {
        this.dispatcher.dispatchResumeTag(tag);
    }

    public RequestCreator load(Uri uri) {
        return new RequestCreator(this, uri, 0);
    }

    public RequestCreator load(String path) {
        if (path == null) {
            return new RequestCreator(this, null, 0);
        }
        if (path.trim().length() != 0) {
            return load(Uri.parse(path));
        }
        throw new IllegalArgumentException("Path must not be empty.");
    }

    public RequestCreator load(File file) {
        if (file == null) {
            return new RequestCreator(this, null, 0);
        }
        return load(Uri.fromFile(file));
    }

    public RequestCreator load(int resourceId) {
        if (resourceId != 0) {
            return new RequestCreator(this, null, resourceId);
        }
        throw new IllegalArgumentException("Resource ID must not be zero.");
    }

    public void invalidate(Uri uri) {
        if (uri != null) {
            this.cache.clearKeyUri(uri.toString());
            return;
        }
        throw new IllegalArgumentException("uri == null");
    }

    public void invalidate(String path) {
        if (path != null) {
            invalidate(Uri.parse(path));
            return;
        }
        throw new IllegalArgumentException("path == null");
    }

    public void invalidate(File file) {
        if (file != null) {
            invalidate(Uri.fromFile(file));
            return;
        }
        throw new IllegalArgumentException("file == null");
    }

    @Deprecated
    public boolean isDebugging() {
        return areIndicatorsEnabled() && isLoggingEnabled();
    }

    @Deprecated
    public void setDebugging(boolean debugging) {
        setIndicatorsEnabled(debugging);
    }

    public void setIndicatorsEnabled(boolean enabled) {
        this.indicatorsEnabled = enabled;
    }

    public boolean areIndicatorsEnabled() {
        return this.indicatorsEnabled;
    }

    public void setLoggingEnabled(boolean enabled) {
        this.loggingEnabled = enabled;
    }

    public boolean isLoggingEnabled() {
        return this.loggingEnabled;
    }

    public StatsSnapshot getSnapshot() {
        return this.stats.createSnapshot();
    }

    public void shutdown() {
        if (this == singleton) {
            throw new UnsupportedOperationException("Default singleton instance cannot be shutdown.");
        } else if (!this.shutdown) {
            this.cache.clear();
            this.cleanupThread.shutdown();
            this.stats.shutdown();
            this.dispatcher.shutdown();
            for (DeferredRequestCreator deferredRequestCreator : this.targetToDeferredRequestCreator.values()) {
                deferredRequestCreator.cancel();
            }
            this.targetToDeferredRequestCreator.clear();
            this.shutdown = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<RequestHandler> getRequestHandlers() {
        return this.requestHandlers;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Request transformRequest(Request request) {
        Request transformed = this.requestTransformer.transformRequest(request);
        if (transformed != null) {
            return transformed;
        }
        throw new IllegalStateException("Request transformer " + this.requestTransformer.getClass().getCanonicalName() + " returned null for " + request);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void defer(ImageView view, DeferredRequestCreator request) {
        this.targetToDeferredRequestCreator.put(view, request);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueueAndSubmit(Action action) {
        Object target = action.getTarget();
        if (!(target == null || this.targetToAction.get(target) == action)) {
            cancelExistingRequest(target);
            this.targetToAction.put(target, action);
        }
        submit(action);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void submit(Action action) {
        this.dispatcher.dispatchSubmit(action);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Bitmap quickMemoryCacheCheck(String key) {
        Bitmap cached = this.cache.get(key);
        if (cached != null) {
            this.stats.dispatchCacheHit();
        } else {
            this.stats.dispatchCacheMiss();
        }
        return cached;
    }

    void complete(BitmapHunter hunter) {
        Action single = hunter.getAction();
        List<Action> joined = hunter.getActions();
        boolean shouldDeliver = false;
        boolean hasMultiple = joined != null && !joined.isEmpty();
        if (single != null || hasMultiple) {
            shouldDeliver = true;
        }
        if (shouldDeliver) {
            Uri uri = hunter.getData().uri;
            Exception exception = hunter.getException();
            Bitmap result = hunter.getResult();
            LoadedFrom from = hunter.getLoadedFrom();
            if (single != null) {
                deliverAction(result, from, single);
            }
            if (hasMultiple) {
                int n = joined.size();
                for (int i = 0; i < n; i++) {
                    Action join = joined.get(i);
                    deliverAction(result, from, join);
                }
            }
            Listener listener = this.listener;
            if (!(listener == null || exception == null)) {
                listener.onImageLoadFailed(this, uri, exception);
            }
        }
    }

    void resumeAction(Action action) {
        Bitmap bitmap = null;
        if (MemoryPolicy.shouldReadFromMemoryCache(action.memoryPolicy)) {
            bitmap = quickMemoryCacheCheck(action.getKey());
        }
        if (bitmap != null) {
            deliverAction(bitmap, LoadedFrom.MEMORY, action);
            if (this.loggingEnabled) {
                String logId = action.request.logId();
                Utils.log("Main", "completed", logId, "from " + LoadedFrom.MEMORY);
                return;
            }
            return;
        }
        enqueueAndSubmit(action);
        if (this.loggingEnabled) {
            Utils.log("Main", "resumed", action.request.logId());
        }
    }

    private void deliverAction(Bitmap result, LoadedFrom from, Action action) {
        if (!action.isCancelled()) {
            if (!action.willReplay()) {
                this.targetToAction.remove(action.getTarget());
            }
            if (result == null) {
                action.error();
                if (this.loggingEnabled) {
                    Utils.log("Main", "errored", action.request.logId());
                }
            } else if (from != null) {
                action.complete(result, from);
                if (this.loggingEnabled) {
                    String logId = action.request.logId();
                    Utils.log("Main", "completed", logId, "from " + from);
                }
            } else {
                throw new AssertionError("LoadedFrom cannot be null.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelExistingRequest(Object target) {
        Utils.checkMain();
        Action action = this.targetToAction.remove(target);
        if (action != null) {
            action.cancel();
            this.dispatcher.dispatchCancel(action);
        }
        if (target instanceof ImageView) {
            ImageView targetImageView = (ImageView) target;
            DeferredRequestCreator deferredRequestCreator = this.targetToDeferredRequestCreator.remove(targetImageView);
            if (deferredRequestCreator != null) {
                deferredRequestCreator.cancel();
            }
        }
    }

    /* loaded from: classes.dex */
    private static class CleanupThread extends Thread {
        private final Handler handler;
        private final ReferenceQueue<Object> referenceQueue;

        CleanupThread(ReferenceQueue<Object> referenceQueue, Handler handler) {
            this.referenceQueue = referenceQueue;
            this.handler = handler;
            setDaemon(true);
            setName("Picasso-refQueue");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Process.setThreadPriority(10);
            while (true) {
                try {
                    Action.RequestWeakReference<?> remove = (Action.RequestWeakReference) this.referenceQueue.remove(1000L);
                    Message message = this.handler.obtainMessage();
                    if (remove != null) {
                        message.what = 3;
                        message.obj = remove.action;
                        this.handler.sendMessage(message);
                    } else {
                        message.recycle();
                    }
                } catch (InterruptedException e) {
                    return;
                } catch (Exception e2) {
                    this.handler.post(new Runnable() { // from class: com.squareup.picasso.Picasso.CleanupThread.1
                        @Override // java.lang.Runnable
                        public void run() {
                            throw new RuntimeException(e2);
                        }
                    });
                    return;
                }
            }
        }

        void shutdown() {
            interrupt();
        }
    }

    public static Picasso with(Context context) {
        if (singleton == null) {
            synchronized (Picasso.class) {
                if (singleton == null) {
                    singleton = new Builder(context).build();
                }
            }
        }
        return singleton;
    }

    public static void setSingletonInstance(Picasso picasso) {
        synchronized (Picasso.class) {
            if (singleton == null) {
                singleton = picasso;
            } else {
                throw new IllegalStateException("Singleton instance already exists.");
            }
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Cache cache;
        private final Context context;
        private Bitmap.Config defaultBitmapConfig;
        private Downloader downloader;
        private boolean indicatorsEnabled;
        private Listener listener;
        private boolean loggingEnabled;
        private List<RequestHandler> requestHandlers;
        private ExecutorService service;
        private RequestTransformer transformer;

        public Builder(Context context) {
            if (context != null) {
                this.context = context.getApplicationContext();
                return;
            }
            throw new IllegalArgumentException("Context must not be null.");
        }

        public Builder defaultBitmapConfig(Bitmap.Config bitmapConfig) {
            if (bitmapConfig != null) {
                this.defaultBitmapConfig = bitmapConfig;
                return this;
            }
            throw new IllegalArgumentException("Bitmap config must not be null.");
        }

        public Builder downloader(Downloader downloader) {
            if (downloader == null) {
                throw new IllegalArgumentException("Downloader must not be null.");
            } else if (this.downloader == null) {
                this.downloader = downloader;
                return this;
            } else {
                throw new IllegalStateException("Downloader already set.");
            }
        }

        public Builder executor(ExecutorService executorService) {
            if (executorService == null) {
                throw new IllegalArgumentException("Executor service must not be null.");
            } else if (this.service == null) {
                this.service = executorService;
                return this;
            } else {
                throw new IllegalStateException("Executor service already set.");
            }
        }

        public Builder memoryCache(Cache memoryCache) {
            if (memoryCache == null) {
                throw new IllegalArgumentException("Memory cache must not be null.");
            } else if (this.cache == null) {
                this.cache = memoryCache;
                return this;
            } else {
                throw new IllegalStateException("Memory cache already set.");
            }
        }

        public Builder listener(Listener listener) {
            if (listener == null) {
                throw new IllegalArgumentException("Listener must not be null.");
            } else if (this.listener == null) {
                this.listener = listener;
                return this;
            } else {
                throw new IllegalStateException("Listener already set.");
            }
        }

        public Builder requestTransformer(RequestTransformer transformer) {
            if (transformer == null) {
                throw new IllegalArgumentException("Transformer must not be null.");
            } else if (this.transformer == null) {
                this.transformer = transformer;
                return this;
            } else {
                throw new IllegalStateException("Transformer already set.");
            }
        }

        public Builder addRequestHandler(RequestHandler requestHandler) {
            if (requestHandler != null) {
                if (this.requestHandlers == null) {
                    this.requestHandlers = new ArrayList();
                }
                if (!this.requestHandlers.contains(requestHandler)) {
                    this.requestHandlers.add(requestHandler);
                    return this;
                }
                throw new IllegalStateException("RequestHandler already registered.");
            }
            throw new IllegalArgumentException("RequestHandler must not be null.");
        }

        @Deprecated
        public Builder debugging(boolean debugging) {
            return indicatorsEnabled(debugging);
        }

        public Builder indicatorsEnabled(boolean enabled) {
            this.indicatorsEnabled = enabled;
            return this;
        }

        public Builder loggingEnabled(boolean enabled) {
            this.loggingEnabled = enabled;
            return this;
        }

        public Picasso build() {
            Context context = this.context;
            if (this.downloader == null) {
                this.downloader = Utils.createDefaultDownloader(context);
            }
            if (this.cache == null) {
                this.cache = new LruCache(context);
            }
            if (this.service == null) {
                this.service = new PicassoExecutorService();
            }
            if (this.transformer == null) {
                this.transformer = RequestTransformer.IDENTITY;
            }
            Stats stats = new Stats(this.cache);
            Dispatcher dispatcher = new Dispatcher(context, this.service, Picasso.HANDLER, this.downloader, this.cache, stats);
            return new Picasso(context, dispatcher, this.cache, this.listener, this.transformer, this.requestHandlers, stats, this.defaultBitmapConfig, this.indicatorsEnabled, this.loggingEnabled);
        }
    }

    /* loaded from: classes.dex */
    public enum LoadedFrom {
        MEMORY(-16711936),
        DISK(-16776961),
        NETWORK(SupportMenu.CATEGORY_MASK);
        
        final int debugColor;

        LoadedFrom(int debugColor) {
            this.debugColor = debugColor;
        }
    }
}
