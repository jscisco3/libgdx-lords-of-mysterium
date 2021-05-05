package com.jscisco.lom.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("E")
public class ExplorationState extends SaveGameState {
    private Long heroId;
    private Long levelId;

    public ExplorationState() {
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long heroId) {
        this.heroId = heroId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    @Override
    public String toString() {
        return "ExplorationState{" +
                "id=" + id +
                ", heroId=" + heroId +
                ", levelId=" + levelId +
                '}';
    }
}
