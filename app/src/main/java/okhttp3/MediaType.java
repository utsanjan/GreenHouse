package okhttp3;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public final class MediaType {
    private static final String QUOTED = "\"([^\"]*)\"";
    private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
    @Nullable
    private final String charset;
    private final String mediaType;
    private final String subtype;
    private final String type;
    private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
    private static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");

    private MediaType(String mediaType, String type, String subtype, @Nullable String charset) {
        this.mediaType = mediaType;
        this.type = type;
        this.subtype = subtype;
        this.charset = charset;
    }

    public static MediaType get(String string) {
        String charsetParameter;
        Matcher typeSubtype = TYPE_SUBTYPE.matcher(string);
        if (typeSubtype.lookingAt()) {
            String type = typeSubtype.group(1).toLowerCase(Locale.US);
            String subtype = typeSubtype.group(2).toLowerCase(Locale.US);
            String charset = null;
            Matcher parameter = PARAMETER.matcher(string);
            for (int s = typeSubtype.end(); s < string.length(); s = parameter.end()) {
                parameter.region(s, string.length());
                if (parameter.lookingAt()) {
                    String name = parameter.group(1);
                    if (name != null && name.equalsIgnoreCase("charset")) {
                        String token = parameter.group(2);
                        if (token == null) {
                            charsetParameter = parameter.group(3);
                        } else if (!token.startsWith("'") || !token.endsWith("'") || token.length() <= 2) {
                            charsetParameter = token;
                        } else {
                            charsetParameter = token.substring(1, token.length() - 1);
                        }
                        if (charset == null || charsetParameter.equalsIgnoreCase(charset)) {
                            charset = charsetParameter;
                        } else {
                            throw new IllegalArgumentException("Multiple charsets defined: \"" + charset + "\" and: \"" + charsetParameter + "\" for: \"" + string + '\"');
                        }
                    }
                } else {
                    throw new IllegalArgumentException("Parameter is not formatted correctly: \"" + string.substring(s) + "\" for: \"" + string + '\"');
                }
            }
            return new MediaType(string, type, subtype, charset);
        }
        throw new IllegalArgumentException("No subtype found for: \"" + string + '\"');
    }

    @Nullable
    public static MediaType parse(String string) {
        try {
            return get(string);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public String type() {
        return this.type;
    }

    public String subtype() {
        return this.subtype;
    }

    @Nullable
    public Charset charset() {
        return charset(null);
    }

    @Nullable
    public Charset charset(@Nullable Charset defaultValue) {
        try {
            return this.charset != null ? Charset.forName(this.charset) : defaultValue;
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public String toString() {
        return this.mediaType;
    }

    public boolean equals(@Nullable Object other) {
        return (other instanceof MediaType) && ((MediaType) other).mediaType.equals(this.mediaType);
    }

    public int hashCode() {
        return this.mediaType.hashCode();
    }
}
