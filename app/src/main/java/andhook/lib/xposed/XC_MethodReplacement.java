package andhook.lib.xposed;

import andhook.lib.xposed.XC_MethodHook;

/* loaded from: classes2.dex */
public abstract class XC_MethodReplacement extends XC_MethodHook {
    public static final XC_MethodReplacement DO_NOTHING = new XC_MethodReplacement(20000) { // from class: andhook.lib.xposed.XC_MethodReplacement.1
        @Override // andhook.lib.xposed.XC_MethodReplacement
        protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
            return null;
        }
    };

    protected abstract Object replaceHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) throws Throwable;

    public XC_MethodReplacement() {
    }

    public XC_MethodReplacement(int priority) {
        super(priority);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // andhook.lib.xposed.XC_MethodHook
    public final void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
        try {
            param.setResult(replaceHookedMethod(param));
        } catch (Throwable t) {
            param.setThrowable(t);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // andhook.lib.xposed.XC_MethodHook
    public final void afterHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
    }

    public static XC_MethodReplacement returnConstant(Object result) {
        return returnConstant(50, result);
    }

    public static XC_MethodReplacement returnConstant(int priority, final Object result) {
        return new XC_MethodReplacement(priority) { // from class: andhook.lib.xposed.XC_MethodReplacement.2
            @Override // andhook.lib.xposed.XC_MethodReplacement
            protected Object replaceHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
                return result;
            }
        };
    }
}
