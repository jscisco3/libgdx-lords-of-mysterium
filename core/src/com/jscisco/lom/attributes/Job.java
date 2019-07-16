package com.jscisco.lom.attributes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;

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

    public static Job warrior() {
        return new Builder("Warrior")
                .withBaseStats(new Stats.Builder()
                        .withStrength(14)
                        .withConstitution(14)
                        .withDexterity(12)
                        .withIntelligence(8)
                        .build())
                .withStatsPerLevel(new Stats.Builder()
                        .withStrength(2)
                        .withConstitution(2)
                        .withDexterity(1)
                        .withIntelligence(0)
                        .build()
                )
                .withIcon(Assets.warrior)
                .build();
    }

    public static Job wizard() {
        return new Builder("Wizard")
                .withBaseStats(new Stats.Builder()
                        .withStrength(8)
                        .withConstitution(8)
                        .withDexterity(10)
                        .withIntelligence(16)
                        .build())
                .withStatsPerLevel(new Stats.Builder()
                        .withStrength(0)
                        .withConstitution(0)
                        .withDexterity(1)
                        .withIntelligence(2)
                        .build()
                )
                .withIcon(Assets.wizard)
                .build();
    }

    public static Job rogue() {
        return new Builder("Rogue")
                .withBaseStats(new Stats.Builder()
                        .withStrength(10)
                        .withConstitution(12)
                        .withDexterity(14)
                        .withIntelligence(10)
                        .build())
                .withStatsPerLevel(new Stats.Builder()
                        .withStrength(1)
                        .withConstitution(1)
                        .withDexterity(1)
                        .withIntelligence(1)
                        .build()
                )
                .withIcon(Assets.rogue)
                .build();
    }
}
