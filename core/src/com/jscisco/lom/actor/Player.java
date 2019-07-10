package com.jscisco.lom.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.dungeon.Zone;
import com.jscisco.lom.util.Position3D;

public class Player extends Entity {

    public Player(Zone zone, Position3D position, double radius) {
        super(zone);
        this.position = position;
        this.fieldOfView = new FieldOfView(radius);
        this.health = new Health(100);
    }

    @Override
    public TextureRegion getTexture() {
        return Assets.player;
    }
}
