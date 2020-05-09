package com.jscisco.lom.shelf.domain;

public abstract class Event {
    EventType type;

    public Event(EventType type) {
        this.type = type;
    }

}
