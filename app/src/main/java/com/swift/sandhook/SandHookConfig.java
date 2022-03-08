package com.swift.sandhook;

import android.os.Build;

/* loaded from: classes2.dex */
public class SandHookConfig {
    public static volatile ClassLoader initClassLoader;
    public static volatile String libSandHookPath;
    public static volatile int SDK_INT = Build.VERSION.SDK_INT;
    public static volatile boolean DEBUG = true;
    public static volatile String SELF_PACKAGE_NAME = null;
    public static volatile boolean compiler = true;
    public static volatile int curUse = 0;
    public static volatile LibLoader libLoader = new LibLoader() { // from class: com.swift.sandhook.SandHookConfig.1
        @Override // com.swift.sandhook.SandHookConfig.LibLoader
        public void loadLib() {
            if (SandHookConfig.libSandHookPath == null) {
                System.loadLibrary("sandhook");
            } else {
                System.load(SandHookConfig.libSandHookPath);
            }
        }
    };

    /* loaded from: classes2.dex */
    public interface LibLoader {
        void loadLib();
    }
}
