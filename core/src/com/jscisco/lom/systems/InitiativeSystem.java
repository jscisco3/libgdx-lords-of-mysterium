package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.InitiativeComponent;
import com.jscisco.lom.components.flags.ActiveTurn;
import com.jscisco.lom.components.flags.PlayerComponent;

public class InitiativeSystem extends IteratingSystem {

    private ComponentMapper<InitiativeComponent> mInitiative;
    private ComponentMapper<ActiveTurn> mActiveTurn;
    private ComponentMapper<PlayerComponent> mPlayer;
    private int initiativeDelta;
    private boolean paused = false;

    public InitiativeSystem() {
        super(Aspect.all(InitiativeComponent.class));
    }

    @Override
    protected void begin() {
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
        }
        if (mPlayer.has(entityId)) {
            this.paused = true;
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

        for (int e : subscription.getEntities().getData()) {
            int initiative = mInitiative.get(e).initiative;
            if (mInitiative.get(e).initiative < initiativeDelta) {
                initiativeDelta = initiative;
            }
        }
        return initiativeDelta;
    }

}
