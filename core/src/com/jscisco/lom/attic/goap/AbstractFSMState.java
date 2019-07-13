package com.jscisco.lom.attic.goap;

import com.jscisco.lom.entity.Entity;

public abstract class AbstractFSMState implements FSMState {

    @Override
    public abstract void update(FSM fsm, Entity entity);

}
