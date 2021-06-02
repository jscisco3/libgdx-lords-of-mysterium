package com.jscisco.lom.application.services;

import com.badlogic.gdx.Screen;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.GameScreen;
import com.jscisco.lom.application.KingdomScreen;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.repository.GameRepository;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.Zone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    final GameRepository gameRepository;
    final ZoneService zoneService;

    @Autowired
    public GameService(GameRepository gameRepository, ZoneService zoneService) {
        this.gameRepository = gameRepository;
        this.zoneService = zoneService;
    }

    public List<SaveGame> getGames() {
        return gameRepository.findAll();
    }

    public void saveGame(SaveGame saveGame) {
        gameRepository.save(saveGame);
    }

    public Screen loadGame(Game game, Long saveGameId) {
        SaveGame saveGame = gameRepository.findById(saveGameId).orElseThrow(IllegalStateException::new);
        if (saveGame.getLevelId() != null) {
            // Load the zone
            Zone zone = zoneService.getZone(saveGame.getZoneId());
            Level level = zone.getLevelById(saveGame.getLevelId());
            logger.info("loading with # of entities: " + level.getEntities().size());
            return new GameScreen(game, saveGame, level.getHero());
        } else {
            return new KingdomScreen(game, saveGame, saveGame.getKingdom());
        }
    }

}
