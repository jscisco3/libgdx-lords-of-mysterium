package com.jscisco.lom.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.jscisco.lom.ui.Selection;

import java.util.List;

public abstract class AbstractSelectionScreen<T> implements Screen {

    protected List<Selection<T>> choices;
    protected int currentSelectionIdx = 0;

    public AbstractSelectionScreen(List<Selection<T>> choices) {
        this.choices = choices;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Input input = Gdx.input;
        if (input.isKeyJustPressed(Input.Keys.DOWN)) {
            decrementSelection();
        }
        if (input.isKeyJustPressed(Input.Keys.UP)) {
            incrementSelection();
        }
        if (input.isKeyJustPressed(Input.Keys.ENTER)) {
            this.handleSelection();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public abstract void handleSelection();

    private void incrementSelection() {
        this.currentSelectionIdx = (this.currentSelectionIdx + 1) % this.choices.size();
    }

    private void decrementSelection() {
        this.currentSelectionIdx -= 1;
        if (this.currentSelectionIdx < 0) {
            this.currentSelectionIdx = this.choices.size() - 1;
        }
    }

}
