package com.jscisco.lom.systems;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.jscisco.lom.components.MovementComponent;
import com.jscisco.lom.components.PositionComponent;
import com.jscisco.lom.util.Position3D;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMovementSystem {

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<MovementComponent> mMovement;
    World world;

    @BeforeEach
    public void init() {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new MovementSystem())
                .build();
        world = new World(config);
        world.inject(this);
    }

    @Test
    public void test_movement_system_moves_entity() {

        int e = world.create();
        PositionComponent positionComponent = mPosition.create(e);
        positionComponent.position = new Position3D(0, 0, 0);
        Assertions.assertThat(positionComponent.position.getX()).isEqualTo(0);

        MovementComponent movementComponent = mMovement.create(e);
        movementComponent.direction = new Position3D(1, 0, 0);

        world.process();
        Assertions.assertThat(mPosition.get(e).position.getX()).isEqualTo(1);
    }

    @Test
    public void test_movement_system_removes_movement_component() {
        int e = world.create();
        PositionComponent positionComponent = mPosition.create(e);
        positionComponent.position = new Position3D(0, 0, 0);
        Assertions.assertThat(positionComponent.position.getX()).isEqualTo(0);

        MovementComponent movementComponent = mMovement.create(e);
        movementComponent.direction = new Position3D(1, 0, 0);

        world.process();
        Assertions.assertThat(mMovement.has(e)).isFalse();
    }

}
