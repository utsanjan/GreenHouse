package com.applisto.appcloner.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.applisto.appcloner.classes.DefaultProvider;
import com.applisto.appcloner.classes.FileAccessMonitor;
import com.applisto.appcloner.classes.HostsBlocker;
import com.applisto.appcloner.classes.PreferenceEditor;
import com.applisto.appcloner.classes.Utils;
import com.applisto.appcloner.service.IRemoteService;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class RemoteService extends Service {
    public static final int INTERFACE_VERSION = 3;
    private static final String TAG = RemoteService.class.getSimpleName();
    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() { // from class: com.applisto.appcloner.service.RemoteService.1
        private void checkCaller() {
            if (!Utils.checkCaller(RemoteService.this)) {
                throw new SecurityException();
            }
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public int getAppClonerInterfaceVersion() {
            checkCaller();
            return 3;
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public void killAppProcesses() throws RemoteException {
            try {
                Context context = RemoteService.this.getApplicationContext();
                DefaultProvider.invokeSecondaryInstance(context, "util.Utils", "killAppProcesses", context);
            } catch (Throwable t) {
                Log.w(RemoteService.TAG, t);
                throw new RemoteException(t.toString());
            }
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public String[] getPreferenceFiles() {
            checkCaller();
            return PreferenceEditor.getPreferenceFiles(RemoteService.this);
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public Map getPreferences(String preferenceFile) {
            checkCaller();
            return PreferenceEditor.getPreferences(RemoteService.this, preferenceFile);
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public void setPreference(String preferenceFile, String key, String preference) {
            checkCaller();
            PreferenceEditor.setPreference(RemoteService.this, preferenceFile, key, preference);
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public Map getAllowedBlockedHosts() {
            checkCaller();
            return HostsBlocker.getAllowedBlockedHosts();
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public void setAllowedBlockedHosts(Map allowedBlockedHosts) {
            checkCaller();
            HostsBlocker.setAllowedBlockedHosts(allowedBlockedHosts);
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public Map getFileAccessMonitorEntries(long afterIndex) {
            checkCaller();
            return FileAccessMonitor.getFileAccessMonitorEntries(afterIndex);
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public List inspectLayout() throws RemoteException {
            checkCaller();
            try {
                Context context = RemoteService.this.getApplicationContext();
                return (List) DefaultProvider.invokeSecondaryStatic(context, "LayoutInspector", "inspectLayout", new Object[0]);
            } catch (Throwable t) {
                Log.w(RemoteService.TAG, t);
                throw new RemoteException(t.toString());
            }
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public boolean performViewAction(int hash, String action, String value) throws RemoteException {
            try {
                Context context = RemoteService.this.getApplicationContext();
                return ((Boolean) DefaultProvider.invokeSecondaryStatic(context, "LayoutInspector", "performViewAction", Integer.valueOf(hash), action, value)).booleanValue();
            } catch (Throwable t) {
                Log.w(RemoteService.TAG, t);
                throw new RemoteException(t.toString());
            }
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.mBinder;
    }
}
