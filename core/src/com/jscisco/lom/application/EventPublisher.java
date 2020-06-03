package com.jscisco.lom.application;

import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.Listener;
import com.jscisco.lom.domain.event.Publisher;

import java.util.HashMap;
import java.util.Map;

public class EventPublisher implements Publisher {

    public Map<Class, Listener> listeners = new HashMap<>();

    public EventPublisher() {
    }

    @Override
    public void register(Listener listener) {
        listeners.put(listener.getClass(), listener);
    }

    @Override
    public void unregister(Listener listener) {
        listeners.remove(listener.getClass());
    }

    @Override
    public void publish(Event event) {
        listeners.values().forEach(l -> l.handle(event));
    }

    public Map<Class, Listener> getListeners() {
        return listeners;
    }
}
