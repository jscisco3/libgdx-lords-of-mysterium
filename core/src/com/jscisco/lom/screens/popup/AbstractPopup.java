package com.jscisco.lom.screens.popup;

import com.badlogic.gdx.Screen;

public class AbstractPopup implements Popup {

    private Screen screen;

    public AbstractPopup(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void render() {

    }
}
