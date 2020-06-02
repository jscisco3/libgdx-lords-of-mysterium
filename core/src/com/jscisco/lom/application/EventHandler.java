package com.jscisco.lom.application;

import com.jscisco.lom.domain.event.Event;
import com.jscisco.lom.domain.event.Listener;
import com.jscisco.lom.domain.event.Publisher;

import java.util.Set;

public class EventHandler implements Publisher {

    public Set<Listener> listeners;

    @Override
    public void register(Listener listener) {
        listeners.add(listener);
    }

    @Override
    public void unregister(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void publish(Event event) {
        listeners.forEach(l -> l.handle(event));
    }
}
