package com.jscisco.lom.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.systems.IteratingSystem;
import com.jscisco.lom.components.PositionComponent;
import com.jscisco.lom.components.Tile;
import com.jscisco.lom.components.flags.PlayerComponent;
import com.jscisco.lom.components.model.TileActor;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RenderSystem extends IteratingSystem {

    private Logger logger = LoggerFactory.getLogger(RenderSystem.class);

    private ComponentMapper<Tile> mTile;
    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<PlayerComponent> mPlayer;
    private int player;


    public RenderSystem() {
        super(Aspect.all(Tile.class, PositionComponent.class));
    }

    @Override
    protected void begin() {
        player = getPlayer();
    }

    @Override
    protected void process(int entityId) {
        TileActor actor = mTile.get(entityId).actor;
        Position3D renderablePosition = mPosition.get(entityId).position;
        float x = renderablePosition.getX() * actor.getRegion().getRegionWidth();
        float y = renderablePosition.getY() * actor.getRegion().getRegionHeight();
        actor.setPosition(x, y);
        actor.setVisible(true);
        if (mPlayer.has(player)) {
            if (renderablePosition.getZ() != mPosition.get(player).position.getZ()) {
                actor.setVisible(false);
            }
        }
    }

    public int getPlayer() {
        EntitySubscription subscription = world.getAspectSubscriptionManager().get(Aspect.all(PlayerComponent.class));
        int[] entities = subscription.getEntities().getData();
        if (entities.length > 0) {
            return entities[0];
        }
        return Integer.MIN_VALUE;
    }
}
