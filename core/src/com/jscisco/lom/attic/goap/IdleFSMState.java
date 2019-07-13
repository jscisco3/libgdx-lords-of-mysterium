package com.jscisco.lom.attic.goap;

import com.jscisco.lom.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdleFSMState extends AbstractFSMState {

    private static final Logger logger = LoggerFactory.getLogger(IdleFSMState.class);

    public IdleFSMState() {
    }

    @Override
    public void update(FSM fsm, Entity entity) {
//        GOAPAction action = currentActions.peek();
//        if (action.getTarget() == null) {
//            logger.debug("{} requires a target, but has none. Plan failed.", action);
//            fsm.pop(); // Move
//            fsm.pop(); // perform
//            fsm.push(idleState);
//            return;
//        }
//        // Get the agent to move itself
//        Position direction = calculateDirectionToWalk(action.getTarget());
//        entity.setNextAction(new MoveAction(entity, direction));
    }
}
