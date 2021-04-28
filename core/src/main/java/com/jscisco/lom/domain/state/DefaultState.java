package com.jscisco.lom.domain.state;

import com.badlogic.gdx.Input;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.Hero;

import java.util.Set;

public class DefaultState extends State {

    public DefaultState(Hero hero) {
        super(hero);
    }

    @Override
    public void handleInput(Set<Integer> input) {
        if (input.contains(Input.Keys.UP)) {
            this.action = new WalkAction(hero, Direction.N);
//            hero.setAction(new WalkAction(hero, Direction.N));
        }
        if (input.contains(Input.Keys.DOWN)) {
            this.action = new WalkAction(hero, Direction.S);

//            hero.setAction(new WalkAction(hero, Direction.S));
        }
        if (input.contains(Input.Keys.LEFT)) {
            this.action = new WalkAction(hero, Direction.W);
//            hero.setAction(new WalkAction(hero, Direction.W));
        }
        if (input.contains(Input.Keys.RIGHT)) {
            this.action = new WalkAction(hero, Direction.E);
//            hero.setAction(new WalkAction(hero, Direction.E));
        }
        if (input.contains(Input.Keys.Z)) {
            hero.setState(new AutoexploreState(hero));
            input.clear();
        }
    }

    @Override
    public String toString() {
        return "DefaultState";
    }
}
