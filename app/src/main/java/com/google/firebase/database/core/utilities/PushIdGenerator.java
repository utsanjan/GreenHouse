package com.google.firebase.database.core.utilities;

import java.util.Random;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class PushIdGenerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String PUSH_CHARS = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz";
    private static final Random randGen = new Random();
    private static long lastPushTime = 0;
    private static final int[] lastRandChars = new int[12];

    public static synchronized String generatePushChildName(long now) {
        String sb;
        synchronized (PushIdGenerator.class) {
            boolean duplicateTime = now == lastPushTime;
            lastPushTime = now;
            char[] timeStampChars = new char[8];
            StringBuilder result = new StringBuilder(20);
            for (int i = 7; i >= 0; i--) {
                timeStampChars[i] = PUSH_CHARS.charAt((int) (now % 64));
                now /= 64;
            }
            result.append(timeStampChars);
            if (!duplicateTime) {
                for (int i2 = 0; i2 < 12; i2++) {
                    lastRandChars[i2] = randGen.nextInt(64);
                }
            } else {
                incrementArray();
            }
            for (int i3 = 0; i3 < 12; i3++) {
                result.append(PUSH_CHARS.charAt(lastRandChars[i3]));
            }
            sb = result.toString();
        }
        return sb;
    }

    private static void incrementArray() {
        for (int i = 11; i >= 0; i--) {
            int[] iArr = lastRandChars;
            if (iArr[i] != 63) {
                iArr[i] = iArr[i] + 1;
                return;
            } else {
                iArr[i] = 0;
            }
        }
    }
}
