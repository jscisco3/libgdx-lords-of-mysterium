package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.ui.Rectangle;
import com.jscisco.lom.domain.attribute.AttributeSet;
import com.jscisco.lom.domain.entity.Player;

public class AdventurerUI extends Table {

    private Player player;
    private Label name;
    private Label position;
    private Label health;

    private Skin skin = GameConfiguration.getSkin();

    public AdventurerUI(Player player, float x, float y, float width, float height, Color color) {
        this.player = player;
        this.setSkin(GameConfiguration.getSkin());
        this.addActor(new Rectangle(x, y, width, height, color));


        this.name = new Label(this.player.getName().getName(), skin);

        this.setFillParent(false);
        this.add(this.name);
        this.row();
        this.position = new Label(String.format("(%d, %d)", player.getPosition().getX(), player.getPosition().getY()), skin);
        this.add(position);
        this.row();
        this.health = new Label(String.format("%.2f/%.2f", player.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.HEALTH), player.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH)), skin);
        this.health.setColor(Color.RED);
        this.add(health);

    }

    // TODO: If this gets to be too much, consider making the player observable.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.position.setText(String.format("(%d, %d)", player.getPosition().getX(), player.getPosition().getY()));
        this.health.setText(String.format("%.2f/%.2f", player.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.HEALTH), player.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH)));
    }
}
