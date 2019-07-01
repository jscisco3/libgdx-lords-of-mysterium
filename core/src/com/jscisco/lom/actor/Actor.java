package com.jscisco.lom.actor;

import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.attributes.Initiative;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;

public abstract class Actor implements Comparable<Actor> {

    protected FieldOfView fieldOfView;
    protected Position3D position;
    protected Health health;
    protected Initiative initiative;

    public Position3D getPosition() {
        return position;
    }

    public void setPosition(Position3D position) {
        this.position = position;
    }

    public FieldOfView getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(FieldOfView fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public Health getHealth() {
        return health;
    }

    public void setHealth(Health health) {
        this.health = health;
    }

    public Initiative getInitiative() {
        return initiative;
    }

    public void setInitiative(Initiative initiative) {
        this.initiative = initiative;
    }

    @Override
    public int compareTo(Actor o) {
        return Integer.compare(this.getInitiative().getCooldown(), o.getInitiative().getCooldown());
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    public int getZ() {
        return position.getZ();
    }

    public Command takeTurn() {
        return null;
    }
}
