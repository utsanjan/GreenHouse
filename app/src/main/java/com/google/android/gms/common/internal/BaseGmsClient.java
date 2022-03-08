package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.IGmsCallbacks;
import com.google.android.gms.common.internal.IGmsServiceBroker;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public abstract class BaseGmsClient<T extends IInterface> {
    public static final int CONNECT_STATE_CONNECTED = 4;
    public static final int CONNECT_STATE_DISCONNECTED = 1;
    public static final int CONNECT_STATE_DISCONNECTING = 5;
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final String KEY_PENDING_INTENT = "pendingIntent";
    private final Context mContext;
    final Handler mHandler;
    private final Object mLock;
    private int zzci;
    private long zzcj;
    private long zzck;
    private int zzcl;
    private long zzcm;
    private zzh zzcn;
    private final Looper zzco;
    private final GmsClientSupervisor zzcp;
    private final GoogleApiAvailabilityLight zzcq;
    private final Object zzcr;
    private IGmsServiceBroker zzcs;
    protected ConnectionProgressReportCallbacks zzct;
    private T zzcu;
    private final ArrayList<zzb<?>> zzcv;
    private zze zzcw;
    private int zzcx;
    private final BaseConnectionCallbacks zzcy;
    private final BaseOnConnectionFailedListener zzcz;
    private final int zzda;
    private final String zzdb;
    private ConnectionResult zzdc;
    private boolean zzdd;
    private volatile zza zzde;
    protected AtomicInteger zzdf;
    private static final Feature[] zzch = new Feature[0];
    public static final String[] GOOGLE_PLUS_REQUIRED_FEATURES = {"service_esmobile", "service_googleme"};

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public interface BaseConnectionCallbacks {
        void onConnected(Bundle bundle);

        void onConnectionSuspended(int i);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public interface BaseOnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public interface ConnectionProgressReportCallbacks {
        void onReportServiceBinding(ConnectionResult connectionResult);
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
        public LegacyClientCallbackAdapter() {
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks
        public void onReportServiceBinding(ConnectionResult connectionResult) {
            if (connectionResult.isSuccess()) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                baseGmsClient.getRemoteService(null, baseGmsClient.getScopes());
            } else if (BaseGmsClient.this.zzcz != null) {
                BaseGmsClient.this.zzcz.onConnectionFailed(connectionResult);
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public interface SignOutCallbacks {
        void onSignOutComplete();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseGmsClient(Context context, Looper looper, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailabilityLight.getInstance(), i, (BaseConnectionCallbacks) Preconditions.checkNotNull(baseConnectionCallbacks), (BaseOnConnectionFailedListener) Preconditions.checkNotNull(baseOnConnectionFailedListener), str);
    }

    protected abstract T createServiceInterface(IBinder iBinder);

    protected abstract String getServiceDescriptor();

    protected abstract String getStartServiceAction();

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    final class zzc extends zzi {
        public zzc(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            ConnectionResult connectionResult;
            ConnectionResult connectionResult2;
            if (BaseGmsClient.this.zzdf.get() != message.arg1) {
                if (zzb(message)) {
                    zza(message);
                }
            } else if ((message.what == 1 || message.what == 7 || ((message.what == 4 && !BaseGmsClient.this.enableLocalFallback()) || message.what == 5)) && !BaseGmsClient.this.isConnecting()) {
                zza(message);
            } else {
                PendingIntent pendingIntent = null;
                if (message.what == 4) {
                    BaseGmsClient.this.zzdc = new ConnectionResult(message.arg2);
                    if (!BaseGmsClient.this.zzn() || BaseGmsClient.this.zzdd) {
                        if (BaseGmsClient.this.zzdc != null) {
                            connectionResult2 = BaseGmsClient.this.zzdc;
                        } else {
                            connectionResult2 = new ConnectionResult(8);
                        }
                        BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult2);
                        BaseGmsClient.this.onConnectionFailed(connectionResult2);
                        return;
                    }
                    BaseGmsClient.this.zza(3, (int) null);
                } else if (message.what == 5) {
                    if (BaseGmsClient.this.zzdc != null) {
                        connectionResult = BaseGmsClient.this.zzdc;
                    } else {
                        connectionResult = new ConnectionResult(8);
                    }
                    BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult);
                    BaseGmsClient.this.onConnectionFailed(connectionResult);
                } else if (message.what == 3) {
                    if (message.obj instanceof PendingIntent) {
                        pendingIntent = (PendingIntent) message.obj;
                    }
                    ConnectionResult connectionResult3 = new ConnectionResult(message.arg2, pendingIntent);
                    BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult3);
                    BaseGmsClient.this.onConnectionFailed(connectionResult3);
                } else if (message.what == 6) {
                    BaseGmsClient.this.zza(5, (int) null);
                    if (BaseGmsClient.this.zzcy != null) {
                        BaseGmsClient.this.zzcy.onConnectionSuspended(message.arg2);
                    }
                    BaseGmsClient.this.onConnectionSuspended(message.arg2);
                    BaseGmsClient.this.zza(5, 1, (int) null);
                } else if (message.what == 2 && !BaseGmsClient.this.isConnected()) {
                    zza(message);
                } else if (zzb(message)) {
                    ((zzb) message.obj).zzo();
                } else {
                    int i = message.what;
                    StringBuilder sb = new StringBuilder(45);
                    sb.append("Don't know how to handle message: ");
                    sb.append(i);
                    Log.wtf("GmsClient", sb.toString(), new Exception());
                }
            }
        }

        private static void zza(Message message) {
            zzb zzbVar = (zzb) message.obj;
            zzbVar.zzk();
            zzbVar.unregister();
        }

        private static boolean zzb(Message message) {
            return message.what == 2 || message.what == 1 || message.what == 7;
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public final class zze implements ServiceConnection {
        private final int zzdj;

        public zze(int i) {
            this.zzdj = i;
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IGmsServiceBroker iGmsServiceBroker;
            if (iBinder == null) {
                BaseGmsClient.this.zzb(16);
                return;
            }
            synchronized (BaseGmsClient.this.zzcr) {
                BaseGmsClient baseGmsClient = BaseGmsClient.this;
                if (iBinder == null) {
                    iGmsServiceBroker = null;
                } else {
                    IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) {
                        iGmsServiceBroker = new IGmsServiceBroker.Stub.zza(iBinder);
                    } else {
                        iGmsServiceBroker = (IGmsServiceBroker) queryLocalInterface;
                    }
                }
                baseGmsClient.zzcs = iGmsServiceBroker;
            }
            BaseGmsClient.this.zza(0, (Bundle) null, this.zzdj);
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            synchronized (BaseGmsClient.this.zzcr) {
                BaseGmsClient.this.zzcs = null;
            }
            BaseGmsClient.this.mHandler.sendMessage(BaseGmsClient.this.mHandler.obtainMessage(6, this.zzdj, 1));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public final class zzf extends zza {
        public zzf(int i, Bundle bundle) {
            super(i, null);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final void zza(ConnectionResult connectionResult) {
            if (!BaseGmsClient.this.enableLocalFallback() || !BaseGmsClient.this.zzn()) {
                BaseGmsClient.this.zzct.onReportServiceBinding(connectionResult);
                BaseGmsClient.this.onConnectionFailed(connectionResult);
                return;
            }
            BaseGmsClient.this.zzb(16);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final boolean zzj() {
            BaseGmsClient.this.zzct.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public abstract class zzb<TListener> {
        private TListener zzdg;
        private boolean zzdh = false;

        public zzb(TListener tlistener) {
            this.zzdg = tlistener;
        }

        protected abstract void zza(TListener tlistener);

        protected abstract void zzk();

        public final void zzo() {
            TListener tlistener;
            synchronized (this) {
                tlistener = this.zzdg;
                if (this.zzdh) {
                    String valueOf = String.valueOf(this);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
                    sb.append("Callback proxy ");
                    sb.append(valueOf);
                    sb.append(" being reused. This is not safe.");
                    Log.w("GmsClient", sb.toString());
                }
            }
            if (tlistener != null) {
                try {
                    zza(tlistener);
                } catch (RuntimeException e) {
                    zzk();
                    throw e;
                }
            } else {
                zzk();
            }
            synchronized (this) {
                this.zzdh = true;
            }
            unregister();
        }

        public final void unregister() {
            removeListener();
            synchronized (BaseGmsClient.this.zzcv) {
                BaseGmsClient.this.zzcv.remove(this);
            }
        }

        public final void removeListener() {
            synchronized (this) {
                this.zzdg = null;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public static final class zzd extends IGmsCallbacks.zza {
        private BaseGmsClient zzdi;
        private final int zzdj;

        public zzd(BaseGmsClient baseGmsClient, int i) {
            this.zzdi = baseGmsClient;
            this.zzdj = i;
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public final void zza(int i, Bundle bundle) {
            Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public final void onPostInitComplete(int i, IBinder iBinder, Bundle bundle) {
            Preconditions.checkNotNull(this.zzdi, "onPostInitComplete can be called only once per call to getRemoteService");
            this.zzdi.onPostInitHandler(i, iBinder, bundle, this.zzdj);
            this.zzdi = null;
        }

        @Override // com.google.android.gms.common.internal.IGmsCallbacks
        public final void zza(int i, IBinder iBinder, zza zzaVar) {
            Preconditions.checkNotNull(this.zzdi, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
            Preconditions.checkNotNull(zzaVar);
            this.zzdi.zza(zzaVar);
            onPostInitComplete(i, iBinder, zzaVar.zzdm);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public final class zzg extends zza {
        private final IBinder zzdk;

        public zzg(int i, IBinder iBinder, Bundle bundle) {
            super(i, bundle);
            this.zzdk = iBinder;
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final void zza(ConnectionResult connectionResult) {
            if (BaseGmsClient.this.zzcz != null) {
                BaseGmsClient.this.zzcz.onConnectionFailed(connectionResult);
            }
            BaseGmsClient.this.onConnectionFailed(connectionResult);
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zza
        protected final boolean zzj() {
            try {
                String interfaceDescriptor = this.zzdk.getInterfaceDescriptor();
                if (!BaseGmsClient.this.getServiceDescriptor().equals(interfaceDescriptor)) {
                    String serviceDescriptor = BaseGmsClient.this.getServiceDescriptor();
                    StringBuilder sb = new StringBuilder(String.valueOf(serviceDescriptor).length() + 34 + String.valueOf(interfaceDescriptor).length());
                    sb.append("service descriptor mismatch: ");
                    sb.append(serviceDescriptor);
                    sb.append(" vs. ");
                    sb.append(interfaceDescriptor);
                    Log.e("GmsClient", sb.toString());
                    return false;
                }
                IInterface createServiceInterface = BaseGmsClient.this.createServiceInterface(this.zzdk);
                if (createServiceInterface == null || (!BaseGmsClient.this.zza(2, 4, (int) createServiceInterface) && !BaseGmsClient.this.zza(3, 4, (int) createServiceInterface))) {
                    return false;
                }
                BaseGmsClient.this.zzdc = null;
                Bundle connectionHint = BaseGmsClient.this.getConnectionHint();
                if (BaseGmsClient.this.zzcy == null) {
                    return true;
                }
                BaseGmsClient.this.zzcy.onConnected(connectionHint);
                return true;
            } catch (RemoteException e) {
                Log.w("GmsClient", "service probably died");
                return false;
            }
        }
    }

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    private abstract class zza extends zzb<Boolean> {
        private final int statusCode;
        private final Bundle zzcf;

        protected zza(int i, Bundle bundle) {
            super(true);
            this.statusCode = i;
            this.zzcf = bundle;
        }

        protected abstract void zza(ConnectionResult connectionResult);

        protected abstract boolean zzj();

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zzb
        protected final void zzk() {
        }

        @Override // com.google.android.gms.common.internal.BaseGmsClient.zzb
        protected final /* synthetic */ void zza(Boolean bool) {
            PendingIntent pendingIntent = null;
            if (bool == null) {
                BaseGmsClient.this.zza(1, (int) null);
                return;
            }
            int i = this.statusCode;
            if (i != 0) {
                if (i != 10) {
                    BaseGmsClient.this.zza(1, (int) null);
                    Bundle bundle = this.zzcf;
                    if (bundle != null) {
                        pendingIntent = (PendingIntent) bundle.getParcelable(BaseGmsClient.KEY_PENDING_INTENT);
                    }
                    zza(new ConnectionResult(this.statusCode, pendingIntent));
                    return;
                }
                BaseGmsClient.this.zza(1, (int) null);
                throw new IllegalStateException(String.format("A fatal developer error has occurred. Class name: %s. Start service action: %s. Service Descriptor: %s. ", getClass().getSimpleName(), BaseGmsClient.this.getStartServiceAction(), BaseGmsClient.this.getServiceDescriptor()));
            } else if (!zzj()) {
                BaseGmsClient.this.zza(1, (int) null);
                zza(new ConnectionResult(8, null));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseGmsClient(Context context, Looper looper, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener, String str) {
        this.mLock = new Object();
        this.zzcr = new Object();
        this.zzcv = new ArrayList<>();
        this.zzcx = 1;
        this.zzdc = null;
        this.zzdd = false;
        this.zzde = null;
        this.zzdf = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.zzco = (Looper) Preconditions.checkNotNull(looper, "Looper must not be null");
        this.zzcp = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcq = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.mHandler = new zzc(looper);
        this.zzda = i;
        this.zzcy = baseConnectionCallbacks;
        this.zzcz = baseOnConnectionFailedListener;
        this.zzdb = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseGmsClient(Context context, Handler handler, GmsClientSupervisor gmsClientSupervisor, GoogleApiAvailabilityLight googleApiAvailabilityLight, int i, BaseConnectionCallbacks baseConnectionCallbacks, BaseOnConnectionFailedListener baseOnConnectionFailedListener) {
        this.mLock = new Object();
        this.zzcr = new Object();
        this.zzcv = new ArrayList<>();
        this.zzcx = 1;
        this.zzdc = null;
        this.zzdd = false;
        this.zzde = null;
        this.zzdf = new AtomicInteger(0);
        this.mContext = (Context) Preconditions.checkNotNull(context, "Context must not be null");
        this.mHandler = (Handler) Preconditions.checkNotNull(handler, "Handler must not be null");
        this.zzco = handler.getLooper();
        this.zzcp = (GmsClientSupervisor) Preconditions.checkNotNull(gmsClientSupervisor, "Supervisor must not be null");
        this.zzcq = (GoogleApiAvailabilityLight) Preconditions.checkNotNull(googleApiAvailabilityLight, "API availability must not be null");
        this.zzda = i;
        this.zzcy = baseConnectionCallbacks;
        this.zzcz = baseOnConnectionFailedListener;
        this.zzdb = null;
    }

    protected String getStartServicePackage() {
        return "com.google.android.gms";
    }

    private final String zzl() {
        String str = this.zzdb;
        return str == null ? this.mContext.getClass().getName() : str;
    }

    protected String getLocalStartServiceAction() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zza zzaVar) {
        this.zzde = zzaVar;
    }

    public final Feature[] getAvailableFeatures() {
        zza zzaVar = this.zzde;
        if (zzaVar == null) {
            return null;
        }
        return zzaVar.zzdn;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onConnectedLocked(T t) {
        this.zzck = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onConnectionSuspended(int i) {
        this.zzci = i;
        this.zzcj = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzcl = connectionResult.getErrorCode();
        this.zzcm = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i, T t) {
        zzh zzhVar;
        boolean z = false;
        if ((i == 4) == (t != null)) {
            z = true;
        }
        Preconditions.checkArgument(z);
        synchronized (this.mLock) {
            this.zzcx = i;
            this.zzcu = t;
            onSetConnectState(i, t);
            if (i != 1) {
                if (i == 2 || i == 3) {
                    if (!(this.zzcw == null || this.zzcn == null)) {
                        String zzt = this.zzcn.zzt();
                        String packageName = this.zzcn.getPackageName();
                        StringBuilder sb = new StringBuilder(String.valueOf(zzt).length() + 70 + String.valueOf(packageName).length());
                        sb.append("Calling connect() while still connected, missing disconnect() for ");
                        sb.append(zzt);
                        sb.append(" on ");
                        sb.append(packageName);
                        Log.e("GmsClient", sb.toString());
                        this.zzcp.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcw, zzl(), this.zzcn.getUseDynamicLookup());
                        this.zzdf.incrementAndGet();
                    }
                    this.zzcw = new zze(this.zzdf.get());
                    if (this.zzcx != 3 || getLocalStartServiceAction() == null) {
                        zzhVar = new zzh(getStartServicePackage(), getStartServiceAction(), false, 129, getUseDynamicLookup());
                    } else {
                        zzhVar = new zzh(getContext().getPackageName(), getLocalStartServiceAction(), true, 129, false);
                    }
                    this.zzcn = zzhVar;
                    if (!zzhVar.getUseDynamicLookup() || getMinApkVersion() >= 17895000) {
                        if (!this.zzcp.zza(new GmsClientSupervisor.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcn.getUseDynamicLookup()), this.zzcw, zzl())) {
                            String zzt2 = this.zzcn.zzt();
                            String packageName2 = this.zzcn.getPackageName();
                            StringBuilder sb2 = new StringBuilder(String.valueOf(zzt2).length() + 34 + String.valueOf(packageName2).length());
                            sb2.append("unable to connect to service: ");
                            sb2.append(zzt2);
                            sb2.append(" on ");
                            sb2.append(packageName2);
                            Log.e("GmsClient", sb2.toString());
                            zza(16, (Bundle) null, this.zzdf.get());
                        }
                    } else {
                        String valueOf = String.valueOf(this.zzcn.zzt());
                        throw new IllegalStateException(valueOf.length() != 0 ? "Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: ".concat(valueOf) : new String("Internal Error, the minimum apk version of this BaseGmsClient is too low to support dynamic lookup. Start service action: "));
                    }
                } else if (i == 4) {
                    onConnectedLocked(t);
                }
            } else if (this.zzcw != null) {
                this.zzcp.zza(this.zzcn.zzt(), this.zzcn.getPackageName(), this.zzcn.zzq(), this.zzcw, zzl(), this.zzcn.getUseDynamicLookup());
                this.zzcw = null;
            }
        }
    }

    void onSetConnectState(int i, T t) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(int i, int i2, T t) {
        synchronized (this.mLock) {
            if (this.zzcx != i) {
                return false;
            }
            zza(i2, (int) t);
            return true;
        }
    }

    public void checkAvailabilityAndConnect() {
        int isGooglePlayServicesAvailable = this.zzcq.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
        if (isGooglePlayServicesAvailable != 0) {
            zza(1, (int) null);
            triggerNotAvailable(new LegacyClientCallbackAdapter(), isGooglePlayServicesAvailable, null);
            return;
        }
        connect(new LegacyClientCallbackAdapter());
    }

    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzct = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        zza(2, (int) null);
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcx == 4;
        }
        return z;
    }

    public boolean isConnecting() {
        boolean z;
        synchronized (this.mLock) {
            if (!(this.zzcx == 2 || this.zzcx == 3)) {
                z = false;
            }
            z = true;
        }
        return z;
    }

    private final boolean zzm() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzcx == 3;
        }
        return z;
    }

    public void disconnect() {
        this.zzdf.incrementAndGet();
        synchronized (this.zzcv) {
            int size = this.zzcv.size();
            for (int i = 0; i < size; i++) {
                this.zzcv.get(i).removeListener();
            }
            this.zzcv.clear();
        }
        synchronized (this.zzcr) {
            this.zzcs = null;
        }
        zza(1, (int) null);
    }

    public void triggerConnectionSuspended(int i) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(6, this.zzdf.get(), i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(int i) {
        int i2;
        if (zzm()) {
            i2 = 5;
            this.zzdd = true;
        } else {
            i2 = 4;
        }
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(i2, this.zzdf.get(), 16));
    }

    protected void triggerNotAvailable(ConnectionProgressReportCallbacks connectionProgressReportCallbacks, int i, PendingIntent pendingIntent) {
        this.zzct = (ConnectionProgressReportCallbacks) Preconditions.checkNotNull(connectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(3, this.zzdf.get(), i, pendingIntent));
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzco;
    }

    public Account getAccount() {
        return null;
    }

    public Feature[] getApiFeatures() {
        return zzch;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Bundle getGetServiceRequestExtraArgs() {
        return new Bundle();
    }

    protected void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(1, i2, -1, new zzg(i, iBinder, bundle)));
    }

    protected final void zza(int i, Bundle bundle, int i2) {
        Handler handler = this.mHandler;
        handler.sendMessage(handler.obtainMessage(7, i2, -1, new zzf(i, null)));
    }

    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public Bundle getConnectionHint() {
        return null;
    }

    public final T getService() throws DeadObjectException {
        T t;
        synchronized (this.mLock) {
            if (this.zzcx != 5) {
                checkConnected();
                Preconditions.checkState(this.zzcu != null, "Client is connected but service is null");
                t = this.zzcu;
            } else {
                throw new DeadObjectException();
            }
        }
        return t;
    }

    public void getRemoteService(IAccountAccessor iAccountAccessor, Set<Scope> set) {
        Throwable e;
        Bundle getServiceRequestExtraArgs = getGetServiceRequestExtraArgs();
        GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzda);
        getServiceRequest.zzak = this.mContext.getPackageName();
        getServiceRequest.zzdt = getServiceRequestExtraArgs;
        if (set != null) {
            getServiceRequest.zzds = (Scope[]) set.toArray(new Scope[set.size()]);
        }
        if (requiresSignIn()) {
            getServiceRequest.zzdu = getAccount() != null ? getAccount() : new Account("<<default account>>", "com.google");
            if (iAccountAccessor != null) {
                getServiceRequest.zzdr = iAccountAccessor.asBinder();
            }
        } else if (requiresAccount()) {
            getServiceRequest.zzdu = getAccount();
        }
        getServiceRequest.zzdv = zzch;
        getServiceRequest.zzdw = getApiFeatures();
        try {
            synchronized (this.zzcr) {
                if (this.zzcs != null) {
                    this.zzcs.getService(new zzd(this, this.zzdf.get()), getServiceRequest);
                } else {
                    Log.w("GmsClient", "mServiceBroker is null, client disconnected");
                }
            }
        } catch (DeadObjectException e2) {
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e2);
            triggerConnectionSuspended(1);
        } catch (RemoteException e3) {
            e = e3;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.zzdf.get());
        } catch (SecurityException e4) {
            throw e4;
        } catch (RuntimeException e5) {
            e = e5;
            Log.w("GmsClient", "IGmsServiceBroker.getService failed", e);
            onPostInitHandler(8, null, null, this.zzdf.get());
        }
    }

    protected boolean enableLocalFallback() {
        return false;
    }

    public boolean requiresSignIn() {
        return false;
    }

    public void onUserSignOut(SignOutCallbacks signOutCallbacks) {
        signOutCallbacks.onSignOutComplete();
    }

    public boolean requiresAccount() {
        return false;
    }

    public boolean requiresGooglePlayServices() {
        return true;
    }

    public boolean providesSignIn() {
        return false;
    }

    public Intent getSignInIntent() {
        throw new UnsupportedOperationException("Not a sign in API");
    }

    protected Set<Scope> getScopes() {
        return Collections.EMPTY_SET;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int i;
        T t;
        IGmsServiceBroker iGmsServiceBroker;
        synchronized (this.mLock) {
            i = this.zzcx;
            t = this.zzcu;
        }
        synchronized (this.zzcr) {
            iGmsServiceBroker = this.zzcs;
        }
        printWriter.append((CharSequence) str).append("mConnectState=");
        if (i == 1) {
            printWriter.print("DISCONNECTED");
        } else if (i == 2) {
            printWriter.print("REMOTE_CONNECTING");
        } else if (i == 3) {
            printWriter.print("LOCAL_CONNECTING");
        } else if (i == 4) {
            printWriter.print("CONNECTED");
        } else if (i != 5) {
            printWriter.print("UNKNOWN");
        } else {
            printWriter.print("DISCONNECTING");
        }
        printWriter.append(" mService=");
        if (t == null) {
            printWriter.append("null");
        } else {
            printWriter.append((CharSequence) getServiceDescriptor()).append("@").append((CharSequence) Integer.toHexString(System.identityHashCode(t.asBinder())));
        }
        printWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
            printWriter.println("null");
        } else {
            printWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzck > 0) {
            PrintWriter append = printWriter.append((CharSequence) str).append("lastConnectedTime=");
            long j = this.zzck;
            String format = simpleDateFormat.format(new Date(this.zzck));
            StringBuilder sb = new StringBuilder(String.valueOf(format).length() + 21);
            sb.append(j);
            sb.append(" ");
            sb.append(format);
            append.println(sb.toString());
        }
        if (this.zzcj > 0) {
            printWriter.append((CharSequence) str).append("lastSuspendedCause=");
            int i2 = this.zzci;
            if (i2 == 1) {
                printWriter.append("CAUSE_SERVICE_DISCONNECTED");
            } else if (i2 != 2) {
                printWriter.append((CharSequence) String.valueOf(i2));
            } else {
                printWriter.append("CAUSE_NETWORK_LOST");
            }
            PrintWriter append2 = printWriter.append(" lastSuspendedTime=");
            long j2 = this.zzcj;
            String format2 = simpleDateFormat.format(new Date(this.zzcj));
            StringBuilder sb2 = new StringBuilder(String.valueOf(format2).length() + 21);
            sb2.append(j2);
            sb2.append(" ");
            sb2.append(format2);
            append2.println(sb2.toString());
        }
        if (this.zzcm > 0) {
            printWriter.append((CharSequence) str).append("lastFailedStatus=").append((CharSequence) CommonStatusCodes.getStatusCodeString(this.zzcl));
            PrintWriter append3 = printWriter.append(" lastFailedTime=");
            long j3 = this.zzcm;
            String format3 = simpleDateFormat.format(new Date(this.zzcm));
            StringBuilder sb3 = new StringBuilder(String.valueOf(format3).length() + 21);
            sb3.append(j3);
            sb3.append(" ");
            sb3.append(format3);
            append3.println(sb3.toString());
        }
    }

    public IBinder getServiceBrokerBinder() {
        synchronized (this.zzcr) {
            if (this.zzcs == null) {
                return null;
            }
            return this.zzcs.asBinder();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zzn() {
        if (this.zzdd || TextUtils.isEmpty(getServiceDescriptor()) || TextUtils.isEmpty(getLocalStartServiceAction())) {
            return false;
        }
        try {
            Class.forName(getServiceDescriptor());
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public String getEndpointPackageName() {
        zzh zzhVar;
        if (isConnected() && (zzhVar = this.zzcn) != null) {
            return zzhVar.getPackageName();
        }
        throw new RuntimeException("Failed to connect when checking package");
    }

    public int getMinApkVersion() {
        return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    protected boolean getUseDynamicLookup() {
        return false;
    }
}
