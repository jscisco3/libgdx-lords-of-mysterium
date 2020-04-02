package com.jscisco.lom.states;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jscisco.lom.LOMGame_Deprecated;
import com.jscisco.lom.config.Config;

public class ConfirmationState extends State {

    private SpriteBatch batch;
    private BitmapFont font;

    public ConfirmationState(LOMGame_Deprecated game) {
        super(game);
        batch = new SpriteBatch();
        batch.begin();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.draw(batch, "Yes or No?", 100, Config.WINDOW_HEIGHT - font.getLineHeight());
        batch.end();
    }

    @Override
    public void update() {

    }

    @Override
    public void handleInput(Input input) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
