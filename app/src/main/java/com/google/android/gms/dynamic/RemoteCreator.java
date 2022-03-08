package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;

/* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
/* loaded from: classes.dex */
public abstract class RemoteCreator<T> {
    private final String zzis;
    private T zzit;

    /* JADX INFO: Access modifiers changed from: protected */
    public RemoteCreator(String str) {
        this.zzis = str;
    }

    protected abstract T getRemoteCreator(IBinder iBinder);

    /* compiled from: com.google.android.gms:play-services-basement@@17.1.1 */
    /* loaded from: classes.dex */
    public static class RemoteCreatorException extends Exception {
        public RemoteCreatorException(String str) {
            super(str);
        }

        public RemoteCreatorException(String str, Throwable th) {
            super(str, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final T getRemoteCreatorInstance(Context context) throws RemoteCreatorException {
        if (this.zzit == null) {
            Preconditions.checkNotNull(context);
            Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
            if (remoteContext != null) {
                try {
                    this.zzit = getRemoteCreator((IBinder) remoteContext.getClassLoader().loadClass(this.zzis).newInstance());
                } catch (ClassNotFoundException e) {
                    throw new RemoteCreatorException("Could not load creator class.", e);
                } catch (IllegalAccessException e2) {
                    throw new RemoteCreatorException("Could not access creator.", e2);
                } catch (InstantiationException e3) {
                    throw new RemoteCreatorException("Could not instantiate creator.", e3);
                }
            } else {
                throw new RemoteCreatorException("Could not get remote context.");
            }
        }
        return this.zzit;
    }
}
