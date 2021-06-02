package com.jscisco.lom.domain.state;

import com.badlogic.gdx.Input;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.action.ChangeLevelAction;
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
        }
        if (input.contains(Input.Keys.DOWN)) {
            this.action = new WalkAction(hero, Direction.S);
        }
        if (input.contains(Input.Keys.LEFT)) {
            this.action = new WalkAction(hero, Direction.W);
        }
        if (input.contains(Input.Keys.RIGHT)) {
            this.action = new WalkAction(hero, Direction.E);
        }
//        if (input.contains(Input.Keys.PERIOD) && (input.contains(Input.Keys.SHIFT_LEFT) || input.contains(Input.Keys.SHIFT_RIGHT))) {
        if (input.contains(Input.Keys.PERIOD)) {
            this.action = new ChangeLevelAction(hero);
        }
        if (input.contains(Input.Keys.Z)) {
            hero.setState(new AutoexploreState(hero));
            input.clear();
        }
        if (input.contains(Input.Keys.BACKSPACE)) {
//            hero.getAttributes().applyBaseValueModifier(new AttributeModifier()
//                    .withOperator(Attribute.Operator.ADD)
//                    .withMagnitude(-10)
//                    .forAttribute(AttributeSet.AttributeDefinition.HEALTH));
        }
    }

    @Override
    public String toString() {
        return "DefaultState";
    }
}
