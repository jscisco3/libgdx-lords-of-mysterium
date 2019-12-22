package com.jscisco.lom.entity;

import com.badlogic.gdx.ai.btree.BehaviorTree;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.action.MoveAction;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class NPC extends Entity {

    private static final Logger logger = LoggerFactory.getLogger(NPC.class);

    private transient BehaviorTree<NPC> behaviorTree;
    private Map<String, Object> knowledge = new HashMap<>();


    public static class Builder {
        private EntityName name;

        private Stage stage;
        private FieldOfView fieldOfView;
        private Position position;
        private Health health;
        private Energy energy;
        private Inventory inventory;
        private Equipment equipment;
        private Assets.Glyphs glyph;
        private Statistics statistics;
        private Job job;
        private DeathStrategy deathStrategy;

        private BehaviorTree<NPC> behaviorTree;


        public Builder(EntityName name) {
            this.name = name;
        }

        public Builder withStage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public Builder withFieldOfView(FieldOfView fov) {
            this.fieldOfView = fov;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withHealth(Health health) {
            this.health = health;
            return this;
        }

        public Builder withEnergy(Energy energy) {
            this.energy = energy;
            return this;
        }

        public Builder withInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder withEquipment(Equipment equipment) {
            this.equipment = equipment;
            return this;
        }

        public Builder withGlyph(Assets.Glyphs glyph) {
            this.glyph = glyph;
            return this;
        }

        public Builder withStats(Statistics statistics) {
            this.statistics = statistics;
            return this;
        }

        public Builder withJob(Job job) {
            this.job = job;
            return this;
        }

        public Builder withBehaviorTree(BehaviorTree<NPC> behaviorTree) {
            this.behaviorTree = behaviorTree;
            return this;
        }

        public Builder withDeathStrategy(DeathStrategy strategy) {
            this.deathStrategy = strategy;
            return this;
        }

        public NPC build() {
            NPC npc = new NPC();
            npc.name = name;
            npc.stage = stage;
            if (stage != null) {
                this.stage.addEntity(npc);
            }
            npc.fieldOfView = fieldOfView;
            npc.position = position;
            npc.health = health;
            npc.energy = energy;
            npc.inventory = inventory;
            npc.equipment = equipment;
            npc.glyph = glyph;
            npc.job = job;
            npc.statistics = statistics;
            npc.behaviorTree = behaviorTree;
            if (npc.behaviorTree != null) {
                npc.behaviorTree.setObject(npc);
            }
            if (deathStrategy != null) {
                npc.deathStrategy = deathStrategy;
            } else {
                npc.deathStrategy = new RegularDeath();
            }
            return npc;
        }
    }

    private NPC() {

    }

    @Override
    public Action getNextAction() {
        List<Position> possibleMoves = new ArrayList<>();
        possibleMoves.add(new Position(-1, 1));
        possibleMoves.add(new Position(-1, 0));
        possibleMoves.add(new Position(-1, -1));
        possibleMoves.add(new Position(0, -1));
        possibleMoves.add(new Position(0, 1));
        possibleMoves.add(new Position(1, 1));
        possibleMoves.add(new Position(1, 0));
        possibleMoves.add(new Position(1, -1));
        Position nextMove = possibleMoves.get(ThreadLocalRandom.current().nextInt(0, possibleMoves.size()));
        this.nextAction = new MoveAction(this, nextMove.getX(), nextMove.getY());
        return super.getNextAction();
    }

    public Map<String, Object> getKnowledge() {
        return knowledge;
    }

    public void learn(String topic, Object value) {
        this.knowledge.put(topic, value);
    }

    public BehaviorTree<NPC> getBehaviorTree() {
        return behaviorTree;
    }

    public void setBehaviorTree(BehaviorTree<NPC> behaviorTree) {
        this.behaviorTree = behaviorTree;
    }
}
