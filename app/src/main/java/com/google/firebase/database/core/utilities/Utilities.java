package com.google.firebase.database.core.utilities;

import android.net.Uri;
import android.util.Base64;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.RepoInfo;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-database@@19.3.0 */
/* loaded from: classes.dex */
public class Utilities {
    private static final char[] HEX_CHARACTERS = "0123456789abcdef".toCharArray();

    public static ParsedUrl parseUrl(String url) throws DatabaseException {
        try {
            Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            if (scheme != null) {
                String host = uri.getHost();
                if (host != null) {
                    RepoInfo repoInfo = new RepoInfo();
                    repoInfo.host = host.toLowerCase();
                    int port = uri.getPort();
                    boolean z = true;
                    if (port != -1) {
                        if (!scheme.equals("https") && !scheme.equals("wss")) {
                            z = false;
                        }
                        repoInfo.secure = z;
                        repoInfo.host += ":" + port;
                    } else {
                        repoInfo.secure = true;
                    }
                    String namespaceParam = uri.getQueryParameter("ns");
                    if (namespaceParam != null) {
                        repoInfo.namespace = namespaceParam;
                    } else {
                        String[] parts = host.split("\\.", -1);
                        repoInfo.namespace = parts[0].toLowerCase();
                    }
                    repoInfo.internalHost = repoInfo.host;
                    String originalPathString = extractPathString(url).replace("+", " ");
                    Validation.validateRootPathString(originalPathString);
                    ParsedUrl parsedUrl = new ParsedUrl();
                    parsedUrl.path = new Path(originalPathString);
                    parsedUrl.repoInfo = repoInfo;
                    return parsedUrl;
                }
                throw new IllegalArgumentException("Database URL does not specify a valid host");
            }
            throw new IllegalArgumentException("Database URL does not specify a URL scheme");
        } catch (Exception e) {
            throw new DatabaseException("Invalid Firebase Database url specified: " + url, e);
        }
    }

    private static String extractPathString(String originalUrl) {
        int schemeOffset = originalUrl.indexOf("//");
        if (schemeOffset != -1) {
            String urlWithoutScheme = originalUrl.substring(schemeOffset + 2);
            int pathOffset = urlWithoutScheme.indexOf("/");
            if (pathOffset == -1) {
                return "";
            }
            int queryOffset = urlWithoutScheme.indexOf("?");
            if (queryOffset != -1) {
                return urlWithoutScheme.substring(pathOffset + 1, queryOffset);
            }
            return urlWithoutScheme.substring(pathOffset + 1);
        }
        throw new DatabaseException("Firebase Database URL is missing URL scheme");
    }

    public static String sha1HexDigest(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(input.getBytes("UTF-8"));
            byte[] bytes = md.digest();
            return Base64.encodeToString(bytes, 2);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 encoding is required for Firebase Database to run!");
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException("Missing SHA-1 MessageDigest provider.", e2);
        }
    }

    public static String stringHashV2Representation(String value) {
        String escaped = value;
        if (value.indexOf(92) != -1) {
            escaped = escaped.replace("\\", "\\\\");
        }
        if (value.indexOf(34) != -1) {
            escaped = escaped.replace("\"", "\\\"");
        }
        return '\"' + escaped + '\"';
    }

    public static String doubleToHashString(double value) {
        StringBuilder sb = new StringBuilder(16);
        long bits = Double.doubleToLongBits(value);
        for (int i = 7; i >= 0; i--) {
            int byteValue = (int) ((bits >>> (i * 8)) & 255);
            int high = (byteValue >> 4) & 15;
            int low = byteValue & 15;
            sb.append(HEX_CHARACTERS[high]);
            sb.append(HEX_CHARACTERS[low]);
        }
        return sb.toString();
    }

    public static Integer tryParseInt(String num) {
        if (num.length() > 11 || num.length() == 0) {
            return null;
        }
        int i = 0;
        boolean negative = false;
        if (num.charAt(0) == '-') {
            if (num.length() == 1) {
                return null;
            }
            negative = true;
            i = 1;
        }
        long number = 0;
        while (i < num.length()) {
            char c = num.charAt(i);
            if (c < '0' || c > '9') {
                return null;
            }
            number = (10 * number) + (c - '0');
            i++;
        }
        if (negative) {
            if ((-number) < -2147483648L) {
                return null;
            }
            return Integer.valueOf((int) (-number));
        } else if (number > 2147483647L) {
            return null;
        } else {
            return Integer.valueOf((int) number);
        }
    }

    public static int compareInts(int i, int j) {
        if (i < j) {
            return -1;
        }
        if (i == j) {
            return 0;
        }
        return 1;
    }

    public static int compareLongs(long i, long j) {
        if (i < j) {
            return -1;
        }
        if (i == j) {
            return 0;
        }
        return 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <C> C castOrNull(Object o, Class<C> clazz) {
        if (clazz.isAssignableFrom(o.getClass())) {
            return o;
        }
        return null;
    }

    public static <C> C getOrNull(Object o, String key, Class<C> clazz) {
        if (o == null) {
            return null;
        }
        Map map = (Map) castOrNull(o, Map.class);
        Object result = map.get(key);
        if (result != null) {
            return (C) castOrNull(result, clazz);
        }
        return null;
    }

    public static void hardAssert(boolean condition) {
        hardAssert(condition, "");
    }

    public static void hardAssert(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("hardAssert failed: " + message);
        }
    }

    public static Pair<Task<Void>, DatabaseReference.CompletionListener> wrapOnComplete(DatabaseReference.CompletionListener optListener) {
        if (optListener != null) {
            return new Pair<>(null, optListener);
        }
        final TaskCompletionSource<Void> source = new TaskCompletionSource<>();
        DatabaseReference.CompletionListener listener = new DatabaseReference.CompletionListener() { // from class: com.google.firebase.database.core.utilities.Utilities.1
            @Override // com.google.firebase.database.DatabaseReference.CompletionListener
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                if (error != null) {
                    TaskCompletionSource.this.setException(error.toException());
                } else {
                    TaskCompletionSource.this.setResult(null);
                }
            }
        };
        return new Pair<>(source.getTask(), listener);
    }

    public static boolean equals(Object left, Object right) {
        if (left == right) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.equals(right);
    }
}
