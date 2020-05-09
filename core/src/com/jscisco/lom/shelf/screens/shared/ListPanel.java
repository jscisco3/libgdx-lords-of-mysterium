package com.jscisco.lom.shelf.screens.shared;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.util.List;

public class ListPanel<T> {

    private List<T> items;

    private int selectedItem = 0;
    private boolean active = false;

    public ListPanel(List<T> items) {
        this.items = items;
    }

    public void render() {
        // Defaults!!

    }

    public void incrementSelection() {
        if (!this.items.isEmpty()) {
            this.selectedItem = (this.selectedItem + 1) % this.items.size();
        }
    }

    public void decrementSelection() {
        if (!this.items.isEmpty()) {
            this.selectedItem -= 1;
            if (this.selectedItem < 0) {
                this.selectedItem = this.items.size() - 1;
            }
        }
    }

    public void activate() {
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    public void handleInput() {
        if (this.active) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                decrementSelection();
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
                incrementSelection();
            }
        }
    }

}
