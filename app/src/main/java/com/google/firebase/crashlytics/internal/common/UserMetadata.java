package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class UserMetadata {
    static final int MAX_ATTRIBUTES = 64;
    static final int MAX_ATTRIBUTE_SIZE = 1024;
    private String userId = null;
    private final ConcurrentHashMap<String, String> attributes = new ConcurrentHashMap<>();

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String identifier) {
        this.userId = sanitizeAttribute(identifier);
    }

    public Map<String, String> getCustomKeys() {
        return Collections.unmodifiableMap(this.attributes);
    }

    public void setCustomKey(String key, String value) {
        if (key != null) {
            String key2 = sanitizeAttribute(key);
            if (this.attributes.size() < 64 || this.attributes.containsKey(key2)) {
                this.attributes.put(key2, value == null ? "" : sanitizeAttribute(value));
            } else {
                Logger.getLogger().d("Exceeded maximum number of custom attributes (64)");
            }
        } else {
            throw new IllegalArgumentException("Custom attribute key must not be null.");
        }
    }

    private static String sanitizeAttribute(String input) {
        if (input == null) {
            return input;
        }
        String input2 = input.trim();
        if (input2.length() > 1024) {
            return input2.substring(0, 1024);
        }
        return input2;
    }
}
