package com.google.firebase.heartbeatinfo;

/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes.dex */
public interface HeartBeatInfo {
    HeartBeat getHeartBeatCode(String str);

    /* compiled from: com.google.firebase:firebase-common@@19.3.0 */
    /* loaded from: classes.dex */
    public enum HeartBeat {
        NONE(0),
        SDK(1),
        GLOBAL(2),
        COMBINED(3);
        
        private final int code;

        HeartBeat(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }
}
