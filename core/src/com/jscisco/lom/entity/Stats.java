package com.jscisco.lom.entity;

public class Stats {

    private int strength;
    private int intelligence;
    private int dexterity;
    private int constitution;

    private Stats() {

    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public static class Builder {
        private int strength;
        private int intelligence;
        private int dexterity;
        private int constitution;

        public Builder() {
            this.strength = 0;
            this.intelligence = 0;
            this.dexterity = 0;
            this.constitution = 0;
        }

        public Builder withStrength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder withIntelligence(int intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder withDexterity(int dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public Builder withConstitution(int constitution) {
            this.constitution = constitution;
            return this;
        }

        public Stats build() {
            Stats stats = new Stats();
            stats.strength = this.strength;
            stats.intelligence = this.intelligence;
            stats.dexterity = this.dexterity;
            stats.constitution = this.constitution;
            return stats;
        }
    }
}
