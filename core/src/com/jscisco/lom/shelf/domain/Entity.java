package com.jscisco.lom.shelf.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class Entity implements Observer {

    private static final Logger logger = LoggerFactory.getLogger(Entity.class);

    EntityId id;
    EntityName name;
    Position position;

    // Statistics
    Health health;
    Action nextAction;

    // The stage this entity is in.
    Stage stage;

    Map<EventType, List<EventHandler>> eventHandlers;

    public Entity(EntityId id, EntityName name, Position position) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        this.id = id;
        this.name = name;
        this.position = position;
        this.eventHandlers = new HashMap<>();
    }

    public Entity withHealth(Health health) {
        this.health = health;
        return this;
    }

    public List<Event> damage(int amount) {
        List<Event> events = new ArrayList<>();
        if (this.health == null) {
            throw new IllegalStateException(String.format("Entity %s does not have a Health component.", id.id()));
        }
        this.health.reduce(amount);
        if (this.health.shouldBeDestroyed()) {
            events.add(new EntityDiedEvent(this));
        }
        return events;
    }

    public List<Event> move(Position newPosition) {
        this.position = newPosition;
        List<Event> events = new ArrayList<>();
        events.add(new EntityMovedEvent(this, newPosition));
        return events;
    }

    public Action nextAction() {
        Action action = nextAction;
        // Only handle an action once!
        nextAction = null;
        return action;
    }

    public Position position() {
        return this.position;
    }

    public void nextAction(Action action) {
        this.nextAction = action;
    }

    public void registerHandler(EventType eventsToHandle, EventHandler handler) {
        if (this.eventHandlers.get(eventsToHandle) == null) {
            List<EventHandler> handlers = new ArrayList<>();
            handlers.add(handler);
            this.eventHandlers.put(eventsToHandle, handlers);
        } else {

            this.eventHandlers.get(eventsToHandle).add(handler);
        }
    }

    @Override
    public void update(Event event) {
        Optional.ofNullable(this.eventHandlers.get(event.type)).ifPresent(handler -> handler.forEach(h -> h.handle(event)));
    }
}
