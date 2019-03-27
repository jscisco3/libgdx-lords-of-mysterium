package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;
import com.jscisco.lom.components.InitiativeComponent;
import com.jscisco.lom.components.flags.ActiveTurn;
import com.jscisco.lom.components.flags.PlayerComponent;
import com.jscisco.lom.dungeon.Dungeon;
import com.jscisco.lom.states.PlayerTurnState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitiativeSystem extends IteratingSystem {

    private Logger logger = LoggerFactory.getLogger(InitiativeSystem.class);

    private ComponentMapper<InitiativeComponent> mInitiative;
    private ComponentMapper<ActiveTurn> mActiveTurn;
    private ComponentMapper<PlayerComponent> mPlayer;
    private int initiativeDelta;
    private Dungeon dungeon;
    private boolean paused = false;

    public InitiativeSystem(Dungeon dungeon) {
        super(Aspect.all(InitiativeComponent.class));
        this.dungeon = dungeon;
    }

    @Override
    protected void begin() {
        logger.info("Begin initiative system");
        initiativeDelta = getMinimumInitiative();
    }

    @Override
    protected boolean checkProcessing() {
        return !paused;
    }

    @Override
    protected void process(int entityId) {
        InitiativeComponent initiativeComponent = mInitiative.get(entityId);
        initiativeComponent.initiative -= initiativeDelta;

        if (initiativeComponent.initiative <= 0) {
            mActiveTurn.create(entityId);
            if (mPlayer.has(entityId)) {
                logger.info("Player has a turn.");
                dungeon.pushState(new PlayerTurnState(dungeon));
                this.paused = true;
            }
        }
    }

    /**
     * Get the minimum initiative of all entities with initiative
     *
     * @return the lowest initiative found
     */
    public int getMinimumInitiative() {
        EntitySubscription subscription = world.getAspectSubscriptionManager().get(Aspect.all(InitiativeComponent.class));

        int initiativeDelta = Integer.MAX_VALUE;
        IntBag entities = subscription.getEntities();
        logger.info(entities.getData().toString());

        for (int e : subscription.getEntities().getData()) {
            if (mInitiative.has(e)) {
                int initiative = mInitiative.get(e).initiative;
                if (mInitiative.get(e).initiative < initiativeDelta) {
                    initiativeDelta = initiative;
                }
            }
        }
        return initiativeDelta;
    }

    public void pause() {
        this.paused = true;
    }

    public void unpause() {
        this.paused = false;
    }

}
