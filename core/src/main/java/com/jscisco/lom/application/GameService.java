package com.jscisco.lom.application;

import com.jscisco.lom.domain.SaveGame;
import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.repository.GameRepository;
import com.jscisco.lom.domain.repository.HeroRepository;
import com.jscisco.lom.domain.repository.LevelRepository;
import com.jscisco.lom.domain.zone.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    final LevelRepository levelRepository;
    final HeroRepository heroRepository;
    final GameRepository gameRepository;

    @Autowired
    public GameService(LevelRepository levelRepository, HeroRepository heroRepository, GameRepository gameRepository) {
        this.levelRepository = levelRepository;
        this.heroRepository = heroRepository;
        this.gameRepository = gameRepository;
    }

    public Level loadLevel(Long levelId, Long heroId) {
        Level level = levelRepository.getById(levelId);
        Hero hero = heroRepository.getById(heroId);
        logger.info("Saved hero's position: " + hero.getPosition().toString());
        level.addEntityAtPosition(hero, hero.getPosition());
        return level;
    }

    public Level loadLevel(Long levelId) {
        return levelRepository.getById(levelId);
    }

    public void saveLevel(Level level) {
        levelRepository.save(level);
    }

    public SaveGame loadGame(Long saveGameId) {
        return gameRepository.getById(saveGameId);
    }

    public void saveGame(SaveGame saveGame) {
        gameRepository.save(saveGame);
    }

}
