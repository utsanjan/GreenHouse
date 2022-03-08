package com.applisto.appcloner.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public interface IRemoteService extends IInterface {
    Map getAllowedBlockedHosts() throws RemoteException;

    int getAppClonerInterfaceVersion() throws RemoteException;

    Map getFileAccessMonitorEntries(long j) throws RemoteException;

    String[] getPreferenceFiles() throws RemoteException;

    Map getPreferences(String str) throws RemoteException;

    List inspectLayout() throws RemoteException;

    void killAppProcesses() throws RemoteException;

    boolean performViewAction(int i, String str, String str2) throws RemoteException;

    void setAllowedBlockedHosts(Map map) throws RemoteException;

    void setPreference(String str, String str2, String str3) throws RemoteException;

    /* loaded from: classes2.dex */
    public static class Default implements IRemoteService {
        @Override // com.applisto.appcloner.service.IRemoteService
        public int getAppClonerInterfaceVersion() throws RemoteException {
            return 0;
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public void killAppProcesses() throws RemoteException {
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public String[] getPreferenceFiles() throws RemoteException {
            return null;
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public Map getPreferences(String preferenceFile) throws RemoteException {
            return null;
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public void setPreference(String preferenceFile, String key, String preference) throws RemoteException {
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public Map getAllowedBlockedHosts() throws RemoteException {
            return null;
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public void setAllowedBlockedHosts(Map allowedBlockedHosts) throws RemoteException {
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public Map getFileAccessMonitorEntries(long afterIndex) throws RemoteException {
            return null;
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public List inspectLayout() throws RemoteException {
            return null;
        }

        @Override // com.applisto.appcloner.service.IRemoteService
        public boolean performViewAction(int hash, String action, String value) throws RemoteException {
            return false;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class Stub extends Binder implements IRemoteService {
        private static final String DESCRIPTOR = "com.applisto.appcloner.service.IRemoteService";
        static final int TRANSACTION_getAllowedBlockedHosts = 6;
        static final int TRANSACTION_getAppClonerInterfaceVersion = 1;
        static final int TRANSACTION_getFileAccessMonitorEntries = 8;
        static final int TRANSACTION_getPreferenceFiles = 3;
        static final int TRANSACTION_getPreferences = 4;
        static final int TRANSACTION_inspectLayout = 9;
        static final int TRANSACTION_killAppProcesses = 2;
        static final int TRANSACTION_performViewAction = 10;
        static final int TRANSACTION_setAllowedBlockedHosts = 7;
        static final int TRANSACTION_setPreference = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteService)) {
                return new Proxy(obj);
            }
            return (IRemoteService) iin;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        int _result = getAppClonerInterfaceVersion();
                        reply.writeNoException();
                        reply.writeInt(_result);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        killAppProcesses();
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        String[] _result2 = getPreferenceFiles();
                        reply.writeNoException();
                        reply.writeStringArray(_result2);
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg0 = data.readString();
                        Map _result3 = getPreferences(_arg0);
                        reply.writeNoException();
                        reply.writeMap(_result3);
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg02 = data.readString();
                        String _arg1 = data.readString();
                        String _arg2 = data.readString();
                        setPreference(_arg02, _arg1, _arg2);
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        Map _result4 = getAllowedBlockedHosts();
                        reply.writeNoException();
                        reply.writeMap(_result4);
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        ClassLoader cl = getClass().getClassLoader();
                        Map _arg03 = data.readHashMap(cl);
                        setAllowedBlockedHosts(_arg03);
                        reply.writeNoException();
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        long _arg04 = data.readLong();
                        Map _result5 = getFileAccessMonitorEntries(_arg04);
                        reply.writeNoException();
                        reply.writeMap(_result5);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        List _result6 = inspectLayout();
                        reply.writeNoException();
                        reply.writeList(_result6);
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        int _arg05 = data.readInt();
                        String _arg12 = data.readString();
                        String _arg22 = data.readString();
                        boolean performViewAction = performViewAction(_arg05, _arg12, _arg22);
                        reply.writeNoException();
                        reply.writeInt(performViewAction ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public static class Proxy implements IRemoteService {
            public static IRemoteService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public int getAppClonerInterfaceVersion() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(1, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppClonerInterfaceVersion();
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public void killAppProcesses() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(2, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().killAppProcesses();
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public String[] getPreferenceFiles() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(3, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferenceFiles();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public Map getPreferences(String preferenceFile) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(preferenceFile);
                    boolean _status = this.mRemote.transact(4, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferences(preferenceFile);
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public void setPreference(String preferenceFile, String key, String preference) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(preferenceFile);
                    _data.writeString(key);
                    _data.writeString(preference);
                    boolean _status = this.mRemote.transact(5, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setPreference(preferenceFile, key, preference);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public Map getAllowedBlockedHosts() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(6, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllowedBlockedHosts();
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public void setAllowedBlockedHosts(Map allowedBlockedHosts) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeMap(allowedBlockedHosts);
                    boolean _status = this.mRemote.transact(7, _data, _reply, 0);
                    if (_status || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                    } else {
                        Stub.getDefaultImpl().setAllowedBlockedHosts(allowedBlockedHosts);
                    }
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public Map getFileAccessMonitorEntries(long afterIndex) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeLong(afterIndex);
                    boolean _status = this.mRemote.transact(8, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFileAccessMonitorEntries(afterIndex);
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    Map _result = _reply.readHashMap(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public List inspectLayout() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _status = this.mRemote.transact(9, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().inspectLayout();
                    }
                    _reply.readException();
                    ClassLoader cl = getClass().getClassLoader();
                    List _result = _reply.readArrayList(cl);
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.applisto.appcloner.service.IRemoteService
            public boolean performViewAction(int hash, String action, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(hash);
                    _data.writeString(action);
                    _data.writeString(value);
                    boolean _result = false;
                    boolean _status = this.mRemote.transact(10, _data, _reply, 0);
                    if (!_status && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().performViewAction(hash, action, value);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IRemoteService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
