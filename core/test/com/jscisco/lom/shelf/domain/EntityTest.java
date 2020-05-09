package com.jscisco.lom.shelf.domain;

import com.jscisco.lom.test.EntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.times;

public class EntityTest {

    private static final Logger logger = LoggerFactory.getLogger(EntityTest.class);

    /**
     * Given an entity
     * When it moves
     * Then it should be in the new position
     */
    @Test
    public void testMovementShouldPutEntityInNewPosition() {
        Entity entity = new TestEntity(new EntityId(12345L), new EntityName("Test Name"));
        entity.move(new Position(1, 1));
        Assertions.assertThat(entity.position).isEqualTo(new Position(1, 1));
    }

    /**
     * Given an entity with an event handler
     * When the appropriate event is raised
     * Then it should be handled.
     */
    @Test
    public void testEntityWithHandlerHandlesAnEvent() {
        Entity e = EntityFactory.testEntity();
        e.registerHandler(EventType.ENTITY_MOVED_EVENT, new EventHandler() {
            @Override
            public void handle(Event event) {
                logger.info("handling...");
                e.position = new Position(10, 10);
            }
        });

        Subject subject = new SubjectImpl();
        subject.attach(e);
        subject.notifyObservers(new EntityMovedEvent(e, new Position(1, 1)) {
        });

        Assertions.assertThat(e.position).isEqualTo(new Position(10, 10));
    }

    /**
     * Given an entity
     * That has multiple event handlers
     * Only the appropriate handler should be called for a type of event
     */
    @Test
    public void multipleDistinctEventHandlersAreNotCalledForSameEventType() {
        Entity e = EntityFactory.testEntity();
        EventHandler handler1 = Mockito.mock(EventHandler.class);
        EventHandler handler2 = Mockito.mock(EventHandler.class);

        e.registerHandler(EventType.ENTITY_MOVED_EVENT, handler1);
        e.registerHandler(EventType.ENTITY_DIED_EVENT, handler2);

        Subject subject = new SubjectImpl();
        subject.attach(e);
        subject.notifyObservers(new EntityMovedEvent(e, new Position(0, 0)));
        Mockito.verify(handler1, times(1)).handle(Mockito.any());
        Mockito.verify(handler2, times(0)).handle(Mockito.any());
    }

    /**
     * Given an Entity
     * That has multiple event handlers each of which handle the same event
     * They should all act when notified of a particular event.
     */
    // TODO
    @Test
    public void multipleEventHandlersSameTypeCalledForEventOfThatType() {
        Entity e = EntityFactory.testEntity();
        EventHandler handler1 = Mockito.mock(EventHandler.class);
        EventHandler handler2 = Mockito.mock(EventHandler.class);

        e.registerHandler(EventType.ENTITY_MOVED_EVENT, handler1);
        e.registerHandler(EventType.ENTITY_MOVED_EVENT, handler2);

        Subject subject = new SubjectImpl();
        subject.attach(e);
        subject.notifyObservers(new EntityMovedEvent(e, new Position(0, 0)));
        Mockito.verify(handler1, times(1)).handle(Mockito.any());
        Mockito.verify(handler2, times(1)).handle(Mockito.any());
    }

    private class SubjectImpl implements Subject {
        List<Observer> observers = new ArrayList<>();

        @Override
        public void attach(Observer o) {
            observers.add(o);
        }

        @Override
        public void detach(Observer o) {
            observers.remove(o);
        }

        @Override
        public void notifyObservers(Event event) {
            observers.forEach(o -> o.update(event));
        }
    }
}
