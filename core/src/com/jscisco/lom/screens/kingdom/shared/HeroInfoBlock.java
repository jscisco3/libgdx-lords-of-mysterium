package com.jscisco.lom.screens.kingdom.shared;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jscisco.lom.config.Config;
import com.jscisco.lom.entity.EntityName;
import com.jscisco.lom.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HeroInfoBlock {

    private Player player;

    private float x;
    private float y;
    private float width;
    private float height;

    private TextureRegion icon;
    private EntityName name;
    private boolean selected;

    private static final BitmapFont font = Config.createFont(36);
    private static final GlyphLayout layout = new GlyphLayout();
    private static final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private List<String> statsStrings = new ArrayList<>();

    public HeroInfoBlock(Player player, float x, float y, float width, float height) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.icon = player.getJob().getIcon();
        this.name = player.getName();
        this.selected = false;

        this.statsStrings.add(String.format("S: %s  ", this.player.getStatistics().getStrength().getValue()));
        this.statsStrings.add(String.format("C: %s  ", this.player.getStatistics().getConstitution().getValue()));
        this.statsStrings.add(String.format("D: %s  ", this.player.getStatistics().getDexterity().getValue()));
        this.statsStrings.add(String.format("I: %s  ", this.player.getStatistics().getIntelligence().getValue()));

    }

    public void render(SpriteBatch batch) {
        if (this.selected) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }

        batch.begin();
        batch.draw(this.icon, x, y + height - icon.getRegionHeight());
        font.draw(batch, name.get(), x + icon.getRegionWidth() + 5, y + height);
        renderStats(batch);
        batch.end();
    }

    private void renderStats(SpriteBatch batch) {
        float h = y + height - this.icon.getRegionHeight();
        float xx = x;

        for (String s : this.statsStrings) {
            font.draw(batch, s, xx, h);

            layout.setText(font, s);
            xx += layout.width;
        }
    }

    public void select() {
        this.selected = true;
    }

    public void deselect() {
        this.selected = false;
    }
}
