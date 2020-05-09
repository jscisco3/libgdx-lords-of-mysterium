package com.jscisco.lom.shelf.entity;

import com.jscisco.lom.shelf.assets.Assets;
import com.jscisco.lom.shelf.domain.Health;
import com.jscisco.lom.shelf.domain.Position;
import com.jscisco.lom.shelf.zone.Stage;

public class Player extends Entity {

    public static class Builder {
        private EntityName name;

        private Stage stage;
        private FieldOfView fieldOfView;
        private Position position;
        private Health health;
        private Energy energy;
        private Inventory inventory;
        private Equipment equipment;
        private Assets.Glyphs glyph;
        private Statistics statistics;
        private Job job;

        public Builder(EntityName name) {
            this.name = name;
        }

        public Builder withStage(Stage stage) {
            this.stage = stage;
            return this;
        }

        public Builder withFieldOfView(FieldOfView fov) {
            this.fieldOfView = fov;
            return this;
        }

        public Builder withPosition(Position position) {
            this.position = position;
            return this;
        }

        public Builder withHealth(Health health) {
            this.health = health;
            return this;
        }

        public Builder withEnergy(Energy energy) {
            this.energy = energy;
            return this;
        }

        public Builder withInventory(Inventory inventory) {
            this.inventory = inventory;
            return this;
        }

        public Builder withEquipment(Equipment equipment) {
            this.equipment = equipment;
            return this;
        }

        public Builder withGlyph(Assets.Glyphs glyph) {
            this.glyph = glyph;
            return this;
        }

        public Builder withStats(Statistics statistics) {
            this.statistics = statistics;
            return this;
        }

        public Builder withJob(Job job) {
            this.job = job;
            return this;
        }

        public Player build() {
            Player player = new Player();
            player.name = name;
            player.stage = stage;
            player.fieldOfView = fieldOfView;
            player.position = position;
            player.health = health;
            player.energy = energy;
            player.inventory = inventory;
            player.equipment = equipment;
            player.glyph = glyph;
            player.job = job;
            player.statistics = statistics;
            return player;
        }
    }

    private Player() {
    }
}
