package com.jscisco.lom.application.services;

import com.badlogic.gdx.Screen;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.Game;
import com.jscisco.lom.persistence.SaveGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Service
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    final ZoneService zoneService;
    final ObjectMapper objectMapper;

    @Autowired
    public GameService(ObjectMapper objectMapper, ZoneService zoneService) {
        this.zoneService = zoneService;
        this.objectMapper = objectMapper;
    }

    public List<SaveGame> getGames() {
        throw new UnsupportedOperationException();
    }

    /**
     * Saves the relevant metadata for the game?
     * @param saveGame
     */
    public void saveGame(SaveGame saveGame) throws IOException {
        // Create the folder if it does not exist
        String filePath = "saves/" + saveGame.getId().toString();
        new File(filePath).mkdirs();
        objectMapper.writeValue(Paths.get(filePath + "/metadata.json").toFile(), saveGame);
    }

    public Screen loadGame(Game game, Long saveGameId) {
        throw new UnsupportedOperationException();
    }

}
