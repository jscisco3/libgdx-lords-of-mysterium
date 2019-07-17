package com.jscisco.lom.attributes;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.assets.Assets;

public class Job {

    private String name;
    private Stats baseStats;
    private Stats statsPerLevel;
    private String textureMapLookup;

    private Job() {

    }

    public String getName() {
        return name;
    }

    public TextureRegion getIcon() {
        return Assets.textureMap.get(this.textureMapLookup);
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    public Stats getStatsPerLevel() {
        return statsPerLevel;
    }

    public static class Builder {
        private String name;
        private String textureMapLookup;
        private Stats baseStats;
        private Stats statsPerLevel;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withIcon(String textureMapLookup) {
            this.textureMapLookup = textureMapLookup;
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
            Job job = new Job();
            job.name = this.name;
            job.textureMapLookup = this.textureMapLookup;
            job.baseStats = this.baseStats;
            job.statsPerLevel = this.statsPerLevel;
            return job;
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
                .withIcon("warrior")
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
                .withIcon("wizard")
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
                .withIcon("rogue")
                .build();
    }
}
