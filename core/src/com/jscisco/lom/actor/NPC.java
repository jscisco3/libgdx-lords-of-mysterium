package com.jscisco.lom.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.action.Action;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.attributes.ai.AI;
import com.jscisco.lom.attributes.ai.RestAI;
import com.jscisco.lom.dungeon.Zone;
import com.jscisco.lom.util.Position3D;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NPC extends Entity {

    private final Logger logger = LoggerFactory.getLogger(NPC.class);
    private AI ai;

    public NPC(Zone zone, TextureRegion texture, Position3D position) {
        super(zone);
        this.texture = texture;
        this.position = position;
        this.health = new Health(40);
        this.ai = new RestAI();
    }

    @Override
    public Action getNextAction() {
        return this.ai.nextAction(this);
    }
}
