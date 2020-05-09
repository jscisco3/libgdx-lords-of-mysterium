package com.jscisco.lom.shelf.screens.popup;

import com.badlogic.gdx.Screen;

public abstract class AbstractPopup implements Popup {

    private Screen screen;

    public AbstractPopup(Screen screen) {
        this.screen = screen;
    }

    @Override
    public void render() {

    }
}
