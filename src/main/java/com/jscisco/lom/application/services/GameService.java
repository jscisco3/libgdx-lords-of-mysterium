package com.jscisco.lom.application.services;

import com.badlogic.gdx.Screen;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.Game;
import com.jscisco.lom.persistence.SaveGame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class GameService {

    final ZoneService zoneService;
    final ObjectMapper objectMapper;

    @Autowired
    public GameService(ObjectMapper objectMapper, ZoneService zoneService) {
        this.zoneService = zoneService;
        this.objectMapper = objectMapper;
    }

    public List<SaveGame> getGames() throws IOException {
        // Populate save games from the folders in saves/
        List<SaveGame> games = new ArrayList<>();
        Path savedGamePath = Paths.get("saves");
        for (File file : Objects.requireNonNull(savedGamePath.toFile().listFiles())) {
            log.info(file.toString());
            File savedGame = Paths.get(file.toString(), "metadata.json").toFile();
            games.add(objectMapper.readValue(savedGame, SaveGame.class));
        }
        return games;
    }

    /**
     * Saves the relevant metadata for the game?
     *
     * @param saveGame
     */
    public void saveGame(SaveGame saveGame) throws IOException {
        // Update last played
        saveGame.setLastPlayed(LocalDateTime.now());
        // Create the folder if it does not exist
        String filePath = "saves/" + saveGame.getId().toString();
        new File(filePath).mkdirs();
        objectMapper.writeValue(Paths.get(filePath + "/metadata.json").toFile(), saveGame);
    }

    public Screen loadGame(Game game, Long saveGameId) {
        throw new UnsupportedOperationException();
    }

}
