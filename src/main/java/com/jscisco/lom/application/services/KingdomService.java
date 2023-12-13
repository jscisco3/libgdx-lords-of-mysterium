package com.jscisco.lom.application.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jscisco.lom.domain.kingdom.Kingdom;
import com.jscisco.lom.persistence.SaveGame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class KingdomService {

    final Logger logger = LoggerFactory.getLogger(KingdomService.class);
    final ObjectMapper objectMapper;

    @Autowired
    public KingdomService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Saves the kingdom to file
     *
     * @param kingdom
     * @param saveGame
     */
    public void saveKingdom(Kingdom kingdom, SaveGame saveGame) throws IOException {
        Path kingdomFile = Paths.get("saves/", saveGame.getId().toString(), "kingdom.json");
        objectMapper.writeValue(kingdomFile.toFile(), kingdom);
    }

}
