package com.jscisco.lom.domain;

public abstract class Event {
    EventType type;

    public Event(EventType type) {
        this.type = type;
    }

}
