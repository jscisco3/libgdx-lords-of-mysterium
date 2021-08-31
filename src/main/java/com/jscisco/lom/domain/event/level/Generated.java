package com.jscisco.lom.domain.event.level;

import com.jscisco.lom.application.configuration.GameConfiguration;
import com.jscisco.lom.domain.zone.Level;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategy;
import com.jscisco.lom.domain.zone.LevelGeneratorStrategyFactory;

public class Generated extends LevelEvent {

    private LevelGeneratorStrategy.Strategy strategy;
    private Long seed;

    public Generated() {
        seed = GameConfiguration.rng.getState();
    }

    public LevelGeneratorStrategy.Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(LevelGeneratorStrategy.Strategy strategy) {
        this.strategy = strategy;
    }

    public Long getSeed() {
        return seed;
    }

    public void setSeed(Long seed) {
        this.seed = seed;
    }

    @Override
    public void process(Level level) {
        super.process(level);
        LevelGeneratorStrategy generator = LevelGeneratorStrategyFactory.getStrategy(this.strategy);
        level.setTiles(generator.generate(level.getWidth(), level.getHeight()));
    }

    @Override
    public String toString() {
        return "Generated{" +
                "id=" + id +
                ", eventTime=" + eventTime +
                ", strategy=" + strategy +
                ", seed=" + seed +
                ", levelId=" + levelId +
                '}';
    }
}
