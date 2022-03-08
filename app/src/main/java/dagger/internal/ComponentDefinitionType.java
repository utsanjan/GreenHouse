package dagger.internal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
/* loaded from: classes.dex */
public @interface ComponentDefinitionType {
    Class<?> value();
}
