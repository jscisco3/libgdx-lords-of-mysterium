package com.jscisco.lom.application;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.jscisco.lom.domain.Direction;
import com.jscisco.lom.domain.action.WalkAction;
import com.jscisco.lom.domain.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class AdventureInputProcessor implements InputProcessor {
    private static final Logger logger = LoggerFactory.getLogger(AdventureInputProcessor.class);

    private final Player player;
    private Set<Integer> keysDown = new HashSet<>();

    public AdventureInputProcessor(Player player) {
        this.player = player;
    }

    @Override
    public boolean keyDown(int keycode) {
        logger.info("Keydown: " + keycode);
        keysDown.add(keycode);
//        if (keysDown.contains(Input.Keys.RIGHT)) {
//            player.setAction(new WalkAction(player, Direction.E));
//        }
//        switch (keycode) {
//            case Input.Keys.LEFT:
//                player.setAction(new WalkAction(player, Direction.W));
//                return true;
//            case Input.Keys.RIGHT:
//                player.setAction(new WalkAction(player, Direction.E));
//                return true;
//            case Input.Keys.UP:
//                player.setAction(new WalkAction(player, Direction.N));
//                return true;
//            case Input.Keys.DOWN:
//                player.setAction(new WalkAction(player, Direction.S));
//                return true;
//            case Input.Keys.PERIOD:
//                player.setAction(new RestAction(player));
//                return true;
//            default:
//                return false;
//        }
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