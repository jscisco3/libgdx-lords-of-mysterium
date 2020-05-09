package com.jscisco.lom.shelf.application;

import com.jscisco.lom.shelf.domain.Event;
import com.jscisco.lom.shelf.domain.Observer;
import com.jscisco.lom.shelf.domain.Subject;

import java.util.HashSet;
import java.util.Set;

public class EventProcessor implements Subject {

    Set<Observer> observers;

    public EventProcessor() {
        this.observers = new HashSet<>();
    }

    @Override
    public void attach(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void detach(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObservers(Event event) {
        this.observers.forEach(o -> o.update(event));
    }
}
