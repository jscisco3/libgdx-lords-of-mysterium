package com.jscisco.lom.application;

import com.jscisco.lom.domain.entity.Hero;
import com.jscisco.lom.domain.repository.HeroRepository;
import com.jscisco.lom.domain.repository.LevelRepository;
import com.jscisco.lom.domain.zone.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LevelService {

    final LevelRepository levelRepository;
    final HeroRepository heroRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository, HeroRepository heroRepository) {
        this.levelRepository = levelRepository;
        this.heroRepository = heroRepository;
    }

    public Level loadLevel(Long levelId, Long heroId) {
        Level level = levelRepository.getById(levelId);
        Hero hero = heroRepository.getById(heroId);
        level.addHero(hero);
        return level;
    }
}
