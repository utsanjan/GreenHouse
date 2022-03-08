package andhook.lib.xposed;

import andhook.lib.xposed.callbacks.XCallback;
import java.lang.reflect.Member;

/* loaded from: classes2.dex */
public class XC_MethodHook extends XCallback {
    /* JADX INFO: Access modifiers changed from: protected */
    public XC_MethodHook() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public XC_MethodHook(int priority) {
        super(priority);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void beforeHookedMethod(MethodHookParam param) throws Throwable {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterHookedMethod(MethodHookParam param) throws Throwable {
    }

    /* loaded from: classes2.dex */
    public static final class MethodHookParam extends XCallback.Param {
        public Object[] args;
        public Member method;
        public int slot;
        public Object thisObject;
        private Object result = null;
        private Throwable throwable = null;
        boolean returnEarly = false;

        public Object getResult() {
            return this.result;
        }

        public void setResult(Object result) {
            this.result = result;
            this.throwable = null;
            this.returnEarly = true;
        }

        public Throwable getThrowable() {
            return this.throwable;
        }

        public boolean hasThrowable() {
            return this.throwable != null;
        }

        public void setThrowable(Throwable throwable) {
            this.throwable = throwable;
            this.result = null;
            this.returnEarly = true;
        }

        public Object getResultOrThrowable() throws Throwable {
            Throwable th = this.throwable;
            if (th == null) {
                return this.result;
            }
            throw th;
        }
    }

    /* loaded from: classes2.dex */
    public final class Unhook {
        private final Member hookMethod;
        private final int slot;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Unhook(Member hookMethod, int slot) {
            this.hookMethod = hookMethod;
            this.slot = slot;
        }

        public Member getHookedMethod() {
            return this.hookMethod;
        }

        public XC_MethodHook getCallback() {
            return XC_MethodHook.this;
        }

        public void unhook() {
            XposedBridge.unhookMethod(this.hookMethod, XC_MethodHook.this);
        }

        public boolean restore() {
            return XposedBridge.unhookMethod(this.hookMethod, this.slot);
        }
    }
}
