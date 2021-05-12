package com.jscisco.lom.application.services;

import com.badlogic.gdx.Screen;
import com.jscisco.lom.Game;
import com.jscisco.lom.application.GameScreen;
import com.jscisco.lom.application.KingdomScreen;
import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.repository.GameRepository;
import com.jscisco.lom.domain.repository.HeroRepository;
import com.jscisco.lom.domain.repository.LevelRepository;
import com.jscisco.lom.domain.repository.ZoneRepository;
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

    final LevelRepository levelRepository;
    final HeroRepository heroRepository;
    final GameRepository gameRepository;
    final ZoneRepository zoneRepository;

    @Autowired
    public GameService(LevelRepository levelRepository, HeroRepository heroRepository, GameRepository gameRepository, ZoneRepository zoneRepository) {
        this.levelRepository = levelRepository;
        this.heroRepository = heroRepository;
        this.gameRepository = gameRepository;
        this.zoneRepository = zoneRepository;
    }

    public List<SaveGame> getGames() {
        return gameRepository.findAll();
    }

    public Level loadLevel(Long levelId) {
        Level level = levelRepository.findById(levelId).get();
        // Initialize all entity positions
        level.getEntities().forEach(e -> e.move(e.getPosition()));
        return level;
    }

    public void saveLevel(Level level) {
        levelRepository.save(level);
    }

    public void saveZone(Zone zone) {
        zoneRepository.save(zone);
    }

    public void saveGame(SaveGame saveGame) {
        gameRepository.save(saveGame);
    }

    public Screen loadGame(Game game, Long saveGameId) {
        SaveGame saveGame = gameRepository.findById(saveGameId).orElseThrow(IllegalStateException::new);
        if (saveGame.getLevelId() != null) {
            Level level = loadLevel(saveGame.getLevelId());
            logger.info("loading with # of entities: " + level.getEntities().size());
            return new GameScreen(game, saveGame, level);
        } else {
            return new KingdomScreen(game, saveGame, saveGame.getKingdom());
        }
    }

}
