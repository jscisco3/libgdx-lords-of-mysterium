package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.RestAction;
import com.jscisco.lom.domain.action.WalkAction;

import java.util.Random;

public class WanderAIController extends AIController {

    private final Random random = new Random();

    @Override
    public Action getNextAction(Entity entity) {
        // Random number 1-9
        // 1-8: Random direction and walk there
        // 9: Rest
        int choice = random.nextInt(9);
        if (choice < 8) {
            return new WalkAction(entity, Direction.values()[choice]);
        }
        return new RestAction(entity);
    }
}
