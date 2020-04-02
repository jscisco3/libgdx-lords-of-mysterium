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
        e.registerHandler(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                e.position = new Position(10, 10);
            }
        });

        Subject subject = new Subject() {
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
        };
        subject.attach(e);
        subject.notifyObservers(new Event() {
        });

        Assertions.assertThat(e.position).isEqualTo(new Position(10, 10));

    }
}
