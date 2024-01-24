package com.jscisco.lom.domain.state;

import com.badlogic.gdx.Input;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.action.PickUpItemAction;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class DefaultState extends State {
    private final Logger logger = LoggerFactory.getLogger(DefaultState.class);

    public DefaultState(Hero hero) {
        super(hero);
    }

    @Override
    public void handleInput(Set<Integer> input) {
        logger.trace("Handling input..." + input);
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
        if (input.contains(Input.Keys.G) || input.contains(Input.Keys.COMMA)) {
            List<Item> items = hero.getLevel().getItemsAtPosition(hero.getPosition());
            logger.info("We have {} items to choose from", items.size());
            try {
                Item item = hero.getLevel().getItemsAtPosition(hero.getPosition()).getFirst();
                this.action = new PickUpItemAction(hero, item);
            } catch (NoSuchElementException e) {
                logger.info("No items to pick up");
                this.action = null;
            }
        }
        // if (input.contains(Input.Keys.PERIOD) && (input.contains(Input.Keys.SHIFT_LEFT) ||
        // input.contains(Input.Keys.SHIFT_RIGHT))) {
        if (input.contains(Input.Keys.PERIOD)) {
//            this.action = new ChangeLevelAction(hero);
        }
        if (input.contains(Input.Keys.Z)) {
            hero.setState(new AutoexploreState(hero));
            input.clear();
        }
        if (input.contains(Input.Keys.BACKSPACE)) {
            // hero.getAttributes().applyBaseValueModifier(new AttributeModifier()
            // .withOperator(Attribute.Operator.ADD)
            // .withMagnitude(-10)
            // .forAttribute(AttributeSet.AttributeDefinition.HEALTH));
        }
    }

    @Override
    public String toString() {
        return "DefaultState";
    }
}
