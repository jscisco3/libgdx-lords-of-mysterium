package com.jscisco.lom.states;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.World;
import com.badlogic.gdx.Input;
import com.jscisco.lom.components.InitiativeComponent;
import com.jscisco.lom.components.MovementComponent;
import com.jscisco.lom.components.flags.ActiveTurn;
import com.jscisco.lom.components.flags.PlayerComponent;
import com.jscisco.lom.systems.InitiativeSystem;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerTurnState extends State {

    private Logger logger = LoggerFactory.getLogger(PlayerTurnState.class);

    private ComponentMapper<MovementComponent> mMovement;
    private ComponentMapper<InitiativeComponent> mInitiative;
    private ComponentMapper<ActiveTurn> mActiveTurn;

    private int player;
    private World world;

    public PlayerTurnState(World world) {
        super(world);
        world.inject(this);
        EntitySubscription subscription = world.getAspectSubscriptionManager().get(Aspect.all(PlayerComponent.class));
        // Assume there is a player
        player = subscription.getEntities().getData()[0];
    }

    @Override
    public void update() {
        world.process();
    }

    @Override
    public void handleInput(Input input) {


        if (input.isKeyPressed(Input.Keys.UP)) {
            MovementComponent movement = mMovement.create(player);
            movement.direction = new Position3D(0, 1, 0);
            endTurn();
        }
        if (input.isKeyPressed(Input.Keys.DOWN)) {
            MovementComponent movement = mMovement.create(player);
            movement.direction = new Position3D(0, -1, 0);
            endTurn();
        }
        if (input.isKeyPressed(Input.Keys.RIGHT)) {
            MovementComponent movement = mMovement.create(player);
            movement.direction = new Position3D(1, 0, 0);
            endTurn();
        }
        if (input.isKeyPressed(Input.Keys.LEFT)) {
            MovementComponent movement = mMovement.create(player);
            movement.direction = new Position3D(-1, 0, 0);
            endTurn();
        }
    }

    private void endTurn() {
        mActiveTurn.remove(player);
        InitiativeSystem initiativeSystem = world.getSystem(InitiativeSystem.class);
        initiativeSystem.unpause();
        logger.info("Ending the player's turn...");
    }
}
