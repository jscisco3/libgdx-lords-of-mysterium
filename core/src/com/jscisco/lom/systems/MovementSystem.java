package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.InitiativeComponent;
import com.jscisco.lom.components.MovementComponent;
import com.jscisco.lom.components.PositionComponent;

/**
 * Responsible for moving an entity.
 * - Adds the specified movement direction to the entity's position
 * - Removes the movement component
 */
public class MovementSystem extends IteratingSystem {

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<MovementComponent> mMovement;
    private ComponentMapper<InitiativeComponent> mInitiative;

    public MovementSystem() {
        super(Aspect.all(PositionComponent.class, MovementComponent.class));
    }

    @Override
    protected void process(int entityId) {
        PositionComponent positionComponent = mPosition.get(entityId);
        MovementComponent movementComponent = mMovement.get(entityId);
        positionComponent.position = positionComponent.position.add(movementComponent.direction);
        mMovement.remove(entityId);
        if (mInitiative.has(entityId)) {
            mInitiative.get(entityId).initiative = 100;
        }
    }
}
