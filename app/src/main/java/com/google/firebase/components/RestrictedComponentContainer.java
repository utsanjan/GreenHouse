package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
/* loaded from: classes.dex */
public final class RestrictedComponentContainer extends AbstractComponentContainer {
    private final Set<Class<?>> allowedDirectInterfaces;
    private final Set<Class<?>> allowedProviderInterfaces;
    private final Set<Class<?>> allowedPublishedEvents;
    private final Set<Class<?>> allowedSetDirectInterfaces;
    private final Set<Class<?>> allowedSetProviderInterfaces;
    private final ComponentContainer delegateContainer;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RestrictedComponentContainer(Component<?> component, ComponentContainer container) {
        Set<Class<?>> directInterfaces = new HashSet<>();
        Set<Class<?>> providerInterfaces = new HashSet<>();
        Set<Class<?>> setDirectInterfaces = new HashSet<>();
        Set<Class<?>> setProviderInterfaces = new HashSet<>();
        for (Dependency dependency : component.getDependencies()) {
            if (dependency.isDirectInjection()) {
                if (dependency.isSet()) {
                    setDirectInterfaces.add(dependency.getInterface());
                } else {
                    directInterfaces.add(dependency.getInterface());
                }
            } else if (dependency.isSet()) {
                setProviderInterfaces.add(dependency.getInterface());
            } else {
                providerInterfaces.add(dependency.getInterface());
            }
        }
        if (!component.getPublishedEvents().isEmpty()) {
            directInterfaces.add(Publisher.class);
        }
        this.allowedDirectInterfaces = Collections.unmodifiableSet(directInterfaces);
        this.allowedProviderInterfaces = Collections.unmodifiableSet(providerInterfaces);
        this.allowedSetDirectInterfaces = Collections.unmodifiableSet(setDirectInterfaces);
        this.allowedSetProviderInterfaces = Collections.unmodifiableSet(setProviderInterfaces);
        this.allowedPublishedEvents = component.getPublishedEvents();
        this.delegateContainer = container;
    }

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public <T> T get(Class<T> anInterface) {
        if (this.allowedDirectInterfaces.contains(anInterface)) {
            T value = (T) this.delegateContainer.get(anInterface);
            if (!anInterface.equals(Publisher.class)) {
                return value;
            }
            T publisher = (T) new RestrictedPublisher(this.allowedPublishedEvents, (Publisher) value);
            return publisher;
        }
        throw new IllegalArgumentException(String.format("Attempting to request an undeclared dependency %s.", anInterface));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<T> getProvider(Class<T> anInterface) {
        if (this.allowedProviderInterfaces.contains(anInterface)) {
            return this.delegateContainer.getProvider(anInterface);
        }
        throw new IllegalArgumentException(String.format("Attempting to request an undeclared dependency Provider<%s>.", anInterface));
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<Set<T>> setOfProvider(Class<T> anInterface) {
        if (this.allowedSetProviderInterfaces.contains(anInterface)) {
            return this.delegateContainer.setOfProvider(anInterface);
        }
        throw new IllegalArgumentException(String.format("Attempting to request an undeclared dependency Provider<Set<%s>>.", anInterface));
    }

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public <T> Set<T> setOf(Class<T> anInterface) {
        if (this.allowedSetDirectInterfaces.contains(anInterface)) {
            return this.delegateContainer.setOf(anInterface);
        }
        throw new IllegalArgumentException(String.format("Attempting to request an undeclared dependency Set<%s>.", anInterface));
    }

    /* compiled from: com.google.firebase:firebase-components@@16.0.0 */
    /* loaded from: classes.dex */
    private static class RestrictedPublisher implements Publisher {
        private final Set<Class<?>> allowedPublishedEvents;
        private final Publisher delegate;

        public RestrictedPublisher(Set<Class<?>> allowedPublishedEvents, Publisher delegate) {
            this.allowedPublishedEvents = allowedPublishedEvents;
            this.delegate = delegate;
        }

        @Override // com.google.firebase.events.Publisher
        public void publish(Event<?> event) {
            if (this.allowedPublishedEvents.contains(event.getType())) {
                this.delegate.publish(event);
                return;
            }
            throw new IllegalArgumentException(String.format("Attempting to publish an undeclared event %s.", event));
        }
    }
}
