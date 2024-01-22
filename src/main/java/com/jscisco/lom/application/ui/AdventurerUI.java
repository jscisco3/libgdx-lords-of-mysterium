package com.jscisco.lom.application.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.Pool;
import com.jscisco.lom.domain.Pools;
import com.jscisco.lom.domain.entity.Hero;

public class AdventurerUI extends Table {

    private Hero hero;
    private Label name;
    private Label position;
    private Label health;
    private Label mana;
    private Label experience;
    private Label level;

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

        // Health
        Pools pools = hero.getPools();
        Pool hp = pools.getHp();
        this.health = new Label(String.format("Health: %d/%d", hp.getCurrent(), hp.getMax()), skin);
        this.add(health);
        this.row();
        // Mana
        Pool mp = pools.getMp();
        this.mana = new Label(String.format("Mana: %d/%d", mp.getCurrent(), mp.getMax()), skin);
        this.add(mana);
        this.row();
        // Experience
        this.experience = new Label(String.format("Experience: %d/%d", pools.getExperience(), (pools.getLevel() + 1) * 1000), skin);
        this.add(experience);
        this.row();
        // Level
        this.level = new Label(String.format("Level: %d", pools.getLevel()), skin);
        this.add(level);
        this.row();

        this.position = new Label(String.format("(%d, %d)", hero.getPosition().getX(), hero.getPosition().getY()),
                skin);
        this.add(position);
        this.row();
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
        Pools pools = hero.getPools();
        this.position.setText(String.format("(%d, %d)", hero.getPosition().getX(), hero.getPosition().getY()));
        this.health.setText(String.format("Health: %d/%d", pools.getHp().getCurrent(), pools.getHp().getMax()));
        this.mana.setText(String.format("Mana: %d/%d", pools.getMp().getCurrent(), pools.getMp().getMax()));
        // this.health.setText(String.format("%.2f/%.2f",
        // hero.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.HEALTH),
        // hero.getAttributes().getAttributeValue(AttributeSet.AttributeDefinition.MAX_HEALTH)));
        this.state.setText(hero.getState().toString());
        // this.zoneInformation.setText("Zone: " + this.hero.getLevel().getZone().getId());
        // this.levelInformation.setText("Level: " + this.hero.getLevel().getId());
        this.fps.setText(String.format("FPS: %d", Gdx.graphics.getFramesPerSecond()));
    }
}
