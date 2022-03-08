package andhook.lib.xposed.callbacks;

import android.os.Bundle;
import java.io.Serializable;

/* loaded from: classes2.dex */
public abstract class XCallback implements Comparable<XCallback> {
    public static final int PRIORITY_DEFAULT = 50;
    public static final int PRIORITY_HIGHEST = 10000;
    public static final int PRIORITY_LOWEST = -10000;
    public final int priority;

    public XCallback() {
        this.priority = 50;
    }

    public XCallback(int priority) {
        this.priority = priority;
    }

    /* loaded from: classes2.dex */
    public static abstract class Param {
        public final Object[] callbacks = null;
        private Bundle extra;

        public synchronized Bundle getExtra() {
            if (this.extra == null) {
                this.extra = new Bundle();
            }
            return this.extra;
        }

        public Object getObjectExtra(String key) {
            Serializable o = getExtra().getSerializable(key);
            if (o instanceof SerializeWrapper) {
                return ((SerializeWrapper) o).object;
            }
            return null;
        }

        public void setObjectExtra(String key, Object o) {
            getExtra().putSerializable(key, new SerializeWrapper(o));
        }

        /* loaded from: classes2.dex */
        private static class SerializeWrapper implements Serializable {
            private static final long serialVersionUID = 1;
            private final Object object;

            public SerializeWrapper(Object o) {
                this.object = o;
            }
        }
    }

    public static void callAll(Param param) {
        if (param.callbacks != null) {
            for (int i = 0; i < param.callbacks.length; i++) {
                try {
                    ((XCallback) param.callbacks[i]).call(param);
                } catch (Throwable th) {
                }
            }
            return;
        }
        throw new IllegalStateException("This object was not created for use with callAll");
    }

    protected void call(Param param) throws Throwable {
    }

    public int compareTo(XCallback other) {
        if (this == other) {
            return 0;
        }
        int i = other.priority;
        int i2 = this.priority;
        if (i != i2) {
            return i - i2;
        }
        if (System.identityHashCode(this) < System.identityHashCode(other)) {
            return -1;
        }
        return 1;
    }
}
