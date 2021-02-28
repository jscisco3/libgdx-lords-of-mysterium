package com.jscisco.lom.application;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.ui.Rectangle;
import com.jscisco.lom.domain.entity.Player;

public class AdventurerUI extends Table {

    private Player player;
    private Label name;
    private Label position;
    private ProgressBar healthBar;

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
        this.healthBar = new ProgressBar(0f, this.player.getAttributes().getMaxHealth().getValue(), 1f, false, skin);
        this.add(healthBar);

    }

    // TODO: If this gets to be too much, consider making the player observable.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.position.setText(String.format("(%d, %d)", player.getPosition().getX(), player.getPosition().getY()));
        this.healthBar.setValue(this.player.getAttributes().getHealth().getValue());
    }
}
