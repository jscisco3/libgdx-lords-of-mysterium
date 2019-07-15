package com.jscisco.lom.screens.kingdom.shared;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.Player;

public class HeroInfoBlock {

    private Player player;

    private float x;
    private float y;
    private float width;
    private float height;

    private TextureRegion icon;
    private String name;

    public HeroInfoBlock(Player player, float x, float y, float width, float height) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.icon = player.getJob().getIcon();
        this.name = player.getName();
    }

    public void render(SpriteBatch batch) {
        batch.begin();

        batch.draw(this.icon, x, y + height);
        Config.font.draw(batch, name, x + icon.getRegionWidth() + 5, y + height);
        renderStats(batch);
        batch.end();
    }

    private void renderStats(SpriteBatch batch) {
        float h = y + height - this.icon.getRegionHeight();
        float xx = x;
        Config.font.draw(batch, String.format("S: %s", this.player.getStats().getStrength()), xx, h);
        xx += 25;
        Config.font.draw(batch, String.format("C: %s", this.player.getStats().getConstitution()), xx, h);
        xx += 25;
        Config.font.draw(batch, String.format("I: %s", this.player.getStats().getIntelligence()), xx, h);
        xx += 25;
        Config.font.draw(batch, String.format("D: %s", this.player.getStats().getDexterity()), xx, h);
    }

}
