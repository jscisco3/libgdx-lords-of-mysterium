package com.jscisco.lom.domain;

import com.jscisco.lom.domain.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class Subject {

    private static final Logger logger = LoggerFactory.getLogger(Subject.class);

    private Set<Observer> observers;

    public Subject() {
        this.observers = new HashSet<>();
    }

    public void notify(Event event) {
        observers.forEach(o -> o.onNotify(event));
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    public void unregister(Observer observer) {
        observers.remove(observer);
    }

}
