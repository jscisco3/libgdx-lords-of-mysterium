package com.jscisco.lom.ui.popup;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ConfirmationPopup extends AbstractPopup {

    private SpriteBatch batch;
    private BitmapFont font;

    public ConfirmationPopup(Screen screen) {
        super(screen);
        font = new BitmapFont();
        font.setColor(255, 0, 0, 1);
    }

    @Override
    public void render() {

    }
}
