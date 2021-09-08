package com.jscisco.lom.application.services;

import com.badlogic.gdx.Screen;
import com.jscisco.lom.Game;
import com.jscisco.lom.persistence.SaveGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    final ZoneService zoneService;

    @Autowired
    public GameService(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    public List<SaveGame> getGames() {
        throw new UnsupportedOperationException();
    }

    public void saveGame(SaveGame saveGame) {
        throw new UnsupportedOperationException();
    }

    public Screen loadGame(Game game, Long saveGameId) {
        throw new UnsupportedOperationException();
    }

}
