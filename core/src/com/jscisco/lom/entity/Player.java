package com.jscisco.lom.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;
import com.jscisco.lom.attributes.*;
import com.jscisco.lom.util.Position;
import com.jscisco.lom.zone.Stage;

public class Player extends Entity {

    public Player(Stage stage, Position position) {
        super(stage);
        this.position = position;
        this.inventory = new Inventory();
        this.equipment = new Equipment();
        this.fieldOfView = new FieldOfView(5f);
        this.health = new Health(100);
        this.job = new Job.Builder("Warrior")
                .withBaseStats(new Stats.Builder()
                        .withStrength(14)
                        .withConstitution(12)
                        .withDexterity(10)
                        .withIntelligence(8)
                        .build())
                .withStatsPerLevel(new Stats.Builder()
                        .withStrength(2)
                        .withConstitution(1)
                        .withDexterity(1)
                        .withIntelligence(0).build())
                .withIcon(Assets.player)
                .build();
        this.stats = this.job.getBaseStats();
    }

    @Override
    public TextureRegion getTexture() {
        return Assets.player;
    }
}
