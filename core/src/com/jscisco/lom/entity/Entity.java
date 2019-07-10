package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.Energy;
import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;

public abstract class Entity {

    protected Stage stage;
    protected Action nextAction;
    protected FieldOfView fieldOfView;
    protected Position position;
    protected Health health;
    protected Energy energy;
    protected TextureRegion texture;

    public Entity(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
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

    public TextureRegion getTexture() {
        return texture;
    }

    public Action getNextAction() {
        Action action = nextAction;
        // Only do this action once!
        nextAction = null;
        return action;
    }

    public void setNextAction(Action nextAction) {
        this.nextAction = nextAction;
    }
}
