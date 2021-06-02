package com.jscisco.lom.domain.state;

import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.entity.AIController;
import com.jscisco.lom.domain.entity.Hero;

import java.util.Set;

public class AIState extends State {

    AIController controller;

    public AIState(Hero hero) {
        super(hero);
    }

    public void setController(AIController controller) {
        this.controller = controller;
    }

    @Override
    public void handleInput(Set<Integer> input) {

    }

    @Override
    public Action getNextAction() {
        return controller.getNextAction();
    }
}
