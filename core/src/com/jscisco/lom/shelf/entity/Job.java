package com.jscisco.lom.shelf.entity;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jscisco.lom.shelf.assets.Assets;

public class Job {

    private String name;
    private Statistics baseStatistics;
    private Statistics statisticsPerLevel;
    private Assets.Glyphs glyph;

    private Job() {

    }

    public String getName() {
        return name;
    }

    public TextureRegion getIcon() {
        return Assets.textureMap.get(this.glyph);
    }

    public Statistics getBaseStatistics() {
        return baseStatistics;
    }

    public Statistics getStatisticsPerLevel() {
        return statisticsPerLevel;
    }

    public static class Builder {
        private String name;
        private Assets.Glyphs glyph;
        private Statistics baseStatistics;
        private Statistics statisticsPerLevel;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withGlyph(Assets.Glyphs glyph) {
            this.glyph = glyph;
            return this;
        }

        public Builder withBaseStats(Statistics baseStatistics) {
            this.baseStatistics = baseStatistics;
            return this;
        }

        public Builder withStatsPerLevel(Statistics statisticsPerLevel) {
            this.statisticsPerLevel = statisticsPerLevel;
            return this;
        }

        public Job build() {
            Job job = new Job();
            job.name = this.name;
            job.glyph = this.glyph;
            job.baseStatistics = this.baseStatistics;
            job.statisticsPerLevel = this.statisticsPerLevel;
            return job;
        }
    }

    public static Job warrior() {
        return new Builder("Warrior")
                .withBaseStats(new Statistics.Builder()
                        .withStrength(new Strength(14))
                        .withConstitution(new Constitution(14))
                        .withDexterity(new Dexterity(12))
                        .withIntelligence(new Intelligence(8))
                        .build())
                .withStatsPerLevel(new Statistics.Builder()
                        .withStrength(new Strength(2))
                        .withConstitution(new Constitution(2))
                        .withDexterity(new Dexterity(1))
                        .withIntelligence(new Intelligence(0))
                        .build()
                )
                .withGlyph(Assets.Glyphs.WARRIOR)
                .build();
    }

    public static Job wizard() {
        return new Builder("Wizard")
                .withBaseStats(new Statistics.Builder()
                        .withStrength(new Strength(8))
                        .withConstitution(new Constitution(8))
                        .withDexterity(new Dexterity(10))
                        .withIntelligence(new Intelligence(16))
                        .build())
                .withStatsPerLevel(new Statistics.Builder()
                        .withStrength(new Strength(0))
                        .withConstitution(new Constitution(0))
                        .withDexterity(new Dexterity(1))
                        .withIntelligence(new Intelligence(2))
                        .build()
                )
                .withGlyph(Assets.Glyphs.WIZARD)
                .build();
    }

    public static Job rogue() {
        return new Builder("Rogue")
                .withBaseStats(new Statistics.Builder()
                        .withStrength(new Strength(10))
                        .withConstitution(new Constitution(12))
                        .withDexterity(new Dexterity(14))
                        .withIntelligence(new Intelligence(10))
                        .build())
                .withStatsPerLevel(new Statistics.Builder()
                        .withStrength(new Strength(1))
                        .withConstitution(new Constitution(1))
                        .withDexterity(new Dexterity(1))
                        .withIntelligence(new Intelligence(1))
                        .build()
                )
                .withGlyph(Assets.Glyphs.ROGUE)
                .build();
    }
}
