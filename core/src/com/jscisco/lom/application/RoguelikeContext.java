package com.jscisco.lom.application;

import com.jscisco.lom.domain.GameObject;

/**
 * The shared context that makes some things a little easier to deal with
 */
public class RoguelikeContext {
    final GameObject player;

    public RoguelikeContext(GameObject player) {
        this.player = player;
    }
}
