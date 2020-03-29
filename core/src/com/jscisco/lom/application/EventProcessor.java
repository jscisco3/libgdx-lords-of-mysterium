package com.jscisco.lom.application;

import com.jscisco.lom.domain.Event;

import java.util.Deque;

public class EventProcessor {
    Deque<Event> eventQueue;

    public EventProcessor(Deque<Event> eventQueue) {
        this.eventQueue = eventQueue;
    }

    public boolean hasEvents() {
        return !eventQueue.isEmpty();
    }

    public void enqueue(Event event) {
        this.eventQueue.addLast(event);
    }

    public void process() {
        while (!eventQueue.isEmpty()) {
            Event event = eventQueue.removeFirst();
            event.process();
        }
    }
}
