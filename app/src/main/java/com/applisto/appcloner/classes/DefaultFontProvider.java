package com.applisto.appcloner.classes;

import android.graphics.Typeface;
import android.util.Log;
import com.applisto.appcloner.hooking.Hooking;
import com.swift.sandhook.annotation.HookMethod;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes2.dex */
public class DefaultFontProvider extends AbstractContentProvider {
    private static final String TAG = DefaultFontProvider.class.getSimpleName();
    private static Typeface sTypeface;

    @Override // com.applisto.appcloner.classes.AbstractContentProvider, android.content.ContentProvider
    public boolean onCreate() {
        try {
            sTypeface = Typeface.createFromAsset(getContext().getAssets(), ".fontFile");
            String str = TAG;
            Log.i(str, "onCreate; sTypeface: " + sTypeface);
            Map<? extends String, ? extends Typeface> hashMap = new HashMap<>();
            hashMap.put("sans-serif", sTypeface);
            hashMap.put("sans-serif-light", sTypeface);
            hashMap.put("sans-serif-condensed", sTypeface);
            hashMap.put("sans-serif-thin", sTypeface);
            hashMap.put("sans-serif-medium", sTypeface);
            Field field = Typeface.class.getDeclaredField("sSystemFontMap");
            field.setAccessible(true);
            Map<String, Typeface> oldFonts = new HashMap<>((Map) field.get(null));
            oldFonts.putAll(hashMap);
            field.set(null, oldFonts);
            Hooking.initHooking(getContext());
            Hooking.addHookClass(Hook.class);
            Log.i(TAG, "onCreate; hooks installed");
        } catch (Throwable t) {
            Log.w(TAG, t);
        }
        return true;
    }

    @HookReflectClass("android.graphics.Paint")
    /* loaded from: classes2.dex */
    public static class Hook {
        @HookMethodBackup("setTypeface")
        @MethodParams({Typeface.class})
        static Method setTypefaceBackup;

        @MethodParams({Typeface.class})
        @HookMethod("setTypeface")
        public static Typeface setTypefaceHook(Object thiz, Typeface tf) {
            try {
                Hooking.callInstanceOrigin(setTypefaceBackup, thiz, DefaultFontProvider.sTypeface);
                return DefaultFontProvider.sTypeface;
            } catch (Throwable t) {
                Log.w(DefaultFontProvider.TAG, t);
                return tf;
            }
        }
    }
}
