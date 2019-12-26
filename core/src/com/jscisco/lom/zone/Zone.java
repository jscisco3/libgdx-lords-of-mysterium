package com.jscisco.lom.zone;

import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.entity.*;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemFactory;
import com.jscisco.lom.states.PlayerTurnState;
import com.jscisco.lom.states.State;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.util.Size3D;
import com.jscisco.lom.zone.strategies.EmptyStageGenerationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Zone {

    // TODO: Configuration file
    private float DEFAULT_TILE_WIDTH = 24.0f;
    private float DEFAULT_TILE_HEIGHT = 24.0f;

    private static final Logger logger = LoggerFactory.getLogger(Zone.class);

    private Size3D size;
    private List<Stage> stages;
    private Deque<State> states = new ArrayDeque<>();

    private int currentStageIndex;

    public Zone(Size3D size, Player player) {
        this.size = size;
        this.stages = new ArrayList<>();

        for (int z = 0; z < size.getDepth(); z++) {
            // Top of dungeon, so only stairs down
            if (z == 0) {
                this.stages.add(new StageImpl(size.getWidth(), size.getHeight(), false, true, new EmptyStageGenerationStrategy()));
            } else if (z == size.getDepth() - 1) {
                // Bottom of dungeon, so only stairs up
                this.stages.add(new StageImpl(size.getWidth(), size.getHeight(), true, false, new EmptyStageGenerationStrategy()));
            } else {
                // Otherwise, both stairs
                this.stages.add(new StageImpl(size.getWidth(), size.getHeight(), true, true, new EmptyStageGenerationStrategy()));
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
            Stage stage = this.stages.get(z);
            stage.findEmptyPosition().ifPresent(pos -> {
                NPC npc = new NPC.Builder(new EntityName("Snuugz"))
                        .withStage(stage)
                        .withPosition(pos)
                        .withHealth(new Health(50))
                        .withGlyph(Assets.Glyphs.SNUUGZ)
                        .build();
                stage.addEntity(npc);
            });
        }


//        NPC hunterSeeker = new NPC(
//                this.getCurrentStage(), Assets.player, this.getCurrentStage().findEmptyPosition()
//        );
//        hunterSeeker.setAi(new HunterSeekerAI(hunterSeeker));
//        this.getCurrentStage().addEntity(hunterSeeker);

        List<Item> createdItems = new ArrayList<>();
//        createdItems.add(ItemFactory.buildBodyArmor());
//        createdItems.add(ItemFactory.buildBoots());
//        createdItems.add(ItemFactory.buildCloak());
//        createdItems.add(ItemFactory.buildGloves());
//        createdItems.add(ItemFactory.buildHelmet());
//        createdItems.add(ItemFactory.buildRing());
//        createdItems.add(ItemFactory.buildRing());
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

        states.add(new PlayerTurnState(this));
    }


    public void popState() {
        if (states.size() > 1) {
            states.removeFirst();
        }
    }

    public void pushState(State state) {
        states.addFirst(state);
    }

    public State getCurrentState() {
        return states.peekFirst();
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
