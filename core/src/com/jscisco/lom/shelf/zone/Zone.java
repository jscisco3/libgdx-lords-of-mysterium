package com.jscisco.lom.shelf.zone;

import com.jscisco.lom.shelf.domain.Position;
import com.jscisco.lom.shelf.entity.*;
import com.jscisco.lom.shelf.items.Item;
import com.jscisco.lom.shelf.items.ItemFactory;
import com.jscisco.lom.shelf.domain.Size3D;
import com.jscisco.lom.shelf.zone.strategies.GenericStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Zone {

    private static final Logger logger = LoggerFactory.getLogger(Zone.class);

    private Size3D size;
    private List<Stage> stages;

    private int currentStageIndex;

    public Zone(Size3D size, Player player) {
        this.size = size;
        this.stages = new ArrayList<>();

        for (int z = 0; z < size.getDepth(); z++) {
            // Top of dungeon, so only stairs down
            if (z == 0) {
                this.stages.add(new Stage(size.getWidth(), size.getHeight(), false, true, new GenericStrategy()));
            } else if (z == size.getDepth() - 1) {
                // Bottom of dungeon, so only stairs up
                this.stages.add(new Stage(size.getWidth(), size.getHeight(), true, false, new GenericStrategy()));
            } else {
                // Otherwise, both stairs
                this.stages.add(new Stage(size.getWidth(), size.getHeight(), true, true, new GenericStrategy()));
            }
//            this.stages.add(new StageImpl(size.getWidth(), size.getHeight(), z, new GenericStrategy()));
//            this.stages.add(new StageImpl(size.getWidth(), size.getHeight(), new EmptyStageGenerationStrategy()));
        }
        this.currentStageIndex = 0;

        if (player == null) {
            player = PlayerFactory.createRandomHero();
            player.setStage(this.getCurrentStage());
            if (this.getCurrentStage().getTileAt(Position.get(1, 1)).getTerrain().isWalkable()) {
                player.setPosition(Position.get(1, 1));
            } else {
                this.getCurrentStage().findEmptyPosition().ifPresent(player::setPosition);
            }
        } else {
            player.setStage(this.getCurrentStage());
            if (this.getCurrentStage().getTileAt(Position.get(1, 1)).getTerrain().isWalkable()) {
                player.setPosition(Position.get(1, 1));
            } else {
                this.getCurrentStage().findEmptyPosition().ifPresent(player::setPosition);
            }
        }
        this.getCurrentStage().addEntity(player);

        for (int z = 0; z < this.size.getDepth(); z++) {
            for (int n = 0; n < 10; n++) {
                Stage stage = this.stages.get(z);
                Position pos = stage.findEmptyPosition().get();
                NPC npc;
                if (n % 2 == 0) {
                    npc = new NPC(NPCRepository.npcs.get("Rat"));
                } else {
                    npc = new NPC(NPCRepository.npcs.get("Snuugz"));
                }
                npc.setPosition(pos);
                npc.setStage(stage);
                stage.addEntity(npc);
            }
        }

        List<Item> createdItems = new ArrayList<>();
        createdItems.add(ItemFactory.buildSword());
        createdItems.add(ItemFactory.buildSword());
        createdItems.add(ItemFactory.buildSword());
        createdItems.add(ItemFactory.buildSword());
        createdItems.add(ItemFactory.buildSword());

        for (Item item : createdItems) {
            logger.info(item.toString());
            this.getCurrentStage().findEmptyPosition().ifPresent(item::setPosition);
            this.getCurrentStage().addItem(item);
        }
    }

    public int getHeight() {
        return size.getHeight();
    }

    public int getWidth() {
        return size.getWidth();
    }

    public Stage getCurrentStage() {
        return this.stages.get(currentStageIndex);
    }

    public List<Stage> getStages() {
        return stages;
    }

    public void incrementStage(Entity entity) {
        assert this.currentStageIndex + 1 < this.stages.size();
        this.getCurrentStage().removeEntity(entity);
        this.currentStageIndex += 1;
        this.getCurrentStage().addEntity(entity);
    }

    public void decrementStage(Entity entity) {
        assert this.currentStageIndex - 1 >= 0;
        this.getCurrentStage().removeEntity(entity);
        this.currentStageIndex -= 1;
        this.getCurrentStage().addEntity(entity);
    }
}
