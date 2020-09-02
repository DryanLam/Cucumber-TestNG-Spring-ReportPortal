package com.sample.dl.contexts.scopes;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

class TestScope implements Scope {

    private Map<String, Object> cache = Collections.synchronizedMap(new HashMap<String, Object>());
    private Map<String, Runnable> destructionCache = Collections.synchronizedMap(new HashMap<String, Runnable>());


    // Thread-safe
    void reset() {
        cache.clear();
    }

    @Override
    Object get(String name, ObjectFactory<?> objectFactory) {
        if (!cache.containsKey(name)) {
            cache.put(name, objectFactory.getObject());
        }
        return cache.get(name);
    }

    @Override
    Object remove(String name) {
        return cache.remove(name);
    }

    @Override
    void registerDestructionCallback(String name, Runnable callback) {
        destructionCache.put(name, callback);
    }

    @Override
    Object resolveContextualObject(String name) {
        destructionCache.remove(name);
        return cache.remove(name);
    }

    @Override
    String getConversationId() {
        return null;
    }
}
