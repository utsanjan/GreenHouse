package okhttp3.internal.publicsuffix;

import andhook.lib.xposed.ClassUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.internal.Util;
import okhttp3.internal.platform.Platform;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;

/* loaded from: classes.dex */
public final class PublicSuffixDatabase {
    private static final byte EXCEPTION_MARKER = 33;
    public static final String PUBLIC_SUFFIX_RESOURCE = "publicsuffixes.gz";
    private byte[] publicSuffixExceptionListBytes;
    private byte[] publicSuffixListBytes;
    private static final byte[] WILDCARD_LABEL = {42};
    private static final String[] EMPTY_RULE = new String[0];
    private static final String[] PREVAILING_RULE = {"*"};
    private static final PublicSuffixDatabase instance = new PublicSuffixDatabase();
    private final AtomicBoolean listRead = new AtomicBoolean(false);
    private final CountDownLatch readCompleteLatch = new CountDownLatch(1);

    public static PublicSuffixDatabase get() {
        return instance;
    }

    public String getEffectiveTldPlusOne(String domain) {
        int firstLabelOffset;
        if (domain != null) {
            String unicodeDomain = IDN.toUnicode(domain);
            String[] domainLabels = unicodeDomain.split("\\.");
            String[] rule = findMatchingRule(domainLabels);
            if (domainLabels.length == rule.length && rule[0].charAt(0) != '!') {
                return null;
            }
            if (rule[0].charAt(0) == '!') {
                firstLabelOffset = domainLabels.length - rule.length;
            } else {
                int firstLabelOffset2 = domainLabels.length;
                firstLabelOffset = firstLabelOffset2 - (rule.length + 1);
            }
            StringBuilder effectiveTldPlusOne = new StringBuilder();
            String[] punycodeLabels = domain.split("\\.");
            for (int i = firstLabelOffset; i < punycodeLabels.length; i++) {
                effectiveTldPlusOne.append(punycodeLabels[i]);
                effectiveTldPlusOne.append(ClassUtils.PACKAGE_SEPARATOR_CHAR);
            }
            int i2 = effectiveTldPlusOne.length();
            effectiveTldPlusOne.deleteCharAt(i2 - 1);
            return effectiveTldPlusOne.toString();
        }
        throw new NullPointerException("domain == null");
    }

    private String[] findMatchingRule(String[] domainLabels) {
        String[] exactRuleLabels;
        String[] wildcardRuleLabels;
        if (this.listRead.get() || !this.listRead.compareAndSet(false, true)) {
            try {
                this.readCompleteLatch.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        } else {
            readTheListUninterruptibly();
        }
        synchronized (this) {
            if (this.publicSuffixListBytes == null) {
                throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
            }
        }
        byte[][] domainLabelsUtf8Bytes = new byte[domainLabels.length];
        for (int i = 0; i < domainLabels.length; i++) {
            domainLabelsUtf8Bytes[i] = domainLabels[i].getBytes(Util.UTF_8);
        }
        String exactMatch = null;
        int i2 = 0;
        while (true) {
            if (i2 >= domainLabelsUtf8Bytes.length) {
                break;
            }
            String rule = binarySearchBytes(this.publicSuffixListBytes, domainLabelsUtf8Bytes, i2);
            if (rule != null) {
                exactMatch = rule;
                break;
            }
            i2++;
        }
        String wildcardMatch = null;
        if (domainLabelsUtf8Bytes.length > 1) {
            byte[][] labelsWithWildcard = (byte[][]) domainLabelsUtf8Bytes.clone();
            int labelIndex = 0;
            while (true) {
                if (labelIndex >= labelsWithWildcard.length - 1) {
                    break;
                }
                labelsWithWildcard[labelIndex] = WILDCARD_LABEL;
                String rule2 = binarySearchBytes(this.publicSuffixListBytes, labelsWithWildcard, labelIndex);
                if (rule2 != null) {
                    wildcardMatch = rule2;
                    break;
                }
                labelIndex++;
            }
        }
        String exception = null;
        if (wildcardMatch != null) {
            int labelIndex2 = 0;
            while (true) {
                if (labelIndex2 >= domainLabelsUtf8Bytes.length - 1) {
                    break;
                }
                String rule3 = binarySearchBytes(this.publicSuffixExceptionListBytes, domainLabelsUtf8Bytes, labelIndex2);
                if (rule3 != null) {
                    exception = rule3;
                    break;
                }
                labelIndex2++;
            }
        }
        if (exception != null) {
            return ("!" + exception).split("\\.");
        } else if (exactMatch == null && wildcardMatch == null) {
            return PREVAILING_RULE;
        } else {
            if (exactMatch != null) {
                exactRuleLabels = exactMatch.split("\\.");
            } else {
                exactRuleLabels = EMPTY_RULE;
            }
            if (wildcardMatch != null) {
                wildcardRuleLabels = wildcardMatch.split("\\.");
            } else {
                wildcardRuleLabels = EMPTY_RULE;
            }
            if (exactRuleLabels.length > wildcardRuleLabels.length) {
                return exactRuleLabels;
            }
            return wildcardRuleLabels;
        }
    }

    /* JADX WARN: Incorrect condition in loop: B:32:0x006e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String binarySearchBytes(byte[] r17, byte[][] r18, int r19) {
        /*
            r0 = r17
            r1 = r18
            r2 = 0
            int r3 = r0.length
            r4 = 0
        L_0x0007:
            if (r2 >= r3) goto L_0x00a0
            int r5 = r2 + r3
            int r5 = r5 / 2
        L_0x000d:
            r6 = 10
            r7 = -1
            if (r5 <= r7) goto L_0x0019
            byte r7 = r0[r5]
            if (r7 == r6) goto L_0x0019
            int r5 = r5 + (-1)
            goto L_0x000d
        L_0x0019:
            int r5 = r5 + 1
            r7 = 1
        L_0x001c:
            int r8 = r5 + r7
            byte r8 = r0[r8]
            if (r8 == r6) goto L_0x0025
            int r7 = r7 + 1
            goto L_0x001c
        L_0x0025:
            int r6 = r5 + r7
            int r6 = r6 - r5
            r8 = r19
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x002d:
            if (r11 == 0) goto L_0x0033
            r12 = 46
            r11 = 0
            goto L_0x0039
        L_0x0033:
            r12 = r1[r8]
            byte r12 = r12[r9]
            r12 = r12 & 255(0xff, float:3.57E-43)
        L_0x0039:
            int r13 = r5 + r10
            byte r13 = r0[r13]
            r13 = r13 & 255(0xff, float:3.57E-43)
            int r14 = r12 - r13
            if (r14 == 0) goto L_0x0044
            goto L_0x0056
        L_0x0044:
            int r10 = r10 + 1
            int r9 = r9 + 1
            if (r10 != r6) goto L_0x004b
            goto L_0x0056
        L_0x004b:
            r15 = r1[r8]
            int r15 = r15.length
            if (r15 != r9) goto L_0x009b
            int r15 = r1.length
            int r15 = r15 + (-1)
            if (r8 != r15) goto L_0x0092
        L_0x0056:
            if (r14 >= 0) goto L_0x005b
            int r3 = r5 + (-1)
            goto L_0x0087
        L_0x005b:
            if (r14 <= 0) goto L_0x0063
            int r12 = r5 + r7
            int r12 = r12 + 1
            r2 = r12
            goto L_0x0087
        L_0x0063:
            int r12 = r6 - r10
            r13 = r1[r8]
            int r13 = r13.length
            int r13 = r13 - r9
            int r15 = r8 + 1
        L_0x006b:
            r16 = r2
            int r2 = r1.length
            if (r15 >= r2) goto L_0x0079
            r2 = r1[r15]
            int r2 = r2.length
            int r13 = r13 + r2
            int r15 = r15 + 1
            r2 = r16
            goto L_0x006b
        L_0x0079:
            if (r13 >= r12) goto L_0x0081
            int r2 = r5 + (-1)
            r3 = r2
            r2 = r16
            goto L_0x0087
        L_0x0081:
            if (r13 <= r12) goto L_0x0089
            int r2 = r5 + r7
            int r2 = r2 + 1
        L_0x0087:
            goto L_0x0007
        L_0x0089:
            java.lang.String r2 = new java.lang.String
            java.nio.charset.Charset r15 = okhttp3.internal.Util.UTF_8
            r2.<init>(r0, r5, r6, r15)
            r4 = r2
            goto L_0x00a2
        L_0x0092:
            r16 = r2
            int r8 = r8 + 1
            r2 = -1
            r9 = 1
            r11 = r9
            r9 = r2
            goto L_0x009d
        L_0x009b:
            r16 = r2
        L_0x009d:
            r2 = r16
            goto L_0x002d
        L_0x00a0:
            r16 = r2
        L_0x00a2:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.publicsuffix.PublicSuffixDatabase.binarySearchBytes(byte[], byte[][], int):java.lang.String");
    }

    private void readTheListUninterruptibly() {
        boolean interrupted = false;
        while (true) {
            try {
                try {
                    readTheList();
                    break;
                } catch (InterruptedIOException e) {
                    Thread.interrupted();
                    interrupted = true;
                } catch (IOException e2) {
                    Platform.get().log(5, "Failed to read public suffix list", e2);
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                    return;
                }
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    private void readTheList() throws IOException {
        InputStream resource = PublicSuffixDatabase.class.getResourceAsStream(PUBLIC_SUFFIX_RESOURCE);
        if (resource != null) {
            BufferedSource bufferedSource = Okio.buffer(new GzipSource(Okio.source(resource)));
            try {
                int totalBytes = bufferedSource.readInt();
                byte[] publicSuffixListBytes = new byte[totalBytes];
                bufferedSource.readFully(publicSuffixListBytes);
                int totalExceptionBytes = bufferedSource.readInt();
                byte[] publicSuffixExceptionListBytes = new byte[totalExceptionBytes];
                bufferedSource.readFully(publicSuffixExceptionListBytes);
                synchronized (this) {
                    this.publicSuffixListBytes = publicSuffixListBytes;
                    this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
                }
                this.readCompleteLatch.countDown();
            } finally {
                Util.closeQuietly(bufferedSource);
            }
        }
    }

    void setListBytes(byte[] publicSuffixListBytes, byte[] publicSuffixExceptionListBytes) {
        this.publicSuffixListBytes = publicSuffixListBytes;
        this.publicSuffixExceptionListBytes = publicSuffixExceptionListBytes;
        this.listRead.set(true);
        this.readCompleteLatch.countDown();
    }
}
