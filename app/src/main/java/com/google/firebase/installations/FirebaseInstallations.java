package com.google.firebase.installations;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.installations.FirebaseInstallationsException;
import com.google.firebase.installations.local.IidStore;
import com.google.firebase.installations.local.PersistedInstallation;
import com.google.firebase.installations.local.PersistedInstallationEntry;
import com.google.firebase.installations.remote.FirebaseInstallationServiceClient;
import com.google.firebase.installations.remote.InstallationResponse;
import com.google.firebase.installations.remote.TokenResult;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
/* loaded from: classes.dex */
public class FirebaseInstallations implements FirebaseInstallationsApi {
    private static final String CHIME_FIREBASE_APP_NAME = "CHIME_ANDROID_SDK";
    private static final int CORE_POOL_SIZE = 0;
    private static final long KEEP_ALIVE_TIME_IN_SECONDS = 30;
    private static final String LOCKFILE_NAME_GENERATE_FID = "generatefid.lock";
    private static final int MAXIMUM_POOL_SIZE = 1;
    private final ExecutorService backgroundExecutor;
    private final RandomFidGenerator fidGenerator;
    private final FirebaseApp firebaseApp;
    private final IidStore iidStore;
    private final List<StateListener> listeners;
    private final Object lock;
    private final ExecutorService networkExecutor;
    private final PersistedInstallation persistedInstallation;
    private final FirebaseInstallationServiceClient serviceClient;
    private final Utils utils;
    private static final Object lockGenerateFid = new Object();
    private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() { // from class: com.google.firebase.installations.FirebaseInstallations.1
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable r) {
            return new Thread(r, String.format("firebase-installations-executor-%d", Integer.valueOf(this.mCount.getAndIncrement())));
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void access$lambda$0(FirebaseInstallations firebaseInstallations) {
        firebaseInstallations.doGetId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void access$lambda$1(FirebaseInstallations firebaseInstallations) {
        firebaseInstallations.doGetAuthTokenForceRefresh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void access$lambda$2(FirebaseInstallations firebaseInstallations) {
        firebaseInstallations.doGetAuthTokenWithoutForceRefresh();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Void access$lambda$3(FirebaseInstallations firebaseInstallations) {
        return firebaseInstallations.deleteFirebaseInstallationId();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FirebaseInstallations(FirebaseApp firebaseApp, UserAgentPublisher publisher, HeartBeatInfo heartbeatInfo) {
        this(new ThreadPoolExecutor(0, 1, (long) KEEP_ALIVE_TIME_IN_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY), firebaseApp, new FirebaseInstallationServiceClient(firebaseApp.getApplicationContext(), publisher, heartbeatInfo), new PersistedInstallation(firebaseApp), new Utils(), new IidStore(firebaseApp), new RandomFidGenerator());
    }

    FirebaseInstallations(ExecutorService backgroundExecutor, FirebaseApp firebaseApp, FirebaseInstallationServiceClient serviceClient, PersistedInstallation persistedInstallation, Utils utils, IidStore iidStore, RandomFidGenerator fidGenerator) {
        this.lock = new Object();
        this.listeners = new ArrayList();
        this.firebaseApp = firebaseApp;
        this.serviceClient = serviceClient;
        this.persistedInstallation = persistedInstallation;
        this.utils = utils;
        this.iidStore = iidStore;
        this.fidGenerator = fidGenerator;
        this.backgroundExecutor = backgroundExecutor;
        this.networkExecutor = new ThreadPoolExecutor(0, 1, (long) KEEP_ALIVE_TIME_IN_SECONDS, TimeUnit.SECONDS, new LinkedBlockingQueue(), THREAD_FACTORY);
    }

    private void preConditionChecks() {
        Preconditions.checkNotEmpty(getApplicationId());
        Preconditions.checkNotEmpty(getProjectIdentifier());
        Preconditions.checkNotEmpty(getApiKey());
    }

    String getProjectIdentifier() {
        if (TextUtils.isEmpty(this.firebaseApp.getOptions().getProjectId())) {
            return this.firebaseApp.getOptions().getGcmSenderId();
        }
        return this.firebaseApp.getOptions().getProjectId();
    }

    public static FirebaseInstallations getInstance() {
        FirebaseApp defaultFirebaseApp = FirebaseApp.getInstance();
        return getInstance(defaultFirebaseApp);
    }

    public static FirebaseInstallations getInstance(FirebaseApp app) {
        Preconditions.checkArgument(app != null, "Null is not a valid value of FirebaseApp.");
        return (FirebaseInstallations) app.get(FirebaseInstallationsApi.class);
    }

    String getApplicationId() {
        return this.firebaseApp.getOptions().getApplicationId();
    }

    String getApiKey() {
        return this.firebaseApp.getOptions().getApiKey();
    }

    String getName() {
        return this.firebaseApp.getName();
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<String> getId() {
        preConditionChecks();
        Task<String> task = addGetIdListener();
        this.backgroundExecutor.execute(FirebaseInstallations$$Lambda$1.lambdaFactory$(this));
        return task;
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<InstallationTokenResult> getToken(boolean forceRefresh) {
        preConditionChecks();
        Task<InstallationTokenResult> task = addGetAuthTokenListener();
        if (forceRefresh) {
            this.backgroundExecutor.execute(FirebaseInstallations$$Lambda$2.lambdaFactory$(this));
        } else {
            this.backgroundExecutor.execute(FirebaseInstallations$$Lambda$3.lambdaFactory$(this));
        }
        return task;
    }

    @Override // com.google.firebase.installations.FirebaseInstallationsApi
    public Task<Void> delete() {
        return Tasks.call(this.backgroundExecutor, FirebaseInstallations$$Lambda$4.lambdaFactory$(this));
    }

    private Task<String> addGetIdListener() {
        TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        StateListener l = new GetIdListener(taskCompletionSource);
        synchronized (this.lock) {
            this.listeners.add(l);
        }
        return taskCompletionSource.getTask();
    }

    private Task<InstallationTokenResult> addGetAuthTokenListener() {
        TaskCompletionSource<InstallationTokenResult> taskCompletionSource = new TaskCompletionSource<>();
        StateListener l = new GetAuthTokenListener(this.utils, taskCompletionSource);
        synchronized (this.lock) {
            this.listeners.add(l);
        }
        return taskCompletionSource.getTask();
    }

    private void triggerOnStateReached(PersistedInstallationEntry persistedInstallationEntry) {
        synchronized (this.lock) {
            Iterator<StateListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                StateListener l = it.next();
                boolean doneListening = l.onStateReached(persistedInstallationEntry);
                if (doneListening) {
                    it.remove();
                }
            }
        }
    }

    private void triggerOnException(PersistedInstallationEntry prefs, Exception exception) {
        synchronized (this.lock) {
            Iterator<StateListener> it = this.listeners.iterator();
            while (it.hasNext()) {
                StateListener l = it.next();
                boolean doneListening = l.onException(prefs, exception);
                if (doneListening) {
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doGetId() {
        doRegistrationInternal(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doGetAuthTokenWithoutForceRefresh() {
        doRegistrationInternal(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void doGetAuthTokenForceRefresh() {
        doRegistrationInternal(true);
    }

    private final void doRegistrationInternal(boolean forceRefresh) {
        PersistedInstallationEntry prefs = getPrefsWithGeneratedIdMultiProcessSafe();
        if (forceRefresh) {
            prefs = prefs.withClearedAuthToken();
        }
        triggerOnStateReached(prefs);
        this.networkExecutor.execute(FirebaseInstallations$$Lambda$5.lambdaFactory$(this, forceRefresh));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$doRegistrationInternal$0(FirebaseInstallations firebaseInstallations, boolean forceRefresh) {
        firebaseInstallations.doNetworkCall(forceRefresh);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x003f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void doNetworkCall(boolean r4) {
        /*
            r3 = this;
            com.google.firebase.installations.local.PersistedInstallationEntry r0 = r3.getPrefsWithGeneratedIdMultiProcessSafe()
            boolean r1 = r0.isErrored()     // Catch: IOException -> 0x0054
            if (r1 != 0) goto L_0x0023
            boolean r1 = r0.isUnregistered()     // Catch: IOException -> 0x0054
            if (r1 == 0) goto L_0x0011
            goto L_0x0023
        L_0x0011:
            if (r4 != 0) goto L_0x001d
            com.google.firebase.installations.Utils r1 = r3.utils     // Catch: IOException -> 0x0054
            boolean r1 = r1.isAuthTokenExpired(r0)     // Catch: IOException -> 0x0054
            if (r1 == 0) goto L_0x001c
            goto L_0x001d
        L_0x001c:
            return
        L_0x001d:
            com.google.firebase.installations.local.PersistedInstallationEntry r1 = r3.fetchAuthTokenFromServer(r0)     // Catch: IOException -> 0x0054
            r0 = r1
            goto L_0x0028
        L_0x0023:
            com.google.firebase.installations.local.PersistedInstallationEntry r1 = r3.registerFidWithServer(r0)     // Catch: IOException -> 0x0054
            r0 = r1
        L_0x0028:
            com.google.firebase.installations.local.PersistedInstallation r1 = r3.persistedInstallation
            r1.insertOrUpdatePersistedInstallationEntry(r0)
            boolean r1 = r0.isErrored()
            if (r1 == 0) goto L_0x003f
            com.google.firebase.installations.FirebaseInstallationsException r1 = new com.google.firebase.installations.FirebaseInstallationsException
            com.google.firebase.installations.FirebaseInstallationsException$Status r2 = com.google.firebase.installations.FirebaseInstallationsException.Status.BAD_CONFIG
            r1.<init>(r2)
            r3.triggerOnException(r0, r1)
            goto L_0x0053
        L_0x003f:
            boolean r1 = r0.isNotGenerated()
            if (r1 == 0) goto L_0x0050
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "cleared fid due to auth error"
            r1.<init>(r2)
            r3.triggerOnException(r0, r1)
            goto L_0x0053
        L_0x0050:
            r3.triggerOnStateReached(r0)
        L_0x0053:
            return
        L_0x0054:
            r1 = move-exception
            r3.triggerOnException(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.installations.FirebaseInstallations.doNetworkCall(boolean):void");
    }

    private PersistedInstallationEntry getPrefsWithGeneratedIdMultiProcessSafe() {
        PersistedInstallationEntry prefs;
        synchronized (lockGenerateFid) {
            CrossProcessLock lock = CrossProcessLock.acquire(this.firebaseApp.getApplicationContext(), LOCKFILE_NAME_GENERATE_FID);
            prefs = this.persistedInstallation.readPersistedInstallationEntryValue();
            if (prefs.isNotGenerated()) {
                String fid = readExistingIidOrCreateFid(prefs);
                prefs = this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(prefs.withUnregisteredFid(fid));
            }
            if (lock != null) {
                lock.releaseAndClose();
            }
        }
        return prefs;
    }

    private String readExistingIidOrCreateFid(PersistedInstallationEntry prefs) {
        if ((!this.firebaseApp.getName().equals(CHIME_FIREBASE_APP_NAME) && !this.firebaseApp.isDefaultApp()) || !prefs.shouldAttemptMigration()) {
            return this.fidGenerator.createRandomFid();
        }
        String fid = this.iidStore.readIid();
        if (TextUtils.isEmpty(fid)) {
            return this.fidGenerator.createRandomFid();
        }
        return fid;
    }

    private PersistedInstallationEntry registerFidWithServer(PersistedInstallationEntry prefs) throws IOException {
        String iidToken = null;
        if (prefs.getFirebaseInstallationId().length() == 11) {
            iidToken = this.iidStore.readToken();
        }
        InstallationResponse response = this.serviceClient.createFirebaseInstallation(getApiKey(), prefs.getFirebaseInstallationId(), getProjectIdentifier(), getApplicationId(), iidToken);
        int i = AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode[response.getResponseCode().ordinal()];
        if (i == 1) {
            return prefs.withRegisteredFid(response.getFid(), response.getRefreshToken(), this.utils.currentTimeInSecs(), response.getAuthToken().getToken(), response.getAuthToken().getTokenExpirationTimestamp());
        }
        if (i == 2) {
            return prefs.withFisError("BAD CONFIG");
        }
        throw new IOException();
    }

    private PersistedInstallationEntry fetchAuthTokenFromServer(PersistedInstallationEntry prefs) throws IOException {
        TokenResult tokenResult = this.serviceClient.generateAuthToken(getApiKey(), prefs.getFirebaseInstallationId(), getProjectIdentifier(), prefs.getRefreshToken());
        int i = AnonymousClass2.$SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode[tokenResult.getResponseCode().ordinal()];
        if (i == 1) {
            return prefs.withAuthToken(tokenResult.getToken(), tokenResult.getTokenExpirationTimestamp(), this.utils.currentTimeInSecs());
        }
        if (i == 2) {
            return prefs.withFisError("BAD CONFIG");
        }
        if (i == 3) {
            return prefs.withNoGeneratedFid();
        }
        throw new IOException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.firebase:firebase-installations@@16.2.1 */
    /* renamed from: com.google.firebase.installations.FirebaseInstallations$2  reason: invalid class name */
    /* loaded from: classes.dex */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode;
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode;

        static {
            int[] iArr = new int[TokenResult.ResponseCode.values().length];
            $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode = iArr;
            try {
                iArr[TokenResult.ResponseCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode[TokenResult.ResponseCode.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$installations$remote$TokenResult$ResponseCode[TokenResult.ResponseCode.AUTH_ERROR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            int[] iArr2 = new int[InstallationResponse.ResponseCode.values().length];
            $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode = iArr2;
            try {
                iArr2[InstallationResponse.ResponseCode.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$google$firebase$installations$remote$InstallationResponse$ResponseCode[InstallationResponse.ResponseCode.BAD_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Void deleteFirebaseInstallationId() throws FirebaseInstallationsException, IOException {
        PersistedInstallationEntry entry = this.persistedInstallation.readPersistedInstallationEntryValue();
        if (entry.isRegistered()) {
            try {
                this.serviceClient.deleteFirebaseInstallation(getApiKey(), entry.getFirebaseInstallationId(), getProjectIdentifier(), entry.getRefreshToken());
            } catch (FirebaseException e) {
                throw new FirebaseInstallationsException("Failed to delete a Firebase Installation.", FirebaseInstallationsException.Status.BAD_CONFIG);
            }
        }
        this.persistedInstallation.insertOrUpdatePersistedInstallationEntry(entry.withNoGeneratedFid());
        return null;
    }
}
