package com.jscisco.lom.entity;

import squidpony.FakeLanguageGen;

public class PlayerFactory {

    private static FakeLanguageGen generator = new FakeLanguageGen();

    public static Player createRandomHero() {
        Player player = new Player(null, null);
        player.setName(generator.word(true));
        return player;
    }

}
