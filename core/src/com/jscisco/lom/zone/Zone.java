package com.jscisco.lom.zone;

import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.entity.NPC;
import com.jscisco.lom.entity.Player;
import com.jscisco.lom.items.Item;
import com.jscisco.lom.items.ItemType;
import com.jscisco.lom.states.PlayerTurnState;
import com.jscisco.lom.states.State;
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

    public Zone(Size3D size) {
        this.size = size;
        this.stages = new ArrayList<>();

        for (int z = 0; z < size.getDepth(); z++) {
//            this.stages.add(new StageImpl(size.getWidth(), size.getHeight(), new GenericStrategy()));
            this.stages.add(new StageImpl(size.getWidth(), size.getHeight(), new EmptyStageGenerationStrategy()));
        }
        this.currentStageIndex = 0;

        Player player = new Player(
                this.getCurrentStage(), this.getCurrentStage().findEmptyPosition(), 10.0
        );
        this.getCurrentStage().addEntity(player);
        this.getCurrentStage().setPlayer(player);


        for (int i = 0; i < 10; i++) {
            this.getCurrentStage().addEntity(new NPC(
                    this.getCurrentStage(), Assets.rat, this.getCurrentStage().findEmptyPosition()
            ));
        }

        for (int i = 0; i < 5; i++) {
            Item item = new Item(new ItemType("Sword", "A Cool Sword", 5), player.getPosition(), Assets.sword);
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
}
