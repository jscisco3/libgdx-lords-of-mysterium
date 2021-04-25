package com.jscisco.lom.domain.entity;

import com.jscisco.lom.domain.Position;
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
            hero.glyph = this.glyph;
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

    @Override
    public double[][] calculateFieldOfView() {
        double[][] fov = super.calculateFieldOfView();
        for (int x = 0; x < fov.length; x++) {
            for (int y = 0 ; y < fov[x].length; y++) {
                if (fov[x][y] > 0) {
                    this.level.getTileAt(Position.of(x, y)).explore();
                }
            }
        }
        return fov;
    }
}
