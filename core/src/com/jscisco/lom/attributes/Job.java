package com.jscisco.lom.attributes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Job {

    private String name;
    private TextureRegion icon;
    private Stats baseStats;
    private Stats statsPerLevel;

    private Job() {

    }

    public String getName() {
        return name;
    }

    public TextureRegion getIcon() {
        return icon;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    public Stats getStatsPerLevel() {
        return statsPerLevel;
    }

    public static class Builder {
        private String name;
        private TextureRegion icon;
        private Stats baseStats;
        private Stats statsPerLevel;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withIcon(TextureRegion icon) {
            this.icon = icon;
            return this;
        }

        public Builder withBaseStats(Stats baseStats) {
            this.baseStats = baseStats;
            return this;
        }

        public Builder withStatsPerLevel(Stats statsPerLevel) {
            this.statsPerLevel = statsPerLevel;
            return this;
        }

        public Job build() {
            Job clazz = new Job();
            clazz.name = this.name;
            clazz.icon = this.icon;
            clazz.baseStats = this.baseStats;
            clazz.statsPerLevel = this.statsPerLevel;
            return clazz;
        }
    }
}
