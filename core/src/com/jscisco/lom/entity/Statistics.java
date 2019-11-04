package com.jscisco.lom.entity;

public class Statistics {

    private Strength strength;
    private Intelligence intelligence;
    private Dexterity dexterity;
    private Constitution constitution;

    private Statistics() {

    }

    public Strength getStrength() {
        return strength;
    }

    public Intelligence getIntelligence() {
        return intelligence;
    }

    public Dexterity getDexterity() {
        return dexterity;
    }

    public Constitution getConstitution() {
        return constitution;
    }

    public static class Builder {
        private Strength strength;
        private Intelligence intelligence;
        private Dexterity dexterity;
        private Constitution constitution;

        public Builder() {
            this.strength = new Strength();
            this.intelligence = new Intelligence();
            this.dexterity = new Dexterity();
            this.constitution = new Constitution();
        }

        public Builder withStrength(Strength strength) {
            this.strength = strength;
            return this;
        }

        public Builder withIntelligence(Intelligence intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder withDexterity(Dexterity dexterity) {
            this.dexterity = dexterity;
            return this;
        }

        public Builder withConstitution(Constitution constitution) {
            this.constitution = constitution;
            return this;
        }

        public Statistics build() {
            Statistics statistics = new Statistics();
            statistics.strength = this.strength;
            statistics.intelligence = this.intelligence;
            statistics.dexterity = this.dexterity;
            statistics.constitution = this.constitution;
            return statistics;
        }
    }
}
