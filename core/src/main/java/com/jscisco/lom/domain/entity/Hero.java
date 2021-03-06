package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.action.Action;

public class Hero extends Entity {

    private Hero() {
        super();
    }

    public static class Builder extends Entity.Builder<Builder> {

        public Hero build() {
            Hero hero = new Hero();
            hero.name = this.name;
            hero.position = this.position;
            hero.asset = this.asset;
            return hero;
        }
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public Action nextAction() {
        Action c = this.action;
        this.action = null;
        return c;
    }
}
