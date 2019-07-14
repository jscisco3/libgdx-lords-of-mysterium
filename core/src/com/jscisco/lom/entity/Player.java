package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.attributes.Equipment;
import com.jscisco.lom.attributes.FieldOfView;
import com.jscisco.lom.attributes.Health;
import com.jscisco.lom.attributes.Inventory;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;

public class Player extends Entity {

    public Player(Stage stage, Position position, double radius) {
        super(stage);
        this.position = position;
        this.inventory = new Inventory();
        this.equipment = new Equipment();
        this.fieldOfView = new FieldOfView(radius);
        this.health = new Health(100);
    }

    @Override
    public TextureRegion getTexture() {
        return Assets.player;
    }
}
