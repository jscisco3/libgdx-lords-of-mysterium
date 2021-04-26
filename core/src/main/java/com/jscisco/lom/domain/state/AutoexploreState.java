package com.jscisco.lom.domain.state;

import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.zone.Level;

import java.util.Set;

public class AutoexploreState extends State {

    final Level level;

    public AutoexploreState(Hero hero) {
        super(hero);
        this.level = hero.getLevel();
    }

    @Override
    public Action getNextAction() {
        return new WalkAction(hero, Direction.N);
    }

    @Override
    public void handleInput(Set<Integer> input) {
        this.hero.setState(new DefaultState(hero));
        input.clear();
    }

    @Override
    public String toString() {
        return "AutoexploreState";
    }
}
