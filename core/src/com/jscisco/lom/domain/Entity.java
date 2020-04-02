package com.jscisco.lom.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity implements Observer {

    EntityId id;
    EntityName name;
    Position position;

    // Statistics
    Health health;

    Action nextAction;

    List<EventHandler> eventHandlers;

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
        this.eventHandlers = new ArrayList<>();
    }

    public Entity withHealth(Health health) {
        this.health = health;
        return this;
    }

    public void damage(int amount) {
        if (this.health == null) {
            throw new IllegalStateException(String.format("Entity %s does not have a Health component.", id.id()));
        }
        this.health.reduce(amount);
        if (this.health.shouldBeDestroyed()) {
            // TODO
        }
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

    public void registerHandler(EventHandler handler) {
        this.eventHandlers.add(handler);
    }

    @Override
    public void update(Event event) {
        for (EventHandler handler : this.eventHandlers) {
            handler.handle(event);
        }
    }
}
