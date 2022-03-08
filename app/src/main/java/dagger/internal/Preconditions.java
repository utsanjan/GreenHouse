package dagger.internal;

/* loaded from: classes.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T reference) {
        if (reference != null) {
            return reference;
        }
        throw null;
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(errorMessage);
    }

    public static <T> T checkNotNull(T reference, String errorMessageTemplate, Object errorMessageArg) {
        String argString;
        if (reference != null) {
            return reference;
        }
        if (!errorMessageTemplate.contains("%s")) {
            throw new IllegalArgumentException("errorMessageTemplate has no format specifiers");
        } else if (errorMessageTemplate.indexOf("%s") == errorMessageTemplate.lastIndexOf("%s")) {
            if (errorMessageArg instanceof Class) {
                argString = ((Class) errorMessageArg).getCanonicalName();
            } else {
                argString = String.valueOf(errorMessageArg);
            }
            throw new NullPointerException(errorMessageTemplate.replace("%s", argString));
        } else {
            throw new IllegalArgumentException("errorMessageTemplate has more than one format specifier");
        }
    }

    public static <T> void checkBuilderRequirement(T requirement, Class<T> clazz) {
        if (requirement == null) {
            throw new IllegalStateException(clazz.getCanonicalName() + " must be set");
        }
    }

    private Preconditions() {
    }
}
