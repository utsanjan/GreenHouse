package com.google.firebase.components;

import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import com.google.firebase.inject.Provider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: com.google.firebase:firebase-components@@16.0.0 */
/* loaded from: classes.dex */
public class ComponentRuntime extends AbstractComponentContainer {
    private static final Provider<Set<Object>> EMPTY_PROVIDER;
    private final EventBus eventBus;
    private final Map<Component<?>, Lazy<?>> components = new HashMap();
    private final Map<Class<?>, Lazy<?>> lazyInstanceMap = new HashMap();
    private final Map<Class<?>, Lazy<Set<?>>> lazySetMap = new HashMap();

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public /* bridge */ /* synthetic */ Object get(Class cls) {
        return super.get(cls);
    }

    @Override // com.google.firebase.components.AbstractComponentContainer, com.google.firebase.components.ComponentContainer
    public /* bridge */ /* synthetic */ Set setOf(Class cls) {
        return super.setOf(cls);
    }

    static {
        Provider<Set<Object>> provider;
        provider = ComponentRuntime$$Lambda$5.instance;
        EMPTY_PROVIDER = provider;
    }

    public ComponentRuntime(Executor defaultEventExecutor, Iterable<ComponentRegistrar> registrars, Component<?>... additionalComponents) {
        this.eventBus = new EventBus(defaultEventExecutor);
        List<Component<?>> componentsToAdd = new ArrayList<>();
        componentsToAdd.add(Component.of(this.eventBus, EventBus.class, Subscriber.class, Publisher.class));
        for (ComponentRegistrar registrar : registrars) {
            componentsToAdd.addAll(registrar.getComponents());
        }
        for (Component<?> additionalComponent : additionalComponents) {
            if (additionalComponent != null) {
                componentsToAdd.add(additionalComponent);
            }
        }
        CycleDetector.detect(componentsToAdd);
        for (Component<?> component : componentsToAdd) {
            Lazy<?> lazy = new Lazy<>((Provider<?>) ComponentRuntime$$Lambda$1.lambdaFactory$(this, component));
            this.components.put(component, lazy);
        }
        processInstanceComponents();
        processSetComponents();
    }

    public static /* synthetic */ Object lambda$new$0(ComponentRuntime componentRuntime, Component component) {
        return component.getFactory().create(new RestrictedComponentContainer(component, componentRuntime));
    }

    private void processInstanceComponents() {
        for (Map.Entry<Component<?>, Lazy<?>> entry : this.components.entrySet()) {
            Component<?> component = entry.getKey();
            if (component.isValue()) {
                Lazy<?> lazy = entry.getValue();
                for (Class<?> anInterface : component.getProvidedInterfaces()) {
                    this.lazyInstanceMap.put(anInterface, lazy);
                }
            }
        }
        validateDependencies();
    }

    private void processSetComponents() {
        Map<Class<?>, Set<Lazy<?>>> setIndex = new HashMap<>();
        for (Map.Entry<Component<?>, Lazy<?>> entry : this.components.entrySet()) {
            Component<?> component = entry.getKey();
            if (!component.isValue()) {
                Lazy<?> lazy = entry.getValue();
                for (Class<?> anInterface : component.getProvidedInterfaces()) {
                    if (!setIndex.containsKey(anInterface)) {
                        setIndex.put(anInterface, new HashSet<>());
                    }
                    setIndex.get(anInterface).add(lazy);
                }
            }
        }
        for (Map.Entry<Class<?>, Set<Lazy<?>>> entry2 : setIndex.entrySet()) {
            Set<Lazy<?>> lazies = entry2.getValue();
            this.lazySetMap.put(entry2.getKey(), new Lazy<>(ComponentRuntime$$Lambda$4.lambdaFactory$(lazies)));
        }
    }

    public static /* synthetic */ Set lambda$processSetComponents$1(Set lazies) {
        Set<Object> set = new HashSet<>();
        Iterator it = lazies.iterator();
        while (it.hasNext()) {
            Lazy<?> lazy = (Lazy) it.next();
            set.add(lazy.get());
        }
        return Collections.unmodifiableSet(set);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<T> getProvider(Class<T> anInterface) {
        Preconditions.checkNotNull(anInterface, "Null interface requested.");
        return this.lazyInstanceMap.get(anInterface);
    }

    @Override // com.google.firebase.components.ComponentContainer
    public <T> Provider<Set<T>> setOfProvider(Class<T> anInterface) {
        Lazy<Set<?>> lazy = this.lazySetMap.get(anInterface);
        if (lazy != null) {
            return lazy;
        }
        return (Provider<Set<T>>) EMPTY_PROVIDER;
    }

    public void initializeEagerComponents(boolean isDefaultApp) {
        for (Map.Entry<Component<?>, Lazy<?>> entry : this.components.entrySet()) {
            Component<?> component = entry.getKey();
            Lazy<?> lazy = entry.getValue();
            if (component.isAlwaysEager() || (component.isEagerInDefaultApp() && isDefaultApp)) {
                lazy.get();
            }
        }
        this.eventBus.enablePublishingAndFlushPending();
    }

    private void validateDependencies() {
        for (Component<?> component : this.components.keySet()) {
            for (Dependency dependency : component.getDependencies()) {
                if (dependency.isRequired() && !this.lazyInstanceMap.containsKey(dependency.getInterface())) {
                    throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component, dependency.getInterface()));
                }
            }
        }
    }
}
