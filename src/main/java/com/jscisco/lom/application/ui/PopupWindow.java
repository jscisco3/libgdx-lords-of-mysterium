package com.jscisco.lom.application.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.jscisco.lom.application.configuration.GameConfiguration;

public abstract class PopupWindow extends Window {


    protected final Table content;
    protected final ScrollPane scroller;
    protected final InputMultiplexer previousInput;

    public PopupWindow(String title, Skin skin, InputMultiplexer previousInput) {
        super(title, skin);
        content = new Table(this.getSkin());
        scroller = new ScrollPane(content);
        this.previousInput = previousInput;

        TextButton close = new TextButton("X", GameConfiguration.getSkin(), "default");
        close.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                close();
            }
        });
        getTitleTable().add(close).size(38, 38).padRight(10).padTop(0);
    }

    protected void close() {
        Gdx.input.setInputProcessor(previousInput);
        this.addAction(Actions.removeActor(this));
    }

    protected abstract void setContent();

    public ScrollPane getScroller() {
        return scroller;
    }
}
