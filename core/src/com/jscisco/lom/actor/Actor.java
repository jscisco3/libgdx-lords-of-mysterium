package com.jscisco.lom.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.attributes.Energy;
import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.commands.Command;
import com.jscisco.lom.util.Position3D;

public abstract class Actor {

    protected Command nextCommand;
    protected FieldOfView fieldOfView;
    protected Position3D position;
    protected Health health;
    protected Energy energy;
    protected TextureRegion texture;

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

    public Energy getEnergy() {
        return energy;
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
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

    public TextureRegion getTexture() {
        return texture;
    }

    public Command getNextCommand() {
        Command command = nextCommand;
        // Only do this action once!
        nextCommand = null;
        return command;
    }

    public void setNextCommand(Command nextCommand) {
        this.nextCommand = nextCommand;
    }
}
