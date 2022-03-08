package com.google.firebase.platforminfo;

import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Dependency;
import java.util.Iterator;
import java.util.Set;

/* compiled from: com.google.firebase:firebase-common@@19.3.0 */
/* loaded from: classes.dex */
public class DefaultUserAgentPublisher implements UserAgentPublisher {
    private final GlobalLibraryVersionRegistrar gamesSDKRegistrar;
    private final String javaSDKVersionUserAgent;

    DefaultUserAgentPublisher(Set<LibraryVersion> libraryVersions, GlobalLibraryVersionRegistrar gamesSDKRegistrar) {
        this.javaSDKVersionUserAgent = toUserAgent(libraryVersions);
        this.gamesSDKRegistrar = gamesSDKRegistrar;
    }

    @Override // com.google.firebase.platforminfo.UserAgentPublisher
    public String getUserAgent() {
        if (this.gamesSDKRegistrar.getRegisteredVersions().isEmpty()) {
            return this.javaSDKVersionUserAgent;
        }
        return this.javaSDKVersionUserAgent + ' ' + toUserAgent(this.gamesSDKRegistrar.getRegisteredVersions());
    }

    private static String toUserAgent(Set<LibraryVersion> tokens) {
        StringBuilder sb = new StringBuilder();
        Iterator<LibraryVersion> iterator = tokens.iterator();
        while (iterator.hasNext()) {
            LibraryVersion token = iterator.next();
            sb.append(token.getLibraryName());
            sb.append('/');
            sb.append(token.getVersion());
            if (iterator.hasNext()) {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public static Component<UserAgentPublisher> component() {
        ComponentFactory componentFactory;
        Component.Builder add = Component.builder(UserAgentPublisher.class).add(Dependency.setOf(LibraryVersion.class));
        componentFactory = DefaultUserAgentPublisher$$Lambda$1.instance;
        return add.factory(componentFactory).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ UserAgentPublisher lambda$component$0(ComponentContainer c) {
        return new DefaultUserAgentPublisher(c.setOf(LibraryVersion.class), GlobalLibraryVersionRegistrar.getInstance());
    }
}
