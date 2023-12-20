package com.jscisco.lom.application.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.entity.Hero;

public class AdventurerUI extends Table {

    private Hero hero;
    private Label name;
    private Label position;
    private Label health;
    private Label state;
    private Label zoneInformation;
    private Label levelInformation;
    // TODO: Move to a debug UI that can be toggled on and off
    private Label fps;

    private Skin skin = GameConfiguration.getSkin();

    public AdventurerUI(Hero hero, float x, float y, float width, float height, Color color) {
        this.hero = hero;
        this.setSkin(GameConfiguration.getSkin());
        this.addActor(new Rectangle(x, y, width, height, color));
        this.zoneInformation = new Label("", skin);
        this.levelInformation = new Label("", skin);

        this.name = new Label(this.hero.getName().getName(), skin);
        this.fps = new Label(String.format("FPS: %d", Gdx.graphics.getFramesPerSecond()), skin);

        this.setFillParent(false);
        this.add(this.name);
        this.row();
        this.position = new Label(String.format("(%d, %d)", hero.getPosition().getX(), hero.getPosition().getY()),
                skin);
        this.add(position);
        this.row();
        // this.health = new Label(String.format("%.2f/%.2f",
        // hero.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.HEALTH),
        // hero.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH)), skin);
        // this.health.setColor(Color.RED);
        // this.add(health);
        this.row();
        this.state = new Label(hero.getState().toString(), skin);
        this.add(state);
        this.row();
        this.add(this.zoneInformation);
        this.row();
        this.add(this.levelInformation);
        this.row();
        this.add(this.fps);

    }

    // TODO: If this gets to be too much, consider making the player observable.
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        this.position.setText(String.format("(%d, %d)", hero.getPosition().getX(), hero.getPosition().getY()));
        // this.health.setText(String.format("%.2f/%.2f",
        // hero.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.HEALTH),
        // hero.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH)));
        this.state.setText(hero.getState().toString());
        // this.zoneInformation.setText("Zone: " + this.hero.getLevel().getZone().getId());
        // this.levelInformation.setText("Level: " + this.hero.getLevel().getId());
        this.fps.setText(String.format("FPS: %d", Gdx.graphics.getFramesPerSecond()));
    }
}
