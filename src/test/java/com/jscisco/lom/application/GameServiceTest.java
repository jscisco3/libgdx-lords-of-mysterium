package com.jscisco.lom.application;

import com.jscisco.lom.application.services.GameService;
import com.jscisco.lom.application.services.ZoneService;
import com.jscisco.lom.configuration.ApplicationConfiguration;
import com.jscisco.lom.persistence.GameVersion;
import com.jscisco.lom.persistence.SaveGame;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
public class GameServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceTest.class);

    @Autowired
    private GameService gameService;

    @Autowired
    private ZoneService zoneService;

    @Test
    public void can_create_save_game() throws IOException {
        gameService.saveGame(new SaveGame(GameVersion.of("Test")));
    }


}
