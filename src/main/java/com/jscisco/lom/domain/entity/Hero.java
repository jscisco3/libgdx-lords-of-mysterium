package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Position;
import com.jscisco.lom.domain.action.Action;
import com.jscisco.lom.domain.state.DefaultState;
import com.jscisco.lom.domain.state.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class Hero extends Entity {

    private static final Logger logger = LoggerFactory.getLogger(Hero.class);

    private State state;

    public Hero() {
        super();
        this.state = new DefaultState(this);
    }

    public static class Builder extends Entity.Builder<Builder> {

        public Hero build() {
            Hero hero = new Hero();
            hero.name = this.name;
            hero.position = this.position;
            hero.glyph = this.glyph;
            hero.pools = pools;
            hero.setInventory(new Inventory());
            return hero;
        }
    }

    // TODO: Remove
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Action nextAction() {
        return state.getNextAction();
    }

    @Override
    public double[][] calculateFieldOfView() {
        logger.debug("Calculating hero FOV");
        double[][] fov = fieldOfView.calculateFOV();
        for (int x = 0; x < fov.length; x++) {
            for (int y = 0; y < fov[x].length; y++) {
                if (fov[x][y] > 0) {
                    this.level.getTile(Position.of(x, y)).explore();
                }
            }
        }
        return fov;
    }

    public State getState() {
        return this.state;
    }

    public void handleInput(Set<Integer> input) {
        state.handleInput(input);
    }

    public void setState(State state) {
        this.state = state;
    }

}
