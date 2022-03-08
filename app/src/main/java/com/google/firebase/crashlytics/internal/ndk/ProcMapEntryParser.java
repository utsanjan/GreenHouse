package com.google.firebase.crashlytics.internal.ndk;

import com.google.firebase.crashlytics.internal.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
final class ProcMapEntryParser {
    private static final Pattern MAP_REGEX = Pattern.compile("\\s*(\\p{XDigit}+)-\\s*(\\p{XDigit}+)\\s+(.{4})\\s+\\p{XDigit}+\\s+.+\\s+\\d+\\s+(.*)");

    private ProcMapEntryParser() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ProcMapEntry parse(String mapEntry) {
        Matcher m = MAP_REGEX.matcher(mapEntry);
        if (!m.matches()) {
            return null;
        }
        try {
            long address = Long.valueOf(m.group(1), 16).longValue();
            long size = Long.valueOf(m.group(2), 16).longValue() - address;
            String perms = m.group(3);
            String path = m.group(4);
            return new ProcMapEntry(address, size, perms, path);
        } catch (Exception e) {
            Logger logger = Logger.getLogger();
            logger.d("Could not parse map entry: " + mapEntry);
            return null;
        }
    }
}
