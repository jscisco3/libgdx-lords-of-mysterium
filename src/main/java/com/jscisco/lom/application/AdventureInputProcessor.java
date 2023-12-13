package com.jscisco.lom.application;

import com.badlogic.gdx.InputProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class AdventureInputProcessor implements InputProcessor {
    private static final Logger logger = LoggerFactory.getLogger(AdventureInputProcessor.class);

    private Set<Integer> keysDown = new HashSet<>();

    public boolean isKeyDown() {
        return !keysDown.isEmpty();
    }

    @Override
    public boolean keyDown(int keycode) {
        logger.trace("Keydown: " + keycode);
        keysDown.add(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysDown.remove(keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public Set<Integer> getKeysDown() {
        return keysDown;
    }
}
