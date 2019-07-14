package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.*;
import com.jscisco.lom.attic.goap.actions.GOAPGoal;
import com.jscisco.lom.terrain.Floor;
import com.jscisco.lom.terrain.Terrain;
import com.jscisco.lom.terrain.Wall;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import squidpony.squidai.DijkstraMap;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {

    protected Stage stage;
    protected Action nextAction;
    protected FieldOfView fieldOfView;
    protected Position position;
    protected Health health;
    protected Energy energy;
    protected Inventory inventory;
    protected Equipment equipment;
    protected TextureRegion texture;
    protected DijkstraMap pathingMap;

    protected Map<GOAPGoal, Object> worldState;
    protected Map<GOAPGoal, Object> goals;

    protected Map<String, Object> knowledge;

    public Entity(Stage stage) {
        this.stage = stage;
        this.pathingMap = new DijkstraMap();
        this.updatePathingMap();
        // GOAP
        this.worldState = new HashMap<>();
        this.goals = new HashMap<>();
        // BTree
        this.knowledge = new HashMap<>();
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

    public Inventory getInventory() {
        return inventory;
    }

    public Equipment getEquipment() {
        return equipment;
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

    public DijkstraMap getPathingMap() {
        return pathingMap;
    }

    public void updatePathingMap() {
        if (stage != null) {
            double[][] costs = new double[stage.getWidth()][stage.getHeight()];
            for (int x = 0; x < stage.getWidth(); x++) {
                for (int y = 0; y < stage.getHeight(); y++) {
                    Terrain t = stage.getTerrainAtPosition(x, y);
                    if (t.getClass() == Floor.class) {
                        costs[x][y] = DijkstraMap.FLOOR;
                    }
                    if (t.getClass() == Wall.class) {
                        costs[x][y] = DijkstraMap.WALL;
                    }
                }
            }
            this.pathingMap.initialize(costs);
        }
    }

    public Map<GOAPGoal, Object> getWorldState() {
        return worldState;
    }

    public Map<String, Object> getKnowledge() {
        return knowledge;
    }

    public void learn(String topic, Object value) {
        this.knowledge.put(topic, value);
    }

    public void updateWorldState(GOAPGoal goal, Object value) {
        this.worldState.put(goal, value);
    }

    public Map<GOAPGoal, Object> getGoals() {
        return goals;
    }

    public void setGoal(GOAPGoal goal, Object value) {
        this.goals.put(goal, value);
    }
}
