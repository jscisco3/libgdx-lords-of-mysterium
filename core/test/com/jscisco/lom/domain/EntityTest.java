package com.jscisco.lom.domain;

import com.jscisco.lom.test.EntityFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class EntityTest {

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
        e.registerHandler(EntityMovedEvent.class, new EventHandler() {
            @Override
            public void handle(Event event) {
                e.position = new Position(10, 10);
            }
        });

        Subject subject = new SubjectImpl();
        subject.attach(e);
        subject.notifyObservers(new Event() {
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
        e.registerHandler(EntityMovedEvent.class, new EventHandler() {
            @Override
            public void handle(Event event) {
                e.position = new Position(10, 10);
            }
        });
        // Maybe these event handler should be mocks, since I want to verify their handle method was called and that is
        // it.
        e.registerHandler(EntityDiedEvent.class, new EventHandler() {
            @Override
            public void handle(Event event) {
                e.position = new Position(20, 20);
            }
        });
        Subject subject = new SubjectImpl();
        subject.attach(e);
        subject.notifyObservers(new EntityMovedEvent(e, new Position(0, 0)));

        Assertions.assertThat(e.position).isEqualTo(new Position(10, 10));
    }

    /**
     * Given an Entity
     * That has multiple event handlers each of which handle the same event
     * They should all act when notified of a particular event.
     */
    // TODO
    @Test
    public void multipleEventHandlersSameTypeCalledForEventOfThatType() {

    }

    private class SubjectImpl implements Subject {
        Observer o = null;

        @Override
        public void attach(Observer o) {
            this.o = o;
        }

        @Override
        public void detach(Observer o) {
            this.o = null;
        }

        @Override
        public void notifyObservers(Event event) {
            this.o.update(event);
        }
    }
}
